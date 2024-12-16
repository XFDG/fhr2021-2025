package cn.sdu.java.exp2;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreUtils {
	public void print_id_name(ArrayList<Student> stuList) { //��װһ��������ӡarraylist�ķ���
		for( Student stu : stuList) {

			System.out.println("������"+stu.name+",�ɼ���"+stu.score);
		}
	}
	
	public int getnum(ArrayList<Student> stuList) {
		return stuList.size();
		
	}
	
	public HashMap<String, Integer> get_classnum(ArrayList<Student> stuList) //ʹ��hashmap��ֵ�Խṹ����Ų����������༶������
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
	
	public int getfailnum(ArrayList<Student> stuList) { //���������������ķ���
		int fail_num = 0;
		for(Student stu :stuList) { //�����жϼ���
			if(stu.score < 60) {
				fail_num++;
			}
		}
		return fail_num;
	}
	

	public ArrayList<Student> get_improper_header(ArrayList<Student> stuList,String ID_header) { 
		//��ѯ��ͷ������ָ���ַ�����ѧ����Ϣ
		ArrayList<Student> improper_stuList = new ArrayList<>(); //��ʼ��һ��ArrayList��Ų����ϵ���Ϣ
		int improper_num = 0; //���ڼ���
		for(Student stu:stuList) {
			if(!stu.stuID.contains(ID_header)) { //����string���contain�������ָ���ַ����Ƿ������Ŀ���ַ�����
				improper_stuList.add(stu);
				improper_num++;
			}
		}
		System.out.println("��ʽ������"+ID_header+"��ͷ����"+improper_num+"����");
		return improper_stuList; //����ArrayList���������ӡ
	}
	
	public void change_improper_part(ArrayList<Student> stuList,
			String queryString,String newString) {
//		�滻ָ�����ֵķ���
		int improper_num = 0;
		for(Student stu:stuList) {
			if(stu.stuID.contains(queryString)) { //�Ȳ��ҵ�
				improper_num++;
				stu.stuID.replace(queryString, newString); //���滻����ͬ������string���еķ���replace
			}
		}
		System.out.println("�滻��"+improper_num+"����");
		
	}
}
