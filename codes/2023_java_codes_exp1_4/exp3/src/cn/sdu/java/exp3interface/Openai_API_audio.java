package cn.sdu.java.exp3interface;

public class Openai_API_audio implements Openai_API //ʵ���ӽӿ� ��Ƶ�ӿ�
{
	String INTERFACE_NAME = "openai_api_audio ��Ƶ�ӿ�";
    String FILE_TYPE = "mp3/opus/aac/flac";
    public Openai_API_audio()
    {
    	System.out.println("����openAI��Ƶ�ӿ�");
    }
	@Override
	public void APIkey_input(String APIkey) {
		// TODO Auto-generated method stub
		System.out.println("ʹ�õ�apikeyΪ��"+APIkey);
		
	}

	@Override
	public void APIfunction1() {
		// TODO Auto-generated method stub
		System.out.println("������Ƶת���ֹ���");
	}

	@Override
	public void APIfunction2() {
		// TODO Auto-generated method stub
		System.out.println("������Ƶ���빦��");
	}

}
