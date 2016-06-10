package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structures.Edge;
import builders.StringsSetBuilder;

public class SuperstringTest 
{

	@Test
	public void test1( ) 
	{
		List<String> test = new ArrayList<String>( ); 
		test.add( "abc" );
		test.add( "bba" );
		test.add( "caa" );
		test.add( "aac" );
		
		StringsSetBuilder b = new StringsSetBuilder( test, 3 );
		b.build( );
		
		System.out.println( b.getSuperstring( ) );
		System.out.println( b.getIndexes( ) );
	}
	
	//@Test
	public void test2( ) 
	{
		List<String> test = new ArrayList<String>( ); 
		test.add( "abcca" );
		test.add( "bba" );
		test.add( "ba" );
		test.add( "add" );
		
		StringsSetBuilder b = new StringsSetBuilder( test, 2 );
		b.build( );
		
		System.out.println( b.getSuperstring( ) );
		System.out.println( b.getIndexes( ) );
	}

}
