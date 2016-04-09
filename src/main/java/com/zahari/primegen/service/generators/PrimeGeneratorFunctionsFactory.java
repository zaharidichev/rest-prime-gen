package com.zahari.primegen.service.generators;

import com.codepoetics.protonpack.StreamUtils;
import com.zahari.primegen.domain.PrimeGeneratorAlgo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 *
 * A convinience class to easily obtain prime number generation functions based on
 * a type of algorithm
 *
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class PrimeGeneratorFunctionsFactory {


    /**
     *
     * Returns a function which is a simple implementation of the Eratosthenos Sieve.
     * It uses an array of booleans to represent the sieve. As this is a statefull
     * implementation with side effects on the sieve, it cannot be run in parallel.
     *
     * @return
     */
    public static Function<Integer,List<Integer>> getErathosthenosSievePrimesGenerator() {

        return (limit) -> {

            final boolean[] booleanSieve = new boolean[limit+1];

            return IntStream.rangeClosed(2, limit)
                    .filter(x -> !booleanSieve[x])
                    .peek(prime -> {
                        int primeToTheSecond = prime * prime;
                        if (primeToTheSecond < limit) {
                            for (int i = primeToTheSecond; i <= limit; i += prime) {
                                booleanSieve[i] = true;
                            }
                        }
                    })
                    .boxed()
                    .collect(toList());

        };

    }


    /**
     * Returns a function which implements a mildly optimised, brure force algorithm
     * for finding primes. It uses a stateless predicate to test whether a number
     * is a prime. Therefore it parallelizes cleanly on the ForkJoin pool by simply invoking
     * .parallel
     *
     * @return
     */
    public static Function<Integer,List<Integer>> getParallelBruteForcePrimeGenerator() {

        return (limit) -> IntStream.rangeClosed(2, limit)
                .boxed()
                .parallel()
                .filter(isPrime)
                .collect(toList());

    }


    private static Predicate<Integer> isPrime = (n) -> {
        if (n==2) return true; // rule out 2
        if (n%2==0) return false; // rule out any multiples of two
        // now we only need to check for odd numbers so we can create an infiinte stream of odds and bound it to the square root of N
        Stream<Integer> infinateBoundedStream = StreamUtils.takeWhile(Stream.iterate(3, x -> x + 2), x -> x * x <= n) ;

        // if non of the numbers in the stream is a divisor of N, then we know its a prime
        return infinateBoundedStream.noneMatch(x -> n % x == 0);

    };


    /**
     * Based on the type of algorithm used, a function is returned which takes as input
     * the maximum number until which to look for primes and returns a list of primes.
     *
     * @param algo
     * @return
     */
    public static Function<Integer,List<Integer>> getPrimeGeneratorImplementation(PrimeGeneratorAlgo algo) {

        return algo == PrimeGeneratorAlgo.serial ? getErathosthenosSievePrimesGenerator() : getParallelBruteForcePrimeGenerator();

    }


}
