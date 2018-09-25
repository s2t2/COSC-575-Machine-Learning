/*
 * DataSet.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Implements a class for a data set for machine-learning methods.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-18
 */
public class DataSet {
	/** the name of this data set */
	protected String name = null;
	/** the attributes of this data set */
	protected Attributes attributes = null;
	/** the examples of this data set */
	protected Examples examples = null;
	/** a random-number generator */
	protected Random random;

	/**
	 * Default constructor.
	 */
	public DataSet() {
		attributes = new Attributes();
		examples = new Examples(attributes);
	}

	/**
	 * Explicit constructor.
	 * 
	 * @param options - the options for this data set
	 */
	public DataSet( String[] options ) {
		attributes = new Attributes();
		examples = new Examples(attributes);

		// Don't need to implement this
	}

	/**
	 * Explicit constructor.
	 * 
	 * @param attributes - the attributes for this data set
	 */
	public DataSet( Attributes attributes ) {
		this.attributes = attributes;
		examples = new Examples(this.attributes);
	}

	/**
	 * Adds the specified example to this data set.
	 * 
	 * @param example - the example to be added
	 */
	public void add( Example example ) {
		examples.add(example);
	}

	/**
	 * Gets the attributes of this DataSet object.
	 * 
	 * @return the attributes of this data set
	 */
	public Attributes getAttributes() {
		return attributes;
	}

	/**
	 * Gets the examples of this data set.
	 * 
	 * @return the examples of this data set
	 */
	public Examples getExamples() {
		return examples;
	}

	/**
	 * Returns true if this data set has numeric attributes; returns false otherwise.
	 * 
	 * @return true if this data set has numeric attributes
	 */
	public boolean getHasNumericAttributes() {
		return attributes.getHasNumericAttributes();
	}

	/**
	 * Loads a data set from the specified file.
	 * 
	 * @param filename - the file from which to read
	 * @throws Exception - if the file is not found or if a parsing exception occurs
	 */
	public void load( String filename ) throws Exception {
		try {
			Scanner scanner = new Scanner(new File(filename));
			parse(scanner);
		} // Try
	  	catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
	  	} // Catch
	}

	/**
	 * Parses a data set from the specified scanner, which consists of
	 * parsing the data set's header, attributes, and examples.
	 * 
	 * @param scanner - a scanner containing the data set's tokens
	 * @throws Exception - if a parsing exception occurs
	 */
	private void parse( Scanner scanner ) throws Exception {
		try {
			if( !scanner.next().equals("@dataset") )
				throw new Exception("Parse error. First token is not @dataset");

			// Set dataset name
			name = scanner.next();

			// The following of scanner are attributes
			attributes.parse(scanner);
			// The last part of scanner are examples
			examples.parse(scanner);

	  } // Try
	  catch ( Exception e ) {
	      System.out.println( e.getMessage() );
	      e.printStackTrace();
	  } // Catch
	}

	/**
	 * Sets the random-number generator for this data set.
	 * 
	 * @param random - the random-number generator
	 */
	public void setRandom( Random random ) {
		this.random = random;
	}

	/**
	 * Returns a string representation of the data set
	 * in a format identical to that of the file format.
	 * 
	 * @return a string containing data set
	 */
	public String toString() {
		StringBuilder stringDataSet = new StringBuilder();

		// Dataset line
		stringDataSet.append("@dataset " + this.name + "\n\n");
		// Attributes lines
		stringDataSet.append(attributes.toString() + "\n");
		// Examples lines
		stringDataSet.append(examples.toString());

		return stringDataSet.toString();
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