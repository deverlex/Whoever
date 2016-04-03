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
	
	public static GenerateId getId() {
		return genToken;
	}
	
	private synchronized String getId(String type) {
		String id = properties.getProperty("id.Header") + type;
		String strTime = String.valueOf((new Date()).getTime());
		String[] strNumber = strTime.split("");
		int length = strNumber.length;
		int rdom;
		int choiceArr;
		for (int i = length - 1; i > length - 12; --i) {
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
	
	public synchronized String getIdUser() {
		return getId(properties.getProperty("id.userId"));
	}

	public synchronized String getIdStatus() {
		return getId(properties.getProperty("id.statusId"));
	}

	public synchronized String getIdComment() {
		return getId(properties.getProperty("id.commentId"));
	}

	public synchronized String getIdPhoto() {
		return getId(properties.getProperty("id.photoId"));
	}

	public synchronized String getIdprofile() {
		return getId(properties.getProperty("id.profileId"));
	}

	public synchronized String getIdContact() {
		return getId(properties.getProperty("id.contactId"));
	}

	public synchronized String getIdMessage() {
		return getId(properties.getProperty("id.messageId"));
	}

	public synchronized String getIdGroup() {
		return getId(properties.getProperty("id.groupId"));
	}

	public synchronized String getIdNotify() {
		return getId(properties.getProperty("id.notifyId"));
	}

}
