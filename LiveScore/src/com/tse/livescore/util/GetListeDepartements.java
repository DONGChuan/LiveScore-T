package com.tse.livescore.util;

public class GetListeDepartements extends GetListe{

	public GetListeDepartements() throws Exception {
		this.url="http://live-score.sqli.cloudbees.net/livescore/departements";
		this.initial();
	}
}
