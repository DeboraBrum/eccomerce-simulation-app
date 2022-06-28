package com.deb.eccomerce.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="pedidos")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ped_id")
  private Long id;
  @Column(name="ped_total")
  private Long valorTotal;
  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  @JsonIgnoreProperties("pedido")
  private List<ItemProduto> lista;
  @ManyToOne
  @JoinColumn(name="us_id")
  private Usuario usuario;
  
}
