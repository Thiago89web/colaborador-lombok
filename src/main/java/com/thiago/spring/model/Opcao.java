package com.thiago.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name = "opcao")
public class Opcao extends AbstractEntity {
	
	
	
	@NotBlank(message = "O campo nome é obrigatório.")
	@Size(max = 60, message = "O nome deve conter no máximo 60 caracteres.")
	@Column(name = "nome_op", nullable = false)
	private String nome_op;		
	
	public Opcao(Long id) {
		super.setId(id);		
	}
	
	
	
}
