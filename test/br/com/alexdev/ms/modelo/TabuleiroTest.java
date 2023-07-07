package br.com.alexdev.ms.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TabuleiroTest {
	
	private Tabuleiro tabuleiro;
	
	@Before
	public void setup() {
		tabuleiro = new Tabuleiro(10, 10, 10);
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
}
