package com.crossbridge.engineloader;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

public class EngineLoaderTest extends TestCase {
	//fail("Not yet implemented");
	String serverUrl="";
	String targetPath="";
	
	public void setUp() {
		serverUrl = "http://localhost:8990/";
		targetPath = System.getProperty("java.io.tmpdir");
		System.out.println("tmpdir: "+targetPath);
	}
	public void tearDown() {
		//clear the foldeer
	}

	public void testLocateEngine() {
	
		EngineLoader loader = EngineLoader.createLoader("APP",serverUrl,targetPath);
		
		String version="";
		String filePath = loader.queryEngine( version);
		
		assertNotNull(filePath);
		final String expectedValue_1="http://localhost:8990/osgi_engine/osgi-engine-3.5.1-1.0.0.zip" ;
		assertEquals(filePath,expectedValue_1);
		
		 filePath = loader.queryEngine( "2.0.0");
		final String expectedValue_2="http://localhost:8990/osgi_engine/osgi-engine-3.12.100-1.0.0-SNAPSHOT.zip" ;
		assertEquals(filePath,expectedValue_2);
		
		
	}
	public void testParsePath() {
		
		EngineLoader loader =EngineLoader.createLoader("APP",serverUrl,targetPath);
		String path = serverUrl+"osgi-engine/osgi.zip";
		String name = loader.parseFileName(path);
		assertEquals(name,"osgi.zip");
		
		String yypath ="http://localhost:8880/forward.static/xxy-3.5.1-1.0.0.txt";
		name = loader.parseFileName(yypath);
		assertEquals(name,"xxy-3.5.1-1.0.0.txt");
		
	}
	public void testDownload() {
		EngineLoader loader =EngineLoader.createLoader("APP",serverUrl,targetPath);
		
		String path = serverUrl + "osgi_engine/osgi-engine-3.5.1-1.0.0.zip";
		try {
			String filePath = loader.downloadEngine(targetPath, path);
			
			File down = new File(filePath);
			System.out.println("download file:"+filePath);
			assertTrue(down.exists());
			if(down.exists()) {
				down.delete();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void testLoadEngine() {
		EngineLoader loader =EngineLoader.createLoader("APP",serverUrl,targetPath);
		
		String version="1.0.0";
		String filePath=null;
		try {
			filePath = loader.loadEngine(version);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(filePath);
		assertFalse(filePath.isEmpty());
		
		File down = new File(filePath);
		System.out.println("download file:"+filePath);
		assertTrue(down.exists());
		if(down.exists()) {
			down.delete();
		}
		
		try {
			filePath = loader.loadEngine("2.0.0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(filePath);
		assertFalse(filePath.isEmpty());
		
		down = new File(filePath);
		System.out.println("download file:"+filePath);
		assertTrue(down.exists());
		if(down.exists()) {
			down.delete();
		}		
		
	}
	public void testloadEngineByServer() {
		EngineLoader loader =EngineLoader.createLoader("SERVER",serverUrl,targetPath);
		
		String version="1.0.0";
		String filePath=null;
		try {
			filePath = loader.loadEngine(version);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(filePath);
		assertFalse(filePath.isEmpty());
		
		File down = new File(filePath);
		System.out.println("download file:"+filePath);
		assertTrue(down.exists());
		if(down.exists()) {
			down.delete();
		}
		
		try {
			filePath = loader.loadEngine("2.0.0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(filePath);
		assertFalse(filePath.isEmpty());
		
		down = new File(filePath);
		System.out.println("download file:"+filePath);
		assertTrue(down.exists());
		if(down.exists()) {
			down.delete();
		}		
	}
	public void testqueryEngineByServer() {
		EngineLoader loader =EngineLoader.createLoader("SERVER",serverUrl,targetPath);
		String engine_1=loader.queryEngine("1.0.0");
		assertNotNull(engine_1);
		assertFalse(engine_1.isEmpty());
		assertTrue(engine_1.contains("3.5.1"));
		
		String engine_2=loader.queryEngine("2.0.0");
		assertNotNull(engine_2);
		assertFalse(engine_2.isEmpty());
		assertTrue(engine_2.contains("3.12.100"));
		
		
		
		
	}

}
