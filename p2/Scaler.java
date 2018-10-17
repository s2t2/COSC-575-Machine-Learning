/*
 * Scaler.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.ArrayList;

/**
 * Scaler Class for scale values of each example on given dataset
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public class Scaler extends Object {

	/* attributes of the dataset */
	private Attributes attributes;
	/* array list containing min value of each attribute */
	private ArrayList<Double> mins;
	/* array list containing max value of each attribute */
	private ArrayList<Double> maxs;

	/**
	 * Default constructor
	 */
	public Scaler() {
		this.mins = new ArrayList<Double>();
		this.maxs = new ArrayList<Double>();
	}

	/**
	 * Find min and max of each attribute then store in mins and maxs
	 * 
	 * @param  ds        - a DataSet object
	 * @throws Exception 
	 */
	public void configure( DataSet ds ) throws Exception {
		attributes = ds.getAttributes();
		Examples examples = ds.getExamples();

		for(int i = 0; i < attributes.size(); i++) {
			// Nominal attributes
			if( attributes.get(i) instanceof NominalAttribute ) {
				mins.add(0.0);
				maxs.add(0.0);
			}

			// Numeric attributes
			else {
				// Declare possible max and min for double
				double minValue = Double.MAX_VALUE;
				double maxValue = -Double.MAX_VALUE;

				// Run through all examples for each attribute
				for(int j = 0; j < examples.size(); j++) {
					double value = examples.get(j).get(i);	// j-th example, i-th attribute
					if(value < minValue) {
						minValue = value;
					}
					if(value > maxValue) {
						maxValue = value;
					}
				}
				mins.add(minValue);
				maxs.add(maxValue);
			}
		}
	}

	/**
	 * Scale all examples in a DataSet object to [0,1]
	 * 
	 * @param  ds        - a DataSet object
	 * @return           - a sclaled DataSet object
	 * @throws Exception 
	 */
	public DataSet scale( DataSet ds ) throws Exception {
		DataSet scaledDataSet = new DataSet(attributes);
		for(int i = 0; i < ds.getExamples().size(); i++) {
			// Scale each example then add to a scaled DataSet object
			Example scaledExample = scale(ds.getExamples().get(i));
			scaledDataSet.add(scaledExample);
		}
		return scaledDataSet;
	}

	/**
	 * Scale all attributes in an example to [0,1]
	 * 
	 * @param  example   - an example to be scaled
	 * @return           - a scaled example
	 * @throws Exception 
	 */
	public Example scale( Example example ) throws Exception {
		// Scale all attributes
		for(int i = 0; i < attributes.size(); i++) {
			// Consider only numeric attribute
			if(attributes.get(i) instanceof NumericAttribute) {
				double value = example.get(i);
				double scaledValue;

				if(value < mins.get(i)) {
					scaledValue = 0.0;
				}
				else if(value > maxs.get(i)) {
					scaledValue = 1.0;
				}
				else {
					scaledValue = (value - mins.get(i)) / (maxs.get(i) - mins.get(i));
				}
				example.set(i, scaledValue); // Set scaled value back to the example
			}
		}
		return example;
	}
}