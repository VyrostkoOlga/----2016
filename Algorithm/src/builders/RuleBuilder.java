package builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import structures.Grammar;
import structures.GrammarString;
import structures.Rule;
import structures.SequenceElement;

public class RuleBuilder 
{
	private List< SequenceElement > _sequence;
	
	public RuleBuilder( List< SequenceElement > sequence )
	{
		_sequence = sequence;
	}
	
	public Grammar buildGrammar( )
	{
		Map< String, Rule > rules = new HashMap< String, Rule >( );
		Set<String> terminals = new HashSet<String>( );
		
		int n = _sequence.size( ) - 1;
		Rule rule = null;
		
		// Извлекаем самый нижний слой
		SequenceElement el = _sequence.get( n );
		for ( String str: el.getOutputStrings( ) )
		{
			//Если длина строки меньше двух, это единственный символ
			// и просто терминал, новое правило добавлять не нужно
			if ( str.length() < 2 )
			{
				terminals.add( str );
				continue;
			}
			// Иначе создаем новое правило вида 1 --> [a, b]
			rule = new Rule( str );
			//rule.addProduct( str.substring(0, 1) );
			//rule.addProduct(str.substring(1, 2));
			rule.addProduct( str );
			rules.put( str, rule );
		}
		
		SortedSet<String> gotStrings = new TreeSet<String>( );
		gotStrings.addAll( rules.keySet( ) );
		while ( n >= 1 )
		{
			el = _sequence.get( n );
			StringBuffer buf;
			for ( GrammarString gs: el.getGrammarStrings( ) )
			{
				// Если правило вывода такого куска уже есть
				if ( rules.containsKey( gs.getString( ) ) )
					continue;
				
				rule = new Rule( gs.getString( ) );
				buf = new StringBuffer( );
				
				List<String> children = gs.getChildren( );
				
				for ( int j = children.size( ) - 1; j>=0; j-- )
				{
					String curChild = children.get( j );
					buf.append( curChild );
					
					// Пробуем найти во множестве терминалов
					if ( terminals.contains( curChild ) )
					{
						rule.addProduct( curChild );
						continue;
					}
					
					//Пробуем найти среди нетерминалов
					if ( rules.containsKey( curChild ) )
					{
						rule.addProduct( rules.get( curChild ).getDeterminant( ) );
						continue;
					}
				}
				
				// Проверяем, нет ли еще не учтенного хвоста
				String result = new String( buf );
				if ( !result.equals( gs.getString( ) ) )
				{
					String curChild = gs.getString( ).substring( result.length( ) );
					
					Stack<String> stack = new Stack<String>( );
					stack.push( curChild );
					while ( !stack.isEmpty( ) )
					{
						curChild = stack.pop( );
						if ( rules.containsKey( curChild ) )
							rule.addProduct( rules.get( curChild ).getDeterminant( ) );
						else if ( terminals.contains( curChild ) )
							rule.addProduct( curChild );
						else if ( curChild.length( ) == 1 )
						{
							terminals.add( curChild );
							rule.addProduct( curChild );
						}
						else
						{
							int middle = curChild.length( ) / 2;
							stack.push( curChild.substring( middle ) );
							stack.push( curChild.substring( 0, middle ) );
						}
					}
				}
				
				rules.put( gs.getString( ), rule );
			}
			n--;
		}
		
		Rule start = rule;
		
		// А вот теперь дополнительное деление правил, 
		// если в этом есть необходимость
		Set<Rule> nrules = new HashSet<Rule>( );
		Stack<Rule> ruleStack = new Stack<Rule>( );
		ruleStack.addAll( rules.values( ) );
		while ( !ruleStack.isEmpty( ) )
		{
			rule = ruleStack.pop( );
			// Если в списке и так не более двух продуктов,
			// просто добавляем существующее правило и идем дальше
			if ( rule.getProducts( ).size( ) <= 2 )
			{
				nrules.add( rule );
				continue;
			}
			
			// Иначе делим список продуктов напополам
			int middle = rule.getProducts( ).size( ) / 2;
			Rule left = new Rule( );
			left.setProducts( rule.getProducts( ).subList(0, middle ) );
			Rule right = new Rule( );
			right.setProducts( rule.getProducts( ).subList(middle, rule.getProducts( ).size( ) ) );
			
			List<String> mainProducts = new ArrayList<String>( );
			if ( left.getProducts( ).size( ) == 1 )
				mainProducts.add( left.getProducts( ).get( 0 ) );
			else
			{
				String leftProduct = String.join("", left.getProducts( ) );
				if ( rules.containsKey( leftProduct ) )
					left = rules.get( leftProduct );
				else
					rules.put(leftProduct, left);
				
				mainProducts.add( left.getDeterminant( ) );
				ruleStack.push( left );
			}
			
			if ( right.getProducts( ).size( ) == 1 )
				mainProducts.add( right.getProducts( ).get( 0 ) );
			else
			{
				String rightProduct = String.join("", right.getProducts( ) );
				if ( rules.containsKey( rightProduct ) )
					left = rules.get( rightProduct );
				else
					rules.put(rightProduct, right);
				
				mainProducts.add( right.getDeterminant( ) );
				ruleStack.push( right );
			}
			rule.setProducts( mainProducts );
			nrules.add( rule );
		}
		
		return new Grammar( start.getDeterminant(), nrules );
	}

}
