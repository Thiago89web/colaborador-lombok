package com.thiago.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiago.spring.model.Opcao;
import com.thiago.spring.repository.OpcaoRepository;


@Service
@Transactional(readOnly = false)
public class OpcaoService {
	
	@Autowired
	private OpcaoRepository opcaoRepository;

	
	@Transactional(readOnly = true)
	public List<Opcao> listAll() {
		return (List<Opcao>) opcaoRepository.findAll();
	}

	public void save(Opcao opcao) {		
		opcaoRepository.save(opcao);
	}
	
	@Transactional(readOnly = true)
	public Opcao get(long id) {
		return opcaoRepository.findById(id).get();
	}

	public void delete(long id) {
		opcaoRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Opcao buscarPorIdEditar(Long id) {
		return opcaoRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<Opcao> buscarPorNome(String pesquisa) {
		return opcaoRepository.findByBuscaPorNome(pesquisa);
	}

	@Transactional(readOnly = true)
	public Optional<Opcao> verificaNome(String nome) {
		return opcaoRepository.findByVerificaNome(nome);
	}

}
