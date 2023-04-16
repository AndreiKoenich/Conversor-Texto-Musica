package application;

public class Musica {
	
	private int nota_original = RegrasMusica.nota_padrao;
	private int nota_atual = RegrasMusica.nota_padrao;
	private int instrumento = RegrasMusica.instrumento_padrao;
	private int volume = RegrasMusica.volume_padrao;
	private int bpm = RegrasMusica.bpm_padrao;
	private boolean silencio = false;
	private int tick = 0;
	
	public int getTick() {
		return this.tick;
	}
	
	public void setTick(int tick) {
		this.tick = tick;
	}

	public int getNota() {
		return nota_atual; 
	}
	public void setNota(int nota) {
		this.nota_original = nota;
		this.nota_atual = nota;
	}
	public int getInstrumento() {
		return instrumento;
	}
	public void setInstrumentoPadrao() {
		this.instrumento = RegrasMusica.instrumento_padrao;
	}
	public void setInstrumentoIncremento(int valor_incremento) {
		this.instrumento += valor_incremento;
		controla_limites_instrumento();
	}
	public void setInstrumentoEspecifico(int numero_instrumento) {
		this.instrumento = numero_instrumento;
	}
	public int getVolume() {
		if (this.silencio == true)
			return 0;
		else
			return this.volume;
	}
	public void reiniciaVolume() {
		this.volume = RegrasMusica.volume_padrao;
	}	
	public void multiplicaVolume() {
		this.volume *= RegrasMusica.multiplicador_volume;
		controla_limites_volume();
	}	
	public int getBpm() {
		return bpm;
	}
	public void incrementaBpm() {
		this.bpm += RegrasMusica.unidades_mudanca_bpm;
		controla_limites_bpm();
	}
	public void decrementaBpm() {
		this.bpm -= RegrasMusica.unidades_mudanca_bpm;
	}
	public boolean getSilencio() {
		return silencio;
	}
	public void setSilencio(boolean silencio) {
		this.silencio = silencio;
	}
	public void incrementaOitava() {
		this.nota_atual += RegrasMusica.unidades_mudanca_oitava;
		controla_limites_nota();
	}
	public void decrementaOitava() {
		this.nota_atual -= RegrasMusica.unidades_mudanca_oitava;
		controla_limites_nota();
	}
	public void reiniciaOitava() {
		this.nota_atual = this.nota_original;
	}
	public void converte_sustenida() {
		if (RegrasMusica.verifica_sustenido(nota_original)) {
			this.nota_atual = this.nota_original+RegrasMusica.incremento_sustenido;
		} 
	}
	public void converte_bemol() {
		if (RegrasMusica.verifica_bemol(nota_original)) {
		this.nota_atual = this.nota_original-RegrasMusica.decremento_bemol;
		}
	}
	public void controla_limites_nota() {
		if (this.nota_atual > RegrasMusica.limite_superior_nota) {this.nota_atual = RegrasMusica.limite_superior_nota;}
		else if (this.nota_atual < RegrasMusica.limite_inferior_nota) {this.nota_atual = RegrasMusica.limite_inferior_nota;}
	}
	public void controla_limites_instrumento() {
		if (this.nota_atual > RegrasMusica.limite_superior_instrumento) {this.nota_atual = RegrasMusica.limite_superior_instrumento;}
		else if (this.nota_atual < RegrasMusica.limite_inferior_instrumento) {this.nota_atual = RegrasMusica.limite_inferior_instrumento;}
	}
	public void controla_limites_bpm() {
		if (this.bpm > RegrasMusica.limite_superior_bpm) {this.bpm = RegrasMusica.limite_superior_bpm;}
		else if (this.bpm < RegrasMusica.limite_inferior_bpm) {this.bpm = RegrasMusica.limite_inferior_bpm;}
	}
	public void controla_limites_volume() {
		if (this.volume > RegrasMusica.limite_superior_volume) {this.volume = RegrasMusica.limite_superior_volume;}
		else if (this.volume < RegrasMusica.limite_inferior_volume) {this.volume = RegrasMusica.limite_inferior_volume;}
	}
	public void reinicia_som() {
		this.nota_original = RegrasMusica.nota_padrao;
		this.nota_atual = RegrasMusica.nota_padrao;
		this.instrumento = RegrasMusica.instrumento_padrao;
		this.volume = RegrasMusica.volume_padrao;
		this.bpm = RegrasMusica.bpm_padrao;
		this.silencio = false;
		this.tick = 0;		
	}
}
