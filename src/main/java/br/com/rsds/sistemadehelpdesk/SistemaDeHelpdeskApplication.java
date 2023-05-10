package br.com.rsds.sistemadehelpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.rsds.sistemadehelpdesk.model.Tickets;
import br.com.rsds.sistemadehelpdesk.repository.TicketsRepository;

@SpringBootApplication
public class SistemaDeHelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeHelpdeskApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(TicketsRepository ticketsRepository) {
		ticketsRepository.deleteAll();
		return args -> {
			Tickets tickets = new Tickets();
			tickets.setStatus(tickets.getStatus());
			tickets.setAssunto("Softphone - Instalar,Configuração");
			tickets.setCategoria("Dispositivo");
			tickets.setTecnico("Suporte Técnico - 01");
			tickets.setNivel("Nível 1");
			tickets.setSolicitante("helpdesk@rsds.com.br");
			tickets.setCriacao(tickets.getCriacao());
			tickets.setUrgencia("Baixa");
			tickets.setVencimento("10/02/2023 10:20");
			ticketsRepository.save(tickets);
		};
	}
}
