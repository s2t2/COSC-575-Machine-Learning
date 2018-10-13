/*
 * IBk.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class IBk extends Classifier implements Serializable, OptionHandler {
	protected DataSet dataset;
	protected Scaler scaler;
	protected int k = 3;

	public IBk();
	public IBk( String[] options ) throws Exception;
	public Performance classify( DataSet dataset ) throws Exception;
	public int classify( Example query ) throws Exception;
	public Classifier clone();
	public double[] getDistribution( Example query ) throws Exception;
	public void setK( int k );
	public void setOptions( String args[] );
	public void train( DataSet dataset ) throws Exception;
}
