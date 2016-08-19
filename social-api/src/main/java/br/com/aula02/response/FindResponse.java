package br.com.aula02.response;

import java.util.List;

import br.com.aula02.request.BaseRequest;

public class FindResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private List<BaseRequest> result;

	public List<BaseRequest> getResult() {
		return result;
	}

	public void setResult(List<BaseRequest> result) {
		this.result = result;
	}
}
