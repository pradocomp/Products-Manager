package com.project.produto.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.produto.model.Produto;
import com.project.produto.model.TipoMadeira;
import com.project.produto.model.TipoStatus;
import com.project.produto.repository.filter.ProdutoFilter;
import com.project.produto.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	//Methods
	private static final String INIT_MAPPING = "novo";
	
	//Pages
	private static final String FORM_PAGE = "CadastroProduto";
	private static final String FILTER_PAGE = "PesquisaProduto";
	
	private static final String REDIRECT_NEW_MAPPING = "redirect:/produtos/novo";
	private static final String REDIRECT_SEARCH_MAPPING = "redirect:/produtos";
	
	/**
	 * Método que inicializa a tela de cadastro de produtos
	 * 
	 * @return
	 */
	@RequestMapping(INIT_MAPPING)
	public ModelAndView novo() {
		ModelAndView model = new ModelAndView(FORM_PAGE);
		model.addObject(new Produto());
		
		return model;
	}
	
	/**
	 * Método que persiste os dados do produto da base de dados
	 * 
	 * @param produto
	 * @param erros
	 * @param attributes
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Produto produto, Errors erros, RedirectAttributes attributes) {
		if (erros.hasErrors()) {
			return FORM_PAGE;
		}
		produtoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto Adicionado com sucesso!");
		return REDIRECT_NEW_MAPPING;
	}
	
	/**
	 * Método que realiza as pesquisas dos produtos
	 * 
	 * @return
	 */
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") ProdutoFilter filtro) {
		ModelAndView model = new ModelAndView(FILTER_PAGE);
		List<Produto> listaProdutos = produtoService.filtrar(filtro);
		model.addObject("produtos", listaProdutos);
		return model;
	}
	
	/**
	 * Método que edita os produtos
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Produto produto) {
		ModelAndView model = new ModelAndView(FORM_PAGE);
		model.addObject(produto);
		return model;
	}
	
	/**
	 * Método que realiza as pesquisas dos produtos
	 * 
	 * @param produto
	 * @param attributes
	 * @return
	 */
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable("id") Produto produto, RedirectAttributes attributes) {
		produtoService.excluir(produto);
		attributes.addFlashAttribute("mensagem", "Produto Excluido com sucesso!");
		return REDIRECT_SEARCH_MAPPING;
	}
	
	/**
	 * Método que atualiza o status dos produtos (para vendido) após o check
	 * 
	 * @param produto
	 * @return
	 */
	@RequestMapping(value = "/{id}/atualizar", method = RequestMethod.PUT)
	public @ResponseBody String atualizar (@PathVariable("id") Produto produto) {
		return produtoService.atualizar(produto);
	}
	
	/**
	 * Método que carrega os dados da combo de madeira
	 * 
	 * @return
	 */
	@ModelAttribute("tiposMadeira")
	public List<TipoMadeira> getTipoMadeira() {
		return Arrays.asList(TipoMadeira.values());
	}
	
	/**
	 * Método que carrega os dados da combo de status do produto
	 * 
	 * @return
	 */
	@ModelAttribute("tipoStatus")
	public List<TipoStatus> getTipoStatus() {
		return Arrays.asList(TipoStatus.values());
	}

}
