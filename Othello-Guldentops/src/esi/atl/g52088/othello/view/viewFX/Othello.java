package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Game;
import esi.atl.g52088.othello.model.Model;
import esi.atl.g52088.othello.model.MoveInformation;
import esi.atl.g52088.othello.model.PlayerColor;
import esi.atl.g52088.othello.util.Observer;
import java.io.File;
import java.util.Objects;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class Othello extends Application implements Observer {

    private HBox root;
    private Model game;
    private GameBoard gameBoard;
    private OthelloButtons othelloButtons;
    private Indicators indicators;
    private PlayerInformations playerInformations;
    private GameInformation gameInformations;
    private ObservableList<MoveInformation> data;
    private Label displayWinner;
    private SecondWindow SecondWindow;

    /**
     * To create the visual aspect of the application, that means, boxes,
     * classe, ...
     *
     * @param primaryStage the primary window
     * @throws java.lang.Exception throw exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Objects.requireNonNull(primaryStage, "Given stage cannot be null.");
        game = new Game();
        game.addObserver(this);

        // --- main window
        root = new HBox(25);

        // --- player form
        PlayerName playersNames = new PlayerName();

        root.getChildren().add(playersNames);

        Button begin = new Button("Commencer");

        begin.setPrefWidth(300);
        PlayerName.setColumnSpan(begin, 2);

        playersNames.add(begin, 1, 3);

        begin.setOnAction(e -> initializePlayer(root, playersNames));

        primaryStage.setTitle("Othello");
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(750);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        File f = new File("style/style.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add(
                "file:///" + f.getAbsolutePath().replace("\\", "/")
        );

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * To update the window when something change (update gameBoard, score, ...)
     *
     */
    @Override
    public void update() {
        if (game.isOver()) {
            displayWinner.setText("Le vainqueur est "
                    + game.getWinner().getName());
            gameBoard.setDisable(true);
        } else {
            displayWinner.setText("");
            gameBoard.setDisable(false);
        }

        gameBoard.drawBoard(game, game.getBoard());

        indicators.updateProgressBar();
        indicators.updateProgressIndicator();

        playerInformations.updateBlackScore();
        playerInformations.updateWhiteScore();
        playerInformations.updateBackground(game.getCurrent().getColor());

        data.add(game.getCurrentMove());

        gameInformations.update(MoveInformation.id);

        if (SecondWindow != null) {
            SecondWindow.update();
        }
    }

    void initializePlayer(HBox playerBox, PlayerName playerName) {
        Objects.requireNonNull(playerBox, "Given HBox cannot be null.");
        Objects.requireNonNull(playerName, "Given playerName cannot be null.");

        game.getCurrent().setName(playerName.getCurrentName());
        game.getOpponent().setName(playerName.getOpponentName());

        if (playerName.getChooseBot().isSelected()) {
            game.getOpponent().setName("Bot");
            game.getOpponent().setBot(true);
        }

        startGame(playerBox);

    }

    private void startGame(HBox playerBox) {
        Objects.requireNonNull(playerBox, "Given HBox cannot be null.");
        playerBox.getChildren().clear();

        // --- Menu
        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("Option");
        MenuItem add = new MenuItem("Second Window");

        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("test");
                SecondWindow = new SecondWindow(game);

            }
        });

        menuFile.getItems().addAll(add);
        menuBar.getMenus().addAll(menuFile);
        root.getChildren().add(menuBar);

        // --- initialize the game
        game.initialize();
        game.start();

        data = FXCollections.observableArrayList();
        // --- differents part of the left side
        VBox leftSide = new VBox();

        gameBoard = new GameBoard(game);
        othelloButtons = new OthelloButtons(game, gameBoard);
        indicators = new Indicators(game);

        leftSide.getChildren()
                .addAll(gameBoard, indicators, othelloButtons);

        // --- differents part of the right side
        VBox rightSide = new VBox();
        playerInformations = new PlayerInformations(game);
        gameInformations = new GameInformation();

        gameInformations.setItems(data);

        displayWinner = new Label("");
        displayWinner.getStyleClass().add("displayWinner");
        rightSide.getChildren()
                .addAll(playerInformations, gameInformations, displayWinner);

        root.getChildren()
                .addAll(leftSide, rightSide);

        update();

    }
}
