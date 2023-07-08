package br.com.alexdev.ms.modelo;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

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
		campos.stream()
			.filter(campo -> campo.getLinha_X() == linha &&
			     	         campo.getColuna_Y() == coluna)
			.findFirst()
			.ifPresent(campo -> campo.abrir());
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
			camposMinados = campos.stream().filter(campo -> campo.isMinado()).count();
			int rnd = (int) (Math.random() * campos.size());
			campos.get(rnd).minar();
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
		StringBuilder sb = new StringBuilder();
		
		int camposIndex = 0;
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				sb.append(" ");
				sb.append(campos.get(camposIndex));
				sb.append(" ");
				camposIndex++;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
