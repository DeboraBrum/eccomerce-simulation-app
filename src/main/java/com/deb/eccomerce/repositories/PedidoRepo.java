package com.deb.eccomerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deb.eccomerce.models.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Long>{
  
}
