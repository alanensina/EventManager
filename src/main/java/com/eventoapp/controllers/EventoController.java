package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired // Fará uma injeção de dependência, ou seja, toda vez que utilizar essa interface, será criado uma nova instância
	private EventoRepository er;

	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET) // Será um GET pois retornará o formúlario
	public String form() {
		return "evento/formEvento"; // Como o formulário está dentro do diretório evento, deve-se informar seu caminho corretamente.
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST) // Será um POST pois essa requisição salvará os dados no banco de dados
	public String form(Evento evento) {
		
		er.save(evento); // Persiste os dados no banco de dados
		
		return "redirect:/cadastrarEvento";
	}
	
}
