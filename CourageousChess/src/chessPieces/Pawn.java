package chessPieces;

import java.awt.Graphics2D;

public class Pawn extends ChessPiece{

	public Pawn(int x, int y, String name, String color) {
		super(x,y, name, color);
		System.out.println(x + " " + y + " " + name + " " + color);
	}


	public void move() {
	
	}

	public boolean collide(ChessAttributes other) {
		return false;
	}
 
	public void draw(Graphics2D g2) {
	}

}
