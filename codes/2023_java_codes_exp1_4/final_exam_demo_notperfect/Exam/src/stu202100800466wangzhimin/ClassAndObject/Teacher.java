package stu202100800466wangzhimin.ClassAndObject;

public class Teacher extends Person
{

	Teacher(String ID, String name) {
		super(ID, name);
		// TODO Auto-generated constructor stub
	}

	public  void getScore(String subject) //��д
	{
		System.out.println("��"+subject+"������"+"��"+name+"���༶ƽ���ɼ�ΪXX��");
	};
	
	public void getScore(Student student) //����
	{
		System.out.println("��"+name+"����ʦ�Ŀ��ԡ�"+student.name+"��ͬѧ�ĳɼ�ΪXX��");
	}
}
