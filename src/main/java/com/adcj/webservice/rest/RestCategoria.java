package com.adcj.webservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adcj.webservice.model.Categoria;
import com.adcj.webservice.repository.RepositoryCategoria;

@CrossOrigin
@RestController
@RequestMapping("/ws/categoria/")
public class RestCategoria {
	
	@Autowired
	private RepositoryCategoria repCategoria;

	@RequestMapping(method=RequestMethod.POST)
	public void salvar(@RequestBody Categoria categoria) {
		repCategoria.save(categoria);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void excluir(@PathVariable("id") Long id) {
		repCategoria.deleteById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void alterar(@RequestBody Categoria categoria) {
		Categoria oldCategoria = repCategoria.findById(categoria.getId()).get();
		oldCategoria.setNome(categoria.getNome());
		oldCategoria.setDescricao(categoria.getDescricao());
		repCategoria.saveAndFlush(categoria);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar(){
		List<Categoria> list = repCategoria.findAll();
		return new ResponseEntity<List<Categoria>>(list, HttpStatus.OK);
	}
	
}
