package com.tse.livescore.activity;

import java.util.Calendar;

import org.json.JSONException;

import com.tse.livescore.util.GetLivesByDate;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker.OnDateChangedListener;

public class List_By_Date extends Activity {
	int year;
	int month;
	int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list__by__date);
		
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
		
		
		Button bn=(Button)this.findViewById(R.id.button);
		bn.setOnClickListener(new Button.OnClickListener(){
			GetLivesByDate lives;
			@Override
			public void onClick(View v) {
				try {
					lives = new GetLivesByDate(year,month,day);
					ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(List_By_Date.this,android.R.layout.simple_list_item_1,lives.getListe());
					ListView list=(ListView) findViewById(R.id.lives);
					list.setAdapter(arrayAdapter);
					list.setOnItemClickListener(new OnItemClickListener(){
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
							Bundle bundle=new Bundle();
								bundle.putInt("id", lives.getId(arg2));
							Intent intent=new Intent(List_By_Date.this,Detail_Live_Page.class);
							intent.putExtras(bundle);
							startActivity(intent);
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list__by__date, menu);
		return true;
	}

}
