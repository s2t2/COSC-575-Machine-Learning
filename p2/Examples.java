/*
 * Examples.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Stores examples for data sets for machine learning.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-18
 */
public class Examples extends ArrayList<Example> {
	/** the attributes structure for these examples */
	private Attributes attributes;

	/**
	 * Explicit constructor.
	 * 
	 * @param attributes - the attributes for this set of examples
	 */
	public Examples( Attributes attributes ) {
		super();
		this.attributes = attributes;
	}

	/**
	 * Given the attributes structure, parses the tokens in
	 * the scanner, makes Examples, and adds them to this Examples object.
	 * 
	 * @param scanner - a Scanner containing the examples' tokens
	 * @throws Exception - if an index is out of bounds or if a parse error occurs
	 */
	public void parse( Scanner scanner ) throws Exception {
		try {
			// Find @example tag
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if( line.equals("@examples") ) break;
			}

			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();

				// Skip empty line
				if( line.equals("") ) {
					continue;
				}

				String[] tokens = line.split(" ");
			    Example example = new Example(tokens.length);

			    for(int i = 0; i < tokens.length; i++) {
			    	// Numeric
					if( attributes.get(i) instanceof NumericAttribute ) {
						example.add( Double.parseDouble(tokens[i]) );
					}

					// Nominal
					else {
						NominalAttribute nomAtt = (NominalAttribute) attributes.get(i);
						example.add( (double)nomAtt.getIndex(tokens[i]) );
					}
			    }
			    this.add(example);
			}
		} // Try

		catch( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		} // Catch
	}

	/**
	 * Returns a string representation of this Examples object.
	 * 
	 * @return a string representation of this Examples object
	 */
	public String toString() {
		StringBuilder examplesString = new StringBuilder("@examples" + "\n\n");

		// For all examples
		for( Example example : this ) {
			// For each attribute of an example
			for( int i = 0; i < example.size(); i++ ) {

				// When the value is not the first attribute
				if( i > 0 ) {
					examplesString.append(" ");
				}

				// Numeric
				if( attributes.get(i) instanceof NumericAttribute ) {
					examplesString.append(Double.toString(example.get(i)));
				}

				// Nominal
				else {
					// To get value, we need index of the value and the nominal type
					int index = example.get(i).intValue();
					NominalAttribute nomAtt = (NominalAttribute) attributes.get(i);
					examplesString.append(nomAtt.getValue(index));
			  }
			}

			examplesString.append("\n");
		}

		return examplesString.toString();
	}
}