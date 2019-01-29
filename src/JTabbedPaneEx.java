import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JTabbedPaneEx extends JFrame{
    ImageIcon icon1, icon2, tabicon;
    JButton b1, b2;
    JTabbedPane jp;
    public JTabbedPaneEx() {
        super("JTabbedPane 테스트");
        icon1 = new ImageIcon("../../images/image3.gif");
        icon2 = new ImageIcon("../../images/image2.gif");
        tabicon = new ImageIcon("../../images/image1.gif");
        b1 = new JButton("사진1", icon1);
        b2 = new JButton("사진2", icon2);

        jp = new JTabbedPane();
        jp.addTab("탭1", new ImageIcon(" "), b1);//탭1에는 사진1
        jp.addTab("탭2", new JPanel().add(new JTextArea(7, 20)));//탭2에는 텍스트 입력 공간
        jp.addTab("탭3", tabicon, b2, "탭사진도 추가했습니다. ");//탭2에는 사진2가 들어가고 탭 사진도 추가

        add(jp);

        setBounds(300, 300, 300, 300);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JTabbedPaneEx();
    }
}
