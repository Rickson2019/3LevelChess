package chess;


import javafx.scene.Group;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 * Chess Board is the class with tiles layout and 
 * other functions.
 * @author Judao Zhong
 * @version 2.0
 *
 */
public class Board3 extends Board {
   
//public class Board extends GridPane {    
    /** The tiles. */
    private final Square[][] squares = new Square[SIZE][SIZE];

    /** The Constant SIZE. */
    static final int SIZE = 8;
    static double SQRsize = 60.0;

    /** The selected piece. */
    public Piece selectedPiece;
    
    /** The pointer white move fail. */
    public boolean pointerWhiteMoveFail;
    
    /** The pointer white move success. */
    public boolean whiteMoveSuccess;
    
    /** The pointer black move fail. */
    public boolean blackMoveFail;
    
    /** The pointer black move success. */
    public boolean blackMoveSuccess;
    
    public static int counterBlack = 16;
    
    public static int counterWhite = 16;
    
    public GridPane board = new GridPane();
    /**
     * Instantiates a new board.
     */
    public Board3() {
        boardLv = 3;
        /* Generate all Squares */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i+j) % 2 == 0 )
                squares[i][j] = new Square(i, j, Color.WHEAT,this);
                else 
                    squares[i][j] = new Square(i, j, Color.GREEN,this);
//                board.add(squares[i][j], i, j);
//                board.getColumnConstraints().add(new ColumnConstraints(60));
//                board.getRowConstraints().add(new RowConstraints(60));
            }
        }
        
        /* Add tiles to board */
        //This loop must be merged into the previous one.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                getChildren().addAll(squares[i][j]);
                squares[i][j].setSQR_X(i);
                squares[i][j].setSQR_Y(j);
            }
        }
    }
        
    /**
     * Gets the num rows.
     *
     * @return the num rows
     */
    public int getNumRows() {
        return 8;
        
    }
    
    /**
     * Gets the num cols.
     *
     * @return the num cols
     */
    public int getNumCols() {
        return 8;
        
    }
    
    /**
     * Gets the array.
     *
     * @return the array
     */
    public Square[][] getArray() {
        
        return squares;
    }
    
    /**
     * Gets the tile.
     *
     * @param xcol the x coor
     * @param yrow the y coor
     * @return the tile
     */ 
    public Square getSquare(int xcol,int yrow) {
//        double temp1 = row*SQRsize;
//        int counterx = 0;
//        double temp2 = col*SQRsize;
//        int countery = 0;
//        while (temp1 > SQRsize +0.00) {
//            temp1 = temp1 - SQRsize +0.00;
//            counterx++;        
//            
//        }
//        while (temp2 > SQRsize) {
//            temp2 = temp2 - SQRsize;
//            countery++;              
//        }
      
        return squares[xcol][yrow];      
    }
    
    
    /**
     * Gets the tile.
     *
     * @param raw_X the x coor
     * @param raw_Y the y coor
     * @return the tile
     */
//    public Square getSquareBKUP(double xCoor,double yCoor) {
//        double temp1 = xCoor;
//        int counterx = 0;
//        double temp2 = yCoor;
//        int countery = 0;
//        while (temp1 > SQRsize +0.00) {
//            temp1 = temp1 - SQRsize +0.00;
//            counterx++;        
//            
//        }
//        while (temp2 > SQRsize) {
//            temp2 = temp2 - SQRsize;
//            countery++;              
//        }
//      
//        return squares[counterx][countery];      
//    }
    
    public Square getSquareBY_JAVAFX_Coor(double raw_X,double raw_Y) {
       
        int got_SQR_X = getSquareX(raw_X, raw_Y);


        int got_SQR_Y = getSquareY(raw_X, raw_Y);

      
        return squares[got_SQR_X][got_SQR_Y];    
      
    }
    
    
    
    /**
     * Gets the SQR_X.
     *
     * @param raw_X_Coor the x coor
     * @param raw_Y_Coor the y coor
     * @return the SQR_X
     */
    public int getSquareX(double raw_X_Coor,double raw_Y_Coor) {
        double temp1 = raw_X_Coor;
        int SQR_X = 0;

        while (temp1 > SQRsize) {
            temp1 = temp1 - SQRsize;
            SQR_X++;         
        }

        return SQR_X;      
    }
    
    /**
     * Gets the SQR_Y.
     *
     * @param xCoor the x coor
     * @param yCoor the y coor
     * @return the SQR_Y
     */
    public int getSquareY(double xCoor,double yCoor) {

        double temp2 = yCoor;
        int SQR_Y = 0;

        while (temp2 > SQRsize) {
            temp2 = temp2 - SQRsize;
            SQR_Y++;      
        }
        return SQR_Y;      
    }
    
    

    
    /**
     * Inits the pieces.
     */
    public void initPieces() {

        squares[0][0].setOccupied(true);
        squares[1][0].setOccupied(true);
        squares[2][0].setOccupied(true);
        squares[3][0].setOccupied(true);
        squares[4][0].setOccupied(true);
        squares[5][0].setOccupied(true);
        squares[6][0].setOccupied(true);
        squares[7][0].setOccupied(true);
        
        squares[0][1].setOccupied(true);
        squares[1][1].setOccupied(true);
        squares[2][1].setOccupied(true);
        squares[3][1].setOccupied(true);
        squares[4][1].setOccupied(true);
        squares[5][1].setOccupied(true);
        squares[6][1].setOccupied(true);
        squares[7][1].setOccupied(true);
        
        squares[0][6].setOccupied(true);
        squares[1][6].setOccupied(true);
        squares[2][6].setOccupied(true);
        squares[3][6].setOccupied(true);
        squares[4][6].setOccupied(true);
        squares[5][6].setOccupied(true);
        squares[6][6].setOccupied(true);
        squares[7][6].setOccupied(true);
        
        squares[0][7].setOccupied(true);
        squares[1][7].setOccupied(true);
        squares[2][7].setOccupied(true);
        squares[3][7].setOccupied(true);
        squares[4][7].setOccupied(true);
        squares[5][7].setOccupied(true);
        squares[6][7].setOccupied(true);
        squares[7][7].setOccupied(true);
     
    }
    

}