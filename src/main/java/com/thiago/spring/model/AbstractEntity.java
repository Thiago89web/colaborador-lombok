package com.thiago.spring.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data//é a junção das anotações @Getter, @Setter, @EqualsAndHashCode e @ToString.
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
