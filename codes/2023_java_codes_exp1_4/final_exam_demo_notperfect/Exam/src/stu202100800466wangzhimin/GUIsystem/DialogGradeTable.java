package stu202100800466wangzhimin.GUIsystem;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DialogGradeTable extends JDialog{
	 JButton jb;
	 JTable table;
	 String cal;
	 DefaultTableModel model;
	 JScrollPane sp1;
	 DialogGradeTable(JFrame f,String cal){
	 super(f,cal);
	 this.setSize(200,100);
		String[] columnNames = {"����", "ѧ��", "����"};
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 }
}
