package com.tse.livescore.activity;


import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class Tab_Page extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		onCreatTabs();
	}
	
	private void onCreatTabs(){
		
        TabHost mTabHost = getTabHost();
        Intent it = new Intent(this, Main_Page.class);
        Intent newLive=new Intent(this,New_Live_Page.class);
        Intent search=new Intent(this,Search_Page.class);
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Liste", getResources().getDrawable(android.R.drawable.ic_menu_agenda)).setContent(it));  
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Rechercher",getResources().getDrawable(android.R.drawable.ic_menu_search)).setContent(search)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Créer",getResources().getDrawable(android.R.drawable.ic_menu_edit)).setContent(newLive));  
    }


}
