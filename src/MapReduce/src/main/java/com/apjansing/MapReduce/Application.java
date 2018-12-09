/**
 * Put your copyright and license info here.
 */
package com.apjansing.MapReduce;

import org.apache.apex.malhar.lib.algo.UniqueCounter;
import org.apache.apex.malhar.lib.io.ConsoleOutputOperator;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.DAG;

@ApplicationAnnotation( name = "MyWordCount" )
public class Application implements StreamingApplication
{

	@Override
	public void populateDAG( DAG dag, Configuration conf )
	{
		WordCountInput input = dag.addOperator( "INPUT", new WordCountInput() );

		UniqueCounter< String > wordCount = dag.addOperator( "COUNTER", new UniqueCounter< String >() );
		dag.addStream( "count", input.outputPort, wordCount.data );
		
//		WordCounter< String > wordCount = dag.addOperator( "COUNTER", WordCounter.class );
//		dag.addStream( "wordinput-count", input.outputPort, wordCount.data );

		MapReducerFileOutputOperator writer = dag.addOperator( "OUTPUT", MapReducerFileOutputOperator.class );
		dag.addStream( "output", wordCount.count, writer.input );
	}
}
