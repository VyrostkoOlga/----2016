package tests;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import structures.Grammar;
import structures.SequenceElement;
import builders.RuleBuilder;
import builders.SequenceBuilder;

public class GrammarTest 
{

	//@Test
	public void test( ) 
	{
		// 8 символов = 5 правил
		String s = "bacababa";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
		//System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test2( ) 
	{
		// 6 символов = 5 правил
		String s = "abcbca";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( grammar.getRules( ) );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test3( ) 
	{
		// 7 символов = 6 правил
		String s = "bcbbcba";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test4( ) 
	{
		//12 symbols --> 11 rules
		String s = "bcdababadeef";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		//System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}
	
	@Test
	public void test5( ) 
	{
		// 24 symbols --> 12 rules
		String s = "bcdababadeefbcdababadeef";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test6( ) 
	{
		// 27 symbols --> 22 rules
		String s = "bcdababadeefbcdababadeefefb";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test7( ) 
	{
		// 360 symbols --> 32 rules
		String s = "abcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfq"
				+ "abcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfq"
				+ "abcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfq"
				+ "abcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfqabcdefrgfq";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test9( ) 
	{
		// 42 symbols --> 34 rules
		String s = "abcabcdeefceafafceceafafafabccaacbsabsssba";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		//System.out.println( grammar.getRules( ) );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test8( ) throws FileNotFoundException, IOException 
	{
		//130 symbols --> 76 rules
		StringBuffer buf = new StringBuffer( );
		String s;
		
		try ( BufferedReader reader = new BufferedReader( 
									  new InputStreamReader( 
									  new BufferedInputStream(
									  new FileInputStream( 
									  new File( "/Users/OlgaVyrostko/Documents/WorkMaterials/6sem/CourseWork/Algorithm/src/tests/SequenceTest.java" 
									  ) ) ) ) ) )
		{
			while ( ( s = reader.readLine( ) ) != null )
			{
				buf.append( String.format( "%s\n", reader.readLine( ) ) );
			}
		}
		s = new String( buf );
		
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test10( ) throws FileNotFoundException, IOException 
	{
		//284 symbols --> 206 rules
		StringBuffer buf = new StringBuffer( );
		String s;
		
		try ( BufferedReader reader = new BufferedReader( 
									  new InputStreamReader( 
									  new BufferedInputStream(
									  new FileInputStream( 
									  new File( "/Users/OlgaVyrostko/Documents/WorkMaterials/6sem/CourseWork/Algorithm/src/structures/SequenceElement.java" 
									  ) ) ) ) ) )
		{
			while ( ( s = reader.readLine( ) ) != null )
			{
				buf.append( String.format( "%s\n", reader.readLine( ) ) );
			}
		}
		s = new String( buf );
		
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test11( ) throws FileNotFoundException, IOException 
	{
		StringBuffer buf = new StringBuffer( );
		String s;
		
		try ( BufferedReader reader = new BufferedReader( 
									  new InputStreamReader( 
									  new BufferedInputStream(
									  new FileInputStream( 
									  new File( "/Users/OlgaVyrostko/Documents/WorkMaterials/6sem/CourseWork/Algorithm/src/tests/StringsSetBuilderTest.java" 
									  ) ) ) ) ) )
		{
			while ( ( s = reader.readLine( ) ) != null )
			{
				buf.append( String.format( "%s\n", reader.readLine( ) ) );
			}
		}
		s = new String( buf );
		
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test12( ) throws FileNotFoundException, IOException 
	{
		// 314 symbols --> 211 rules
		StringBuffer buf = new StringBuffer( );
		String s;
		
		try ( BufferedReader reader = new BufferedReader( 
									  new InputStreamReader( 
									  new BufferedInputStream(
									  new FileInputStream( 
									  new File( "/Users/OlgaVyrostko/Documents/WorkMaterials/6sem/CourseWork/Algorithm/src/tests/SuperstringTest.java" 
									  ) ) ) ) ) )
		{
			while ( ( s = reader.readLine( ) ) != null )
			{
				buf.append( String.format( "%s\n", reader.readLine( ) ) );
			}
		}
		s = new String( buf );
		
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		
		assertEquals( s, grammar.product( ) );
	}

}
