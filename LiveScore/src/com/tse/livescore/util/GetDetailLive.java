package com.tse.livescore.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetDetailLive {
	JSONObject jsonObject;
	JSONArray jsonArray;
	
	public GetDetailLive(int idLive) {
		String s;
		try {
			s = HttpUtil.getRequest("http://live-score.sqli.cloudbees.net/livescore/live/"+idLive);
			jsonObject=new JSONObject(s);
			jsonArray=jsonObject.getJSONArray("evenements");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getId() {
		try {
			return jsonObject.getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public String getCommentateur(){
		try {
			return jsonObject.getString("commentateur");
		} catch (JSONException e) {
			return "NULL";
		}
	}
	
	public String getNom(){
		try {
			return jsonObject.getString("nom");
		} catch (JSONException e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	
	public String getEquipe1() {
		try {
			return jsonObject.getString("equipe1");
		} catch (JSONException e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	
	public String getEquipe2() {
		try {
			return jsonObject.getString("equipe2");
		} catch (JSONException e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	
	public String getScoreEquipe1() {
		try {
			return jsonObject.getString("scoreEquipe1");
		} catch (JSONException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	public String getScoreEquipe2() {
		try {
			return jsonObject.getString("scoreEquipe2");
		} catch (JSONException e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	public String getCompititionNom(){
		try {
			return jsonObject.getJSONObject("competition").getString("libelle");
		} catch (JSONException e) {
			return "NULL";
		}
	}
	
	public String getDepartementNom(){
		try {
			return jsonObject.getJSONObject("departement").getString("nom");
		} catch (JSONException e) {
			return "NULL";
		}
	}
	
	public String getLatitude() {
		try {
			return jsonObject.getString("latitude");
		} catch (JSONException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	public String getLongitude() {
		try {
			return jsonObject.getString("longitude");
		} catch (JSONException e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	public String getShortDescription(){
		try {
			return jsonObject.getString("shortDescription");
		} catch (JSONException e) {
			return "NULL";
		}
	}
	
	public String getLongDescription(){
		try {
			return jsonObject.getString("longDescription");
		} catch (JSONException e) {
			return "NULL";
		}
	}
	
	public String getDebut() throws ParseException{
		String s;
		try {
			s = jsonObject.getString("dateDebut");
		} catch (JSONException e) {
			return "NULL";
		}
		DateFormat format=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US);
		Date date=format.parse(s);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sout=sdf.format(date);
		return sout;
	}
	
	public String getSportNom(){
		try {
			return jsonObject.getJSONObject("sport").getString("nom");
		} catch (JSONException e) {
			e.printStackTrace();
			return "NULL";
		}
	}
	
	public int evenementSize(){
		return jsonArray.length();
	}
	
	public String getEvenement(int id) {
		try {
			return jsonArray.getJSONObject(id).getString("commentaire");
		} catch (JSONException e) {
			e.printStackTrace();
			return "NULL";
		}
		
	}
	
	public int getEvenementId(int i) {
		try {
			return jsonArray.getJSONObject(i).getInt("id");
		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
