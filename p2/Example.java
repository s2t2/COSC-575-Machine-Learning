/*
 * Example.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

import java.util.Collections;
import java.util.ArrayList;

/**
 * Stores the attribute values of an example.
 * Numeric values are stored as is.
 * Nominal values are stored as Doubles and are indices of the value in the attributes structure.
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-09-18
 */
public class Example extends ArrayList<Double> {
	
	/**
	 * Default constructor
	 */
	public Example() {

	}

	/**
	 * Explicit constructor.
	 * Constructs an Example with n values, where n is greater or equal to two.
	 * 
	 * @param n - the number of values of this example
	 */
	public Example( int n ) {
		super(n);
	}
}
