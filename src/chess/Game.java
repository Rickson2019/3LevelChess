package chess;

import java.awt.Button;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

/**
 * Chess Game class is the main scene of the game.
 * 
 * @author Judao Zhong
 * @version 2.0
 *
 */
public class Game extends Application {

    static double SQRsize = 60.0;
    static double totalLength = 480.0;
    /** currently chosen tile. */
    Square currentSquare;
    Square moveToSquare;
    /** The board. */
    private Board boardL1;
    private Board2 boardL2;
    private Board3 boardL3;

    /** Group of all images. */
    private Pane root;
    private AnchorPane rootPane = new AnchorPane();

    /** The selected piece. */
    public Piece selectedPiece = null;
    Piece tmpPicePiece = selectedPiece;
    /** The white player. */
    private final Player whitePlayer;

    /** The black player. */
    private final Player blackPlayer;

    /** The current player. */
    private Player currentPlayer;

    public static Label bishopLabel_white;

    /** The Wbishop. */
    public Piece bishop_white;

    /** The Wbishop 2. */
    public Piece bishop_white2;

    public Label rookLabel_white;

    /** The Wrook. */
    public Piece rook_black1;

    /** The Wrook 2. */
    public Piece rook_black2;

    /** The Wpawn. */
    public Piece pawn_black1;

    /** The Wpawn 2. */
    public Piece pawn_black2;

    /** The Wpawn 3. */
    public Piece pawn_black3;

    /** The Wpawn 4. */
    public Piece pawn_black4;

    /** The Wpawn 5. */
    public Piece pawn_black5;

    /** The Wpawn 6. */
    public Piece pawn_black6;

    /** The Wpawn 7. */
    public Piece pawn_black7;

    /** The Wpawn 8. */
    public Piece pawn_black8;

    /** The Wking. */
    public Piece king_black;

    public Label queenwhiteLabel_white;
    /** The Wqueen. */
    public Piece queen_black;

    public Label knightLabel_white;
    /** The Wknight. */
    public Piece knight_black1;

    /** The Wknight 2. */
    public Piece knight_black2;

    /** The Bbishop. */
    public Piece bishop_black;

    /** The Bbishop 2. */
    public Piece bishop_black2;

    /** The Brook. */
    public Piece rook_white1;

    /** The Brook 2. */
    public Piece rook_white2;

    /** The Bpawn. */
    public Piece pawn_white1;

    /** The Bpawn 2. */
    public Piece pawn_white2;

    /** The Bpawn 3. */
    public Piece pawn_white3;

    /** The Bpawn 4. */
    public Piece pawn_white4;

    /** The Bpawn 5. */
    public Piece pawn_white5;

    /** The Bpawn 6. */
    public Piece pawn_white6;

    /** The Bpawn 7. */
    public Piece pawn_white7;

    /** The Bpawn 8. */
    public Piece pawn_white8;

    /** The Bking. */
    public Piece king_white;

    /** The Bqueen. */
    public Piece queen_white;

    /** The Bknight. */
    public Piece knight_white1;

    /** The Bknight 2. */
    public Piece knight_white2;

    /** The game. */
    public Game game;

     static final double xCoor_Board2L = 500.00;
     static final double xCoor_Board3L = 1000.00;

    /** The status. */
//    public Label status;

    /**
     * Instantiates a new game.
     */
    public Game() {
        this.whitePlayer = new Player(Color.WHITE);
        this.blackPlayer = new Player(Color.BLACK);
        rootPane.setPrefSize(1000, 500);

        boardL1 = new Board();
        boardL2 = new Board2();
        boardL3 = new Board3();

//        status = new Label();

        bishopLabel_white = new Label(" ");

//        
//        status.setText("White Player's Turn");
//   
//        status.setTextFill(Color.BLACK);
//        status.setTranslateX(680);
//        status.setTranslateY(20);
//        status.setScaleX(2);
//        status.setScaleY(2);
    }

