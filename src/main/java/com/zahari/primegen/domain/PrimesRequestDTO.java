package com.zahari.primegen.domain;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class PrimesRequestDTO {

    private Integer limit;
    private String implementation;



    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public PrimeGeneratorAlgo getImplementationAsEnum() {
        return PrimeGeneratorAlgo.valueOf(implementation);
    }


}
