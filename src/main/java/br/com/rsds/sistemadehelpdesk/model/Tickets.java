package br.com.rsds.sistemadehelpdesk.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "ASSUNTO")
	private String assunto;

	@NotNull
	@NotBlank
	@Column(name = "CATEGORIA")
	private String categoria;

	@NotNull
	@NotBlank
	@Column(name = "TECNICO")
	private String tecnico;

	@NotNull
	@NotBlank
	@Column(name = "NIVEL")
	private String nivel;

	@NotNull
	@NotBlank
	@Column(name = "SOLICITANTE")
	private String solicitante;

	@NotNull
	@NotBlank
	@Column(name = "CRIACAO")
	private String criacao = LocalDate.now().toString();

	@NotNull
	@NotBlank
	@Column(name = "URGENCIA")
	private String urgencia;

	@NotNull
	@NotBlank
	@Column(name = "VENCIMENTO")
	private String vencimento;
}
