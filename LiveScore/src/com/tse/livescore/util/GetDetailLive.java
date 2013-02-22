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
	
	public GetDetailLive(int idLive) throws Exception{
		String s=HttpUtil.getRequest("http://live-score.sqli.cloudbees.net/livescore/live/"+idLive);
		jsonObject=new JSONObject(s);
		jsonArray=jsonObject.getJSONArray("evenements");
	}
	
	public int getId() throws Exception{
		return jsonObject.getInt("id");
	}
	
	public String getCommentateur(){
		try {
			return jsonObject.getString("commentateur");
		} catch (JSONException e) {
			return " ";
		}
	}
	
	public String getNom() throws JSONException{
		return jsonObject.getString("nom");
	}
	
	public String getEquipe1() throws JSONException{
		return jsonObject.getString("equipe1");
	}
	
	public String getEquipe2() throws JSONException{
		return jsonObject.getString("equipe2");
	}
	
	public String getScoreEquipe1() throws JSONException{
		return jsonObject.getString("scoreEquipe1");
	}
	
	public String getScoreEquipe2() throws JSONException{
		return jsonObject.getString("scoreEquipe2");
	}
	
	public String getCompititionNom(){
		try {
			return jsonObject.getJSONObject("competition").getString("libelle");
		} catch (JSONException e) {
			return " ";
		}
	}
	
	public String getDepartementNom(){
		try {
			return jsonObject.getJSONObject("departement").getString("nom");
		} catch (JSONException e) {
			return " ";
		}
	}
	
	public String getLatitude() throws JSONException{
		return jsonObject.getString("latitude");
	}
	
	public String getLongitude() throws JSONException{
		return jsonObject.getString("longitude");
	}
	
	public String getShortDescription(){
		try {
			return jsonObject.getString("shortDescription");
		} catch (JSONException e) {
			return " ";
		}
	}
	
	public String getLongDescription(){
		try {
			return jsonObject.getString("longDescription");
		} catch (JSONException e) {
			return " ";
		}
	}
	
	public String getDebut() throws ParseException{
		String s;
		try {
			s = jsonObject.getString("dateDebut");
		} catch (JSONException e) {
			return " ";
		}
		DateFormat format=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US);
		Date date=format.parse(s);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sout=sdf.format(date);
		return sout;
	}
	
	public String getSportNom() throws JSONException{
		return jsonObject.getJSONObject("sport").getString("nom");
	}
	
	public int evenementSize(){
		return jsonArray.length();
	}
	
	public String getEvenement(int id) throws JSONException{
		return jsonArray.getJSONObject(id).getString("commentaire");
	}
	
	public int getEvenementId(int i) throws JSONException{
		return jsonArray.getJSONObject(i).getInt("id");
	}
}
