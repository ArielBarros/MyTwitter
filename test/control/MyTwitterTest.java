package control;

import java.io.File;

//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import persistence.RepositorioUsuario;
import profile.PessoaFisica;
import profile.exception.PDException;
import profile.exception.PEException;
import profile.exception.PIException;
import control.MyTwitter;

public class MyTwitterTest {
	
	private MyTwitter my;
	
	@Before
	public void MyTwitterInittest() {
		File f = new File("AccountDB.xml");
		if(f.exists())
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
		criarPerfiltest();
		try {
			my.cancelarPerfil("Ana");
		} catch (PDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void criarPerfilUsuarioExistentetest() {

	}
	
	@Test
	public void criarPerfilUsuarioDesativadotest() {

	}
}
