package Profile;

import java.util.Vector;


public abstract class Perfil {
	private String usuario;
	private Vector<String> seguidores;
	private Vector<Tweet> timeline;
	private boolean ativo;
	
	public Perfil(String usuario) {
		this.usuario = usuario;
		this.seguidores = new Vector<String>();
		this.timeline = new Vector<Tweet>();
		setAtivo(true);
	}
	
	public void addSeguidor(String usuario){
		seguidores.add(usuario);
	}
	
	public void addTweet(Tweet tweet){
		timeline.add(tweet);
	}

	public String getUsuario() {
		return usuario;
	}

	public Vector<String> getSeguidores() {
		return seguidores;
	}

	public Vector<Tweet> getTimeline() {
		return timeline;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
