package Persistence;
import Profile.Perfil;


public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario);
	public void buscar(String usuario);
	public void atualizar(Perfil usuario);
}
