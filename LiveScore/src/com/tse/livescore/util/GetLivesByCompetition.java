package com.tse.livescore.util;

public class GetLivesByCompetition extends GetListe{
	public GetLivesByCompetition(int idCompetition) throws Exception{
		this.url="http://live-score.sqli.cloudbees.net/livescore/livesByCompetition/"+idCompetition;
		this.initial();
	}
}
