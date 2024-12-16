package cn.sdu.java.exp4thread;

public class HandingGoods {
	public static void main(String args[])
	{
		Goods goods = new Goods(20);
		Worker worker1 = new Worker(goods, "����");
		Worker worker2 = new Worker(goods, "����");
		Worker worker3 = new Worker(goods, "����");
		worker1.start(); //�����߳�
		worker2.start();
		worker3.start();
		try {
			worker1.join(); //����߳��Ƿ������
			worker2.join();
			worker3.join();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("----��ȫ������----");
	}
}


class Worker extends Thread{

	private Goods goods;
	public Worker(Goods goods,String name)
	{
		super(name);
		this.goods = goods;
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			while(goods.goods_num > 0 )
			{
				synchronized (goods) { //ͬ�����̣߳�ȷ��һ��ֻ��һ���߳��ܹ����ʸ÷���
				if(goods.goods_num>0) {
					
					goods.handing();
					}
				}
				try {
					Thread.sleep(100); //������Ҫʱ��
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
				}
			}

	}

}

class Goods{
	public int goods_num;
	public Goods(int goods_num) {
		this.goods_num = goods_num;
	}
	
	public void handing() {
		System.out.println("���� " + Thread.currentThread().getName() + " ���ڰ�����Ʒ");
		goods_num--;
		System.out.println("�ֿ��л�ʣ�� " + goods_num + " ����Ʒ");
		
	}
}