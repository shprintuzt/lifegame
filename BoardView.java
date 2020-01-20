package lifegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class BoardView extends JPanel
		implements BoardModelListener, MouseListener, MouseMotionListener {

	public BoardModel model = new BoardModel();

	public int e_x, e_y;
	
	public BoardView(BoardModel m) {
		m.addListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		model = m;
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i=0;i<model.getWidth()+1;i++) {
			g.drawLine(Sell_width()*i+x_slide(), y_slide(), Sell_width()*i+x_slide(),
				Sell_height()*model.getHeight()+y_slide());
		}
		for(int i=0;i<model.getHeight()+1;i++) {
			g.drawLine(x_slide(), Sell_height()*i+y_slide(),
				Sell_width()*model.getWidth()+x_slide(), Sell_height()*i+y_slide());
		}
		g.setColor(Color.GREEN);
		for(int i=0;i<model.getWidth();i++) {
			for(int j=0;j<model.getHeight();j++) {
				if( model.isAlive(i, j) ) g.fillRect(i*this.Sell_width()+x_slide(), j*this.Sell_height()+y_slide(), this.Sell_width(), this.Sell_height());
			}
		}
	}

	public int x_slide() {
		return (this.getWidth() - Sell_width()*model.getWidth())/2;
	}
	
	public int y_slide() {
		return (this.getHeight() - Sell_height()*model.getHeight())/2;
	}
	
	public int Sell_width() {
		int width = this.getWidth();
		return width/model.getWidth();
	}

	public int Sell_height() {
		int height = this.getHeight();
		return height/model.getHeight();
	}
	
	public void updated(BoardModel m) {
		this.repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
	
	}
	
	public void mouseEntered(MouseEvent e) {
	
	}
	
	public void mouseExited(MouseEvent e) {
	
	}
	
	public void mousePressed(MouseEvent e) {
		e_x = ( e.getX() - x_slide() )/Sell_width(); e_y = ( e.getY() - y_slide() )/Sell_height();
		model.switchLife(e_x, e_y);
	}
	
	public void mouseReleased(MouseEvent e) {
	
	}
	
	public void mouseDragged(MouseEvent e) {
		int e_xtemp = ( e.getX() - x_slide() )/Sell_width();
		int e_ytemp = ( e.getY() - y_slide() )/Sell_height();
		if( e_xtemp != e_x || e_ytemp != e_y ) {
			model.switchLife(e_xtemp, e_ytemp);
		}
		e_x = e_xtemp; e_y = e_ytemp;
	}
	
	public void mouseMoved(MouseEvent e) {
	
	}

}
