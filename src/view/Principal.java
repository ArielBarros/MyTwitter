package view;

import java.io.File;

import control.MyTwitter;

import persistence.RepositorioUsuario;
import profile.PessoaFisica;
import profile.exception.PEException;


public class Principal {

	public static void main(String[] args) {
		File f = new File("AccountDB.xml");
		if(f.delete())
			System.out.println("Arquivo Do repositorio Deletado.");
		
		MyTwitter my = new MyTwitter(new RepositorioUsuario());
		
		try {
			my.criarPerfil(new PessoaFisica("Ana"));
		} catch (PEException e) {
			e.getMessage();
		}
	}
}
