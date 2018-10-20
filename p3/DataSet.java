/*
 * DataSet.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

public class DataSet;
  ...
  public boolean isEmpty();
  public double gainRatio( int attribute ) throws Exception;
  public int getBestSplittingAttribute() throws Exception;
  public ArrayList<DataSet> splitOnAttribute( int attribute ) throws Exception;
  public boolean homogeneous() throws Exception;
  public int[] getClassCounts() throws Exception;
  public int getMajorityClassLabel() throws Exception;
