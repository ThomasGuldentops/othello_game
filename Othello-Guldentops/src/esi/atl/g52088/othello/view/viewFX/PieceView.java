package esi.atl.g52088.othello.view.viewFX;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import esi.atl.g52088.othello.model.Piece;
import esi.atl.g52088.othello.model.PlayerColor;

/**
 * This class will allow to represent a piece by a circle.
 * The circle may be BLACK or WHITE
 * 
 * @author g52088 - Guldentops Thomas
 */
public class PieceView extends Circle {

    private final Piece piece;

    PieceView(Piece piece) {
        this.piece = piece;
        this.setPiece(piece);

        this.setRadius(25);
    }

    /**
     * To fill the color of the piece ith the right color.
     * @param piece 
     */
    void setPiece(Piece piece) {
        this.setFill(piece.getColor()
                == PlayerColor.BLACK ? Color.BLACK : Color.WHITE);
    }

}
