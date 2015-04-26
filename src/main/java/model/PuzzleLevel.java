package model;

import java.io.File;

public class PuzzleLevel extends Level
{
    int moveLimit;

    public PuzzleLevel(String name, int number,
                       int highScore, int twoStarScore, int threeStarScore, String levelData,
                       boolean locked, int mLim, boolean specialAllowed, File diskLocation)
    {
        super(name, number, highScore, twoStarScore, threeStarScore, levelData, locked, specialAllowed, diskLocation);
        moveLimit = mLim;
    }

    /**
     * A constructor used to cast the superclass Level
     * to this type of level.
     * @param l The level to cast.
     */
    public PuzzleLevel(Level l)
    {
        super(l);
    }
    @Override
    public String getType()
    {
        return "Puzzle";
    }

    public int getMoveLimit()
    {
        return moveLimit;
    }

    public void setMoveLimit(int ml)
    {
        moveLimit = ml;
    }

    @Override
    public String getLevelMetadata()
    {
        String dat = "";
        dat += name + " ";
        dat += this.getType() + " ";
        dat += "1 ";
        dat += this.getMoveLimit() + " ";
        dat += this.twoStarScore + " ";
        dat += this.threeStarScore + " ";
        if (this.specialMovesAllowed) dat += "1 ";
        else dat += "0 ";
        return dat;
    }
}
