package br.com.aula02.bo;

public class Client {

	private String registrationId;
	private String name;
	private String phone;
	private String address;
	
	
	@Override
	public boolean equals(Object obj) {
		Client client = (Client) obj;
		return this.registrationId.equals(client.getRegistrationId());
	}
		
	@Override
	public String toString() {
		StringBuilder objString = new StringBuilder();
		
		objString.append("Nome: ").append(this.name).append(",\n")
		.append("Matrícula: ").append(this.registrationId).append(",\n")
		.append("Telefone: ").append(this.phone).append(",\n")
		.append("Endereço: ").append(this.address).append(",\n");
		
		return objString.toString();
	}
	
	public boolean match(String value){
		if(value == null || value.trim().isEmpty())
			return false;
		if(this.name != null && this.name.toLowerCase().trim().contains(value.toLowerCase().trim()))
			return true;
		if(this.registrationId != null && this.registrationId.equals(value))
			return true;
		if(this.phone != null && this.phone.contains(value))
			return true;
		if(this.address != null && this.address.toLowerCase().trim().contains(value.toLowerCase().trim()))
			return true;
		
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String id) {
		this.registrationId = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
