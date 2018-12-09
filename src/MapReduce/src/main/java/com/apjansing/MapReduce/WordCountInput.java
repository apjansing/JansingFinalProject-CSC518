/**
 * Put your copyright and license info here.
 */
package com.apjansing.MapReduce;

import java.util.Scanner;

import org.apache.apex.malhar.lib.io.SimpleSinglePortInputOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple operator that emits random number.
 */
public class WordCountInput extends SimpleSinglePortInputOperator< String > implements Runnable
{
	final private static Logger logger = LoggerFactory.getLogger( WordCountInput.class );
	private String fileName = "MobyDick.txt";

	public String getFilename()
	{
		return fileName;
	}

	public void setFilename( String filename )
	{
		this.fileName = filename;
	}

	@Override
	public void run()
	{
		try( Scanner sc = new Scanner( this.getClass().getClassLoader().getResourceAsStream( fileName ) ) )
		{
			while( sc.hasNext() )
			{
				String line = sc.nextLine();
				String[] words = line.trim().split( "[\\p{Punct}\\s\\\"\\'“”]+" );
				for ( String word : words )
				{
					word = word.trim().toLowerCase();
					if ( !word.isEmpty() )
						outputPort.emit( word );
				}
			}
		}

	}
}