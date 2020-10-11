package esi.atl.g52088.othello.view.viewFX;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Allow to create a window before the game with two labels and two textFields,
 * where the players can write their names
 *
 * @author g52088 - Guldentops Thomas
 */
public class PlayerName extends GridPane {

    private TextField currentName;
    private TextField opponentName;
    private CheckBox chooseBot;

    public PlayerName() {

        Label playerBlack = new Label("Joueur 1");
        add(playerBlack, 0, 0);
        currentName = new TextField();
        add(currentName, 1, 0);

        Label playerWhite = new Label("Joueur 2");
        add(playerWhite, 0, 1);

        opponentName = new TextField();
        add(opponentName, 1, 1);

        createCheckBoxBot();

        setVgap(20);
        setMinSize(1000, 750);
        setAlignment(Pos.CENTER);

        // --- css style
        playerBlack.getStyleClass().add("label");
        playerWhite.getStyleClass().add("label");

        playerBlack.getStyleClass().add("nameLabel");
        playerWhite.getStyleClass().add("nameLabel");

        currentName.getStyleClass().add("playerName");
        opponentName.getStyleClass().add("playerName");
    }

    private void createCheckBoxBot() {
        chooseBot = new CheckBox();
        Label botLabel = new Label("Bot");
        add(botLabel, 0, 2);

        chooseBot.setOnAction(e -> {
            if (chooseBot.isSelected()) {
                opponentName.setText("bot");
                opponentName.setDisable(true);
            } else {
                opponentName.setText("");
                opponentName.setDisable(false);
            }
        });

        botLabel.setStyle("-fs-text-fill : #FFFFFF;");
        add(chooseBot, 1, 2);
    }

    /**
     * To know the current player name (BLACK PLAYER)
     *
     * @return the current player name
     */
    public String getCurrentName() {
        return currentName.getText();
    }

    /**
     * To know the opponent player name (WHITE PLAYER)
     *
     * @return the opponent player name
     */
    public String getOpponentName() {
        return opponentName.getText();
    }

    
    /**
     * The chooseBox
     * @return the bot's chooseBox
     */
    public CheckBox getChooseBot() {
        return chooseBot;
    }

}
