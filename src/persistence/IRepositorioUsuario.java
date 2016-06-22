package persistence;
import persistence.exception.PEException;
import profile.Perfil;


public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws PEException;
	public Perfil buscar(String usuario);
	public void atualizar(Perfil usuario);
	public int numeroDePerfis();
}
