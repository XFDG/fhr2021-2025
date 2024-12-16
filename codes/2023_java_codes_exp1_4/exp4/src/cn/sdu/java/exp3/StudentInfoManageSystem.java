package cn.sdu.java.exp3;

import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;

public class StudentInfoManageSystem extends Frame implements ActionListener,TextListener 
{

	TextField t;
	Button b;
	ArrayList<Student> stuList;
	String text="";
	DefaultTableModel model;
	Choice ch1;
	Choice ch2;
	JTextArea classnumArea;
	public StudentInfoManageSystem(String title,ArrayList<Student> stuList) {
		super(title);
		this.stuList = stuList;
		t = new TextField("", 30);
		t.addTextListener(this);
		
		
		b = new Button("��ѯ");
		b.setActionCommand("1");	//��������ж�������ţ�
		b.addActionListener(this);
		
		
		ch1 = new Choice(); //����choiceѡ���ѯģʽ
		ch1.add("���༶");
		ch1.add("��ѧ��");
		
		
		ch2 = new Choice();
		ch2.add("������");
		ch2.add("�ɼ�����");
		ch2.add("ѧ������");
		
		JTable table = new JTable(); //����Jtable��Ų�ѯ����
		table.setFont(new Font("����", Font.PLAIN, 12));//���塢��ɫ����С
		table.setColumnSelectionAllowed (true);	  //�Ƿ�����ѡ��һ����
		table.setRowSelectionAllowed (true);		  //�Ƿ�����ѡ��һ����
        // ������ͷ
        String[] columnNames = {"����", "ѧ��", "�༶", "�ɼ�"};
        // ����һ��Ĭ�ϵı��ģ��
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames); //ʹ��Ĭ�ϵı�񲼾�
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        
		classnumArea = new JTextArea();
		classnumArea.setSize(500, 400);
		classnumArea.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        classnumArea.setForeground(Color.BLACK);    //��������ı���ɫ
        classnumArea.setFont(new Font("����",Font.BOLD,16));    //�޸�������ʽ

		classnumArea.append("--------------��ʾ��Ϣ-------------\n");
		show_num(stuList);
		
        
		Panel p = new Panel();
		p.add(t);
		p.add(ch1);
		p.add(ch2);
		p.add(b);
		
		p.add(classnumArea, BorderLayout.CENTER);
		add(scrollPane); //����Դ���������panel
		add(scrollPane,BorderLayout.SOUTH);
		add(p, BorderLayout.NORTH);
		setSize(500, 700);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
        String fileLocation="data/student_score.txt";
        ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);

        
		StudentInfoManageSystem sim = new StudentInfoManageSystem("�ɼ�����ϵͳ",stuList);
		System.out.print(stuList.isEmpty());
		sim.addWindowListener(new WindowAdapter(){ //��Ӵ��ڼ���������AWT��frameҲ����ͨ�����رմ���
		public void windowClosing(WindowEvent e){
		    	System.exit(0);
		    }
		    });
		 	
		
	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String actionCommond=e.getActionCommand();//��ȡ����ж�������ţ�
		classnumArea.setText("");
		classnumArea.append("--------------��ʾ��Ϣ-------------\n");
		show_num(stuList);//��ʾ���༶����
        if(actionCommond.equals("1")) { //��������
        	model.getDataVector().clear();
        	stuList_sort(stuList);
        	String chString = ch1.getSelectedItem();
        	Pattern pattern = Pattern.compile(text);
        	//��Ӧ����ж���������Ҫ�Ĳ���
			for (Student stu : stuList) {
				if (chString=="���༶") //�༶ģ����ѯ
				{
					Matcher matcher = pattern.matcher(stu.classString); //����matcher��ѯĿ���ַ���
					if(text == "")//Ϊ��ʱֱ�����ȫ����Ϣ
			        {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        	continue;
			        
			        }
			        if (matcher.find()) {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        } else {
			        }
				}else if(chString=="��ѧ��")//ѧ�ž�ȷ��ѯ
				{
					if(text == "")
			        {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        	continue;
			        
			        }
			        if (text.equals(stu.stuID)) {
			        	model.addRow(new Object[]{stu.name, stu.stuID, stu.classString, stu.score});
			        } else {
//			        	
			        }
			        
				}
		        
			}
			classnumArea.append("��ǰΪ"+chString+ch2.getSelectedItem()+"��ѯ\n"); //���ز�ѯ���Ľ������
			if(model.getRowCount()==0)
	        {
	        	classnumArea.append("��ѯ�޽��\n");
	        }else {
	        	classnumArea.append("��ѯ��"+model.getRowCount()+"�����\n");
			}
        }else if(actionCommond.equals("2")) {
        	
        }else {
        	
        }
        
//        Component c = (Component) e.getSource();
//		System.out.println("ComponentName:" + c.getName());
//		if (e.getSource() == b) {
//			
//		}
	}
	public void textValueChanged(TextEvent e)
	{
		TextField tf=(TextField) e.getSource();
		text=tf.getText();
		System.out.println(text);
    	//��Ӧ����ж���������Ҫ�Ĳ���
	}
	
	
	public static HashMap<String, Integer> get_classnum(ArrayList<Student> stuList) //ʹ��hashmap��ֵ�Խṹ����Ų����������༶������
	{
		HashMap<String, Integer> classnum = new HashMap<String, Integer>(); //����stulist
		for(Student stu:stuList) {
			String classString = stu.classString;
			if(!classnum.keySet().contains(classString)){ //�ж϶����hashmap���Ƿ���ڵ�ǰ��ȡ���İ༶�ַ���
				classnum.put(classString, 0); //����ǲ����ڵģ���ʼ��һ���µİ༶������0�ļ�ֵ��
			}
			classnum.put(classString, classnum.get(classString) + 1); //��ѯ��Ӧ�༶����+1
			
		}
		return classnum; //�������ļ�ֵ��
	}
	
	
	private void show_num(ArrayList<Student> stuList)
	{
		HashMap<String, Integer> classnum  = get_classnum(stuList);
		for(String classname:classnum.keySet()) //��ӡ���ص�hashmap�ļ�ֵ��
		{
			Integer numInteger = classnum.get(classname);
			classnumArea.append("�༶��"+classname+" ������"+numInteger+"\n");
		}
		
	}
	
	
	
	private void stuList_sort (ArrayList<Student> stuList)  //������
	{
		String ch2String = ch2.getSelectedItem();
		if(ch2String == "�ɼ�����")
		{
			Collections.sort(stuList);
		}else if(ch2String == "ѧ������")
		{
			Collections.sort(stuList,new ScoreComparator());
			
		}
	}
	
}

class ScoreComparator implements Comparator<Student> //�ⲿ�Ƚ���
{

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		String o1ID = o1.stuID.substring(o1.stuID.length() - 9); //ȥ��ǰ��λ��ͬ���ַ�
		String o2ID = o2.stuID.substring(o2.stuID.length() - 9);
		int result = Integer.valueOf(o1ID).intValue() < Integer.valueOf(o2ID).intValue() ? -1 : (o1.score == o2.score ? 0 : 1);   
        return result;
	}
	
}

