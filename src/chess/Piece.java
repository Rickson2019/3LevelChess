package chess;

import java.util.ArrayList;

import javafx.scene.Group;


/**
 * Chess Piece class is a class with functions 
 * related to parent class of piece.
 * @author Judao Zhong
 * @version 1.0
 *
 */
public abstract class Piece extends Group {
    protected double xPos;
    protected double yPos;
    public Player player;
    public Square square;
    
    public Piece(Player player, double x, double y) {
      this.xPos = x;
      this.yPos = y;
      this.player = player;
      
      
    }
    public Player getOwner() {
      return player;
      
    }
    public void setSquare(Square square) {
      this.square = square;   
    }
    public Square getSquare() {
      return square;
    }
    

    public abstract void setCaptured();
    
//    public abstract boolean validMove();
    public abstract void move(int Xcol, int Yrow, Board board);
    

   

//    public abstract ArrayList<Square> movePattern(Player player,Square thisSquare);
    public ArrayList<Square> movePattern(Player player, Square thisSquare, Board board) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
