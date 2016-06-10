package tests.grammars;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import structures.Grammar;
import structures.SequenceElement;
import builders.RuleBuilder;
import builders.SequenceBuilder;

public class Fib {
	
	private String getFibString( String s1, String s2, int n )
	{
		int i = 2;
		String current = "";
		while ( i < n )
		{
			current = s1 + s2;
			s1 = s2;
			s2 = current;
			i++;
		}
		return current;
	}

	@Test
	public void test( ) 
	{
		for ( int i=20; i<50; i++ )
		{
			String s = getFibString( "ac", "b", i );
			//System.out.println( s );
			
			SequenceBuilder b = new SequenceBuilder( s );
			List< SequenceElement > sequence = b.build( );
			
			RuleBuilder gb = new RuleBuilder( sequence );
			Grammar grammar = gb.buildGrammar( );
			
			System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );	
			//System.out.println( grammar );
			
			assertEquals( s, grammar.product( ) );
		}
	}

}
