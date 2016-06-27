package view;

import java.io.File;

import control.MyTwitter;
import control.exception.MyTwitterOperationException;
import persistence.RepositorioUsuario;
import persistence.exception.MFPException;
import profile.Perfil;
import profile.PessoaFisica;
import profile.Tweet;
import profile.exception.SIException;

public class Principal {

	public static void main(String[] args) {
		File f = new File("AccountDB.xml");
		if(f.delete())
			System.out.println("Arquivo Do repositorio Deletado.");
		
		MyTwitter my = new MyTwitter(new RepositorioUsuario());
		
		try {
			my.criarPerfil(new PessoaFisica("José"));
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			my.criarPerfil(new PessoaFisica("Ana"));
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			my.criarPerfil(new PessoaFisica("Beto"));
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			my.cancelarPerfil("Beto");
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			my.seguir("José", "Ana");
		} catch (SIException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("---Numero de perfil do usuario especificado---");
			System.out.println(my.numeroSeguidores("Ana"));
			System.out.println("-----------------------------------------");
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("---Todos os perfis do usuário especificado---");
			for (Perfil perfil : my.seguidores("Ana")) {
				System.out.println(perfil.getUsuario());
			}
			System.out.println("-----------------------------------------");
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			my.tweetar("Ana", "Meu primeiro Twitt !!");
		} catch (MFPException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			my.tweetar("José", "twitt do josé!!");
		} catch (MFPException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("---Todos os Twetts da timeline do usuário especificado---");
			for (Tweet tweet : my.timeline("José")) {
				System.out.print(tweet.getUsuario()+":  ");
				System.out.println(tweet.getMensagem());
			}
			System.out.println("-----------------------------------------");
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("---Somente os Twetts do usuário especificado---");
			for (Tweet tweet : my.tweets("José")) {
				System.out.print(tweet.getUsuario()+":  ");
				System.out.println(tweet.getMensagem());
			}
			System.out.println("-----------------------------------------");
		} catch (MyTwitterOperationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
