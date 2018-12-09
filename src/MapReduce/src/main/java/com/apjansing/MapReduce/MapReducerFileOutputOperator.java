/*
 * Code inspired by https://github.com/dtpublic/examples FileWriter.java
 */
package com.apjansing.MapReduce;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

import org.apache.apex.malhar.lib.io.fs.AbstractFileOutputOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.api.Context;

public class MapReducerFileOutputOperator extends AbstractFileOutputOperator< HashMap< String, Integer > >
{
	private static final Logger logger = LoggerFactory.getLogger( MapReducerFileOutputOperator.class );
	private static final String CHARSET_NAME = "UTF-8";
	private static final String NL = System.lineSeparator();

	@NotNull
	private String fileName;

	private transient String fName;

	@Override
	public void setup( Context.OperatorContext context )
	{
		// create file name for this partition by appending the operator id to
		// the base name
		//
		long id = context.getId();
		fName = fileName + "_p" + id;
		super.setup( context );

		logger.debug( "Leaving setup, fName = {}, id = {}", fName, id );
	}

	@Override
	protected String getFileName( HashMap< String, Integer > tuple )
	{
		return fName;
	}

	@Override
	protected byte[] getBytesForTuple( HashMap< String, Integer > tuple )
	{
		byte result[] = null;
		try
		{
			result = ( tuple.toString() + NL ).getBytes( CHARSET_NAME );
		} catch( Exception e )
		{
			logger.info( "Error: got exception {}", e );
			throw new RuntimeException( e );
		}
		return result;
	}

	// getters and setters
	public String getFileName()
	{
		return fileName;
	}

	public void setFileName( String v )
	{
		fileName = v;
	}

}
