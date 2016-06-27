package control;
import java.io.IOException;
import java.util.Vector;

import persistence.IRepositorioUsuario;
import persistence.exception.UJCException;
import profile.Perfil;
import profile.Tweet;
import profile.exception.MFPException;
import profile.exception.PDException;
import profile.exception.PEException;
import profile.exception.PIException;
import profile.exception.SIException;

public class MyTwitter implements ITwitter{
	
	private IRepositorioUsuario repositorio;
	
	public MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void criarPerfil(Perfil usuario) throws PEException {
			try {
				this.repositorio.cadastrar(usuario);
			} catch (UJCException ujce) {
				throw new PEException("O Perfil já existe! ", usuario.getUsuario());
			}
			atualizarDados();
	}

	private void atualizarDados(){
		try {
			this.repositorio.salvarDados();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar no repositório");
		}
	}
	
	@Override
	public void cancelarPerfil(String usuario) throws PDException, PIException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				perfil.setAtivo(false);
			}else {
				throw new PDException("Perfil Já está Desativado !", usuario);
			}
		} else {
			throw new PIException("Perfil inexistente !", usuario);
		}
		atualizarDados();
	}

	@Override
	public void tweetar(String usuario, String mensagem) throws PIException, PDException, MFPException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				if( (mensagem.length() > 0) && (mensagem.length() <= 140) ){
					Tweet tweet = new Tweet(usuario, mensagem);
					perfil.addTweet(tweet);
					Vector<String> seguidores = perfil.getSeguidores();
					for (int i = 0; i < perfil.getSeguidores().size(); i++) {
						Perfil seguidor = this.repositorio.buscar(seguidores.elementAt(i));
						if ( seguidor != null) {
							if (seguidor.isAtivo()) {
								seguidor.addTweet(tweet);
							}
						}
					}
				} else {
					throw new MFPException("Mensagem fora do padrão !", usuario);
				}
			} else {
				throw new PDException("Perfil Desativado !", usuario);
			}
		} else {
			throw new PIException("Perfil inexistente !", usuario);
		}
		atualizarDados();
	}
	
	@Override
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getTimeline();
			}else{
				throw new PDException("Perfil Desativado !", usuario);
			}
		}else{
			throw new PIException("Perfil inexistente !", usuario);
		}
	}
	
	@Override
	public Vector<Tweet> tweets(String usuario) throws PDException, PIException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Tweet> TodosOsTweets = perfil.getTimeline();
				Vector<Tweet> PropriosTweets = new Vector<Tweet>();
				for (int i = 0; i < TodosOsTweets.size(); i++) {
					if (TodosOsTweets.elementAt(i).getUsuario().equals(usuario)) {
						PropriosTweets.add(TodosOsTweets.elementAt(i));
					}
				}
				 return PropriosTweets;
			}else{
				throw new PDException("Perfil Desativado !", usuario);
			}
		}else{
			throw new PIException("Perfil inexistente !", usuario);
		}
	}
	
	@Override
	public void seguir(String seguidor, String seguido) throws PDException, PIException, SIException {
		//falta implementar se o seguidor ja segue o perfil seguido
		if(this.repositorio.buscar(seguidor) != null){
			throw new PIException("Perfil seguidor inexistente !", seguidor);
		}else if(this.repositorio.buscar(seguido) != null){
			throw new PIException("Perfil seguido inexistente !", seguido);
		}else if(this.repositorio.buscar(seguidor).isAtivo()){
			throw new PDException("Perfil seguidor Desativado !", seguidor);
		}else if(this.repositorio.buscar(seguido).isAtivo()){
			throw new PDException("Perfil seguido Desativado !", seguido);
		}else if(this.repositorio.buscar(seguidor).getUsuario().equals(seguido)){
			throw new SIException("Perfil seguidor inválido !", seguidor);
		}else{
			this.repositorio.buscar(seguido).addSeguidor(seguidor);
			atualizarDados();
		}
	}
	
	@Override
	public int numeroSeguidores(String usuario) throws PDException, PIException{
		return seguidores(usuario).size();
	}
	
	@Override
	public Vector<Perfil> seguidores(String usuario) throws PDException, PIException {
		Perfil perfil = this.repositorio.buscar(usuario);
		Vector<Perfil> seguidores = new Vector<Perfil>();
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<String> listaDosSeguidores = perfil.getSeguidores();
				for (int i = 0; i < perfil.getSeguidores().size(); i++) {
					Perfil seguidor = this.repositorio.buscar(listaDosSeguidores.elementAt(i));
					if (seguidor != null) {
						if (seguidor.isAtivo()) {
							seguidores.add(seguidor);
						}
					}
				}
			} else {
				throw new PDException("Perfil Desativado !", usuario);
			}
		}else {
			throw new PIException("Perfil inexistente !", usuario);
		}
		return seguidores;
	}
}
