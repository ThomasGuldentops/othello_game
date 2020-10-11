package esi.atl.g52088.othello.view.console;

import esi.atl.g52088.othello.model.Board;
import esi.atl.g52088.othello.model.Player;
import esi.atl.g52088.othello.model.PlayerColor;
import esi.atl.g52088.othello.model.Position;
import java.util.Scanner;

/**
 *
 * @author g52088 - Guldentops Thomas
 */
public class View {

    private Scanner in;

    static final String RED = ConsoleColor.RED;
    static final String CYAN = ConsoleColor.CYAN;
    static final String RESET = ConsoleColor.RESET;
    static final String YELLOW = ConsoleColor.YELLOW;
    static final String GREEN = ConsoleColor.GREEN;
    static final String PURPLE = ConsoleColor.PURPLE;
    static final String BLACK = ConsoleColor.BLACK;
    static final String WHITE = ConsoleColor.WHITE;

    /**
     * Constructor of view class.
     */
    public View() {
        in = new Scanner(System.in);
    }

    /**
     * Initialize the view.
     */
    public void initialize() {
        System.out.println("Jeu du Othello ! ");
    }

    /**
     * Allow to write a message if the command used is "quit"
     */
    public void quit() {
        System.out.println("Bye bye.");
    }

    /**
     * Allow to display an error on the console.
     *
     * @param msg String with the error message
     */
    public void displayError(String msg) {
        System.out.println(RED + "Message d'erreur : " + msg + RESET);
    }

    /**
     * Allow to display help (command user can use)
     */
    public void displayHelp() {
        System.out.println("Commandes : ");
        System.out.println("\t - " + YELLOW + "play" + RESET
                + "(" + GREEN + "i" + RESET + "," + GREEN + "j" + RESET + ")");
        System.out.println("\t - " + YELLOW + "show" + RESET);
        System.out.println("\t - " + YELLOW + "quit" + RESET);

        System.out.println();
    }

    /**
     * Allow to ask a command from the user
     *
     * @return String command the user typed
     */
    public String askCommand() {
        System.out.println("Entrez votre commande : ");
        String msg = in.nextLine();
        return msg;
    }

    /**
     * Display the current player
     *
     * @param player the current player
     */
    public void displayCurrentPlayer(Player player) {
        String color;

        if (player.getColor() == PlayerColor.BLACK) {
            color = "NOIR";
        } else {
            color = "BLANC";
        }

        System.out.println("C'est à votre tour joueur " + color
                + PURPLE + "\n\tScore actuel : "
                + this.displayScore(player) + RESET);
    }

    /**
     * Display game over and congratulation message to the winner(s)
     *
     * @param winner winner
     */
    public void displayOver(Player winner) {
        System.out.println();
        System.out.print("Félicitations au gagnant!");
        System.out.println(winner.getColor());
        System.out.println("La partie est terminée.");
    }

    /**
     * Display the current score of the given player
     *
     * @param player player which be evaluated
     * @return the score of this player
     */
    public String displayScore(Player player) {
        return "" + player.getScore();
    }

    /**
     * To display the board.
     *
     * @param board
     *
     * Allow to hide the enemy piece.
     */
    public void displayBoard(Board board) {
        Position p;

        System.out.print("     ");
        for (int i = 0; i < board.getColumns(); i++) {
            System.out.print("|   "+ i + "   |  ");
        }
        System.out.println("\n =============================================="
                + "============================================");

        for (int i = 0; i < board.getRows(); i++) {
            System.out.print(i + " ||");

              for (int j = 0; j < board.getColumns(); j++) {
                p = new Position(i, j);
                
                String displayPiece = "_____";

                if (board.getPiece(p) != null) {
                    if (board.getPiece(p).getColor() == PlayerColor.BLACK) {
                        //Current Player's piece color
                        displayPiece = " N[" + board.getPiece(p).getValue() + "]";
                    } else {
                        //Opponent Player's piece color
                        displayPiece = " B[" + board.getPiece(p).getValue() + "]";
                    }
                }
                System.out.print(" | "
                        + displayPiece + ConsoleColor.RESET + " | ");
            }

            System.out.println();
        }

    }
}
