/*
 * NaiveBayes.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class NaiveBayes extends Classifier implements Serializable, OptionHandler;
  protected Attributes attributes;
  protected CategoricalEstimator classDistribution;
  protected ArrayList< ArrayList<Estimator> > classConditionalDistributions;

  public NaiveBayes();
  public NaiveBayes( String[] options ) throws Exception;
  public Performance classify( DataSet dataSet ) throws Exception;
  public int classify( Example example ) throws Exception;
  public public Classifier clone();
  public double[] getDistribution( Example example ) throws Exception;
  public void setOptions( String[] options );
  public void train( DataSet dataset ) throws Exception;
