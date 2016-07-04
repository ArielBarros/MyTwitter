package view;

import java.io.File;

import control.MyTwitter;

import persistence.RepositorioUsuario;
import profile.Perfil;
import profile.PessoaFisica;
import profile.Tweet;
import profile.exception.MFPException;
import profile.exception.PDException;
import profile.exception.PEException;
import profile.exception.PIException;
import profile.exception.PJSException;
import profile.exception.SIException;


public class TesteMyTwitter {

	public TesteMyTwitter(){
		/*
		 * Criar perfil passou
		 * cancelarperfil passou
		 * seguir passou
		 * seguidores passou
		 * numeroDeSeguidores passou
		 * twettar passou
		 * timeline passou
		 * tweets passou
		 */
		
		/*
		
		*/
		File f = new File("AccountDB.xml");
		if(f.delete())
			System.out.println("Arquivo Do repositorio Deletado.");
		MyTwitter my = new MyTwitter(new RepositorioUsuario());
		
		try {
			my.criarPerfil(new PessoaFisica("Ana"));
		} catch (PEException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.criarPerfil(new PessoaFisica("JJ"));
		} catch (PEException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.criarPerfil(new PessoaFisica("João"));
		} catch (PEException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.seguir("Ana", "JJ");
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (PIException e) {
			System.out.println(e.getMessage());
		} catch (SIException e) {
			System.out.println(e.getMessage());
		}catch (PJSException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.seguir("João", "JJ");
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (PIException e) {
			System.out.println(e.getMessage());
		} catch (SIException e) {
			System.out.println(e.getMessage());
		}catch (PJSException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			for (Perfil seguidor : my.seguidores("JJ")) {
				System.out.println(seguidor.getUsuario());
			}
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (PIException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(my.numeroSeguidores("JJ"));
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (PIException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.cancelarPerfil("Ana");
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (PIException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.tweetar("JJ", "Minha primeira publicação");
		} catch (PIException e) {
			System.out.println(e.getMessage());
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (MFPException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			my.tweetar("João", "Olá, eu sou o João");
		} catch (PIException e) {
			System.out.println(e.getMessage());
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (MFPException e) {
			System.out.println(e.getMessage());
		}
		
		try {			
			for (Tweet tweet : my.timeline("João")) {
				System.out.print(tweet.getUsuario()+": ");
				System.out.println(tweet.getMensagem());
			}
		} catch (PIException e) {
			System.out.println(e.getMessage());
		} catch (PDException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			for (Tweet tweet : my.tweets("João")) {
				System.out.print(tweet.getUsuario()+": ");
				System.out.println(tweet.getMensagem());
			}
		} catch (PDException e) {
			System.out.println(e.getMessage());
		} catch (PIException e) {
			System.out.println(e.getMessage());
		}
	}
}
