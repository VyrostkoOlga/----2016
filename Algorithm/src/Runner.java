
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import structures.Grammar;
import structures.SequenceElement;
import builders.RuleBuilder;
import builders.SequenceBuilder;

//fabccaacbsabsssba
// сжать последовательность Фибоначчи и другие подобные последовательности
// Lemma 9

public class Runner 
{
	private static final int MAX_LENGTH = 1000;
	private static String ALPHABETH = "qwertyuiopasdfghjklzxcvbnm";
	
	public static void main( String [] args ) throws Exception
	{
		//for ( int i=5; i<MAX_LENGTH; i++ )
		{
			String s = generateString( 1024 );
			//System.out.println( s );
			
			SequenceBuilder b = new SequenceBuilder( s );
			List< SequenceElement > sequence = b.build( );
			
			RuleBuilder gb = new RuleBuilder( sequence );
			Grammar grammar = gb.buildGrammar( );
			
			System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
			System.out.println( grammar.product( ).equals( s ) );
		}
	}
	
	/*
	public static void main( String [] args ) throws FileNotFoundException, IOException
	{
		StringBuffer buf = new StringBuffer( );
		String s;
		
		try ( BufferedReader reader = new BufferedReader( 
									  new InputStreamReader( 
									  new BufferedInputStream(
									  new FileInputStream( 
									  new File( "/Users/OlgaVyrostko/Documents/WorkMaterials/6sem/CourseWork/Algorithm/src/tests/EdgesTest.java" 
									  ) ) ) ) ) )
		{
			while ( ( s = reader.readLine( ) ) != null )
			{
				buf.append( String.format( "%s\n", reader.readLine( ) ) );
			}
		}
		s = new String( buf );
		
		SequenceBuilder b = new SequenceBuilder( s );
		List< SequenceElement > sequence = b.build( );
		
		RuleBuilder gb = new RuleBuilder( sequence );
		Grammar grammar = gb.buildGrammar( );
		System.out.println( String.format( "%s symbols --> %s rules", s.length( ), grammar.getRules( ).size( ) ) );
		System.out.println( grammar.product( ).equals( s ) );
	}
	*/
	
	private static String generateString( int length ) throws UnsupportedEncodingException
	{
		Random random = new Random( ALPHABETH.length( ) );
		
		int idx;
		String currentPiece;
		StringBuffer buf = new StringBuffer( );
		for ( int i=0; i<length; i++ )
		{
			idx = random.nextInt( ALPHABETH.length( ) );
			currentPiece = ALPHABETH.substring( idx, idx + 1 );
			buf.append( currentPiece );
		}
		
		return new String( buf );
	}

}
