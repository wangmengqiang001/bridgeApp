package com.crossbridge.engineloader;

public class EngineLoaderOnServer extends EngineLoader {

	protected EngineLoaderOnServer(String serverUrl, String targetPath) {
		super(serverUrl, targetPath);
		// TODO Auto-generated constructor stub
		//initialize the map to query 
	}

	@Override
	protected String queryEngine(String version) {
		
		String enginePath="";	
		
		if("2.0.0".equals(version)){
			enginePath = serverUrl+OSGI_ENGINE+"/"+"osgi-engine-3.12.100-1.0.0-SNAPSHOT.zip";
			
		}else {
			enginePath = serverUrl+OSGI_ENGINE+"/"+"osgi-engine-3.5.1-1.0.0.zip";
		}
		return enginePath;
	}
}
