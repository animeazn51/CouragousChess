package chessPieces;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class King extends ChessPiece{

	private int [] paths = {-1,-1, -1, 1, 1, -1, 1, 1, 0, -1, 0, 1, 1, 0, -1, 0};

	public King(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
	}

	public void move() {

	}

	public boolean collide(ChessAttributes other) {
		return false;
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
	}

	public ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess) {
		this.setSelected(true);
		for(int i=0; i<paths.length; i+=2){
			int nextX = xPos + paths[i];
			int nextY = yPos + paths[i+1];
			if(nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7){
				ChessAttributes piece = getChessPiece(chess,nextX, nextY);
				if(!(this.getColor().equals(piece.getColor())) || piece.getColor().equals("Blank")){
					piece.setSelected(true);
				}
			}
		}
		if(!(this.getCheck()) && !(this.getHasMoved())){
			ChessAttributes rookCastle = getChessPiece(chess, xPos+3, yPos);
			ChessAttributes temp = getChessPiece(chess, xPos+1, yPos);
			ChessAttributes temp2 = getChessPiece(chess, xPos+2, yPos);

			if(rookCastle.getName().equals("Rook") && !(rookCastle.getHasMoved()) && temp.getName().equals("Blank") && temp2.getName().equals("Blank")){
				temp2.setSelected(true);
				castle = true;
			}
			rookCastle = getChessPiece(chess, xPos-4, yPos);
			temp = getChessPiece(chess, xPos-3, yPos);
			temp2 = getChessPiece(chess, xPos-2, yPos);
			ChessAttributes temp3 = getChessPiece(chess, xPos-1, yPos);
			
			if(rookCastle.getName().equals("Rook") && !(rookCastle.getHasMoved()) && temp.getName().equals("Blank") && temp2.getName().equals("Blank") && temp3.getName().equals("Blank")){
				temp2.setSelected(true);
				castle = true;
			}
		}
		return chess;
	}

}
