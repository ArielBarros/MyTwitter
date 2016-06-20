package Profile;


public class PessoaJuridica extends Perfil {
	private long cnpj;
	
	public PessoaJuridica(String usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	
}
