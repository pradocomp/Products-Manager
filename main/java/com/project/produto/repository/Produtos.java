package com.project.produto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.produto.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long> {
	
	public List<Produto> findByCodigoContaining(String codigo);
	
}
