package stu202100800466wangzhimin.ClassAndObject;

public class Test {
	public static void main(String[] args) {
		
		Student stu=new Student("202100800466","������");
		Teacher tea=new Teacher("201911100321","����");
		
		Exam e1=new Exam("Java",stu);
		e1.getScore();
		Exam e2=new Exam("��ѧ",tea);
		e2.getScore();
		tea.getScore(stu);
		
	}
}