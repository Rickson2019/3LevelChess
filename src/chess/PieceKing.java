package chess;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class PieceKing extends Piece {
    private Image image;

    public double xPos;
    public double yPos;
    public ImageView iv;

    public PieceKing(Player type, double x, double y) {
        super(type, x, y);
        String img;
        if (type.getColour() == Color.WHITE) {

            img = "file:src/ChessPiece/White_King.png";

            xPos = x * square.SQRsize;
            yPos = y * square.SQRsize;
        } else {
            img = "file:src/ChessPiece/Black_King.png";

            xPos = x * square.SQRsize;
            yPos = y * square.SQRsize;
        }
        this.image = new Image(img);
        iv = new ImageView(image);

        iv.setY(yPos);
        iv.setX(xPos);
        getChildren().add(iv);
    }

//    @Override
//    public boolean validMove() {
//
//        return true;
//    }
    public void move(int SQR_X, int SQR_Y,Board board) {
        if(board.boardLv == 1) {
            iv.setX(SQR_X * Board.SQRsize+0.1);
            iv.setY(SQR_Y * Board.SQRsize+0.1);
        }
        if(board.boardLv ==2) {
            iv.setX(SQR_X * Board.SQRsize + Game.xCoor_Board2L+0.1);
            iv.setY(SQR_Y * Board.SQRsize+0.1);
        }
        if(board.boardLv ==3) {
            iv.setX(SQR_X * Board.SQRsize + Game.xCoor_Board3L+0.1);
            iv.setY(SQR_Y * Board.SQRsize+0.1);
        }
    }

    public void setCaptured() {
        setSquare(null);
        
        if (getOwner().getColour() == Color.WHITE) {
            iv.setX(650);
            iv.setY(100);
        } else {
            iv.setX(750);
            iv.setY(100);
        }

    }

    public ArrayList<Square> movePattern(Player player, Square thisSquare, Board board) {
        ArrayList<Square> availableSquares = new ArrayList<>();
        int i = 0;

        // Y+1
        try {
            if (!board.getSquare(thisSquare.getSQR_X()  , thisSquare.getSQR_Y() + 1).isOccupied
                    || board.getSquare( thisSquare.getSQR_X() , thisSquare.getSQR_Y() + 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                availableSquares.add(board.getSquare( thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 1));
                availableSquares.get(i).setSQR_X(thisSquare.getSQR_X());
                availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Y+1 not added");
        }

        // Y-1
        try {
            if (!board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() - 1).isOccupied
                    || board.getSquare(thisSquare.getSQR_X() , thisSquare.getSQR_Y() - 1 )
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                availableSquares.add(board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() - 1));
                availableSquares.get(i).setSQR_X(thisSquare.getSQR_X());
                availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 1);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Y-1 not added");
        }

        // X-1
        try {
            if (!board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y()).isOccupied
                    || board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y())
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y()));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y());
            i++;
            }
        } catch (Exception e) {
            System.out.println("X-1 not added");
        }

        // X+1
        try {
            if (!board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y()).isOccupied
                    || board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y())
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y()));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y());
            i++;
            }
        } catch (Exception e) {
            System.out.println("X+1 not added");
        }

        // X+1, Y+1 working
        try {
            if (!board.getSquare(thisSquare.getSQR_X()+1, thisSquare.getSQR_Y()+1).isOccupied
                    || board.getSquare(thisSquare.getSQR_X()+1, thisSquare.getSQR_Y() +1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                    availableSquares.add(
                    board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() + 1));
                    availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 1);
                    availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
                    i++;
            }
        } catch (Exception e) {
            System.out.println("X+1,Y+1 not added");
        }

        // X-1, Y-1 working
        try {
            if(!board.getSquare(thisSquare.getSQR_X()-1, thisSquare.getSQR_Y() - 1).isOccupied
                    || board.getSquare(thisSquare.getSQR_X()-1, thisSquare.getSQR_Y() - 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() - 1 , thisSquare.getSQR_Y() - 1));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 1);
            i++;
            }
        } catch (Exception e) {
            System.out.println("X-1 Y-1 not added");
        }

        // X-1 Y+1 working
        try {
            if (!board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y() + 1).isOccupied
                    || board.getSquare(thisSquare.getSQR_X() - 1 , thisSquare.getSQR_Y() + 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y() + 1 ));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
            i++;
            }
        } catch (Exception e) {
            System.out.println("X-1 Y+1 not added");
        }

        // X+1 Y-1 working
        try {
            if(!board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() - 1).isOccupied
                    || board.getSquare(thisSquare.getSQR_X()+1, thisSquare.getSQR_Y() - 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() - 1));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 1);
            i++;
            }
        } catch (Exception e) {
            System.out.println("X+1 Y-1 Not added");
        }

        return availableSquares;
    }

}
