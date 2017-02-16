package com.project.produto.model;

public enum TipoStatus {
	
	DISPONIVEL("Disponível"),
	VENDIDO("Vendido");

	private String descricao;
	
	private TipoStatus(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao () {
		return descricao;
	}
}
