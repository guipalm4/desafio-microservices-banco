package com.guiPalma.desafio.cadastropessoa.endpoint.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.guiPalma.desafio.cadastropessoa.dto.PessoaDto;
import com.guiPalma.desafio.cadastropessoa.endpoint.consumer.ContaCorrenteConsumer;
import com.guiPalma.desafio.cadastropessoa.model.Pessoa;
import com.guiPalma.desafio.cadastropessoa.repository.CadastroPessoaRepository;
import com.guiPalma.desafio.core.enums.TipoPessoaEnum;
import com.guiPalma.desafio.core.exceptions.ServiceErrorException;
import com.guiPalma.desafio.core.responses.CadastroPessoaResponse;
import com.guiPalma.desafio.core.responses.ContaCorrenteResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroPessoaServiceTest {
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;

	@MockBean
	private CadastroPessoaRepository cadastroPessoaRepository;
	
	@MockBean
	private ContaCorrenteConsumer contaCorrenteConsumer;
	
	private Long idPessoa;
	private String nome;

	
	private static final String NUMERO_DOCUMENTO_CPF = "12345678901";
	private static final String NUMERO_DOCUMENTO_CNPJ = "12345678901234";
	private static final String DOCUMENTO_IVALIDO = "123456789012348a5s5";
	
	
	@Before
	public void setUp() {	
		this.nome = "NOME TESTE";
		this.idPessoa = 1236L;
		
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void deve_cadastar_pessoa_com_cpf_caminho_feliz() {
		
		var mock = Pessoa.builder()
					.id(idPessoa)
					.nome(nome)
					.tipo(TipoPessoaEnum.PESSOAFISICA.getCod())
					.build();
		
		var responseContaCorrente = new ContaCorrenteResponse();
						
		
		BDDMockito.given(cadastroPessoaRepository.findBynumDocumento(Mockito.anyString())).willReturn(null);
		BDDMockito.given(cadastroPessoaRepository.save(Mockito.any(Pessoa.class))).willReturn(mock);
		BDDMockito.given(contaCorrenteConsumer.cadastrarContaCorrente(mock)).willReturn(responseContaCorrente);
		
		var dto = PessoaDto.builder().
				tipo(TipoPessoaEnum.PESSOAFISICA.getCod())
				.numDocumento(NUMERO_DOCUMENTO_CPF).build();
		

		CadastroPessoaResponse result = cadastroPessoaService.cadastrarPessoa(dto);
		
		verify(cadastroPessoaRepository,  times(1)). findBynumDocumento(Mockito.anyString());
		verify(cadastroPessoaRepository,  times(1)). save(Mockito.any(Pessoa.class));
		verify(contaCorrenteConsumer,  times(1)). cadastrarContaCorrente(Mockito.any(Pessoa.class));
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getContaCorrenteResponse());
		assertEquals(TipoPessoaEnum.PESSOAFISICA, result.getTipoPessoa());
		assertNull(result.getMsgErro());
		
	}
	@Test
	public void deve_cadastar_pessoa_com_cnpj_caminho_feliz() {
		
		var pessoaMock = Pessoa.builder()
					.id(idPessoa)
					.nome(nome)
					.tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
					.build();
		
		var responseContaCorrente = new ContaCorrenteResponse();
						
		
		BDDMockito.given(cadastroPessoaRepository.findBynumDocumento(Mockito.anyString())).willReturn(null);
		BDDMockito.given(cadastroPessoaRepository.save(Mockito.any(Pessoa.class))).willReturn(pessoaMock);
		BDDMockito.given(contaCorrenteConsumer.cadastrarContaCorrente(pessoaMock)).willReturn(responseContaCorrente);
		
		var dto = PessoaDto.builder().
				tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
				.numDocumento(NUMERO_DOCUMENTO_CNPJ).build();

		CadastroPessoaResponse result = cadastroPessoaService.cadastrarPessoa(dto);
		
		verify(cadastroPessoaRepository,  times(1)). findBynumDocumento(Mockito.anyString());
		verify(cadastroPessoaRepository,  times(1)). save(Mockito.any(Pessoa.class));
		verify(contaCorrenteConsumer,  times(1)). cadastrarContaCorrente(Mockito.any(Pessoa.class));
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getScorePessoa());
		assertNotNull(result.getContaCorrenteResponse());
		assertEquals(TipoPessoaEnum.PESSOAJURIDICA, result.getTipoPessoa());
		assertNull(result.getMsgErro());		
	}
	
	@Test
	public void deve_lancar_excecao_documento_ja_cadastrado() {
		
		var pessoaMock = Pessoa.builder()
				.id(idPessoa)
				.nome(nome)
				.tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())	
				.build();
		
		var dto = PessoaDto.builder().
				tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
				.numDocumento(NUMERO_DOCUMENTO_CNPJ).build();
		
		BDDMockito.given(cadastroPessoaRepository.findBynumDocumento(Mockito.anyString())).willReturn(pessoaMock);
		BDDMockito.given(cadastroPessoaRepository.save(Mockito.any(Pessoa.class))).willReturn(pessoaMock);
	
		
		exceptionRule.expect(ServiceErrorException.class);
	    exceptionRule.expectMessage("Numero documento ja cadastrado");	
		
		cadastroPessoaService.cadastrarPessoa(dto);
		
		verify(cadastroPessoaRepository,  never()). save(Mockito.any(Pessoa.class));
		verify(contaCorrenteConsumer,   never()). cadastrarContaCorrente(Mockito.any(Pessoa.class));
		
		
	}
	
	@Test
	public void deve_lancar_excecao_documento_invalidp() {
		
		var dto = PessoaDto.builder().
				tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
				.numDocumento(DOCUMENTO_IVALIDO).build();
		
		BDDMockito.given(cadastroPessoaRepository.findBynumDocumento(Mockito.anyString())).willReturn(null);
		
		exceptionRule.expect(ServiceErrorException.class);
	    exceptionRule.expectMessage("Numero do documento invalido");
	    
	    cadastroPessoaService.cadastrarPessoa(dto);
	    
	    verify(cadastroPessoaRepository,  never()). save(Mockito.any(Pessoa.class));
		verify(contaCorrenteConsumer,   never()). cadastrarContaCorrente(Mockito.any(Pessoa.class));
	}
	
	
	
	@Test 
	public void deve_cadastrar_pessoa_com_erro_api_conta_corrente() {
		
		var dto = PessoaDto.builder().
				tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
				.numDocumento(NUMERO_DOCUMENTO_CNPJ).build();
		
		var pessoaMock = Pessoa.builder()
				.id(idPessoa)
				.nome(nome)
				.tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
				.build();
		
		var responseMock = ContaCorrenteResponse.builder()
				.status(HttpStatus.SERVICE_UNAVAILABLE)
				.msgErro("Falha de comunicação com API externa")
				.build();
		
		
		BDDMockito.given(cadastroPessoaRepository.save(Mockito.any(Pessoa.class))).willReturn(pessoaMock);		
		BDDMockito.given(contaCorrenteConsumer.cadastrarContaCorrente(pessoaMock)).willReturn(responseMock);
		
		CadastroPessoaResponse result =  cadastroPessoaService.cadastrarPessoa(dto);		
		
		verify(cadastroPessoaRepository,  times(1)). save(Mockito.any(Pessoa.class));
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getScorePessoa());
		assertNotNull(result.getContaCorrenteResponse());
		assertNotNull(result.getContaCorrenteResponse().getMsgErro());
		
	}
	
	

}
