package com.project.produto.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O Código é obrigatório")
	private String codigo;
	
	@NotEmpty(message = "O nome é obrigatório")
	private String nome;
	
	@Size(max = 200, message = "A descrição não pode ultrapassar 60 caracteres")
	private String descricao;
	
	@NotNull(message = "O Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor não pode ser negativo")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	
	@Enumerated(EnumType.STRING)
	private TipoMadeira madeira;
	
	@Enumerated(EnumType.STRING)
	private TipoStatus status;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public TipoMadeira getMadeira() {
		return madeira;
	}
	
	public void setMadeira(TipoMadeira madeira) {
		this.madeira = madeira;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public TipoStatus getStatus() {
		return status;
	}
	
	public void setStatus(TipoStatus status) {
		this.status = status;
	}
	
	public boolean isDisponivel() {
		return TipoStatus.DISPONIVEL.equals(this.status);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
