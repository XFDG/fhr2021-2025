package stu202100800466wangzhimin.ClassAndObject;

public class Student extends Person
{

	Student(String ID, String name) {
		super(ID, name);
		// TODO Auto-generated constructor stub
	}

	public  void getScore(String subject) //��д
	{
		System.out.println("��"+subject+"�����ԡ�"+name+"��ͬѧ�ĳɼ�ΪXX��");
	};
}
