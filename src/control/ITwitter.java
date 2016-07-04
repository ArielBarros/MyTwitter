package control;
import java.util.Vector;

import profile.exception.MFPException;
import profile.exception.PDException;
import profile.exception.PEException;
import profile.exception.PIException;
import profile.exception.PJSException;
import profile.exception.SIException;
import profile.Perfil;
import profile.Tweet;

public interface ITwitter {
	public void criarPerfil(Perfil usuario) throws PEException;
	public void cancelarPerfil(String usuario) throws PDException, PIException;
	public void tweetar(String usuario, String mensagem) throws PIException, PDException, MFPException;
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException;
	public Vector<Tweet> tweets(String usuario) throws PDException, PIException;
	public void seguir(String seguidor, String seguido) throws PDException, PIException, SIException, PJSException;
	public int numeroSeguidores(String usuario) throws PDException, PIException;
	public Vector<Perfil> seguidores(String usuario) throws PDException, PIException;
}