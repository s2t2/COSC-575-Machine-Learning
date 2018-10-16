/*
 * Performance.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

/**
 * Performance Class
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public class Performance extends Object {
	/** Attribute of dataset evaluated in this perofrmance object */
	private Attributes attributes;
	/** Confusion matrix */
	private int[][] confusionMatrix;
	/** Number of corrected prediction */
	private int corrects = 0;
	/** Sum of accuracy if performance was added by other performance objects */
	private double sum = 0.0;
	/** Sum of square of accuracy  */
	private double sumSqr = 0.0;
	/** Number of class labels */
	private int c;
	/** Number of predictions */
	private int n = 0;
	/** Number of additions to this performance object */
	private int m = 0;

	/**
	 * Default constructor.
	 * 
	 * @param  attributes - attributes of dataset
	 * @throws Exception 
	 */
	public Performance( Attributes attributes ) throws Exception {
		this.attributes = attributes;
	  	c = attributes.getClassAttribute().size(); // Set number of class labels
	}

	/**
	 * Add a prediction
	 * 
	 * @param actual - the index of actual class
	 * @param prediction - the distribution over class labels predicted by a Classifier.getDistribution()
	 */
	public void add( int actual, double[] predictions ) {
		// Check actual and predicted index of class label
		if(actual == Utils.maxIndex(predictions)) corrects++;
		n++;
	}

	/**
	 * Add performance with another performance object
	 * 
	 * @param  p - another performance object
	 * @throws Exception 
	 */
	public void add( Performance p ) throws Exception {
		this.corrects += p.corrects;
		this.n += p.n;
		this.sum += p.getAccuracy();
		this.sumSqr += p.getAccuracy() * p.getAccuracy();
		this.m += 1;
	}

	/**
	 * Compute accuracy
	 * 
	 * @return a double indicating accuracy of this performance object
	 */
	public double getAccuracy() {
		double accuracy;

		// Simple evaluation
		if( m < 2 )
			accuracy = corrects/(double) n;
		// K-Fold cross validaiton
		else
			accuracy = sum / m;

		return accuracy;
	}

	/**
	 * Compute Standard Deviation of accuracy
	 * 
	 * @return SD of accuracy
	 */
	public double getSDAcc() {
		double variance;

		// Simple evaluation
		if( m < 2 )
			variance = (sumSqr - (Math.pow(sum, 2)/n)) / (n - 1);
		// K-Fold cross validaiton
		else
			variance = (sumSqr - (Math.pow(sum, 2)/m)) / (m - 1);
		
		return Math.sqrt(variance);
	}

	/**
	 * Information String of this performance object
	 * 
	 * @return a string containing information
	 */
	public String toString() {
		StringBuilder performanceString = new StringBuilder();

		// Number of fold
		performanceString.append( "Number of fold: " + Integer.toString(this.m) + "\n" );
		// Number of corrects
		performanceString.append( "Corrects: " + Integer.toString(this.corrects) + "\n" );
		// Number of examples
		performanceString.append( "Total examples: " + Integer.toString(this.n) + "\n" );
		// Accuracy
		performanceString.append( "Accuracy: " + Double.toString(this.getAccuracy()) );
		// SD of accuracy
		performanceString.append( " (+-" + Double.toString(this.getSDAcc()) + ")" );

		return performanceString.toString();
	}
}