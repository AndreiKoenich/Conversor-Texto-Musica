package application;

import java.util.Iterator;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.text.Text;

/* Direciona a execução do programa para um ponto específico, dependendo do botão acionado pelo usuário. */
public class ControleMenu { 
	public static void testaBotoes(Group raiz) {
		
		((Button)raiz.lookup("#botao_salvartexto")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
			String texto = LeituraTexto.leituraTexto(raiz); /* Realiza a leitura do texto digitado no campo da interface. */
			if (!texto.isEmpty())  
				SalvaArquivos.salvaArquivoTexto(raiz,texto); /* Salva o texto digitado no campo em um arquivo texto de saída. */
		});
		
		((Button)raiz.lookup("#botao_carregartexto")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
			CarregaArquivos.carregaArquivoTexto(raiz);
		});
		
		((Button)raiz.lookup("#botao_salvarmidi")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
			String texto = LeituraTexto.leituraTexto(raiz);
			if (!texto.isEmpty()) 
				SalvaArquivos.salvaArquivoMidi(raiz,texto); /* Salva o som gerado pelo texto digitado em um arquivo .mid de saída. */
		});
		
		((Button)raiz.lookup("#botao_carregarmidi")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
			CarregaArquivos.carregaArquivosMidi(raiz); /* Carrega e reproduz um arquivo .mid selecionado pelo usuário. */
		});
		
		((Button)raiz.lookup("#botao_ajuda")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
			MenuAjuda.exibeAjuda(); /* Exibe um texto explicando o funcionamento do programa ao usuário. */
		});
		
		((Button)raiz.lookup("#botao_tocarmusica")).setOnAction(event -> {
			ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
			String texto = LeituraTexto.leituraTexto(raiz); /* Realiza a leitura do texto digitado no campo da interface. */
			if (!texto.isEmpty()) 
				Reprodutor.reproduzSom(raiz,texto); /* Faz o mapeamento do texto inserido na caixa de texto, e reproduz o som gerado. */
		});
	}
	
	/* Função para remover qualquer mensagem de informação quando algum botão é pressionado. */
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
