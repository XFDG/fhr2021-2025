package cn.sdu.java.exp2class;
public class Test {
    // ��ȡ��ǰ���ں�ʱ��
	 public static void main(String[] args) {
		 EletronicDevice eletronicDevice = new EletronicDevice("�ҵ��豸");
		 System.out.println("�������������������ء�����������");
		 eletronicDevice.charge(); //���غ���charge
		 eletronicDevice.charge(4);
		 SmartWatch smartWatch = new SmartWatch("�ҵ��ֱ�");
		 System.out.println("������������������д������������");
		 eletronicDevice.get_date();//��д����get_date
		 smartWatch.get_date(); 
		 System.out.println("�������������������ء�����������");
		 smartWatch.charge();
		 smartWatch.charge(6);
		 System.out.println("���������������Ͷ�̬������������");
		 User meUser = new User(smartWatch);
		 meUser.watch_time();
	    }
}

