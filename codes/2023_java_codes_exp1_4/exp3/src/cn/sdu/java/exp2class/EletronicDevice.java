package cn.sdu.java.exp2class;

public class EletronicDevice {
	String name;
	static {
//		System.out.println("�����豸��̬��");
	}
	
	public EletronicDevice(String name)
	{
		this.name = name; //�豸��
	}
	
	public void get_date() {
		System.out.println("�豸����"+this.name+" ��Ǹ,�����ṩʱ��");
	}
	
	public void charge() {
		System.out.println("���ڳ�硣����");
	}
	
	public int charge(int time) { //���غ���charge
		System.out.println("���ڶ�ʱ��硣����Ԥ��"+time+"Сʱ������ϡ�");
		return time;
	}
}
