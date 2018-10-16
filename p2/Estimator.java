/*
 * Estimator.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

/**
 * Abstract class for an estimator
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public abstract class Estimator extends Object {
	/* number of samples */
	protected int n = 0;

	/**
 	 * Default constructor.
 	 */
	public Estimator() {
		this.n = 0;
	}

	abstract public void add( Number x ) throws Exception;
	
	/**
	 * Get number of samples in this estimator
	 * 
	 * @return number of estomator
	 */
	public Integer getN() {
		return this.n;
	}
	
	abstract public Double getProbability( Number x );
}
