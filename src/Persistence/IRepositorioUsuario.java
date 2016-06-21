package Persistence;
import Persistence.exception.PEException;
import Profile.Perfil;


public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws PEException;
	public Perfil buscar(String usuario);
	public void atualizar(Perfil usuario);
	public int numeroDePerfis();
}
