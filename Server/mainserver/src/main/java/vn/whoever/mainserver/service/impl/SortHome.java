package vn.whoever.mainserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.whoever.mainserver.dao.StatusDao;
import vn.whoever.mainserver.service.HomeService;

/**
 * For insert to Homestatus DB
 * @author spider man
 *
 */
public class SortHome implements Runnable {

	@Autowired
	private StatusDao statusDao;
	
	private boolean isRun;
	
	public void run() {
		isRun = true;
		while(isRun) {
			
			try {
				Thread.sleep(600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Tieu chi de sap xep vao home status
	 * 
	 * Khoang time < 10h
	 * 
	 */
}
