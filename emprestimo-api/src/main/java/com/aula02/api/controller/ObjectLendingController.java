package com.aula02.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aula02.api.exception.ObjectLendingException;
import com.aula02.api.response.AddResponse;
import com.aula02.api.response.BaseResponse;
import com.aula02.api.response.FindResponse;
import com.aula02.model.ObjectLending;
import com.aula02.model.ObjectLendingDomain;

@RestController
@RequestMapping("/emprestimo")
public class ObjectLendingController {
	
	@RequestMapping(path="{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FindResponse> getObjeto(@PathVariable Long id) throws ObjectLendingException{
		ObjectLending obj = ObjectLendingDomain.findById(id);
		String message = obj != null ? "Empréstimo recuperado com sucesso" : "A pesquisa não retornou resultado.";
		FindResponse response = new FindResponse();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(message);
		response.setResult(obj);
		
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AddResponse> addObjectLending(@RequestBody ObjectLending input) throws ObjectLendingException{
		ObjectLending obj = ObjectLendingDomain.addObjectLending(input);
		URI uri=URI.create("/emprestimo/"+obj.getId());
		String message = "Incluído com sucesso empréstimo Id " + obj.getId();
		
		AddResponse response = new AddResponse(uri.getPath());
		response.setCode(HttpStatus.OK.value());
		response.setMessage(message);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="{id}")
	@ResponseBody
	public ResponseEntity<BaseResponse> updateObjeto(@PathVariable Long id,@RequestBody ObjectLending objParam) throws ObjectLendingException{
		ObjectLendingDomain.updateObjectLending(objParam);
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Objeto atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="{id}")
	@ResponseBody
	public ResponseEntity<BaseResponse> deleteObjectLending(@PathVariable Long id) throws ObjectLendingException {
		ObjectLendingDomain.deleteObjecctLending(id);
		
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Registro atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FindResponse> listObjetos(@RequestParam(name="client",required=false) String registrationId) throws ObjectLendingException{
		List<ObjectLending> listAux = new ArrayList<ObjectLending>( ObjectLendingDomain.list());
		List<ObjectLending> list=new ArrayList<ObjectLending>(); 
		if (!StringUtils.isEmpty(registrationId)){
			list = listAux.stream()
					.filter(lending -> registrationId.equals(lending.getClientRegistrationId()))
					.collect(Collectors.toList());
		}else{
			list=listAux;
		}
		
		String message = !list.isEmpty() ? list.size() + " resultado(s) encontrado(s)." : "Pesquisa não retornou nenhum resultado!";
		
		FindResponse response = new FindResponse();
		response.setCode(HttpStatus.OK.value());
		response.setResult(list);
		response.setMessage(message);
		
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.PATCH ,path="{id}")
	@ResponseBody
	public ResponseEntity<BaseResponse> updateLendingStatus(@PathVariable Long id, @RequestBody String status) throws ObjectLendingException {
		JSONObject jsonStatus = new JSONObject(status);
		String statusAux = jsonStatus.get("status").toString();
		
		ObjectLendingDomain.updateLendingStatus(id,statusAux);
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Emprestimo confirmado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
}
