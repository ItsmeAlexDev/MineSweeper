package br.com.alexdev.ms.visao;

import static java.lang.Integer.parseInt;
import static java.lang.System.in;
import static java.util.Arrays.stream;

import java.util.Iterator;
import java.util.Scanner;

import br.com.alexdev.ms.excecao.ExplosaoException;
import br.com.alexdev.ms.excecao.SaidaException;
import br.com.alexdev.ms.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner input = new Scanner(in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while (continuar) {
				gameLoop();
				
				System.out.println("novo jogo? (S/n)");
				String respostaInput = input.nextLine();
				
				if("n".equalsIgnoreCase(respostaInput))
					continuar = false;
				else
					tabuleiro.reiniciarJogo();
			}
		} catch(SaidaException e) {
			System.out.println("FIM DE JOGO!");
		} finally {
			input.close();
		}
	}

	private void gameLoop() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String input = getInput("Digite (x, y): ");
				
				Iterator<Integer> xy = stream(input.split(","))
					.map(e -> parseInt(e.trim()))
					.iterator();
				
				input = getInput("1 - Abrir / 2 - (Des)Marcar");
				
				if ("1".equals(input))
					tabuleiro.abrir(xy.next(), xy.next());
				if ("2".equals(input))
					tabuleiro.abrir(xy.next(), xy.next());
			}

			System.out.println(tabuleiro);
			System.out.println("Você ganhou, gg :D");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu :/");
		}
	}
	
	private String getInput(String texto) {
		System.out.println(texto);
		String input = this.input.nextLine();
		
		if("sair".equalsIgnoreCase(input) ||
		   "exit".equalsIgnoreCase(input))
			throw new SaidaException();
		
		return input;
	}
}
