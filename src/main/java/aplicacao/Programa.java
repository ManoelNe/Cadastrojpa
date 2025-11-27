package aplicacao;

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
		 
		 System.out.println("=== Cadastro de Pessoa ===");
		 
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
	      
	      // Fechar tudo
	        em.close();
	        emf.close();
	        sc.close();
	}

}


