package com.project.produto.model;

public enum TipoMadeira {

	ANGICO("Angico"),
	TAMBURIL("Tamburil"),
	EUCALIPTO("Eucalipto"),
	SIBIPIRUNA("Sibipiruna"),
	BALSAMO("Bálsamo"),
	CUMARU("Cumarú"),
	IPE_CHAMPAGNE("Ipê Champagne"),
	JACARANDA_VERMELHO("Jacarandá Vermelho"),
	JATOBA("Jatobá"),
	CEDRO_ROSA("Cedro Rosa"),
	DORMENTE("Dormente");
	
	private String descricao;
	
	private TipoMadeira(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
