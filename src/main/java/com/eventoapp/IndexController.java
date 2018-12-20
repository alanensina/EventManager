package com.eventoapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// Controlador do index
@Controller
public class IndexController {

	// Método responsável que recebe uma requisição e retorna a view index.html
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
