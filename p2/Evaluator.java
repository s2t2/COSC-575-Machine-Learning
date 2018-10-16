/*
 * Evaluator.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Random;
import java.util.ArrayList;

/**
 * Evaluator for evaluate performance of a classifier
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public class Evaluator implements OptionHandler {
	/** Seed number for consistent random */
	private long seed = 2026875034;
	/** Random object */
	private Random random;
	/** Default fold number */
	private int folds = 10;
	/** Classifier */
	private Classifier classifier;
	/** Train and test sets */
	private TrainTestSets tts;

	/**
	 * Default constructor
	 */
	public Evaluator() throws Exception {
		this.random = new Random(this.seed);
	}

	/**
	 * Constructor with specific classifier and option arguments
	 * 
	 * @param  classifier - set classifier
	 * @param  options - options from command line
	 * @throws Exception
	 */
	public Evaluator( Classifier classifier, String[] options ) throws Exception {
		this.classifier = classifier;
		this.classifier.setOptions(options);
		this.tts = new TrainTestSets(options);
		this.setOptions(options);
		this.random = new Random(this.seed);
	}

	/**
	 * Evaluate the dataset using the given classifier
	 * If both train and test sets are provided, then do simple evaluation
	 * If only train set is provided, then do k-fold cross validation
	 * 
	 * @return the Performace object of the evaluation
	 * @throws Exception 
	 */
	public Performance evaluate() throws Exception {
		Performance performance = new Performance( tts.getTrainingSet().getAttributes() );
		
		// Check testing set
		// If testing set is provided, then run simple evaluation
		if( tts.getTestingSet().name != null ) {
			// Train
			classifier.train(tts.getTrainingSet());
			// Test
			performance = classifier.classify( tts.getTestingSet() );
		}

		// No testing set
		else {
			// Set random to be the same random and same seed
			tts.getTrainingSet().setRandom(this.random);
			// K-fold cross validation
			tts.getTrainingSet().setFolds(folds); // Do partitioning
			for(int i = 0; i < folds; i++) {
				TrainTestSets partitionedDataset = tts.getTrainingSet().getCVSets(i);
				// Train
				classifier.train( partitionedDataset.getTrainingSet() );
				// Test
				Performance testPerformance = classifier.classify( partitionedDataset.getTestingSet() );
				performance.add( testPerformance );
			}
		}

		return performance;
	}
	
	/**
	 * Get seed number
	 * 
	 * @return seed number
	 */
	public long getSeed() {
		return this.seed;
	}

	/**
	 * Set option from command line
	 * 
	 * @param  args[] - arguments from command line
	 * @throws Exception - if cannot parse interger
	 */
	public void setOptions( String args[] ) throws Exception {
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-x") && args.length > i+1)
				this.folds = Integer.parseInt( args[i+1] );
		}
	}

	/**
	 * Set seed number
	 * 
	 * @param seed - seed number
	 */
	public void setSeed( long seed ) {
		this.seed = seed;
	}

}

