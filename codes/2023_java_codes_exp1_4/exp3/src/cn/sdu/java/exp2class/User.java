package cn.sdu.java.exp2class;

public class User {
	EletronicDevice myDevice;
	public User(EletronicDevice myDevice) {
		this.myDevice = myDevice;
	}
	public void watch_time() {
		System.out.println("�û��鿴ʱ��");
		myDevice.get_date();
	}
}
