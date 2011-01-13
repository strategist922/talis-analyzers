package com.talis.lucene.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ISOLatin1AccentFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.Reader;

public class PorterStemmingNormalisedEnAnalyzer extends Analyzer {

	@Override
	public TokenStream tokenStream(String arg0, Reader reader) {
		   return new PorterStemFilter(
	        		new ISOLatin1AccentFilter(
	        			new StopFilter(new LowerCaseTokenizer(reader),
	        							StandardAnalyzer.STOP_WORDS)));
	}
}
