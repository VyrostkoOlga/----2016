package structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Grammar 
{
	private String _start;
	private Set< Rule > _rules;
	
	public Grammar( String start, Set< Rule > rules )
	{
		_start = start;
		_rules = rules;
	}
	
	public String getStart( ) { return _start; }
	public Set< Rule > getRules( ) { return _rules; }
	
	@Override
	public String toString( )
	{
		return _rules.toString( );
	}
	
	public String product( )
	{
		return product( _start ); 
	}
	
	private Rule findRule( String determinant )
	{
		for ( Rule r: _rules )
		{
			if ( r.getDeterminant( ).equals( determinant ) )
				return r;
		}
		return null;
	}
	
	private String product( String start )
	{
		Rule r = findRule( start );
		if ( null == r )
			return start;

		List< String > products = new ArrayList< String >( );
		for ( String product: r.getProducts( ) )
		{
			products.add( product( product ) );
		}
		return String.join( "", products );
	}
}
