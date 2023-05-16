package br.com.rsds.sistemadehelpdesk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.sistemadehelpdesk.exception.RecordNotFoundException;
import br.com.rsds.sistemadehelpdesk.model.Tickets;
import br.com.rsds.sistemadehelpdesk.repository.TicketsRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class TicketsService {

	private final TicketsRepository ticketRepository;

	public TicketsService(TicketsRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public List<Tickets> list() {
		return ticketRepository.findAll();
	}

	public Tickets FindById(@PathVariable @NotNull @Positive Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public Tickets create(@Valid Tickets record) {
		return this.ticketRepository.save(record);
	}

	public Tickets update(@NotNull @Positive Long id, @RequestBody @Valid Tickets record) {
		return ticketRepository.findById(id).map(recordFind -> {
			recordFind.setAssunto(record.getAssunto());
			recordFind.setCategoria(record.getCategoria());
			recordFind.setTecnico(record.getTecnico());
			recordFind.setNivel(record.getNivel());
			recordFind.setSolicitante(record.getSolicitante());
			recordFind.setCriacao(record.getCriacao());
			recordFind.setVencimento(record.getVencimento());
			return ticketRepository.save(recordFind);
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void remove(Long id) {
		ticketRepository.delete(ticketRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
