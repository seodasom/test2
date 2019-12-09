package board;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Notice extends JFrame implements ActionListener {
	JTextField tf[];
	JLabel[] lb;
	JTextArea ta;
	JButton bt[];
	String[] l_title = {"글제목", "작성일", "작성자","내용"};
	String[] b_title = {"글등록", "다시쓰기", "목록보기"};
	List<BoardValue> list = new ArrayList<>();
	
	public Notice() {
		super("게시판 글쓰기");
		setLayout(new FlowLayout());
		
		lb = new JLabel[4];
		for(int i=0; i<lb.length; i++) {
			lb[i] = new JLabel(l_title[i]);
		}

		
		tf = new JTextField[3];
		for(int i=0; i<tf.length; i++) {
			tf[i] = new JTextField(20);
		}
		
		bt = new JButton[3];
		for(int i=0; i<bt.length; i++) {
			bt[i] = new JButton(b_title[i]);
		}
		
		ta = new JTextArea(7,25);
		for(int i=0; i<3; i++) {
			add(lb[i]);
			add(tf[i]);
		}
		
		add(lb[3]);	
		add(ta);
		
		for(int i=0; i<3; i++) {
			add(bt[i]);
			bt[i].addActionListener(this);
		}
		
		
		//bt1.addActionListener(this);
		//bt2.addActionListener(this);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Notice();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = "";
		String yes = "위 내용을 등록하시겠습니까?";
		for(int i=0; i<tf.length; i++) {
			message += lb[i].getText() + ":" + tf[i].getText() + "\n";
		}
		message += lb[3].getText() + ":" + ta.getText();
		
		if(e.getActionCommand().equals("글등록")) {
			int con = JOptionPane.showConfirmDialog(this, message+"\n"+yes);
			if(con==JOptionPane.YES_NO_OPTION) {
				list.add(new BoardValue(tf[0].getText(), tf[2].getText(), ta.getText(), tf[1].getText()));
			}
			else {
			}
			 
		}
		if(e.getActionCommand().equals("다시쓰기")) {
			for(int i=0; i<tf.length; i++) {
				tf[i].setText("");;
			}
			ta.setText("");
			tf[0].requestFocus();
		}
		if(e.getActionCommand().equals("목록보기")) {
			String listMessage = "";
			System.out.println(list.size());
			for(int i=0; i<list.size(); i++) {
				BoardValue bv = list.get(i);
				listMessage += (i+1) + " 제목 : " + bv.getTitle() + ", 작성자 : " + bv.getName() + "\n";
			}
			JOptionPane.showMessageDialog(this, listMessage);
		}
	}

}
