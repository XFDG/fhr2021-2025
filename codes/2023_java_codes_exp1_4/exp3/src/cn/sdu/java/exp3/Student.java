package cn.sdu.java.exp3;

public class Student implements Comparable<Student> //ʵ���ڲ��Ƚ����ӿ�
{
	String stuID;
	String classString; //����������Ϣ һ�������ڰ༶ һ����ѧ��
	String name;
	int score;
	
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		int result = this.score < o.score ? 1 : (this.score == o.score ? 0 : -1);   
        return result;

	}
}
