package vn.whoever.core.local;

public class UpdateRankLanguage implements Runnable, UpdateRank {

	private boolean isRuntime = true;
	private int timeDelay = 86400000; // 1 day

	@Override
	public void run() {
		while (isRuntime) {

			try {
				Thread.sleep(timeDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateRanking() {

	}
}
