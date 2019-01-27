import javax.swing.*;
import java.awt.*;
import java.io.*;

public class JEditorPaneEx extends JFrame{
    public JEditorPaneEx()throws IOException {
        super("JEditorPane test");
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);//편집(쓰기) 불가능
        editorPane.setPage("https://www.naver.com");//URL 지정
        JScrollPane sp = new JScrollPane(editorPane);//스크롤바 객체 생성
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//수직스크롤바 항상 표시
        sp.setPreferredSize(new Dimension(800, 600));//전체 창 크기
        add(sp);//스크롤바 추가
        pack();
        setLocation(1000, 200);//창 생성 위치
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws IOException {
        new JEditorPaneEx();
    }
}
