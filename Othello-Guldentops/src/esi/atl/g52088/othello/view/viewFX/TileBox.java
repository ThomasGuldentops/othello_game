package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Model;
import esi.atl.g52088.othello.model.Position;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class TileBox extends Rectangle {

    private final Position position;
    private final static int SIZE_TILE = 50;
    private Color fillColor = Color.GREEN;

    /**
     * To create a new tile. A tile is a square on the board.
     *
     * @param position the position of the new created tile.
     */
    TileBox(Position position, Model game) {
        super(SIZE_TILE, SIZE_TILE);

        this.setStroke(Color.GRAY);
        
        fillColor = game.getSpecialsSquares().contains(position) ? Color.AQUA : Color.GREEN;
        this.setFill(fillColor);
        
        this.position = position;

    }

    public Color getFillColor() {
        return fillColor;
    }
    
    

}
