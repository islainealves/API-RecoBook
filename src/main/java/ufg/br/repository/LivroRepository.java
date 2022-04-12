package ufg.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufg.br.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> { }