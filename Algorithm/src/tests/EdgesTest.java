package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import builders.StringsSetBuilder;

public class EdgesTest {

	//@Test
	public void test() 
	{
		List<String> test = new ArrayList<String>( ); 
		test.add( "abc" );
		test.add( "bba" );
		test.add( "caa" );
		test.add( "aac" );
		
		StringsSetBuilder b = new StringsSetBuilder( test, 4 );
		b.build( );
		
		System.out.println( b.getSuperstring( ) );
		System.out.println( b.getGrammarStrings( ) );
	}
	
	@Test
	public void test1() 
	{
		List<String> test = new ArrayList<String>( ); 
		test.add( "ab" );
		test.add( "ba" );
		test.add( "ca" );
		test.add( "acc" );
		
		StringsSetBuilder b = new StringsSetBuilder( test, 4 );
		b.build( );
		
		System.out.println( b.getSuperstring( ) );
		System.out.println( b.getGrammarStrings( ) );
	}

}
