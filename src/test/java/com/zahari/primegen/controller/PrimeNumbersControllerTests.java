package com.zahari.primegen.controller;

import com.zahari.primegen.PrimeGenApplication;
import com.zahari.primegen.domain.PrimeGeneratorAlgo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PrimeGenApplication.class)
@WebAppConfiguration
public class PrimeNumbersControllerTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void contextLoads() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}

	@Before
	public void setup() {
	}


	private void testPrimesUntilElevenForAlgo(PrimeGeneratorAlgo algo) throws Exception {

		this.mockMvc
				.perform(get("/api/primenums").param("limit", "11").param("algoType", algo.name()))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.algoType", is(algo.name())))
				.andExpect(jsonPath("$.numberOfPrimesFound", is(5)))
				.andExpect(jsonPath("$.primes", contains(2, 3, 5, 7,11)));

	}

	@Test
	public void returnsThePrimeNumbersUntillElevenForParallelAlgo() throws Exception {
		this.testPrimesUntilElevenForAlgo(PrimeGeneratorAlgo.parallel);
	}

	@Test
	public void returnsThePrimeNumbersUntillElevenForSerialAlgo() throws Exception {
		this.testPrimesUntilElevenForAlgo(PrimeGeneratorAlgo.serial);
	}

	@Test
	public void returnsErrorForMissingParams() throws Exception {
		this.mockMvc
				.perform(get("/api/primenums"))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void returnsErrorForMisspelledAlgo() throws Exception {
		this.mockMvc
				.perform(get("/api/primenums").param("limit","11").param("algoType", "foo"))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.errors", hasSize(1)));

	}

	@Test
	public void returnsErrorForNegativeNumber() throws Exception {
		this.mockMvc
				.perform(get("/api/primenums").param("limit","-11").param("algoType", "serial"))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
		        .andExpect(jsonPath("$.errors", hasSize(1)));

	}

	@Test
	public void returnsErrorWhenNumberOverflowsAndBecomesNegative() throws Exception {

		this.mockMvc
				.perform(get("/api/primenums").param("limit","2147483650").param("algoType", "serial"))
				.andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
				.andExpect(jsonPath("$.message", is("There was a failure formatting your numeric input. Limit field should be within the bounds of a Java Integer")));

	}

}
