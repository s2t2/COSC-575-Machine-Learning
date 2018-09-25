/*
 * NumericAttribute.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Collections;
import java.util.ArrayList;

/**
 * Stores information for a numeric attribute.
 * A numeric attribute has a name. Its domain is the real numbers.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-20
 */
public class NumericAttribute extends Attribute {
	
	/**
	 * Default constructor.
	 */
	public NumericAttribute() {
		super();
	}

	/**
	 * Explicit constructor. Creates a numeric attribute set of the specified name.
	 * 
	 * @param name - the name of this data set
	 */
	public NumericAttribute( String name ) {
		this.name = name;
	}

	/**
	 * Returns a string representation of this NumericAttribute.
	 * 
	 * @return a string representation of this NumericAttribute
	 */
	public String toString() {
		String stringAttribute = "@attribute " + this.name + " numeric";
		return stringAttribute;
	}

	/**
	 * Returns whether the specified value is valid for a numeric attribute.
	 * 
	 * @param value - the value for testing
	 * @return true if the value is valid; false otherwise
	 */
	public boolean validValue( Double value ) {
		return true;
	}

	public static void main( String args[] ) {
		// main function
	}
}
