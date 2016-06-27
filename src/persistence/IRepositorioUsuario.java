package persistence;
import java.io.FileNotFoundException;
import java.io.IOException;

import persistence.exception.UJCException;
import profile.Perfil;
import profile.exception.PDException;
import profile.exception.PIException;

public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws UJCException;
	public Perfil buscar(String usuario) throws PDException, PIException;
	public void atualizar(Perfil usuario);
	public void carregarDados() throws FileNotFoundException;
	public void salvarDados() throws IOException;
}
