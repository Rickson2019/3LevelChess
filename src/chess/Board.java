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
public class Board extends Group {
    int boardLv;
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
    public Board() {
        boardLv = 1;
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
    
    public int returnBoardLv(Board board) {
        return board.boardLv;
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
    

    
    /**
     * Select black piece.
     *
     * @param myPiece the piece 1
     */
    public void selectBlackPiece(Piece myPiece) {
        
        try {
            //IF CHOSEN BLACK PIECES
            //SET ALL BLACKS TO ACTIVE
            if(myPiece.getOwner().getColour()==Color.BLACK) {
                myPiece.getSquare().setActive(true);
            
            }
        } catch (Exception e) {
            myPiece.getSquare().setActive(false);
            System.out.println("set active failed (Board 283)");
        }
        
        
       
        try {
            //SET THE SQUAE TO BE NOT OCCUPIED
            if(myPiece.getSquare().getActive() && myPiece.getOwner().getColour() == Color.BLACK) {
                
                
            
            myPiece.getSquare().setOccupied(false);
            myPiece.setSquare(null);
            selectedPiece = myPiece;
            

            }
        } catch (Exception e) {
            myPiece.getSquare().setOccupied(true);
            e.printStackTrace();
        }
    }
    
    /**
     * Select white piece.
     *
     * @param myPiece the piece 1
     */
    public void selectWhitePiece(Piece myPiece) {
        try {
        if(myPiece.getOwner().getColour()==Color.WHITE) {
            try {
                myPiece.getSquare().setActive(true);
            } catch (Exception e) {
//                myPiece.getSquare().setActive(false);
                System.out.println("Set Active failed..");
            }
           
        
        }
       //323 myPiece.getSquare().getActive() &&
        if( myPiece.getOwner().getColour()!=Color.BLACK) {
            
           
        
        myPiece.getSquare().setOccupied(false);
//        myPiece.setSquare(null);
        
        }
        }catch (Exception e) {
           e.printStackTrace();
        }
        finally {
            selectedPiece = myPiece;
        }
    }
    
    /**
     * Move black piece.
     *
     * @param row theOld x
     * @param col the y
     */
    
    

    
    
    public void moveBlackPiece(double x, double y,Board board) {
        System.out.println("Moving to board LV " + board.boardLv);
        int got_SQR_X = board.getSquareX(x, y);
        int got_SQR_Y = board.getSquareY(x, y);
        System.out.println("Strange at Board line 355, cannot  get piece");
        System.out.println("selectedPiece.getOwner().getColour() " + selectedPiece);
        if(selectedPiece.getOwner().getColour()==Color.BLACK) {
            System.out.println("the Square to go is occupied? " + board.getSquare(got_SQR_X, got_SQR_Y).isOccupied);

            if(!board.getSquare(got_SQR_X, got_SQR_Y).isOccupied)
            {
                System.out.println("Moving to board LV " + board.boardLv + " using Move()");
                selectedPiece.move(got_SQR_X,got_SQR_Y,board);
                board.getSquare(got_SQR_X,got_SQR_Y).setPiece(selectedPiece);
                selectedPiece.setSquare(board.getSquare(got_SQR_X,got_SQR_Y));
                board.getSquare(got_SQR_X,got_SQR_Y).setOccupied(true);
                blackMoveSuccess = true;
            } else {
               
                //makes the piece of the square captured
                board.getSquare(got_SQR_X, got_SQR_Y).returnPiece().setCaptured(); 
            
      
                        selectedPiece.move(got_SQR_X,got_SQR_Y,board);
           
                        board.getSquare(got_SQR_X,got_SQR_Y).setPiece(selectedPiece);
            selectedPiece.setSquare(board.getSquare(got_SQR_X,got_SQR_Y));
            board.getSquare(got_SQR_X,got_SQR_Y).setOccupied(true);
            
            blackMoveSuccess = true;
            }
            counterBlack--;
            
            } else
        blackMoveFail = true;
    }
    
    
    
    /**
     * Move white piece.
     *
     * @param x the x
     * @param y the y
     */
    public void moveWhitePiece(double x, double y,Board board) {
        System.out.println("Moving to board LV " + board.boardLv);
        
        int gotSQR_X = board.getSquareX(x, y);
        System.out.println("gotSQR_X" +  board.getSquareX(x, y));
        int gotSQR_Y = board.getSquareY(x, y);
        System.out.println("gotSQR_Y" +  board.getSquareY(x, y));
        System.out.println("board LV " +  board.getSquareBY_JAVAFX_Coor(x, y).board.boardLv);
            System.out.println("Strange at Board line 402, cannot  get piece");
        System.out.println("selectedPiece.getOwner().getColour() " + selectedPiece);
        
        if(selectedPiece.getOwner().getColour()==Color.WHITE) {
            System.out.println("the Square to go is occupied? " + board.getSquare(gotSQR_X, gotSQR_Y).isOccupied);
           if (!board.getSquare(gotSQR_X, gotSQR_Y).isOccupied()) {
               System.out.println("Moving to board LV " + board.boardLv + " using Move()");
        selectedPiece.move(gotSQR_X,gotSQR_Y,board);
        board.getSquare(gotSQR_X,gotSQR_Y).setPiece(selectedPiece);
        selectedPiece.setSquare(getSquare(gotSQR_X,gotSQR_Y));
        board.getSquare(gotSQR_X,gotSQR_Y).setOccupied(true);
        whiteMoveSuccess = true;
        
           } else {
                
               board.getSquare(gotSQR_X, gotSQR_Y).returnPiece().setCaptured();
               
           selectedPiece.move(gotSQR_X,gotSQR_Y,board);
           board.getSquare(gotSQR_X,gotSQR_Y).setPiece(selectedPiece);
           selectedPiece.setSquare(board.getSquare(gotSQR_X,gotSQR_Y));
           board.getSquare(gotSQR_X,gotSQR_Y).setOccupied(true);
          
              
           whiteMoveSuccess = true;
           }
           counterWhite--;
           
        } else {
            pointerWhiteMoveFail = true;
        }
    }
    
    /**
     * Gets the selected piece.
     *
     * @return the selected piece
     */
    public Piece getSelectedPiece() {
        return selectedPiece;
    }
}