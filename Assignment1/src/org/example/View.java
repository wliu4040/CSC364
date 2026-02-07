package org.example;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener {
    private static JComboBox<String> userClickType;
    private static View view;
    private View() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Concurrent Grid Pathfinder"));
        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new BoxLayout(buttonHolder,BoxLayout.X_AXIS));
        buttonHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(buttonHolder);
        buttonHolder.add(new JLabel("Grid Size:"));
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(60,30));
        field.setMinimumSize(new Dimension(60,30));
        buttonHolder.add(field);
        buttonHolder.add(new JButton("Start"));
        buttonHolder.add(new JButton("Reset"));
        JComboBox<String> combo = new JComboBox<>(new String[]{"START", "END", "OBSTACLE"});
        combo.setMaximumSize(new Dimension(120,30));
        combo.setMinimumSize(new Dimension(120,30));
        buttonHolder.add(combo);

        GridPanel gPanel = new GridPanel(10,10);
        gPanel.addPropertyChangeListener(this);
        add(gPanel);
        this.userClickType = combo;
        setVisible(true);
    }

    public static View getInstance() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
    public String getUserClickType() {
        return userClickType.getSelectedItem().toString();
    }
}
