package lz77.runner;

import lz77.structures.Window;

public class Runner 
{
	public static void main( String [] args )
	{
		String input = "aabcdaacd";
		Window w = new Window( input );
		w.encode( );
	}
}
