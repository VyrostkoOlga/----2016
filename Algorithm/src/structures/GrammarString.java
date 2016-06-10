package structures;

import java.util.ArrayList;
import java.util.List;

public class GrammarString 
{
	private String _string;
	private int _start;
	private int _end;
	private List<String> _children;
	
	public GrammarString( String string )
	{
		_string = string;
		_start = -1;
		_end = -1;
		_children = new ArrayList<String>( );
	}
	
	public GrammarString( )
	{
		_string = "";
		_start = -1;
		_end = -1;
		_children = new ArrayList<String>( );
	}

	public void setString( String string ) { _string = string;}
	public String getString( ) { return _string; }
	
	public void setStart( int start ) { _start = start; }
	public int getStart( ) { return _start; }
	
	public void setEnd( int end ) { _end = end; }
	public int getEnd( ) { return _end; }
	
	public void setChildren( List<String> children ) { _children = children; }
	public List<String> getChildren( ) { return _children; }
	
	public void addChild( String child ) { _children.add( child ); }
	public void removeChild( String child ) { _children.remove( child ); }
	
	@Override
	public String toString( )
	{
		return String.format( "%s -> %s", _string, _children );
	}
	
	public boolean isChild( int start, int end )
	{
		return ( start >= _start ) && ( end <= _end );
	}
}
