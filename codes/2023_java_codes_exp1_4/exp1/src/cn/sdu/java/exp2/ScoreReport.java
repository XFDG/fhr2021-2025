package cn.sdu.java.exp2;

import java.util.ArrayList;

public class ScoreReport {
	public static void main(String[] args) {
		ScoreLoader sl = new ScoreLoader();
		String fileLocation="bin/file/student_score.txt";
		
		//ʾ��1
		ScoreLoader.readfile(fileLocation);

		//ʾ��2
//		ArrayList<String> scoreList = sl.readScore(fileLocation);
//		ѭ������scoreList
//		int studentAmount=scoreList.size();
//		for (int i=0;i<studentAmount;i++) {
//			String s = scoreList.get(i);
//		}
//		for( String score : scoreList) {	//��һ��ѭ����ʽ
//			
//		}

		//ʾ��3
//		ArrayList<Student> stuList = sl.readStudentInfo(fileLocation);
////		ѭ������stuList
//		for( Student stu : stuList) {	//��һ��ѭ����ʽ
//			String name = stu.name;
//			int score = stu.score;
//			System.out.println("������"+name+",�ɼ���"+score);
////			System.out.println("������"+stu.name+",�ɼ���"+stu.score);
//		}
	}
}
