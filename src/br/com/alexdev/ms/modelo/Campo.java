package br.com.alexdev.ms.modelo;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

import br.com.alexdev.ms.excecao.ExplosaoException;

public class Campo {
	
	private final int linha_X;
	private final int coluna_Y;
	
	private boolean minado;

	private boolean aberto;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int x, int y){
		this.linha_X = x;
		this.coluna_Y = y;
	}
	
	boolean addVizinho(Campo vizinho) {
		boolean isDiagonal = linha_X != vizinho.linha_X && coluna_Y != vizinho.coluna_Y;
		int delta = abs(linha_X - vizinho.linha_X) + abs(coluna_Y - vizinho.coluna_Y);
		
		if (delta == 1 && !isDiagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (delta == 2 && isDiagonal) {
			vizinhos.add(vizinho);
			return true;
		} else 
			return false;
	}
	
	void alternarMarcacao() {
		if (!aberto)
			marcado = !marcado;
	}
	
	void minar() {
		minado = true;
	}
	
	boolean abrir() {
		if (!aberto && !marcado) {
			aberto = true;
			
			if (minado)
				throw new ExplosaoException();
			
			if (vizinhacaSegura())
				vizinhos.forEach(v -> v.abrir());
			
			return true;
		} else
			return false;
	}
	
	
	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	public boolean isAberto() {
		return aberto;
	}
}
