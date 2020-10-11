package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.MoveInformation;
import esi.atl.g52088.othello.model.Position;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class will inherit of tableView class that means it's will be a 
 * tableView too.
 * This custo tableView will display all the informations of the game.
 * <ul>
 * <li> id of the current move </li>
 * <li> name of the current player </li>
 * <li> current action </li>
 * <li> position where the piece was putted </li>
 * <li> how many ennemy pieces the current move has flipped </li>
 * </ul>
 * @author g52088 - Guldentops Thomas
 */
public class GameInformation extends TableView {

    GameInformation() {

        // --- columns creation
        TableColumn<MoveInformation, Integer> idCol = createIdCol();
        TableColumn<MoveInformation, String> name = createNameCol();
        TableColumn<MoveInformation, String> actionCol = createActionCol();
        TableColumn<MoveInformation, Position> positionCol = createPositionCol();
        TableColumn<MoveInformation, Integer> priseCol = createPriseCol();
        
        // --- eliminate the last column
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        getColumns().addAll(idCol, name, actionCol, positionCol, priseCol);

    }

    private TableColumn<MoveInformation, Integer> createIdCol() {
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory("id"));

        return id;

    }

    private TableColumn<MoveInformation, String> createNameCol() {
        TableColumn name = new TableColumn("Nom");
        name.setCellValueFactory(new PropertyValueFactory<>("player"));

        return name;
    }

    private TableColumn<MoveInformation, String> createActionCol() {
        TableColumn action = new TableColumn("Action");
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        return action;
    }

    private TableColumn<MoveInformation, Position> createPositionCol() {
        TableColumn position = new TableColumn("Position");
        position.setCellValueFactory(new PropertyValueFactory<>("pos"));

        return position;
    }

    private TableColumn<MoveInformation, Integer> createPriseCol() {
        TableColumn flipped = new TableColumn("Prises");
        flipped.setCellValueFactory(new PropertyValueFactory<>("flipped"));

        return flipped;
    }

    void update(int index){
        scrollTo(index);
    }
}
