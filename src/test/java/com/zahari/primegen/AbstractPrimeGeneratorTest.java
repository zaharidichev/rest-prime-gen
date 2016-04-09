package com.zahari.primegen;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public abstract class AbstractPrimeGeneratorTest {


    protected Function<Integer,List<Integer>> generator;


    @Before
    public abstract void setup();


    @Test
    public void negativeNumberShouldReturnEmptyInput() {
        assertTrue(this.generator.apply(-10).isEmpty());
    }

    @Test
    public void testThatOneIsNotAPrime() {
        assertTrue(this.generator.apply(1).isEmpty());
    }

    @Test
    public void testThatTwoIsAPrime() {
        List<Integer> result = this.generator.apply(2);
        assertTrue(result.contains(2));
        assertTrue(result.size() == 1);

    }

    @Test
    public void testPrimesFromOneToTen() {
        List<Integer> expected = Arrays.asList(2,3,5,7);
        List<Integer> result = this.generator.apply(10);
        assertTrue(result.size() == 4);

        for(Integer expectedInt : expected) {
            assertTrue(result.contains(expectedInt));
        }

    }



}
