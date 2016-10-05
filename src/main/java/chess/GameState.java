package chess;


import chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState implements Cloneable {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Piece[][] board = new Piece[8][8];

    /**
     * Create the game state.
     */
    public GameState() {
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     *
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     *
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return board[position.getRow()][position.getColumn()];
    }

    /**
     * Method to place a piece at a given position
     *
     * @param piece    The piece to place
     * @param position The position
     */
    void placePiece(Piece piece, Position position) {
        board[position.getRow()][position.getColumn()] = piece;
    }

    /**
     * Applies vector to position
     *
     * @param position
     * @param vector
     * @return position or null in case of out-of-bounds
     */
    public Position apply(Position position, Vector vector, Player player) {
        // could be implemented via constructor and exception catch, but this is bad style IMO
        int row = position.getRow() + player.getRowDirection() * vector.getdRow();
        int col = position.getColumn() + vector.getdCol();
        if (row < Position.MIN_ROW || row > Position.MAX_ROW || col < Position.MIN_COLUMN || col > Position.MAX_COLUMN) {
            return null;
        } else {
            return new Position(col, row);
        }
    }

    /**
     * Get all players move according to pieces rules
     *
     * @param player
     * @return
     */
    private List<Move> getPlayerMoves(Player player) {
        List<Move> moves = new ArrayList<Move>();
        for (int row = Position.MIN_ROW; row <= Position.MAX_COLUMN; row++) {
            for (int col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
                Position from = new Position(col, row);
                Piece piece = getPieceAt(from);
                if (piece != null && piece.getOwner() == player) {
                    for (Position to : piece.getValidMoves(from, this)) {
                        moves.add(new Move(from, to));
                    }
                }
            }
        }
        return moves;
    }

    /**
     * @param player
     * @return true if player can hit other king if it was his move
     */
    private boolean canHitOtherKing(Player player) {
        for (Move move : getPlayerMoves(player)) {
            Piece pieceAt = getPieceAt(move.getTo());
            if (pieceAt != null && pieceAt instanceof King && pieceAt.getOwner() != player) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns current player moves
     *
     * @return
     */
    public List<Move> getCurrentPlayerMoves() {
        List<Move> piecesMoves = getPlayerMoves(currentPlayer);
        List<Move> result = new ArrayList<Move>();
        for (Move move : piecesMoves) {
            // filter those moves after which we are under check
            GameState test = clone();
            test.move(move);
            if (test.canHitOtherKing(currentPlayer.getOther())) {
                continue;
            }
            result.add(move);
        }
        return result;
    }

    /**
     * Validates move and performs if valid
     *
     * @param move
     * @return true if valid
     */
    public boolean validateAndMove(Move move) {
        if (!getCurrentPlayerMoves().contains(move)) return false;
        move(move);
        return true;
    }

    /**
     * @return Whether it's checkmate for current player
     */
    public boolean isCheckMate() {
        return getCurrentPlayerMoves().size() == 0;
    }

    /**
     * Just move piece
     */
    private void move(Move move) {
        Piece piece = getPieceAt(move.getFrom());
        placePiece(piece, move.getTo());
        placePiece(null, move.getFrom());
        currentPlayer = currentPlayer.getOther();
    }

    /**
     * @return
     */
    @Override
    protected GameState clone() {
        GameState gameState = new GameState();
        gameState.currentPlayer = currentPlayer;
        gameState.board = new Piece[8][8];
        for (int row = Position.MIN_ROW; row <= Position.MAX_COLUMN; row++) {
            for (int col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
                gameState.board[row][col] = board[row][col];
            }
        }
        return gameState;
    }
}
