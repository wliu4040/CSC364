package org.example;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    public View() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Concurrent Grid Pathfinder"));
        JPanel buttonHolder = new JPanel();
        buttonHolder.setLayout(new BoxLayout(buttonHolder,BoxLayout.X_AXIS));
        add(buttonHolder);
        buttonHolder.add(new JLabel("Grid Size:"));
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(60,30));
        buttonHolder.add(field);
        buttonHolder.add(new JButton("Start"));
        buttonHolder.add(new JButton("Reset"));

        add(new JLabel("(Click 1 = START, Click 2 = END, Click = toggle OBSTACLE)"));

        add(new GridPanel(10,10));



        setVisible(true);
    }
}
