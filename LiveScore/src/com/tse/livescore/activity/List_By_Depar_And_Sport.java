package com.tse.livescore.activity;


import com.tse.livescore.util.GetListeDepartements;
import com.tse.livescore.util.GetListeSports;
import com.tse.livescore.util.GetLivesByDepartAndSport;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class List_By_Depar_And_Sport extends Activity {
	GetListeSports gls;
	GetListeDepartements gld;
	int idSport;
	int idDepartement;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list__by__depar__and__sport);
		
		try {
			gls=new GetListeSports();
			ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gls.getListe());
			Spinner spinner=(Spinner) this.findViewById(R.id.sports);
			spinner.setAdapter(arrayAdapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
							idSport=gls.getId(arg2);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					idSport=1;
				}
			});
			
			gld=new GetListeDepartements();
			ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gld.getListe());
			Spinner sp2=(Spinner) this.findViewById(R.id.departements);
			sp2.setAdapter(arrayAdapter2);
			sp2.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
						idDepartement=gld.getId(arg2);
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					idDepartement=1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ImageButton bn=(ImageButton)this.findViewById(R.id.button);
		bn.setOnClickListener(new Button.OnClickListener(){
			GetLivesByDepartAndSport lives;
			@Override
			public void onClick(View v) {
				try {
					lives = new GetLivesByDepartAndSport(idDepartement,idSport);
					ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(List_By_Depar_And_Sport.this,android.R.layout.simple_list_item_1,lives.getListe());
					ListView list=(ListView) findViewById(R.id.lives);
					list.setAdapter(arrayAdapter);
					list.setOnItemClickListener(new OnItemClickListener(){
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
							Bundle bundle=new Bundle();
								bundle.putInt("id", lives.getId(arg2));
							Intent intent=new Intent(List_By_Depar_And_Sport.this,Detail_Live_Page.class);
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
		//getMenuInflater().inflate(R.menu.activity_list__by__depar__and__sport,
			//	menu);
		return true;
	}

}
