package com.guiPalma.desafio.cadastropessoa.endpoint.consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.guiPalma.desafio.cadastropessoa.model.Pessoa;
import com.guiPalma.desafio.core.enums.TipoPessoaEnum;
import com.guiPalma.desafio.core.responses.ContaCorrenteResponse;
import com.netflix.config.ConfigurationManager;



@SpringBootTest
@RunWith(SpringRunner.class)
public class ContaCorrenteConsumerTest {
	
	@Autowired
	private ContaCorrenteConsumer contaCorrenteConsumer;

	@MockBean
	private RestTemplate restTemplate;
	
	private Long idPessoa;
	private String nome;

	
	@Before
	public void setUp() {	
		this.nome = "NOME TESTE";
		this.idPessoa = 1236L;
		
	}
	@Test
	public void deve_executar_circuit_breaker_com_erro_api_externa() {
		
		var pessoaMock = Pessoa.builder()
				.id(idPessoa)
				.nome(nome)
				.tipo(TipoPessoaEnum.PESSOAJURIDICA.getCod())
				.build();
		
		abrirCircuitBreaker();
		
		ContaCorrenteResponse result = contaCorrenteConsumer.cadastrarContaCorrente(pessoaMock);
		var mensagemEsperada = "Falha de comunicação com API externa";
		
		assertNotNull(result);
		assertEquals(HttpStatus.SERVICE_UNAVAILABLE, result.getStatus());
		assertEquals(mensagemEsperada, result.getMsgErro());
		fecharCircuitBreaker();
		
	}

	private void abrirCircuitBreaker() {
		ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.forceOpen",
				"true");
		System.setProperty("hystrix.command.default.circuitBreaker.forceOpen", "true");
	}

	private void fecharCircuitBreaker() {
		ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.circuitBreaker.forceOpen",
				"false");
		System.setProperty("hystrix.command.default.circuitBreaker.forceOpen", "false");
	}	
	
	
	
	
}


