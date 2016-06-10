package structures;

public class Edge implements Comparable<Edge>
{
	private String _first;
	private String _second;
	private int _overlap;
	
	public Edge( String first, String second, int overlap )
	{
		_first = first;
		_second = second;
		_overlap = overlap;
	}
	
	public String getFirst( ) { return _first; }
	public String getSecond( ) { return _second; }
	public int getOverlap( ) { return _overlap; }
	public void setOverlap( int overlap ) { _overlap = overlap; }
	
	public static Edge createEdge( String first, String second )
	{
		return new Edge( first, second, countOverlap( first, second ) );
	}
	
	private static int countOverlap( String first, String second )
	{
		int k = Math.min( first.length( ),  second.length( ) );
		String currentOverlap;
		
		while ( k >= 0 )
		{
			currentOverlap = second.substring( 0, k );
			if ( first.endsWith( currentOverlap ) )
				return k;
			k--;
		}
		return k;
	}
	
	@Override
	public boolean equals( Object o )
	{
		if ( ! ( o instanceof Edge ) )
			return false;
		
		Edge e = ( Edge ) o;
		return ( _first.equals( e._first ) && _second.equals( e._second ) );
	}

	@Override
	public int compareTo(Edge o) 
	{
		return o._overlap - _overlap;
	}
	
	@Override
	public int hashCode( )
	{
		return _overlap;
	}
	
	@Override
	public String toString( )
	{
		return String.format( "(%s, %s, %s)", _first, _second, _overlap );
	}

}
