package Persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Persistence.exception.PEException;
import Profile.Perfil;

public class UsuarioXstream implements IRepositorioUsuario {
	
	public static final String ACCOUNT_DB_XML_NAME = "AccountDB.xml";
	private Vector<Perfil> perfis = null;
	
	public UsuarioXstream() {
		try {
			loadData();
		} catch (FileNotFoundException fnfe) {
			this.perfis = new Vector<Perfil>();
			try {
				saveData();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadData() throws FileNotFoundException {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("perfil", Perfil.class);
		perfis = ((Vector<Perfil>) xstream.fromXML(new FileReader(ACCOUNT_DB_XML_NAME), Vector.class));
		System.out.println(perfis.size());
	}

	private void saveData() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("perfil", Perfil.class);
		xstream.toXML(perfis, new FileWriter(ACCOUNT_DB_XML_NAME));
	}

	@Override
	public void cadastrar(Perfil usuario) throws PEException{
		if (this.buscar(usuario.getUsuario()) == null) {
			this.perfis.addElement(usuario);
			try {
				saveData();
			} catch (IOException ioe) {
				throw new PEException("O Perfil não pode ser criado! " + ioe.getMessage(), usuario.getUsuario());
			}
		}else{
			throw new PEException("O Perfil já existe! ", usuario.getUsuario());
		}
	}

	@Override
	public Perfil buscar(String usuario) {
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
	public void atualizar(Perfil usuario) {
		// TODO Auto-generated method stub
	}

	@Override
	public int numeroDePerfis() {
		return this.perfis.size();
	}
}
