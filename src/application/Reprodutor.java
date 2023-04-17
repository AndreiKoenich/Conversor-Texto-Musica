package application;
import javax.sound.midi.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.Group;

public class Reprodutor {
	public static void reproduzSom(Group raiz, String texto) {
		Decodificador decodificador = new Decodificador();
		
		try {			
			/* Inicializa o sequenciador, a sequencia e a trilha a serem usadas na reprodução do som. */
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
				adicionaTrilha(decodificador.getMusica(),sequenciador,trilha); 
			}
			
			sequenciador.start(); /* Inicia a reprodução do som. */

			while (sequenciador.isRunning()) { /* Aguarda a reprodução do som ser finalizada. */
				Thread.sleep(RegrasMusica.tempo_espera);
	        }
			sequenciador.stop(); /* Encerra a reprodução do som. */
			sequenciador.close(); /* Fecha o sequenciador utilizado para reproduzir o som. */
		}
        catch (MidiUnavailableException | InvalidMidiDataException |  InterruptedException excecao) {
            excecao.printStackTrace();
        }
		
		ControleMenu.apagaTextoInformativo(raiz); /* Apaga qualquer mensagem de informação que existir na interface. */
        Text mensagemReproducaoFinalizada = new Text("A reprodução do som foi finalizada!");
        mensagemReproducaoFinalizada.setX(155);
        mensagemReproducaoFinalizada.setY(370);
        mensagemReproducaoFinalizada.setFill(Color.GREEN);
        raiz.getChildren().add(mensagemReproducaoFinalizada);	
	}
	
	/* Método para adicionar notas musicais na trilha, para serem reproduzidas. */
	public static void adicionaTrilha(Musica musica, Sequencer sequenciador, Track trilha) {
		try {
			sequenciador.setTempoInBPM(musica.getBpm()); /* Ajusta o BPM do som a ser reproduzido. */
		    
		    ShortMessage mensagemPrograma = new ShortMessage();
		    mensagemPrograma.setMessage(ShortMessage.PROGRAM_CHANGE, 0, musica.getInstrumento(), 0); /* Ajusta o instrumento do som a ser reproduzido. */
		    trilha.add(new MidiEvent(mensagemPrograma, musica.getTick()));
		    
		    ShortMessage mensagemNotaOn = new ShortMessage();
		    mensagemNotaOn.setMessage(ShortMessage.NOTE_ON, 0, musica.getNota(), musica.getVolume()); /* Ajusta o volume do som a ser reproduzido. */
		    MidiEvent eventoNotaOn = new MidiEvent(mensagemNotaOn, musica.getTick());
		    trilha.add(eventoNotaOn);
		    
		    musica.setTick(musica.getTick()+RegrasMusica.tick_duracao); /* Estabelece a duração da nota. */
		    
		    ShortMessage mensagemNotaOff = new ShortMessage();
		    mensagemNotaOff.setMessage(ShortMessage.NOTE_OFF, 0, musica.getNota(), musica.getVolume());
		    MidiEvent eventoNotaOff = new MidiEvent(mensagemNotaOff, musica.getTick());
		    trilha.add(eventoNotaOff);
		}
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
	}
}