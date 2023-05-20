package br.com.rsds.sistemadehelpdesk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import br.com.rsds.sistemadehelpdesk.dto.TicketsDTO;
import br.com.rsds.sistemadehelpdesk.service.TicketsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/Tickets-list")
@Validated
public class TicketsController {

	private final TicketsService ticketsService;

	public TicketsController(TicketsService ticketsService) {
		this.ticketsService = ticketsService;
	}

	@GetMapping
	public List<TicketsDTO> list() {
		return ticketsService.list();
	}

	@GetMapping("/{id}")
	public TicketsDTO FindById(@PathVariable @NotNull @Positive Long id) {
		return ticketsService.FindById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public TicketsDTO create(@RequestBody @Valid @NotNull TicketsDTO record) {
		return this.ticketsService.create(record);
	}

	@PutMapping("/{id}")
	public TicketsDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull TicketsDTO record) {
		return ticketsService.update(id, record);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable @NotNull @Positive Long id) {
		ticketsService.remove(id);
	}
}
