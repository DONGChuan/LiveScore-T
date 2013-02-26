package com.tse.livescore.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import com.tse.livescore.util.GetListeCompetitions;
import com.tse.livescore.util.GetListeDepartements;
import com.tse.livescore.util.GetListeSports;
import com.tse.livescore.util.HttpUtil;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker.OnDateChangedListener;

public class New_Live_Page extends Activity {
	int competitionId;
	int departementId;
	int sportId;
	GetListeSports gls;
	GetListeDepartements gld;
	GetListeCompetitions glc;
	int year;
	int month;
	int day;
	JSONObject jsonObject=new JSONObject();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__live__page);
		
		final EditText etComm=(EditText) this.findViewById(R.id.commentateur);
		final EditText etEqu1=(EditText) this.findViewById(R.id.equipe1);
		final EditText etEqu2=(EditText) this.findViewById(R.id.equipe2);
		final EditText etLati=(EditText) this.findViewById(R.id.latitude);
		final EditText etLong=(EditText) this.findViewById(R.id.longitude);
		final EditText etShoD=(EditText) this.findViewById(R.id.shortDescription);
		final EditText etLonD=(EditText) this.findViewById(R.id.longDescription);
		
		competition();
		sport();
		departement(); 
		date();
		
		Button creer=(Button) this.findViewById(R.id.creer);
		creer.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if(etComm.getText().toString().length()==0||
							etEqu1.getText().toString().length()==0 ||
							etEqu2.getText().toString().length()==0|| 
							etLati.getText().toString().length()==0||
							etLong.getText().toString().length()==0|| 
							etShoD.getText().toString().length()==0|| 
							etLonD.getText().toString().length()==0)
						return;
					jsonObject.put("commentateur", etComm.getText().toString());
					jsonObject.put("nom", etEqu1.getText().toString()+" - "+etEqu2.getText().toString());
					jsonObject.put("equipe1", etEqu1.getText().toString());
					jsonObject.put("equipe2", etEqu2.getText().toString());
					jsonObject.put("competitionId", competitionId);
					jsonObject.put("departementId", departementId);
					jsonObject.put("latitude", Double.valueOf(etLati.getText().toString()));
					jsonObject.put("longitude", Double.valueOf(etLong.getText().toString()));
					jsonObject.put("shortDescription", etShoD.getText().toString());
					jsonObject.put("longDescription", etLonD.getText().toString());
					GregorianCalendar gc=new GregorianCalendar(year,month,day);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String debut=sdf.format(gc.getTime());
					jsonObject.put("debut", debut);
					jsonObject.put("sportId", sportId);
					String str=jsonObject.toString();
					try {
						HttpUtil.postRequest(str);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Intent intent=new Intent(New_Live_Page.this,Tab_Page.class);
					startActivity(intent);
					finish();
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	private void competition(){
		try {
			glc=new GetListeCompetitions();
			ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,glc.getListe());
			Spinner spinner=(Spinner) this.findViewById(R.id.competition);
			spinner.setAdapter(arrayAdapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
							competitionId=glc.getId(arg2);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					competitionId=1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sport(){
		try {
			gls=new GetListeSports();
			ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,gls.getListe());
			Spinner spinner=(Spinner) this.findViewById(R.id.sport);
			spinner.setAdapter(arrayAdapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
						sportId=gls.getId(arg2);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					sportId=1;
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void departement(){
		try {
			gld=new GetListeDepartements();
			ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,gld.getListe());
			Spinner sp2=(Spinner) this.findViewById(R.id.departement);
			sp2.setAdapter(arrayAdapter2);
			sp2.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
						departementId=gld.getId(arg2);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					departementId=1;
				}
			});
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void date(){
		DatePicker datePicker=(DatePicker) this.findViewById(R.id.datePicker);
		Calendar c=Calendar.getInstance();
		year=c.get(Calendar.YEAR);
		month=c.get(Calendar.MONTH);
		day=c.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, month, day, new OnDateChangedListener(){
			@Override
			public void onDateChanged(DatePicker view, int newYear,
					int monthOfYear, int dayOfMonth) {
				year=newYear;
			month=monthOfYear;
				day=dayOfMonth;
			}
		});
	}
		
	protected static final int MENU_Quit = Menu.FIRST ;
	protected static final int MENU_ABOUT = Menu.FIRST + 1 ;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(1, MENU_Quit, 0, "End");
		menu.add(1, MENU_ABOUT, 0, "About...");
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ABOUT:
			openOptionsDialog();
			break;
		case MENU_Quit:
			finish();
			break;
		}
		return true;
	}
	
	private void openOptionsDialog() {

		new AlertDialog.Builder(this).setTitle(R.string.about_title)
				.setMessage(R.string.about_msg)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).show();
	}

}
