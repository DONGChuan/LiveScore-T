package com.tse.livescore.activity;

import java.util.ArrayList;

import com.tse.livescore.util.GetDetailLive;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Detail_Live_Page extends Activity {
	GetDetailLive live;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail__live__page);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		int id=bundle.getInt("id");
		
		ListView listView=(ListView) this.findViewById(R.id.live);
		
		try {
			live=new GetDetailLive(id);
			ArrayList<String> list=new ArrayList<String>();
			//list.add("Mise A jour");
			list.add(live.getDepartementNom());
			list.add(live.getSportNom());
			list.add(live.getCompititionNom());
			list.add(live.getCommentateur());
			list.add(live.getLatitude());
			list.add(live.getLongitude());
			list.add(live.getDebut());
			list.add(live.getEquipe1());
			list.add(live.getScoreEquipe1());
			list.add(live.getEquipe2());
			list.add(live.getScoreEquipe2());
			list.add(live.getShortDescription());
			list.add(live.getLongDescription());
			
			for(int i=0;i<live.evenementSize();i++){
				list.add(live.getEvenement(i));
			}
			ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
			listView.setAdapter(arrayAdapter);
			
			listView.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if(arg2==0){
						Bundle bundle=new Bundle();
						try {
							bundle.putInt("id", live.getId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detail__live__page, menu);
		return true;
	}

}
