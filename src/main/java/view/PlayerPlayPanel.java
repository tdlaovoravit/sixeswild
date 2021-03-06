package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import controllers.player.ReturnToLevelSelectController;
import model.Board;
import model.moves.RemoveSpecialMove;
import model.moves.ResetSpecialMove;
import model.moves.SwapSpecialMove;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * PlayerPlayPanel Class.
 * Manages the contents and behavior of the PlayerPlayPanel, which is
 * what the user sees as they are playing a level.
 *
 * @author Arthur Lockman, bckawuh, jamarciano
 */
public class PlayerPlayPanel {
    private JPanel menuBarPanel;
    private JButton quitButton;
    private JPanel playContentPanel;
    private JPanel playSidebarPanel;
    private JPanel boardPanel;
    private BoardViewPanel boardViewPanel;
    private JButton reset1Button;
    private JButton remove1Button;
    private JButton swap2Button;
    private JProgressBar scoreBar;
    private JLabel threeStarsLabel;
    private JLabel twoStarsLabel;
    private JLabel oneStarLabel;
    private JPanel playPanel;
    private JLabel scoreLabel;
    private JLabel titleLabel;
    private JLabel movesLabel;
    private JPanel timerPlaceholder;
    private TimerPanel timerViewPanel;
    private Board board;
    private AudioClip clip;

