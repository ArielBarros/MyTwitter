package control;
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
	
	//falta chamar o salvardados em todos os métodos que precisam
	private IRepositorioUsuario repositorio;
	//private Perfil perfil;
	
	public MyTwitter(IRepositorioUsuario repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public void criarPerfil(Perfil usuario) throws MyTwitterOperationException {
		try {
			this.repositorio.cadastrar(usuario);
		} catch (PEException pee) {
			throw new MyTwitterOperationException(pee);
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
		Perfil perfilSeguidor = this.repositorio.buscar(seguidor);
		boolean igual = false;
		if (perfilSeguidor != null) {
			if (perfilSeguidor.isAtivo()) {
				Perfil perfilSeguido = this.repositorio.buscar(seguido);
				if (perfilSeguidor != perfilSeguido) {
					if (perfilSeguido != null) {
						if (perfilSeguido.isAtivo()) {
							Vector<String> seguidores = perfilSeguido.getSeguidores();
							for (int i = 0; i < perfilSeguido.getSeguidores().size(); i++) {
								if (seguidores.elementAt(i).equals(seguidor)) {
									igual = true;
								}
							}
							if(!igual){
								perfilSeguido.addSeguidor(seguidor);
							}else{
								//lançar a exceção para o seguidor que ja segue o seguido.
							}
						} else {
							throw new PDException("Perfil seguido Desativado !", seguido);
						}
					} else {
						throw new PIException("Perfil seguido inexistente !", seguido);
					}
				} else {
					throw new SIException("Perfil seguido inexistente !", seguido);
				}
			} else {
				throw new PDException("Perfil seguidor Desativado !", seguidor);
			}
		} else {
			throw new PIException("Perfil seguidor inexistente !", seguidor);
		}
	}

	@Override
	public int numeroSeguidores(String usuario) throws PDException, PIException {
		//modo mais dinâmico
		//return seguidores(usuario).size();
		
		Perfil perfil = this.repositorio.buscar(usuario);
		int numeroDeSeguidores = 0;
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<String> listaDosSeguidores = perfil.getSeguidores();
				for (int i = 0; i < perfil.getSeguidores().size(); i++) {
					Perfil seguidor = this.repositorio.buscar(listaDosSeguidores.elementAt(i));
					if (seguidor != null) {
						if (seguidor.isAtivo()) {
							numeroDeSeguidores++;
						}
					}
				}
			} else {
				throw new PDException("Perfil Desativado !", usuario);
			}
		}else {
			throw new PIException("Perfil inexistente !", usuario);
		}
		return numeroDeSeguidores;
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
