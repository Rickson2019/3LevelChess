package chess;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class PieceBishop extends Piece{
    private Image image;
   
//    private Tile initSQR=getTile();
    public double xPos;
    public double yPos;
    
//    private static final double  leftGridValues = 1;
//    private static final double  rightGridValues = 75.02;
//    private static final double  upGridValues = 1;
//    private static final double  downGridValues = 75.02;
    
    public ImageView iv;
    int count = 0;
    public PieceBishop(Player type, double x, double y) {
        super(type, x, y);
        String img;
        if(type.getColour()==Color.WHITE){
            //image = new Image("file:src/ChessPiece/White_Bishop.png");
            img = "file:src/ChessPiece/White_Bishop.png";
            //ImageView imageView = new ImageView();
            //imageView.setImage(image);
            //setSquare(initSQR);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();
//            imageView.setPreserveRatio(true);
//            imageView.setSmooth(true);
//            imageView.setCache(true);
            
            
            
            
            
            
            xPos = x * square.SQRsize+1;
            yPos = y * square.SQRsize+1;
        }
        else{
            img = "file:src/ChessPiece/Black_Bishop.png";
            //ImageView imageView = new ImageView();
            //imageView.setImage(image);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();
//            imageView.setPreserveRatio(true);
//            imageView.setSmooth(true);
//            imageView.setCache(true);
            
            xPos = x * square.SQRsize+1;
            yPos = y * square.SQRsize+1;
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
        iv.setX(xPos);
        iv.setY(yPos);
        
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
       
        if(getOwner().getColour()==Color.WHITE) {
            iv.setX(650);
            iv.setY(120);
        }else {
            iv.setX(750);
            iv.setY(120);
        }

       
       
//        count++;
//        Game.bishopLabel_white.setText(""+count);
        Game.bishopLabel_white.setTranslateX(730);
        Game.bishopLabel_white.setTranslateY(70);
        Game.bishopLabel_white.setScaleX(2);
        Game.bishopLabel_white.setScaleY(2);
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
                System.out.println("not added");
            }

        }
        j = thisSquare.SQR_X;
        k = thisSquare.SQR_Y;

        // NE
        while (j < 8 && k > 0) {

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

        j = thisSquare.getSQR_X();
        k = thisSquare.getSQR_Y();

        // NW
        while (j > 0 && k > 0) {

            try {

                j -= 1;
                k -= 1;
                availableSquares.add(board.getSquare(j, k));

                
                availableSquares.get(i).setSQR_X(board.getSquare(j, k).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(j, k).getSQR_Y());
                i++;
                if(board.getSquare(j, k).isOccupied) {
                    if (board.getSquare(j, k).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
                        i--;
                        availableSquares.remove(i);
                        
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
        while (j > 0 && k < 8 ) {

            try { 

                j -= 1;
                k += 1;

                
                availableSquares.add(board.getSquare(j, k));
                availableSquares.get(i).setSQR_X(board.getSquare(j, k).getSQR_X());
                availableSquares.get(i).setSQR_Y(board.getSquare(j, k).getSQR_Y());
                i++;
                
      
                
                if (board.getSquare(j , k).isOccupied) {
                    System.out.println(thisSquare);
                    
                    if (board.getSquare(j , k).returnPiece().player.colour == thisSquare.returnPiece().player.colour) {
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
        return availableSquares;

    }

}
