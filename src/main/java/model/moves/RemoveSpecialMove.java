package model.moves;

import model.Board;
import model.Square;

/**
 * A special move that removes a tile from the board.
 *
 * @author Arthur Lockman, bckawuh
 */
public class RemoveSpecialMove implements IMove
{
    /** The associated square. */
    protected Square square;
    /** The associated board. */
    protected Board board;

    /**
     * Move constructor. Removes the tile from the square
     * and refills the board accordingly.
     * @param s The square.
     * @param b The board.
     */
    public RemoveSpecialMove(Square s, Board b)
    {
        this.square = s;
        this.board = b;
    }

    /**
     * Create an empty move.
     */
    public RemoveSpecialMove()
    {

    }

    /**
     * Complete the move.
     * @return True if the move was successful, false otherwise
     */
    @Override
    public boolean doMove()
    {
        if (!this.isValid()) return false;
        square.removeTile();
        square.flipCleared();
        board.refill();
        return true;
    }

    /**
     * Determine if the move is valid.
     * @return True if the move is valid, false otherwise
     */
    @Override
    public boolean isValid()
    {
        return square.isActive() && square.getTile() != null;
    }

    /**
     * Get the move score.
     * @return The score.
     */
    @Override
    public int getScore()
    {
        return 0;
    }
}
