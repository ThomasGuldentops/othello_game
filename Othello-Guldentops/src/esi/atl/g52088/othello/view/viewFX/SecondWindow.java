package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Game;
import esi.atl.g52088.othello.model.Model;
import esi.atl.g52088.othello.model.PlayerColor;
import esi.atl.g52088.othello.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class SecondWindow implements Observer {

    private VBox secondWindow;
    private Model game;

    private Label namePlayer1;
    private Label namePlayer2;

    private Label scorePlayer1;
    private Label scorePlayer2;
    private Text scoreP1;
    private Text scoreP2;

    private Label nbJetonJoueur1;
    private Label nbJetonJoueur2;
    private Text jetonP1;
    private Text jetonP2;

    public SecondWindow(Model game) {
        this.game = game;
        game.addObserver(this);

        // --- label creation
        namePlayer1 = new Label(game.getCurrentName());
        namePlayer2 = new Label(game.getOpponentName());

        scorePlayer1 = new Label("Score du joueur " + namePlayer1.getText());
        scorePlayer2 = new Label("Score du joueur " + namePlayer2.getText());

        nbJetonJoueur1 = new Label("Nombre jeton player  " + namePlayer1.getText());
        nbJetonJoueur2 = new Label("Nombre jeton player  " + namePlayer2.getText());

        //  --- variables informations
        scoreP1 = new Text("2");
        scoreP2 = new Text("2");

        jetonP1 = new Text("2");
        jetonP2 = new Text("2");

        // --- main window
        secondWindow = new VBox(25);

        secondWindow.getChildren().addAll(
                namePlayer1,
                namePlayer2,
                scorePlayer1, scoreP1,
                scorePlayer2, scoreP2,
                nbJetonJoueur1, jetonP1,
                nbJetonJoueur2, jetonP2);

        Scene scene = new Scene(secondWindow, 500, 500);

        Stage newWindow = new Stage();
        newWindow.setMaxWidth(1000);
        newWindow.setMaxHeight(750);

        newWindow.setTitle("Information Players");
        newWindow.setScene(scene);

        newWindow.show();

    }

    @Override
    public void update() {
        if (this != null) {
            setScoreP1(String.valueOf(game.getScore(PlayerColor.BLACK)));
            setScoreP2(String.valueOf(game.getScore(PlayerColor.WHITE)));
            setJetonP1(String.valueOf(game.getBoard().getTakenSquare(game.getCurrent()).size()));
            setJetonP2(String.valueOf(game.getBoard().getTakenSquare(game.getOpponent()).size()));
        }
    }

    public void setScoreP1(String scoreP1) {
        this.scoreP1.setText(scoreP1);
    }

    public void setScoreP2(String scoreP2) {
        this.scoreP2.setText(scoreP2);
    }

    public void setJetonP1(String jetonP1) {
        this.jetonP1.setText(jetonP1);
    }

    public void setJetonP2(String jetonP2) {
        this.jetonP2.setText(jetonP2);
    }

}
