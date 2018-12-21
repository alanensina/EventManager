package com.eventoapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired // Fará uma injeção de dependência, ou seja, toda vez que utilizar essa interface, será criado uma nova instância
	private EventoRepository er;
	
	@Autowired 
	private ConvidadoRepository cr;

	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET) // Será um GET pois retornará o formúlario
	public String form() {
		return "evento/formEvento"; // Como o formulário está dentro do diretório evento, deve-se informar seu caminho corretamente.
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST) // Será um POST pois essa requisição salvará os dados no banco de dados
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Dados inconsistentes, verifique os campos!");	
			return "redirect:/cadastrarEvento";
		}	
		
		er.save(evento); // Persiste os dados no banco de dados
		attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso!");	
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index"); // Página que renderizará conforme a lista de eventos
		Iterable<Evento> eventos = er.findAll(); // O event EventoRepository buscará uma lista de eventos
		mv.addObject("eventos", eventos); // O primeiro parâmetro é o mesmo que se encontra entre a ${} no index.html, já o segundo parâmetro é a lista de eventos criada na linha de cima. Sendo assim a lista de eventos já consegue ser renderizada na view.		
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET) // Ao clicar sobre o evento, ele redirencionará automaticamente para os detalhes do evento
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);		
		
		Iterable<Convidado> convidados = cr.findByEvento(evento); // Recebe a lista de convidados de um determinado evento
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	
	/*
		Salvando o convidado na tabela do evento.
		Caso o usuário não tenha preenchido os campos corretamente, será exibida uma mensagem de erro.
		Caso esteja tudo certo, será exibida uma mensagem de sucesso.
	
	*/
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
		attributes.addFlashAttribute("mensagem", "Dados inconsistentes, verifique os campos!");	
		return "redirect:/{codigo}";
		}		
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);	
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso.");
		return "redirect:/{codigo}";
	}	
}
