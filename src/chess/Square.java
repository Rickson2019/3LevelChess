package chess;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * The class does everything related to Squares(tiles)
 * @author JudaoZHong
 * @version 1.0
 */
public class Square extends Rectangle{
    static double SQRsize = 60.0;

    /* Test whether the tile being used */
    boolean isOccupied = false;
    /* The status of the tile.*/
    boolean isActive = false;
    /* the row of the tile */
    int SQR_Y;
    /* the column of the tile */
    int SQR_X;
    
    Board board;
    
    
    /* Piece object */
    Piece piece;
    /* x coordinate of the tile */
    private double xCoor;
    /* y coordinate of the tile */
    private double yCoor;
    private Color color;
 
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /* Square constructor */
    public Square(double xCoor, double yCoor, Color color,Board board) {
        //recall rectangle constructor
        //x, y tile
        super(xCoor * SQRsize, yCoor * SQRsize, SQRsize, SQRsize);

        this.xCoor = xCoor;
        this.yCoor = yCoor;
        setFill(color);
        this.color = color;
        this.board = board;
    }

    /**
     * @return the isOccupied
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * @param isOccupied the isOccupied to set
     */
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    /**
     * @return the row
     */
    public int getSQR_Y() {
        return SQR_Y;
    }

    /**
     * @param row the row to set
     */
    public void setSQR_Y(int row) {
        this.SQR_Y = row;
    }

    /**
     * @return the column
     */
    public int getSQR_X() {
        return SQR_X;
    }

    /**
     * @param column the column to set
     */
    public void setSQR_X(int column) {
        this.SQR_X = column;
    }

    /**
     * @return the xCoor
     */
    public double getxCoor() {
        return xCoor;
    }

    /**
     * @param xCoor the xCoor to set
     */
    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    /**
     * @return the yCoor
     */
    public double getyCoor() {
        return yCoor;
    }

    /**
     * @param yCoor the yCoor to set
     */
    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }


    
    /**
     * @param piece the piece to set
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * @return the piece
     */
    public Piece returnPiece() {
        return piece;
    }
    
    public void setActive(boolean active) {
        isActive = active; 
        
    }
    public boolean getActive() {
        return isActive;
        
    }
}
