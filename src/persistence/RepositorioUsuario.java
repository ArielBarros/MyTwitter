package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import persistence.exception.UJCException;
import persistence.exception.UNCException;
import profile.Perfil;
import profile.PessoaFisica;
import profile.PessoaJuridica;

public class RepositorioUsuario implements IRepositorioUsuario {
	
	public static final String PERFIS_DB_XML_NAME = "Repositorio.xml";
	private Vector<Perfil> perfis = null;
	
	public RepositorioUsuario() {
		try {
			carregarDados();
		} catch (FileNotFoundException fnfe) {
			this.perfis = new Vector<Perfil>();
			try {
				salvarDados();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void carregarDados() throws FileNotFoundException {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("perfil", Perfil.class);
		perfis = ((Vector<Perfil>) xstream.fromXML(new FileReader(PERFIS_DB_XML_NAME), Vector.class));
		System.out.println("Número de Perfis cadastrados: "+perfis.size());
	}

	public void salvarDados() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("perfil", Perfil.class);
		xstream.toXML(perfis, new FileWriter(PERFIS_DB_XML_NAME));
	}

	@Override
	public void cadastrar(Perfil usuario) throws UJCException {
		
		if (this.buscar(usuario.getUsuario()) == null) {
			this.perfis.addElement(usuario);
			try {
				salvarDados();
			} catch (IOException ioe) {
				throw new UJCException("O Perfil não pode ser criado! " + ioe.getMessage(), usuario.getUsuario());
			}
		}else{
			throw new UJCException("O Perfil já existe! ", usuario.getUsuario());
		}
	}

	@Override
	public Perfil buscar(String usuario){
		if (this.perfis.size() > 0) {
			for (int i = 0; i < this.perfis.size(); i++) {
				Perfil perfil = (Perfil) this.perfis.elementAt(i);
				if (perfil.getUsuario().equals(usuario)) {
					return perfil;
				}
			}
		}
		return null;
	}

	@Override
	public void atualizar(Perfil usuario) throws UNCException {		
		Perfil perfil = this.buscar(usuario.getUsuario());
		if(perfil != null){
			if (perfil instanceof PessoaFisica) {
				if(usuario instanceof PessoaFisica){
					perfil = usuario;	
				}
			}else if(perfil instanceof PessoaJuridica){
				if(usuario instanceof PessoaJuridica){
					perfil = usuario;
				}
			}
		}else{
			throw new UNCException("Usuario não cadastrado", usuario.getUsuario());
		}
	}
	
}
