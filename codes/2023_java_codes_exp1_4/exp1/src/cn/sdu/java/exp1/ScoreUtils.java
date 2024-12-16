package cn.sdu.java.exp1;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
public class ScoreUtils {
	public static void main(String args[]) {
		Random random = new Random(); //����������Random�����random
		int[] scores = new int[50]; //�������������ųɼ�
		//����ʹ��ArrayList������array����Ϊ�˶�̬���������С���򻯴���
		//����������5���ȼ���arraylist
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		ArrayList<Integer> c = new ArrayList<>();
		ArrayList<Integer> d = new ArrayList<>();
		ArrayList<Integer> e = new ArrayList<>();
		for(int i = 0;i < 50;i++) {
			scores[i] = random.nextInt(51)+50; //��������������ɷ�Χ��50��100֮��ȽϷ���ʵ�������
		}
		Arrays.sort(scores); //����api����
		int level = 0;//����ȼ�
		for(int i = 49;i >= 0;i--) { //������ȡ�ɼ���ת��Ϊ�ȼ�
			level = (scores[i]-1)/10; //ע�⣺������Χ���±߽�Ϊ�����䣬����Ҫ-1��֤ȡ������
			switch (level) { //�����жϵȼ������ʹ��switch case�ṹ��ʵ�������岻��
			case 9:
				a.add(scores[i]); //ʹ��add������̬����ӳɼ�
				break;
			case 8:
				b.add(scores[i]);
				break;
			case 7:
				c.add(scores[i]);
				break;
				
			case 6:
				d.add(scores[i]);
				break;
			default:
				e.add(scores[i]);
				break;
			}
		}
		//��ӡ
		System.out.println("����������������������³ɼ�������������");
		System.out.print(a);
		System.out.print("\n");
		System.out.print("��ֵΪ��");
		System.out.printf("%.1f", calculateMean(a));
		System.out.print("\n");
		System.out.println("���������������õ������³ɼ�������������");
		System.out.print(b);
		System.out.print("\n");
		System.out.print("��ֵΪ��");
		System.out.printf("%.1f", calculateMean(b));
		System.out.print("\n");
		System.out.println("������������һ��������³ɼ�������������");
		System.out.print(c);
		System.out.print("\n");
		System.out.print("��ֵΪ��");
		System.out.printf("%.1f", calculateMean(c));
		System.out.print("\n");
		System.out.println("����������������������³ɼ�������������");
		System.out.print(d);
		System.out.print("\n");
		System.out.print("��ֵΪ��");
		System.out.printf("%.1f", calculateMean(d));
		System.out.print("\n");
		System.out.println("������������������������³ɼ�������������");
		System.out.print(e);
		System.out.print("\n");
		System.out.print("��ֵΪ��");
		System.out.printf("%.1f", calculateMean(e));
		
		System.out.print("\n");
		random_select(scores);
	}
	
	//����ƽ��ֵ������ArrayList���ؾ�ֵ
	public static double calculateMean(ArrayList<Integer> arrayList) {
        int sum = 0;
        for (Integer element : arrayList) {
            sum += element;
        }
        return (double) sum / arrayList.size();
    }
	//�����ѡ����ӡ
	public static void random_select(int[] array) {
		Random random = new Random();
		int[] indexs = new int[10];
		for(int i = 0;i < 10;i++) {
			indexs[i] = random.nextInt(50);
		}
		System.out.println("�����������������ȡ�ɼ����¡�����������");
		for(int i = 0;i < indexs.length;i++) {
			System.out.print(array[indexs[i]]+" ");
		}
	}
	
}
