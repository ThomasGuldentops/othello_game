package esi.atl.g52088.othello.main;

import esi.atl.g52088.othello.controler.Controller;
import esi.atl.g52088.othello.model.Game;
import esi.atl.g52088.othello.view.console.View;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Othello {

    /**
     * Main class of Othello.
     *
     * Othello is a strategy board game played between 2 players. One player
     * plays black and the other white.
     *
     * Each player gets 32 discs and black always starts the game.
     *
     * Then the game alternates between white and black until:
     *
     * one player can not make a valid move to outflank the opponent. both
     * players have no valid moves.
     *
     * When a player has no valid moves, he pass his turn and the opponent
     * continues.
     *
     * A player can not voluntarily forfeit his turn.
     *
     * When both players can not make a valid move the game ends.
     *
     * The goal is to get the majority of colour discs on the board at the end
     * of the game.
     *
     * @param args //
     */
    public static void main(String[] args) {
        Game game = new Game();
        View view = new View();
        Controller controller = new Controller(game, view);

        controller.initialize();

        controller.startGame();
    }
}
