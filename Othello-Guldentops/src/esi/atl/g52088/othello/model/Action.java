package esi.atl.g52088.othello.model;

/**
 * This enum allow to create an action during the game. Action may be
 * <ul>
 * <li> Begin : when the game begin or the game is restarted </li>
 * <li> Pass : when a player pass his turn</li>
 * <li> Surrend : when a player surrended</li>
 * <li> Put : when a player put a piece on the board</li>
 * </ul>
 *
 * @author g52088 - Guldentops Thomas
 */
public enum Action {
    BEGIN("commence"),
    PASS("passe "),
    SURREND("abandonne"),
    PUT("place");

    private final String text;

    /**
     * Constructor of the action enum. Allow to create a text with the current
     * action.
     *
     * @param text this text will add some informaiton on the action
     */
    private Action(String text) {
        this.text = text;
    }

    /**
     * To return the text
     *
     * @return get the text of the action
     */
    public String getText() {
        return text;
    }

}
