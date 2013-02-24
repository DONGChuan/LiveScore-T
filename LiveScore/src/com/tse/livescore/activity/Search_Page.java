package com.tse.livescore.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Search_Page extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search__page);
		
		ListView list=(ListView) this.findViewById(R.id.search);
		ArrayList<String> fonctions=new ArrayList<String>();
		fonctions.add("liste par competition");
		fonctions.add("liste par date");
		fonctions.add("liste par departements et par sports");
		
		ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fonctions);
		list.setAdapter(arrayAdapter);
		list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2){
				case 0:
					Intent intent0=new Intent(Search_Page.this,List_By_Competition.class);
					startActivity(intent0);
					break;
				case 1:
					Intent intent1=new Intent(Search_Page.this,List_By_Date.class);
					startActivity(intent1);
					break;
				case 2:
					Intent intent2=new Intent(Search_Page.this,List_By_Depar_And_Sport.class);
					startActivity(intent2);
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search__page, menu);
		return true;
	}

}
