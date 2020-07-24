package org.ict.algorithm.util;

import java.util.function.Supplier;

/**
 * Creating a simple retry command with function passing in Java 8
 * 
 * @see https://myadventuresincoding.wordpress.com/2014/07/30/java-creating-a-simple-retry-command-with-function-passing-in-java-8/
 * @see https://github.com/myadventuresincoding/RetryCommandExample
 * @see https://github.com/jhalterman/failsafe/tree/master/src/main/java/net/jodah/failsafe
 * @see http://servicedesignpatterns.com/WebServiceInfrastructures/IdempotentRetry
 * @see https://www.yegor256.com/2014/08/15/retry-java-method-on-exception.html
 * @see https://github.com/jcabi/jcabi-aspects
 * @see https://crunchify.com/how-to-retry-operation-n-number-of-times-in-java/
 */
public class RetryCommand<T> {
	
	private int retryCounter;
	
	private int maxRetries;
	
	public RetryCommand(int maxRetries) {
		this.maxRetries = maxRetries;
	}
	
	public T run(Supplier<T> function) {
		try {
			return function.get();
		} catch(Exception e) {
			return retry(function);
		}
	}
	
	private T retry(Supplier<T> function) throws RuntimeException {
		System.out.println("FAILED - Command failed, will be retried " + maxRetries + " times.");
        retryCounter = 0;
        while (retryCounter < maxRetries) {
            try {
                return function.get();
            } catch (Exception ex) {
                retryCounter++;
                System.out.println("FAILED - Command failed on retry " + retryCounter + " of " + maxRetries + " error: " + ex );
                if (retryCounter >= maxRetries) {
                    System.out.println("Max retries exceeded.");
                    break;
                }
            }
        }
        throw new RuntimeException("Command failed on all of " + maxRetries + " retries");
	}
	
	public int getMaxRetries() {
		return this.maxRetries;
	}
}
