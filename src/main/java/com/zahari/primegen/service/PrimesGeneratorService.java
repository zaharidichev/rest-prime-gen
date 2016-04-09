package com.zahari.primegen.service;

import com.zahari.primegen.domain.PrimesRequestDTO;
import com.zahari.primegen.domain.PrimesResultDTO;
import com.zahari.primegen.service.generators.PrimeGeneratorFunctionsFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */

@Service
public class PrimesGeneratorService {


    public PrimesResultDTO getPrimes(PrimesRequestDTO params) {


        Function<Integer,List<Integer>> generator = PrimeGeneratorFunctionsFactory.getPrimeGeneratorImplementation(params.getImplementationAsEnum());

        List<Integer> primes = generator.apply(params.getLimit());

        return new PrimesResultDTO.Builder()
                .withNumberOfPrimesFound(primes.size())
                .withPrimes(primes)
                .build();

    }


}
