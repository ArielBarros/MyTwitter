package persistence;
import java.io.FileNotFoundException;
import java.io.IOException;

import profile.Perfil;
import profile.exception.PDException;
import profile.exception.PEException;
import profile.exception.PIException;

public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws PEException;
	public Perfil buscar(String usuario) throws PDException, PIException;
	public void atualizar(Perfil usuario);
	public void carregarDados() throws FileNotFoundException;
	public void salvarDados() throws IOException;
}
