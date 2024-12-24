package stu202100800466wangzhimin.GUIsystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class StudentInfoManageSystem extends JFrame implements ActionListener,FocusListener
{

	ArrayList<Student> stuList;
	ArrayList<Student> selectedstuList;
	JPanel p1;
	JScrollPane sp1;
	JTable table;
	DefaultTableModel model;
	String filename = "data/Student.txt";
	JTextField tf1;
	JComboBox<String> cb1;
	JDialog dialog;
	JDialog dialog_cal;
	JDialog dialog_grade;
	JTable  gradetable;
	
	
	StudentInfoManageSystem(String title,ArrayList<Student> stuList)
	{
		super(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //����gui�ر��Զ���ֹ��������
		this.setLayout(new BorderLayout());
		this.stuList = stuList;

		
		panelConfig();//ҳ���Ϸ�panel ����
		menuConfig();//ҳ��˵�����
		tableConfig();//jtable����
        
		this.add(sp1,BorderLayout.SOUTH);//panel��������
		this.add(p1,BorderLayout.NORTH);
		this.setSize(600, 700);
		this.setResizable(false);
		this.setVisible(true);

		
	}
	
	
	public static void main(String[] args)
	{
		String filename = "data/student_scores.txt";
		StudentInfoLoader studentInfoLoader = new StudentInfoLoader();
		ArrayList<Student> stuList = studentInfoLoader.readStudentsInfo(filename);
		StudentInfoManageSystem studentInfoManageSystem = new StudentInfoManageSystem("ѧ������ϵͳ", stuList);
	}
	




	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommond=e.getActionCommand();
		if(actionCommond.equals("search")) {
			String searchString = tf1.getText();
			String choiceString = (String) cb1.getSelectedItem();
			model.getDataVector().clear();
			selectedstuList= searchstuList(choiceString, searchString);
			for (Student student:selectedstuList)
			{
				model.addRow(new Object[]{student.stuID, student.classString,student.name, student.score_data, 
						student.score_sys,student.score_software,student.score_java,student.score_ML});
			}
			
			int studentcount = table.getRowCount();
			if(studentcount == 0)
			{
				model.getDataVector().clear();
				dialogShow("��ѯ�޽��");
			}else {
				dialogShow("��ѯ��"+studentcount+"�����");
				
			}
			table.repaint();
		}else if (actionCommond.equals("cal")) {
//			dialog_cal=new DialogCal(this, "ͳ�ƽ��");
			int score_max = 0;
			int score_min = 100;
			int score_mid = 0;
			int score_mean = 0;
			int sum = 0;
			for(Student student:stuList) {
				if(student.score_data>score_max)
				{
					score_max = student.score_data;
				}
				
				if(student.score_data<score_max)
				{
					score_min = student.score_data;
				}
				sum = sum +student.score_data;
				
				
			}
			score_mean = sum/52;
			dialogShow("���ݽṹ��߷�"+score_max+"��ͷ�"+score_min+"\n��ֵ"+score_min);
		}else if(actionCommond.equals("grade"))
		{
			for(Student student:stuList)
			{
				double grade = 2*student.score_data+3*student.score_sys+3*student.score_software+3.5*student.score_java+4*student.score_ML;
				grade = grade/(2+3+3+3.5+4);
				student.grade = grade;
			}
			Collections.sort(stuList,new ComparatorGrade());
			
			dialog_grade = new JDialog();
	        dialog_grade.setTitle("�����ĵ�");
	        dialog_grade.setSize(470, 200);
			String[] columnNames1 = {"����", "ѧ��", "����","����"};
			JTable table1 = new JTable();
			JFrame f1 = new JFrame("���㼨��");
			table1.setFont(new Font("΢���ź�", Font.PLAIN, 12));//���塢��ɫ����С
			table1.setColumnSelectionAllowed (true);	  //�Ƿ�����ѡ��һ����
			table1.setRowSelectionAllowed (true);		  //�Ƿ�����ѡ��һ����
			DefaultTableModel model1 = new DefaultTableModel();
	        model1.setColumnIdentifiers(columnNames1); //ʹ��Ĭ�ϵı�񲼾�
	        int i =1;
	        for (Student student:stuList)
			{
	        	if(i<11) {
				model1.addRow(new Object[]{i, student.stuID,student.name, student.grade});
				System.out.println(i+" "+student.stuID+" "+student.name+" "+student.grade);
				i++;
				}
			}
	        table1.setModel(model1);
	        table1.getTableHeader().setFont(new Font("΢���ź�", Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
	        table1.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
	        table1.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������
	        table1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
	        DefaultTableCellRenderer dc1=new DefaultTableCellRenderer();
	        dc1.setHorizontalAlignment(JLabel.CENTER);
	        table1.setDefaultRenderer(Object.class, dc1);
	        JScrollPane  sp2 = new JScrollPane(table1);        	      
	        f1.add(sp2);
	        f1.setSize(400,400);
	        f1.setVisible(true);
	        // ���õ���Ϊģ̬
//	        dialog_grade.setModal(true);
	        // ���õ����رղ���
	        dialog_grade.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        // ���õ�������������ڵ�λ��
	        dialog_grade.setLocationRelativeTo(this);	        
//	        dialog_grade.setVisible(true);
			
			
			
		}
		if(actionCommond.equals("save"))
		{
			try 
			{
				String  savePath = "data/result.txt";
				try (FileWriter writer = new FileWriter(savePath)) {
		            // д������
		            writer.write("ѧ��,�༶,����,���ݽṹ,����ϵͳ,�������,java,����ѧϰ\n");
		            // д��ÿ�� Employee ���������
		            for (Student employee : selectedstuList) {
		                writer.write(employee.stuID + "," + employee.classString + "," + employee.name +
		                        "," + employee.score_data + "," + employee.score_sys + "," + employee.score_software+ "," + employee.score_java
		                       + "," + employee.score_ML+"\n");
		                System.out.println(employee.score_data);
		            }
		        } catch (IOException exio) {
		            exio.printStackTrace();
		        }
				dialogShow("����ɹ���·��Ϊ\n/data/result.txt");
				
			}catch(NullPointerException ex)
			{
				dialogShow("���Ȳ�ѯ�ٱ���!\n����ָ���쳣��");
			}
			
		}
		
		
	}
	
	
	
	
	private void panelConfig() {
		JLabel lb1 = new JLabel("�������ѯ����:");
		lb1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		tf1 = new JTextField("",20);
		tf1.addFocusListener(this);
		JButton b1 = new JButton("��ѯ");
		b1.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		b1.addActionListener(this);
		b1.setActionCommand("search");
		JButton b2 = new JButton("ͳ�Ƴɼ�");
		b2.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		b2.addActionListener(this);
		b2.setActionCommand("cal");
		JButton b3 = new JButton("���㼨��");
		b3.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		b3.addActionListener(this);
		b3.setActionCommand("grade");
		
		String[] options = {"��","������", "��ѧ��"};
		cb1 = new JComboBox<>(options);
		cb1.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		cb1.setActionCommand("choice");
		cb1.addActionListener(this);
		ImageIcon icon = new ImageIcon("data/background.png");
		JLabel label = new JLabel(icon);
		

		
		
		p1 = new JPanel();
		
		p1.add(lb1);
		p1.add(tf1);
		p1.add(cb1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(label,BorderLayout.SOUTH);
		
		
	}
	
	private void dialogShow(String taString)
	{
		dialog = new JDialog();
        dialog.setTitle("��ʾ");
        dialog.setSize(200, 100);
        JTextArea ta = new JTextArea();
        ta.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        ta.append(taString);
        ta.setEditable(false);
        // ��ӵ����е����
        dialog.add(ta,BorderLayout.CENTER);	        	        	   
        

        // ���õ���Ϊģ̬
        dialog.setModal(true);

        // ���õ����رղ���
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        
        
        // ���õ�������������ڵ�λ��
        dialog.setLocationRelativeTo(this);
        dialog.setSize(200,150);
        // ��ʾ����
        dialog.setVisible(true);
        
	}
	
	
	
	private void menuConfig()
	{
		// �����˵���
        JMenuBar menuBar = new JMenuBar();

        // �����˵�
        JMenu fileMenu = new JMenu("�ļ�");
        JMenu helpMenu = new JMenu("����");
        // �����˵���
        JMenuItem saveItem = new JMenuItem("��ѯ�������",KeyEvent.VK_S);
        
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        saveItem.setActionCommand("save");
        saveItem.addActionListener(this);
        fileMenu.add(saveItem);
        
        
        JMenuItem helpItem = new JMenuItem("�����ĵ�",KeyEvent.VK_P);
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        helpItem.setActionCommand("help");
        helpItem.addActionListener(this);
        helpMenu.add(helpItem);
        
        // ���˵���ӵ��˵���
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // ���˵������õ�������
        this.setJMenuBar(menuBar);
	}
	
	private  void tableConfig()
	{
		String[] columnNames = {"ѧ��", "�༶", "����", "���ݽṹ","����ϵͳ","�������","Java�������","����ѧϰ"};
		table = new JTable();
		table.setFont(new Font("΢���ź�", Font.PLAIN, 12));//���塢��ɫ����С
		table.setColumnSelectionAllowed (true);	  //�Ƿ�����ѡ��һ����
		table.setRowSelectionAllowed (true);		  //�Ƿ�����ѡ��һ����
		model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames); //ʹ��Ĭ�ϵı�񲼾�
        
        table.setModel(model);
        table.getTableHeader().setFont(new Font("΢���ź�", Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������
        table.setFont(new Font("΢���ź�", Font.PLAIN, 12));
        table.getColumnModel().getColumn(0).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(100);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        sp1 = new JScrollPane(table);
	}

	
	
	private ArrayList<Student> searchstuList(String choiceString,String searchString)
	{
		selectedstuList = new ArrayList<Student>();
		String regexString = searchString.replaceAll("(.)", "$1.*");//ÿ���ַ��м䶼�������ƥ��
		Pattern pattern = Pattern.compile(regexString);
		String[] tipStrings = {"�밴��˳��ģ����ѯ","ģ����ѯ�������š�����","��ȷ��ѯ��������Ϣ��","ģ����ѯ,����������Ϣ��",""};
		List<String> tipsList = Arrays.asList(tipStrings);
		if (tipsList.contains(searchString))
			{
				selectedstuList = stuList;
				Collections.sort(selectedstuList,new ComparatorID());
			}
		else 
			{
				switch (choiceString) {
				case "��ѧ��":
					for (Student Student:stuList)
					{
						if(Student.stuID.equals(searchString))
						{
							selectedstuList.add(Student);
						}
					}					
					break;
					
				case "������":
					for (Student Student:stuList)
					{
						Matcher matcher = pattern.matcher(Student.name);
						if(matcher.find())
						{
							selectedstuList.add(Student);
						}
						
					}
					break;
					
				default:
					selectedstuList = stuList;
					Collections.sort(selectedstuList,new ComparatorID());
					break;
				}
			}
		
		return selectedstuList;
	}
}
