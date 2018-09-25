/*
 * Attribute.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

/**
 * Stores information for an attribute. An attribute has a name.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-18
 */
public class Attribute extends Object {
	/** Stores information for an attribute. An attribute has a name. */
	protected String name;
 
 	/**
 	 * Default constructor.
 	 */
	public Attribute() {
		this.name = "";
	}

	/**
	 * Explicit constructor that sets the name of this attribute.
	 * 
	 * @param name - the name of this attribute
	 */
	public Attribute( String name ) {
		this.name = name;
	}

	/**
	 * Gets the name of this attribute.
	 * 
	 * @return a string storing the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the size of this attribute's domain.
	 * 
	 * @return an int storing the size of the domain
	 */
	public int size() {
		return Integer.MAX_VALUE;
	}

	/**
	 * Sets the name of this attribute to the specified name.
	 * 
	 * @param name - the name of this attribute
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * Returns a string representation of this attribute.
	 * 
	 * @return a string representation of this attribute
	 */
	public String toString() {
		return name;
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
