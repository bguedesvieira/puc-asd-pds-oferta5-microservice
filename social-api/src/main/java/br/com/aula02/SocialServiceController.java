package br.com.aula02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aula02.domain.SocialDomain;
import br.com.aula02.exception.SocialServiceException;
import br.com.aula02.request.BaseRequest;
import br.com.aula02.response.BaseResponse;

@RestController
@EnableAutoConfiguration
@RequestMapping("/social")
public class SocialServiceController {
	
	@ExceptionHandler(SocialServiceException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody BaseResponse handleException(SocialServiceException e) {
		return new BaseResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody BaseResponse handleException(Exception e) {
		return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado!");
	}
	
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BaseResponse> addFriend(@RequestBody BaseRequest request) throws SocialServiceException {
    	SocialDomain.addFriend(request);
    	
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Adicionado com sucesso!");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
    
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<BaseResponse> deleteFriend(@RequestBody BaseRequest request) throws SocialServiceException {
		SocialDomain.removeFriend(request);
    	
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Removido com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SocialServiceController.class, args);
    }
}
