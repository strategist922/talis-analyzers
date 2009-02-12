package com.talis.lucene.analysis;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.apache.lucene.analysis.Analyzer;

public class PorterStemmingNormalisedEnNoStopAnalyzerTest extends PorterStemmingNormalisedTestBase {

	public static junit.framework.Test suite()
	{
		return new JUnit4TestAdapter(PorterStemmingNormalisedEnNoStopAnalyzerTest.class);
	}

	@Override
	Analyzer getAnalyzer() {
		return new PorterStemmingNormalisedEnNoStopAnalyzer();
	}

	@Override
	List<String> getStopList() {
		return new ArrayList<String>();
	}	  

}

