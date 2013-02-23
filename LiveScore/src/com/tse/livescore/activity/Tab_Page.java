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
        LayoutInflater inflater_tab1 = LayoutInflater.from(this);   
        
        inflater_tab1.inflate(R.layout.tab1, mTabHost.getTabContentView());  
        inflater_tab1.inflate(R.layout.tab1, mTabHost.getTabContentView()); 
        inflater_tab1.inflate(R.layout.tab1, mTabHost.getTabContentView());  
        
        Intent it = new Intent(this, Main_Page.class);
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("List").setContent(it));  
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Search").setContent(it)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("New").setContent(it));  
    }


}
