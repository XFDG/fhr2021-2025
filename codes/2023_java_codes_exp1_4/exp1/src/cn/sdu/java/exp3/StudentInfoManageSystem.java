package cn.sdu.java.exp3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentInfoManageSystem extends Frame implements ActionListener {

	TextField t;
	Button b;
	TextArea ta;
//	ArrayList<Student> stuList;
	
	public StudentInfoManageSystem(String title,ArrayList<Student> stuList) {
		super(title);
//		this.stuList = stuList;s
		t = new TextField("enter something", 20);
		b = new Button("��ť");
		ta= new TextArea();
		b.setActionCommand("1");	//��������ж�������ţ�
		b.addActionListener(this);
		Panel p = new Panel();
		p.add(t);p.add(b);
		add(p, BorderLayout.NORTH);
		add(ta, BorderLayout.SOUTH);
		setSize(300, 300);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
        String fileLocation="data/student_score.txt";
        ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);
		StudentInfoManageSystem sim = new StudentInfoManageSystem("System",stuList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommond=e.getActionCommand();//��ȡ����ж�������ţ�
        if(actionCommond.equals("1")) {
        	System.out.println("actionCommondΪ"+actionCommond+"��Action�¼���actionPerformed�б�����");
        	//��Ӧ����ж���������Ҫ�Ĳ���
//			for (Student stu : stuList) { // ��һ��ѭ����ʽ
//				String name = stu.name;
//				int score = stu.score;
//				System.out.println("������" + name + ",�ɼ���" + score);
//			}
        }else if(actionCommond.equals("2")) {
        	
        }else {
        	
        }
        
//        Component c = (Component) e.getSource();
//		System.out.println("ComponentName:" + c.getName());
//		if (e.getSource() == b) {
//			
//		}
	}
}
