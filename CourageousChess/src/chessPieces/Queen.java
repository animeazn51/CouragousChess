package chessPieces;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Queen extends ChessPiece{
	
	private int [] paths = {-1,-1, -1, 1, 1, -1, 1, 1, 0, -1, 0, 1, 1, 0, -1, 0};
	private int nextX, nextY;
	
	public Queen(int x, int y, String name, String color, String gridColor) {
		super(x,y, name, color, gridColor);
	}

	public void draw(Graphics2D g2, int gridSize) {
		super.draw(g2, gridSize);
		
		/*Draw Main Body*/
		Polygon mainBody = new Polygon();
		mainBody.addPoint((int)(locationX + 7*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)));//Left spike
		mainBody.addPoint((int)(locationX + 11.5*(gridSize/32.0)),(int)(locationY + 16*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 16*(gridSize/32.0)),(int)(locationY + 7*(gridSize/32.0)));//Center Spike
		mainBody.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 16*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 25*(gridSize/32.0)),(int)(locationY + 11*(gridSize/32.0)));//Right Spike
		mainBody.addPoint((int)(locationX + 22*(gridSize/32.0)),(int)(locationY + 25*(gridSize/32.0)));
		mainBody.addPoint((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 25*(gridSize/32.0)));
		
		//Draw that line under the queen
		Rectangle rect = new Rectangle((int)(locationX + 10*(gridSize/32.0)),(int)(locationY + 26.5*(gridSize/32.0)),(int)(22*(gridSize/32.0) - 10*(gridSize/32.0)), (int)(2*(gridSize/32.0)));
				
		// Draw Left Center Spike
		Polygon leftCenter = new Polygon();
		leftCenter.addPoint((int)(locationX + 11.5*(gridSize/32.0)),(int)(locationY + 16*(gridSize/32.0)));
		leftCenter.addPoint((int)(locationX + 10.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));
		leftCenter.addPoint((int)(locationX + 10.5*(gridSize/32.0)),(int)(locationY + 8*(gridSize/32.0)));//Left-Center spike
		leftCenter.addPoint((int)(locationX + 13.5*(gridSize/32.0)),(int)(locationY + 12*(gridSize/32.0)));	
		
		//Draw Right Center Spike
		Polygon rightCenter = new Polygon();
		rightCenter.addPoint((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 16*(gridSize/32.0)));
		rightCenter.addPoint((int)(locationX + 21.5*(gridSize/32.0)),(int)(locationY + 15*(gridSize/32.0)));
		rightCenter.addPoint((int)(locationX + 21.5*(gridSize/32.0)),(int)(locationY + 8*(gridSize/32.0)));//Right-Center spike
		rightCenter.addPoint((int)(locationX + 18.5*(gridSize/32.0)),(int)(locationY + 12*(gridSize/32.0)));
		
		// Draws the Circles on the spikes
		Ellipse2D.Double leftSpike = new Ellipse2D.Double((int)(locationX + 6*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)),(int)(gridSize/12.0),(int)(gridSize/12.0));
		Ellipse2D.Double leftCenterSpike = new Ellipse2D.Double((int)(locationX + 9.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)),(int)(gridSize/12.0),(int)(gridSize/12.0));
		Ellipse2D.Double centerSpike = new Ellipse2D.Double((int)(locationX + 15*(gridSize/32.0)),(int)(locationY + 5*(gridSize/32.0)),(int)(gridSize/12.0),(int)(gridSize/12.0));
		Ellipse2D.Double rightCenterSpike = new Ellipse2D.Double((int)(locationX + 20.5*(gridSize/32.0)),(int)(locationY + 6*(gridSize/32.0)),(int)(gridSize/12.0),(int)(gridSize/12.0));
		Ellipse2D.Double rightSpike = new Ellipse2D.Double((int)(locationX + 24*(gridSize/32.0)),(int)(locationY + 9*(gridSize/32.0)),(int)(gridSize/12.0),(int)(gridSize/12.0));
		
		if (color.equals("Black")) 
			g2.setColor(Color.BLACK); 
		else
			g2.setColor(Color.WHITE);
		g2.fillPolygon(mainBody);
		g2.fillPolygon(leftCenter);
		g2.fillPolygon(rightCenter);
		g2.fill(leftSpike);
		g2.fill(centerSpike);
		g2.fill(rightSpike);
		g2.fill(leftCenterSpike);
		g2.fill(rightCenterSpike);
		g2.fill(rect);
		
		if (color.equals("Black"))
			g2.setColor(Color.WHITE); 
		else
			g2.setColor(Color.BLACK);
		g2.drawPolygon(mainBody);
		g2.drawPolygon(leftCenter);
		g2.drawPolygon(rightCenter);
		g2.draw(leftSpike);
		g2.draw(rightSpike);
		g2.draw(centerSpike);
		g2.draw(leftCenterSpike);
		g2.draw(rightCenterSpike);
		g2.draw(rect);
	}
	
	public ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess) {
		this.setSelected(true);
		for(int i = 0; i< paths.length; i+=2)
			chess = nextMoveSet(chess,xPos,yPos,paths[i],paths[i+1]);
		
		return chess;
	}
	
	public ArrayList<ChessAttributes> nextMoveSet(ArrayList<ChessAttributes> chess, int currentX, int currentY, int moveX, int moveY) {
		nextX = currentX + moveX;
		nextY = currentY + moveY;
		ChessAttributes piece = getChessPiece(chess,nextX, nextY);
		
		if(nextX >= 0 && nextX <= 7 && nextY >= 0 && nextY <= 7){
			if(this.getColor().equals(piece.getColor()))
				return chess;
			else if(!(this.getColor().equals(piece.getColor())) && !(piece.getColor().equals("Blank"))){
				piece.setSelected(true);
				kingCheck(this.getChessPiece(chess, xPos, yPos),piece);
				return chess;
			}
			else{
				piece.setSelected(true);
				return nextMoveSet(chess,nextX,nextY,moveX,moveY);
		   	}
		}
		return chess;
	}
}
