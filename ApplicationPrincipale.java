package com.jee.projet1;

// Importation des classes nécessaires pour démarrer une application Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Annotation qui indique que cette classe est le point d'entrée de l'application Spring Boot
@SpringBootApplication(scanBasePackages = {"com.jee.projet1.controller", "com.jee.projet1.model", "com.jee.projet1.repository"})
public class ApplicationPrincipale {

	// Méthode principale qui sert de point de départ à l'exécution de l'application
	public static void main(String[] arguments) {
		// Lancement de l'application Spring Boot à partir de la classe ApplicationPrincipale
		SpringApplication.run(ApplicationPrincipale.class, arguments);
	}

}
