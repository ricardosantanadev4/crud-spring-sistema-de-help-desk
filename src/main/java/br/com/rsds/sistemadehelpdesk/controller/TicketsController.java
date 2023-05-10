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
import br.com.rsds.sistemadehelpdesk.service.TicketsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/Tickets-list")
@Validated
public class TicketsController {

	private final TicketsRepository ticketRepository;
	private final TicketsService ticketsService;

	public TicketsController(TicketsRepository ticketRepository, TicketsService ticketsService) {
		this.ticketRepository = ticketRepository;
		this.ticketsService = ticketsService;
	}

	@GetMapping
	public List<Tickets> list() {
		return ticketsService.list();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tickets> FindById(@PathVariable @NotNull @Positive Long id) {
		return ticketsService.FindById(id).map(recordFind -> ResponseEntity.ok(recordFind))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Tickets create(@RequestBody @Valid Tickets record) {
		return this.ticketsService.create(record);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tickets> update(@PathVariable @NotNull @Positive Long id,
			@RequestBody @Valid Tickets record) {
		return ticketsService.update(id, record).map(recordFound -> ResponseEntity.ok(recordFound))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable @NotNull @Positive Long id) {
		if (ticketsService.remove(id)) {
			return ResponseEntity.noContent().<Void>build();
		}
		return ResponseEntity.notFound().build();
	}
}
