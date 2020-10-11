package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Action;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import esi.atl.g52088.othello.model.Model;
import java.util.Objects;
import javafx.scene.layout.HBox;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class OthelloButtons extends GridPane {

    private final Model game;
    private final GameBoard gameBoard;

    OthelloButtons(Model game, GameBoard gameBoard) {
        Objects.requireNonNull(game, "Given game cannot be null.");
        Objects.requireNonNull(gameBoard, "Given gameBoard cannot be null.");
        this.game = game;
        this.gameBoard = gameBoard;

        HBox buttonbox = new HBox(10);
        buttonbox.setPadding(new Insets(0, 20, 5, 20));
        buttonbox.setStyle("-fx-padding: 5px");
        
        getChildren().add(buttonbox);

        insertQuitButton(buttonbox);
        insertPassButton(buttonbox);
        insertRetryButton(buttonbox);
    }

    /**
     * To insert the quit button
     *
     * @param buttonBox the submit button
     */
    private void insertQuitButton(HBox buttonBox) {
        Button abandon = new Button("Abandonner");
        buttonBox.getChildren().add(abandon);

        abandon.setOnAction(new AbandonButtonHandler());
    }

    /**
     * To insert the pass button
     *
     * @param buttonBox the submit button
     */
    private void insertPassButton(HBox buttonBox) {
        Objects.requireNonNull(buttonBox, "Given HBox cannot be null.");
        Button pass = new Button("Passer");
        pass.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(pass);

        pass.setOnAction(new PassButtonHandler());
    }

    /**
     * To insert the retry button
     *
     * @param buttonBox the submit button
     */
    private void insertRetryButton(HBox buttonBox) {
        Objects.requireNonNull(buttonBox, "Given HBox cannot be null.");
        Button retry = new Button("Recommencer");
        buttonBox.getChildren().add(retry);

        retry.setOnAction(new RetryButtonHandler());
    }

    /**
     * To create the quit button. This button allow to the current player to
     * abandon the game. If the current player click on this button he'll lose.
     */
    class AbandonButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            try {
                game.setCurrentAction(Action.SURREND);
                game.setGameOver(true);
                

            } catch (Exception err) {
                System.out.println("Erreur : " + err.getMessage());
            }

        }
    }

    /**
     * To create the pass button. This button allow to the current player to
     * pass his turn. Pass the turn means the opponent player will be play
     * without the current player had played.
     */
    private class PassButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            try {
                game.setCurrentAction(Action.PASS);
                game.swapPlayers();

            } catch (Exception err) {
                System.out.println("Erreur : " + err.getMessage());
            }

        }
    }

    /**
     * To start a new game.
     */
    private class RetryButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            try {
                game.setCurrentAction(Action.BEGIN);
                game.resetGame();

            } catch (Exception err) {
                System.out.println("Erreur : " + err.getMessage());
            }

        }
    }
}
