/*
 * GaussianEstimator.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

/**
 * Gaussian Estimator Class for NumericAttribute
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public class GaussianEstimator extends Estimator {
	protected Double sum = 0.0;
	protected Double sumsqr = 0.0;
	protected final static Double oneOverSqrt2PI = 1.0/Math.sqrt(2.0*Math.PI);

	/**
	 * Default constructor.
	 */
	public GaussianEstimator() {

	}

	/**
	 * Add value to Gaussian Estimator
	 * 
	 * @param  x - value
	 * @throws Exception 
	 */
	public void add( Number x ) throws Exception {
		sum += x.doubleValue();
		sumsqr += x.doubleValue() * x.doubleValue();
		this.n++;
	}

	/**
	 * Get arithmetic mean of the distribution
	 * 
	 * @return a number indicating mean of distribution
	 */
	public Double getMean() {
		return sum / (double) n;
	}

	/**
	 * Get variance of this estimator
	 * 
	 * @return a number indicating variance
	 */
	public Double getVariance() {
		return (sumsqr - (Math.pow(sum, 2) / (n*1.0) )) / (double) (n - 1);
	}

	/**
	 * Gets the probability density of the normal distribution
	 * 
	 * @param  x - value
	 * @return - probability of this estimator
	 */
	public Double getProbability( Number x ) {
		return (oneOverSqrt2PI * (1.0/Math.sqrt(getVariance()))) * Math.exp( -1.0 * (Math.pow(x.doubleValue() - getMean(),2) / (2*getVariance()) ) );
	}
}