package application;
import javax.sound.midi.*;

public class Reprodutor {
	public static void reproduzSom(String texto) {
		Decodificador decodificador = new Decodificador();
		try {

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

				/*
				System.out.println("--------------------------------------------------------");
				System.out.println("NOTA MUSICAL:\n" + decodificador.getMusica().getNota());
				System.out.println("VOLUME:\n" + decodificador.getMusica().getVolume());
				System.out.println("INSTRUMENTO:\n" + decodificador.getMusica().getInstrumento());		
				System.out.println("BPM:\n" + decodificador.getMusica().getBpm());
				*/
				
				Sequencer sequenciador = MidiSystem.getSequencer();
				sequenciador.open();
				Sequence sequencia = new Sequence(Sequence.PPQ, 4);
			    Track trilha = sequencia.createTrack();
		        sequenciador.setSequence(sequencia);
		
				adicionaTrilha(decodificador.getMusica(),sequenciador,trilha);
				
				sequenciador.start();
				Thread.sleep(sequencia.getMicrosecondLength()/1000);
				sequenciador.stop();
				sequenciador.close();
			}
		}
        catch (MidiUnavailableException | InvalidMidiDataException |  InterruptedException e) {
            e.printStackTrace();
        }
	}
	public static int adicionaTrilha(Musica musica, Sequencer sequenciador, Track trilha) {
		try {
		    int tick = 0;
		    
		    sequenciador.setTempoInBPM(musica.getBpm());
		    
		    ShortMessage mensagemPrograma = new ShortMessage();
		    mensagemPrograma.setMessage(ShortMessage.PROGRAM_CHANGE, 0, musica.getInstrumento(), 0);
		    trilha.add(new MidiEvent(mensagemPrograma, tick));
		    
		    ShortMessage mensagemNotaOn = new ShortMessage();
		    mensagemNotaOn.setMessage(ShortMessage.NOTE_ON, 0, musica.getNota(), musica.getVolume());
		    MidiEvent eventoNotaOn = new MidiEvent(mensagemNotaOn, tick);
		    trilha.add(eventoNotaOn);
		    
		    tick += 16; // duração da nota
		    
		    ShortMessage mensagemNotaOff = new ShortMessage();
		    mensagemNotaOff.setMessage(ShortMessage.NOTE_OFF, 0, musica.getNota(), musica.getVolume());
		    MidiEvent eventoNotaOff = new MidiEvent(mensagemNotaOff, tick);
		    trilha.add(eventoNotaOff);
		    return tick;
		}
        catch (InvalidMidiDataException e) {
            e.printStackTrace();
            return -1;
        }
	}
}
