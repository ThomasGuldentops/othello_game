package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Model;
import esi.atl.g52088.othello.model.PlayerColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class PlayerInformations extends VBox {

    private final Model game;

    private Text blackScoreText;
    private Text whiteScoreText;
    private final GridPane blackPlayerInformation;
    private final GridPane whitePlayerInformation;

    PlayerInformations(Model game) {
        this.game = game;

        HBox playerBox = new HBox();

        blackPlayerInformation = addBlackPlayerInfo();
        whitePlayerInformation = addWhitePlayerInfo();

        playerBox.getChildren().addAll(blackPlayerInformation, whitePlayerInformation);

        getChildren().addAll(playerBox);

        // --- css
        blackPlayerInformation.getStyleClass().add("playerInformation");
        whitePlayerInformation.getStyleClass().add("playerInformation");

        playerBox.getStyleClass().add("boxPlayer");

    }

    private GridPane addBlackPlayerInfo() {
        GridPane grid = new GridPane();

        Label nameLabel = new Label("Name");
        nameLabel.setAlignment(Pos.CENTER);

        Text nameText = new Text(game.getCurrentName());
        grid.add(nameLabel, 0, 0);
        grid.add(nameText, 0, 1);

        Label colorLabel = new Label("Piece");
        colorLabel.setAlignment(Pos.CENTER);

        Circle colorCircle = new Circle(15, Color.BLACK);
        colorCircle.setTranslateX(12);
        grid.add(colorLabel, 1, 0);
        grid.add(colorCircle, 1, 1);

        Label scoreLabel = new Label("Score");
        blackScoreText = new Text("2");
        blackScoreText.setTranslateX(25);
        grid.add(scoreLabel, 2, 0);
        grid.add(blackScoreText, 2, 1);

        blackScoreText.setFont(Font.font("",
                FontWeight.BOLD, FontPosture.REGULAR, 25));

        return grid;

    }

    private GridPane addWhitePlayerInfo() {
        GridPane grid = new GridPane();

        Label nameLabel = new Label("Name");
        nameLabel.setAlignment(Pos.CENTER);

        Text nameText = new Text(game.getOpponentName());
        grid.add(nameLabel, 0, 0);
        grid.add(nameText, 0, 1);

        Label colorLabel = new Label("Piece");
        colorLabel.setAlignment(Pos.CENTER);

        Circle colorCircle = new Circle(15, Color.WHITE);
        colorCircle.setTranslateX(12);

        grid.add(colorLabel, 1, 0);
        grid.add(colorCircle, 1, 1);

        Label scoreLabel = new Label("Score");
        whiteScoreText = new Text("2");
        whiteScoreText.setTranslateX(25);

        grid.add(scoreLabel, 2, 0);
        grid.add(whiteScoreText, 2, 1);

        whiteScoreText.setFont(Font.font("",
                FontWeight.BOLD, FontPosture.REGULAR, 25));

        return grid;
    }

    /**
     * To update the score of the black player.
     */
    void updateBlackScore() {
        blackScoreText.setText(String.valueOf(
                game.getScore(PlayerColor.BLACK)));

    }

    /**
     * To update the score of the white player.
     */
    void updateWhiteScore() {
        whiteScoreText.setText(String.valueOf(
                game.getScore(PlayerColor.WHITE)));
    }

    /**
     * Allow to change the current color of the background. The background will
     * change depend of the current player turn. It's will be green on the
     * player who must play now and black for the other.
     *
     * @param color color of the current player
     */
    void updateBackground(PlayerColor color) {
        if (color == PlayerColor.BLACK) {
            blackPlayerInformation.setBackground(new Background(
                    new BackgroundFill(Color.LIGHTGREEN,
                            CornerRadii.EMPTY, Insets.EMPTY)));

            whitePlayerInformation.setBackground(new Background(
                    new BackgroundFill(Color.DIMGRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            blackPlayerInformation.setBackground(new Background(
                    new BackgroundFill(Color.DIMGRAY,
                            CornerRadii.EMPTY, Insets.EMPTY)));

            whitePlayerInformation.setBackground(new Background(
                    new BackgroundFill(Color.LIGHTGREEN,
                            CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

}
