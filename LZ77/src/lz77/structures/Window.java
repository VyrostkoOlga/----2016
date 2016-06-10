package lz77.structures;

public class Window 
{
	private String _input;
	private int _startView;
	
	public Window( String input )
	{
		_input = input;
	}
	
	public void encode( )
	{
		EncodedString encoded = new EncodedString( );
		EncodedElement el = new EncodedElement( );
		
		_startView = 0;
		while ( _startView < _input.length() )
		{
			el = findPrefix( );
			encoded.addElement( el );
		}
		
		System.out.println( encoded );
	}
	
	private EncodedElement findPrefix( )
	{
		EncodedElement el = new EncodedElement( );
		
		int prefIdx = 1;
		int curIdx = -1;
		int prevIdx = -1;
		
		String prefix = "";
		
		while ( prefIdx <= _input.length() - _startView )
		{
			prefix = _input.substring( _startView, _startView + prefIdx );
			curIdx = _input.indexOf( prefix );
			
			if ( curIdx >= _startView )
				break;
			
			prefIdx++;
			prevIdx = curIdx;
		}
		
		prefIdx -= 1;
		
		if ( prefIdx == _input.length() - _startView )
		{
			el.setI( _startView - prevIdx );
			el.setJ( prefIdx );
			el.setX( '$' );
			_startView = _input.length( );
		}
		else if ( prevIdx == -1 )
		{
			el.setI( 0 );
			el.setJ( 0 );
			el.setX( _input.charAt( _startView ) );
			_startView++;
		}
		else
		{
			el.setI( _startView - prevIdx );
			el.setJ( prefIdx );
			el.setX( _input.charAt( _startView + prefIdx ) );
			_startView = _startView + prefIdx + 1;
		}
		return el;
	}
}
