package com.thiago.spring.controller;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiago.spring.model.Opcao;
import com.thiago.spring.service.OpcaoService;



@Controller
@RequestMapping("/opcoes")
public class OpcaoController {
	
	@Autowired
	OpcaoService opcaoService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Opcao opcao) {

		return "/opcao/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("opcoes", opcaoService.listAll());
		return "/opcao/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Opcao opcao, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "/opcao/cadastro";
		}
		
		Optional<Opcao> nome = opcaoService.verificaNome(opcao.getNome_op());
		if(nome.isPresent()) {
			attr.addFlashAttribute("fail", "Opção já existe.");
		}else {
			opcaoService.save(opcao);
			attr.addFlashAttribute("success", "Opção salva com sucesso.");				
		}
		
		return "redirect:/opcoes/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("opcao", opcaoService.buscarPorIdEditar(id));
		return "/opcao/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Opcao opcao, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "/opcao/cadastro";
		}
		
		opcaoService.save(opcao);
		attr.addFlashAttribute("success", "Opcao editado com sucesso.");
		return "redirect:/opcoes/cadastrar";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
		try {				
						
			opcaoService.delete(id);
			attr.addFlashAttribute("success", "Opção excluida com sucesso.");
			
		} catch (DataIntegrityViolationException ex) {
			attr.addFlashAttribute("fail", "Opção não pode ser excluida. Pois já está em uso.");
		}	
									
		return "redirect:/opcoes/listar";
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {		
		model.addAttribute("opcoes", opcaoService.buscarPorNome(nome));
		return "/opcao/lista";
	}

}
