import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JFrameEx extends JFrame{

    public JFrameEx() {
        super("JFrame 테스트");//타이틀

        JButton bt1 = new JButton("Hello Swing North");//버튼1
        JButton bt2 = new JButton("Hello Swing Center");//버튼2
        Container cp = getContentPane();

        cp.add("West", bt2);//위치지정(동서남북)
        cp.add("East", bt1);//위치지정
        //cp.add가 아닌 add만 할 경우 센터로 위치지정

        setSize(1000, 1000);//창 크기 지정
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//닫기 버튼 클릭(프레임 종료) 시
    }
    public static void main(String[] args) {
        new JFrameEx();
    }
}
