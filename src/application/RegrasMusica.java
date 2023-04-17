package application;

/* Classe contendo as informações e os limites padrão para a música a ser reproduzida. */
public class RegrasMusica { 
	public static int nota_padrao = 60;
	
	public static int limite_inferior_nota = 0;
	public static int limite_superior_nota = 127;

	public static int limite_inferior_instrumento = 0;
	public static int limite_superior_instrumento = 127;

	public static int unidades_mudanca_oitava = 12;
	public static int unidades_mudanca_bpm = 50;
	public static int limite_inferior_bpm = 10;
	public static int limite_superior_bpm = 1000;

	public static int incremento_sustenido = 1;
	public static int decremento_bemol = 1;

	public static int limite_inferior_volume = 0;
	public static int limite_superior_volume = 127;

	public static int volume_padrao = 70;
	public static int multiplicador_volume = 2;
	public static int instrumento_padrao = 0;
	public static int bpm_padrao = 50;
	
	public static int tick_duracao = 8;
	public static int tempo_espera = 1000;
	
	
	/* Método para verificar se a nota atualmente sendo reproduzida pode ser convertida em bemol. */
	public static boolean verifica_bemol(int valor_nota) {
		int[] lista_bemol = {62,64,67,69,71}; /* Somente as notas ré, mi, sol, lá e si podem ser convertidas em bemol. */
		for (int i = 0; i < lista_bemol.length; i++) {
			if (lista_bemol[i] == valor_nota)
				return true;
		}
		return false;
	}
	
	/* Método para verificar se a nota atualmente sendo reproduzida pode ser convertida em sustenida. */
	public static boolean verifica_sustenido(int valor_nota) {
		int[] lista_sustenido = {60,62,65,67,69}; /* Somente as notas dó, ré, fá, sol, e lá podem ser convertidas em bemol. */
		for (int i = 0; i < lista_sustenido.length; i++) {
			if (lista_sustenido[i] == valor_nota)
				return true;
		}
		return false;
	}
}
