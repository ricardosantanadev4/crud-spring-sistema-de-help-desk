package br.com.rsds.sistemadehelpdesk.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.rsds.sistemadehelpdesk.dto.TicketsDTO;
import br.com.rsds.sistemadehelpdesk.dto.mapper.TicketsMapper;
import br.com.rsds.sistemadehelpdesk.exception.RecordNotFoundException;
import br.com.rsds.sistemadehelpdesk.repository.TicketsRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class TicketsService {

	private final TicketsRepository ticketRepository;
	private final TicketsMapper ticketsMapper;

	public TicketsService(TicketsRepository ticketRepository, TicketsMapper ticketsMapper) {
		this.ticketRepository = ticketRepository;
		this.ticketsMapper = ticketsMapper;
	}

	public List<TicketsDTO> list() {
		return ticketRepository.findAll().stream().map(ticketsMapper::toDTO).collect(Collectors.toList());
	}

	public TicketsDTO FindById(@PathVariable @NotNull @Positive Long id) {
		return ticketRepository.findById(id).map(ticketsMapper::toDTO)
				.orElseThrow(() -> new RecordNotFoundException(id));
	}

	public TicketsDTO create(@Valid @NotNull TicketsDTO record) {
		return ticketsMapper.toDTO(ticketRepository.save(ticketsMapper.toEntity(record)));
	}

	public TicketsDTO update(@NotNull @Positive Long id, @RequestBody @Valid @NotNull TicketsDTO record) {
		return ticketRepository.findById(id).map(recordFind -> {
			recordFind.setAssunto(record.assunto());
			recordFind.setCategoria(record.categoria());
			recordFind.setTecnico(record.tecnico());
			recordFind.setNivel(record.nivel());
			recordFind.setSolicitante(record.solicitante());
			recordFind.setCriacao(record.criacao());
			recordFind.setVencimento(record.vencimento());
			return ticketsMapper.toDTO(ticketRepository.save(recordFind));
		}).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public void remove(Long id) {
		ticketRepository.delete(ticketRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
	}
}