    /**
     * Initialize the board and pieces.
     *
     * @param primaryStage the primary stage
     */
    public void start(Stage primaryStage) {
        // create the board with SQRsize for each tile

        root = new Pane(boardL1);
        
        rootPane.getChildren().add(boardL2);
        rootPane.setLeftAnchor(boardL2, xCoor_Board2L);
        rootPane.getChildren().add(boardL3);
        rootPane.setLeftAnchor(boardL3, xCoor_Board3L);
        rootPane.getChildren().add(root);
        instantiate();

        // app size
        final int appWidth = 1250;
        final int appHeight = 800;
        Scene scene = new Scene(rootPane, appWidth, appHeight, Color.WHITE);
        // Scene scene = new Scene(board);
        root.getChildren().addAll(bishop_white, bishop_white2, bishop_black, bishop_black2,

                rook_black1, rook_black2, rook_white1, rook_white2, pawn_black1, pawn_black2, pawn_black3, pawn_black4,
                pawn_black5, pawn_black6, pawn_black7, pawn_black8, pawn_white1, pawn_white2, pawn_white3, pawn_white4, pawn_white5, pawn_white6,
                pawn_white7, pawn_white8, king_black, king_white, queen_black, queen_white, knight_black1, knight_black2, knight_white1, knight_white2,
                bishopLabel_white);
        /* disable the resizing function of window */
        // primaryStage.setResizable(false);
        primaryStage.setTitle("Land,Air&Sea");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            StackPane root2 = new StackPane();
//            Button buttonOK = new Button("OK");
            Label instructionLabel = new Label("Drag and release to move the pieces");
            root2.getChildren().add(instructionLabel);

//            root2.getChildren().addAll(buttonOK);
            Stage stage = new Stage();
            stage.setTitle("Notice");
            stage.setScene(new Scene(root2, 220, 120));
            stage.show();
            // Hide this current window (if this is what you want)

        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Display the position respect to the mouse click */

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(event.getX() <= Game.totalLength)
                    currentSquare = boardL1.getSquareBY_JAVAFX_Coor(event.getX(), event.getY());
                if(event.getX() >= Game.xCoor_Board2L && event.getX() <= Game.xCoor_Board2L + totalLength)
                    currentSquare = boardL2.getSquareBY_JAVAFX_Coor(event.getX(), event.getY());
                if(event.getX() >= Game.xCoor_Board3L && event.getX() <= Game.xCoor_Board3L + totalLength)
                    currentSquare = boardL3.getSquareBY_JAVAFX_Coor(event.getX(), event.getY());
                
                currentSquare.setFill(Color.RED);
                selectedPiece = currentSquare.returnPiece();
                
                if (currentPlayer == blackPlayer && currentSquare.isOccupied)
                    activateBlack();
                else if (currentPlayer == whitePlayer && currentSquare.isOccupied)
                    activateWhite();

                // LV1
                try {
                   
                    ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer, currentSquare,boardL1);
                    ArrayList<Square> availableSquares2 = selectedPiece.movePattern(currentPlayer, currentSquare.board.getSquare(currentSquare.getSQR_X(), currentSquare.getSQR_Y()),boardL2);
                    ArrayList<Square> availableSquares3 = selectedPiece.movePattern(currentPlayer, currentSquare.board.getSquare(currentSquare.getSQR_X(), currentSquare.getSQR_Y()),boardL3);
                    if (availableSquares.size() != 0) 
                        for (int i = 0; i < availableSquares.size(); i++) {

                            availableSquares.get(i).setFill(Color.YELLOW);
                            availableSquares.get(i).setStroke(Color.BLUE);
                        }
                    
                    if (availableSquares2.size() != 0)
                        for (int i = 0; i < availableSquares2.size(); i++) {

                            availableSquares2.get(i).setFill(Color.YELLOW);
                            availableSquares2.get(i).setStroke(Color.BLUE);
                        }
                    
                    if (availableSquares3.size() != 0)
                        for (int i = 0; i < availableSquares3.size(); i++) {
                            availableSquares3.get(i).setFill(Color.YELLOW);
                            availableSquares3.get(i).setStroke(Color.BLUE);
                        }
                    

                    try {
                        if (currentPlayer.getColour() == Color.BLACK) {
                            currentSquare.board.selectBlackPiece(selectedPiece);
//                            moveToSquare.board.selectWhitePiece(selectedPiece);
                        }
                        else if (currentPlayer.getColour() == Color.WHITE) {
                            currentSquare.board.selectWhitePiece(selectedPiece);
//                            moveToSquare.board.selectWhitePiece(selectedPiece);
                        }
                            
                    } catch (Exception e) {
                        availableSquares = null;
                        availableSquares2 = null;
                        availableSquares3 = null;
//                        selectedPiece = currentSquare.returnPiece();
                        System.out.println("cannot choose");
                    }
                } catch (Exception e) {
                    System.out.println("R" + currentSquare.getSQR_X() + " C " + currentSquare.getSQR_Y());

                }

            }

        });
        
        
        scene.setOnMouseReleased(
                /**
                 * Mouse Event.
                 */
                new EventHandler<MouseEvent>() {

                    /**
                     * Handle.
                     *
                     * @param event2 the event
                     */
                    public void handle(MouseEvent event2) {
                        boolean canMove = false;
                        
                        
                        
                        if(event2.getX() <= Game.totalLength)
                            moveToSquare = boardL1.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY());
                        
                        if(event2.getX() >= Game.xCoor_Board2L && event2.getX() <= Game.xCoor_Board2L + totalLength)
                            moveToSquare = boardL2.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY());
                        
                        if(event2.getX() >= Game.xCoor_Board3L && event2.getX() <= Game.xCoor_Board3L + totalLength)
                            moveToSquare = boardL3.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY());
                        
                        System.out.println("Line 326: moveToSquare.board.boardLv " + moveToSquare.board.boardLv);
                        
                        try {
                            if (currentPlayer.getColour() == Color.BLACK) {
                                System.out.println("Line330 selected Piece: "+ selectedPiece);
                                System.out.println("Line331 currentSqr : "+ currentSquare);
                                System.out.println("line 345 selectPiece commented out");
                                currentSquare.board.selectBlackPiece(selectedPiece);
                                moveToSquare.board.selectBlackPiece(selectedPiece);
                                
                            }
                            else if (currentPlayer.getColour() == Color.WHITE) {
                                moveToSquare.board.selectWhitePiece(selectedPiece);
                                currentSquare.board.selectWhitePiece(selectedPiece);
                            }
                                
                        } catch (Exception e) {
//                            selectedPiece = currentSquare.returnPiece();
                            System.out.println("cannot choose");
                        }
                        
