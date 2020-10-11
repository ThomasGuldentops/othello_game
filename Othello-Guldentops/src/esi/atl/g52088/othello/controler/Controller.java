package esi.atl.g52088.othello.controler;

import esi.atl.g52088.othello.model.Game;
import esi.atl.g52088.othello.view.console.View;
import java.util.Objects;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Controller {

    private final Game game;
    private final View view;

    /**
     * Controller of the game.
     *
     * @param game class Game
     * @param view class View
     */
    public Controller(Game game, View view) {
        Objects.requireNonNull(game, "Game can't be null.");
        Objects.requireNonNull(view, "View can't be null.");

        this.game = game;
        this.view = view;
    }

    /**
     * Allow to initialize the game.
     */
    public void initialize() {
        this.game.initialize();
        this.view.initialize();
    }

    /**
     * To use select command
     *
     * @param msg will be the row and column selected
     */
    private void playCommand(String msg) {
        try {
            
            int column = Integer.valueOf(msg.substring(msg.length() - 1));
            int row = Integer.valueOf(msg.substring(5, 6));

            game.play(row, column);
        }// Wrong usage of command
        catch (NumberFormatException | java.lang.ArrayIndexOutOfBoundsException e) {
            view.displayError("Vous avez entrés une donnée incorrecte.\n"
                    + e.toString());
        }

    }

    /**
     * Allow to display a message when the game is over.
     */
    private void displayEndGame() {
        game.getFinalScore();
        this.view.displayOver(game.getWinner());
    }

    /**
     * To start the game.
     */
    public void startGame() {
        game.start();
        view.displayHelp();
        boolean end = false;

        while (!game.isOver() && !end) {
            view.displayBoard(game.getBoard());
            view.displayCurrentPlayer(game.getCurrent());

            String command = view.askCommand();

            if (command.equals("quit")) {
                end = true;
                view.quit();
            }

            if (command.contains("play")) {
                playCommand(command);
            }

            if (command.equals("show")) {
                view.displayBoard(game.getBoard());
            }
        }

        if (!end) {
            //to fix the "quit" error
            this.displayEndGame();
        }

    }
}
