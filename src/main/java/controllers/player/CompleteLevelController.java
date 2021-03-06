package controllers.player;

import controllers.IActionListener;
import model.*;
import view.PlayerApplication;
import view.WinLevelPanel;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *  CompleteLevelController Class.
 *  Manages the completion stage of a level.
 *
 *  @author Arthur Lockman, Yiğit Uyan, bckawuh, jamarciano
 */
public class CompleteLevelController implements IActionListener
{
    PlayerApplication app;
    EarnBadgeController badgeController;
    boolean completed = false;

    /**
     * CompleteLevelController Class.
     *
     * @param app: the Player application
     */
    public CompleteLevelController(PlayerApplication app)
    {
        this.app = app;
        badgeController = app.getBadgeController();
    }

    /**
     *  Recognizes that the level has been completed, displays level
     *  statistics.
     */
    @Override
    public void actionPerformed()
    {
        if (!completed) {
            completed = true;
            System.out.println("PlayerPanel: " + app.getPlayerPlayPanel().getBoard().getCurrentLevel());
            //System.out.println("Game: " + app.getGame().getBoard().getCurrentLevel());

            // This is how we fire the doAction method in BadgeController,
            // If we figure out how to generate Actions, we can also do it that way...
            app.getGame().setBoard(app.getPlayerPlayPanel().getBoard());
            badgeController.doAction();

            Level l = app.getPlayerPlayPanel().getBoard().getCurrentLevel();
            Board b = app.getPlayerPlayPanel().getBoardViewPanel().getBoard();

            boolean didWin = (b.getScore() >= 1);
            boolean isNewHighScore = (b.getScore() > app.getPlayerPlayPanel().getBoard().getCurrentLevel().getHighScore());
            try {
                app.getGame().getLevels().get(app.getGame().getLevels().indexOf(l) + 1).setLocked(!didWin);
            } catch (java.lang.IndexOutOfBoundsException e) {
                System.out.println("All levels unlocked.");
            }
            app.getPlayerPlayPanel().getBoard().getCurrentLevel().setHighScore(b.getScore());


            if (didWin) {
                // play victory sound
                // sound from free version of http://www.sonniss.com/sound-effects/laughs-sound-effects/, by Sound Ex Machina
                app.playSound(2);
            } else {
                // play failure sound
                // sound from The Price Is Right
                app.playSound(3);
            }

            int stars = 0;
            if (b.getScore() >= l.getThreeStarScore())
                stars = 3;
            else if (b.getScore() >= l.getTwoStarScore())
                stars = 2;
            else if (b.getScore() >= 1)
                stars = 1;
            app.getPlayerPlayPanel().getBoardViewPanel().refresh();
            WinLevelPanel wl = new WinLevelPanel("Level " + app.getGame().getLevels().indexOf(l),
                    l.getHighScore(), b.getScore(), stars, didWin, isNewHighScore);
            wl.setVisible(true);
            int response = wl.getResponse();
            if (response == 0) {
                ((CardLayout) app.getPlayerApplication().getLayout()).show(app.getPlayerApplication(), "levelSelect");
                app.setSize(new Dimension(500, 500));
                app.setLocationRelativeTo(null);
            } else if (response == 1) {
                if (didWin) {
                    app.getPlayerLevelSelectPanel().getLevelList().setSelectedIndex(app.getGame().getLevels().indexOf(l) + 1);
                } else {
                    app.getPlayerLevelSelectPanel().getLevelList().setSelectedIndex(app.getGame().getLevels().indexOf(l));
                }
                for (MouseListener listener : app.getPlayerLevelSelectPanel().getPlayButton().getMouseListeners()) {
                    if (listener instanceof StartGameController) {
                        ((StartGameController) listener).doAction();
                    }
                }
            }
        }
    }
}
