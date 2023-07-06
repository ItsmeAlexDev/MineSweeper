package br.com.alexdev.ms.modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.alexdev.ms.excecao.ExplosaoException;



public class CampoTest {

	private Campo campo;
	
	@Before
	public void setup() {
		campo = new Campo(5, 5);
	}

	@Test
	public void testVizinhoValido_left_up() {
		Campo vizinhoCandidate_1_left_up = new Campo(4, 4);
		assertTrue(campo.addVizinho(vizinhoCandidate_1_left_up));
	}
	@Test
	public void testVizinhoValido__up() {
		Campo vizinhoCandidate_2_up = new Campo(4, 5);
		assertTrue(campo.addVizinho(vizinhoCandidate_2_up));
	}
	@Test
	public void testVizinhoValido_right_up() {
		Campo vizinhoCandidate_3_right_up = new Campo(4, 6);
		assertTrue(campo.addVizinho(vizinhoCandidate_3_right_up));
	}
	@Test
	public void testVizinhoValido_left() {
		Campo vizinhoCandidate_4_left = new Campo(5, 4);
		assertTrue(campo.addVizinho(vizinhoCandidate_4_left));
	}
	@Test
	public void testVizinhoValido_right() {
		Campo vizinhoCandidate_5_right = new Campo(5, 6);
		assertTrue(campo.addVizinho(vizinhoCandidate_5_right));
	}
	@Test
	public void testVizinhoValido_left_down() {
		Campo vizinhoCandidate_6_left_down = new Campo(6, 4);
		assertTrue(campo.addVizinho(vizinhoCandidate_6_left_down));
	}
	@Test
	public void testVizinhoValido_down() {
		Campo vizinhoCandidate_7_down = new Campo(6, 5);
		assertTrue(campo.addVizinho(vizinhoCandidate_7_down));
	}
	@Test
	public void testVizinhoValido_right_down() {
		Campo vizinhoCandidate_8_right_down = new Campo(6, 6);
		assertTrue(campo.addVizinho(vizinhoCandidate_8_right_down));
	}
	@Test
	public void testVizinhosInvalidos() {
		Campo vizinhoCandidate_1 = new Campo(1, 1);
		Campo vizinhoCandidate_2 = new Campo(2, 1);
		Campo vizinhoCandidate_3 = new Campo(1, 2);
		Campo vizinhoCandidate_4 = new Campo(2, 2);
		
		assertFalse(campo.addVizinho(vizinhoCandidate_1));
		assertFalse(campo.addVizinho(vizinhoCandidate_2));
		assertFalse(campo.addVizinho(vizinhoCandidate_3));
		assertFalse(campo.addVizinho(vizinhoCandidate_4));
	}
	@Test
	public void valorDefaultAberto() {
		assertFalse(campo.isAberto());
	}
	@Test
	public void valorDefaultMinado() {
		assertFalse(campo.isMinado());
		}
	@Test
	public void testValorMinadoAposMinar() {
		campo.minar();
		assertTrue(campo.isMinado());
	}
	@Test
	public void testValorAbertoAposAbrir() {
		campo.abrir();
		assertTrue(campo.isAberto());
	}
	@Test
	public void valorDefaultMarcado() {
		assertFalse(campo.isMarcado());
	}
	@Test
	public void alternarMarcacao() {
		
		assertFalse(campo.isMarcado());
		
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		

		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	@Test
	public void testAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	@Test
	public void testAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	@Test
	public void testAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	@Test
	public void testAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {campo.abrir();});
	}
	@Test
	public void testVizinhacaSegura() {
		Campo campo1 = new Campo(3, 3);
		
		Campo campo2 = new Campo(4, 4);
		campo2.addVizinho(campo1);
		
		campo.addVizinho(campo2);
		campo.abrir();

		assertTrue(campo.isAberto());
		assertTrue(campo2.isAberto());
		assertTrue(campo1.isAberto());
	}
	@Test
	public void testVizinhacaMinada() {
		Campo campo1 = new Campo(3, 3);
		
		Campo campo2 = new Campo(4, 4);
		campo2.addVizinho(campo1);
		campo2.minar();
		
		campo.addVizinho(campo2);
		campo.abrir();

		assertTrue(campo.isAberto());
		assertFalse(campo2.isAberto());
		assertFalse(campo1.isAberto());
	}
	@Test
	public void testCoordenadasGetters() {
		Campo campo1 = new Campo(3, 3);
		Campo campo2 = new Campo(4, 4);
		
		assertEquals(5, campo.getLinha_X());
		assertEquals(5, campo.getColuna_Y());

		assertEquals(3, campo1.getLinha_X());
		assertEquals(3, campo1.getColuna_Y());

		assertEquals(4, campo2.getLinha_X());
		assertEquals(4, campo2.getColuna_Y());
	}
	@Test
	public void testToString() {
		Campo campoMarcado = new Campo(1, 1);
		Campo campoAbertoMinado = new Campo(1, 1);
		Campo campoAbertoMinasNaVizinhaca = new Campo(1, 1);
		Campo campoAberto = new Campo(1, 1);
		Campo campoDefault = new Campo(1, 1);
		
		Campo campoVizinhoMinado = new Campo(1, 2);
		campoVizinhoMinado.minar();
		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado);
		
		campoMarcado.alternarMarcacao();
		assertEquals("x", campoMarcado.toString());
		
		campoAbertoMinado.abrir();
		campoAbertoMinado.minar();
		assertEquals("*", campoAbertoMinado.toString());
		
		campoAbertoMinasNaVizinhaca.abrir();
		assertEquals("1", campoAbertoMinasNaVizinhaca.toString());
		
		campoAberto.abrir();
		assertEquals(" ", campoAberto.toString());
		
		assertEquals("?", campoDefault.toString());
	}
	@Test
	public void minasVizinhacaToString() {
		
		Campo campoAbertoMinasNaVizinhaca = new Campo(2, 2);
		campoAbertoMinasNaVizinhaca.abrir();
		
		Campo campoVizinhoMinado1 = new Campo(1, 1);
		campoVizinhoMinado1.minar();
		Campo campoVizinhoMinado2 = new Campo(1, 2);
		campoVizinhoMinado2.minar();
		Campo campoVizinhoMinado3 = new Campo(1, 3);
		campoVizinhoMinado3.minar();
		Campo campoVizinhoMinado4 = new Campo(2, 1);
		campoVizinhoMinado4.minar();
		Campo campoVizinhoMinado5 = new Campo(2, 3);
		campoVizinhoMinado5.minar();
		Campo campoVizinhoMinado6 = new Campo(3, 1);
		campoVizinhoMinado6.minar();
		Campo campoVizinhoMinado7 = new Campo(3, 2);
		campoVizinhoMinado7.minar();
		Campo campoVizinhoMinado8 = new Campo(3, 3);
		campoVizinhoMinado8.minar();
		
		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado1);
		assertEquals("1", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado2);
		assertEquals("2", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado3);
		assertEquals("3", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado4);
		assertEquals("4", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado5);
		assertEquals("5", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado6);
		assertEquals("6", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado7);
		assertEquals("7", campoAbertoMinasNaVizinhaca.toString());

		campoAbertoMinasNaVizinhaca.addVizinho(campoVizinhoMinado8);
		assertEquals("8", campoAbertoMinasNaVizinhaca.toString());
	}
	@Test
	public void testReiniciar() {
		campo.abrir();
		campo.minar();
		campo.marcar();
		assertTrue(campo.isAberto());
		assertTrue(campo.isMinado());
		assertTrue(campo.isMarcado());
		
		campo.reiniciar();
		
		assertFalse(campo.isAberto());
		assertFalse(campo.isMinado());
		assertFalse(campo.isMarcado());
	}
	@Test
	public void testObjetivo() {
		Campo campoAbertoMinado = new Campo(1, 1);
		campoAbertoMinado.abrir();
		campoAbertoMinado.minar();
		
		Campo campoAbertoNaoMinado = new Campo(1, 1);
		campoAbertoNaoMinado.abrir();
		
		Campo campoNaoAbertoMinado = new Campo(1, 1);
		campoNaoAbertoMinado.minar();
		
		Campo campoNaoAbertoNaoMinado = new Campo(1, 1);
		
		Campo campoMinadoMarcado = new Campo(1, 1);
		campoMinadoMarcado.minar();
		campoMinadoMarcado.marcar();
		
		Campo campoMinadoNaoMarcado = new Campo(1, 1);
		campoMinadoNaoMarcado.minar();
		
		Campo campoNaoMinadoNaoMarcado = new Campo(1, 1);
		
		assertTrue(campoMinadoMarcado.objetivoAlcancado());
		assertTrue(campoAbertoNaoMinado.objetivoAlcancado());

		assertFalse(campoAbertoMinado.objetivoAlcancado());
		assertFalse(campoNaoAbertoNaoMinado.objetivoAlcancado());
		assertFalse(campoNaoAbertoMinado.objetivoAlcancado());
		assertFalse(campoMinadoNaoMarcado.objetivoAlcancado());
		assertFalse(campoNaoMinadoNaoMarcado.objetivoAlcancado());
		
	}
}
