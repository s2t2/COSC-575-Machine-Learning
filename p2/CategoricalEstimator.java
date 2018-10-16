/*
 * CategoricalEstimator.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Collections;
import java.util.ArrayList;

/**
 * Categorical Estimator Class
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public class CategoricalEstimator extends Estimator {
	protected ArrayList<Integer> dist;

	/**
 	 * Default constructor.
 	 */
	public CategoricalEstimator() {
		this.dist = new ArrayList<Integer>();
	}

	/**
	 * Constructor
	 * 
	 * @param  k - number of categories
	 */
	public CategoricalEstimator( Integer k ) {
		this.dist = new ArrayList<Integer>(k);
		for(int i = 0; i < k; i++) {
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
		return (double) (this.dist.get(x.intValue()) + 0.1) / (double) (n + 0.1);
	}

}