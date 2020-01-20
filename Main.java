package lifegame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Lifegame_goto");

		final JPanel base = new JPanel();
		frame.setContentPane(base);
		base.setPreferredSize(new Dimension(400, 300));
		frame.setMinimumSize(new Dimension(200, 200));

		base.setLayout(new BorderLayout());
		final BoardModel model = new BoardModel();
		final BoardView view = new BoardView(model);
		view.setBackground(Color.BLACK);
		base.add(view, BorderLayout.CENTER);

		final JPanel buttonPanel = new JPanel();
		base.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		Next next = new Next(model);
		buttonPanel.add(next);
		
		Undo undo = new Undo(model);
		buttonPanel.add(undo);
		
		Auto auto = new Auto(model);
		buttonPanel.add(auto);
		
		Dialog dialog = new Dialog(frame,model);
		buttonPanel.add(dialog);
		
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		menu.add(file);
		frame.setJMenuBar(menu);
		
		frame.pack();
		frame.setVisible(true);
	}
}
