/*
 * Scaler.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class Scaler extends Object;

  private Attributes attributes;
  private ArrayList<Double> mins;
  private ArrayList<Double> maxs;

  public Scaler();
  public void configure( DataSet ds ) throws Exception;
  public DataSet scale( DataSet ds ) throws Exception;
  public Example scale( Example example ) throws Exception;
