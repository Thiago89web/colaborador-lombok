package com.thiago.spring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thiago.spring.model.Colaborador;
import com.thiago.spring.model.Opcao;
import com.thiago.spring.service.ColaboradorService;
import com.thiago.spring.service.OpcaoService;

@Controller
@RequestMapping("/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

	@Autowired
	private OpcaoService opcaoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Colaborador colaborador) {
		return "/colaborador/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap modelMap) {

		modelMap.addAttribute("colaborador", colaboradorService.listAll());
		return "/colaborador/lista";
	}

	@PostMapping(path = { "/salvar", "/editar" })
	public String salvar(@Valid Colaborador colaborador, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/colaborador/cadastro";
		}
		try {
			for (Opcao obj : colaborador.getOpcoes()) {
				Optional<Colaborador> ids = colaboradorService.buscarPorOpcaoId(obj.getId());
				if (ids.isPresent() && colaborador.getId() != ids.get().getId()) {
					Opcao listOpId = opcaoService.get(obj.getId());
					attr.addFlashAttribute("fail",
							"A opção: " + listOpId.getNome_op() + "  já foi escolhida. Por vafor escolha outra!");
					return "redirect:/colaboradores/cadastrar";
				}
			}
			colaboradorService.save(colaborador);
			attr.addFlashAttribute("success", "Colaborador inserido com sucesso.");
		} catch (DataIntegrityViolationException ex) {
			attr.addFlashAttribute("fail", "C.P.F. já existe cadastrado.");
		}
		return "redirect:/colaboradores/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("colaborador", colaboradorService.buscarPorIdEditar(id));
		return "/colaborador/cadastro";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		colaboradorService.delete(id);
		attr.addFlashAttribute("success", "Colaborador excluido com sucesso.");
		return "redirect:/colaboradores/listar";
	}

	@GetMapping("/buscar/nome")
	public String consultarPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("colaborador", colaboradorService.consultarPorNome(nome));
		return "/colaborador/lista";
	}

	@ModelAttribute("opcoes")
	public List<Opcao> listaDeOpcoes() {
		return opcaoService.listAll();
	}

}
