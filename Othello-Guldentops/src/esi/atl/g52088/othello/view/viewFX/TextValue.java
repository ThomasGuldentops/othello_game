package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.PlayerColor;
import java.util.Objects;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
class TextValue extends Text {


    /**
     * To create a text on a circle. This text will contains the value of
     * the piece.
     * @param value te value of the piece
     * @param color the color of the text
     */
    public TextValue(String value, PlayerColor color) {
        super(value);
        Objects.requireNonNull(color);
        
        setTextAlignment(TextAlignment.CENTER);
        setTranslateX(15);
        setFont(Font.font(30));
        setFill(this.setColor(color));
        
    }

    /**
     * To change color of the text (can't read a black text on a black piece)
     * @param color
     * @return 
     */
    private Color setColor(PlayerColor color){
        return color == PlayerColor.BLACK ? Color.WHITE : Color.BLACK;
    }
}
