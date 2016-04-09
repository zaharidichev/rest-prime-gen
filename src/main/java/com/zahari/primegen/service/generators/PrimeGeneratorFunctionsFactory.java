package com.zahari.primegen.service.generators;

import com.zahari.primegen.domain.PrimeGeneratorAlgo;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

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


    public static Function<Integer,List<Integer>> getPrimeGeneratorImplementation(PrimeGeneratorAlgo algo) {

        return algo == PrimeGeneratorAlgo.serial ? getErathosthenosSievePrimesGenerator() : null;

    }


}
