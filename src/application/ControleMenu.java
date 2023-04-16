package application;

import java.util.Iterator;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class ControleMenu {
	public static void testaBotoes(Group raiz) {
		
		((Button)raiz.lookup("#botao_salvartexto")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz);
			String texto = LeituraTexto.leituraTexto(raiz);
			if (!texto.isEmpty()) 
				SalvaArquivos.salvaArquivoTexto(raiz,texto);
		});
		
		((Button)raiz.lookup("#botao_carregartexto")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz);
			CarregaArquivos.carregaArquivoTexto(raiz);
		});
		
		((Button)raiz.lookup("#botao_salvarmidi")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz);
			String texto = LeituraTexto.leituraTexto(raiz);
			if (!texto.isEmpty()) 
				SalvaArquivos.salvaArquivoMidi(raiz,texto);
		});
		
		((Button)raiz.lookup("#botao_carregarmidi")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz);
			CarregaArquivos.carregaArquivosMidi(raiz);
		});
		
		((Button)raiz.lookup("#botao_ajuda")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz);
			MenuAjuda.exibeAjuda(raiz);
		});
		
		((Button)raiz.lookup("#botao_tocarmusica")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz);
			String texto = LeituraTexto.leituraTexto(raiz);
			if (!texto.isEmpty()) 
				Reprodutor.reproduzSom(raiz,texto);
		});
	}
	
	public static void apagaTextoInformativo(Group raiz) {
		Iterator<Node> iterador = raiz.getChildren().iterator();
		while (iterador.hasNext()) {
		    Node filho = iterador.next();
		    if (filho instanceof Text) {
		        iterador.remove();
		    }
		}
	}
}
