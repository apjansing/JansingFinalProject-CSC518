package com.apjansing.MapReduce;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.apex.malhar.lib.util.UnifierHashMapSumKeys;
import org.apache.commons.lang.mutable.MutableInt;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;

public class WordCounter< String > extends BaseOperator
{
	protected HashMap< String, MutableInt > map = new HashMap< String, MutableInt >();

	public String cloneKey( String key )
	{
		return key;
	}

	public void processTuple( String tuple )
	{
		MutableInt i = map.get( tuple );
		if ( i == null )
		{
			i = new MutableInt( 0 );
			map.put( cloneKey( tuple ), i );
		}
		i.increment();
	}

	private boolean cumulative;
	HashSet< String > inputSet = new HashSet<>();

	/**
	 * The input port which receives incoming tuples.
	 */
	public final transient DefaultInputPort< String > data = new DefaultInputPort< String >()
	{
		/**
		 * Reference counts tuples
		 */
		@Override
		public void process( String tuple )
		{
			inputSet.add( tuple );
			processTuple( tuple );
		}
	};

	/**
	 * The output port which emits a map from keys to the number of times they
	 * occurred within an application window.
	 */
	public final transient DefaultOutputPort< HashMap< String, Integer > > count = new DefaultOutputPort< HashMap< String, Integer > >()
	{
		@Override
		public Unifier< HashMap< String, Integer > > getUnifier()
		{
			UnifierHashMapSumKeys< String, Integer > unifierHashMapSumKeys = new UnifierHashMapSumKeys<>();
			unifierHashMapSumKeys.setType( Integer.class );
			return unifierHashMapSumKeys;
		}
	};

	/**
	 * Gets the cumulative mode.
	 * 
	 * @return The cumulative mode.
	 */
	public boolean isCumulative()
	{
		return cumulative;
	}

	/**
	 * If enabled then the unique keys is counted and maintained in memory for the
	 * life of the operator. If not enabled keys are counted a per window
	 * bases.<br/>
	 * <b>Note:</b> If cumulative mode is enabled and the operator receives many
	 * unique keys, then this operator could eventually run out of memory.
	 * 
	 * @param cumulative
	 */
	public void setCumulative( boolean cumulative )
	{
		this.cumulative = cumulative;
	}
}
