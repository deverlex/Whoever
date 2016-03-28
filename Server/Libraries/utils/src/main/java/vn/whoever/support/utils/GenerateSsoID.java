package vn.whoever.support.utils;

import java.util.Date;
import java.util.Random;

public class GenerateSsoID {
	
	private static GenerateSsoID generateSsoID = new GenerateSsoID();
	
	public static GenerateSsoID getInstance() {
		return generateSsoID;
	}
	
	public String getSsoId() {
		Random random = new Random();
		Date date = new Date();
		long time = date.getTime();
		long intTime = (time%10000000);
		int ird = random.nextInt(99999) + 10000;
		long ssoId = intTime * 100000 + ird;
		return String.valueOf(ssoId);
	}
}
