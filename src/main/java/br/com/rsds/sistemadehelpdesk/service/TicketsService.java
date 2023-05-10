package br.com.rsds.sistemadehelpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	@GetMapping
	public List<Tickets> list() {
		return ticketRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Tickets> FindById(@PathVariable @NotNull @Positive Long id) {
		return ticketRepository.findById(id);
	}

	@PostMapping
	public Tickets create(@Valid Tickets record) {
		return this.ticketRepository.save(record);
	}

	@PutMapping("/{id}")
	public Optional<Tickets> update(@NotNull @Positive Long id, @RequestBody @Valid Tickets record) {
		return ticketRepository.findById(id).map(recordFind -> {
			recordFind.setAssunto(record.getAssunto());
			recordFind.setCategoria(record.getCategoria());
			recordFind.setTecnico(record.getTecnico());
			recordFind.setNivel(record.getNivel());
			recordFind.setSolicitante(record.getSolicitante());
			recordFind.setCriacao(record.getCriacao());
			recordFind.setVencimento(record.getVencimento());
			return ticketRepository.save(recordFind);
		});
	}

	@DeleteMapping("/{id}")
	public Boolean remove(Long id) {
		return ticketRepository.findById(id).map(recordFind -> {
			ticketRepository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
