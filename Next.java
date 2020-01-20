package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Next extends JButton implements ActionListener {

	public BoardModel model = new BoardModel();
	
	public Next(BoardModel m) {
		model = m;
		this.setText("Next");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		model.nextState();
	}
	
}
