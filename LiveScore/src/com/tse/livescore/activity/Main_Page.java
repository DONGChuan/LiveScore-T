package com.tse.livescore.activity;


import org.json.JSONException;
import com.tse.livescore.util.GetLives;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Main_Page extends Activity {
	
	private ListView listView_football;
	private ListView listView_basketball;
	private ListView listView_rugby;
	
	GetLives lives;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main__page);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads()
		.detectDiskWrites()
		.detectNetwork() 
		.penaltyLog() 
		.build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects() 
		.penaltyLog() 
		.penaltyDeath()
		.build());
		
		//if(checkInternet.isNetworkAvailable(this)){
			findViews();
			setList();	
		/*}else{
			new AlertDialog.Builder(this).setTitle(R.string.warning)
			.setMessage(R.string.internet_error)
			.setPositiveButton("Ok",
					new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface dialog, int which){
							// TODO Auto-generated method stub
						}
				}).show();
		}*/
				
	}

	private void findViews() {
		listView_football =(ListView) this.findViewById(R.id.football_listview);
		listView_rugby =(ListView) this.findViewById(R.id.rugby_listview);
		listView_basketball =(ListView) this.findViewById(R.id.basketball_listview);
	}
	
	private void setList() {
		
		try {
			lives = new GetLives();
			
			ArrayAdapter<String> arrayAdapter_football=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lives.getListeSport(1));
			listView_football.setAdapter(arrayAdapter_football);
			setOnItemClickListener(listView_football,1);
			
			ArrayAdapter<String> arrayAdapter_rugby=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lives.getListeSport(2));
			listView_rugby.setAdapter(arrayAdapter_rugby);
			setOnItemClickListener(listView_rugby,2);
			
			ArrayAdapter<String> arrayAdapter_basketball=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lives.getListeSport(3));
			listView_basketball.setAdapter(arrayAdapter_basketball);
			setOnItemClickListener(listView_basketball,3);
			
			Toast.makeText(this, "º”‘ÿ≥…π¶", Toast.LENGTH_LONG).show();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setOnItemClickListener(ListView list, final int ID) {
			list.setOnItemClickListener(new OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					Bundle bundle=new Bundle();
					try {
						bundle.putInt("id", Integer.parseInt(lives.getListeSportID(ID).get(arg2)));
					} catch (JSONException e) {
						e.printStackTrace();
					}
					Intent intent=new Intent(Main_Page.this,Detail_Live_Page.class);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
	}

	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_Quit = Menu.FIRST + 1;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_ABOUT, 0, "About...");
		menu.add(0, MENU_Quit, 0, "End");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
			case MENU_ABOUT:
				openOptionsDialog();
				break;
			case MENU_Quit :
				finish();
				break;
		}
		return true;
	}
	
	private void openOptionsDialog() {
		
		new AlertDialog.Builder(this).setTitle(R.string.about_title)
		.setMessage(R.string.about_msg)
		.setPositiveButton("Ok",
				new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						// TODO Auto-generated method stub
					}
			}).show();
	}
	
}
