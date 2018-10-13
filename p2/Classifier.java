/*
 * Classifier.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public abstract class Classifier extends Object implements OptionHandler {
	
	/**
 	 * Default constructor.
 	 */
	public Classifier() {

	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param  options   - options for contruction
	 * @throws Exception - if options are invalid
	 */
	public Classifier( String[] options ) throws Exception {
		this.setOptions(options);
	}

	abstract public Performance classify( DataSet dataset ) throws Exception;
	
	abstract public int classify( Example example ) throws Exception;
	
	public abstract Classifier clone();
	
	abstract public double[] getDistribution( Example example ) throws Exception;
	
	public void setOptions( String[] options ) {

	}
	
	/**
	 * Construct string from this class object
	 * 
	 * @return a string representing this object
	 */
	public String toString() {
		return "Classifier Object";
	}
	
	abstract public void train( DataSet dataset ) throws Exception;
}
