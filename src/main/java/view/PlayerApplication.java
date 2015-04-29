package view;

import controllers.player.StartGameController;
import controllers.player.EarnBadgeController;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * PlayerApplication Class.
 * Manages the contents and behavior of the PlayerApplication.
 *
 * @author ...
 */
public class PlayerApplication extends JFrame
{
    private JPanel playerApplication;
    private SplashScreen splashScreen;
    private PlayerMenuPanel menuPanel;
    private PlayerLevelSelectPanel playerLevelSelectPanel;
    private PlayerPlayPanel playerPlayPanel;
    private BadgesViewPanel badgesViewPanel;
    private InstructionViewPanel instructionViewPanel;
    private Game game;

    // Since this is a standalone Controller, the app has a special reference.
    private EarnBadgeController badgeController;

    /**
     * PlayerApplication Constructor.
     */
    public PlayerApplication()
    {
        super("Sixes Wild");
        setContentPane(playerApplication);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ((CardLayout) playerApplication.getLayout()).show(playerApplication, "splash");
        this.setResizable(false);
        setSize(new Dimension(500, 600));
        this.setLocationRelativeTo(null);
        this.setTitle("Sixes Wild");
        this.game = new Game();
        badgeController = new EarnBadgeController(this);
//        this.playerPlayPanel.setBoard(this.game.getBoard());

        /** Adds a window listener */
        this.addWindowListener(new WindowListener()
        {
            //Add controllers once window is initialized.
            @Override
            public void windowOpened(WindowEvent e) {
                playerLevelSelectPanel.updateLevelList(game);
                playerLevelSelectPanel.getPlayButton().addMouseListener(new
                        StartGameController((PlayerApplication) e.getWindow()));
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        setVisible(true);

    }

    public EarnBadgeController getBadgeController() {
        return badgeController;
    }

    public JPanel getPlayerApplication()
    {
        return playerApplication;
    }

    public SplashScreen getSplashScreen()
    {
        return splashScreen;
    }

    public PlayerMenuPanel getMenuPanel()
    {
        return menuPanel;
    }

    public PlayerLevelSelectPanel getPlayerLevelSelectPanel()
    {
        return playerLevelSelectPanel;
    }

    public PlayerPlayPanel getPlayerPlayPanel()
    {
        return playerPlayPanel;
    }

    public Game getGame()
    {
        return game;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        playerApplication = new JPanel();
        playerApplication.setLayout(new CardLayout(0, 0));
        splashScreen = new SplashScreen();
        playerApplication.add(splashScreen.$$$getRootComponent$$$(), "splash");
        menuPanel = new PlayerMenuPanel();
        playerApplication.add(menuPanel.$$$getRootComponent$$$(), "menuPanel");
        playerLevelSelectPanel = new PlayerLevelSelectPanel();
        playerApplication.add(playerLevelSelectPanel.$$$getRootComponent$$$(), "levelSelect");
        playerPlayPanel = new PlayerPlayPanel();
        playerApplication.add(playerPlayPanel.$$$getRootComponent$$$(), "playPanel");
        badgesViewPanel = new BadgesViewPanel();
        playerApplication.add(badgesViewPanel.$$$getRootComponent$$$(), "achievement");
        instructionViewPanel = new InstructionViewPanel();
        playerApplication.add(instructionViewPanel.$$$getRootComponent$$$(), "instructions");
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return playerApplication;
    }
}
