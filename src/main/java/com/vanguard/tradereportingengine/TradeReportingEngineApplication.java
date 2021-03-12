package com.vanguard.tradereportingengine;

import com.vanguard.tradereportingengine.service.TradeReportingEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TradeReportingEngineApplication {

	@Autowired
	private TradeReportingEngine service;

	@RequestMapping(value = "/execute", method = RequestMethod.POST)
	public void executeProgram() {
		service.processInputFiles();
	}

	public static void main(String[] args) {
		SpringApplication.run(TradeReportingEngineApplication.class, args);
	}

}
