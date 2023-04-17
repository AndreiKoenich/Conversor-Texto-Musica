package application;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LeituraTexto {
	public static String leituraTexto(Group raiz) {
		String texto = ((TextArea) raiz.lookup("#campo_texto")).getText();
		if (texto.isEmpty()) { /* Controle dos casos em que a caixa de texto está vazia. */
            Text mensagemTextoVazio = new Text("Erro: nenhum texto foi digitado!");
            mensagemTextoVazio.setX(165);
            mensagemTextoVazio.setY(370);
            mensagemTextoVazio.setFill(Color.RED);
            raiz.getChildren().add(mensagemTextoVazio);
            return ""; /* Retorna uma string vazia, pois nenhum texto foi digitado pelo usuário. */
		}
		else
			return texto; /* Retorna o texto não-vazio digitado pelo usuário. */
	}
}
