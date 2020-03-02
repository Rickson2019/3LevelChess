package chess;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MoveHandler {
    public MoveHandler(Scene scene, Square currentSquare, Board boardL1,Board boardL2,Player currentPlayer,Piece selectedPiece) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                currentSquare = boardL1.getSquareBY_JAVAFX_Coor(event.getX(), event.getY());
                
                if (event.getX() > Game.xCoor_Board2L && event.getX() < Game.xCoor_Board2L + Game.totalLength) {
                    currentSquare = boardL1.getSquareBY_JAVAFX_Coor(event.getX(), event.getY());
                }
                
                currentSquare.setFill(Color.RED);
                selectedPiece = currentSquare.returnPiece();

                if (currentPlayer == Game.blackPlayer && currentSquare.isOccupied)
                    activateBlack();
                else if (currentPlayer == whitePlayer && currentSquare.isOccupied)
                    activateWhite();

                // if LV2
                try {
                    ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer, currentSquare,
                            boardL2);
                    if (availableSquares.size() != 0)
                        for (int i = 0; i < availableSquares.size(); i++) {

//                               System.out.println("get available X" + availableSquares.get(i).getX());
//                               System.out.println("get available Y" + availableSquares.get(i).getY());
                            availableSquares.get(i).setFill(Color.YELLOWGREEN);
                            availableSquares.get(i).setStroke(Color.BLUE);
                        }

                    try {
                        if (currentPlayer.getColour() == Color.BLACK)
                            boardL2.selectBlackPiece(selectedPiece);
                        else if (currentPlayer.getColour() == Color.WHITE)
                            boardL2.selectWhitePiece(selectedPiece);
                    } catch (Exception e) {
                        availableSquares = null;
                        selectedPiece = currentSquare.returnPiece();
                        System.out.println("cannot choose");
                    }
                } catch (Exception e) {
                    System.out.println("R" + currentSquare.getSQR_Y() + " C " + currentSquare.getSQR_X());

                }

                // LV1
                try {
                    ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer, currentSquare,
                            boardL1);
                    if (availableSquares.size() != 0)
                        for (int i = 0; i < availableSquares.size(); i++) {

                            availableSquares.get(i).setFill(Color.YELLOW);
                            availableSquares.get(i).setStroke(Color.BLUE);
                        }

                    try {
                        if (currentPlayer.getColour() == Color.BLACK)
                            boardL1.selectBlackPiece(selectedPiece);
                        else if (currentPlayer.getColour() == Color.WHITE)
                            boardL1.selectWhitePiece(selectedPiece);
                    } catch (Exception e) {
                        availableSquares = null;
                        selectedPiece = currentSquare.returnPiece();
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

                        selectedPiece = currentSquare.returnPiece();

                        boolean canMove = false;

                        // If LV2
                        if (event2.getX() > xCoor_Board2L && event2.getX() < xCoor_Board2L + totalLength) {
                            try {
                                currentSquare.setFill(currentSquare.getColor());
                                ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer,
                                        currentSquare, boardL2);
                                for (int i = 0; i < availableSquares.size(); i++) {
                                    availableSquares.get(i).setStroke(null);
                                    availableSquares.get(i).setFill(availableSquares.get(i).getColor());

                                }

                                if (currentPlayer == blackPlayer && boardL2.getSquareBY_JAVAFX_Coor(event2.getX(),
                                        event2.getY()) != currentSquare) {
                                    System.out.println(boardL2.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()));
//                                    ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer,currentSquare, board);
                                    for (int i = 0; i < availableSquares.size(); i++) {
                                        if (availableSquares.get(i) == boardL2.getSquareBY_JAVAFX_Coor(event2.getX(),
                                                event2.getY()))
                                            canMove = true;
//                                            System.out.println(canMove);

                                    }
                                    if (canMove) {
                                        boardL2.moveBlackPiece(event2.getX(), event2.getY());
                                        currentSquare.setPiece(null);
                                        currentSquare.isOccupied = false;
                                    }

                                } else if (currentPlayer == whitePlayer && boardL2
                                        .getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
                                    for (int i = 0; i < availableSquares.size(); i++) {
                                        if (availableSquares.get(i) == boardL2.getSquareBY_JAVAFX_Coor(event2.getX(),
                                                event2.getY()))
                                            canMove = true;

                                    }
                                    if (canMove) {
                                        boardL2.moveWhitePiece(event2.getX(), event2.getY());
                                        currentSquare.setPiece(null);
                                        currentSquare.isOccupied = false;
                                    }
                                }
                            } catch (Exception e) {
                                currentSquare.setPiece(selectedPiece);
                                System.out.println("Choose the other color to play!");
                            }

                            if (currentPlayer == blackPlayer && boardL2.blackMoveSuccess == true) {
                                switchPlayers();

                                boardL2.blackMoveSuccess = false;
                            } else if (currentPlayer == whitePlayer && boardL2.whiteMoveSuccess == true) {
                                switchPlayers();

                                boardL2.whiteMoveSuccess = false;
                            }
                        }

                        // LV1
                        try {
                            currentSquare.setFill(currentSquare.getColor());
                            System.out.println(currentSquare.getColor());
                            ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer, currentSquare,
                                    boardL1);
                            for (int i = 0; i < availableSquares.size(); i++) {

//                                    System.out.println("get avaiable X" + availableSquares.get(i).getX());
//                                    System.out.println("get available Y" + availableSquares.get(i).getY());
                                availableSquares.get(i).setStroke(null);
                                availableSquares.get(i).setFill(availableSquares.get(i).getColor());

                            }

                            if (currentPlayer == blackPlayer
                                    && boardL1.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
//                                ArrayList<Square> availableSquares = selectedPiece.movePattern(currentPlayer,currentSquare, board);
                                for (int i = 0; i < availableSquares.size(); i++) {
                                    if (availableSquares.get(i) == boardL1.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;
//                                        System.out.println(canMove);

                                }
                                if (canMove) {
                                    boardL1.moveBlackPiece(event2.getX(), event2.getY());
                                    currentSquare.setPiece(null);
                                    currentSquare.isOccupied = false;
                                }

                            } else if (currentPlayer == whitePlayer
                                    && boardL1.getSquareBY_JAVAFX_Coor(event2.getX(), event2.getY()) != currentSquare) {
                                for (int i = 0; i < availableSquares.size(); i++) {
                                    if (availableSquares.get(i) == boardL1.getSquareBY_JAVAFX_Coor(event2.getX(),
                                            event2.getY()))
                                        canMove = true;

                                }
                                if (canMove) {
                                    boardL1.moveWhitePiece(event2.getX(), event2.getY());
                                    currentSquare.setPiece(null);
                                    currentSquare.isOccupied = false;
                                }
                            }
                        } catch (Exception e) {
                            currentSquare.setPiece(selectedPiece);
//                            e.printStackTrace();
                            System.out.println("Choose the other color to play!");
                        }

                        if (currentPlayer == blackPlayer && boardL1.blackMoveSuccess == true) {
                            switchPlayers();

                            boardL1.blackMoveSuccess = false;
                        } else if (currentPlayer == whitePlayer && boardL1.whiteMoveSuccess == true) {
                            switchPlayers();

                            boardL1.whiteMoveSuccess = false;
                        }

                    }

                });

    }
    }
}
