package ufg.br.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ufg.br.entity.Livro;
import ufg.br.repository.LivroRepository;
import ufg.br.responses.Response;

// A anotação @RestController permite definir um controller com características API REST;
@RestController
public class LivroController {
	// A anotação @Autowired delega ao Spring Boot a inicialização do objeto;
    @Autowired
    private LivroRepository livroRepository;
    // A anotação @RequestMapping permite definir uma rota
    @RequestMapping(value = "/livro", method = RequestMethod.GET)
    public List<Livro> Get() {
        return livroRepository.findAll();
    }
    
    // @PathVariable indica que o valor da variável virá de uma informação da rota;
    @RequestMapping(value = "/livro/{id}", method = RequestMethod.GET)
    public ResponseEntity<Livro> GetById(@PathVariable(value = "id") long id)
    {
    	// https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html (desde v 1.8)
    	// findById espera um retorno do tipo Optional<Pessoa>
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent())
            return new ResponseEntity<Livro>(livro.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // @RequestBody indica que o valor do objeto virá do corpo da requisição e 
    //              consegue mapear os dados vindos em Json para os atributos da classe;
    @RequestMapping(value = "/livro", method =  RequestMethod.POST)
    public ResponseEntity<Response<Livro>> Post(@Valid @RequestBody Livro livro, BindingResult result)
    {
    	Response<Livro> response = new Response<Livro>();
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}
    	livroRepository.save(livro);
    	response.setData(livro);
        return ResponseEntity.ok(response);
    }        

    @RequestMapping(value = "/livro/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Response<Livro>> Put(@PathVariable(value = "id") long id, @Valid @RequestBody
            Livro newLivro, BindingResult result)
    {
        Optional<Livro> oldLivro = livroRepository.findById(id);
    	Response<Livro> response = new Response<Livro>();
    	if (result.hasErrors()) {
    		result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
    		return ResponseEntity.badRequest().body(response);
    	}        	        
        if(oldLivro.isPresent()){
            Livro livro = oldLivro.get();
            livro.setNome(newLivro.getNome());
            livro.setSinopse(newLivro.getSinopse());
            livro.setOpiniao(newLivro.getOpiniao());
            livro.setNota(newLivro.getNota());
            response.setData(livro);
            livroRepository.save(livro);
            return ResponseEntity.ok(response);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
    @RequestMapping(value = "/livro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent()){
            livroRepository.delete(livro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }    
    
}	