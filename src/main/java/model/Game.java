package model;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Game Class.
 * Manages the contents and behavior of Sixes Wild Games.
 *
 * @authors ..., Bryce Kaw-uh
 */
public class Game
{
    protected Board board;
    protected ArrayList<Badge> badges;
    protected ArrayList<Level> levels;
    protected int [] userBadges;

    /**
     * Game Constructor.
     * Loads the Game from disk.
     */
    public Game()
    {
        reloadFromDisk();
        createBadges();
        checkBadges();
        readBadges();
        checkBadges();
        saveBadges();
        checkBadges();
        readBadges();
        checkBadges();
    }

    /** Reloads the Game from its location on disk. */
    public void reloadFromDisk()
    {
        int i = 1;

        levels = new ArrayList<Level>();

        ClassLoader classLoader = getClass().getClassLoader();
        File folder = null;
        try
        {
            folder = new File(classLoader.getResource("levels").toURI().getPath());
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        File[] listOfFiles = folder.listFiles();

        for(File file : listOfFiles)
        {
            String content = null;
            try {
                Scanner scanner = new Scanner(file);
                content = scanner.useDelimiter("\\Z").next();
                scanner.close();
                this.initializeLevel(i, content, file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e)
            {
                System.out.println("Empty level file.");
            }
            i++;
        }
    }

    /**
     * Replaces a Level.
     * @param l:  Level to replace.
     * @param index: ?
     */
    public boolean replaceLevel(Level l, int index)
    {
        if (index - 1 > levels.size())
            return false;
        levels.set(index, l);
        return true;
    }

    /**
     * Initializes the Level with the given information.
     * @param levelNumber:  what number the level will be
     * @param levelData:  a String representation of the level data
     * @param diskLocation:  where on disk the Level will be stored
     */
    public void initializeLevel(int levelNumber, String levelData, File diskLocation)
    {
        String lData = levelData;
        System.out.println("DATA FOR LEVEL: " + levelNumber + ": " + levelData);
        String delims = " ";
        String[] levData = lData.split(delims);

        String name = levData[0];
        String type = levData[1];
        int twoStarScore = Integer.parseInt(levData[4]);
        int threeStarScore = Integer.parseInt(levData[5]);

        int highScore = Integer.parseInt(levData[16]);
        int num = levelNumber;
        boolean lock = true;
        if(Integer.parseInt(levData[17]) == 1){
            lock = false;
        }
        boolean specialAllowed = false;
        if(Integer.parseInt(levData[6]) == 1)
        {
            specialAllowed = true;
        }

        if(type.equals("Puzzle")){
            int mLim = Integer.parseInt(levData[3]);
            levels.add(new PuzzleLevel(name, num, highScore, twoStarScore, threeStarScore,
                    levelData, lock, mLim, specialAllowed, diskLocation));
        } else if(type.equals("Lightning")){
            int tLim = Integer.parseInt(levData[2]);
            levels.add(new LightningLevel(name, num, highScore, twoStarScore, threeStarScore,
                    levelData, lock, tLim, specialAllowed, diskLocation));
        } else if(type.equals("Release")){
            levels.add(new ReleaseLevel(name, num, highScore, twoStarScore, threeStarScore,
                    levelData, lock, specialAllowed, diskLocation));
        } else if(type.equals("Elimination")){
            levels.add(new EliminationLevel(name, num, highScore, twoStarScore, threeStarScore,
                    levelData, lock, specialAllowed, diskLocation));
        } else{
            System.out.println("ERROR: invalid level data");
        }
    }

    /**
     * Returns a list of the Levels.
     */
    public ArrayList<Level> getLevels()
    {
        return levels;
    }

    /**
     * Returns the board.
     */
    public Board getBoard()
    {
        return board;
    }

    public ArrayList<Badge> getBadges()
    {
        return badges;
    }

    public void createBadges()
    {
        badges = new ArrayList<Badge>();
        createScoreBadges();
    }

    public void createScoreBadges()
    {
        for(int i = 0; i < levels.size(); i++)
        {
            String newBadgeDescription = "Got " + levels.get(i).threeStarScore + " points in level " + i;
            Badge newBadge = new Badge("ScoreBadge", (levels.get(i).name + "-ScoreBadge"), newBadgeDescription);
            newBadge.setBadgeScoreRequirement(i, levels.get(i).threeStarScore);
            badges.add(newBadge);
        }
    }

    public void checkBadges()
    {
        System.out.println("");
        System.out.println("*** THE BADGE CHECK ***");
        for(int i = 0; i < badges.size(); i++)
        {
            Badge badge = badges.get(i);
            System.out.println("Badge: " + badge.name);
            System.out.println("- Type: " + badge.type);
            System.out.println("- Desc: " + badge.description);
            System.out.println("- Earn: " + badge.earned);
            System.out.println(" ");
        }
    }

    public void readBadges()
    {
        int i = 1;


        ClassLoader classLoader = getClass().getClassLoader();
        File folder = null;
        try
        {
            folder = new File(classLoader.getResource("user").toURI().getPath());
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        File[] listOfFiles = folder.listFiles();

        for(File file : listOfFiles)
        {
            String content = null;
            try {
                Scanner scanner = new Scanner(file);
                content = scanner.useDelimiter("\\Z").next();
                scanner.close();
                this.loadBadges(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e)
            {
                System.out.println("Empty user file.");
            }
            i++;
        }
    }

    public void loadBadges(String data)
    {
        String bData = data;
        System.out.println("DATA FOR BADGES: " + bData);
        String delims = " ";
        String[] badgeData = bData.split(delims);

        int i = 0;
        for(String s : badgeData)
        {
            if(Integer.parseInt(s) == 1)
            {
                badges.get(i).earned = true;
            }
            else
            {
                badges.get(i).earned = false;
            }
            i++;
        }
        i = 0;
    }

    public void saveBadges()
    {
        String data = new String();

        int i = 0;
        for(Badge b : badges)
        {
            if(b.earned == true)
            {
                data = data + "1";
            }
            else
            {
                data = data + "0";
            }

            i++;

            if(badges.size() != i)
            {
                data = data + " ";
            }
        }
        i = 0;

        ClassLoader classLoader = getClass().getClassLoader();
        File folder = null;
        try
        {
            folder = new File(classLoader.getResource("user").toURI().getPath());
        } catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        File[] listOfFiles = folder.listFiles();

            System.out.println("~ Destination: " + folder.getPath() + "/badges");
            try
            {
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(folder.getPath() + "/badges"), "utf-8"));
                System.out.println("~ Writing: " + "1 1");
                writer.write("1 1");
                writer.close();
            }
            catch (IOException ex)
            {
                // report
            }
            System.out.println("~ Complete. ");
    }
}