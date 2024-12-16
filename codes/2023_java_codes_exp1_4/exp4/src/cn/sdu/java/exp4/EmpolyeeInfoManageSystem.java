package cn.sdu.java.exp4;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class EmpolyeeInfoManageSystem extends JFrame implements ActionListener,FocusListener{
	

	/**
	 * 
	 */
	ArrayList<Employee> employeeList;
	ArrayList<Employee> selectedemployeeList;
	JPanel p1;
	JScrollPane sp1;
	JTable table;
	DefaultTableModel model;
	String filename = "data/employee.txt";
	JTextField tf1;
	JComboBox<String> cb1;
	JDialog dialog;
	public EmpolyeeInfoManageSystem(String title,ArrayList<Employee> employeeList)
	{
		super(title);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //����gui�ر��Զ���ֹ��������
		this.setLayout(new BorderLayout());
		this.employeeList = employeeList;

		
		panelConfig();//ҳ���Ϸ�panel ����
		menuConfig();//ҳ��˵�����
		tableConfig();//jtable����
        
		this.add(sp1,BorderLayout.SOUTH);//panel��������
		this.add(p1,BorderLayout.NORTH);
		this.setSize(580, 700);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args)
	{
		String fileLocation="data/employee.txt";
		ArrayList<Employee> employlList = EmployeeInfoLoader.readEmployeesInfo(fileLocation);//��ȡ�ļ�
		EmpolyeeInfoManageSystem eim = new EmpolyeeInfoManageSystem("Ա������ϵͳ", employlList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommond=e.getActionCommand();
		
		if(actionCommond.equals("search")) {
			String searchString = tf1.getText();
			String choiceString = (String) cb1.getSelectedItem();
			model.getDataVector().clear();
			selectedemployeeList= searchEmployeeList(choiceString, searchString);
			for (Employee employee:selectedemployeeList)
			{
				model.addRow(new Object[]{employee.name, employee.department,employee.phonenumber, employee.email, employee.isTop});
			}
			int employeecount = table.getRowCount();
			if(employeecount == 0)
			{
				model.getDataVector().clear();
				dialogShow("��ѯ�޽��");
			}else {
				dialogShow("��ѯ��"+employeecount+"�����");
			}
		}
		
		
		if(actionCommond.equals("save"))
		{
			try 
			{
				String  savePath = "data/query_result.txt";
				try (FileWriter writer = new FileWriter(savePath)) {
		            // д������
		            writer.write("����,����,�绰,Email,211/985\n");

		            // д��ÿ�� Employee ���������
		            for (Employee employee : selectedemployeeList) {
		                writer.write(employee.name + "," + employee.department + "," + employee.phonenumber +
		                        "," + employee.email + "," + employee.isTop + "\n");
		            }
		        } catch (IOException exio) {
		            exio.printStackTrace();
		        }
				dialogShow("����ɹ���·��Ϊ\n/data/query_result.txt");
				
			}catch(NullPointerException ex)
			{
				dialogShow("���Ȳ�ѯ�ٱ���!\n����ָ���쳣��");
			}
			
		}
		
		
		if(actionCommond.equals("help"))
		{
			dialog = new JDialog();
	        dialog.setTitle("�����ĵ�");
	        dialog.setSize(470, 200);
	        JTextArea ta = new JTextArea();
	        ta.setFont(new Font("΢���ź�", Font.PLAIN, 14));
	        ta.append("����������������Ա������ϵͳ�����ĵ�����������������\n");
	        ta.append("1.֧�����ֲ�ѯ��ʽ��\n(1)���ɲ�ѯ���ɶ�������Ϣģ����ѯ������ʱ�뱣֤������Ϣͬ��˳��\n"
	        		+ "(2)��������ѯ���ɶ���������ģ����ѯ\n"
	        		+"(3)�����Ų�ѯ���ɶԲ��Ž��о�ȷ��ѯ\n"
	        		+"2.֧�ֲ�ѯ���������δ��ѯֱ�ӱ��������ʾ��Ϣ\n"
	        		+"����·��Ϊ/data/query_result.txt\n"
	        		+"3.֧�ֲ�ѯ��ʽ��ɫ������ʾ");
	        ta.setEditable(false);
	        dialog.add(ta);	        	        	        
	        // ���õ���Ϊģ̬
	        dialog.setModal(true);
	        // ���õ����رղ���
	        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        // ���õ�������������ڵ�λ��
	        dialog.setLocationRelativeTo(this);	        
	        dialog.setVisible(true);
			
		}
		
		if(actionCommond.equals("choice"))
		{
			String choiceString = (String) cb1.getSelectedItem();
			switch(choiceString) {
			case "���ɲ�ѯ":
				tf1.setForeground(Color.gray); //����ʾ��������Ϊ��ɫ
	            tf1.setText("�밴��˳��ģ����ѯ");     //��ʾ��ʾ����
				break;
			case "������":
				tf1.setForeground(Color.gray); //����ʾ��������Ϊ��ɫ
	            tf1.setText("ģ����ѯ�������š�����");     //��ʾ��ʾ����
				break;
			case "������":
				tf1.setForeground(Color.gray); 
	            tf1.setText("��ȷ��ѯ��������Ϣ��");   
				break;	
			case "�����������ϲ�ѯ":
				tf1.setForeground(Color.gray);
	            tf1.setText("ģ����ѯ,����������Ϣ��"); 
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		//�õ�����ʱ����ǰ�ı������ʾ���ֺʹ����ö���ʱ����ʾ����һ����˵���û���Ҫ��������
		if(tf1.getText().equals("�밴��˳��ģ����ѯ")|tf1.getText().equals("ģ����ѯ�������š�����")|
				tf1.getText().equals("��ȷ��ѯ��������Ϣ��")|tf1.getText().equals("ģ����ѯ,����������Ϣ��")) {
	        tf1.setText("");     //����ʾ�������
	        tf1.setForeground(Color.black);  //�����û������������ɫΪ��ɫ
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
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
		
		String[] options = {"���ɲ�ѯ","������", "������","�����������ϲ�ѯ"};
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
		p1.add(label,BorderLayout.SOUTH);
		
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
		String[] columnNames = {"����", "����", "�绰", "Email","211/985"};
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
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(150);
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, dc);
        
        sp1 = new JScrollPane(table);
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
	
	private ArrayList<Employee> searchEmployeeList(String choiceString,String searchString)
	{
		ArrayList<Employee> seletedemployeeList = new ArrayList<Employee>();
		String regexString = searchString.replaceAll("(.)", "$1.*");//ÿ���ַ��м䶼�������ƥ��
		Pattern pattern = Pattern.compile(regexString);
		String[] tipStrings = {"�밴��˳��ģ����ѯ","ģ����ѯ�������š�����","��ȷ��ѯ��������Ϣ��","ģ����ѯ,����������Ϣ��",""};
		List<String> tipsList = Arrays.asList(tipStrings);
		if (tipsList.contains(searchString))
			{
				seletedemployeeList = employeeList;
			}
		else 
			{
				switch (choiceString) {
				case "���ɲ�ѯ":
					for (Employee employee:employeeList)
					{
						String allString = employee.name+ employee.department+employee.phonenumber+ employee.email+employee.isTop;
						Matcher matcher = pattern.matcher(allString);
						if(matcher.find())
						{
							seletedemployeeList.add(employee);
						}
					}					
					break;
					
				case "������":
					for (Employee employee:employeeList)
					{
						Matcher matcher = pattern.matcher(employee.name);
						if(matcher.find())
						{
							seletedemployeeList.add(employee);
						}
						
					}
					break;
					
				case "������":
					for (Employee employee:employeeList)
					{
						if(employee.department.equals(searchString))
						
						{
							seletedemployeeList.add(employee);
						}
					}
					break;				
				case "�����������ϲ�ѯ":
				for (Employee employee:employeeList)
				{
					String allString = employee.name+ employee.department;
					Matcher matcher = pattern.matcher(allString);
					if(matcher.find())
					{
						seletedemployeeList.add(employee);
					}
				}				
					break;
					
				default:
					break;
				}
			}
		
		return seletedemployeeList;
	}
	
	
	



}
