/*
 * AttributeFactory.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Scanner;

/**
 * A factory for making NominalAttributes and NumericAttributes from a scanner.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-18
 */
public class AttributeFactory extends Object {

	/**
	 * Processes a single attribute declaration,
	 * consisting of the keyword &attribute, name,
	 * and either the keyword numeric or a list of nominal values.
	 *
	 * @param scanner - a scanner containing the attribute's tokens
	 * @return the constructed attribute
	 * @throws Exception - if a parse exception occurs
	 */
	public static Attribute make( Scanner scanner ) throws Exception {
		
		Attribute attribute = new Attribute();
		try {
			// Ignore the keyword @attribute
			scanner.next();
			
			String attributeName = scanner.next();
			
			// Numeric
			if( scanner.hasNext("numeric") ) {
				scanner.next();
				NumericAttribute numAtt = new NumericAttribute( attributeName );
				attribute = numAtt;
			}

			// Nominal
			else {
				NominalAttribute nomAtt = new NominalAttribute( attributeName );
				while( !scanner.hasNext("@attribute") && !scanner.hasNext("@examples") && scanner.hasNext() )
					nomAtt.addValue( scanner.next() );
					attribute = nomAtt;
			}
		} // Try
		
		catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		} // Catch

		return attribute;
	}
}