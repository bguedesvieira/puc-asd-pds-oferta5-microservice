package com.aula02.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.aula02.api.response.AddResponse;
import com.aula02.api.response.BaseResponse;
import com.aula02.api.response.FindResponse;
import com.aula02.exception.ObjetoApiException;
import com.aula02.model.Objeto;

@RestController
@RequestMapping("/objeto")
public class ObjetoController {
	private static Map<Long,Objeto> objetos = new HashMap<Long,Objeto>();
	
	private static long ultimoId=0;
	
	private static Objeto addObjetoMap(Objeto obj){
		if (obj.getId() == null){
			obj.setId( Long.valueOf(++ultimoId));
		}
		
		objetos.put(obj.getId(), obj);
		return obj;
	}
	
	private static Objeto updateObjetoMap(Long id,Objeto objParam) throws ObjetoApiException{
		
		if(!objetos.containsKey(id)){
			throw new ObjetoApiException(new StringBuilder("Objeto não encontrado! Id ").append(id).toString());
		}
		objetos.put(id, objParam);
		return objParam;
	}
	
	private static Objeto findObjetoMapById(Long id) throws ObjetoApiException{
		Objeto obj = objetos.get(id);
		if (obj == null) {
			throw new ObjetoApiException(new StringBuilder("Objeto não encontrado! Id ").append(id).toString());
		}
		return obj;
	}
	
	private static void deleteObjetoMap(Long id) throws ObjetoApiException{
		if(!objetos.containsKey(id)){
			throw new ObjetoApiException(new StringBuilder("Objeto não encontrado! Id ").append(id).toString());
		}
		objetos.remove(id);
	}
	
	private static Collection<Objeto> getListOfValueMap(){
		return objetos.values();
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FindResponse> getObjeto(@PathVariable Long id) throws ObjetoApiException{		
		Objeto obj = ObjetoController.findObjetoMapById(id);
		String message = obj != null ? "Objeto recuperado com sucesso" : "A pesquisa não retornou resultado.";
		FindResponse response = new FindResponse();
		response.setCode(HttpStatus.OK.value());
		response.setMessage(message);
		response.setResult(obj);
		
		return ResponseEntity.ok().body(response);
	}
	
	@SuppressWarnings("unused")
	private boolean verifyOwnerCo(String codProprietario) throws ObjetoApiException {
		return true;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AddResponse> storeObjeto(@RequestBody Objeto obj) throws ObjetoApiException{
		ObjetoController.addObjetoMap(obj);
		URI uri=URI.create("/objeto/"+obj.getId());
		String message = "Incluído com sucesso objeto Id " + obj.getId();
		
		AddResponse response = new AddResponse(uri.getPath());
		response.setCode(HttpStatus.OK.value());
		response.setMessage(message);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="{id}")
	@ResponseBody
	public ResponseEntity<BaseResponse> updateObjeto(@PathVariable Long id,@RequestBody Objeto objParam) throws ObjetoApiException{
		Objeto obj = ObjetoController.updateObjetoMap(id, objParam);
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Objeto atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="{id}")
	@ResponseBody
	public ResponseEntity<BaseResponse> deleteObjeto(@PathVariable Long id) throws ObjetoApiException{
		ObjetoController.deleteObjetoMap(id);
		
		BaseResponse response = new BaseResponse(HttpStatus.OK.value(), "Registro atualizado com sucesso!");
		return ResponseEntity.ok().body(response);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FindResponse> listObjetos(@RequestParam(name="cliente",required=false) String registrationId) throws ObjetoApiException{
		List<Objeto> listAux = new ArrayList<Objeto>(ObjetoController.getListOfValueMap());
		List<Objeto> list=new ArrayList<Objeto>(); 
		if (!StringUtils.isEmpty(registrationId)){
			list = listAux.stream()
					.filter(o -> registrationId.equals(o.getCodProprietario()))
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
}
