package com.crossbridge.engineloader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EngineLoaderOnApp extends EngineLoader{

	protected EngineLoaderOnApp(String serverUrl, String targetPath) {
		super(serverUrl, targetPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String queryEngine(String version) {
		String urlQuery = serverUrl+"forward.static/downengine?"+"version="+version;
		
		 HttpURLConnection connection=null;
		 String location="";
		 URL url;
		 
		 System.out.println("url path: "+ urlQuery);
		 
		   try {
				url = new URL(urlQuery);
			
	          // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
	          connection = (HttpURLConnection) url.openConnection();
	          // 设置连接方式：get
	          connection.setRequestMethod("GET");
	          // 设置连接主机服务器的超时时间：15000毫秒
	          connection.setConnectTimeout(15000);
	          // 设置读取远程返回的数据时间：60000毫秒
	          connection.setReadTimeout(60000);
	          // 发送请求
	          connection.setInstanceFollowRedirects(false);
	          connection.connect();
	          // 通过connection连接，获取输入流
	         
	          System.out.println("ResponseCode: "+connection.getResponseCode());
	          if (connection.getResponseCode() == 302) {
	  			 location = connection.getHeaderField("Location");
	  			 System.out.println("Location:"+location);
	              
	          }
	          //location = connection.getHeaderField("Location");
		   } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(connection!=null) {
					connection.disconnect();
				}
			}
          
          return location;
	
	}
}
