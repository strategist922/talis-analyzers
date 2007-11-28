package com.talis.lucene.analysis.normen;

import java.io.Reader;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class NormaliseStandardAnalyzer extends Analyzer  {

	public NormaliseStandardAnalyzer() {
	}

	@Override
	public TokenStream tokenStream(String arg0, Reader reader) {
		TokenStream result = new StandardTokenizer(reader);
		result = new StandardFilter(result);
		result = new LowerCaseFilter(result);
		result = new ISOLatin1AccentFilter(result);
		return result;
	}

}
