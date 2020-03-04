package com.guiPalma.desafio.cadastropessoa.endpoint.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.guiPalma.desafio.cadastropessoa.dto.PessoaDto;
import com.guiPalma.desafio.core.exceptions.FieldMessage;
import com.guiPalma.desafio.core.util.Validator;

public class PessoaInsertValidator  implements ConstraintValidator<PessoaInsert, PessoaDto>{

	@Override
	public boolean isValid(PessoaDto pessoa, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(! Validator.has(pessoa.getNome())){
			list.add(new FieldMessage("Nome", "Não informado"));			
		}
		
		if(! Validator.has(pessoa.getNumDocumento())){
			list.add(new FieldMessage("Numero do Documento", "Não informado"));			
		}
		
		if(!Validator.has(pessoa.getTipo())){			
			list.add(new FieldMessage("Tipo Pessoa", "Não informado"));		
			
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();		

}
}