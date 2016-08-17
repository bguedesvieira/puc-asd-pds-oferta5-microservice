package com.bernardo.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.model.Objeto;

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
	
	private static Objeto updateObjetoMap(Long id,Objeto objParam){
		Objeto obj = ObjetoController.findObjetoMapById(id);
		if(obj == null){
			throw new RuntimeException("Objeto id "+id+"não encontrado!");
		}
		objetos.put(id, objParam);
		return objParam;
	}
	
	private static Objeto findObjetoMapById(Long id){
		return objetos.get(id);
	}
	
	private static void deleteObjetoMap(Long id){
		Objeto obj = ObjetoController.findObjetoMapById(id);
		if(obj == null){
			throw new RuntimeException("Objeto id "+id+"não encontrado!");
		}
		objetos.remove(id);
	}
	
	private static Collection<Objeto> getListOfValueMap(){
		return objetos.values();
	}
	
	@RequestMapping(path="{id}", method=RequestMethod.GET)
	public Objeto getObjeto(@PathVariable Long id){		
		Objeto obj = new Objeto(id,"Revista",new Long(123456789),"Teste 1","Autor 1","Editora 1","1");
		
		return obj;
	}
	
	private boolean verifyOwnerCo(String codProprietario){
		
		return true;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public URI storeObjeto(@RequestBody Objeto obj){
		ObjetoController.addObjetoMap(obj);
		URI uri=URI.create("/objeto/"+obj.getId());
		return uri;
	}
	
	@RequestMapping(method=RequestMethod.PUT,path="{id}")
	public Objeto updateObjeto(@PathVariable Long id,@RequestBody Objeto objParam){
		
		Objeto obj = ObjetoController.updateObjetoMap(id, objParam);	
		
		return obj;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,path="{id}")
	public void deleteObjeto(@PathVariable Long id){
		ObjetoController.deleteObjetoMap(id);
	}
	
	@RequestMapping(path="all")
	public List<Objeto> listObjetos(@RequestParam(name="codProprietario",required=false) String codProprietario){
		List<Objeto> listAux = new ArrayList<Objeto>(ObjetoController.getListOfValueMap());
		List<Objeto> list=null; 
		
		if (!StringUtils.isEmpty(codProprietario)){
			list = listAux.stream()
					.filter(o -> codProprietario.equals(o.getCodProprietario()))
					.collect(Collectors.toList());
		}else{
			list=listAux;
		}
		
		return list;
	}	
}
