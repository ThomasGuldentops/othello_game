package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Action;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import esi.atl.g52088.othello.model.Model;
import esi.atl.g52088.othello.model.PlayerColor;
import java.util.Objects;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The class for the progress indicator and progress bar
 *
 * @author g52088 - Guldentops Thomas
 */
public class Indicators extends VBox {

    private ProgressBar pb;
    private ProgressIndicator pi;
    private final Model game;
    private final GameBoard boardGame;

    Indicators(Model game) {
        Objects.requireNonNull(game, "Given game cannot be null.");
        this.game = game;
        this.boardGame = new GameBoard(this.game);

        HBox pbBox = addProgressBar();
        HBox piBox = addPresgressIndicator();

        getChildren().addAll(pbBox, piBox);
    }

    /**
     * To add the progress bar on the view (just after the board game)
     *
     * @return the hbox which contain the progress bar
     */
    private HBox addProgressBar() {
        HBox hbox = new HBox();
        Label label = new Label("Noir / Blanc ");
        label.setStyle("-fx-padding: 15px;");

        pb = new ProgressBar(0.5);
        pb.setPrefWidth(300);
        pb.setStyle("-fx-accent : black;  -fx-padding: 15px;");
        updateProgressBar();

        hbox.getChildren().addAll(label, pb);
        return hbox;

    }

    /**
     * To add the indicator bar on the view (just after the board game)
     *
     * @return the hbox which contain the indicator bar
     */
    private HBox addPresgressIndicator() {
        HBox hbox = new HBox();
        Label label = new Label("Progression du jeu ");
        label.setStyle("-fx-padding: 15px;");

        pi = new ProgressIndicator();
        pi.setStyle("-fx-accent : black; -fx-padding: 15px;");
        updateProgressIndicator();

        hbox.getChildren().addAll(label, pi);
        return hbox;

    }

    /**
     * To update the progress bar. The calcul is the total number of box on the
     * board divide by the number of occupied box
     */
    void updateProgressIndicator() {
        try {
            if (game.isOver()
                    || game.getCurrentAction().equals(Action.SURREND.getText())) {
                pi.setProgress(1);
            } else {
                double totalSquares = game.getBoard().getRows()
                        * game.getBoard().getColumns();

                pi.setProgress(
                        boardGame.getNumberPiecesInGame(
                                game.getBoard()) / totalSquares);
            }
        } catch (Exception e) {
            System.out.println("Error updateProgressIndicator() : " + e.getMessage());
        }

    }

    /**
     * To update the progress bar. The calcul is the total score (addition
     * between the current and the opponent score ) divided by the score of the
     * opponent
     */
    void updateProgressBar() {
        try {

            double totalScore = game.getScore(PlayerColor.WHITE)
                    + game.getScore(PlayerColor.BLACK);

            pb.setProgress(game.getScore(PlayerColor.BLACK) / totalScore);

        } catch (Exception e) {
            System.out.println("Error updateProgressBar() : " + e.getMessage());
        }
    }

}
