package com.tse.livescore.util;

import org.json.JSONException;

public class GetListeCompetitions extends GetListe{
	
	@Override
	public String getNom(int i) throws JSONException{
		return jsonArray.getJSONObject(i).getString("libelle");
	}

	public GetListeCompetitions() throws Exception {
		this.url="http://live-score.sqli.cloudbees.net/livescore/competitions";
		this.initial();
	}

}
