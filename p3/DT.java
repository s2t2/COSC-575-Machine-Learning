/*
 * DT.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class DT extends Classifier implements Serializable, OptionHandler;

  protected Attributes attributes;
  protected Node root;

  public DT();
  public DT( String[] options ) throws Exception;
  public Performance classify( DataSet ds ) throws Exception;
  public int classify( Example example ) throws Exception;
  public double[] getDistribution( Example example ) throws Exception;
  public void prune() throws Exception;
  public void setOptions( String[] options ) throws Exception;
  public void train( DataSet ds ) throws Exception;

  // private recursive methods

  private double[] getDistribution(Node node, Example example) throws Exception;
  private double prune( Node node ) throws Exception;
  private Node train_aux( DataSet ds ) throws Exception;
