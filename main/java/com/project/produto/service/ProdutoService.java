package com.project.produto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.produto.model.Produto;
import com.project.produto.model.TipoStatus;
import com.project.produto.repository.Produtos;
import com.project.produto.repository.filter.ProdutoFilter;

@Service
public class ProdutoService {
	
	@Autowired
	private Produtos produtos;
	
	/**
	 * Realiza a persistência do objeto na base
	 * 
	 * @param produto
	 */
	public void salvar(Produto produto) {
		produtos.save(produto);
	}
	
	/**
	 * Realiza a exclusão do objeto na base
	 * 
	 * @param codigo
	 */
	public void excluir(Produto produto) {
		produtos.delete(produto);
	}

	/**
	 * Atualiza o status do objeto via Ajax
	 * 
	 * @param produto
	 */
	public String atualizar(Produto produto) {
		produto.setStatus(TipoStatus.VENDIDO);
		produtos.save(produto);
		return TipoStatus.VENDIDO.getDescricao();
	}

	/**
	 * Lista os produtos do filtro 
	 * 
	 * @param filtro
	 * @return
	 */
	public List<Produto> filtrar(ProdutoFilter filtro) {
		String codigo = filtro.getCodigo() == null ? "%" : filtro.getCodigo();
		return produtos.findByCodigoContaining(codigo);
	}
	
}
