package com.tse.livescore.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tse.livescore.util.GetLives;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Main_Page extends Activity {

	private ListView listView_sports;

	GetLives lives;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main__page);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
				.build());

		initalViews();

	}

	private void initalViews() {
		
		listView_sports = (ListView) this.findViewById(R.id.sports_listview);
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.item_sport, new String[] { "title", "img" },
				new int[] { R.id.title, R.id.img });
		listView_sports.setAdapter(adapter);
		listView_sports.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putInt("id", position + 1);
				bundle.putString("sport_name",(String) getData().get(position).get("title"));
				Intent intent = new Intent(Main_Page.this, Single_sport.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "Football");
		map.put("img", R.drawable.football);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "Rugby");
		map.put("img", R.drawable.rugby);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "Basketball");
		map.put("img", R.drawable.basketball);
		list.add(map);

		return list;
	}



	protected static final int MENU_Quit = Menu.FIRST ;
	protected static final int MENU_ABOUT = Menu.FIRST + 1 ;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(1, MENU_Quit, 0, "Quitter").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		menu.add(1, MENU_ABOUT, 0, "A propos ...").setIcon(android.R.drawable.ic_menu_info_details);		
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
			System.exit(0);
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
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
        	 AlertDialog.Builder alertbBuilder=new AlertDialog.Builder(this);
             alertbBuilder.setMessage("Vous voulez vraiment quitter?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                     
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                    	 Main_Page.this.finish();
                     }
             }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                     
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                             
                     }
             }).create();
             alertbBuilder.show();
            return false;  
        }  
        return false;  
    }  

}
