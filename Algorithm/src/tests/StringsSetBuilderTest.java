package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structures.SequenceElement;
import builders.StringsSetBuilder;

public class StringsSetBuilderTest 
{	
	//@Test
	public void test3( ) 
	{
		List<String> test = new ArrayList<String>( ); 
		test.add( "abccaaaa");
		test.add( "caaaab" );
		test.add( "acaa" );
		
		StringsSetBuilder b = new StringsSetBuilder( test, 3 );
		SequenceElement el = b.build( );
		
		System.out.println( el );
		System.out.println( el.getOutputStrings( ) );
	}

}
