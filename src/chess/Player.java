package chess;

import javafx.scene.paint.Color;

/**
 * Chess Player
 * @author Judao Zhong
 * @version 1.0
 *
 */
public class Player {
    
    /** The colour. */
    public final Color colour;


    /**
     * Instantiates a new player.
     *
     * @param playerColour the player colour
     */
    public Player(Color playerColour){

        colour = playerColour;
    }

    /**
     * Gets the colour.
     *
     * @return the colour
     */
    public Color getColour(){
          return colour;
    }
}

