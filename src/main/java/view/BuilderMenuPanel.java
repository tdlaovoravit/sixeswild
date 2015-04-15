package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuilderMenuPanel {
    private JPanel builderMenuPanel;
    private JLabel bannerImage;
    private JPanel menuButtonPanel;
    private JButton editLevelButton;
    private JButton instructionsButton;
    private JButton newLevelButton;
    private JButton creditsButton;
    private JPanel leftButtons;
    private JPanel rightButtons;

    public BuilderMenuPanel()
    {

        creditsButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                JPanel parent = (JPanel) builderMenuPanel.getParent();
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "splash");
                JFrame topLevelFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, builderMenuPanel);
                topLevelFrame.setSize(new Dimension(500, 600));
                topLevelFrame.setLocationRelativeTo(null);
            }
        });
        editLevelButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                JPanel parent = (JPanel) builderMenuPanel.getParent();
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "levelSelect");
                JFrame topLevelFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, builderMenuPanel);
                topLevelFrame.setLocationRelativeTo(null);
            }
        });
        newLevelButton.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                JPanel parent = (JPanel) builderMenuPanel.getParent();
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "editPanel");
                JFrame topLevelFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, builderMenuPanel);
                topLevelFrame.setSize(new Dimension(880, 600));
                topLevelFrame.setLocationRelativeTo(null);
            }
        });
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
        builderMenuPanel = new JPanel();
        builderMenuPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        builderMenuPanel.setMaximumSize(new Dimension(500, 195));
        builderMenuPanel.setMinimumSize(new Dimension(500, 195));
        builderMenuPanel.setPreferredSize(new Dimension(500, 195));
        bannerImage = new JLabel();
        bannerImage.setAutoscrolls(true);
        bannerImage.setHorizontalAlignment(0);
        bannerImage.setHorizontalTextPosition(0);
        bannerImage.setIcon(new ImageIcon(getClass().getResource("/view/banner.png")));
        bannerImage.setText("");
        builderMenuPanel.add(bannerImage, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(500, 150), new Dimension(500, 150), new Dimension(500, 150), 0, false));
        menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 20, 0, 20), -1, -1));
        builderMenuPanel.add(menuButtonPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, -1), new Dimension(500, -1), new Dimension(500, -1), 0, false));
        leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        menuButtonPanel.add(leftButtons, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        newLevelButton = new JButton();
        newLevelButton.setText("New Level");
        leftButtons.add(newLevelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 100), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
        creditsButton = new JButton();
        creditsButton.setText("Credits");
        leftButtons.add(creditsButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 100), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
        rightButtons = new JPanel();
        rightButtons.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        menuButtonPanel.add(rightButtons, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        editLevelButton = new JButton();
        editLevelButton.setText("Edit Level");
        rightButtons.add(editLevelButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 100), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
        instructionsButton = new JButton();
        instructionsButton.setText("Instructions");
        rightButtons.add(instructionsButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 100), new Dimension(-1, 100), new Dimension(-1, 100), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return builderMenuPanel;
    }
}
