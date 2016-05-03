package vn.whoever.mainserver.service.impl;

public class SortHome implements Runnable {
	
	private boolean isRun;
	
	public void run() {
		isRun = true;
		while(isRun) {
			System.out.println("SortHome running.....");
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
