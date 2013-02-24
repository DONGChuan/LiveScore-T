package com.tse.livescore.util;

import org.json.JSONException;
import org.json.JSONObject;

public class MAJScore {
	public static boolean execute(int id,int scoreEquipe1, int scoreEquipe2) throws Exception {
		String url="http://live-score.sqli.cloudbees.net/livescore/live/"+id+"/score";
		JSONObject jsonObject=new JSONObject();
			jsonObject.put("scoreEquipe1", scoreEquipe1);
			jsonObject.put("scoreEquipe2", scoreEquipe2);
			String str=jsonObject.toString();
			return HttpUtil.putRequest(url, str);
	}
}
