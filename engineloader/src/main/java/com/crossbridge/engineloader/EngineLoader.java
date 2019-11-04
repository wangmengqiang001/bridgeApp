package com.crossbridge.engineloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class EngineLoader {
	
	public static final String SERVER="SERVER";
	public static final String WEBAPP="APP"; 
	protected final String OSGI_ENGINE="osgi_engine";
	protected String  serverUrl;
	public EngineLoader(String serverUrl, String targetPath) {
		super();
		this.serverUrl = serverUrl;
		this.targetPath = targetPath;
		
	}
	protected String  targetPath;
	protected final int BUFFER_SIZE=4096;
		
	public static EngineLoader createLoader(String type,String serverUrl, String targetPath){
		if(SERVER.equals(type)) {
			return new EngineLoaderOnServer(serverUrl,targetPath);
		}else if(WEBAPP.equals(type)) {
			return new EngineLoaderOnApp(serverUrl,targetPath);
		}
		
		return null;
	}
	public String loadEngine(String version) throws IOException {
		String enginePath = this.queryEngine(version);
		if(enginePath !=null && !enginePath.isEmpty()) {
			return this.downloadEngine(targetPath, enginePath);
		}
		return "";
		
	}
	
	
	protected abstract String queryEngine(String version);
	
	protected String downloadEngine(String targetPath,String path) throws IOException {
		
		URL osgiEngine = new URL(path);

		InputStream is = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			is = osgiEngine.openStream();

			String fullClientPath;
			String fileName=parseFileName(path);
			fullClientPath=targetPath +fileName ;
			file = new File(fullClientPath);
			if (!file.exists()) {
				boolean success = file.createNewFile();
				if (!success)
					throw new IOException("Creating new file [" + file
							+ "] failed.");
			}

			int bytesRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];

			fos = new FileOutputStream(file);
			while ((bytesRead = is.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}
			fos.flush();
			return fullClientPath;
		} finally {
			// 流的关闭操作应该放在finally中，modified by zhuwei at 20131024
			if (is != null) {
				is.close();
			}

			if (fos != null) {
				fos.close();
			}
		}
	}
	protected String parseFileName(String path) {
		 String file=path.substring(path.lastIndexOf('/')+1);
		return file;
	}
	

}