    /**
     * PlayerPlayPanel Constructor.
     */
    public PlayerPlayPanel() {
        clip = Applet.newAudioClip(getClass().getResource("/sounds/reset.wav"));

        /**
         * Adds a MouseListener
         */
        playContentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.println("Mouse released in panel.");
                if (boardViewPanel.getBoard().getCurrentMove() != null) {
                    boardViewPanel.getBoard().makeMove(boardViewPanel.getBoard().getCurrentMove());
                } else {
                    boardViewPanel.getBoard().deselectAll();
                    boardViewPanel.refresh();
                }
            }
        });
        remove1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                boardViewPanel.getBoard().setCurrentMove(new RemoveSpecialMove());
                if (!board.enoughSpecialRemaining() && (board.getCurrentMove() instanceof RemoveSpecialMove ||
                        board.getCurrentMove() instanceof SwapSpecialMove)) {
                    JOptionPane.showOptionDialog(null, "You're out of that kind of move. " +
                                    "Would you like to purchase more?", "Uh-oh!", JOptionPane.OK_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Buy ($0.99)"}, "default");
                    board.replenishSpecialMoves();
                }
                getReset1Button().setText("Reset (" + board.getNumResetMoves() + ")");
                getSwap2Button().setText("Swap (" + board.getNumSwapMoves() + ")");
                getRemove1Button().setText("Remove (" + board.getNumRemoveMoves() + ")");
            }
        });
        swap2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boardViewPanel.getBoard().setCurrentMove(new SwapSpecialMove(boardViewPanel.getBoard()));
                if (!board.enoughSpecialRemaining() && (board.getCurrentMove() instanceof RemoveSpecialMove ||
                        board.getCurrentMove() instanceof SwapSpecialMove)) {
                    JOptionPane.showOptionDialog(null, "You're out of that kind of move. " +
                                    "Would you like to purchase more?", "Uh-oh!", JOptionPane.OK_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Buy ($0.99)"}, "default");
                    board.replenishSpecialMoves();
                }
                getReset1Button().setText("Reset (" + board.getNumResetMoves() + ")");
                getSwap2Button().setText("Swap (" + board.getNumSwapMoves() + ")");
                getRemove1Button().setText("Remove (" + board.getNumRemoveMoves() + ")");
            }
        });
        reset1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boardViewPanel.getBoard().setCurrentMove(new ResetSpecialMove(boardViewPanel.getBoard(),
                        boardViewPanel.getBoard().getCurrentLevel()));
                if (!boardViewPanel.getBoard().enoughSpecialRemaining()) {
                    JOptionPane.showOptionDialog(null, "You're out of that kind of move. " +
                                    "Would you like to purchase more?", "Uh-oh!", JOptionPane.OK_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Buy ($0.99)"}, "default");
                    boardViewPanel.getBoard().replenishSpecialMoves();
                }
                boardViewPanel.getBoard().makeMove(boardViewPanel.getBoard().getCurrentMove());


                // play reset sound
                // sound from free version of http://www.sonniss.com/sound-effects/users-tomorrow-sound-effects-library/, by SoundMorph
                clip.play();
                boardViewPanel.getBoard().refill();
                boardViewPanel.refresh();
                movesLabel.setText("Moves: " + (boardViewPanel.getBoard().getMovesAllowed() -
                        boardViewPanel.getBoard().getMoveCount()));
                getReset1Button().setText("Reset (" + boardViewPanel.getBoard().getNumResetMoves() + ")");
                boardViewPanel.getBoard().isComplete();
            }
        });
    }

    /**
     * Sets the Board
     *
     * @param b: the Board
     */
    public void setBoard(Board b) {
        this.board = b;
        this.boardViewPanel.setBoard(this.board);
        this.boardViewPanel.refresh();
    }


    /**
     * Returns the menuBarPanel
     *
     * @return the menuBarPanel
     */
    public JPanel getMenuBarPanel() {
        return menuBarPanel;
    }

    /**
     * Returns the quitButton
     *
     * @return the quitButton
     */
    public JButton getQuitButton() {
        return quitButton;
    }

    /**
     * Returns the playContentPanel
     *
     * @return the playContentPanel
     */
    public JPanel getPlayContentPanel() {
        return playContentPanel;
    }

    /**
     * Returns the playSidebarPanel
     *
     * @return the playSidebarPanel
     */
    public JPanel getPlaySidebarPanel() {
        return playSidebarPanel;
    }

    /**
     * Returns the boardPanel
     *
     * @return the boardPanel
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }

    /**
     * Returns the boardViewPanel
     *
     * @return the boardViewPanel
     */
    public BoardViewPanel getBoardViewPanel() {
        return boardViewPanel;
    }

    /**
     * Returns the reset1Button
     *
     * @return the reset1Button
     */
    public JButton getReset1Button() {
        return reset1Button;
    }

    /**
     * Returns the remove1Button
     *
     * @return the remove1Button
     */
    public JButton getRemove1Button() {
        return remove1Button;
    }

    /**
     * Returns the swap2Button
     *
     * @return the swap2Button
     */
    public JButton getSwap2Button() {
        return swap2Button;
    }

    /**
     * Returns the scoreBar
     *
     * @return the scoreBar
     */
    public JProgressBar getScoreBar() {
        return scoreBar;
    }

    /**
     * Returns the threeStarsLabel
     *
     * @return the threeStarsLabel
     */
    public JLabel getThreeStarsLabel() {
        return threeStarsLabel;
    }

    /**
     * Returns the twoStarsLabel
     *
     * @return twoStarsLabel
     */
    public JLabel getTwoStarsLabel() {
        return twoStarsLabel;
    }

    /**
     * Returns the oneStarLabel
     *
     * @return oneStarLabel
     */
    public JLabel getOneStarLabel() {
        return oneStarLabel;
    }

    /**
     * Returns the playPanel
     *
     * @return the playPanel
     */
    public JPanel getPlayPanel() {
        return playPanel;
    }

    /**
     * Returns the scoreLabel
     *
     * @return the scoreLabel
     */
    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Returns the titleLabel
     *
     * @return the titleLabel
     */
    public JLabel getTitleLabel() {
        return titleLabel;
    }

    /**
     * Returns the board
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the movesLabel
     *
     * @return the movesLabel
     */
    public JLabel getMovesLabel() {
        return movesLabel;
    }

    public void setMovesLabel(JLabel movesLabel) {
        this.movesLabel = movesLabel;
    }

    /**
     * Returns the timerViewPanel
     *
     * @return the timerViewPanel
     */
    public TimerPanel getTimerViewPanel() {
        return timerViewPanel;
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
        playPanel = new JPanel();
        playPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        playPanel.setMaximumSize(new Dimension(700, 625));
        playPanel.setMinimumSize(new Dimension(700, 625));
        playPanel.setPreferredSize(new Dimension(700, 625));
        menuBarPanel = new JPanel();
        menuBarPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        menuBarPanel.setBackground(new Color(-3167898));
        playPanel.add(menuBarPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        quitButton = new JButton();
        quitButton.setText("Quit");
        menuBarPanel.add(quitButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(75, -1), new Dimension(75, -1), new Dimension(75, -1), 1, false));
        titleLabel = new JLabel();
        titleLabel.setText("Puzzle");
        menuBarPanel.add(titleLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        timerPlaceholder = new JPanel();
        timerPlaceholder.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        timerPlaceholder.setOpaque(false);
        menuBarPanel.add(timerPlaceholder, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, -1), new Dimension(200, -1), new Dimension(200, -1), 1, false));
        timerViewPanel = new TimerPanel();
        timerPlaceholder.add(timerViewPanel.$$$getRootComponent$$$(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playContentPanel = new JPanel();
        playContentPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        playPanel.add(playContentPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playSidebarPanel = new JPanel();
        playSidebarPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        playContentPanel.add(playSidebarPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(20, 0, 0, 0), -1, -1));
        playSidebarPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scoreBar = new JProgressBar();
        scoreBar.setForeground(new Color(-8388603));
        scoreBar.setName("scoreBar");
        scoreBar.setOrientation(1);
        scoreBar.setStringPainted(false);
        scoreBar.setValue(0);
        panel1.add(scoreBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 30), -1, -1));
        panel2.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        oneStarLabel = new JLabel();
        oneStarLabel.setIcon(new ImageIcon(getClass().getResource("/view/onestaricn.png")));
        panel3.add(oneStarLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 30), -1, -1));
        panel2.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        twoStarsLabel = new JLabel();
        twoStarsLabel.setIcon(new ImageIcon(getClass().getResource("/view/twostaricn.png")));
        panel4.add(twoStarsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 30), -1, -1));
        panel2.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        threeStarsLabel = new JLabel();
        threeStarsLabel.setIcon(new ImageIcon(getClass().getResource("/view/threestaricn.png")));
        panel5.add(threeStarsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        playSidebarPanel.add(panel6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 20, 0, 20), -1, -1));
        panel6.add(panel7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scoreLabel = new JLabel();
        scoreLabel.setName("scoreLabel");
        scoreLabel.setText("Score: 0");
        panel7.add(scoreLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        movesLabel = new JLabel();
        movesLabel.setName("movesLabel");
        movesLabel.setText("Moves: 0");
        panel7.add(movesLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(3, 1, new Insets(0, 10, 10, 20), -1, -1));
        panel6.add(panel8, new GridConstraints(1, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reset1Button = new JButton();
        reset1Button.setText("Reset (5)");
        panel8.add(reset1Button, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        remove1Button = new JButton();
        remove1Button.setText("Remove (5)");
        panel8.add(remove1Button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        swap2Button = new JButton();
        swap2Button.setText("Swap (5)");
        panel8.add(swap2Button, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayoutManager(1, 1, new Insets(5, 5, 5, 5), -1, -1));
        playContentPanel.add(boardPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 2, false));
        boardViewPanel = new BoardViewPanel();
        boardPanel.add(boardViewPanel.$$$getRootComponent$$$(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return playPanel;
    }
}
