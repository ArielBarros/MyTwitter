package control;
import java.util.Vector;

import control.exception.MyTwitterOperationException;
import persistence.exception.MFPException;
import profile.Perfil;
import profile.Tweet;
import profile.exception.SIException;

public interface ITwitter {
	public void criarPerfil(Perfil usuario) throws MyTwitterOperationException;
	public void cancelarPerfil(String usuario) throws MyTwitterOperationException;
	public void tweetar(String usuario, String mensagem) throws MFPException, MyTwitterOperationException;
	public Vector<Tweet> timeline(String usuario) throws MyTwitterOperationException;
	public Vector<Tweet> tweets(String usuario) throws MyTwitterOperationException;
	public void seguir(String seguidor, String seguido) throws SIException, MyTwitterOperationException;
	public int numeroSeguidores(String usuario) throws MyTwitterOperationException;
	public Vector<Perfil> seguidores(String usuario) throws MyTwitterOperationException;
}
