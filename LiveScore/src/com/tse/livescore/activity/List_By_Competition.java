package com.tse.livescore.activity;


import org.json.JSONException;

import com.tse.livescore.util.GetListeCompetitions;
import com.tse.livescore.util.GetLivesByCompetition;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class List_By_Competition extends Activity {
	GetListeCompetitions glc;
	int idCompetition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list__by__competition);
		
		try {
			glc=new GetListeCompetitions();
			ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,glc.getListe());
			Spinner spinner=(Spinner) this.findViewById(R.id.competitions);
			spinner.setAdapter(arrayAdapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
						try {
							idCompetition=glc.getId(arg2);
						} catch (JSONException e) {
							e.printStackTrace();
						}
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					idCompetition=1;
				}
			});
		}catch(Exception e){
				e.printStackTrace();
			}
			
		
		Button bn=(Button)this.findViewById(R.id.button);
		bn.setOnClickListener(new Button.OnClickListener(){
			GetLivesByCompetition lives;
			@Override
			public void onClick(View v) {
				try {
					lives = new GetLivesByCompetition(idCompetition);
					ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(List_By_Competition.this,android.R.layout.simple_list_item_1,lives.getListe());
					ListView list=(ListView) findViewById(R.id.lives);
					list.setAdapter(arrayAdapter);
					list.setOnItemClickListener(new OnItemClickListener(){
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
								long arg3) {
							Bundle bundle=new Bundle();
							try {
								bundle.putInt("id", lives.getId(arg2));
							} catch (JSONException e) {
								e.printStackTrace();
							}
							Intent intent=new Intent(List_By_Competition.this,Detail_Live_Page.class);
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
		getMenuInflater().inflate(R.menu.activity_list__by__competition, menu);
		return true;
	}

}
