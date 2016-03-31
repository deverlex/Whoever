package vn.whoever.support.utils;

import java.awt.Point;
import java.util.Date;
import java.util.Random;

public class GenerateSsoID {
	
	private static GenerateSsoID generateSsoID = new GenerateSsoID();
	
	public static void main(String[] args) {
		System.out.println(GenerateSsoID.getInstance().getSsoId());
	}
	
	public static GenerateSsoID getInstance() {
		return generateSsoID;
	}
	
	Point point;
	
	public String getSsoId() {
		Random random = new Random();
		Date date = new Date();
		long time = date.getTime() % 100000000L;
		int ird = random.nextInt(8999) + 1000;
		System.out.println(ird);
		long ssoId = time * 10000L + ird;
		return String.valueOf(ssoId);
	}
}
