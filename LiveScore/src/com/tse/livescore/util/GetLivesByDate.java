package com.tse.livescore.util;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class GetLivesByDate extends GetListe{
	public GetLivesByDate(int year,int month,int day) throws Exception {
		
		GregorianCalendar gc=new GregorianCalendar(year,month,day);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String s=sdf.format(gc.getTime());
		this.url="http://live-score.sqli.cloudbees.net/livescore/livesByDate/"+s;
		this.initial();
	}
}
