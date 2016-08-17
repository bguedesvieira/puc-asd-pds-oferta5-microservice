package br.com.aula02.response;

import java.util.List;

import br.com.aula02.bo.Client;

public class FindResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private List<Client> result;

	public List<Client> getResult() {
		return result;
	}

	public void setResult(List<Client> result) {
		this.result = result;
	}
}
