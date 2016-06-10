package structures;

import java.util.List;

public class SequenceElement 
{
	private List<String> _inputStrings;
	private List<String> _outputStrings;
	private List<GrammarString> _grammarStrings;
	
	public SequenceElement( List<String> inputStrings, 
							List<String> outputStrings,
							List<GrammarString> grammarStrings )
	{
		_inputStrings = inputStrings;
		_outputStrings = outputStrings;
		_grammarStrings = grammarStrings;
	}
	
	public List<String> getInputStrings( ) { return _inputStrings; }
	public List<String> getOutputStrings( ) { return _outputStrings; }
	public List<GrammarString> getGrammarStrings( ) { return _grammarStrings; }
	
	@Override
	public String toString( )
	{
		return String.format( "%s", _grammarStrings );
	}
}
