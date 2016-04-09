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


    /**
     *
     * Returns a result DTO with the prime numbers, their count and the algo used to generate them
     *
     * @param params
     * @return
     */
    public PrimesResultDTO getPrimes(PrimesRequestDTO params) {

        Function<Integer,List<Integer>> generator = PrimeGeneratorFunctionsFactory.getPrimeGeneratorImplementation(params.getAlgoTypeAsEnum());
        List<Integer> primes = generator.apply(params.getLimit());

        return new PrimesResultDTO.Builder()
                .withNumberOfPrimesFound(primes.size())
                .withAlgoType(params.getAlgoType())
                .withPrimes(primes)
                .build();

    }


}
