package structures;

import java.util.ArrayList;
import java.util.List;

public class Rule
{
	public static int CURRENT_DETERMINANT = 0;
	
	private String _determinant;
	private List<String> _products;
	
	public Rule( )
	{
		_determinant = Integer.toString( CURRENT_DETERMINANT );
		CURRENT_DETERMINANT++;
		
		_products = new ArrayList<String> ( );
	}
	
	public Rule( String formedBy )
	{	
		_determinant = Integer.toString( CURRENT_DETERMINANT );
		CURRENT_DETERMINANT++;
		
		_products = new ArrayList<String> ( );
	}
	
	public String getDeterminant( ) { return _determinant; }
	public List<String> getProducts( ) { return _products; }
	public void setProducts( List< String > products ) { _products = products; }
	
	public void addProduct( String s ) { _products.add( s ); }
	
	public void replaceInRule( String formedBy, String determinant )
	{
		for ( int i=0; i<_products.size( ); i++ )
		{
			if ( _products.get( i ).equals( formedBy ) )
				_products.set( i, determinant );
		}
	}
	
	@Override
	public boolean equals( Object ob )
	{
		if ( !( ob instanceof Rule ) )
			return false;
		
		Rule r = ( Rule ) ob;
		return r._determinant.equals( _determinant );
	}
	
	@Override
	public int hashCode( )
	{
		return _determinant.codePointAt( 0 );
	}
	
	@Override
	public String toString( )
	{
		return String.format( "%s --> %s ", _determinant, _products );
	}
}
