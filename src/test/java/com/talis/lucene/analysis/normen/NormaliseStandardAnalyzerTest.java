package com.talis.lucene.analysis.normen;

import static com.talis.lucene.analysis.Utils.assertAnalyzesTo;
import junit.framework.JUnit4TestAdapter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.junit.Test;

public class NormaliseStandardAnalyzerTest {

	  public static junit.framework.Test suite()
	  {
	    return new JUnit4TestAdapter(NormaliseStandardAnalyzerTest.class);
	  }

	  @Test
	  public void testNormalisedLatinAccentword() throws Exception {
	    Analyzer a = new NormaliseStandardAnalyzer();

	    // alphanumeric tokens
	    assertAnalyzesTo(a, "B2B", new String[]{"b2b"});
	    assertAnalyzesTo(a, "2B", new String[]{"2b"});

	    // underscores are delimiters, but not in email addresses (below)
	    assertAnalyzesTo(a, "word_having_underscore", new String[]{"word", "having", "underscore"});
	    assertAnalyzesTo(a, "word_with_underscore_and_stopwords", new String[]{"word", "with", "underscore", "and", "stopwords"});

	    // other delimiters: "-", "/", ","
	    assertAnalyzesTo(a, "some-dashed-phrase",   new String[]{"some", "dashed", "phrase" });
	    assertAnalyzesTo(a, "dogs,chase,cats", new String[]{"dogs", "chase", "cats"});
	    assertAnalyzesTo(a, "ac/dc", new String[]{"ac", "dc"});

	    // internal apostrophes: O'Reilly, you're, O'Reilly's
	    // possessives are actually removed by StardardFilter, not the tokenizer
	    assertAnalyzesTo(a, "O'Reilly", new String[]{"o'reilly"});
	    assertAnalyzesTo(a, "you're", new String[]{"you're"});
	    assertAnalyzesTo(a, "she's", new String[]{"she"});
	    assertAnalyzesTo(a, "Jim's", new String[]{"jim"});
	    assertAnalyzesTo(a, "don't", new String[]{"don't"});
	    assertAnalyzesTo(a, "O'Reilly's", new String[]{"o'reilly"});

	    // t and s had been stopwords in Lucene <= 2.0, which made it impossible
	    // to correctly search for these terms:
	    assertAnalyzesTo(a, "s-class", new String[]{"s", "class"});
	    assertAnalyzesTo(a, "t-com", new String[]{"t", "com"});
	    // 'a' is still a stopword:
	    assertAnalyzesTo(a, "a-class", new String[]{"a", "class"});

	    // company names
	    assertAnalyzesTo(a, "AT&T", new String[]{"at&t"});
	    assertAnalyzesTo(a, "Excite@Home", new String[]{"excite@home"});

	    // domain names
	    assertAnalyzesTo(a, "www.nutch.org",   new String[]{"www.nutch.org" });

	    // email addresses, possibly with underscores, periods, etc
	    assertAnalyzesTo(a, "test@example.com", new String[]{"test@example.com"});
	    assertAnalyzesTo(a, "first.lastname@example.com", new String[]{"first.lastname@example.com"});
	    assertAnalyzesTo(a, "first_lastname@example.com", new String[]{"first_lastname@example.com"});

	    // floating point, serial, model numbers, ip addresses, etc.
	    // every other segment must have at least one digit
	    assertAnalyzesTo(a, "21.35", new String[]{"21.35"});
	    assertAnalyzesTo(a, "R2D2 C3PO", new String[]{"r2d2", "c3po"});
	    assertAnalyzesTo(a, "216.239.63.104",   new String[]{"216.239.63.104"});
	    assertAnalyzesTo(a, "1-2-3",   new String[]{"1-2-3"});
	    assertAnalyzesTo(a, "a1-b2-c3",   new String[]{"a1-b2-c3"});
	    assertAnalyzesTo(a, "a1-b-c3",   new String[]{"a1-b-c3"});

	    // numbers
	    assertAnalyzesTo(a, "David has 5000 bones", new String[]{"david", "has", "5000", "bones"});

	    // various
	    assertAnalyzesTo(a, "C embedded developers wanted", new String[]{"c", "embedded", "developers", "wanted" });
	    assertAnalyzesTo(a, "foo bar FOO BAR", new String[]{"foo", "bar", "foo", "bar"});
	    assertAnalyzesTo(a, "foo      bar .  FOO <> BAR", new String[]{"foo", "bar", "foo", "bar"});
	    assertAnalyzesTo(a, "\"QUOTED\" word", new String[]{"quoted", "word"});

	    // acronyms have their dots stripped
	    assertAnalyzesTo(a, "U.S.A.", new String[]{ "usa" });

	    // It would be nice to change the grammar in StandardTokenizer.jj to make "C#" and "C++" end up as tokens.
	    assertAnalyzesTo(a, "C++", new String[]{"c"});
	    assertAnalyzesTo(a, "C#", new String[]{"c"});
	    
	    // stopwords are kept
	    StringBuilder sb = new StringBuilder();
	    for (String s : StopAnalyzer.ENGLISH_STOP_WORDS){
	    	sb.append(s + " ");
	    }
	    assertAnalyzesTo(a, sb.toString(), StopAnalyzer.ENGLISH_STOP_WORDS);
	    
	    //latin accent characters are normalised
	    assertAnalyzesTo(a, "À", new String[]{ "a" });
	    assertAnalyzesTo(a, "Á", new String[]{ "a" });
	    assertAnalyzesTo(a, "Â", new String[]{ "a" });
//	    assertAnalyzesTo(a, "Ã", new String[]{ "a" });
	    assertAnalyzesTo(a, "Ä", new String[]{ "a" });
	    assertAnalyzesTo(a, "Æ", new String[]{ "ae" });
	    assertAnalyzesTo(a, "Ç", new String[]{ "c" });
	    assertAnalyzesTo(a, "È", new String[]{ "e" });
	    assertAnalyzesTo(a, "É", new String[]{ "e" });
	    assertAnalyzesTo(a, "Ê", new String[]{ "e" });
	    assertAnalyzesTo(a, "Ë", new String[]{ "e" });
	    assertAnalyzesTo(a, "Ì", new String[]{ "i" });
	    assertAnalyzesTo(a, "Í", new String[]{ "i" });
	    assertAnalyzesTo(a, "Î", new String[]{ "i" });
	    assertAnalyzesTo(a, "Ï", new String[]{ "i" });
	    assertAnalyzesTo(a, "Ð", new String[]{ "d" });
	    assertAnalyzesTo(a, "Ñ", new String[]{ "n" });
	    assertAnalyzesTo(a, "ÀpplÉ", new String[]{ "apple" });
	    assertAnalyzesTo(a, "Ò", new String[]{ "o" });
	    assertAnalyzesTo(a, "Ó", new String[]{ "o" });
	    assertAnalyzesTo(a, "Ô", new String[]{ "o" });
	    assertAnalyzesTo(a, "Õ", new String[]{ "o" });
	    assertAnalyzesTo(a, "Ö", new String[]{ "o" });
	    assertAnalyzesTo(a, "Ø", new String[]{ "o" });
	    assertAnalyzesTo(a, "Œ", new String[]{ "oe" });
	    assertAnalyzesTo(a, "Þ", new String[]{ "th" });
	    assertAnalyzesTo(a, "Þere", new String[]{ "there" });
	    assertAnalyzesTo(a, "Ù", new String[]{ "u" });
	    assertAnalyzesTo(a, "Ú", new String[]{ "u" });
	    assertAnalyzesTo(a, "Û", new String[]{ "u" });
	    assertAnalyzesTo(a, "Ü", new String[]{ "u" });
	    assertAnalyzesTo(a, "Ý", new String[]{ "y" });
	    assertAnalyzesTo(a, "Ÿ", new String[]{ "y" });

	    assertAnalyzesTo(a, "à", new String[]{ "a" });
	    assertAnalyzesTo(a, "á", new String[]{ "a" });
	    assertAnalyzesTo(a, "â", new String[]{ "a" });
	    assertAnalyzesTo(a, "ã", new String[]{ "a" });
	    assertAnalyzesTo(a, "ä", new String[]{ "a" });
	    assertAnalyzesTo(a, "å", new String[]{ "a" });
	    assertAnalyzesTo(a, "æ", new String[]{ "ae" });
	    assertAnalyzesTo(a, "ç", new String[]{ "c" });
	    assertAnalyzesTo(a, "è", new String[]{ "e" });
	    assertAnalyzesTo(a, "é", new String[]{ "e" });
	    assertAnalyzesTo(a, "ê", new String[]{ "e" });
	    assertAnalyzesTo(a, "ë", new String[]{ "e" });
	    assertAnalyzesTo(a, "ì", new String[]{ "i" });
	    assertAnalyzesTo(a, "í", new String[]{ "i" });
	    assertAnalyzesTo(a, "î", new String[]{ "i" });
	    assertAnalyzesTo(a, "ï", new String[]{ "i" });
	    assertAnalyzesTo(a, "ð", new String[]{ "d" });
	    assertAnalyzesTo(a, "ñ", new String[]{ "n" });
	    assertAnalyzesTo(a, "âpplë", new String[]{ "apple" });
	    assertAnalyzesTo(a, "ò", new String[]{ "o" });
	    assertAnalyzesTo(a, "ó", new String[]{ "o" });
	    assertAnalyzesTo(a, "ô", new String[]{ "o" });
	    assertAnalyzesTo(a, "õ", new String[]{ "o" });
	    assertAnalyzesTo(a, "ö", new String[]{ "o" });
	    assertAnalyzesTo(a, "ø", new String[]{ "o" });
	    assertAnalyzesTo(a, "ß", new String[]{ "ss" });
	    assertAnalyzesTo(a, "œ", new String[]{ "oe" });
	    assertAnalyzesTo(a, "þ", new String[]{ "th" });
	    assertAnalyzesTo(a, "þere", new String[]{ "there" });
	    assertAnalyzesTo(a, "ù", new String[]{ "u" });
	    assertAnalyzesTo(a, "ú", new String[]{ "u" });
	    assertAnalyzesTo(a, "û", new String[]{ "u" });
	    assertAnalyzesTo(a, "ü", new String[]{ "u" });
	    assertAnalyzesTo(a, "ý", new String[]{ "y" });
	    assertAnalyzesTo(a, "ÿ", new String[]{ "y" });
	    
	    
	  }
}
