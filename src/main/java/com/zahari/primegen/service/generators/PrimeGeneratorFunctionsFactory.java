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
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class PrimeGeneratorFunctionsFactory {


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




    public static Function<Integer,List<Integer>> getPrimeGeneratorImplementation(PrimeGeneratorAlgo algo) {

        return algo == PrimeGeneratorAlgo.serial ? getErathosthenosSievePrimesGenerator() : getParallelBruteForcePrimeGenerator();

    }


}
