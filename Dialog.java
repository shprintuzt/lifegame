package lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dialog extends JButton implements ActionListener {

	public JFrame frame = new JFrame();
	public BoardModel model = new BoardModel();
	
	public Dialog(JFrame f, BoardModel m) {
		frame = f;
		model = m;
		this.setText("Dialog");
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		model.width = Integer.parseInt(JOptionPane.showInputDialog(frame,"width"));
		model.height = Integer.parseInt(JOptionPane.showInputDialog(frame,"height"));
		model.clearSell();
		model.fireUpdate();
	}
}
