package org.example;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton{
    private static final int buttonHeight = 30;
    private static final int buttonWidth = 60;

    private static final Font font = new Font("TimesRoman", Font.BOLD, 16);

    public static JButton createButton(String text, int x, int y){
        JButton button = new JButton(text);
        button.setBounds(x,y,buttonWidth,buttonHeight);
        button.setHorizontalTextPosition(CENTER);

        button.setContentAreaFilled(false);
        button.setBorder(null);
        button.setFont(font);

        return button;
    }
}
