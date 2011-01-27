/*
 *    Copyright 2011 Talis Systems Ltd
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.talis.lucene.analysis;

import static com.talis.lucene.analysis.Utils.assertAnalyzesTo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.junit.Test;

public abstract class PorterStemmingNormalisedTestBase {

	  abstract Analyzer getAnalyzer();
	  abstract List<String> getStopList();
	  
	  @Test
	  public void testStemming() throws Exception{
		  List<String> stopList = getStopList();
		  Analyzer analyzer = getAnalyzer();
		  
		  File input = new File("./src/test/resources/data/porter-stemming-input.txt");
		  File output = new File("./src/test/resources/data/porter-stemming-output.txt");
		  
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

}
