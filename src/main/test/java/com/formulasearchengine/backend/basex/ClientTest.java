package com.formulasearchengine.backend.basex;

import junit.framework.TestCase;
import org.junit.Test;

public class ClientTest extends TestCase {
	@Test
	public void testbasicTest() throws Exception {
		(new ServerTest()).testImportData();
		Client.basex( 			"declare default element namespace \"http://www.w3.org/1998/Math/MathML\";\n" +
			"for $m in //*:expr return \n" +
			"for $x in $m//*:apply\n" +
			"[*[1]/name() = 'divide']\n" +
			"where\n" +
			"fn:count($x/*) = 3\n" +
			"return\n" +
			"<a href=\"http://demo.formulasearchengine.com/index.php?curid={$m/@url}\">result</a>" );
		assertEquals(1,1);
	}


}