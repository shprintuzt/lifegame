package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Undo extends JButton implements ActionListener, BoardModelListener  {

	public BoardModel model = new BoardModel();
	
	public Undo(BoardModel m) {
		model = m;
		m.addListener(this);
		this.setEnabled(false);
		this.setText("Undo");
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		model.preState();
	}
	
	public void updated(BoardModel model) {
		if ( model.sell_list.size() <=  0 ) this.setEnabled(false);
		else this.setEnabled(true);
	}
	
}