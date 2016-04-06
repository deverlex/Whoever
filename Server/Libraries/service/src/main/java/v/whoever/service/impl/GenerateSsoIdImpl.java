package v.whoever.service.impl;

import java.util.Date;
import java.util.Random;

import vn.whoever.service.GenerateSsoId;
import vn.whoever.service.KeyCode;

public class GenerateSsoIdImpl implements GenerateSsoId {
	
	private GenerateSsoIdImpl() {}
	
	Random random = new Random();
	
	private static GenerateSsoId ssoId = new GenerateSsoIdImpl();
	
	public static GenerateSsoId getId() {
		return ssoId;
	}

	public String getSsoId() {
		return generateId();
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return generateId();
	}
	
	private synchronized String generateId() {
		String id = "";
		String strTime = String.valueOf((new Date()).getTime());
		String[] strNumber = strTime.split("");
		int length = strNumber.length;
		int rdom;
		int choiceArr;
		for(int i = 0; i < 16; ++i) {
			if(i <  length) {
				rdom = random.nextInt(16) + Integer.valueOf(strNumber[i]);
				if (rdom > 9) {
					choiceArr = random.nextInt(2);
					if (choiceArr < 1) {
						id += KeyCode.normalCode[rdom];
					} else
						id += KeyCode.capitalCode[rdom];
				} else
					id += KeyCode.numberCode[rdom];
			} else {
				rdom = random.nextInt(25);
				if (rdom > 9) {
					choiceArr = random.nextInt(2);
					if (choiceArr < 1) {
						id += KeyCode.normalCode[rdom];
					} else
						id += KeyCode.capitalCode[rdom];
				} else
					id += KeyCode.numberCode[rdom];
			}
		}
		return id;
	}
	
	
}
