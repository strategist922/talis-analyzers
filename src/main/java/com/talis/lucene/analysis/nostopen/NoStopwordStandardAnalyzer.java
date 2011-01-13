package com.talis.lucene.analysis.nostopen;

import java.io.Reader;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class NoStopwordStandardAnalyzer extends Analyzer  {

	public NoStopwordStandardAnalyzer() {
	}

	@Override
	public TokenStream tokenStream(String arg0, Reader reader) {
		TokenStream result = new StandardTokenizer(reader);
		result = new StandardFilter(result);
		result = new LowerCaseFilter(result);
		return result;
	}

}
