package view;

import java.util.Scanner;

import persistence.RepositorioUsuario;
import profile.Perfil;
import profile.PessoaFisica;
import profile.PessoaJuridica;
import profile.Tweet;
import profile.exception.MFPException;
import profile.exception.PDException;
import profile.exception.PEException;
import profile.exception.PIException;
import profile.exception.PJSException;
import profile.exception.SIException;
import control.MyTwitter;

public class Principal {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		MyTwitter my = new MyTwitter(new RepositorioUsuario());
		boolean loop = true;
		while (loop) {
			switch (menu()) {
			case 1:
				int key = 0;
				System.out.println(" [1] Cadastrar Pessoa Física");
				System.out.println(" [2] Cadastrar Pessoa Jurídica");
				key = scanner.nextInt();
				switch (key) {
					case 1:
						try {
							System.out.println("Digite o nome do Perfil a ser cadastrado: ");
							Perfil perfil = new PessoaFisica(scanner.next());
							my.criarPerfil(perfil);
							System.out.println("Digite o CPF da pessoa física: ");
							((PessoaFisica) perfil).setCpf(scanner.nextLong());
							System.out.println("Cadastro realizado com sucesso !");
						} catch (PEException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2:
						try {
							System.out.println("Digite o nome do Perfil a ser cadastrado: ");
							Perfil perfil = new PessoaJuridica(scanner.next());
							my.criarPerfil(perfil);
							System.out.println("Digite o CNPJ da pessoa jurídica: ");
							((PessoaJuridica) perfil).setCnpj(scanner.nextLong());
							System.out.println("Cadastro realizado com sucesso !");
						} catch (PEException e) {
							System.out.println(e.getMessage());
						}
						break;
	
					default:
						System.out.println("Escolha inválida");
						break;
				}
				break;
			case 2:
				try {
					System.out.println("Digite o nome do Perfil a ser cancelado: ");
					my.cancelarPerfil(scanner.next());
					System.out.println("Perfil cancelado com sucesso !");
				} catch (PDException e) {
					System.out.println(e.getMessage());
				} catch (PIException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					System.out.println("Digite o nome do Perfil que vai publicar o tweet: ");
					String usuario = scanner.next();
					System.out.println("Digite a mensagem do tweet: ");
					String mensagem = scanner.next();
					my.tweetar(usuario, mensagem);
					System.out.println("Tweet publicado com sucesso !");
				} catch (PIException e) {
					System.out.println(e.getMessage());
				} catch (PDException e) {
					System.out.println(e.getMessage());
				} catch (MFPException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					System.out.println("Digite o nome do Perfil Seguidor: ");
					String seguidor = scanner.next();
					System.out.println("Digite o nome do Perfil à ser seguido: ");
					String seguido = scanner.next();
					my.seguir(seguidor, seguido);
					System.out.println("Seguido com sucesso !");
				} catch (PDException e) {
					System.out.println(e.getMessage());
				} catch (PIException e) {
					System.out.println(e.getMessage());
				} catch (SIException e) {
					System.out.println(e.getMessage());
				}catch (PJSException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {			
					System.out.println("Digite o nome do Perfil para resgatar a timeline: ");
					for (Tweet tweet : my.timeline(scanner.next())) {
						System.out.print(tweet.getUsuario()+": ");
						System.out.println(tweet.getMensagem());
					}
				} catch (PIException e) {
					System.out.println(e.getMessage());
				} catch (PDException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				try {
					System.out.println("Digite o nome do Perfil para resgatar os Tweets: ");
					for (Tweet tweet : my.tweets(scanner.next())) {
						System.out.print(tweet.getUsuario()+": ");
						System.out.println(tweet.getMensagem());
					}
				} catch (PDException e) {
					System.out.println(e.getMessage());
				} catch (PIException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				try {
					System.out.println("Digite o nome do Perfil para resgatar os Seguidores: ");
					for (Perfil seguidor : my.seguidores(scanner.next())) {
						System.out.println(seguidor.getUsuario());
					}
				} catch (PDException e) {
					System.out.println(e.getMessage());
				} catch (PIException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 8:
				try {
					System.out.println("Digite o nome do Perfil para resgatar o número total de seguidores: ");
					System.out.println(my.numeroSeguidores(scanner.next()));
				} catch (PDException e) {
					System.out.println(e.getMessage());
				} catch (PIException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 9:
				System.out.print("Tchau, volte sempre !!!");
				loop = false;
				break;
			default:
				break;
			}
		}
	}

	private static int menu() {
		System.out.println("--------------------------------");
		System.out.println("-----------MyTwitter------------");
		System.out.println("--------------------------------");
		System.out.println(" [1] Criar Perfil");
		System.out.println(" [2] Cancelar Perfil");
		System.out.println(" [3] Twettar");
		System.out.println(" [4] Seguir");
		System.out.println(" [5] Resgatar Timeline");
		System.out.println(" [6] Resgatar Tweets");
		System.out.println(" [7] Resgatar Seguidores");
		System.out.println(" [8] Numero de Seguidores");
		System.out.println(" [9] Sair");
		System.out.println("--------------------------------");
		System.out.println("Entre com a opção: ");
		return scanner.nextInt();
	}
}
