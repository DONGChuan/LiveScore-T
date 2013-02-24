package com.tse.livescore.util;

import org.json.JSONException;
import org.json.JSONObject;

public class AjoutEvenement {
	public static boolean execute(int id,String s) {
		String url="http://live-score.sqli.cloudbees.net/livescore/live/"+id+"/evenement";
		JSONObject jo=new JSONObject();
		try {
			jo.put("commentaire", s);
			String str=jo.toString();
			return HttpUtil.putRequest(url, str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
