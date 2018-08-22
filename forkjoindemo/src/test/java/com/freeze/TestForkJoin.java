package com.freeze;

import com.freeze.executorservice.ExecutorServiceCalculator;
import com.freeze.forkjoin.ForkJoinCalculator;
import com.freeze.forloop.ForloopCalculator;
import com.freeze.service.Calculator;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author he_jiebing
 * @date 2018-08-22 13:33
 */
public class TestForkJoin {

	long[] numbers = LongStream.rangeClosed(1, 100000000L).toArray();

	@Test
	public void testForloop() {

		Instant startTime = Instant.now();
		Calculator calculator = new ForloopCalculator();
		long result = calculator.sum(numbers);
		System.out.println("result: " + result);
		Instant endTime = Instant.now();
		System.out.println("耗时: "
				+ Duration.between(startTime, endTime).toMillis() + "毫秒");//75ms 左右

	}

	@Test
	public void testExecutorService(){
        Instant startTime = Instant.now();
        Calculator calculator = new ExecutorServiceCalculator();
        long result = calculator.sum(numbers);
        System.out.println("result: " + result);
        Instant endTime = Instant.now();
        System.out.println("耗时: "
                + Duration.between(startTime, endTime).toMillis() + "毫秒");//60 ms
    }
    @Test
    public void testForkJoin(){
        Instant startTime = Instant.now();
        Calculator calculator = new ForkJoinCalculator();
        long result = calculator.sum(numbers);
        System.out.println("result: " + result);
        Instant endTime = Instant.now();
        System.out.println("耗时: "
                + Duration.between(startTime, endTime).toMillis() + "毫秒");//
    }
}
