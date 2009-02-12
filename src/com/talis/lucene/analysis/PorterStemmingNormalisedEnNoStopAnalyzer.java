package com.talis.lucene.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ISOLatin1AccentFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.PorterStemFilter;

import java.io.Reader;

public class PorterStemmingNormalisedEnNoStopAnalyzer extends Analyzer {

	@Override
	public TokenStream tokenStream(String arg0, Reader reader) {
		   return new PorterStemFilter(
	        		new ISOLatin1AccentFilter(
	        			new LowerCaseTokenizer(reader)));
	}
}
