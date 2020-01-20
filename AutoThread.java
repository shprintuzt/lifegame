package lifegame;

public class AutoThread extends Thread {

	public BoardModel model = new BoardModel();
	boolean running = true;

	public AutoThread(BoardModel m) {
		model = m;
	}

	@Override
	public void run() {
		while( running ) {
			model.nextState();
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				running = false;
			}
		}
	}

}
