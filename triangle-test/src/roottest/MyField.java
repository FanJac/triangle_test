package roottest;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MyField extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    MyField(String title, Color fontColor) {
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                title, TitledBorder.LEFT, TitledBorder.CENTER, new Font("宋体", Font.BOLD, 16), fontColor));
    }
}
