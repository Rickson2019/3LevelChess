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
    public Piece rook_white;

    /** The Wrook 2. */
    public Piece rook_white2;

    /** The Wpawn. */
    public Piece pawn_white;

    /** The Wpawn 2. */
    public Piece pawn_white2;

    /** The Wpawn 3. */
    public Piece pawn_white3;

    /** The Wpawn 4. */
    public Piece pawn_white4;

    /** The Wpawn 5. */
    public Piece pawn_white5;

    /** The Wpawn 6. */
    public Piece pawn_white6;

    /** The Wpawn 7. */
    public Piece pawn_white7;

    /** The Wpawn 8. */
    public Piece pawn_white8;

    /** The Wking. */
    public Piece Wking;

    public Label queenwhiteLabel_white;
    /** The Wqueen. */
    public Piece queen_white;

    public Label knightLabel_white;
    /** The Wknight. */
    public Piece knight_white;

    /** The Wknight 2. */
    public Piece knight_white2;

    /** The Bbishop. */
    public Piece bishop_black;

    /** The Bbishop 2. */
    public Piece bishop_black2;

    /** The Brook. */
    public Piece rook_black;

    /** The Brook 2. */
    public Piece rook_black2;

    /** The Bpawn. */
    public Piece pawn_black;

    /** The Bpawn 2. */
    public Piece Bpawn2;

    /** The Bpawn 3. */
    public Piece Bpawn3;

    /** The Bpawn 4. */
    public Piece Bpawn4;

    /** The Bpawn 5. */
    public Piece Bpawn5;

    /** The Bpawn 6. */
    public Piece Bpawn6;

    /** The Bpawn 7. */
    public Piece Bpawn7;

    /** The Bpawn 8. */
    public Piece Bpawn8;

    /** The Bking. */
    public Piece Bking;

    /** The Bqueen. */
    public Piece queen_black;

    /** The Bknight. */
    public Piece Bknight;

    /** The Bknight 2. */
    public Piece Bknight2;

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

                rook_white, rook_white2, rook_black, rook_black2, pawn_white, pawn_white2, pawn_white3, pawn_white4,
                pawn_white5, pawn_white6, pawn_white7, pawn_white8, pawn_black, Bpawn2, Bpawn3, Bpawn4, Bpawn5, Bpawn6,
                Bpawn7, Bpawn8, Wking, Bking, queen_white, queen_black, knight_white, knight_white2, Bknight, Bknight2,
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
                System.out.println(currentSquare);
                currentSquare.setFill(Color.RED);
                selectedPiece = currentSquare.returnPiece();

                if (currentPlayer == blackPlayer && currentSquare.isOccupied)
                    activateBlack();
                else if (currentPlayer == whitePlayer && currentSquare.isOccupied)
                    activateWhite();

                // LV1
                try {
                   
                    ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer, currentSquare,boardL1);
                    ArrayList<Square> availableSquares2 = selectedPiece.movePattern(currentPlayer, currentSquare,boardL2);
                    ArrayList<Square> availableSquares3 = selectedPiece.movePattern(currentPlayer, currentSquare,boardL3);
                    if (availableSquares.size() != 0)
                        for (int i = 0; i < availableSquares.size(); i++) {
                           
                            availableSquares.get(i).setFill(Color.YELLOW);
                            availableSquares.get(i).setStroke(Color.BLUE);
                            availableSquares2.get(i).setFill(Color.BLUE);
                            availableSquares2.get(i).setFill(Color.YELLOW);
                            availableSquares3.get(i).setFill(Color.BLUE);
                            availableSquares3.get(i).setFill(Color.YELLOW);
                        }

                    try {
                        if (currentPlayer.getColour() == Color.BLACK) {
                            currentSquare.board.selectBlackPiece(selectedPiece);
                            moveToSquare.board.selectWhitePiece(selectedPiece);
                        }
                        else if (currentPlayer.getColour() == Color.WHITE) {
                            currentSquare.board.selectWhitePiece(selectedPiece);
                            moveToSquare.board.selectWhitePiece(selectedPiece);
                        }
                            
                    } catch (Exception e) {
                        availableSquares = null;
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
                        
                        
                        
                        
                        if(event2.getX() <= Game.totalLength)
                            moveToSquare = boardL1.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY());
                        
                        if(event2.getX() >= Game.xCoor_Board2L && event2.getX() <= Game.xCoor_Board2L + totalLength)
                            moveToSquare = boardL2.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY());
                        
                        if(event2.getX() >= Game.xCoor_Board3L && event2.getX() <= Game.xCoor_Board3L + totalLength)
                            moveToSquare = boardL3.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY());
                        
                        System.out.println("Line 326" + moveToSquare.board.boardLv);
                        
                        try {
                            if (currentPlayer.getColour() == Color.BLACK) {
                                System.out.println("Line325 selected Piece: "+ selectedPiece);
                                System.out.println("Line326 currentSqr : "+ currentSquare);
                                currentSquare.board.selectWhitePiece(selectedPiece);
                                moveToSquare.board.selectWhitePiece(selectedPiece);
                            }
                            else if (currentPlayer.getColour() == Color.WHITE) {
                                moveToSquare.board.selectWhitePiece(selectedPiece);
                            }
                                
                        } catch (Exception e) {
//                            selectedPiece = currentSquare.returnPiece();
                            System.out.println("cannot choose");
                        }
                        
                        selectedPiece = currentSquare.returnPiece();
                        try {
                            System.out.println("selected piece 314 "+selectedPiece);
                            if (currentPlayer.getColour() == Color.BLACK)
                                currentSquare.board.selectBlackPiece(selectedPiece);
                            else if (currentPlayer.getColour() == Color.WHITE)
                                currentSquare.board.selectWhitePiece(selectedPiece);
                     
                        
                        
                        } catch (Exception e) {
                            System.out.println("I clicked on " 
                                    + moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()).board.boardLv 
                                    + " And the SelectedPiece is " + selectedPiece );
                        }
                        
                        
                        

                        
                        System.out.println("selectedPiece Game line 342 " + selectedPiece);
                        
                       
                        
                        System.out.println("LV " + moveToSquare.board.boardLv);
                        System.out.println("SQR color " + moveToSquare.getColor());
                        boolean canMove = false;

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
                                availableSquares2.get(i).setStroke(null);
                                availableSquares2.get(i).setFill(availableSquares.get(i).getColor());
                                availableSquares3.get(i).setStroke(null);
                                availableSquares3.get(i).setFill(availableSquares.get(i).getColor());
                                availableSquares4.get(i).setStroke(null);
                                availableSquares4.get(i).setFill(availableSquares.get(i).getColor());

                            }
                            
                            System.out.println("selectedPiece Game line 427 " + selectedPiece);
                            
                            if (currentPlayer == blackPlayer
                                    && moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
                                for (int i = 0; i < availableSquares.size(); i++) {
                                    if (availableSquares.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;
                                }
                                if (canMove) {
                                    System.out.println("selectedPiece Game line 439 " + selectedPiece);
                                    System.out.println("Board " + moveToSquare.board.boardLv);
                                    moveToSquare.board.moveBlackPiece(event2.getX(), event2.getY(),moveToSquare.board);
                                    currentSquare.setPiece(null);
                                    currentSquare.isOccupied = false;
                                }

                            } else if (currentPlayer == whitePlayer
                                    && moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
                                for (int i = 0; i < availableSquares.size(); i++) {
                                    if (availableSquares.get(i) == moveToSquare.board.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;

                                }
                                if (canMove) {
                                    moveToSquare.board.moveWhitePiece(event2.getX(), event2.getY(),moveToSquare.board);
                                    currentSquare.setPiece(null);
                                    currentSquare.isOccupied = false;
                                }
                            }
                        } catch (Exception e) {
                            currentSquare.setPiece(selectedPiece);
                        }

                        if (currentPlayer == blackPlayer && moveToSquare.board.blackMoveSuccess == true) {
                            switchPlayers();

                            moveToSquare.board.blackMoveSuccess = false;
                        } else if (currentPlayer == whitePlayer && moveToSquare.board.whiteMoveSuccess == true) {
                            switchPlayers();

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

        rook_white = new PieceRook(blackPlayer, 0, 0);
        rook_white.setSquare(boardL1.getSquare(0, 0));
        boardL1.getSquare(0, 0).setPiece(rook_white);

        rook_white2 = new PieceRook(blackPlayer, 7, 0);
        rook_white2.setSquare(boardL1.getSquare(7, 0));
        boardL1.getSquare(7, 0).setPiece(rook_white2);
//        System.out.println(boardL1.getSquare(7, 0).returnPiece());

        rook_black = new PieceRook(whitePlayer, 0, 7);
        rook_black.setSquare(boardL1.getSquare(0, 7));
        boardL1.getSquare(0, 7).setPiece(rook_black);

        rook_black2 = new PieceRook(whitePlayer, 7, 7);
        rook_black2.setSquare(boardL1.getSquare(7, 7));
        boardL1.getSquare(7, 7).setPiece(rook_black2);

        pawn_white = new PiecePawn(blackPlayer, 0, 1);
        pawn_white.setSquare(boardL1.getSquare(0, 1));
        boardL1.getSquare(0, 1).setPiece(pawn_white);

        pawn_white2 = new PiecePawn(blackPlayer, 1, 1);
        pawn_white2.setSquare(boardL1.getSquare(1, 1));
        boardL1.getSquare(1, 1).setPiece(pawn_white2);

        pawn_white3 = new PiecePawn(blackPlayer, 2, 1);
        pawn_white3.setSquare(boardL1.getSquare(2, 1));
        boardL1.getSquare(2, 1).setPiece(pawn_white3);

        pawn_white4 = new PiecePawn(blackPlayer, 3, 1);
        pawn_white4.setSquare(boardL1.getSquare(3, 1));
        boardL1.getSquare(3, 1).setPiece(pawn_white4);

        pawn_white5 = new PiecePawn(blackPlayer, 4, 1);
        pawn_white5.setSquare(boardL1.getSquare(4, 1));
        boardL1.getSquare(4, 1).setPiece(pawn_white5);

        pawn_white6 = new PiecePawn(blackPlayer, 5, 1);
        pawn_white6.setSquare(boardL1.getSquare(5, 1));
        boardL1.getSquare(5, 1).setPiece(pawn_white6);

        pawn_white7 = new PiecePawn(blackPlayer, 6, 1);
        pawn_white7.setSquare(boardL1.getSquare(6, 1));
        boardL1.getSquare(6, 1).setPiece(pawn_white7);

        pawn_white8 = new PiecePawn(blackPlayer, 7, 1);
        pawn_white8.setSquare(boardL1.getSquare(7, 1));
        boardL1.getSquare(7, 1).setPiece(pawn_white8);

        pawn_black = new PiecePawn(whitePlayer, 0, 6);
        pawn_black.setSquare(boardL1.getSquare(0, 6));
        boardL1.getSquare(0, 6).setPiece(pawn_black);

        Bpawn2 = new PiecePawn(whitePlayer, 1, 6);
        Bpawn2.setSquare(boardL1.getSquare(1, 6));
        boardL1.getSquare(1, 6).setPiece(Bpawn2);

        Bpawn3 = new PiecePawn(whitePlayer, 2, 6);
        Bpawn3.setSquare(boardL1.getSquare(2, 6));
        boardL1.getSquare(2, 6).setPiece(Bpawn3);

        Bpawn4 = new PiecePawn(whitePlayer, 3, 6);
        Bpawn4.setSquare(boardL1.getSquare(3, 6));
        boardL1.getSquare(3, 6).setPiece(Bpawn4);

        Bpawn5 = new PiecePawn(whitePlayer, 4, 6);
        Bpawn5.setSquare(boardL1.getSquare(4, 6));
        boardL1.getSquare(4, 6).setPiece(Bpawn5);

        Bpawn6 = new PiecePawn(whitePlayer, 5, 6);
        Bpawn6.setSquare(boardL1.getSquare(5, 6));
        boardL1.getSquare(5, 6).setPiece(Bpawn6);

        Bpawn7 = new PiecePawn(whitePlayer, 6, 6);
        Bpawn7.setSquare(boardL1.getSquare(6, 6));
        boardL1.getSquare(6, 6).setPiece(Bpawn7);

        Bpawn8 = new PiecePawn(whitePlayer, 7, 6);
        Bpawn8.setSquare(boardL1.getSquare(7, 6));
        boardL1.getSquare(7, 6).setPiece(Bpawn8);

        Wking = new PieceKing(blackPlayer, 4, 0);
        Wking.setSquare(boardL1.getSquare(4, 0));
        boardL1.getSquare(4, 0).setPiece(Wking);

        Bking = new PieceKing(whitePlayer, 4, 7);
        Bking.setSquare(boardL1.getSquare(4, 7));
        boardL1.getSquare(4, 7).setPiece(Bking);

        queen_white = new PieceQueen(blackPlayer, 3, 0);
        queen_white.setSquare(boardL1.getSquare(3, 0));
        boardL1.getSquare(3, 0).setPiece(queen_white);

        queen_black = new PieceQueen(whitePlayer, 3, 7);
        queen_black.setSquare(boardL1.getSquare(3, 7));
        boardL1.getSquare(3, 7).setPiece(queen_black);

        knight_white = new PieceKnight(blackPlayer, 1, 0);
        knight_white.setSquare(boardL1.getSquare(1, 0));
        boardL1.getSquare(1, 0).setPiece(knight_white);

        knight_white2 = new PieceKnight(blackPlayer, 6, 0);
        knight_white2.setSquare(boardL1.getSquare(6, 0));
        boardL1.getSquare(6, 0).setPiece(knight_white2);

        Bknight = new PieceKnight(whitePlayer, 1, 7);
        Bknight.setSquare(boardL1.getSquare(1, 7));
        boardL1.getSquare(1, 7).setPiece(Bknight);

        Bknight2 = new PieceKnight(whitePlayer, 6, 7);
        Bknight2.setSquare(boardL1.getSquare(6, 7));
        boardL1.getSquare(6, 7).setPiece(Bknight2);

        boardL1.initPieces();
//        activateWhite();
    }

    /**
     * Switch players.
     */
    public void switchPlayers() {
        if (boardL1.counterBlack == 1 && boardL1.counterWhite > 1) {

//            status.setText("Black player lost!");

        } else if (boardL1.counterWhite == 1 && boardL1.counterBlack > 1) {

//            status.setText("White player lost!");
        }

        else if (this.currentPlayer == this.blackPlayer) {
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
            rook_white.getSquare().setActive(true);
            rook_white2.getSquare().setActive(true);
            pawn_white.getSquare().setActive(true);
            pawn_white2.getSquare().setActive(true);
            pawn_white3.getSquare().setActive(true);
            pawn_white4.getSquare().setActive(true);
            pawn_white5.getSquare().setActive(true);
            pawn_white6.getSquare().setActive(true);
            pawn_white7.getSquare().setActive(true);
            pawn_white8.getSquare().setActive(true);
            Wking.getSquare().setActive(true);
            queen_white.getSquare().setActive(true);
            knight_white.getSquare().setActive(true);
            knight_white2.getSquare().setActive(true);

            bishop_black.getSquare().setActive(false);
            bishop_black2.getSquare().setActive(false);
            rook_black.getSquare().setActive(false);
            rook_black2.getSquare().setActive(false);
            pawn_black.getSquare().setActive(false);
            Bpawn2.getSquare().setActive(false);
            Bpawn3.getSquare().setActive(false);
            Bpawn4.getSquare().setActive(false);
            Bpawn5.getSquare().setActive(false);
            Bpawn6.getSquare().setActive(false);
            Bpawn7.getSquare().setActive(false);
            Bpawn8.getSquare().setActive(false);
            Bking.getSquare().setActive(false);
            queen_black.getSquare().setActive(false);
            Bknight.getSquare().setActive(false);
            Bknight2.getSquare().setActive(false);

        } catch (Exception e) {
            System.out.println("cannot set White to and back to inactived");

//           bishop_white.getSquare().setActive(true);
//           bishop_white2.getSquare().setActive(true);
//           rook_white.getSquare().setActive(true);
//           rook_white2.getSquare().setActive(true);
//           pawn_white.getSquare().setActive(true);
//           pawn_white2.getSquare().setActive(true);
//           pawn_white3.getSquare().setActive(true);
//           pawn_white4.getSquare().setActive(true);
//           pawn_white5.getSquare().setActive(true);
//           pawn_white6.getSquare().setActive(true);
//           pawn_white7.getSquare().setActive(true);
//           pawn_white8.getSquare().setActive(true);
//           Wking.getSquare().setActive(true);
//           queen_white.getSquare().setActive(true);
//           knight_white.getSquare().setActive(true);
//           knight_white2.getSquare().setActive(true);
//           
//           bishop_white.getSquare().setActive(false);
//           bishop_white2.getSquare().setActive(false);
//           rook_white.getSquare().setActive(false);
//           rook_white2.getSquare().setActive(false);
//           pawn_white.getSquare().setActive(false);
//           pawn_white2.getSquare().setActive(false);
//           pawn_white3.getSquare().setActive(false);
//           pawn_white4.getSquare().setActive(false);
//           pawn_white5.getSquare().setActive(false);
//           pawn_white6.getSquare().setActive(false);
//           pawn_white7.getSquare().setActive(false);
//           pawn_white8.getSquare().setActive(false);
//           Wking.getSquare().setActive(false);
//           queen_white.getSquare().setActive(false);
//           knight_white.getSquare().setActive(false);
//           knight_white2.getSquare().setActive(false);
        }

    }

    /**
     * Allows Black Player to move pieces
     */
    public void activateBlack() {

        try {

            bishop_black.getSquare().setActive(true);
            bishop_black2.getSquare().setActive(true);
            rook_black.getSquare().setActive(true);
            rook_black2.getSquare().setActive(true);
            pawn_black.getSquare().setActive(true);
            Bpawn2.getSquare().setActive(true);
            Bpawn3.getSquare().setActive(true);
            Bpawn4.getSquare().setActive(true);
            Bpawn5.getSquare().setActive(true);
            Bpawn6.getSquare().setActive(true);
            Bpawn7.getSquare().setActive(true);
            Bpawn8.getSquare().setActive(true);
            Bking.getSquare().setActive(true);
            queen_black.getSquare().setActive(true);
            Bknight.getSquare().setActive(true);
            Bknight2.getSquare().setActive(true);

//            bishop_white.getSquare().setActive(false);
//            bishop_white2.getSquare().setActive(false);
//            rook_white.getSquare().setActive(false);
//            rook_white2.getSquare().setActive(false);
//            pawn_white.getSquare().setActive(false);
//            pawn_white2.getSquare().setActive(false);
//            pawn_white3.getSquare().setActive(false);
//            pawn_white4.getSquare().setActive(false);
//            pawn_white5.getSquare().setActive(false);
//            pawn_white6.getSquare().setActive(false);
//            pawn_white7.getSquare().setActive(false);
//            pawn_white8.getSquare().setActive(false);
//            Wking.getSquare().setActive(false);
//            queen_white.getSquare().setActive(false);
//            knight_white.getSquare().setActive(false);
//            knight_white2.getSquare().setActive(false);

        } catch (Exception e) {

//            bishop_black.getSquare().setActive(true); 
//            bishop_black2.getSquare().setActive(true);
//            rook_black.getSquare().setActive(true);
//            rook_black2.getSquare().setActive(true);
//            pawn_black.getSquare().setActive(true);
//            Bpawn2.getSquare().setActive(true);
//            Bpawn3.getSquare().setActive(true);
//            Bpawn4.getSquare().setActive(true);
//            Bpawn5.getSquare().setActive(true);
//            Bpawn6.getSquare().setActive(true);
//            Bpawn7.getSquare().setActive(true);
//            Bpawn8.getSquare().setActive(true);
//            Bking.getSquare().setActive(true);
//            queen_black.getSquare().setActive(true);
//            Bknight.getSquare().setActive(true);
//            Bknight2.getSquare().setActive(true);
            e.printStackTrace();
            System.out.println("Cannot Set Black to Active, set back to inactivated");
//            bishop_black.getSquare().setActive(false);
//            bishop_black2.getSquare().setActive(false);
//            rook_black.getSquare().setActive(false);
//            rook_black2.getSquare().setActive(false);
//            pawn_black.getSquare().setActive(false);
//            Bpawn2.getSquare().setActive(false);
//            Bpawn3.getSquare().setActive(false);
//            Bpawn4.getSquare().setActive(false);
//            Bpawn5.getSquare().setActive(false);
//            Bpawn6.getSquare().setActive(false);
//            Bpawn7.getSquare().setActive(false);
//            Bpawn8.getSquare().setActive(false);
//            Bking.getSquare().setActive(false);
//            queen_black.getSquare().setActive(false);
//            Bknight.getSquare().setActive(false);
//            Bknight2.getSquare().setActive(false);

        }

    }

}