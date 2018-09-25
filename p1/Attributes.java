/*
 * Attributes.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Stores information for attributes for data sets for machine learning.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-18
 */
public class Attributes {
	/** a list of attributes */
	private ArrayList<Attribute> attributes;
	/** a flag indicating that the data set has one or more numeric attributes */
	private boolean hasNumericAttributes;
	/** stores the position of the class label */
	private int classIndex;

	/**
	 * Default constructor.
	 */
	public Attributes() {
		attributes = new ArrayList<Attribute>();
		hasNumericAttributes = false;
	}

	/**
	 * Adds a new attribute to this set of attributes.
	 *
	 * @param attribute - the attribute's name
	 */
	public void add( Attribute attribute ) {
		attributes.add(attribute);

		// Need to update if the new attribute is numberical
		if( attribute instanceof NumericAttribute ) hasNumericAttributes = true;
	}

	/**
	 * Returns the index of the class label.
	 *
	 * @return the index of the class label
	 */
	public int getClassIndex() {
		return classIndex;
	}

	/**
	 * Returns true if this set of attributes has one or more numeric attributes; returns false otherwise.
	 * 
	 * @return true if this set of attributes has numeric attributes
	 */
	public boolean getHasNumericAttributes() {
		return hasNumericAttributes;
	}

	/**
	 * Returns the ith attribute in this set of attributes.
	 * 
	 * @param i - the index of the specified attribute
	 * @return the ith Attribute
	 */
	public Attribute get( int i ) {
		return attributes.get(i);
	}

	/**
	 * Returns the class attribute.
	 * 
	 * @return the class attribute
	 */
	public Attribute getClassAttribute() {
		return attributes.get(classIndex);
	}

	/**
	 * Returns the attribute's index.
	 * 
	 * @param name - the attribute's name
	 * @return the attribute's position in the names array
	 * @throws Exception - if the attribute does not exist
	 */
	public int getIndex( String name ) throws Exception {
		int index = -1;
		try {
			for(int i = 0; i < attributes.size(); i++ ) {
				if(name.equals(attributes.get(i).getName())) {
					index = i;
					break;
				}
			}
		} // Try
		
		catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		} // Catch

		// If not matched
		if(index == -1) {
			throw new Exception("The attribte named " + name + " doesn't exist");
		}

		// If matched
		return index;
	}

	/**
	 * Returns the number of attributes.
	 * 
	 * @return the number of attributes
	 */
	public int size() {
		return attributes.size();
	}

	/**
	 * Parses the attribute declarations in the specified scanner.
	 * By convention, the last attribute is the class label after parsing.
	 * 
	 * @param scanner - a Scanner containing the data set's tokens
	 * @throws Exception - if a parse exception occurs
	 */
	public void parse( Scanner scanner ) throws Exception {
		while( scanner.hasNext("@attribute") ) {
			Attribute attribute = AttributeFactory.make(scanner);
			attributes.add(attribute);

			// Update if numeric attribute is found
			if( attribute instanceof NumericAttribute ) hasNumericAttributes = true;
		}

		// Set the last attribute to be class label
		setClassIndex( attributes.size() - 1 );
	}

	/**
	 * Sets the class index for this set of attributes.
	 * 
	 * @param classIndex - the new class index
	 * @throws Exception - if the class index is out of of bounds
	 */
	public void setClassIndex( int classIndex ) throws Exception {
		if( classIndex < attributes.size() && classIndex >= 0 ) this.classIndex = classIndex;
		else {
			throw new Exception("Class index is out of of bounds");
		}
	}

	/**
	 * Returns a string representation of this Attributes object.
	 * 
	 * @return a string representation of this Attributes object
	 */
	public String toString() {
		StringBuilder stringAttributes = new StringBuilder();
		for( Attribute attribute : attributes ) {

			// Numeric
			if( attribute instanceof NumericAttribute ) {
				NumericAttribute numAtt = ( NumericAttribute ) attribute;
				stringAttributes.append( numAtt.toString() + "\n" );
			}

			// Nominal
			else {
				NominalAttribute nomAtt = ( NominalAttribute ) attribute;
				stringAttributes.append( nomAtt.toString() + "\n" );
			}
		}

		return stringAttributes.toString();
	}

	/**
	 * A main method for testing.
	 * 
	 * @param args - command-line arguments
	 */
	public static void main( String args[] ) {
		// main function
	}
}