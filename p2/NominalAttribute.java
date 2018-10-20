/*
 * NominalAttribute.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Collections;
import java.util.ArrayList;

/**
 * Stores information for a nominal attribute. A nominal attribute has has a name and a domain.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-20
 */
public class NominalAttribute extends Attribute {

	/** a list of strings for the domain of nominal attributes */
	ArrayList<String> domain;
	
	/**
	 * Default constructor.
	 */
	public NominalAttribute() {
		
	}

	/**
	 * Explicit constructor. Creates a nominal attribute with the specified name.
	 * 
	 * @param name - the name of this data set
	 */
	public NominalAttribute( String name ) {
		super(name);
		domain = new ArrayList<String>();
	}

	/**
	 * Adds a new nominal value to the domain of this nominal attribute.
	 * 
	 * @param value - the attribute's new domain value
	 */
	public void addValue( String value ) {
		domain.add(value);
	}

	/**
	 * Gets the size of this nominal attribute's domain.
	 * 
	 * @return an int storing the size of the domain
	 */
	public int size() {
		return domain.size();
	}

	/**
	 * Returns the value of this nominal attribute at the specified index.
	 * 
	 * @param index - the attribute value's index
	 * @return the attribute value at the specified index
	 */
	public String getValue( int index ) {
		return domain.get(index);
	}

	/**
	 * Returns the index of the specified value index for this nominal attribute.
	 * 
	 * @param value - the attribute's value
	 * @return the index of the specified value
	 * @throws Exception - if the value is not in the domain
	 */
	public int getIndex( String value ) throws Exception {
		int index = -1;
	  
		try {
			for(int i = 0; i < domain.size(); i++) {
				if( value.equals( domain.get(i) ) ) {
					index = i;
				}
			}
		} // Try
		catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		} // Catch

		if(index == -1) {
			throw new Exception("The nomimal attribte value " + value + " doesn't exist");
		}

		return index;
	}

	/**
	 * Returns a string representation of this NominalAttribute.
	 * 
	 * @return a string representation of this NominalAttribute
	 */
	public String toString() {
		StringBuilder stringAttribute = new StringBuilder(super.toString());

		for( String value : domain )
			stringAttribute.append(" " + value);

		return stringAttribute.toString();
	}

	/**
	 * Returns whether the value is valid for a nominal attribute
	 * 
	 * @param value - the value for testing
	 * @return true if the value is valid; false otherwise
	 */
	public boolean validValue( String value ) {
		boolean valid = false;
		for( String domainValue : domain ) {
			if( value.equals(domainValue) ) {
				valid = true;
				break;
			}
		}
		return valid;
	}

	public static void main( String args[] ) {
		// main function
	}
}
