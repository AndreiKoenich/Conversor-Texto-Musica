package application;
import javax.sound.midi.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.Group;

public class Reprodutor {
	public static void reproduzSom(Group raiz, String texto) {
		Decodificador decodificador = new Decodificador();
		
		try {
			Sequencer sequenciador = MidiSystem.getSequencer();
			sequenciador.open();
			Sequence sequencia = new Sequence(Sequence.PPQ, 4);
		    Track trilha = sequencia.createTrack();
	        sequenciador.setSequence(sequencia);
	        decodificador.getMusica().reinicia_som();   

			for (int indice = 0; indice < texto.length(); indice++) {
				if (texto.charAt(indice) == 'B' && texto.startsWith("BPM+",indice)) {
					decodificador.getDecodificador().get("BPM+").run();
					indice+=("BPM+").length()-1;
					decodificador.getMusica().setSilencio(false);
				}
				else if (texto.charAt(indice) == 'B' && texto.startsWith("BPM-",indice)) {
					decodificador.getDecodificador().get("BPM-").run();
					indice+=("BPM-").length()-1;
					decodificador.getMusica().setSilencio(false);
				}
				else if (!(decodificador.getDecodificador().containsKey(String.valueOf(texto.charAt(indice))))) {
					if (indice > 0 && texto.charAt(indice-1) >= 'A' && texto.charAt(indice-1) <= 'G')
						decodificador.getDecodificador().get("a").run();
					else
						decodificador.getMusica().setSilencio(true);
				}
				else {
					decodificador.getMusica().setSilencio(false);
					decodificador.getDecodificador().get((String.valueOf(texto.charAt(indice)))).run();
				}

				adicionaTrilha(decodificador.getMusica(),sequenciador,trilha);
			}
			
			sequenciador.start();

			while (sequenciador.isRunning()) {
				Thread.sleep(RegrasMusica.tempo_espera);
	        }
			sequenciador.stop();
			sequenciador.close();
		}
        catch (MidiUnavailableException | InvalidMidiDataException |  InterruptedException excecao) {
            excecao.printStackTrace();
        }
		
        ControleMenu.apagaTextoInformativo(raiz);
        Text mensagemReproducaoFinalizada = new Text("A reprodução do som foi finalizada!");
        mensagemReproducaoFinalizada.setX(155);
        mensagemReproducaoFinalizada.setY(370);
        mensagemReproducaoFinalizada.setFill(Color.GREEN);
        raiz.getChildren().add(mensagemReproducaoFinalizada);	
	}
	
	public static void adicionaTrilha(Musica musica, Sequencer sequenciador, Track trilha) {
		try {
			sequenciador.setTempoInBPM(musica.getBpm());
		    
		    ShortMessage mensagemPrograma = new ShortMessage();
		    mensagemPrograma.setMessage(ShortMessage.PROGRAM_CHANGE, 0, musica.getInstrumento(), 0);
		    trilha.add(new MidiEvent(mensagemPrograma, musica.getTick()));
		    
		    ShortMessage mensagemNotaOn = new ShortMessage();
		    mensagemNotaOn.setMessage(ShortMessage.NOTE_ON, 0, musica.getNota(), musica.getVolume());
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