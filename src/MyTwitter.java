import java.util.Vector;

import Persistence.IRepositorioUsuario;
import Persistence.exception.PEException;
import Profile.Perfil;
import Profile.Tweet;

public class MyTwitter implements ITwitter{
	
	private IRepositorioUsuario repositorio;
	
	public MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void criarPerfil(Perfil usuario) throws MyTwitterOperationException{
		try {
			this.repositorio.cadastrar(usuario);
		} catch (PEException pee) {
			throw new MyTwitterOperationException(pee);
		}
	}

	@Override
	public void cancelarPerfil(String usuario) {
		try {
			//this.repositorio.
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void tweetar(String usuario, String mensagem) {
		//cercar com try catch
		tweetar(usuario, mensagem);
	}

	@Override
	public Vector<Tweet> timeline(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Tweet> tweets(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void seguir(String seguidor, String seguido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int numeroSeguidores(String Usuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Perfil> seguidores(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
