package lz77.structures;

public class EncodedElement 
{
	/**
	 * Класс для представления элемента закодированной строки
	 * i - индекс начала текущего префикса в уже закодированной части строки (window)
	 * j - длина префикса
	 * x - следующий за префиксом символ
	 */
	
	private int _i;
	private int _j;
	private char _x;
	
	public void setI( int i ) { _i = i; }
	public int getI( ) { return _i; }
	
	public void setJ( int j ) { _j = j;}
	public int getJ( ) { return _j; }
	
	public void setX( char x ) { _x = x; }
	public char getX( ) { return _x; }
	
	public EncodedElement( )
	{
		
	}
	
	public EncodedElement( int i, int j, char x )
	{
		_i = i;
		_j = j;
		_x = x;
	}
	
	public String toString( ) 
	{
		return String.format("(%s, %s, %s)", _i, _j, _x );
	}
}
