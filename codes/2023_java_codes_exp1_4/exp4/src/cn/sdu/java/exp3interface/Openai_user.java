package cn.sdu.java.exp3interface;



public class Openai_user { //�����û��࣬ģ����ýӿڵĹ���

	public static void main(String[] args) {
		Openai_user user = new Openai_user();
		Openai_API_audio audio_api = new Openai_API_audio(); //������Ƶ����ӿ�
		
		System.out.println("�ӿ�����"+audio_api.INTERFACE_NAME);
		System.out.println("���ܵ��ļ����ͣ�"+audio_api.FILE_TYPE);
		user.API_key_input(audio_api, "123456");
		user.use_APIfuction(audio_api);
		System.out.println("----------------");
		
		Openai_API_chat chat_api = new Openai_API_chat(); //�������ִ���ӿ�
		System.out.println("�ӿ�����"+chat_api.INTERFACE_NAME);
		System.out.println("���ܵ��ļ����ͣ�"+chat_api.FILE_TYPE);
		user.API_key_input(chat_api, "456789");
		user.use_APIfuction(chat_api);
		
	}
	
	public void API_key_input(Openai_API api,String apikey)//�û�����openAIAPIkey
	{
		api.APIkey_input(apikey);
		
	}
	
	public void use_APIfuction(Openai_API api) //�û�����API����
	{
		api.APIfunction1();
		api.APIfunction2();
	}
}
