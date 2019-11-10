package br.com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.Dependente;
import br.com.model.Funcionario;
import br.com.service.DependenteService;
import br.com.service.FuncionarioService;

@Controller
@RequestMapping("/dependentes")
public class DependenteController {

	@Autowired private DependenteService service;
	@Autowired private FuncionarioService funcionarioService;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("pages/dependente/dependentes");
		mv.addObject("dependentes", service.list());
		List<Funcionario> funcionarios = funcionarioService.list();
		mv.addObject("funcionarios", funcionarios);
		mv.addObject("existeFuncionario", funcionarios != null && !funcionarios.isEmpty() ? true : false);
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Dependente dependente) {
		ModelAndView mv = new ModelAndView("pages/dependente/novo_dependente");
		mv.addObject("dependente", dependente);
		List<Funcionario> funcionarios = funcionarioService.list();
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView salvar(@Valid Dependente dependente, BindingResult result, Model model, RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/dependentes");
		
		if (result.hasErrors()) {
			return novo(dependente);
		}

		attributes.addFlashAttribute("mensagem", "Dependente salvo com sucesso");
		this.service.save(dependente);
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Dependente dependente = this.service.getById(id); 
		return novo(dependente);
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/dependentes");
		this.service.remove(id);
		attributes.addFlashAttribute("removido", "Dependente removido com sucesso!");
		return mv;
	}
}
