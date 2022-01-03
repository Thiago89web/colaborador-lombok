package com.thiago.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiago.spring.model.Colaborador;
import com.thiago.spring.repository.ColaboradorRepository;


@Service
@Transactional(readOnly = false)
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRespository;
	
	@Transactional(readOnly = true)
	public List<Colaborador> listAll() {
		return (List<Colaborador>) colaboradorRespository.findAll();
	}
	
	public void save(Colaborador colaborador){			
		colaboradorRespository.save(colaborador);				
	}	
	
	public void delete(long id) {
		colaboradorRespository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Colaborador buscarPorIdEditar(Long id) {		
		return colaboradorRespository.findById(id).get();
	}
	
	@Transactional(readOnly = true)
	public List<Colaborador> consultarPorNome(String nome) {		
		return colaboradorRespository.findByConsultarPorNome(nome);
	}		
	
	@Transactional(readOnly = true)
	public Optional<Colaborador> buscarPorOpcaoId(Long list) {
		return colaboradorRespository.findByIdAndOpcao(list);
	}
	

}
