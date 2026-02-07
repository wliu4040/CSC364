package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main extends JFrame {
    public Main() {
        add(View.getInstance());
        setSize(800,600);
        setVisible(true);
    }
    static void main() {
        Main frame = new Main();
    }
}
