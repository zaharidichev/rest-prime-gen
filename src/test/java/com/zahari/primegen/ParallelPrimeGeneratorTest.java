package com.zahari.primegen;

import com.zahari.primegen.domain.PrimeGeneratorAlgo;
import com.zahari.primegen.service.generators.PrimeGeneratorFunctionsFactory;
import org.junit.Before;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class ParallelPrimeGeneratorTest extends AbstractPrimeGeneratorTest {

    @Before
    @Override
    public void setup() {
        super.generator = PrimeGeneratorFunctionsFactory.getPrimeGeneratorImplementation(PrimeGeneratorAlgo.parallel);
    }
}
