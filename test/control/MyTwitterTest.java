package control;

import java.io.File;

//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import persistence.RepositorioUsuario;
import profile.PessoaFisica;
import control.MyTwitter;
import control.exception.MyTwitterOperationException;

public class MyTwitterTest {
	
	private MyTwitter my;
	
	@Before
	public void MyTwitterInittest() {
		File f = new File("AccountDB.xml");
		f.delete();
		my = new MyTwitter(new RepositorioUsuario());
	}
	
	@Test
	public void criarPerfiltest() {
		try {
			my.criarPerfil(new PessoaFisica("José"));
			System.out.println("Passou: criarPerfiltest");
		} catch (MyTwitterOperationException e) {
			System.out.println("Não Passou: criarPerfiltest");
		}
	}
	
	@Test
	public void cancelarPerfiltest() {
		criarPerfiltest();
		try {
			my.cancelarPerfil("José");
			System.out.println("Passou: cancelarPerfiltest");
		} catch (MyTwitterOperationException e) {
			System.out.println("Não Passou: cancelarPerfiltest");
			//System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void criarPerfilUsuarioExistentetest() {
		criarPerfiltest();
		try {
			my.criarPerfil(new PessoaFisica("José"));
			System.out.println("Não Passou: criarPerfilUsuarioExistentetest");
		} catch (MyTwitterOperationException e) {
			System.out.println("Passou: criarPerfilUsuarioExistentetest");
			//System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void criarPerfilUsuarioDesativadotest() {
		criarPerfiltest();	
		cancelarPerfiltest();
		try {
			my.criarPerfil(new PessoaFisica("José"));
			System.out.println("Não Passou: criarPerfilUsuarioDesativadotest");
		} catch (MyTwitterOperationException e) {
			System.out.println("Passou: criarPerfilUsuarioDesativadotest");
			//System.out.println(e.getMessage());
		}
	}
}
