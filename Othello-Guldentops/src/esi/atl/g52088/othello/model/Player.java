package esi.atl.g52088.othello.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Player {

    private final PlayerColor color;
    private final List<Piece> pieces;
    private final List<Piece> bag;
    private int score;
    private String name;
    private boolean bot;

    /**
     * To create a player with his color and initializate his bag. The bag will
     * contains 30 pieces (plus 2 piece already putted on the board).
     *
     * @throws NullPointerException thrown if the given color is null.
     *
     * @param color color of a Player.
     */
    Player(PlayerColor color, String name) {
        Objects.requireNonNull(color, "A color cannot be null");
        Objects.requireNonNull(name, "The given name can't be null.");

        this.color = color;
        this.name = name;
        
        if (name.equals("bot")) {
            bot = true;
        }
        
        this.pieces = new ArrayList<>();
        this.bag = new ArrayList<>();
        this.score = 0;

        fillBagPlayer(color);

    }

    private void fillBagPlayer(PlayerColor color) {
        Objects.requireNonNull(color, "The given color can't be null.");
        
        //One piece with value 3 will be added on the bag
        this.bag.add(new Piece(color, 3));
        //Ten pieces with value 2 will be added on the bag
        for (int i = 0; i < 10; i++) {
            this.bag.add(new Piece(color, 2));
        }
        //Eighteen pieces with value 1 will be added on the bag
        for (int i = 0; i < 18; i++) {
            this.bag.add(new Piece(color, 1));
        }
        //One piece with value 0 will be added on the bag
        this.bag.add(new Piece(color, 0));
    }

    /**
     * To know the player color
     *
     * @return the color
     */
    public PlayerColor getColor() {
        return color;
    }

    /**
     * Get list of player's pieces.
     *
     * @return list of pieces.
     */
    public List<Piece> getPieces() {
        return this.pieces;
    }

    /**
     * To change de current score of the current player
     *
     * @throws IllegalArgumentException Thrown if the given score is negative
     * @param score the new score value
     */
    void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("A score can't be negative");
        }
        this.score = score;
    }

    /**
     * To return the current score
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Allows to add a Piece on List : Piece 's player
     *
     * @param piece piece will be added
     */
    void addPiece(Piece piece) {
        Objects.requireNonNull(piece, "The given piece can't be null.");
        this.bag.add(piece);
    }
    
    void addPieceInBoard(Piece piece){
        pieces.add(piece);
    }

    /**
     * Allow to delete a piece on the player's piece list
     *
     * @param piece piece will be deleted
     */
    void remove(Piece piece) {
        Objects.requireNonNull(piece, "The given piece can't be null.");
        bag.remove(piece);
    }

    /**
     * To know all the content of the bag
     *
     * @return a list of the all pieces containt on the bag
     */
    List<Piece> getBagContent() {
        return bag;
    }

    /**
     * To know if the bag is empty or not
     *
     * @return true if the bag is empty, else otherwise
     */
    boolean hasPieceInBag() {
        return !bag.isEmpty();
    }

    /**
     * To draw a random piece
     *
     * @return the random drawn piece
     */
    public Piece randomPiece() {
        int randomIndex = (int) (Math.random() * this.bag.size() - 1);
        Piece drawnPiece = bag.get(randomIndex);
        return drawnPiece;
    }

    /**
     * To reset the stats of player.
     */
    void resetPlayer() {
        this.score = 0;
        this.getBagContent().clear();
        this.pieces.clear();
    }

    /**
     * To get the player's name
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return color == PlayerColor.BLACK ? "Blanc" : "Noir";

    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + Objects.hashCode(this.bag);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.color != other.color) {
            return false;
        }
        return Objects.equals(this.bag, other.bag);
    }

}
