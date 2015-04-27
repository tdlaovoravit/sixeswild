package model.moves;

import model.Board;
import model.Square;
import model.Tile;

import java.util.ArrayList;

/**
 * A move that removes tiles from the board. This move is used
 * to remove tiles from a user selection.
 *
 * @author arthurlockman
 */
public class RemoveTileMove implements IMove
{
    /**
     * The squares that are involved in the move.
     */
    ArrayList<Square> squares;
    /**
     * The board that the squares exist in.
     */
    Board board;

    /**
     * Constructor for the remove tile move.
     * @param b The board that the move is attached to.
     */
    public RemoveTileMove(Board b)
    {
        this.squares = new ArrayList<Square>();
        this.board = b;
    }

    /**
     * Alternate constructor that takes a seed tile.
     * @param b The board that the move is attached to.
     * @param s The square that is the seed for the move.
     */
    public RemoveTileMove(Board b, Square s)
    {
        this(b);
        this.squares.add(s);
    }

    /**
     * Perform the remove tile move.
     * @return Boolean, whether or not the move is successful.
     */
    public boolean doMove()
    {
        if (!this.isValid()) return false;
        for (Square s : squares)
        {
            s.removeTile();
            s.flipCleared();
        }
        board.refill();
        return true;
    }

    /**
     * Check if the move is valid.
     * @return Boolean, if the move is a valid move.
     */
    public boolean isValid()
    {
        int sum = 0;
        for (Square s : squares)
        {
            sum += s.getTile().getValue();
        }
        return (sum == 6);
    }

    /**
     * Add a square to the move.
     * @param s The square to add.
     */
    public void addSquare(Square s)
    {
        squares.add(s);
    }
}
