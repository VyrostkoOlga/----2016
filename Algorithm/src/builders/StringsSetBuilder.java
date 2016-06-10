package builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import structures.Edge;
import structures.GrammarString;
import structures.SequenceElement;

public class StringsSetBuilder 
{
	// Input
	private List<String> _inputStrings;
	private int _maxLength;
	
	// Work
	private Queue<Edge> _queue;
	private List<Edge> _edges;
	private List<Integer> _indexes;
	private List<String> _clearedStrings;
	private List<String> _workStrings;
	private Set<String> _used;
	
	private Map<String, String> _sp;
	private Map<String, String> _np;
	
	// Output
	private List<GrammarString> _grammarStrings;
	private List<String> _outputStrings;
	private String _superstring;
	
	public StringsSetBuilder( List<String> inputStrings, int maxLength )
	{
		_inputStrings = inputStrings;
		_maxLength = maxLength;
	}
	
	public List<String> getInputStrings( ) { return _inputStrings; }
	public List<String> getOutputStrings( ) { return _outputStrings; }
	public List<GrammarString> getGrammarStrings( ) { return _grammarStrings; }
	public int getMaxLength( ) { return _maxLength; }
	public List<Edge> getEdges( ) { return _edges; }
	public List<Integer> getIndexes( ) { return _indexes;}
	public String getSuperstring( ) { return _superstring; }
	
	public SequenceElement build( )
	{	
		initialize( );
		clearStrings( );
		
		if ( _workStrings.size( ) == 1 )
			workWithOneString( );
		else
		{
			prepareQueue( );	
			prepareEdges( );
			buildSuperstring( );
		}
		
		workWithClearedStrings( );
		extraCuts( );
		prepareOutputStrings( );
		
		return new SequenceElement( _inputStrings, _outputStrings, _grammarStrings );
	}
	
	private void workWithOneString( )
	{
		_superstring = _workStrings.get( 0 );
		
		GrammarString gs = new GrammarString( _superstring );
		gs.setStart( 0 );
		gs.setEnd( _superstring.length( ) );
		_grammarStrings.add( gs );
		
		_indexes.add( 0 );
	}
	
	private void initialize( )
	{
		_queue = new PriorityQueue<Edge>( );
		_edges = new ArrayList<Edge>( );
		_indexes = new ArrayList<Integer>( );
		_clearedStrings = new ArrayList<String>( );
		_grammarStrings = new ArrayList<GrammarString>( );
		_outputStrings = new ArrayList<String>( );
		_superstring = "";
	}
	
	private void clearStrings( )
	{
		_clearedStrings = new ArrayList<String>( );
		_workStrings = new ArrayList<String>( _inputStrings );
		
		String first, second;
		for ( int i=0; i<_inputStrings.size( ); i++ )
			for ( int j=i+1; j<_inputStrings.size( ); j++ )
			{
				first = _inputStrings.get( i );
				second = _inputStrings.get( j );
				if ( first.indexOf( second ) != -1 )
				{
					_clearedStrings.add( second );
					_workStrings.remove( second );
				}
				else if ( second.indexOf( first ) != -1 )
				{
					_clearedStrings.add( first );
					_workStrings.remove( first );
				}
			} 
	}
	
	private void prepareQueue( )
	{
		_queue = new PriorityQueue<Edge>( );
		String first, second;
		
		for ( int i=0; i<_workStrings.size( ); i++ )
			for ( int j=i+1; j<_workStrings.size( ); j++ )
			{
				first = _workStrings.get( i );
				second = _workStrings.get( j );
				_queue.add( Edge.createEdge( first, second ) );
				_queue.add( Edge.createEdge( second, first ) );
			}
	}
	
