package br.com.rsds.sistemadehelpdesk.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Tickets {
	@NotNull
	@NotBlank
	@Pattern(regexp = "Aberto|Fechado")
	@Column(name = "STATUS")
	private String status = TicketsStatus.Aberto.toString();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@NotBlank
	@Column(name = "ASSUNTO", nullable = false)
	private String assunto;

	@NotNull
	@NotBlank
	@Column(name = "CATEGORIA", nullable = false)
	private String categoria;

	@NotNull
	@NotBlank
	@Column(name = "TECNICO", nullable = false)
	private String tecnico;

	@NotNull
	@NotBlank
	@Column(name = "NIVEL", nullable = false)
	private String nivel;

	@NotNull
	@NotBlank
	@Column(name = "SOLICITANTE", nullable = false)
	private String solicitante;

	@NotNull
	@NotBlank
	@Column(name = "CRIACAO", nullable = false)
	private String criacao = LocalDate.now().toString();

	@NotNull
	@NotBlank
	@Column(name = "URGENCIA", nullable = false)
	private String urgencia;

	@NotNull
	@NotBlank
	@Column(name = "VENCIMENTO", nullable = false)
	private String vencimento;
}
