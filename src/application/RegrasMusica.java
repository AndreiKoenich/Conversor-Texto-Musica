package application;

public class RegrasMusica {
	public int nota_padrao = 60;
	
	public int limite_inferior_nota = 0;
	public int limite_superior_nota = 127;

	public int limite_inferior_instrumento = 0;
	public int limite_superior_instrumento = 127;

	public int unidades_mudanca_oitava = 12;
	public int unidades_mudanca_bpm = 50;
	public int limite_inferior_bpm = 10;
	public int limite_superior_bpm = 1000;

	public int incremento_sustenido = 1;
	public int decremento_bemol = 1;

	public int limite_inferior_volume = 0;
	public int limite_superior_volume = 127;

	public int volume_padrao = 93;
	public int multiplicador_volume = 2;
	public int instrumento_padrao = 0;
	public int bpm_padrao = 50;
	
	public boolean verifica_bemol(int valor_nota) {
		int[] lista_bemol = {62,64,67,69,71};
		for (int i = 0; i < lista_bemol.length; i++) {
			if (lista_bemol[i] == valor_nota)
				return true;
		}
		return false;
	}
	
	public boolean verifica_sustenido(int valor_nota) {
		int[] lista_sustenido = {60,62,65,67,69};
		for (int i = 0; i < lista_sustenido.length; i++) {
			if (lista_sustenido[i] == valor_nota)
				return true;
		}
		return false;
	}
}
