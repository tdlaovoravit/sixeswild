package view;

import model.Level;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 *  BuilderLevelCellRenderer Class.
 *  Manages the gathering of level information for display in the
 *  Builder level select menu.
 *
 *  @authors Arthur Lockman, Bryce Kaw-uh
 */
public class BuilderLevelCellRenderer extends JLabel implements ListCellRenderer<Level>
{
    @Override
    /**
     * Gets the necessary information for a level, which will be displayed
     * in the Builder level select menu.
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     */
    public Component getListCellRendererComponent(JList<? extends Level> list, Level value, int index, boolean isSelected, boolean cellHasFocus)
    {
        String description = value.toString();
        ImageIcon icon;
        String rsrc = "";

        rsrc = "/view/" + value.getType().toLowerCase() + "icn.png";

        URL iconURL = getClass().getResource(rsrc);
        iconURL = (iconURL == null) ? getClass().getResource("/view/puzzleicn.png") : iconURL;
        icon = new ImageIcon(iconURL);

        setIcon(icon);
        setText(description);
        setOpaque(true);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        if (isSelected)
        {
            setBackground(new Color(86, 95, 182));
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
