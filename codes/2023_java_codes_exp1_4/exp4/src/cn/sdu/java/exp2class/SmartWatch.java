package cn.sdu.java.exp2class;
import java.util.Date;
import java.text.*;

public class SmartWatch extends EletronicDevice{
	public SmartWatch(String name) { //���Ͷ�̬
		super(name);
		System.out.println(name);
	}
	static {
//		System.out.println("�����ֱ�̬��");
	}
	
	public void get_date() { //��дget_date����
		Date date = new Date();
        
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			 
		System.out.println("�豸����"+this.name+": " + ft.format(date));
	}
	


	
}