//                        System.out.println("selectedPiece Game line 342 " + selectedPiece);
//                        System.out.println("LV " + moveToSquare.board.boardLv);
//                        System.out.println("SQR color " + moveToSquare.getColor());
//                        try {
//                            System.out.println("selected piece 314 "+selectedPiece);
//                            if (currentPlayer.getColour() == Color.BLACK)
//                                currentSquare.board.selectBlackPiece(selectedPiece);
//                            else if (currentPlayer.getColour() == Color.WHITE)
//                                currentSquare.board.selectWhitePiece(selectedPiece);
//                     
//                        
//                        
//                        } catch (Exception e) {
//                            System.out.println("I clicked on " 
//                                    + moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()).board.boardLv 
//                                    + " And the SelectedPiece is " + selectedPiece );
//                        }
                        

                        
                        canMove = false;
                        // LV1
                        try {
                            currentSquare.setFill(currentSquare.getColor());
                            System.out.println(currentSquare.getColor());
                            ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer, currentSquare,
                                    moveToSquare.board);
                            ArrayList<Square> availableSquares2 = selectedPiece.movePattern(currentPlayer, currentSquare,
                                    boardL2);
                            ArrayList<Square> availableSquares3 = selectedPiece.movePattern(currentPlayer, currentSquare,
                                    boardL3);
                            ArrayList<Square> availableSquares4 = selectedPiece.movePattern(currentPlayer, currentSquare,
                                    boardL1);
                            for (int i = 0; i < availableSquares.size(); i++) {
                                availableSquares.get(i).setStroke(null);
                                availableSquares.get(i).setFill(availableSquares.get(i).getColor());
                            }
                            
                            for (int i = 0; i < availableSquares2.size(); i++) {
                                availableSquares2.get(i).setStroke(null);
                                availableSquares2.get(i).setFill(availableSquares2.get(i).getColor());
                            }
                            
                            for (int i = 0; i < availableSquares3.size(); i++) {
                                availableSquares3.get(i).setStroke(null);
                                availableSquares3.get(i).setFill(availableSquares2.get(i).getColor());
                            }
                            
                            for (int i = 0; i < availableSquares4.size(); i++) {
                                availableSquares4.get(i).setStroke(null);
                                availableSquares4.get(i).setFill(availableSquares2.get(i).getColor());
                            }
                            
                            
                            
                            
                            System.out.println("selectedPiece Game line 427 " + selectedPiece);
                            
                            if (currentPlayer == blackPlayer
                                    && moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
                                try {
                                    for (int i = 0; i < availableSquares.size(); i++) {
                                        if (availableSquares.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                                event2.getY()))
                                            canMove = true;
                                    }
                                    for (int i = 0; i < availableSquares2.size(); i++) {
                                        if (availableSquares2.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                                event2.getY()))
                                            canMove = true;
                                    }
                                    for (int i = 0; i < availableSquares3.size(); i++) {
                                        if (availableSquares3.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                                event2.getY()))
                                            canMove = true;
                                    }
                                    for (int i = 0; i < availableSquares4.size(); i++) {
                                        if (availableSquares4.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                                event2.getY()))
                                            canMove = true;
                                    }
                                    if (canMove) {
                                        System.out.println("selectedPiece Game line 439 " + selectedPiece);
                                        System.out.println("Board " + moveToSquare.board.boardLv);
                                        moveToSquare.board.moveBlackPiece(event2.getX(), event2.getY(),moveToSquare.board);
//                                        currentSquare.setPiece(null);
                                        currentSquare.isOccupied = false;
                                    }
                                    if (!canMove) {
                                        currentSquare.setPiece(tmpPicePiece);
                                        currentSquare.isOccupied = true;
                                    }
                                } catch (Exception e) {
                                    currentSquare.setPiece(selectedPiece);
                                    e.printStackTrace();
                                }

                            } else if (currentPlayer == whitePlayer
                                    && moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
                                for (int i = 0; i < availableSquares.size(); i++) {
                                    if (availableSquares.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;
                                }
                                for (int i = 0; i < availableSquares2.size(); i++) {
                                    if (availableSquares2.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;
                                }
                                for (int i = 0; i < availableSquares3.size(); i++) {
                                    if (availableSquares3.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;
                                }
                                for (int i = 0; i < availableSquares4.size(); i++) {
                                    if (availableSquares4.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;
                                }
                              
                                if (canMove) {
                                    moveToSquare.board.moveWhitePiece(event2.getX(), event2.getY(),moveToSquare.board);
//                                    currentSquare.setPiece(null);
                                    currentSquare.isOccupied = false;
                                }
                                if (!canMove) {
                                    currentSquare.setPiece(tmpPicePiece);
                                    currentSquare.isOccupied = true;
                                }
                            }
                        } catch (Exception e) {
                            currentSquare.setPiece(selectedPiece);
                        }

                        if (currentPlayer == blackPlayer && moveToSquare.board.blackMoveSuccess == true) {
                            switchPlayers();
                            canMove = false;
                            moveToSquare.board.blackMoveSuccess = false;
                        } else if (currentPlayer == whitePlayer && moveToSquare.board.whiteMoveSuccess == true) {
                            switchPlayers();
                            canMove = false;
                            moveToSquare.board.whiteMoveSuccess = false;
                        }

                    }

                });

    }

    /**
     * Instantiate.
     */
    public void instantiate() {
        currentPlayer = whitePlayer;

        bishop_white = new PieceBishop(whitePlayer, 2, 7);
        bishop_white.setSquare(boardL1.getSquare(2, 7));
        boardL1.getSquare(2, 7).setPiece(bishop_white);

        bishop_white2 = new PieceBishop(whitePlayer, 5, 7);
        bishop_white2.setSquare(boardL1.getSquare(5, 7));
        boardL1.getSquare(5, 7).setPiece(bishop_white2);

        bishop_black = new PieceBishop(blackPlayer, 2, 0);
        bishop_black.setSquare(boardL1.getSquare(2, 0));
        boardL1.getSquare(2, 0).setPiece(bishop_black);

        bishop_black2 = new PieceBishop(blackPlayer, 5, 0);
        bishop_black2.setSquare(boardL1.getSquare(5, 0));
        boardL1.getSquare(5, 0).setPiece(bishop_black2);

        rook_black1 = new PieceRook(blackPlayer, 0, 0);
        rook_black1.setSquare(boardL1.getSquare(0, 0));
        boardL1.getSquare(0, 0).setPiece(rook_black1);

        rook_black2 = new PieceRook(blackPlayer, 7, 0);
        rook_black2.setSquare(boardL1.getSquare(7, 0));
        boardL1.getSquare(7, 0).setPiece(rook_black2);
//        System.out.println(boardL1.getSquare(7, 0).returnPiece());

        rook_white1 = new PieceRook(whitePlayer, 0, 7);
        rook_white1.setSquare(boardL1.getSquare(0, 7));
        boardL1.getSquare(0, 7).setPiece(rook_white1);

        rook_white2 = new PieceRook(whitePlayer, 7, 7);
        rook_white2.setSquare(boardL1.getSquare(7, 7));
        boardL1.getSquare(7, 7).setPiece(rook_white2);

        pawn_black1 = new PiecePawn(blackPlayer, 0, 1);
        pawn_black1.setSquare(boardL1.getSquare(0, 1));
        boardL1.getSquare(0, 1).setPiece(pawn_black1);

        pawn_black2 = new PiecePawn(blackPlayer, 1, 1);
        pawn_black2.setSquare(boardL1.getSquare(1, 1));
        boardL1.getSquare(1, 1).setPiece(pawn_black2);

        pawn_black3 = new PiecePawn(blackPlayer, 2, 1);
        pawn_black3.setSquare(boardL1.getSquare(2, 1));
        boardL1.getSquare(2, 1).setPiece(pawn_black3);

        pawn_black4 = new PiecePawn(blackPlayer, 3, 1);
        pawn_black4.setSquare(boardL1.getSquare(3, 1));
        boardL1.getSquare(3, 1).setPiece(pawn_black4);

        pawn_black5 = new PiecePawn(blackPlayer, 4, 1);
        pawn_black5.setSquare(boardL1.getSquare(4, 1));
        boardL1.getSquare(4, 1).setPiece(pawn_black5);

        pawn_black6 = new PiecePawn(blackPlayer, 5, 1);
        pawn_black6.setSquare(boardL1.getSquare(5, 1));
        boardL1.getSquare(5, 1).setPiece(pawn_black6);

        pawn_black7 = new PiecePawn(blackPlayer, 6, 1);
        pawn_black7.setSquare(boardL1.getSquare(6, 1));
        boardL1.getSquare(6, 1).setPiece(pawn_black7);

        pawn_black8 = new PiecePawn(blackPlayer, 7, 1);
        pawn_black8.setSquare(boardL1.getSquare(7, 1));
        boardL1.getSquare(7, 1).setPiece(pawn_black8);

        pawn_white1 = new PiecePawn(whitePlayer, 0, 6);
        pawn_white1.setSquare(boardL1.getSquare(0, 6));
        boardL1.getSquare(0, 6).setPiece(pawn_white1);

        pawn_white2 = new PiecePawn(whitePlayer, 1, 6);
        pawn_white2.setSquare(boardL1.getSquare(1, 6));
        boardL1.getSquare(1, 6).setPiece(pawn_white2);

        pawn_white3 = new PiecePawn(whitePlayer, 2, 6);
        pawn_white3.setSquare(boardL1.getSquare(2, 6));
        boardL1.getSquare(2, 6).setPiece(pawn_white3);

        pawn_white4 = new PiecePawn(whitePlayer, 3, 6);
        pawn_white4.setSquare(boardL1.getSquare(3, 6));
        boardL1.getSquare(3, 6).setPiece(pawn_white4);

        pawn_white5 = new PiecePawn(whitePlayer, 4, 6);
        pawn_white5.setSquare(boardL1.getSquare(4, 6));
        boardL1.getSquare(4, 6).setPiece(pawn_white5);

        pawn_white6 = new PiecePawn(whitePlayer, 5, 6);
        pawn_white6.setSquare(boardL1.getSquare(5, 6));
        boardL1.getSquare(5, 6).setPiece(pawn_white6);

        pawn_white7 = new PiecePawn(whitePlayer, 6, 6);
        pawn_white7.setSquare(boardL1.getSquare(6, 6));
        boardL1.getSquare(6, 6).setPiece(pawn_white7);

        pawn_white8 = new PiecePawn(whitePlayer, 7, 6);
        pawn_white8.setSquare(boardL1.getSquare(7, 6));
        boardL1.getSquare(7, 6).setPiece(pawn_white8);

        king_black = new PieceKing(blackPlayer, 4, 0);
        king_black.setSquare(boardL1.getSquare(4, 0));
        boardL1.getSquare(4, 0).setPiece(king_black);

        king_white = new PieceKing(whitePlayer, 4, 7);
        king_white.setSquare(boardL1.getSquare(4, 7));
        boardL1.getSquare(4, 7).setPiece(king_white);

        queen_black = new PieceQueen(blackPlayer, 3, 0);
        queen_black.setSquare(boardL1.getSquare(3, 0));
        boardL1.getSquare(3, 0).setPiece(queen_black);

        queen_white = new PieceQueen(whitePlayer, 3, 7);
        queen_white.setSquare(boardL1.getSquare(3, 7));
        boardL1.getSquare(3, 7).setPiece(queen_white);

        knight_black1 = new PieceKnight(blackPlayer, 1, 0);
        knight_black1.setSquare(boardL1.getSquare(1, 0));
        boardL1.getSquare(1, 0).setPiece(knight_black1);

        knight_black2 = new PieceKnight(blackPlayer, 6, 0);
        knight_black2.setSquare(boardL1.getSquare(6, 0));
        boardL1.getSquare(6, 0).setPiece(knight_black2);

        knight_white1 = new PieceKnight(whitePlayer, 1, 7);
        knight_white1.setSquare(boardL1.getSquare(1, 7));
        boardL1.getSquare(1, 7).setPiece(knight_white1);

        knight_white2 = new PieceKnight(whitePlayer, 6, 7);
        knight_white2.setSquare(boardL1.getSquare(6, 7));
        boardL1.getSquare(6, 7).setPiece(knight_white2);

        boardL1.initPieces();
//        activateWhite();
    }

    /**
     * Switch players.
     */
    public void switchPlayers() {
        if (this.currentPlayer == this.blackPlayer) {
            this.currentPlayer = this.whitePlayer;
//            status.setText("White Player's Turn");  
//            status.setTextFill(Color.GRAY);

        } else if (this.currentPlayer == this.whitePlayer) {
            this.currentPlayer = this.blackPlayer;
//            status.setText("Black Player's Turn");
//            status.setTextFill(Color.BLACK);
        }
        for (int x = 0; x < boardL1.getNumCols(); x++) {
            for (int y = 0; y < boardL1.getNumCols(); y++) {
                boardL1.getArray()[x][y].setActive(false);
                boardL2.getArray()[x][y].setActive(false);
                boardL3.getArray()[x][y].setActive(false);

            }
        }
    }

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {

        return currentPlayer;
    }

    /**
     * Allows White Player to move pieces
     */
    private void activateWhite() {

        try {
            bishop_white.getSquare().setActive(true);
            bishop_white2.getSquare().setActive(true);
            rook_white1.getSquare().setActive(true);
            rook_white2.getSquare().setActive(true);
            pawn_white1.getSquare().setActive(true);
            pawn_white2.getSquare().setActive(true);
            pawn_white3.getSquare().setActive(true);
            pawn_white4.getSquare().setActive(true);
            pawn_white5.getSquare().setActive(true);
            pawn_white6.getSquare().setActive(true);
            pawn_white7.getSquare().setActive(true);
            pawn_white8.getSquare().setActive(true);
            king_white.getSquare().setActive(true);
            queen_white.getSquare().setActive(true);
            knight_white1.getSquare().setActive(true);
            knight_white2.getSquare().setActive(true);



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cannot set White to and back to inactived");
            bishop_white.getSquare().setActive(false);
            bishop_white2.getSquare().setActive(false);
            rook_white1.getSquare().setActive(false);
            rook_white2.getSquare().setActive(false);
            pawn_white1.getSquare().setActive(false);
            pawn_white2.getSquare().setActive(false);
            pawn_white3.getSquare().setActive(false);
            pawn_white4.getSquare().setActive(false);
            pawn_white5.getSquare().setActive(false);
            pawn_white6.getSquare().setActive(false);
            pawn_white7.getSquare().setActive(false);
            pawn_white8.getSquare().setActive(false);
            king_white.getSquare().setActive(false);
            queen_white.getSquare().setActive(false);
            knight_white1.getSquare().setActive(false);
            knight_white2.getSquare().setActive(false);
        }

    }

    /**
     * Allows Black Player to move pieces
     */
    public void activateBlack() {

        try {

            bishop_black.getSquare().setActive(true);
            bishop_black2.getSquare().setActive(true);
            rook_black1.getSquare().setActive(true);
            rook_black2.getSquare().setActive(true);
            pawn_black1.getSquare().setActive(true);
            pawn_black2.getSquare().setActive(true);
            pawn_black3.getSquare().setActive(true);
            pawn_black4.getSquare().setActive(true);
            pawn_black5.getSquare().setActive(true);
            pawn_black6.getSquare().setActive(true);
            pawn_black7.getSquare().setActive(true);
            pawn_black8.getSquare().setActive(true);
            king_black.getSquare().setActive(true);
            queen_black.getSquare().setActive(true);
            knight_black1.getSquare().setActive(true);
            knight_black2.getSquare().setActive(true);
            
 

        } catch (Exception e) {


            e.printStackTrace();
            System.out.println("Cannot Set Black to Active, set back to inactivated");
            bishop_black.getSquare().setActive(false);
            bishop_black2.getSquare().setActive(false);
            rook_black1.getSquare().setActive(false);
            rook_black2.getSquare().setActive(false);
            pawn_black1.getSquare().setActive(false);
            pawn_black2.getSquare().setActive(false);
            pawn_black3.getSquare().setActive(false);
            pawn_black4.getSquare().setActive(false);
            pawn_black5.getSquare().setActive(false);
            pawn_black6.getSquare().setActive(false);
            pawn_black7.getSquare().setActive(false);
            pawn_black8.getSquare().setActive(false);
            king_black.getSquare().setActive(false);
            queen_black.getSquare().setActive(false);
            knight_black1.getSquare().setActive(false);
            knight_black2.getSquare().setActive(false);
        }

    }

}