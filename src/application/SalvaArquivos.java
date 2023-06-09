package application;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SalvaArquivos {
	
	public static void salvaArquivoTexto(Group raiz, String texto) {
		/* Cria um FileChooser para permitir que o usuário escolha o diretório. */
		FileChooser selecionadorArquivos = new FileChooser();
		selecionadorArquivos.setTitle("Escolha o diretório para salvar o texto digitado no campo");

		/* Define o filtro de extensão para apenas arquivos .txt */
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo de texto (*.txt)", "*.txt");
		selecionadorArquivos.getExtensionFilters().add(extFilter);

		/* Abre o FileChooser e aguarda o usuário selecionar um diretório e digitar um nome de arquivo. */
		Stage estagio = new Stage();
		File arquivo = selecionadorArquivos.showSaveDialog(estagio);
		if (arquivo != null) {
		    try {
		        /* Cria um BufferedWriter e um PrintWriter para escrever no arquivo texto. */
		        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
		        PrintWriter printWriter = new PrintWriter(writer);

		        printWriter.println(texto);

		        /* Fecha o BufferedWriter e o PrintWriter. */
		        printWriter.close();
		        writer.close();
		    } catch (IOException excecao) {
		        /* Trata exceções de I/O. */
		        excecao.printStackTrace();
		    }
		}
		
		/* Imprime a mensagem na interface, para informar que a operação foi bem sucedida. */
		Text mensagemSalvouTexto = new Text("Arquivo de texto salvo com sucesso!");
        mensagemSalvouTexto.setX(160);
        mensagemSalvouTexto.setY(370);
        mensagemSalvouTexto.setFill(Color.GREEN);
        raiz.getChildren().add(mensagemSalvouTexto);
	}
	
	public static void salvaArquivoMidi(Group raiz, String texto) {
		/* Cria um FileChooser para permitir que o usuário escolha o diretório. */
		FileChooser selecionadorArquivos = new FileChooser();
		selecionadorArquivos.setTitle("Escolha o diretório para salvar o texto digitado no campo");

		/* Define o filtro de extensão para apenas arquivos .mid */
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo MIDI", "*.mid");
		selecionadorArquivos.getExtensionFilters().add(extFilter);

		/* Abre o FileChooser e aguarda o usuário selecionar um diretório e digitar um nome de arquivo. */
		Stage estagio = new Stage();
		File arquivo_saida = selecionadorArquivos.showSaveDialog(estagio);
		
		if (arquivo_saida != null) {
		    try {
		    	
		    	Decodificador decodificador = new Decodificador();
				Sequencer sequenciador = MidiSystem.getSequencer();
				sequenciador.open();
				Sequence sequencia = new Sequence(Sequence.PPQ, 4);
			    Track trilha = sequencia.createTrack();
		        sequenciador.setSequence(sequencia);
		    	
				/* Iteração para ler cada caractere presente na caixa de texto, e traduzir cada um em uma nota musical. */
		        for (int indice = 0; indice < texto.length(); indice++) {
					if (texto.charAt(indice) == 'B' && texto.startsWith("BPM+",indice)) { /* Testa a presença de uma sequência de caracteres BPM+ */
						decodificador.getDecodificador().get("BPM+").run(); /* Aumenta o BPM do som sendo reproduzido. */
						indice+=("BPM+").length()-1;
						decodificador.getMusica().setSilencio(false);
					}
					else if (texto.charAt(indice) == 'B' && texto.startsWith("BPM-",indice)) { /* Testa a presença de uma sequência de caracteres BPM- */
						decodificador.getDecodificador().get("BPM-").run(); /* Diminui o BPM do som sendo reproduzido. */
						indice+=("BPM-").length()-1;
						decodificador.getMusica().setSilencio(false);
					}
					else if (!(decodificador.getDecodificador().containsKey(String.valueOf(texto.charAt(indice))))) {
						if (indice > 0 && texto.charAt(indice-1) >= 'A' && texto.charAt(indice-1) <= 'G') /* Reproduz a nota da iteração imediatamente anterior. */
							decodificador.getDecodificador().get("a").run(); 
						else
							decodificador.getMusica().setSilencio(true);
					}
					else {
						decodificador.getMusica().setSilencio(false);
						decodificador.getDecodificador().get((String.valueOf(texto.charAt(indice)))).run();
					}

					/* Adiciona uma nova trilha com uma nova nota, ao fim de cada leitura de caractere do texto inserido. */
					Reprodutor.adicionaTrilha(decodificador.getMusica(),sequenciador,trilha); 
				}
				
				MidiSystem.write(sequencia, 0, arquivo_saida); /* Escreve a sequência MIDI no arquivo de saída. */

		    } catch (InvalidMidiDataException | MidiUnavailableException | IOException excecao) {
		        excecao.printStackTrace();
		    }
		    
	        /* Imprime a mensagem na interface, para informar que a operação foi bem sucedida. */
		    Text mensagemSalvouMidi = new Text("Sequência MIDI salva com sucesso!");
	        mensagemSalvouMidi.setX(165);
	        mensagemSalvouMidi.setY(370);
	        mensagemSalvouMidi.setFill(Color.GREEN);
	        raiz.getChildren().add(mensagemSalvouMidi);
		}
	}
}
