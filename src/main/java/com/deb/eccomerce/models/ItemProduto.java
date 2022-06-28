package com.deb.eccomerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_do_pedido")
public class ItemProduto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;
  @Column(name = "item_valor")
  private Long valorUnitario;
  @Column(name = "item_qtde")
  private Integer quantidade;
  @Column(name = "item_total")
  private Long valorTotal;
  @ManyToOne
  @JoinColumn(name = "ped_id")
  @JsonIgnoreProperties("lista")
  private Pedido pedido;
  @ManyToOne
  @JoinColumn(name = "prod_id")
  private Produto produto;
}
