package application;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LeituraTexto {
	public static String leituraTexto(Group raiz) {
		String texto = ((TextArea) raiz.lookup("#campo_texto")).getText();
		if (texto.isEmpty()) {
            Text mensagemTextoVazio = new Text("Erro: nenhum texto foi digitado!");
            mensagemTextoVazio.setX(165);
            mensagemTextoVazio.setY(370);
            mensagemTextoVazio.setFill(Color.RED);
            raiz.getChildren().add(mensagemTextoVazio);
            return "";
		}
		else
			return texto;
	}
}
