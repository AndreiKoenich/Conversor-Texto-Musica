package application;

import java.util.HashMap;


public class Decodificador {
	
	private static Musica musica = new Musica();
	
	private static final HashMap<String, Runnable> decodificador = new HashMap<>();
    static {
    	/* Mapeamentos de notas musicais. */
        decodificador.put("A", () -> musica.setNota(69)); // Lá
        decodificador.put("B", () -> musica.setNota(71)); // Si
        decodificador.put("C", () -> musica.setNota(60)); // Dó
        decodificador.put("D", () -> musica.setNota(62)); // Ré
        decodificador.put("E", () -> musica.setNota(64)); // Mi
        decodificador.put("F", () -> musica.setNota(65)); // Fá
        decodificador.put("G", () -> musica.setNota(67)); // Sol
  
        /* Mapeamentos para converter uma nota para bemol. */ 
        decodificador.put("H", () -> musica.converte_bemol());
        decodificador.put("I", () -> musica.converte_bemol());
        decodificador.put("J", () -> musica.converte_bemol()); 
        decodificador.put("K", () -> musica.converte_bemol());
        decodificador.put("L", () -> musica.converte_bemol());
        decodificador.put("M", () -> musica.converte_bemol());
        decodificador.put("h", () -> musica.converte_bemol());
        decodificador.put("i", () -> musica.converte_bemol());
        decodificador.put("j", () -> musica.converte_bemol());
        decodificador.put("k", () -> musica.converte_bemol());
        decodificador.put("l", () -> musica.converte_bemol());
        decodificador.put("m", () -> musica.converte_bemol());
        
        /* Mapeamentos para converter uma nota para sustenida. */
        decodificador.put("T", () -> musica.converte_sustenida());
        decodificador.put("U", () -> musica.converte_sustenida());
        decodificador.put("V", () -> musica.converte_sustenida());
        decodificador.put("W", () -> musica.converte_sustenida());
        decodificador.put("X", () -> musica.converte_sustenida());
        decodificador.put("Y", () -> musica.converte_sustenida());
        decodificador.put("Z", () -> musica.converte_sustenida());
        decodificador.put("t", () -> musica.converte_sustenida());
        decodificador.put("u", () -> musica.converte_sustenida());
        decodificador.put("v", () -> musica.converte_sustenida());
        decodificador.put("w", () -> musica.converte_sustenida());
        decodificador.put("x", () -> musica.converte_sustenida());
        decodificador.put("y", () -> musica.converte_sustenida());
        decodificador.put("z", () -> musica.converte_sustenida());
        
        /* Mapeamentos para manter a nota inalterada. */
        decodificador.put("a", () -> musica.setNota(musica.getNota()));
        decodificador.put("b", () -> musica.setNota(musica.getNota()));
        decodificador.put("c", () -> musica.setNota(musica.getNota()));
        decodificador.put("d", () -> musica.setNota(musica.getNota()));
        decodificador.put("e", () -> musica.setNota(musica.getNota()));
        decodificador.put("f", () -> musica.setNota(musica.getNota()));
        decodificador.put("g", () -> musica.setNota(musica.getNota()));
        decodificador.put("N", () -> musica.setNota(musica.getNota()));
        decodificador.put("O", () -> musica.setNota(musica.getNota()));
        decodificador.put("P", () -> musica.setNota(musica.getNota()));
        decodificador.put("Q", () -> musica.setNota(musica.getNota()));
        decodificador.put("R", () -> musica.setNota(musica.getNota()));
        decodificador.put("S", () -> musica.setNota(musica.getNota()));
        decodificador.put("n", () -> musica.setNota(musica.getNota()));
        decodificador.put("o", () -> musica.setNota(musica.getNota()));
        decodificador.put("p", () -> musica.setNota(musica.getNota()));
        decodificador.put("q", () -> musica.setNota(musica.getNota()));
        decodificador.put("r", () -> musica.setNota(musica.getNota()));
        decodificador.put("s", () -> musica.setNota(musica.getNota()));
        
        /* Mapeamento para obter novamente o instrumento padrão. */
        decodificador.put(String.valueOf("0"), () -> musica.setInstrumentoPadrao());    
  
        /* Mapeamentos para mudanças de instrumentos. */
        decodificador.put("1", () -> musica.setInstrumentoIncremento(1));
        decodificador.put("2", () -> musica.setInstrumentoIncremento(2));
        decodificador.put("3", () -> musica.setInstrumentoIncremento(3));
        decodificador.put("4", () -> musica.setInstrumentoIncremento(4));
        decodificador.put("5", () -> musica.setInstrumentoIncremento(5));
        decodificador.put("6", () -> musica.setInstrumentoIncremento(6));
        decodificador.put("7", () -> musica.setInstrumentoIncremento(7));
        decodificador.put("8", () -> musica.setInstrumentoIncremento(8));
        decodificador.put("9", () -> musica.setInstrumentoIncremento(9));

        /* Mapeamentos para mudança de volume. */
        decodificador.put("+", () -> musica.multiplicaVolume());    
        decodificador.put("-", () -> musica.reiniciaVolume());   
        
        /* Mapeamentos para mudanças de oitava. */
        decodificador.put("!", () -> musica.incrementaOitava());    
        decodificador.put("?", () -> musica.decrementaOitava());     
        decodificador.put(".", () -> musica.reiniciaOitava());
        
        /* Mapeamentos para mudanças de oitava. */
        decodificador.put(";", () -> musica.setInstrumentoEspecifico(76));    
        decodificador.put(",", () -> musica.setInstrumentoEspecifico(19));    
        decodificador.put(" ", () -> musica.setInstrumentoEspecifico(1));    
        decodificador.put("\n", () -> musica.setInstrumentoEspecifico(15));    
        
        /* Mapeamentos para mudanças de BPM. */
        decodificador.put("BPM+", () -> musica.incrementaBpm());    
        decodificador.put("BPM-", () -> musica.decrementaBpm());     
    }
    
    public HashMap<String, Runnable> getDecodificador() {
        return decodificador;
    }
    
    public Musica getMusica() {
        return musica;
    }
}
