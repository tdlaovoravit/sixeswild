package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SplashScreen Class.
 * Manages the contents and behavior of Sixes Wild Splash Screens.
 *
 * @author ..., Bryce Kaw-uh
 */
public class SplashScreen {
    private JPanel splashScreen;
    private JLabel logoView;
    private JLabel creditsView;
    protected JButton playButton;

    /**
     * SplashScreen Constructor.
     */
    public SplashScreen() {
        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                JPanel parent = (JPanel) splashScreen.getParent();
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "menuPanel");
                JFrame topLevelFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, splashScreen);
                topLevelFrame.setSize(new Dimension(500, 500));
                topLevelFrame.setLocationRelativeTo(null);
            }
        });
        playButton.setName("Play Game");
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
        splashScreen = new JPanel();
        splashScreen.setLayout(new BorderLayout(0, 0));
        splashScreen.setMaximumSize(new Dimension(500, 660));
        logoView = new JLabel();
        logoView.setAlignmentY(0.0f);
        logoView.setHorizontalAlignment(0);
        logoView.setHorizontalTextPosition(0);
        logoView.setIcon(new ImageIcon(getClass().getResource("/view/splash.png")));
        logoView.setInheritsPopupMenu(false);
        logoView.setMaximumSize(new Dimension(500, 300));
        logoView.setMinimumSize(new Dimension(500, 300));
        logoView.setOpaque(false);
        logoView.setPreferredSize(new Dimension(500, 300));
        logoView.setText("");
        logoView.setVerticalAlignment(1);
        logoView.setVerticalTextPosition(1);
        splashScreen.add(logoView, BorderLayout.CENTER);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setMaximumSize(new Dimension(500, 275));
        panel1.setMinimumSize(new Dimension(500, 275));
        panel1.setPreferredSize(new Dimension(500, 275));
        splashScreen.add(panel1, BorderLayout.SOUTH);
        creditsView = new JLabel();
        creditsView.setHorizontalAlignment(0);
        creditsView.setHorizontalTextPosition(0);
        creditsView.setMaximumSize(new Dimension(500, 160));
        creditsView.setMinimumSize(new Dimension(50, 160));
        creditsView.setText("<html><center><h1>Sixes Wild</h1>\n<br>\nConcept by George Heineman\n<br><br>\n<strong>Implementation Team</strong><br>\nArthur Lockman<br>\nBryce Kaw-uh<br>\nYigit Uyan<br>\nJesse Marciano<br>\nBrendan Casey<br>\nDavid Laovoravit<br><center>\n</html>");
        creditsView.setVerticalAlignment(0);
        panel1.add(creditsView, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 25, 10, 25), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playButton = new JButton();
        playButton.setText("Play Game");
        panel2.add(playButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return splashScreen;
    }
}
