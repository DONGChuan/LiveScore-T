package com.tse.livescore.util;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;


public class GetListe {
	protected String url;
	protected JSONArray jsonArray;
	
	GetListe() {}
	
	protected void initial() throws Exception{
		String s=HttpUtil.getRequest(url);
		jsonArray=new JSONArray(s);
	}
	
	public int size(){
		return jsonArray.length();
	}
	
	public String getNom(int i) throws JSONException{
		return jsonArray.getJSONObject(i).getString("nom");
	}
	
	public int getId(int id) throws JSONException{
		return jsonArray.getJSONObject(id).getInt("id");
	}
	
	public List<String> getListe() throws JSONException{
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0;i<size();i++){
			list.add(getNom(i));
		}
		return list;
	}
	
	public List<String> getListeSport(int ID) throws JSONException{
		
		ArrayList<String> list=new ArrayList<String>();
		
		for(int i=0;i<size();i++){
			if(jsonArray.getJSONObject(i).getJSONObject("sport").getInt("id") == ID )	
				list.add(getNom(i));
		}
		return list;
	}
	
	public List<String> getListeSportID(int ID) throws JSONException{
		
		ArrayList<String> list=new ArrayList<String>();
		
		for(int i=0;i<size();i++){
			if(jsonArray.getJSONObject(i).getJSONObject("sport").getInt("id") == ID )	
				list.add(String.valueOf(getId(i)));
		}
		return list;
	}
}
