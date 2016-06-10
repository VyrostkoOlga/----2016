package builders;

import java.util.ArrayList;
import java.util.List;

import structures.GrammarString;
import structures.SequenceElement;

public class SequenceBuilder 
{
	private String _input;
	private List< SequenceElement > _sequence;
	
	public SequenceBuilder( String s )
	{
		_input = s;
		_sequence = new ArrayList< SequenceElement >( );
	}

	public List< SequenceElement > build( )
	{
		_sequence = new ArrayList< SequenceElement >( );
		
		SequenceElement el;
		StringsSetBuilder builder;
		
		workWithOneString( );
		
		el = _sequence.get( 0 );
		int n = _input.length( );
		while ( n > 2 )
		{
			n /= 2;
			builder = new StringsSetBuilder( el.getOutputStrings( ), n );
			el = builder.build( );
			_sequence.add( el );
		}
		return _sequence;
	}
	
	private void workWithOneString( )
	{
		List< String > inputStrings = new ArrayList< String >( );
		List< String > outputStrings = new ArrayList< String >( );
		List< GrammarString > grammarStrings = new ArrayList< GrammarString >( );
		
		inputStrings.add( _input );
		outputStrings.add( _input );
		
		GrammarString gs = new GrammarString( _input );
		String first = _input.substring( 0, _input.length( ) / 2 );
		String second = _input.substring( _input.length( ) / 2 );
		gs.addChild( first );
		gs.addChild( second );
		grammarStrings.add( gs );
		
		SequenceElement el = new SequenceElement(inputStrings, outputStrings, grammarStrings );
		_sequence.add( el );
	}
}
