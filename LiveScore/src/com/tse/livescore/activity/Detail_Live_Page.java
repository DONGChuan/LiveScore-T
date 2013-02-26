package com.tse.livescore.activity;

import java.util.ArrayList;

import com.tse.livescore.util.AjoutEvenement;
import com.tse.livescore.util.GetDetailLive;
import com.tse.livescore.util.MAJScore;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.tse.livescore.util.*;;
public class Detail_Live_Page extends Activity {
	GetDetailLive live;
	int id;
	int scoreEquipe1;
	int scoreEquipe2;
	int changed=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail__live__page);
		
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		id=bundle.getInt("id");
		
		afficher();
		score();
		evenement();
		
	}
		
	
	@Override
	public void finish() {
		Bundle bundle = new Bundle();
		bundle.putInt("changed",changed);
		Intent aintent = new Intent(Detail_Live_Page.this, Single_sport.class);
		aintent.putExtras(bundle);
		setResult(RESULT_OK,aintent);
		super.finish();
	}



	private void afficher(){
		try {
			live=new GetDetailLive(id);
			TextView tv=(TextView) this.findViewById(R.id.sport);
			tv.setText(live.getSportNom());
			tv=(TextView) this.findViewById(R.id.date);
			tv.setText(live.getDebut());
			tv=(TextView) this.findViewById(R.id.competition);
			tv.setText(live.getCompititionNom());
			tv=(TextView) this.findViewById(R.id.latitude);
			tv.setText("latitude : "+live.getLatitude());
			tv=(TextView) this.findViewById(R.id.longitude);
			tv.setText("longitude : "+live.getLongitude());
			tv=(TextView) this.findViewById(R.id.equipe1);
			tv.setText(live.getEquipe1());
			tv=(TextView) this.findViewById(R.id.equipe2);
			tv.setText(live.getEquipe2());
			tv=(TextView) this.findViewById(R.id.score1);
			tv.setText(live.getScoreEquipe1());
			tv=(TextView) this.findViewById(R.id.score2);
			tv.setText(live.getScoreEquipe2());
			tv=(TextView) this.findViewById(R.id.shortDes);
			tv.setText(live.getShortDescription());
			tv=(TextView) this.findViewById(R.id.longDes);
			tv.setText(live.getLongDescription());
			tv=(TextView) this.findViewById(R.id.nomCommen);
			tv.setText(live.getCommentateur());
			
			
			ListView listView=(ListView) this.findViewById(R.id.comentaires);
			ArrayList<String> list=new ArrayList<String>();
			for(int i=0;i<live.evenementSize();i++){
				list.add(live.getEvenement(i));
			}
			ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
			listView.setAdapter(arrayAdapter);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void score(){
		scoreEquipe1=Integer.parseInt(live.getScoreEquipe1()) ;
		scoreEquipe2=Integer.parseInt(live.getScoreEquipe2());
		ImageButton e1plus1=(ImageButton) this.findViewById(R.id.e1plus1);
		e1plus1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				scoreEquipe1++;
				changed=1;
				try {
					MAJScore.execute(live.getId(), scoreEquipe1, scoreEquipe2);
					maj();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ImageButton e1moins1=(ImageButton)this.findViewById(R.id.e1moins1);
		e1moins1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				scoreEquipe1--;
				changed=1;
				try {
					MAJScore.execute(live.getId(), scoreEquipe1, scoreEquipe2);
					maj();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		
		ImageButton e2plus1=(ImageButton)this.findViewById(R.id.e2plus1);
		e2plus1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				scoreEquipe2++;
				changed=1;
				try {
					MAJScore.execute(live.getId(), scoreEquipe1, scoreEquipe2);
					maj();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		
		ImageButton e2moins1=(ImageButton)this.findViewById(R.id.e2moins1);
		e2moins1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				scoreEquipe2--;
				changed=1;
				try {
					MAJScore.execute(live.getId(), scoreEquipe1, scoreEquipe2);
					maj();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
	}
	
	private void evenement(){
		Button commenter=(Button) this.findViewById(R.id.commenter);
		commenter.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et=(EditText) findViewById(R.id.commentaire);
				if(et.getText().toString().length()==0) 
					return;
				AjoutEvenement.execute(id, et.getText().toString());
				maj();
			}
		});
	}
	
	private void maj(){
		Bundle bundle=new Bundle();
		bundle.putInt("id", id);
		Intent intent=new Intent(this,Detail_Live_Page.class);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}

	protected static final int MENU_Update = Menu.FIRST;
	protected static final int MENU_Delete = Menu.FIRST + 1;
	protected static final int MENU_Quit = Menu.FIRST + 2;
	protected static final int MENU_ABOUT = Menu.FIRST + 3;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(3, MENU_Update, 0, "Fresh");
		menu.add(3, MENU_Delete, 0, "Delete");
		menu.add(3, MENU_Quit, 0, "End");
		menu.add(3, MENU_ABOUT, 0, "About...");
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
		case MENU_Delete:
			delete();
			break;
		}
		return true;
	}
	
	private boolean delete(){
		
		try {
			return HttpUtil.DeleteRequest(this.id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
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
		maj();
		Toast.makeText(this, "Fresh successful", Toast.LENGTH_LONG).show();
	}

}
