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
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
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
}
