package br.com.alexdev.ms.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TabuleiroTest {
	
	private Tabuleiro tabuleiro;
	private Tabuleiro tabuleiroInerte;
	private Tabuleiro tabuleiroPequeno;
	
	@Before
	public void setup() {
		tabuleiro = new Tabuleiro(10, 10, 10);
		tabuleiroInerte = new Tabuleiro(10, 10, 0);
		tabuleiroPequeno = new Tabuleiro(3, 3, 3);
	}
	
	@Test
	public void testParametrosTabuleiro() {
		assertEquals(10, tabuleiro.getLinhas());
		assertEquals(10, tabuleiro.getColunas());
		assertEquals(10, tabuleiro.getMinas());
	}
	@Test
	public void testCamposTabuleiro() {
		assertEquals(100, tabuleiro.getCamposCount());
	}
	@Test
	public void testAbrirCampoPeloTabuleiro() {
		tabuleiroInerte.abrir(3, 3);
		Campo campoAberto = tabuleiroInerte
				.getCampos()
				.stream()
				.filter(campo -> campo.getLinha_X() == 3)
				.filter(campo -> campo.getColuna_Y() == 3)
				.findFirst()
				.orElse(null);
		assertTrue(campoAberto.isAberto());
	}
	@Test
	public void testMarcarCampoPeloTabuleiro() {
		tabuleiroInerte.alterarMarcacao(3, 3);
		Campo campoMarcado = tabuleiroInerte
				.getCampos()
				.stream()
				.filter(campo -> campo.getLinha_X() == 3)
				.filter(campo -> campo.getColuna_Y() == 3)
				.findFirst()
				.orElse(null);
		assertTrue(campoMarcado.isMarcado());
	}
	@Test
	public void testReiniciarJogo() {
		tabuleiro.reiniciarJogo();

		assertFalse(tabuleiro.objetivoAlcancado());
		assertEquals(10, tabuleiro.getLinhas());
		assertEquals(10, tabuleiro.getColunas());
		assertEquals(10, tabuleiro.getMinas());
		assertTrue(tabuleiro
					.getCampos()
					.stream()
					.allMatch(campo -> campo.isFechado()));
	}
	@Test
	public void testToString() {
		String tabuleiro3x3Str = "   0  1  2  \n"
							   + "0  ?  ?  ? \n"
							   + "1  ?  ?  ? \n"
							   + "2  ?  ?  ? \n";
		assertEquals(tabuleiro3x3Str, tabuleiroPequeno.toString());
		
	}
}
