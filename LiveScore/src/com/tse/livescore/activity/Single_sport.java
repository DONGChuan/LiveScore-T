package com.tse.livescore.activity;

import com.tse.livescore.util.GetLives;
import com.tse.livescore.util.HttpUtil;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Single_sport extends Activity {

	int sport_id;
	String sport_name;
	private ListView lv_sport;
	GetLives lives;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_sport);
		initalViews();
		initaldata();
	}

	private void initalViews() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		sport_id = bundle.getInt("id");
		sport_name = bundle.getString("sport_name");
		this.setTitle(sport_name);
		lv_sport = (ListView) this.findViewById(R.id.single_sport);
		
		View layout = this.findViewById(R.id.linearLayout);
		
		switch(sport_id){
		case 1:layout.setBackgroundResource(R.drawable.football_background);break;
		case 2:layout.setBackgroundResource(R.drawable.rugby_background);break;
		case 3:layout.setBackgroundResource(R.drawable.basketball_background);break;
		}
	
	}

	private void initaldata() {
		try {
			lives = new GetLives();
			SimpleAdapter adapter = new SimpleAdapter(this,
					lives.getListeSport2(sport_id), R.layout.item_match,
					new String[] { "title", "score" }, new int[] { R.id.title,
							R.id.score });
			lv_sport.setAdapter(adapter);		
			lv_sport.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Bundle bundle = new Bundle();
					try {
						bundle.putInt("id", Integer.parseInt(lives
								.getListeSportID(sport_id).get(position)));
						Intent intent = new Intent(Single_sport.this,
								Detail_Live_Page.class);
						intent.putExtras(bundle);
						startActivityForResult(intent, 0);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 

				}
				

			});
			
			lv_sport.setOnItemLongClickListener(new OnItemLongClickListener(){

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						final int arg2, long arg3) {

					new AlertDialog.Builder(Single_sport.this).setTitle(R.string.about_title_delete)
					.setMessage(R.string.about_delete)
					.setPositiveButton("Oui",
							new DialogInterface.OnClickListener(){
								@Override
								public void onClick(DialogInterface dialog, int which){
									try {
										HttpUtil.DeleteRequest(arg2);
										update();	
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
						}).setNegativeButton("Non",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which){
									// TODO Auto-generated method stub
								}
							}
						).show();
					
					return false;
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) { // resultCode娑撳搫娲栨导鐘垫畱閺嶅洩顔囬敍灞惧灉閸λ欐稉顓炴礀娴肩姷娈戦弰鐤狤SULT_OK
		case RESULT_OK:
			Bundle b = data.getExtras(); // data娑撶瘔娑擃厼娲栨导鐘垫畱Intent
			int changed = b.getInt("changed");// str閸楀厖璐熼崶鐐扮炊閻ㄥ嫬锟�Hello, this is B speaking"
			if(changed==1){
				initaldata();
			}
			break;
		default:
			break;
		}
	}

	protected static final int MENU_Update = Menu.FIRST;
	protected static final int MENU_Quit = Menu.FIRST + 1;
	protected static final int MENU_ABOUT = Menu.FIRST + 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_Update, 0, "Mise à jour");
		menu.add(0, MENU_Quit, 0, "Quitter");
		menu.add(0, MENU_ABOUT, 0, "A propos...");
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
		case MENU_Update:
			update();
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

	private void update() {
		initaldata();
		Toast.makeText(this, "Mise à jour réussie", Toast.LENGTH_LONG).show();
	}

}
