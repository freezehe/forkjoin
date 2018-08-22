package com.freeze.forloop;

import com.freeze.service.Calculator;

/**
 * @author he_jiebing
 * @date 2018-08-22 13:34
 */
public class ForloopCalculator implements Calculator {
	@Override
	public long sum(long[] numbers) {
		long sum = 0;
		for (long i : numbers) {
			sum += i;
		}
		return sum;
	}
}
