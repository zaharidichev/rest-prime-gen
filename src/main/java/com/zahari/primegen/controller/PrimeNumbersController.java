package com.zahari.primegen.controller;

import com.zahari.primegen.domain.PrimesRequestDTO;
import com.zahari.primegen.domain.PrimesResultDTO;
import com.zahari.primegen.service.PrimesGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */


@RestController
@RequestMapping("/primenums")
public class PrimeNumbersController {


    @Autowired
    private PrimesGeneratorService primesService;

    @RequestMapping(method = RequestMethod.GET)
    public PrimesResultDTO getPrimes(PrimesRequestDTO params) {
        return primesService.getPrimes(params);
    }

}