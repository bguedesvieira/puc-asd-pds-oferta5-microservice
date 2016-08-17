package br.com.aula02;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aula02.bo.Client;
import br.com.aula02.domain.ClientDomain;
import br.com.aula02.exception.ClientServiceException;
import br.com.aula02.response.BaseResponse;
import br.com.aula02.response.FindResponse;

@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
public class ClientMicroService {
	
	@ExceptionHandler(ClientServiceException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody BaseResponse handleException(ClientServiceException e) {
		return new BaseResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponse handleException(Exception e) {
		return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado!");
	}
	
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResponse> add(@RequestBody Client client) throws ClientServiceException {
		ClientDomain.addClient(client);

		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Adicionado com sucesso!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
    @RequestMapping(path = "{registrationId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<BaseResponse> delete(@PathVariable String registrationId) throws ClientServiceException {
		ClientDomain.removeClient(registrationId);
		
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Removido com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<BaseResponse> update(@RequestBody Client client) throws ClientServiceException {
    	ClientDomain.update(client);

		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Atualizado com sucesso!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FindResponse> find(@RequestParam(name = "search", required = false) String value) throws ClientServiceException {
    	List<Client> clients = ClientDomain.findClient(value);
    	String message = clients != null && !clients.isEmpty() ? clients.size() + " resultado(s) encontrado(s)." : "A pesquisa não retornou resultado.";
    	
    	FindResponse response = new FindResponse();
    	response.setCode(HttpStatus.OK.value());
    	response.setMessage(message);
    	response.setResult(clients);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientMicroService.class, args);
    }
}
