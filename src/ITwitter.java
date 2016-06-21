import java.util.Vector;
import Profile.Perfil;
import Profile.Tweet;

public interface ITwitter {
	public void criarPerfil(Perfil usuario) throws MyTwitterOperationException;
	public void cancelarPerfil(String usuario);
	public void tweetar(String usuario, String mensagem);
	public Vector<Tweet> timeline(String usuario);
	public Vector<Tweet> tweets(String usuario);
	public void seguir(String seguidor, String seguido);
	public int numeroSeguidores(String Usuario);
	public Vector<Perfil> seguidores(String usuario);
}
