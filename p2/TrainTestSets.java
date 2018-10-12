/*
 * TrainTestSets.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

/**
 * Implements a class for storing training and testing sets for machine-learning methods.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-20
 */
public class TrainTestSets implements OptionHandler {
	
	/** the training examples */
	protected DataSet train;
	/** the testing examples */
	protected DataSet test;

	/**
	 * Default constructor.
	 */
	public TrainTestSets() {
		train = new DataSet();
		test = new DataSet();
	}

	/**
	 * Explicit constructor that processes the specified arguments.
	 * 
	 * @param options - the arguments for this train/test set
	 * @throws Exception - if the file is not found or if a parsing exception occurs
	 */
	public TrainTestSets( String [] options ) throws Exception {
		train = new DataSet();
		test = new DataSet();
		this.setOptions(options);
	}

	/**
	 * Explicit constructor that sets the training and testing sets to the specified data sets.
	 * 
	 * @param train - the training set
	 * @param test - the testing set
	 */
	public TrainTestSets( DataSet train, DataSet test ) {
		this.train = train;
		this.test = test;
	}

	/**
	 * Returns the training set of this train/test set.
	 * 
	 * @return a training set
	 */
	public DataSet getTrainingSet() {
		return train;
	}

	/**
	 * Returns the testing set of this train/test set.
	 * 
	 * @return a testing set
	 */
	public DataSet getTestingSet() {
		return test;
	}

	/**
	 * Sets the training set of this train/test set to the specified data set.
	 * 
	 * @param train - the specified training set
	 */
	public void setTrainingSet( DataSet train ) {
		this.train = train;
	}

	/**
	 * Sets the testing set of this train/test set to the specified data set.
	 * 
	 * @param test - the specified testing set
	 */
	public void setTestingSet( DataSet test ) {
		this.test = test;
	}

	/**
	 * Sets the options for this train/test set.
	 * The -t option loads the data set with the specified file name as the training set.
	 * The -T option loads the data set with the specified file name as the testing set.
	 * 
	 * @param options - the arguments
	 * @throws Exception - if the file is not found or if a parsing exception occurs
	 */
	public void setOptions( String[] options ) throws Exception {
		try {
			for( int i = 0; i < options.length; i++ ) {
				String option = options[i];

				// Train data loading
				if( option.equals("-t") ) {
					if( options.length > i + 1 ) {
						train.load(options[i+1]);
					}
					else {
						throw new Exception("Training file name is missing");
					}
				}

				// Test data loading
				if( option.equals("-T") ) {
					if( options.length > i + 1 )
						test.load(options[i+1]);
					else {
						throw new Exception("Testing file name is missing");
					}
				}
			}
		} // Try
		catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		}
	}

	/**
	 * Returns a string representation of this train/test set in a format
	 * similar to that of the file format. Includes the testing examples if present.
	 * 
	 * @return a string containing the attributes information and examples
	 */
	public String toString() {
		StringBuilder trainTestString = new StringBuilder();

		// Train dataset
		String trainString = ( train.name != null ) ? train.toString() : "<Empty training set>";
		trainTestString.append(trainString);

		// Test dataset
		String testString = ( test.name != null ) ? test.toString() : "<Empty testing set>";
		trainTestString.append("\n\n" + testString);
		
		return trainTestString.toString();
	}
}