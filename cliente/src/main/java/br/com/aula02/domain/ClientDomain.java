package br.com.aula02.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.aula02.bo.Client;
import br.com.aula02.exception.ClientServiceException;

public class ClientDomain {
	
	private static Map<String, Client> clientMap = new ConcurrentHashMap<String, Client>();
	
	public static void addClient(Client client) throws ClientServiceException{
		if(client == null || isBlank(client.getRegistrationId())){
			throw new ClientServiceException("Matrícula deve ser preenchida!");
		}
		if(clientMap.containsKey(client.getRegistrationId())){
			throw new ClientServiceException("Cliente já cadastrado");
		} else {
			clientMap.put(client.getRegistrationId(), client);
		}
		
	}
	
	public static void removeClient(String registrationId) throws ClientServiceException{
		if(isBlank(registrationId)){
			throw new ClientServiceException("Matrícula deve ser preenchida!");
		}
		clientMap.remove(registrationId);
	}
	
	public static List<Client> findClient(String searchValue){
		if(isBlank(searchValue)){
			return getList();
		} else {
			List<Client> clients = new ArrayList<Client>();
			for (Client client : clientMap.values()) {
				if (client.match(searchValue)) {
					clients.add(client);
				}
			}
			return clients;
		}
	}
	
	public static void update(Client client) throws ClientServiceException{
		if(client == null || isBlank(client.getRegistrationId())){
			throw new ClientServiceException("Parâmetros incorretos!");
		}
		
		if(clientMap.containsKey(client.getRegistrationId())){
			clientMap.replace(client.getRegistrationId(), client);
		} else {
			throw new ClientServiceException("Cliente não encontrado!");
		}
	}
	
	private static List<Client> getList(){
		return new ArrayList<Client>(clientMap.values());
	}

	private static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
}
