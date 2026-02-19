package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Pessoa;

public class main {

	public static void main(String[] args) {
		String arquivo = "pessoa.txt";
		
		Pessoa p1 = new Pessoa("Ricardo", 50);
		
		//Serialização de Objeto -> arquivo
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))){
			oos.writeObject(p1);
		} catch(IOException e) {
			System.out.println("Erro ao serializar: " + e.getMessage());
			
		}
		
		//Desserialização de arquivo -> Objeto
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))){
			Pessoa p2 = (Pessoa) ois.readObject();
			System.out.println("Desserilizado: " + p2);

		} catch(IOException | ClassNotFoundException e) {
			System.out.println("Erro ao desserializar: " + e.getMessage());
			
		}
		
	}

}
