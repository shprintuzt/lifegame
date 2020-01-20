package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Auto extends JButton implements ActionListener {

	public BoardModel model = new BoardModel();
	public AutoThread thread;
	public boolean running = false;
	
	public Auto(BoardModel m) {
		model = m;
		this.setText("Auto");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		running = !running;
		if( running ) {
			thread = new AutoThread(model);
			thread.start();
			this.setText("Stop");
		} else {
			thread.interrupt();
			this.setText("Auto");
		}
	}
	
}
