package esi.atl.g52088.othello.model;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class allow to create a moveInformation. A moveInformation is an object
 * which represent what the current player is doing during his turn. This class
 * is composed by :
 * <ul>
 * <li> current player name </li>
 * <li> action of the current player </li>
 * <li> position of the current putted piece on the board</li>
 * <li> how many this putted piece has captured ennemy piece(s)</li>
 * </ul>
 *
 * @author g52088 - Guldentops Thomas
 */
public class MoveInformation {

    public static int id = 0;
    private int currentId;

    private SimpleStringProperty player;
    private SimpleStringProperty action;
    private String pos;
    private Integer flipped;

    /**
     * Allow to create a moveInformation
     *
     * @param player name of the current player
     * @param action what the current player did
     * @param pos where he putted his piece
     * @param flipped how many piece have been flipped
     */
    public MoveInformation(String player, String action, String pos, Integer flipped) {
        Objects.requireNonNull(player, "given player cannot be null");
        Objects.requireNonNull(action, "given action cannot be null");
        Objects.requireNonNull(pos, "given position cannot be null");
        Objects.requireNonNull(flipped, "given flipped cannot be null");

        this.player = new SimpleStringProperty(player);
        this.action = new SimpleStringProperty(action);
        this.pos = pos;
        this.flipped = flipped;
        id++;
        this.currentId = id;

    }

    /**
     * Allow to create a moveInformation
     *
     * @param player name of the current player
     * @param action what the current player did
     *
     * (it still continue to increment tho)
     */
    public MoveInformation(String player, String action) {
        Objects.requireNonNull(player, "given player cannot be null");
        Objects.requireNonNull(action, "given action cannot be null");

        this.player = new SimpleStringProperty(player);
        this.action = new SimpleStringProperty(action);
        id++;
        this.currentId = id;

    }

    /**
     * To have the number of the current id
     *
     * @return the current id
     */
    public int getId() {
        return currentId;
    }

    /**
     * To get the current player name
     *
     * @return the current player name
     */
    public String getPlayer() {
        return player.get();
    }

    /**
     * To get the current action
     *
     * @return the current action
     */
    public String getAction() {
        return action.get();
    }

    /**
     * To get how many piece was flipped during the turn
     *
     * @return number of flipped piece
     */
    public Integer getFlipped() {
        return flipped;
    }

    /**
     * To get the position on the putted piece
     *
     * @return position of last putted piece
     */
    public String getPos() {
        return pos;
    }

}
