package v.whoever.service.impl;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import vn.whoever.service.IdConfig;
import vn.whoever.service.KeyCode;
import vn.whoever.service.GenerateId;

public class GenerateIdImpl implements GenerateId {

	/**
	 * Header: XX-XX-ABCD.EFGH.IKMN
	 */
	
	Random random = new Random();
	Properties properties = IdConfig.getInstance().getProperties();
	
	private static GenerateId genToken = new GenerateIdImpl();
	private GenerateIdImpl() {}
	
	public static void main(String[] args) {
		System.out.println(GenerateIdImpl.generateId().getId());
	}
	
	public static GenerateId generateId() {
		return genToken;
	}
	
	public synchronized String getId() {
		String id = properties.getProperty("id.Header");
		String strTime = String.valueOf((new Date()).getTime());
		String[] strNumber = strTime.split("");
		int length = strNumber.length;
		int rdom;
		int choiceArr;
		for (int i = length - 1; i > length - 14; --i) {
			rdom = random.nextInt(16) + Integer.valueOf(strNumber[i]);
			if (rdom > 9) {
				choiceArr = random.nextInt(2);
				if (choiceArr < 1) {
					id += KeyCode.normalCode[rdom];
				} else
					id += KeyCode.capitalCode[rdom];
			} else
				id += KeyCode.numberCode[rdom];
		}
		rdom = random.nextInt(7) + Integer.valueOf(strNumber[length - 1]) 
									+ Integer.valueOf(strNumber[length - 2]);
		choiceArr = random.nextInt(2);
		if (choiceArr < 1) {
			id += KeyCode.capitalCode[rdom];
		} else id += KeyCode.normalCode[rdom];
		return id;
	}
}
