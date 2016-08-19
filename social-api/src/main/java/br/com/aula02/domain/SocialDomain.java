package br.com.aula02.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.aula02.exception.SocialServiceException;
import br.com.aula02.request.BaseRequest;

public class SocialDomain {
	
	private static Map<String, List<String>> friendsMap = new ConcurrentHashMap<String, List<String>>();
	
	public static void addFriend(BaseRequest request) throws SocialServiceException{
		if(request == null || isBlank(request.getUserId()) || isBlank(request.getFriendId())){
			throw new SocialServiceException("Parâmetros incorretos!");
		}
		String userId = request.getUserId();
		String friendId = request.getFriendId();
		
		if(friendsMap.containsKey(userId)){
			if(!friendsMap.get(userId).contains(friendId)){
				friendsMap.get(userId).add(friendId);
			}
			createFriendAssociation(userId, friendId);
		} else {
			friendsMap.put(userId, new ArrayList<String>(Arrays.asList(friendId)));
			createFriendAssociation(userId, friendId);
		}
	}
	
	public static void removeFriend(BaseRequest request) throws SocialServiceException{
		if(request == null || isBlank(request.getUserId()) || isBlank(request.getFriendId())){
			throw new SocialServiceException("Parâmetros incorretos!");
		}
		String userId = request.getUserId();
		String friendId = request.getFriendId();
		
		if(friendsMap.containsKey(userId)){
			friendsMap.get(userId).remove(friendId);
		} 
		
		if(friendsMap.containsKey(friendId)){
			friendsMap.get(friendId).remove(userId);
		} 
	}
	
	/**
	 * Cria associacao entre os usuarios, adicionando o usuario a lista do amigo tambem adicionado
	 * @param userId
	 * @param friendId
	 */
	private static void createFriendAssociation(String userId, String friendId){
		if(friendsMap.containsKey(friendId)){
			if(!friendsMap.get(friendId).contains(userId)){
				friendsMap.get(friendId).add(userId);
			}
		} else {
			friendsMap.put(friendId, new ArrayList<String>(Arrays.asList(userId)));
		}
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
