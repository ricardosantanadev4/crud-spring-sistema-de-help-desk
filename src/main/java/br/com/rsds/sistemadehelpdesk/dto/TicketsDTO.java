package br.com.rsds.sistemadehelpdesk.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TicketsDTO(
		@NotNull @NotBlank @Pattern(regexp = "Aberto|Fechado") String status ,
		Long id,
		@NotNull @NotBlank @Column(name = "ASSUNTO") String assunto,
		@NotNull @NotBlank String categoria,
		@NotNull @NotBlank String tecnico,
		@NotNull @NotBlank String nivel,
		@NotNull @NotBlank String solicitante,
		@NotNull @NotBlank String criacao,
		@NotNull @NotBlank String urgencia,
		@NotNull @NotBlank String vencimento
		) {

}
