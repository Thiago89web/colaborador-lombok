package com.thiago.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colaborador")
public class Colaborador extends AbstractEntity {
	
	
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Size(max = 60, message = "O nome deve conter no máximo 60 caracteres.")
	private String nome;
	
	@NotBlank(message = "O campo CPF é obrigatório.")
	@CPF(message = "CPF Invalido")
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@ManyToMany
	@JoinTable(
		name = "colaborador_tem_opcao", 
        joinColumns = { @JoinColumn(name = "colaborador_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "opcao_id", referencedColumnName = "id") }
	)
	@NotEmpty(message = "Selecione pelo menos uma opção")
	private List<Opcao> opcoes;	
		

}
