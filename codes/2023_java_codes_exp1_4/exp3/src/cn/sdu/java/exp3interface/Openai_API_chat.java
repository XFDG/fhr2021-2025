package cn.sdu.java.exp3interface;

public class Openai_API_chat implements Openai_API //ʵ���ӽӿ� ����ӿ�
{
	String INTERFACE_NAME = "openai_api_chat ����ӿ�";
    String FILE_TYPE = "txt";
    public Openai_API_chat()
    {
    	System.out.println("����openAI����ӿ�");
    }
	@Override
	public void APIkey_input(String APIkey) {
		// TODO Auto-generated method stub
		System.out.println("ʹ�õ�apikeyΪ��"+APIkey);
	}

	@Override
	public void APIfunction1() {
		// TODO Auto-generated method stub
		System.out.println("�������칦�ܹ���");
	}

	@Override
	public void APIfunction2() {
		// TODO Auto-generated method stub
		System.out.println("�������ֲ�ȫ����");
	}
}
