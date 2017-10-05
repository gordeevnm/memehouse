package ru.kek.memehouse.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@RestController
public class TestController {
	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	public String test1() {
		return "test1";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test2() {
		return "test2";
	}
}
