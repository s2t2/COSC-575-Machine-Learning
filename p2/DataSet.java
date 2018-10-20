/*
 * DataSet.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

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
	/** default fold number for k-fold cross validation */
	protected int folds = 10;
	/** a list of index indicates testing set for that partition number */
  	protected int[] partitions = null;

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
	 * Get TrainTestSets from partitioned dataset
	 * 
	 * @param  p - a partition number, must not exceed fold number
	 * @return a TrainTestSets partitioned from entire dataset corresponding to the partition number
	 * @throws Exception - if the partition number exceeds the fold number
	 */
	public TrainTestSets getCVSets( int p ) throws Exception {
		try {
			TrainTestSets tts = new TrainTestSets();
			DataSet train = new DataSet(this.attributes);
			DataSet test = new DataSet(this.attributes);

			for (int i = 0; i < this.examples.size(); i++) {
				if (this.partitions[i] == p) {
					test.add(this.examples.get(i));
				}
				else {
					train.add(this.examples.get(i));
				}
			}

			tts.setTrainingSet(train);
			tts.setTestingSet(test);

			return tts;
		} // Try
		catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
		} // Catch

		return null;
	}
	
	/**
	 * Return fold number for cross validation
	 * 
	 * @return an int indicate fold number
	 */
	public int getFolds() {
		return this.folds;
	}

	/**
	 * Set fold number and also separate dataset into partitions
	 * Each number in partitions indicates that the example at that index
	 * is in testing set for that partition number
	 * 
	 * @param  folds - fold number and also number of partitions
	 * @throws Exception - folds number should not exceed the example number
	 */
	public void setFolds( int folds ) throws Exception {
		if(folds > this.examples.size()) {
			throw new Exception("folds number must be less than example number.");
		}

		try {
			this.folds = folds;
			this.partitions = new int[this.examples.size()];

			// Randomly assign testing set to examples with partition number
			// We need to keep ratio of all partitions to be equally assigned
			// Eg. partitions[i] = 3 means the examples[i] is in testing set for partition number 3
			int maxExamplesEachPartition = (int) Math.ceil( this.examples.size() / (double) folds );
			// Eg. exampleNumberInPartitions[i] = 10 means partition number i-th has 10 examples
			HashMap<Integer,Integer> exampleNumberInPartitions = new HashMap<Integer,Integer>();
			for(int i = 0; i < folds; i++) {
				exampleNumberInPartitions.put(i, 0);
			}

			// Remaining partition indices
			ArrayList<Integer> availablePartitionNumbers = new ArrayList<Integer>();
			for(int i = 0; i < folds; i++) {
				availablePartitionNumbers.add(i);
			}

			// Available partition number size
			int availablePartitionNumberSize = availablePartitionNumbers.size();

			// Randomly assign partition index to partitions[i]
			for(int i = 0; i < partitions.length; i++) {
				int partitionIndex = availablePartitionNumbers.get( random.nextInt( availablePartitionNumberSize ) );
				partitions[i] = partitionIndex;

				// Increment example number of the partition
				int incremented = exampleNumberInPartitions.get(partitions[i]) + 1;
				exampleNumberInPartitions.put(partitions[i], incremented);
				
				// If the example number in a particular partition is exceeded the maximum
				// Remove the paritition number from available partition numbers
				if( exampleNumberInPartitions.get(partitions[i]) >= maxExamplesEachPartition ) {
					int index = availablePartitionNumbers.indexOf(partitions[i]);
					availablePartitionNumbers.remove(index);
					availablePartitionNumberSize--;
				}
			}

		} // Try
	  	catch ( Exception e ) {
			System.out.println( e.getMessage() );
			e.printStackTrace();
	  	} // Catch
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