package com.zahari.primegen.domain;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class PrimesRequestDTO {

    private Integer limit;
    private String algoType;



    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getAlgoType() {
        return algoType;
    }

    public void setAlgoType(String algoType) {
        this.algoType = algoType;
    }

    public PrimeGeneratorAlgo getAlgoTypeAsEnum() {
        return PrimeGeneratorAlgo.valueOf(algoType);
    }


}
