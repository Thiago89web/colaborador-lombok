package com.thiago.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thiago.spring.model.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>{
	
	@Query(value = "SELECT * FROM Colaborador c WHERE c.nome LIKE CONCAT('%',?1,'%')", nativeQuery = true)	
	List<Colaborador> findByConsultarPorNome(@Param("nome") String nome);
	
	@Query("select c from Colaborador c join c.opcoes op where op.id like :list")
	Optional<Colaborador> findByIdAndOpcao(@Param("list") Long list);
	
	 		 	 
}
