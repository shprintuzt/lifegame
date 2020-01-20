package lifegame;

import java.util.ArrayList;
import java.util.LinkedList;

public class BoardModel {

	private boolean sell[][] = new boolean[10][10];

	LinkedList<boolean[][]> sell_list = new LinkedList<boolean[][]>();
	
	int width = 10;
	int height = 10;
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isAlive(int x, int y) {
		if( 0 <= x && x < 10 && 0 <= y && y < 10) return sell[x][y];
		else return false;
	}

	public void clearSell() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				sell[i][j] = false;
			}
		}
		while( sell_list.size() > 0 ) {
			sell_list.removeFirst();
		}
	}
	
	private ArrayList<BoardModelListener> listeners =
			new ArrayList<BoardModelListener>();

	public void addListener(BoardModelListener listener) {
		listeners.add(listener);
	}

	public void fireUpdate() {
		for(BoardModelListener listener: listeners) {
			listener.updated(this);
		}
	}

	synchronized public void switchLife(int x, int y) {
		sell_add(sell);
		if( 0 <= x && x < 10 && 0 <= y && y < 10) {
			sell[x][y] = !sell[x][y];
			fireUpdate();
		}
	}
	
	synchronized public void nextState() {
		sell_add(sell);
		boolean sell_temp[][] = new boolean[getWidth()][getHeight()];
		for(int i=0;i<getWidth();i++) {
			for(int j=0;j<getHeight();j++) {
				sell_temp[i][j] = sell[i][j];
				if( sell[i][j] == false ) {
					if( n_neighbor(i,j) == 3 ) sell_temp[i][j] = true;
				} else {
					if( n_neighbor(i,j) != 2 && n_neighbor(i,j) != 3 ) {
						sell_temp[i][j] = false;
					}
				}
			}
		}
		for(int i=0;i<getWidth();i++) {
			for(int j=0;j<getHeight();j++) {
				sell[i][j] = sell_temp[i][j];
			}
		}
		fireUpdate();
	}
	
	public int n_neighbor(int x, int y) {
		int n = 0;
		for(int i=x-1;i<x+2;i++) {
			for(int j=y-1;j<y+2;j++) {
				if(( i != x || j != y ) && isAlive(i,j) ) n++;
			}
		}
		return n;
	}
	
	public void preState() {
		if( sell_list.size() > 0 ) {
			sell = sell_list.getFirst();
			sell_list.removeFirst();
			fireUpdate();
		}
	}
	
	public void sell_add(boolean sell[][]) {
		if( sell_list.size() >= 32 ) {
			sell_list.removeLast();
		}
		boolean b[][] = new boolean[10][10];
		for(int i=0;i<getWidth();i++) {
			for(int j=0;j<getHeight();j++) {
				b[i][j] = sell[i][j];
			}
		}

		sell_list.addFirst(b);
	}

}
