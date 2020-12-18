package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String tarefa;
	
	@ManyToOne
	@JsonIgnore
	private Lista lista;
	
	// 0 == nao
	// 1 == sim
	private int concluida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public int getConcluida() {
		return concluida;
	}

	public void setConcluida(int concluida) {
		this.concluida = concluida;
	}
}
