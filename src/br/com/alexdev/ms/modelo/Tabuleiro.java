package br.com.alexdev.ms.modelo;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import br.com.alexdev.ms.excecao.ExplosaoException;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private int minas;
	
	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;
		
		gerarCampos();
		associarVizinhos();
		minarCampos();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.stream()
				.filter(campo -> campo.getLinha_X() == linha &&
				     	         campo.getColuna_Y() == coluna)
				.findFirst()
				.ifPresent(campo -> campo.abrir());
		} catch (ExplosaoException e) {
			
			campos.forEach(campo -> campo.setAberto(true));
			
			throw e;
		}
	}
	
	public void alterarMarcacao(int linha, int coluna) {
		campos.stream()
			.filter(campo -> campo.getLinha_X() == linha &&
			     	         campo.getColuna_Y() == coluna)
			.findFirst()
			.ifPresent(campo -> campo.alternarMarcacao());
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++)
			for (int coluna = 0; coluna < linhas; coluna++)
				campos.add(new Campo(linha, coluna));
	}

	private void associarVizinhos() {
		for (Campo campo_1 : campos)
			for (Campo campo_2 : campos)
				campo_1.addVizinho(campo_2);
	}
	
	private void minarCampos() {
		long camposMinados = 0;
		
		while (camposMinados < minas) {
			int rnd = (int) (Math.random() * campos.size());
			campos.get(rnd).minar();
			camposMinados = campos.stream().filter(campo -> campo.isMinado()).count();
		}
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(campo -> campo.objetivoAlcancado());
	}
	
	public void reiniciarJogo() {
		campos.stream().forEach(campo -> campo.reiniciar());
		minarCampos();
	}
	
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public int getMinas() {
		return minas;
	}
	
	public List<Campo> getCampos() {
		return unmodifiableList(campos);
	}

	public int getCamposCount() {
		return campos.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		
		stringbuilder.append("   ");
		
		for (int index_coluna = 0; index_coluna < colunas; index_coluna++)
			stringbuilder.append(index_coluna + "  ");
		
		stringbuilder.append("\n");
		
		int camposIndex = 0;

		for (int linha = 0; linha < linhas; linha++) {
			stringbuilder.append(linha + " ");
			
			for (int coluna = 0; coluna < colunas; coluna++) {
				stringbuilder.append(" " + campos.get(camposIndex) + " ");
				camposIndex++;
			}
			stringbuilder.append("\n");
		}
		return stringbuilder.toString();
	}
}
