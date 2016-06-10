package lz77.structures;

import java.util.ArrayList;
import java.util.List;

public class EncodedString 
{
	private List<EncodedElement> _encoded;
	
	public EncodedString( )
	{
		_encoded = new ArrayList<EncodedElement>( );
	}
	
	public void addElement( EncodedElement el ) { _encoded.add( el ); }
	public EncodedElement getElement( int idx ) { return _encoded.get( idx ); } 
	
	public List<EncodedElement> getEncoded( ) {return _encoded;}
	
	public String toString( ) 
	{
		StringBuffer buf = new StringBuffer( );
		for ( EncodedElement el: _encoded )
		{
			buf.append( String.format("%s ", el) );
		}
		buf.append( "\n" );
		return new String( buf );
	}
	
}
