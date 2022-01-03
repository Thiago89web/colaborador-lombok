package com.thiago.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thiago.spring.model.Opcao;

@Repository
public interface OpcaoRepository extends JpaRepository<Opcao, Long> {

	@Query(value = "SELECT * FROM Opcao op WHERE op.nome_op LIKE CONCAT('%',?1,'%')", nativeQuery = true)	
	List<Opcao> findByBuscaPorNome(@Param("nome") String nome);

	@Query("SELECT op.nome_op FROM Opcao op WHERE LOWER(op.nome_op) LIKE LOWER(CONCAT('%',:nome,'%'))")
	Optional<Opcao> findByVerificaNome(String nome);
		
}
