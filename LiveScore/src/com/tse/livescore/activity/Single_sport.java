package com.tse.livescore.activity;

import org.json.JSONException;

import com.tse.livescore.util.GetLives;

import android.os.Bundle;
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
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

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
		switch (resultCode) { // resultCode为回传的标记，我在B中回传的是RESULT_OK
		case RESULT_OK:
			Bundle b = data.getExtras(); // data为B中回传的Intent
			int changed = b.getInt("changed");// str即为回传的值"Hello, this is B speaking"
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
		menu.add(0, MENU_Update, 0, "Fresh");
		menu.add(0, MENU_Quit, 0, "End");
		menu.add(0, MENU_ABOUT, 0, "About...");
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
		Toast.makeText(this, "Fresh successful", Toast.LENGTH_LONG).show();
	}

}
