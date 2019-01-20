package com.lang.ui;


import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() throws HeadlessException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        setPreferredSize(new Dimension(660, 500 ));
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        this.add(contentPanel);

        JPanel panel = new JPanel(null);
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(JBColor.ORANGE, 2));

        panel.add(new ClassModel(new Rectangle(130, 130)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }


}
