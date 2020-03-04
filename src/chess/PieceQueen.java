package chess;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Chess PieceQueen
 * 
 * @author Judao Zhong
 * @version 2.0
 *
 */
public class PieceQueen extends Piece {
    private Image image;

    public double xPos;
    public double yPos;
//    private static final double leftGridValues = 1;
//    private static final double rightGridValues = 75.02;
//    private static final double upGridValues = 1;
//    private static final double downGridValues = 75.02;
    public ImageView iv;

    public PieceQueen(Player type, double x, double y) {
        super(type, x, y);
        String img;
        if (type.getColour() == Color.WHITE) {
            // image = new Image("file:src/ChessPiece/White_Bishop.png");
            img = "file:src/ChessPiece/White_Queen.png";
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
            img = "file:src/ChessPiece/Black_Queen.png";
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
            iv.setX(100);
            iv.setY(560);
        } else {
            iv.setX(160);
            iv.setY(560);
        }
    }

    public ArrayList<Square> movePattern(Player player, Square thisSquare, Board board) {
        ArrayList<Square> availableSquares = new ArrayList<>();
        int i = 0;
        int j = thisSquare.getSQR_X();
        int k = thisSquare.getSQR_Y();

        // SE
        while (j < 7 && k < 7) {

            try {

                j += 1;
                k += 1;
                availableSquares.add(board.getSquare(j, k));
                availableSquares.get(i).setSQR_X(board.getSquare(k, j).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(k, j).getSQR_Y());
                i++;
                if(board.getSquare(j, k).isOccupied) {
                    if (board.getSquare(j, k).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        availableSquares.remove(i-1);
                        i--;
                    }
                    break;
                } 
            } catch (Exception e) {
                System.out.println("not added");
            }

        }
        j = thisSquare.getSQR_X();
        k = thisSquare.getSQR_Y();

        // NE
        while (j < 7 && k > 1) {

            try {
                j += 1;
                k -= 1;
                availableSquares.add(board.getSquare(j, k));
                availableSquares.get(i).setSQR_Y(board.getSquare(j, k).getSQR_Y());
                availableSquares.get(i).setSQR_X(board.getSquare(j, k).getSQR_X());
                i++;
                if(board.getSquare(j, k).isOccupied) {
                    if (board.getSquare(j, k).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        availableSquares.remove(i-1);
                        i--;
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        j = thisSquare.getSQR_X() ;
        k = thisSquare.getSQR_Y() ;

        // NW
        while (j > 1 && k > 1) {

            try {

                j -= 1;
                k -= 1;
                availableSquares.add(board.getSquare(j, k));
                availableSquares.get(i).setSQR_X(board.getSquare(j, k).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(j, k).getSQR_Y());
                i++;
                if(board.getSquare(j, k).isOccupied) {
                    if (board.getSquare(j, k).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        availableSquares.remove(i-1);
                        i--;
                    }
                    break;
                }
            } catch (Exception e) {
                System.out.println("not added");
            }

        }
        j = thisSquare.getSQR_X();
        k = thisSquare.getSQR_Y() ;

        // SW
        while (j > 1 && k < 7 ) {

            try { 

                j -= 1;
                k += 1;

                
                availableSquares.add(board.getSquare(j, k));
                availableSquares.get(i).setSQR_Y(board.getSquare(j, k).getSQR_Y());
                availableSquares.get(i).setSQR_X(board.getSquare(j, k).getSQR_X());
                i++;
                
      
                
                if (board.getSquare(j - 1 , k +1).isOccupied) {
                    System.out.println(thisSquare);
                    
                    if (board.getSquare(j - 1 , k + 1).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        System.out.println("sameClr"+thisSquare.returnPiece().player.colour);
                        availableSquares.remove(i-1);
                        i--;
                    }    
                    break;
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("not added");
            }
        }
        
            //down
            k = thisSquare.getSQR_Y();
            while (k < 7) {
                k += 1;
               
                availableSquares.add(board.getSquare(thisSquare.getSQR_X(), k));
                availableSquares.get(i).setSQR_Y(board.getSquare(thisSquare.getSQR_X(), k).getSQR_Y());
                availableSquares.get(i).setSQR_X(board.getSquare(thisSquare.getSQR_X(), k).getSQR_X());
                i++;
                if(board.getSquare(thisSquare.getSQR_X(), k).isOccupied) {
                    if (board.getSquare(thisSquare.getSQR_X(), k).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        availableSquares.remove(i-1);
                        i--;
                    }
                    break;
                }
            }
      
        
        //right
        j = thisSquare.getSQR_X();
        while (j < 7) {
            j += 1;


            availableSquares.add(board.getSquare(j, thisSquare.getSQR_Y()));
            availableSquares.get(i).setSQR_Y(board.getSquare(j, thisSquare.getSQR_Y() ).getSQR_Y());
            availableSquares.get(i).setSQR_X(board.getSquare(j, thisSquare.getSQR_Y() ).getSQR_X());
            i++;
            
            if (board.getSquare(j, thisSquare.getSQR_X() ).isOccupied) {
                if (board.getSquare(j, thisSquare.getSQR_X()).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                    availableSquares.remove(i-1);
                    i--;
                }
                break;
            }
        }
        //up
        int l = thisSquare.getSQR_Y();
        while (l > 0) {
            l -= 1;
            availableSquares.add(board.getSquare(thisSquare.getSQR_X(), l));
            availableSquares.get(i).setSQR_Y(board.getSquare(thisSquare.getSQR_X() , l).getSQR_Y());
            availableSquares.get(i).setSQR_X(board.getSquare(thisSquare.getSQR_X() , l).getSQR_X());
            i++;
            if (board.getSquare(thisSquare.getSQR_X(), l).isOccupied) {
                if (board.getSquare(thisSquare.getSQR_X(), l).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                    availableSquares.remove(i-1);
                    i--;
                }
                break;
            }

        }
        //left
        try {
            int m = thisSquare.getSQR_X() ;
            while (m > 0) {
                m -= 1;
                availableSquares.add(board.getSquare(m, thisSquare.getSQR_Y()) );
                availableSquares.get(i).setSQR_Y(board.getSquare(m, thisSquare.getSQR_Y() ).getSQR_Y());
                availableSquares.get(i).setSQR_X(board.getSquare(m, thisSquare.getSQR_Y() ).getSQR_X());
                i++;
                if (board.getSquare(m, thisSquare.getSQR_Y()).isOccupied) {
                    if (board.getSquare(m, thisSquare.getSQR_Y()).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        availableSquares.remove(i-1);
                        i--;
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("some not added");
        }
        

        return availableSquares;

    }
}
