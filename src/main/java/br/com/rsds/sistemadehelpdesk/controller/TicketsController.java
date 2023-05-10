package br.com.rsds.sistemadehelpdesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rsds.sistemadehelpdesk.model.Tickets;
import br.com.rsds.sistemadehelpdesk.repository.TicketsRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/Tickets-list")
@AllArgsConstructor
@Validated
public class TicketsController {

	TicketsRepository ticketRepository;

	@GetMapping
	public List<Tickets> list() {
		return ticketRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tickets> FindById(@PathVariable @NotNull @Positive Long id) {
		return ticketRepository.findById(id).map(recordFind -> ResponseEntity.ok(recordFind))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Tickets create(@RequestBody @Valid Tickets record) {
		return this.ticketRepository.save(record);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tickets> update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid Tickets record) {
		return ticketRepository.findById(id).map(recordFind -> {
			recordFind.setAssunto(record.getAssunto());
			recordFind.setCategoria(record.getCategoria());
			recordFind.setTecnico(record.getTecnico());
			recordFind.setNivel(record.getNivel());
			recordFind.setSolicitante(record.getSolicitante());
			recordFind.setCriacao(record.getCriacao());
			recordFind.setVencimento(record.getVencimento());
			return ResponseEntity.ok().body(ticketRepository.save(recordFind));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable @NotNull @Positive Long id) {
		return ticketRepository.findById(id).map(recordFind -> {
			ticketRepository.deleteById(id);
			return ResponseEntity.noContent().<Void>build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
