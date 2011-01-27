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

import java.io.Reader;

import org.apache.lucene.analysis.KeywordAnalyzer;
import org.apache.lucene.analysis.KeywordTokenizer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;

public class StopWordKeywordAnalyzer extends KeywordAnalyzer {

	private static String[] STOP_WORDS = StopAnalyzer.ENGLISH_STOP_WORDS;
	
	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		TokenStream result = new KeywordTokenizer(reader);
        result = new StopFilter(result, StopFilter.makeStopSet(STOP_WORDS), true);
        return result;
	}
	
}
