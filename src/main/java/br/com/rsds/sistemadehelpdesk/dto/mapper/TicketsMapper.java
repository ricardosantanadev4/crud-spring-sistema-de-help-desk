package br.com.rsds.sistemadehelpdesk.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.rsds.sistemadehelpdesk.dto.TicketsDTO;
import br.com.rsds.sistemadehelpdesk.model.Tickets;

@Component
public class TicketsMapper {
	public TicketsDTO toDTO(Tickets ticket) {

		if (ticket == null) {
			return null;
		}

		return new TicketsDTO(ticket.getStatus(), ticket.getId(), ticket.getAssunto(), ticket.getCategoria(),
				ticket.getTecnico(), ticket.getNivel(), ticket.getSolicitante(), ticket.getCriacao(),
				ticket.getUrgencia(), ticket.getVencimento());
	}

	public Tickets toEntity(TicketsDTO ticketDTO) {

		if (ticketDTO == null) {
			return null;
		}

		Tickets ticket = new Tickets();
		if (ticketDTO.id() != null) {
			ticket.setId(ticketDTO.id());
		}

		ticket.setStatus(ticketDTO.status());
		ticket.setAssunto(ticketDTO.assunto());
		ticket.setCategoria(ticketDTO.categoria());
		ticket.setTecnico(ticketDTO.tecnico());
		ticket.setNivel(ticketDTO.nivel());
		ticket.setSolicitante(ticketDTO.solicitante());
		ticket.setCriacao(ticketDTO.criacao());
		ticket.setUrgencia(ticketDTO.urgencia());
		ticket.setVencimento(ticketDTO.vencimento());
		return ticket;
	}
}
