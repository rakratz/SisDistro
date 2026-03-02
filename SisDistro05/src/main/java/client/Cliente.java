package client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import api.Calculadora;
import config.RmiConfig;
import server.CalculadoraImpl;

public class Cliente {

	public static void main(String[] args) {
		try {
			Calculadora calc = (Calculadora) Naming.lookup(RmiConfig.serviceUrl());
			
			System.out.println("2 + 3 = " + calc.soma(2, 3));
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
