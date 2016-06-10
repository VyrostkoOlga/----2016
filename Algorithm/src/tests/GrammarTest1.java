package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import structures.Grammar;
import structures.SequenceElement;
import builders.RuleBuilder;
import builders.SequenceBuilder;

public class GrammarTest1 
{

	//@Test
	public void test( ) 
	{
		//35 symbols --> 15 rules
		String s = "bacababa|bacababa|bacababa|bacababa";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
		//System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test1( ) 
	{
		//90 symbols --> 25 rules
		// повторяющихся кусков = 9
		String s = "bacababa|bacababa|bacababa|bacababa|bacababa|bacababa|bacababa|bacababa|bacababa|bacababa|";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
		//System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}
	
	@Test
	public void test3( ) 
	{
		// 80 symbols --> 19 rules
		// повторяющихся кусков = 9
		String s = "bacabab|bacabab|bacabab|bacabab|bacabab|bacabab|bacabab|bacabab|bacabab|bacabab|";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
		//System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}

}
