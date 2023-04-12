package application;

import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class LeituraTexto {
	public static void leituraTexto(Group raiz) {
		
		String texto = ((TextArea) raiz.lookup("#campo_texto")).getText();
		
		if (texto.isEmpty()) {
            Text mensagemTextoVazio = new Text("Erro: nenhum texto foi digitado!");
            mensagemTextoVazio.setX(165);
            mensagemTextoVazio.setY(370);
            mensagemTextoVazio.setFill(Color.RED);
            raiz.getChildren().add(mensagemTextoVazio);
		}
		
		else {
			Decodificador decodificador = new Decodificador();
			decodificador.getMusica().reinicia_som();
			
			for (int i = 0; i < texto.length(); i++) {
				if (texto.charAt(i) == 'B' && texto.startsWith("BPM+",i)) {
					decodificador.getDecodificador().get("BPM+").run();
					i+=("BPM+").length()-1;
					decodificador.getMusica().setSilencio(false);
				}
				else if (texto.charAt(i) == 'B' && texto.startsWith("BPM-",i)) {
					decodificador.getDecodificador().get("BPM-").run();
					i+=("BPM-").length()-1;
					decodificador.getMusica().setSilencio(false);
				}
				else if (!(decodificador.getDecodificador().containsKey(String.valueOf(texto.charAt(i))))) {
					if (i > 0 && texto.charAt(i-1) >= 'A' && texto.charAt(i-1) <= 'G')
						decodificador.getDecodificador().get("a").run();
					else
						decodificador.getMusica().setSilencio(true);
				}
				else {
					decodificador.getMusica().setSilencio(false);
					decodificador.getDecodificador().get((String.valueOf(texto.charAt(i)))).run();
				}
			}
		
			System.out.println("--------------------------------------------------------");
			System.out.println("NOTA MUSICAL:\n" + decodificador.getMusica().getNota());
			System.out.println("VOLUME:\n" + decodificador.getMusica().getVolume());
			System.out.println("INSTRUMENTO:\n" + decodificador.getMusica().getInstrumento());		
			System.out.println("BPM:\n" + decodificador.getMusica().getBpm());
			
			
		}
	}
}
