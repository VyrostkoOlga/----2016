package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import structures.Grammar;
import structures.Rule;
import structures.SequenceElement;
import builders.RuleBuilder;
import builders.SequenceBuilder;

public class StringGrammarTest 
{

	@Test
	public void test1( ) 
	{
		// Длина строки = 23
		String s = "aabacabaaabacabafffaaba";
		
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		
		System.out.println( grammar.getStart( ) );
		for ( Rule rule: grammar.getRules( ) )
		{
			System.out.println( rule );
		}
		
		grammar.product( );
		
		System.out.println( grammar.getRules().size( ));
		System.out.println( grammar.product( ).equals( s ) );
	}

}
