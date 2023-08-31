package br.com.alexdev.ms;

import br.com.alexdev.ms.modelo.Tabuleiro;
import br.com.alexdev.ms.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(5, 5, 5);
		new TabuleiroConsole(tabuleiro);
	}
}
