package control;

import java.io.File;

//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import persistence.RepositorioUsuario;
import profile.PessoaFisica;
import profile.exception.PEException;
import control.MyTwitter;

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
			my.criarPerfil(new PessoaFisica("Ana"));
		} catch (PEException pee) {
			pee.getMessage();
		}
	}
	
	@Test
	public void cancelarPerfiltest() {

	}
	
	@Test
	public void criarPerfilUsuarioExistentetest() {

	}
	
	@Test
	public void criarPerfilUsuarioDesativadotest() {

	}
}
