package cn.sdu.java.exp2;
import java.util.HashMap;
import java.util.ArrayList;

public class ScoreReport {
	 public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
		String fileLocation="bin/file/student_score.txt";
		
		//ʾ��1
//		ScoreLoader.readfile(fileLocation);

		//ʾ��2
//		ArrayList<String> scoreList = sl.readScore(fileLocation);
////		ѭ������scoreList
//		int studentAmount=scoreList.size();
//		for (int i=0;i<studentAmount;i++) {
//			String s = scoreList.get(i);
//			System.out.println(s);
//		}
//		for( String score : scoreList) {	//��һ��ѭ����ʽ
//			
//		}

//		//ʾ��3
//		ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);
////		ѭ������stuList
//		for( Student stu : stuList) {	//��һ��ѭ����ʽ
//			String name = stu.name;
//			int score = stu.score;
//			System.out.println("������"+name+",�ɼ���"+score);
////			System.out.println("������"+stu.name+",�ɼ���"+stu.score);
//	 }
		ArrayList<Student> stuList = sl.readStudentInfo(fileLocation); //��ȡ�ļ���ŵ�arraylist��
		
		ScoreUtils scoreUtils = new ScoreUtils(); //ScoreUtils�з�װ���õĵĳɼ�������
		
		System.out.println("�������������༶�����Ͳ���������������������");
		HashMap<String, Integer> classnum  = scoreUtils.get_classnum(stuList);
		for(String classname:classnum.keySet()) //��ӡ���ص�hashmap�ļ�ֵ��
		{
			Integer numInteger = classnum.get(classname);
			System.out.println("�༶��"+classname+" ������"+numInteger);
		}
		scoreUtils.getfailnum(stuList);
		int fail_num = scoreUtils.getfailnum(stuList); //���õ�һ����������ȡ����������
		System.out.println("������������"+fail_num);
		
		
		
		System.out.println("������������ѧ�ſ�ͷ�����ϡ�����������");
		ArrayList<Student> improper_stuList =  scoreUtils.get_improper_header(stuList, "2019008");
		scoreUtils.print_id_name(improper_stuList);
		
		
		System.out.println("�������������滻�����鿴������������");
		scoreUtils.change_improper_part(stuList, "008", "111");
	}
	
	 
	
	
	
}