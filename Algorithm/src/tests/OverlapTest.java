package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import structures.Edge;

public class OverlapTest 
{

	@Test
	public void test1( ) 
	{
		Edge e = Edge.createEdge( "aaa", "abb" );
		assertEquals( 1, e.getOverlap( ) );
	}
	
	@Test
	public void test2( ) 
	{
		Edge e = Edge.createEdge( "aaabc", "bca" );
		assertEquals( 2, e.getOverlap( ) );
	}
	
	@Test
	public void test3( ) 
	{
		Edge e = Edge.createEdge( "aaaba", "bca" );
		assertEquals( 0, e.getOverlap( ) );
	}

}
