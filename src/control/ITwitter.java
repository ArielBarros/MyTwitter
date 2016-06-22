package control;
import java.util.Vector;

import control.exception.MyTwitterOperationException;
import persistence.exception.MFPException;
import persistence.exception.PDException;
import persistence.exception.PIException;
import persistence.exception.SIException;
import profile.Perfil;
import profile.Tweet;

public interface ITwitter {
	public void criarPerfil(Perfil usuario) throws MyTwitterOperationException;
	public void cancelarPerfil(String usuario) throws PDException, PIException;
	public void tweetar(String usuario, String mensagem) throws PIException, PDException, MFPException;
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException;
	public Vector<Tweet> tweets(String usuario) throws PDException, PIException;
	public void seguir(String seguidor, String seguido) throws PDException, PIException, SIException;
	public int numeroSeguidores(String usuario) throws PDException, PIException;
	public Vector<Perfil> seguidores(String usuario) throws PDException, PIException;
}
