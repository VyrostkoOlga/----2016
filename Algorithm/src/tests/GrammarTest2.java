package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import structures.Grammar;
import structures.SequenceElement;
import builders.RuleBuilder;
import builders.SequenceBuilder;

public class GrammarTest2 
{
	//@Test
	public void test( ) 
	{
		//8 symbols --> 3 rules
		String s = "aaaaaaaa";
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
		//32 symbols --> 5 rules
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
		System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}
	
	//@Test
	public void test3( ) 
	{
		//31 symbols --> 11 rules
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
		System.out.println( grammar );
		
		assertEquals( s, grammar.product( ) );
	}
}
