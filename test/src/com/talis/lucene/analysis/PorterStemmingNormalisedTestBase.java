package com.talis.lucene.analysis;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;

public abstract class PorterStemmingNormalisedTestBase {

	  abstract Analyzer getAnalyzer();
	  abstract List<String> getStopList();
	  
	  @Test
	  public void testStemming() throws Exception{
		  List<String> stopList = getStopList();
		  Analyzer analyzer = getAnalyzer();
		  
		  File input = new File("./test/data/porter-stemming-input.txt");
		  File output = new File("./test/data/porter-stemming-output.txt");
		  
		  BufferedReader inputReader = new BufferedReader(
				  							new InputStreamReader(
				  								new FileInputStream(input)));
		  BufferedReader outputReader = new BufferedReader(
				  							new InputStreamReader(
				  								new FileInputStream(output)));
		  String in = null;
		  while ((in = inputReader.readLine()) != null){
			  String expected = outputReader.readLine();
			  if (stopList.contains(in)){
				  continue;
			  }
			  assertAnalyzesTo(analyzer, in, expected);
		  }
	  }
	  
	  @Test
	  public void testPossesives() throws Exception{
		  String in = "l'ecole";
		  assertAnalyzesTo(getAnalyzer(), in, "l", "ecol");
	  }
	  
	  public void assertAnalyzesTo(Analyzer a, String input, String... expected) throws Exception {
		    TokenStream ts = a.tokenStream("dummy", new StringReader(input));
		    for (int i = 0; i < expected.length; i++) {
		      Token t = ts.next();
		      assertNotNull(t);
		      assertEquals(expected[i], t.termText());
		    }
		    assertNull(ts.next());
		    ts.close();
	  }
	
}