	private void prepareEdges( )
	{
		_edges = new ArrayList<Edge>( );
		_used = new HashSet<String>( );
		
		_sp = new HashMap<String, String>( );
		_np = new HashMap<String, String>( );
		
		for ( String s: _workStrings )
		{
			_sp.put( s, null );
			_np.put( s, null );
		}
		
		Edge current;
		Set<Edge> toRemove;
		
		while ( _used.size( ) < _workStrings.size( ) )
		{
			current = _queue.poll( );
			
			_used.add( current.getFirst( ) );
			_used.add( current.getSecond( ) );
			_edges.add( current );
			
			if ( _sp.get( current.getFirst( ) ) == null )
				_sp.put( current.getFirst( ), current.getFirst( ) );
			
			_np.put( current.getFirst( ), current.getSecond( ) );
			
			toRemove = new HashSet<Edge>( );
			String psp = _sp.get( current.getSecond( ) ); 
			String sp = _sp.get( current.getFirst( ) );
			Set<String> changes = new HashSet<String>( );
			
			for ( String s: _used )
			{
				if ( _sp.get( s ) != null && _sp.get( s ).equals( psp ) )
				{
					_sp.put( s, _sp.get( sp ) );
					changes.add( s );
				}
			}
			_sp.put( current.getSecond( ), sp );
			changes.add( current.getSecond( ) );
			
			for ( Edge e: _queue )
			{
				if ( e.getFirst( ).equals( current.getFirst( ) ) )
					toRemove.add( e );
				else if ( e.getSecond( ).equals( current.getSecond( ) ) )
					toRemove.add( e );
				else if ( changes.contains( e.getFirst( ) ) )
				{
					String curSp = _sp.get( e.getSecond( ) );
					if ( curSp != null && curSp.equals( sp ) )
						toRemove.add( e );
				}
			}
			
			_queue.removeAll( toRemove );
		}
	}
	
	private void buildSuperstring( )
	{	
		StringBuffer superstring = new StringBuffer( );
		_indexes = new ArrayList<Integer>( );
		
		Edge current;
		GrammarString gs;
		String curV;
		
		// Формируем множество начал цепей
		Set<String> beginnings = new HashSet<String>( );
		for ( String key: _sp.keySet( ) )
			if ( _sp.get( key ).equals( key ) )
				beginnings.add( key );
		
		for ( String beginning: beginnings )
		{
			superstring.append( beginning );
			gs = new GrammarString( beginning );
			gs.setEnd( superstring.length( ) );
			gs.setStart( gs.getEnd( ) - beginning.length( ) );
			_indexes.add( gs.getStart( ) );
			_grammarStrings.add( gs );
			
			curV = _np.get( beginning );
			
			while ( curV != null )
			{
				current = getEdge( beginning, curV );
				superstring.append( curV.substring( current.getOverlap( ) ) );
				_indexes.add( superstring.length( ) - curV.length( ) );
			
				gs = new GrammarString( curV );
				gs.setStart( _indexes.get( _indexes.size( ) - 1 ) );
				gs.setEnd( gs.getStart( ) + curV.length( ) );
				_grammarStrings.add( gs );
				
				beginning = curV;
				curV = _np.get( beginning );
			}
		}
		_superstring = new String( superstring );
	}
	
	private void workWithClearedStrings( )
	{
		GrammarString gs;
		int start, end;
		for ( String s: _clearedStrings )
		{
			gs = new GrammarString( s );
			start = _superstring.indexOf( s );
			end = start + s.length( );
			gs.setStart( start );
			gs.setEnd( end );
			_grammarStrings.add( gs );
			
			if ( !_indexes.contains( start ) && start != -1 )
				_indexes.add( start );
		}
		_indexes.add( _superstring.length( ) );
	}
	
	private void extraCuts( )
	{
		Queue<Integer> q = new PriorityQueue<Integer>( );
		q.addAll( _indexes );
		
		int start, end, middle;
		while ( q.size( ) > 1 )
		{
			start = q.poll( );
			end = q.poll( );
			if ( end - start > _maxLength )
			{
				middle = start + ( end - start ) / 2; 
				
				_indexes.add( middle );
				q.add( start );
				q.add( middle );
				q.add( end );
			}
			else
			{
				q.add( end );
			}
		}
		Collections.sort( _indexes );
	}
	
	private void prepareOutputStrings( )
	{
		int idx1, idx2;
		String result;
		for ( int i=_indexes.size( ) - 1; i > 0; i-- )
		{
			idx1 = _indexes.get( i );
			idx2 = _indexes.get( i - 1 );
			result = _superstring.substring( idx2, idx1 );
			_outputStrings.add( result );
			
			for ( GrammarString gs: _grammarStrings )
			{
				if ( gs.isChild( idx2, idx1 ) )
					gs.addChild( result );
			}
		}
	}
	
	private Edge getEdge( String start, String end )
	{
		for ( Edge e: _edges )
			if ( e.getFirst( ).equals( start ) && e.getSecond( ).equals( end ) )
				return e;
		return null;
	}
}
