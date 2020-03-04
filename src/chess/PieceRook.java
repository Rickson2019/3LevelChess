package chess;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Chess PieceRook is a child class of Piece.
 * 
 * @author Judao Zhong
 * @version 1.0
 *
 */
public class PieceRook extends Piece {
    private Image image;

//    private Tile initSQR=getTile();
    public double xPos;
    public double yPos;
    public ImageView iv;

    public PieceRook(Player type, double x, double y) {
        super(type, x, y);
        String img;
        if (type.getColour() == Color.WHITE) {
            // image = new Image("file:src/ChessPiece/White_Bishop.png");
            img = "file:src/ChessPiece/White_Rook.png";
            // ImageView imageView = new ImageView();
            // imageView.setImage(image);
            // setSquare(initSQR);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();
//            imageView.setPreserveRatio(true);
//            imageView.setSmooth(true);
//            imageView.setCache(true);

            xPos = x * square.SQRsize;
            yPos = y * square.SQRsize;
        } else {
            img = "file:src/ChessPiece/Black_Rook.png";
            // ImageView imageView = new ImageView();
            // imageView.setImage(image);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();
//            imageView.setPreserveRatio(true);
//            imageView.setSmooth(true);
//            imageView.setCache(true);

            xPos = x * square.SQRsize;
            yPos = y * square.SQRsize;
        }
        this.image = new Image(img);
        iv = new ImageView(image);
//        imageView.setImage(image);
//        imageView.fitHeightProperty();
//        imageView.fitWidthProperty();
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
//        iv.setX(getSquare().getX()*80);
        iv.setY(yPos);
        iv.setX(xPos);
        getChildren().add(iv);
    }

//    @Override
//    public boolean validMove() {
//        // TODO Auto-generated method stub
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
            iv.setX(150);
            iv.setY(510);

        } else {
            iv.setX(200);
            iv.setY(510);
        }

    }

    @Override
    public ArrayList<Square> movePattern(Player player, Square thisSquare, Board board) {
        ArrayList<Square> availableSquares = new ArrayList<>();
        int i = 0;

        // down
        int j = thisSquare.getSQR_Y();
        while (j < 7) {
        try {
                j += 1;
                availableSquares.add(board.getSquare(thisSquare.getSQR_X(), j));
                availableSquares.get(i).setSQR_X(board.getSquare(thisSquare.getSQR_X(), j).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(thisSquare.getSQR_X(), j).getSQR_Y());
                i++;         
            } catch (Exception e) {
                System.out.println("some not added");
                continue;
                
            }
        if (board.getSquare(thisSquare.getSQR_X(), j).isOccupied) {
            if (board.getSquare(thisSquare.getSQR_X(), j).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                availableSquares.remove(i-1);
                i--;
            }
            break;
        }
        }
        
       
        // right
        int k = thisSquare.getSQR_X();
        while (k < 7) {
        try {
                k += 1;
                availableSquares.add(board.getSquare(k, board.getSquareY(k, thisSquare.getSQR_Y())));
                availableSquares.get(i).setSQR_X(board.getSquare(k,thisSquare.getSQR_Y()).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(k,thisSquare.getSQR_Y()).getSQR_Y());
                i++;

        } catch (Exception e) {
            System.out.println("some not added");
        }
        if (board.getSquare(k, thisSquare.getSQR_Y()).isOccupied) {
            if (board.getSquare(k, thisSquare.getSQR_Y()).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                availableSquares.remove(i-1);
                i--;
            }
            break;
        }

        }

        // up
        int l = thisSquare.getSQR_Y();
        while (l > 0) {
        try {
                l -= 1;
                availableSquares.add(board.getSquare(thisSquare.getSQR_X(), l));
                availableSquares.get(i).setSQR_X(board.getSquare(thisSquare.getSQR_X(), l).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(thisSquare.getSQR_X(), l).getSQR_Y());
                i++;         
            
            } catch (Exception e) {
                continue;
            }
        if (board.getSquare(thisSquare.getSQR_X(), l).isOccupied) {
            if (board.getSquare(thisSquare.getSQR_X(), l).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                availableSquares.remove(i-1);
                i--;
            }
            break;
        }
        }       

        // left
        int m = thisSquare.getSQR_X();
        while (m > 0 && !board.getSquare(m - 1, thisSquare.getSQR_Y()).isOccupied) {
            try {
                m -= 1;
                availableSquares.add(board.getSquare(m, thisSquare.getSQR_Y()));
                availableSquares.get(i).setSQR_X(board.getSquare(m, thisSquare.getSQR_Y()).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(m, thisSquare.getSQR_Y()).getSQR_Y());
                i++;
            } catch (Exception e) {
                continue;
            }
            if (board.getSquare(m  , thisSquare.getSQR_Y()).isOccupied) {
                if (board.getSquare(m , thisSquare.getSQR_Y() ).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                    availableSquares.remove(i-1);
                    i--;
                }
                break;
            }

        }
        
        return availableSquares;
    
    
    }
}