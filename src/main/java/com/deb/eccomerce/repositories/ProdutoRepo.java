package com.deb.eccomerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deb.eccomerce.models.Produto;

public interface ProdutoRepo extends JpaRepository<Produto, Long>{
  
}
