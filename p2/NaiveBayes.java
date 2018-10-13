/*
 * NaiveBayes.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class NaiveBayes extends Classifier implements Serializable, OptionHandler {
	/* attributes of the dataset */
	protected Attributes attributes;
	/* distribution of class labels */
	protected CategoricalEstimator classDistribution;
	/* distribution of all attributes for all possible class labels */
	protected ArrayList< ArrayList<Estimator> > classConditionalDistributions;

	/**
	 * Default constructor
	 */
	public NaiveBayes() {
		this.classDistribution = null;
		this.classConditionalDistributions = null;
	}

	/**
	 * Constructor with options
	 */
	public NaiveBayes( String[] options ) throws Exception {
		this.classDistribution = null;
		this.classConditionalDistributions = null;
		this.setOptions(options);
	}

	/**
	 * Classify all examples in the given dataset
	 * 
	 * @param  dataSet   - a DataSet object
	 * @return           - a performance object of this classifier
	 * @throws Exception 
	 */
	public Performance classify( DataSet dataSet ) throws Exception {
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
	 * @param  example   - an example to classify class label
	 * @return           - index of the predicted class label
	 * @throws Exception 
	 */
	public int classify( Example example ) throws Exception {
		return Utils.maxIndex(this.getDistribution(example));
	}

	public public Classifier clone();

	/**
	 * Get distribution of predicted class labels given an example
	 * 
	 * @param  example   - an example
	 * @return           - a distribution of predicted class labels
	 * @throws Exception 
	 */
	public double[] getDistribution( Example example ) throws Exception {
		double[] classProbDist = new double[this.classConditionalDistributions.size()];
		double probProduct;

		// All classes
		for(int i = 0; i < this.classConditionalDistributions.size(); i++) {
			double probProduct = 1.0;

			// All attributes
			for(int j = 0; j < this.classConditionalDistributions.get(i).size(); j++) {
				probProduct *= this.classConditionalDistributions.get(j).getProbability(example.get(j));
			}

			classProbDist[i] = probProduct * this.classDistribution.getProbability(i);
		}

		return classProbDist;
	}

	/**
	 * Option setting for this class
	 * @param options - option list string
	 */
	public void setOptions( String[] options ) {

	}

	public void train( DataSet dataset ) throws Exception {
		this.attributes = dataset.getAttributes();
		this.classDistribution = new CategoricalEstimator(this.attributes.size());
		this.classConditionalDistributions = new ArrayList< ArrayList<Estimator> >();

		// For all possible class labels
		for(int i = 0; i < this.attributes.getClassAttribute().size(); i++) {
			this.classConditionalDistributions.add(new ArrayList<Estimator>());

			// For all attributes
			for(int j = 0; j < this.attributes.size(); j++) {

				// Numeric
				if(attributes.get(j) instanceof NumericAttribute) {
					this.classConditionalDistributions.get(i).add(new GaussianEstimator());
				}

				// Nominal
				else {
					this.classConditionalDistributions.get(i).add(new CategoricalEstimator(this.attributes.get(i).size()));
				}
			}
		}

		// Feed all estimators with all examples
		int classIndex = dataset.getAttributes().getClassIndex();
		for(Example example : dataset.getExamples()) {
			for(int i = 0; i < this.attributes.size(); i++) {

				int actualClassLabel = example.get(classIndex).intValue();
				
				// Other attributes than class label
				if(i != classIndex) {
					this.classConditionalDistributions.get(actualClassLabel).get(i).add(example.get(i));
				}

				// Class label
				else {
					this.classDistribution.add(actualClassLabel);
				}
			}
		}
	}

	/**
	 * Main function for test this class
	 * 
	 * @param args - arguments for creating an evaluator
	 */
	public static void main(String[] args) {
		try {
			Evaluator evaluator = new Evaluator(new NaiveBayes(), args);
			Performance performace = evaluator.evaluate();
			System.out.println(performace.toString());
		} // try
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // catch
	}
}
