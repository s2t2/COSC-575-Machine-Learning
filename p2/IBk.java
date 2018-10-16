/*
 * IBk.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class IBk extends Classifier implements Serializable, OptionHandler {
	/* the training dataset */
	protected DataSet dataset;
	/* scaler to make all attribute value to be within [0,1] */
	protected Scaler scaler;
	/* number of k for k-nearest neighbor classifier */
	protected int k = 3;

	/**
	 * Default constructor
	 */
	public IBk() {
		this.dataset = null;
		this.scaler = new Scaler();
	}

	/**
	 * Constructor with options
	 * 
	 * @param  options   - option arguments
	 * @throws Exception 
	 */
	public IBk( String[] options ) throws Exception {
		this.dataset = null;
		this.scaler = new Scaler();
		this.setOptions(options);
	}

	/**
	 * Classify all examples in the given dataset
	 * 
	 * @param  dataset   - a DataSet object
	 * @return           - a performance object of this classifier
	 * @throws Exception 
	 */
	public Performance classify( DataSet dataset ) throws Exception {
		Performance performance = new Performance(dataSet.getAttributes());
		int classIndex = dataSet.getAttributes().getClassIndex();

		for(Example example : dataSet.getExamples()) {
			int actual = example.get(classIndex);
			double[] predictions = this.getDistribution();
			performance.add(actual, predictions);
		}

		return performance;
	}
	

	/**
	 * Classify class label by this classifier given an example
	 * 
	 * @param  query     - an example to classify class label
	 * @return           - index of the predicted class label
	 * @throws Exception 
	 */
	public int classify( Example query ) throws Exception {
		return Utils.maxIndex(this.getDistribution(query));
	}

	/**
	 * Clone this classifier but untrained
	 * 
	 * @return - a NaiveBayes object with untrained
	 */
	public Classifier clone() {
		IBk ibk = new IBk();
		ibk.setK(this.k); // Clear all other variables except k value

		return ibk;
	}

	/**
	 * Get distribution of predicted class labels given an example
	 * 
	 * @param  query     - an example
	 * @return           - a distribution of predicted class labels
	 * @throws Exception 
	 */
	public double[] getDistribution( Example query ) throws Exception {
		// Scale the given example
		Example scaledQuery = null; // Initial by assuming that it is nominal attribute
		if( this.dataset.getHasNumericAttributes() ) {
			scaledQuery = scaler.scale(query);
		}
		else {
			scaledQuery = query;
		}

		// Create 2d array for store distance and label for each k-nearest
		double[][] kNearestDistances = new double[this.k][2]; // store [k][distance,label]
		// Initial all distance with possible maximum double
		for(int i = 0; i < kNearestDistances.length; i++) {
			kNearestDistances[i][0] = Double.MAX_VALUE;
		}

		// Find k-nearest distance between the given query and all examples in the dataset
		for(Example example : dataset.getExamples()) {
			double distance = 0.0;

			// Compute distance between current example and all examples in the dataset
			for(int i = 0; i < example.size(); i++) {
				// Compute only other attibutes than class label
				if( i != this.dataset.getAttributes().getClassIndex() ) {
					// If nominal attribute mismatched, increase distance by 1.0
					if( this.dataset.getAttributes().get(i) instanceof NominalAttribute ) {
						if( Double.compare(scaledQuery.get(i), example.get(i)) != 0 ) {
							distance += 1.0;
						}
					}

					// If numeric attribute mismatched, increase distance by squared differences
					else if( example.getAttributes().get(i) instanceof NumericAttribute ) {
						distance += Math.pow( Math.abs(scaledQuery.get(i) - example.get(i)), 2 );
					}
				}
			}

			// Take square root to get Eucledien distance
			distance = Math.sqrt(distance);

			double maxDistance = -1.0;
			int maxIndex = -1;

			// Find the current max distance in the kNN array
			for(int i = 0; i < kNearestDistances.length; i++) {
				if( kNearestDistances[i][0] > maxDistance ) {
					maxDistance = kNearestDistances[i][0];
					maxIndex = i;
				}
			}

			// Update kNearestDistances array
			// if the current distance is less than current max distance
			// then update current distance to the array at current max index
			if( distance < maxDistance ) {
				kNearestDistances[maxIndex][0] = distance;
				kNearestDistances[maxIndex][1] = example.get( this.dataset.getAttributes().getClassIndex() );
			}
		}

		double[] classDistribution = new double[ example.getAttributes().size() ];

		// Set distribution of predicted class labels given an example
		for(int i = 0; i < kNearestDistances.length; i++) {
			classDistribution[ (int)kNearestDistances[i][1] ] += 1.0;
		}

		return classDistribution;
	}
	
	/**
	 * Set k value of k-nearest neighbor
	 * 
	 * @param k - an integer value
	 */
	public void setK( int k ) {
		this.k = k;
	}

	/**
	 * Set options for this classifier
	 * 
	 * @param args[] - option arguments
	 */
	public void setOptions( String args[] ) {
		for( int i = 0; i < args.length; i++ ) {
			if( args[i].equals("-k") && args.length > i+1 ) {
				this.setK( Integer.parseInt(args[i+1]) );
			}
		}
	}

	/**
	 * Train this classifier
	 * For k-nearest neighbor, there is no training but need to store entire dataset instead
	 * Therefore, just scale dataset if there are some numeric attributes and store them in this classifier
	 * 
	 * @param  dataset   - a training dataset
	 * @throws Exception 
	 */
	public void train( DataSet dataset ) throws Exception {
		// There are some numeric attributes
		if( dataset.getHasNumericAttributes() ) {
			this.scaler.configure(dataset);
			this.dataset = scaler.scale(dataset);
		}
		// No numeric attributes in this dataset
		else {
			this.dataset = dataset;
		}
	}

	/**
	 * Main function for test this class
	 * 
	 * @param args - option arguments
	 */
	public static void main( String[] arge ) {
		try {
			Evaluator evaluator = new Evaluator( new IBk(args), args );
			Performance performace = evaluator.evaluate();
			System.out.println( performace );
		} // try
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // catch
	}
}
