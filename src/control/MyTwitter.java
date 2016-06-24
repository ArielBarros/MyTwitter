package control;
import java.io.IOException;
import java.util.Vector;

import control.exception.MyTwitterOperationException;
import persistence.IRepositorioUsuario;
import persistence.exception.MFPException;
import persistence.exception.PDException;
import persistence.exception.PEException;
import persistence.exception.PIException;
import persistence.exception.SIException;
import profile.Perfil;
import profile.Tweet;

public class MyTwitter implements ITwitter{
	
	private IRepositorioUsuario repositorio;
	
	public MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void criarPerfil(Perfil usuario) throws MyTwitterOperationException {
		try {
			this.repositorio.cadastrar(usuario);
			atualizarDados();
		} catch (PEException pee) {
			throw new MyTwitterOperationException(pee);
		}
	}

	private void atualizarDados(){
		try {
			this.repositorio.salvarDados();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar no repositório");
		}
	}
	
	@Override
	public void cancelarPerfil(String usuario) throws MyTwitterOperationException {
		try{
			Perfil perfil = this.repositorio.buscar(usuario);
			perfil.setAtivo(false);
		}catch (PDException pde){
			throw new MyTwitterOperationException(pde);
		}catch (PIException pie) {
			throw new MyTwitterOperationException(pie);
		}
		atualizarDados();
	}

	@Override
	public void tweetar(String usuario, String mensagem) throws MyTwitterOperationException, MFPException {
		try{
			Perfil perfil = this.repositorio.buscar(usuario);
			if( (mensagem.length() > 0) && (mensagem.length() <= 140) ){
				Tweet tweet = new Tweet(usuario, mensagem);
				perfil.addTweet(tweet);
				Vector<String> seguidores = perfil.getSeguidores();
				for (int i = 0; i < perfil.getSeguidores().size(); i++) {
					try{
						Perfil seguidor = this.repositorio.buscar(seguidores.elementAt(i));
						seguidor.addTweet(tweet);
					}catch (PDException pde){} catch (PIException pie) {}
				}
			} else {
				throw new MFPException("Mensagem fora do padrão !", usuario);
			}
		}catch (PDException pde){
			throw new MyTwitterOperationException(pde);
		}catch (PIException pie) {
			throw new MyTwitterOperationException(pie);
		}
		atualizarDados();
	}
	

	@Override
	public Vector<Tweet> timeline(String usuario) throws MyTwitterOperationException {
		try{
			Perfil perfil = this.repositorio.buscar(usuario);
			return perfil.getTimeline();
		}catch (PDException pde){
			throw new MyTwitterOperationException(pde);
		}catch (PIException pie) {
			throw new MyTwitterOperationException(pie);
		}
	}
	

	@Override
	public Vector<Tweet> tweets(String usuario) throws MyTwitterOperationException {
		try{
			Perfil perfil = this.repositorio.buscar(usuario);
			Vector<Tweet> TodosOsTweets = perfil.getTimeline();
			Vector<Tweet> PropriosTweets = new Vector<Tweet>();
			for (int i = 0; i < TodosOsTweets.size(); i++) {
				if (TodosOsTweets.elementAt(i).getUsuario().equals(usuario)) {
					PropriosTweets.add(TodosOsTweets.elementAt(i));
				}
			}
			return PropriosTweets;
		}catch (PDException pde){
			throw new MyTwitterOperationException(pde);
		}catch (PIException pie) {
			throw new MyTwitterOperationException(pie);
		}
	}
	

	@Override
	public void seguir(String seguidor, String seguido) throws MyTwitterOperationException, SIException {
		boolean jaESeguidor = false;
		try {
			Perfil perfilSeguidor = this.repositorio.buscar(seguidor);
			try {
				Perfil perfilSeguido = this.repositorio.buscar(seguido);
				if (perfilSeguidor != perfilSeguido) {
					Vector<String> seguidores = perfilSeguido.getSeguidores();
					for (int i = 0; i < perfilSeguido.getSeguidores().size(); i++) {
						if (seguidores.elementAt(i).equals(seguidor)) {
							jaESeguidor = true;
							break;
						}
					}
					if(!jaESeguidor){
						perfilSeguido.addSeguidor(seguidor);
					}else{
						//lançar a exceção para o seguidor que ja segue o seguido.
					}
				}else{
					throw new SIException("Perfil seguidor inválido !", seguido);
				}
			}catch (PDException pde){
				throw new MyTwitterOperationException(pde);
			}catch (PIException pie) {
				throw new MyTwitterOperationException(pie);
			}
		}catch (PDException pde){
			throw new MyTwitterOperationException(pde);
		}catch (PIException pie) {
			throw new MyTwitterOperationException(pie);
		}
		atualizarDados();
	}
	

	@Override
	public int numeroSeguidores(String usuario) throws MyTwitterOperationException{
		return seguidores(usuario).size();
	}
	

	@Override
	public Vector<Perfil> seguidores(String usuario) throws MyTwitterOperationException {
		try{
			Perfil perfil = this.repositorio.buscar(usuario);
			Vector<Perfil> seguidores = new Vector<Perfil>();
			Vector<String> listaDosSeguidores = perfil.getSeguidores();
			for (int i = 0; i < perfil.getSeguidores().size(); i++) {
				try{
					Perfil seguidor = this.repositorio.buscar(listaDosSeguidores.elementAt(i));
					seguidores.add(seguidor);
				}catch (PDException pde){} catch (PIException pie) {}
			}
			return seguidores;
		}catch (PDException pde){
			throw new MyTwitterOperationException(pde);
		}catch (PIException pie) {
			throw new MyTwitterOperationException(pie);
		}
	}
}
