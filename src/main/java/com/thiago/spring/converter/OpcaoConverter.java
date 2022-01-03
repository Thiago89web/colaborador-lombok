package com.thiago.spring.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.thiago.spring.model.Opcao;




@Component
public class OpcaoConverter implements Converter<String[], List<Opcao>> {

	@Override
	public List<Opcao> convert(String[] source) {
		List<Opcao> opcoes = new ArrayList<>();
		for (String id : source ) {
			if(!id.equals("0")) {
			opcoes.add(new Opcao(Long.parseLong(id)));							
			}
		}
		return opcoes;
	}		

}
