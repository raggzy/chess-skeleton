package chess;

import chess.pieces.Piece;

import java.io.*;

/**
 * This class provides the basic CLI interface to the Chess game.
 */
public class CLI {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final BufferedReader inReader;
    private final PrintStream outStream;

    private GameState gameState = null;

    public CLI(InputStream inputStream, PrintStream outStream) {
        this.inReader = new BufferedReader(new InputStreamReader(inputStream));
        this.outStream = outStream;
        writeOutput("Welcome to Chess!");
    }

    /**
     * Write the string to the output
     *
     * @param str The string to write
     */
    private void writeOutput(String str) {
        this.outStream.println(str);
    }

    /**
     * Retrieve a string from the console, returning after the user hits the 'Return' key.
     *
     * @return The input from the user, or an empty-length string if they did not type anything.
     */
    private String getInput() {
        try {
            this.outStream.print("> ");
            return inReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from input: ", e);
        }
    }

    void startEventLoop() {
        writeOutput("Type 'help' for a list of commands.");
        doNewGame();

        while (true) {
            showBoard();
            if (gameState.isCheckMate()) {
                writeOutput("Checkmate. Congrats to " + gameState.getCurrentPlayer().getOther());
            } else {
                writeOutput(gameState.getCurrentPlayer() + "'s Move");
            }

            String input = getInput();
            if (input == null) {
                break; // No more input possible; this is the only way to exit the event loop
            } else if (input.length() > 0) {
                if (input.equals("help")) {
                    showCommands();
                } else if (input.equals("new")) {
                    doNewGame();
                } else if (input.equals("quit")) {
                    writeOutput("Goodbye!");
                    System.exit(0);
                } else if (input.equals("board")) {
                    writeOutput("Current Game:");
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("move")) {
                    String[] split = input.split(" ");
                    if (split.length == 3) {
                        try {
                            Position from = new Position(split[1]);
                            Position to = new Position(split[2]);
                            move(new Move(from, to));
                        } catch (Exception e) {
                            writeOutput("Invalid move command. See help");
                        }
                    } else {
                        writeOutput("Invalid move command. See help");
                    }
                } else {
                    writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
                }
            }
        }
    }

    private void list() {
        writeOutput(gameState.getCurrentPlayer() + "'s possible moves:");
        for (Move move : gameState.getCurrentPlayerMoves()) {
            writeOutput(move.toString());
        }
    }

    private void move(Move move) {
        if (!gameState.validateAndMove(move)) {
            writeOutput("You entered not valid move of yours!");
        }
    }

    private void doNewGame() {
        gameState = new GameState();
        gameState.reset();
    }

    private void showBoard() {
        writeOutput(getBoardAsString());
    }

    private void showCommands() {
        writeOutput("Possible commands: ");
        writeOutput("    'help'                       Show this menu");
        writeOutput("    'quit'                       Quit Chess");
        writeOutput("    'new'                        Create a new game");
        writeOutput("    'board'                      Show the chess board");
        writeOutput("    'list'                       List all possible moves");
        writeOutput("    'move <colrow> <colrow>'     Make a move");
    }

    /**
     * Display the board for the user(s)
     */
    private String getBoardAsString() {
        StringBuilder builder = new StringBuilder();
        builder.append(NEWLINE);

        printColumnLabels(builder);
        for (int i = Position.MAX_ROW; i >= Position.MIN_ROW; i--) {
            printSeparator(builder);
            printSquares(i, builder);
        }

        printSeparator(builder);
        printColumnLabels(builder);

        return builder.toString();
    }


    private void printSquares(int row, StringBuilder builder) {
        builder.append(Position.getRowLabel(row));

        for (int col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            Piece piece = gameState.getPieceAt(new Position(col, row));
            char pieceChar = piece == null ? ' ' : piece.getIdentifier();
            builder.append(" | ").append(pieceChar);
        }
        builder.append(" | ").append(Position.getRowLabel(row)).append(NEWLINE);
    }

    private void printSeparator(StringBuilder builder) {
        builder.append("  +---+---+---+---+---+---+---+---+").append(NEWLINE);
    }

    private void printColumnLabels(StringBuilder builder) {
        builder.append("   ");
        for (int col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            builder.append(" ").append(Position.getColumnLabel(col)).append("  ");
        }

        builder.append(NEWLINE);
    }

    public static void main(String[] args) {
        CLI cli = new CLI(System.in, System.out);
        cli.startEventLoop();
    }
}
