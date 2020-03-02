package chess;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class PieceKnight extends Piece {
    private Image image;

//    private Tile initSQR=getTile();
    public double xPos;
    public double yPos;
    public ImageView iv;

    public PieceKnight(Player type, double x, double y) {
        super(type, x, y);
        String img;
        if (type.getColour() == Color.WHITE) {
            // image = new Image("file:src/ChessPiece/White_Bishop.png");
            img = "file:src/ChessPiece/White_Knight.png";
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
            img = "file:src/ChessPiece/Black_Knight.png";
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

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
            iv.setX(650);
            iv.setY(400);
        } else {
            iv.setX(750);
            iv.setY(400);
        }

    }

    public ArrayList<Square> movePattern(Player player, Square thisSquare, Board board) {
        ArrayList<Square> availableSquares = new ArrayList<>();
        int i = 0;

        // X + 2; Y + 1
        try {
            if ((board.getSquare( thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() + 1).returnPiece() != null
                    && board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() + 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour)
                    || !board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() + 1).isOccupied) {
                availableSquares.add(board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() + 1));
                
                availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 2);
                availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
                i = i++;
            }
        } catch (Exception e) {
            System.out.println("no");
        }
        
        // X + 2, Y - 1
        try {
            if((board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() - 1).returnPiece() != null &&
                    board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() - 1).returnPiece().player.colour != thisSquare.returnPiece().player.colour)||
                    !board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() - 1).isOccupied)  {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() + 2, thisSquare.getSQR_Y() - 1));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 2);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 1);
            i = i++;
            }
        } catch (Exception e) {
            System.out.println("not added");
        }

        // X - 2, Y + 1
        try {
            if ((board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() + 1 ).returnPiece() != null
                    && board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() + 1 )
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour)
                    || !(board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() + 1 )).isOccupied) {
                availableSquares.add(board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() + 1 ));
                availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 2);
                availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
                i = i++;
            }
        } catch (Exception e) {
            System.out.println("no");
        }
        
 

        // X - 2, Y - 1
        try {
            if ((board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() - 1).returnPiece() != null
                    && board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() - 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour)
                    || !board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() -1).isOccupied) {
                availableSquares.add(board.getSquare( thisSquare.getSQR_X() - 2, thisSquare.getSQR_Y() -1));
                
                availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 2);
                availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 1);
                i = i++;
            }
        } catch (Exception e) {
            System.out.println("just dont add this square");
        }
        
        // X - 1, Y + 2
        try {
            if((board.getSquare(thisSquare.getSQR_X() -  1, thisSquare.getSQR_Y() +  2).returnPiece() != null &&
                    board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y() +  2).returnPiece().player.colour != thisSquare.returnPiece().player.colour)||
                    !board.getSquare(thisSquare.getSQR_X() -  1, thisSquare.getSQR_Y() +  2).isOccupied)  {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() -  1, thisSquare.getSQR_Y() +  2));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 2);
            i = i++;
            }
        } catch (Exception e) {
            System.out.println("not added");
        }
        

        // X - 1, Y - 2
        try {
            if ((board.getSquare( thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y() - 2).returnPiece() != null
                    && board.getSquare( thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y() - 2)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour)
                    || !board.getSquare( thisSquare.getSQR_X()- 1, thisSquare.getSQR_Y() - 2).isOccupied) {
                availableSquares.add(board.getSquare( thisSquare.getSQR_X()- 1, thisSquare.getSQR_Y() - 2));
                availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 1);
                availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 2);
                
                i = i++;
            }
        } catch (Exception e) {
            System.out.println("not added");
        }

        
        //  X + 1, Y + 2,
        try {
            if((board.getSquare(thisSquare.getSQR_X() +  1, thisSquare.getSQR_Y() +  2).returnPiece() != null &&
                    board.getSquare(thisSquare.getSQR_X() +  1, thisSquare.getSQR_Y() +  2).returnPiece().player.colour != thisSquare.returnPiece().player.colour)||
                    !board.getSquare(thisSquare.getSQR_X() +  1, thisSquare.getSQR_Y() +  2).isOccupied)  {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() +  1, thisSquare.getSQR_Y() +  2));
            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 2);
            i = i++;
            }
        } catch (Exception e) {
            System.out.println("not added");
        }
      

        // X + 1, Y - 2 
        try {
            if((board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() - 2).returnPiece() != null &&
                    board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() - 2).returnPiece().player.colour != thisSquare.returnPiece().player.colour)||
                    !board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() - 2).isOccupied)  {
            availableSquares.add(board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y() - 2));

            availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 1);
            availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() - 2);
            i = i++;
            }
        } catch (Exception e) {
            System.out.println("not added");
        }



 

        return availableSquares;

    }

}
