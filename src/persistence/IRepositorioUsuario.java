package persistence;
import persistence.exception.PDException;
import persistence.exception.PEException;
import persistence.exception.PIException;
import profile.Perfil;


public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws PEException;
	public Perfil buscar(String usuario) throws PDException, PIException;
	public void atualizar(Perfil usuario);
	public int numeroDePerfis();
}
