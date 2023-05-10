package br.com.rsds.sistemadehelpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rsds.sistemadehelpdesk.model.Tickets;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {

}
