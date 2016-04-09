package com.zahari.primegen.domain;

import java.util.List;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class PrimesResultDTO {

    Integer numberOfPrimesFound;
    List<Integer> primes;


    public Integer getNumberOfPrimesFound() {
        return numberOfPrimesFound;
    }

    public List<Integer> getPrimes() {
        return primes;
    }


    public static class Builder {

        private PrimesResultDTO result;

        public Builder() {
            this.result = new PrimesResultDTO();
        }

        public Builder withNumberOfPrimesFound(Integer numberFound) {
            this.result.numberOfPrimesFound = numberFound;
            return this;
        }


        public Builder withPrimes(List<Integer> primes) {
            this.result.primes = primes;
            return this;
        }

        public PrimesResultDTO build() {
            return this.result;
        }


    }

}
