package com.aula02.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

import com.aula02.api.exception.ObjectLendingException;

public class ObjectLendingDomain {
	private static Map<Long,ObjectLending> lendingMap = new ConcurrentHashMap<Long,ObjectLending>();
	private static long lastId=0;
	
	public static void validate(ObjectLending objectLending,Operation operation) throws ObjectLendingException{
		StringBuilder message = new StringBuilder();
		if (objectLending == null){
			message.append("Empréstimo não pode ser nulo! ");
		}
		
		if (!Operation.CREATE.equals(operation)){
			if (!lendingMap.containsKey(objectLending.getId())){
				message.append("Empréstimo não encontrado! ");
			}
		}
		
		if (Operation.CREATE.equals(operation) || Operation.UPDATE.equals(operation)){
			validateValues(objectLending,message);
		}
		
		if (message.length() != 0){
			throw new ObjectLendingException(message.toString());
		}
	}
	
	private static void validateValues(ObjectLending objectLending, StringBuilder message) {
		if (objectLending.getId() == null){
			message.append("Id é obrigatório! ");
		}
		
		if (StringUtils.isEmpty(objectLending.getClientRegistrationId())){
			message.append("Cliente deve ser informado! ");
		}
		
		if (objectLending.getObjectId() == null){
			message.append("Objeto deve ser informado! ");
		}
	}

	public static ObjectLending addObjectLending(ObjectLending lending) throws ObjectLendingException{
		
		ObjectLending objectLending = lending;
		if (objectLending != null && objectLending.getId() == null){
			objectLending.setId(++lastId);
		}
		objectLending.setStatus("P");
		
		validate(objectLending,Operation.CREATE);
		
		lendingMap.put( Long.valueOf(lastId) , objectLending);
		
		return objectLending;
	}
	
	public static ObjectLending updateObjectLending(ObjectLending lending) throws ObjectLendingException{
		
		ObjectLending objectLending = lending;
		
		validate(objectLending,Operation.UPDATE);
		
		lendingMap.put(objectLending.getId(),objectLending);
		
		return objectLending;
	}
	
	public static void deleteObjecctLending(Long lendingId) throws ObjectLendingException{
		
		if (lendingMap.containsKey(lendingId)){
			throw new ObjectLendingException("Empréstimo não encontrado! ");
		}
		
		lendingMap.remove(lendingId);
	}
	
	public static ObjectLending findById(Long lendingId) {
		ObjectLending lending = lendingMap.get(lendingId);
		
		return lending;
	}
	
	public static Collection<ObjectLending> list() {
		return lendingMap.values();
	}
	
	public static void updateLendingStatus(Long lendingId,String status) throws ObjectLendingException{
		ObjectLending lending = lendingMap.get(lendingId);
		if (lending == null){
			throw new ObjectLendingException("Empréstimo não encontrado. Id "+lendingId);
		}
		lending.setStatus(status);
		updateObjectLending(lending);
	}
}


