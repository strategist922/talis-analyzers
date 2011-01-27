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

/*
 * Copyright (c) 2006 Talis Information Ltd.
 *
 * This software is the confidential and proprietary information of Talis
 * Information Ltd. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Talis.
 *
 * http://www.talis.com
 * $Id: $
 */

package com.talis.lucene.analysis;

import static com.talis.lucene.analysis.Utils.assertAnalyzesTo;
import static com.talis.lucene.analysis.Utils.assertStopWord;
import junit.framework.JUnit4TestAdapter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.junit.Test;

public class StopWordKeywordAnalyzerTest {

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(StopWordKeywordAnalyzerTest.class);
	}
	
	@Test
	public void testStopWordKeywordAnalyzer() throws Exception {
		Analyzer a = new StopWordKeywordAnalyzer();

		assertAnalyzesTo (a, "AbCdE", new String[]{"AbCdE"});
		assertAnalyzesTo (a, "AbCdE and Hello", new String[]{"AbCdE and Hello"});
		assertAnalyzesTo (a, "http://www.example.org/foo#bar", new String[]{"http://www.example.org/foo#bar"});
		assertAnalyzesTo (a, "http://www.w3.org/1999/02/22-rdf-syntax-ns#type", new String[]{"http://www.w3.org/1999/02/22-rdf-syntax-ns#type"});

		for (String stopWord : StopAnalyzer.ENGLISH_STOP_WORDS) {
			assertStopWord (a, stopWord);
		}
	}

}
