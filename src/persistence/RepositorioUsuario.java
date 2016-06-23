package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import persistence.exception.PDException;
import persistence.exception.PEException;
import persistence.exception.PIException;
import profile.Perfil;

public class RepositorioUsuario implements IRepositorioUsuario {
	
	public static final String ACCOUNT_DB_XML_NAME = "AccountDB.xml";
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
	private void carregarDados() throws FileNotFoundException {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("perfil", Perfil.class);
		perfis = ((Vector<Perfil>) xstream.fromXML(new FileReader(ACCOUNT_DB_XML_NAME), Vector.class));
		System.out.println("Número de perfis cadastrados: "+perfis.size());
	}

	private void salvarDados() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("perfil", Perfil.class);
		xstream.toXML(perfis, new FileWriter(ACCOUNT_DB_XML_NAME));
	}

	@Override
	public void cadastrar(Perfil usuario) throws PEException {
		try{
			this.buscar(usuario.getUsuario());
			throw new PEException("O Perfil já existe! ", usuario.getUsuario());
		}catch (PDException pde){
			throw new PEException("O Perfil já existe e está desativado! ", usuario.getUsuario());
		}catch (PIException pie) {
			this.perfis.addElement(usuario);
			try {
				salvarDados();
			} catch (IOException ioe) {
				throw new PEException("O Perfil não pode ser criado! " + ioe.getMessage(), usuario.getUsuario());
			}
		}
		
		
		/*
		if (this.buscar(usuario.getUsuario()) == null) {
			this.perfis.addElement(usuario);
			try {
				salvarDados();
			} catch (IOException ioe) {
				throw new PEException("O Perfil não pode ser criado! " + ioe.getMessage(), usuario.getUsuario());
			}
		}else{
			throw new PEException("O Perfil já existe! ", usuario.getUsuario());
		}
		*/
	}

	@Override
	public Perfil buscar(String usuario) throws PDException, PIException {
		if (this.perfis.size() > 0) {
			for (int i = 0; i < this.perfis.size(); i++) {
				Perfil perfil = (Perfil) this.perfis.elementAt(i);
				if (perfil.getUsuario().equals(usuario)) {
					if(perfil.isAtivo()){
							return perfil;
					}else{
						throw new PDException("Perfil está Desativado !", usuario);
					}
				}
			}
			throw new PIException("Perfil inexistente !", usuario);
		}else{
			throw new PIException("Perfil inexistente !", usuario);
		}
	}

	@Override
	public void atualizar(Perfil usuario) {
		// TODO Auto-generated method stub
	}

	@Override
	public int numeroDePerfis() {
		return this.perfis.size();
	}
}
