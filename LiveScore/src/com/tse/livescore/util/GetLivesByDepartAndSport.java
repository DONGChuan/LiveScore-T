package com.tse.livescore.util;

public class GetLivesByDepartAndSport extends GetListe{
	public GetLivesByDepartAndSport(int idDepart,int idSport) throws Exception{
		this.url="http://live-score.sqli.cloudbees.net/livescore/livesByDepartementAndSport/"+idDepart+"/"+idSport;
		this.initial();
	}
}
