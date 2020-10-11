package esi.atl.g52088.othello.view.viewFX;

import esi.atl.g52088.othello.model.Board;
import esi.atl.g52088.othello.model.Model;
import esi.atl.g52088.othello.model.Position;
import java.util.Objects;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public final class GameBoard extends GridPane {

    GameBoard(Model game) {
        Objects.requireNonNull(game, "The given game can't be null.");

        drawBoard(game, game.getBoard());
    }

    /**
     * To draw the green boxes. Each box can be cliquable or contains a piece
     */
    void drawBoard(Model game, Board board) {
        Objects.requireNonNull(game, "The given game can't be null.");
        Objects.requireNonNull(board, "The given board can't be null.");

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {

                Position position = new Position(i, j);

                TileBox box = new TileBox(position, game);

                this.add(box, i, j);

                if (game.getBoard().getPiece(position) != null) {
                    this.drawPieces(board, position);
                } else {

                    this.canPut(box, game, position);

                }
            }

        }
    }

    /**
     * To know if the current player can put a piece on the given box at the
     * given position
     *
     * @param box the game tile where the mouse is
     * @param game the game
     * @param position the position where the mouse is
     */
    private void canPut(TileBox box, Model game, Position position) {
        Objects.requireNonNull(box, "The given box can't be null.");
        Objects.requireNonNull(game, "The given game can't be null.");
        Objects.requireNonNull(position, "The given position can't be null.");

        box.addEventFilter(MouseEvent.MOUSE_ENTERED,
                e -> {
                    if (!game.getPosition().contains(position)) {
                        box.setFill(Color.RED);
                    } else {
                        box.setFill(Color.LIGHTGREEN);
                        if (!game.isGameOver()) {
                            box.setOnMouseClicked(f -> putPiece(game,
                            position));
                        }
                    }

                });

        box.addEventFilter(MouseEvent.MOUSE_EXITED,
                e -> {
                    box.setFill(box.getFillColor());
                });
    }

    /**
     * To put the piece
     *
     * @param game game
     * @param position the position where the current player will play
     */
    private void putPiece(Model game, Position position) {
        Objects.requireNonNull(position, "The given position can't be null.");
        Objects.requireNonNull(game, "The given game can't be null.");

        game.play(position.getRow(), position.getColumn());

    }

    /**
     * To draw the pieces.
     */
    void drawPieces(Board board, Position position) {
        Objects.requireNonNull(board, "The given board can't be null.");
        Objects.requireNonNull(position, "The given position can't be null.");

        PieceView piece = new PieceView(board.getPiece(position));
        this.add(piece, position.getRow(), position.getColumn());

        TextValue val = new TextValue(
                Integer.toString(board.getPiece(position).getValue()),
                board.getPiece(position).getColor());

        this.add(val, position.getRow(), position.getColumn());
    }

    /**
     * To know how many pieces are putted on the board.
     *
     * @param board the board game
     * @return the number of non null piece
     */
    double getNumberPiecesInGame(Board board) {
        Objects.requireNonNull(board, "The given board can't be null.");

        double tot = 0;

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {

                Position p = new Position(i, j);

                if (board.getPiece(p) != null) {
                    tot++;
                }
            }
        }
        return tot;
    }

}
