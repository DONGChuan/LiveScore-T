package com.tse.livescore.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import com.tse.livescore.activity.R;


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
	
	public String getScoreEquipe1(int i){
		try {
			return jsonArray.getJSONObject(i).getString("scoreEquipe1");
		} catch (JSONException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	public String getScoreEquipe2(int i) {
		try {
			return jsonArray.getJSONObject(i).getString("scoreEquipe2");
		} catch (JSONException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	public String getNom(int i) {
		try {
			return jsonArray.getJSONObject(i).getString("nom");
		} catch (JSONException e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	
	public int getId(int id) {
		try {
			return jsonArray.getJSONObject(id).getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public List<String> getListe() {
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0;i<size();i++){
			list.add(getNom(i));
		}
		return list;
	}
	
	public List<String> getListeSport(int ID) throws JSONException{
		
		ArrayList<String> list=new ArrayList<String>();
		String a = null ;
		
		for(int i=0;i<size();i++){
			if(jsonArray.getJSONObject(i).getJSONObject("sport").getInt("id") == ID )	{
				a = getNom(i);
				for(int i1=0;i1<15-getNom(i).length();i1++){
					a+=" ";
				}
				a+= getScoreEquipe1(i) + ":"+ getScoreEquipe2(i);
				list.add(a);
			}
				
		}
		return list;
	}
	
public List<Map<String,Object>> getListeSport2(int ID) {		
		ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		String a = null ;
		
		for(int i=0;i<size();i++){
			
			int id2=-1;
			try {
				id2=jsonArray.getJSONObject(i).getJSONObject("sport").getInt("id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if( id2== ID ){
				a = getNom(i);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", a);
				map.put("score", getScoreEquipe1(i) + ":"+ getScoreEquipe2(i));
				list.add(map);
			}
				
		}
		return list;
	}
	
	public List<String> getListeSportID(int ID) {
		
		ArrayList<String> list=new ArrayList<String>();
		
		for(int i=0;i<size();i++){
			int id2=-1;
			try {
				id2=jsonArray.getJSONObject(i).getJSONObject("sport").getInt("id");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if( id2== ID )	
				list.add(String.valueOf(getId(i)));
		}
		return list;
	}
}
