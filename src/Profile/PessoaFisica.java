package Profile;


public class PessoaFisica extends Perfil {
	private long cpf;
	
	public PessoaFisica(String usuario) {
		super(usuario);
		// TODO Auto-generated constructor stub
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

}
