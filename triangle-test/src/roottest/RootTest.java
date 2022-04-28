package roottest;

import javax.swing.*;

public class RootTest {

    public static void main(String[] args) {
        JFrame frame = new MyFrame("三角形执行路径测试");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1700, 900);
        frame.setVisible(true);
    }
}
