package com.talis.lucene.analysis;

import java.util.Arrays;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;

public class PorterStemmingNormalisedEnAnalyzerTest extends PorterStemmingNormalisedTestBase {

	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(PorterStemmingNormalisedEnAnalyzerTest.class);
	}

	@Override
	Analyzer getAnalyzer() {
		return new PorterStemmingNormalisedEnAnalyzer();
	}

	@Override
	List<String> getStopList() {
		return Arrays.asList(StopAnalyzer.ENGLISH_STOP_WORDS);
	}	  

}
