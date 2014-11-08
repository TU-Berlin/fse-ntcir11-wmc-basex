package com.formulasearchengine.backend.basex;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ServerTest extends TestCase {
	static public String getFileContents (String fname) throws IOException {
		try (InputStream is = ServerTest.class.getClassLoader().getResourceAsStream(fname)) {
			final Scanner s = new Scanner(is, "UTF-8");
			//Stupid scanner tricks to read the entire file as one token
			s.useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
	}
	@Test
	public void testImportData () throws Exception {
		final String file = this.getClass().getClassLoader().getResource( "sampleHarvest.xml" ).getPath();
		String fcontent = getFileContents( "sampleHarvest.xml" );
		Server srv = new Server();
		srv.importData( fcontent );
		System.out.println(file);

	}
	//Depends on testImportData
	@Test
	public void testQuery () throws Exception {
		String fcontent = getFileContents( "sampleHarvest.xml" );
		Server srv = new Server();
		srv.importData( fcontent );
		srv.runQuery( "count(./*/*)", System.out );
	}

}