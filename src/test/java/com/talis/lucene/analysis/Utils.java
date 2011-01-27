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
