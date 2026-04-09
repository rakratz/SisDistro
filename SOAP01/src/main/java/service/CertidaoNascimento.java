package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

//Service Endpoint Interface(SEI)
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface CertidaoNascimento {
	
	@WebMethod
	public int calcularIdade(String idade);
	
	@WebMethod
	public String diaSemanaNascimento(String idade);
}
