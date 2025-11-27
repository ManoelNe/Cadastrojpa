package aplicacao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 
		// Conexão JPA
		 
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		 EntityManager em = emf.createEntityManager();
		 
		 System.out.println("=== MENU ===");
		 System.out.println("01 - Cadastrar Pessoa");
		 System.out.println("02 - Consultar Pessoas");
		 System.out.println("03 - Remover Pessoa por ID");
		 System.out.print("Escolha uma opção: ");
		 int opcao = sc.nextInt();
		 sc.nextLine();
		 
		 
		if (opcao == 1 ) {
			 System.out.print("Digite o nome: ");
		     String nome = sc.nextLine();  // lê o nome digitado

		     System.out.print("Digite o email: ");
		     String email = sc.nextLine(); // lê o email digitado
		     
		     System.out.print("Digite a idade: ");
		     Double idade = sc.nextDouble(); // lê o email digitado
		      
		      Pessoa p = new Pessoa(null, nome, email, idade);
		      
		      // Salvar no banco 
		      em.getTransaction().begin();
		      em.persist(p);
		      em.getTransaction().commit();
		}
		else if (opcao == 2) {
		    List<Pessoa> lista = em.createQuery("FROM Pessoa", Pessoa.class).getResultList();

		    System.out.println("\n=== PESSOAS CADASTRADAS ===");
		    for (Pessoa pessoa : lista) {
		        System.out.println(pessoa);
		    }

		} else if (opcao == 3) {
		    System.out.print("Digite o ID da pessoa para remover: ");
		    int id = sc.nextInt();

		    Pessoa pessoa = em.find(Pessoa.class, id);

		    if (pessoa != null) {
		        em.getTransaction().begin();
		        em.remove(pessoa);
		        em.getTransaction().commit();
		        System.out.println("Pessoa removida com sucesso!");
		    } else {
		        System.out.println("ID não encontrado!");
		    }

		} else {
		    System.out.println("Opção inválida!");
		}
		
	      
	      
	      
	      // Fechar tudo
	        em.close();
	        emf.close();
	        sc.close();
	}

}


