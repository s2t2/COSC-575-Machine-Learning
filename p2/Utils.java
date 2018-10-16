/*
 * Utils.java
 * Copyright (c) 2018 Georgetown University.  All Rights Reserved.
 */

/**
 * Utilities Class containing some miscellaneous methods
 *
 * @author 	Kornraphop Kawintiranon (Ken) <kk1155@georgetown.edu>
 * @since 	2018-10-15
 */
public class Utils {

	/**
	 * Search for max value in an array and find its index
	 * 
	 * @param  p an array
	 * @return   index of max value of p array
	 */
	public static int maxIndex( double[] p ) {
		int maxIdx = 0;
		double maxValue = -Double.MAX_VALUE;
		for(int i = 0; i < p.length; i++) {
			if ( p[i] > maxValue ) {
				maxValue = p[i];
				maxIdx = i;
			}
		}
		return maxIdx;
	}
}
