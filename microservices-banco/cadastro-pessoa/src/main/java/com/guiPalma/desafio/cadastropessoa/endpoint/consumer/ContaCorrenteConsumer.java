package com.guiPalma.desafio.cadastropessoa.endpoint.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.guiPalma.desafio.cadastropessoa.model.Pessoa;
import com.guiPalma.desafio.core.enums.TipoPessoaEnum;
import com.guiPalma.desafio.core.exceptions.ServiceErrorException;
import com.guiPalma.desafio.core.responses.CadastroPessoaResponse;
import com.guiPalma.desafio.core.responses.ContaCorrenteResponse;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContaCorrenteConsumer {
	
	//private final RestTemplate restTemplate;
	//private final EurekaClient eurekaClient;
	
	@Value("${app.gateway.endpoint}")
	private String gatewayServiceEndpoint;

    @Value("${app.contaCorrentService.serviceId}")
    private String contaCorrentServiceId;

    @Value("${app.contaCorrentService.endpoint.cadastroContaCorrente}")
    private String cadastroContaCorrenteEndpointUri;    

    @HystrixCommand(fallbackMethod = "criarResponseErroApiExterna")
    public ContaCorrenteResponse cadastrarContaCorrente(Pessoa pessoaCadastrada) {    	
     	RestTemplate restTemplate = new RestTemplate();
    	String url = montarUrContaCorrente();
    	return restTemplate.postForObject(url, criarResponse(pessoaCadastrada), ContaCorrenteResponse.class );
    }
    
    private String montarUrContaCorrente() {
    	return gatewayServiceEndpoint + contaCorrentServiceId + cadastroContaCorrenteEndpointUri;
    }

    public ContaCorrenteResponse criarResponseErroApiExterna(Pessoa pessoaCadastrada) {
        //ideia aqui era criar uma fila para conta corrente cadastrar quando o serviço voltasse
    	return ContaCorrenteResponse.builder()
        		.status(HttpStatus.SERVICE_UNAVAILABLE)
        		.msgErro("Falha de comunicação com API externa")
        		.build();
    }    
    
    private CadastroPessoaResponse criarResponse(Pessoa pessoa) {
		 return CadastroPessoaResponse.builder()
				 	.id(pessoa.getId())
				 	.tipoPessoa(pessoa.getTipoPessoa())
				 	.scorePessoa(pessoa.getScore())
				 	.build();
				 	
	 }
	 

}
