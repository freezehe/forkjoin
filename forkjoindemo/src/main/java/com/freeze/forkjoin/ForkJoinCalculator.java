package com.freeze.forkjoin;

import com.freeze.service.Calculator;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author he_jiebing
 * @date 2018-08-22 13:49
 */
public class ForkJoinCalculator implements Calculator {
	private static final int THRESHOLD = 10000;// 阈值
	private ForkJoinPool pool;

	private static class SumTask extends RecursiveTask<Long> {
		private long[] numbers;
		private int from;
		private int to;
		boolean canCompute = (from - to) <= THRESHOLD;

		public SumTask(long[] numbers, int from, int to) {
			this.numbers = numbers;
			this.from = from;
			this.to = to;
		}

		@Override
		protected Long compute() {
			// 当需要计算的数字小于阈值时，直接计算结果
			if (canCompute) {
				long sum = 0;
				for (int i = from; i <= to; i++) {
					sum += numbers[i];
				}
				return sum;
				// 否则，把任务一分为二，递归计算
			} else {
				int middle = (from + to) / 2;
				SumTask taskLeft = new SumTask(numbers, from, middle);
				SumTask taskRight = new SumTask(numbers, middle + 1, to);
				taskLeft.fork();
				taskRight.fork();
				return taskLeft.join() + taskRight.join();
			}
		}
	}

	public ForkJoinCalculator() {
		// 也可以使用公用的 ForkJoinPool：
		// pool = ForkJoinPool.commonPool()
		pool = new ForkJoinPool();
	}

	@Override
	public long sum(long[] numbers) {
		return pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
	}
}
