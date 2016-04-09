package com.zahari.primegen;

import com.zahari.primegen.domain.PrimeGeneratorAlgo;
import com.zahari.primegen.service.generators.PrimeGeneratorFunctionsFactory;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class SerialPrimeGeneratorTest extends AbstractPrimeGeneratorTest {


    @Override
    public void setup() {
        super.generator = PrimeGeneratorFunctionsFactory.getPrimeGeneratorImplementation(PrimeGeneratorAlgo.serial);
    }
}
