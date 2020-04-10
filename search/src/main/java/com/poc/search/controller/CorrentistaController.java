package com.poc.search.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.search.model.Correntista;
import com.poc.search.service.CorrentistaService;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/correntistas")
public class CorrentistaController {

	@Autowired
	private CorrentistaService userService;

	@GetMapping
	public List<Correntista> getAllCorrentistas() {
		return this.userService.listAll();
	}

	@GetMapping(path = "/search")
	public List<Correntista> searchCorrentistas(@RequestParam String palavras) {
		return this.userService.search(palavras);
	}

}
