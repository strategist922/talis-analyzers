package com.talis.lucene.analysis;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class Utils {

	public static void assertAnalyzesTo(Analyzer a, String input, String... expected) throws IOException  {
		TokenStream ts = a.tokenStream("dummy", new StringReader(input));
		for (int i = 0; i < expected.length; i++) {
			Token t = ts.next();
			assertNotNull(t);
			assertEquals(expected[i], t.termText());
		}
		assertNull(ts.next());
		ts.close();
	}
	  
	public static void assertStopWord(Analyzer a, String input) throws IOException {
		TokenStream ts = a.tokenStream("dummy", new StringReader(input));
		assertNull(ts.next());
		ts.close();
	}
	
}
