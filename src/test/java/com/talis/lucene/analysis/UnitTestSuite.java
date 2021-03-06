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

import junit.framework.Test;
import junit.framework.TestSuite;


import com.talis.lucene.analysis.normen.NormaliseStandardAnalyzerTest;
import com.talis.lucene.analysis.nostopen.NoStopwordStandardAnalyzerTest;

/**
 *  Synchronous unit tests
 */
public class UnitTestSuite {

  /**
   *  The main program for the UnitTestSuite class
   */
  public static void main( String[] args ) {
    junit.textui.TestRunner.run( suite() );
  }
	

  /**
   *  A suite of unit tests for JUnit
   */
  public static Test suite() {
    TestSuite suite = new TestSuite( "All Unit Tests for com.talis.lucene.analysis" );
    
    suite.addTest( NoStopwordStandardAnalyzerTest.suite() );
    suite.addTest( NormaliseStandardAnalyzerTest.suite() );
    suite.addTest( PorterStemmingNormalisedEnAnalyzerTest.suite() );
    suite.addTest( PorterStemmingNormalisedEnNoStopAnalyzerTest.suite() );
    suite.addTest( StopWordKeywordAnalyzerTest.suite() );

    return suite;
    
  }

}
