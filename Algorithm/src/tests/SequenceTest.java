package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.SequenceBuilder;

public class SequenceTest 
{

	@Test
	public void test( ) 
	{
		String s = "bacababa";
		SequenceBuilder b = new SequenceBuilder( s );
		System.out.println( b.build( ) );
	}
	
	@Test
	public void test1( ) 
	{
		String s = "abcbabaaab";
		SequenceBuilder b = new SequenceBuilder( s );
		System.out.println( b.build( ) );
	}

}
