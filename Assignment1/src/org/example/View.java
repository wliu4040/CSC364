package org.example;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel{
    private static JComboBox<String> userClickType;
    private static View view;
    private static GridPanel gridPanel;
    private static JComboBox<Integer> gridSizesBox;
    private View() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Concurrent Grid Pathfinder"));

        JComboBox<Integer> gridSizes = new JComboBox<>();
        gridSizesBox = gridSizes;

        gridSizes.setMaximumSize(new Dimension(100,30));
        gridSizes.setMinimumSize(new Dimension(100,30));

        for(int i = 10; i <= 100; i++) {
            gridSizes.addItem(i);
        }

        gridSizes.addActionListener(e -> resetGrid());


        JPanel buttonHolder = new JPanel();
        add(buttonHolder);
        buttonHolder.setLayout(new BoxLayout(buttonHolder,BoxLayout.X_AXIS));
        buttonHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonHolder.add(gridSizes);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {new BFS(2).start();});

        buttonHolder.add(startButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGrid());

        buttonHolder.add(resetButton);

        JComboBox<String> combo = new JComboBox<>(new String[]{"START", "END", "OBSTACLE"});
        combo.setMaximumSize(new Dimension(120,30));
        combo.setMinimumSize(new Dimension(120,30));

        buttonHolder.add(combo);
        userClickType = combo;
        gridPanel = new GridPanel(10);
        add(gridPanel);
        setVisible(true);
    }

    public static View getInstance() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    public String getUserClickType() {
        return userClickType.getSelectedItem().toString();
    }

    private void resetGrid() {
        int selected = Integer.valueOf(gridSizesBox.getSelectedItem().toString());
        if (gridPanel != null) {
            view.remove(gridPanel);
        }
        gridPanel = new GridPanel(selected);
        view.add(gridPanel);

        view.repaint();
        view.revalidate();
    }

    public GridPanel getGridPanel() {
        return gridPanel;
    }
}
