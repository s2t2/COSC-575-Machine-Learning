/*
 * Estimator.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public abstract class Estimator extends Object {
	protected int n = 0; // number of samples

	/**
 	 * Default constructor.
 	 */
	public Estimator() {
		this.n = 0;
	}

	abstract public void add( Number x ) throws Exception;
	
	public Integer getN() {
		return this.n;
	}
	
	abstract public Double getProbability( Number x );
}
