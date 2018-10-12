/*
 * CategoricalEstimator.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class CategoricalEstimator extends Estimator {
	protected ArrayList<Integer> dist;

	/**
 	 * Default constructor.
 	 */
	public CategoricalEstimator();

	/**
	 * Constructor
	 * 
	 * @param  k - number of categories
	 */
	public CategoricalEstimator( Integer k ) {
		super();
		this.dist = new ArrayList<Integer>(k);
		for(int i = 0; i < this.dist.size(); i++) {
			this.dist.add(0);
		}
	}

	/**
	 * Add one to the category
	 * 
	 * @param  x - an index of category
	 * @throws Exception
	 */
	public void add( Number x ) throws Exception {
		int index = x.intValue();
		this.dist.set(index, this.dist.get(index) + 1); // Increment category by one
		this.n++;
	}

	/**
	 * Gets probability using add one smoothing
	 * 
	 * @param  x - an index of the category that want to get probability
	 * @return a number of probability
	 */
	public Double getProbability( Number x ) {
		return (double) ( dist.get(x.intValue()) + 1 ) / (double) (n);
	}

}