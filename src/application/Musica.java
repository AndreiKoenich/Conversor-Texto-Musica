package application;

public class Musica {
	
	RegrasMusica regras = new RegrasMusica();

	private int nota_original = regras.nota_padrao;
	private int nota_atual = regras.nota_padrao;
	private int instrumento = regras.instrumento_padrao;
	private int volume = regras.volume_padrao;
	private int bpm = regras.bpm_padrao;
	private boolean silencio = false;

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
		this.instrumento = regras.instrumento_padrao;
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
		this.volume = regras.volume_padrao;
	}	
	public void multiplicaVolume() {
		this.volume *= regras.multiplicador_volume;
		controla_limites_volume();
	}	
	public int getBpm() {
		return bpm;
	}
	public void incrementaBpm() {
		this.bpm += regras.unidades_mudanca_bpm;
		controla_limites_bpm();
	}
	public void decrementaBpm() {
		this.bpm -= regras.unidades_mudanca_bpm;
	}
	public boolean getSilencio() {
		return silencio;
	}
	public void setSilencio(boolean silencio) {
		this.silencio = silencio;
	}
	public void incrementaOitava() {
		this.nota_atual += regras.unidades_mudanca_oitava;
		controla_limites_nota();
	}
	public void decrementaOitava() {
		this.nota_atual -= regras.unidades_mudanca_oitava;
		controla_limites_nota();
	}
	public void reiniciaOitava() {
		this.nota_atual = this.nota_original;
	}
	public void converte_sustenida() {
		if (regras.verifica_sustenido(nota_original)) {
			this.nota_atual = this.nota_original+regras.incremento_sustenido;
		} 
	}
	public void converte_bemol() {
		if (regras.verifica_bemol(nota_original)) {
		this.nota_atual = this.nota_original-regras.decremento_bemol;
		}
	}
	public void controla_limites_nota() {
		if (this.nota_atual > regras.limite_superior_nota) {this.nota_atual = regras.limite_superior_nota;}
		else if (this.nota_atual < regras.limite_inferior_nota) {this.nota_atual = regras.limite_inferior_nota;}
	}
	public void controla_limites_instrumento() {
		if (this.nota_atual > regras.limite_superior_instrumento) {this.nota_atual = regras.limite_superior_instrumento;}
		else if (this.nota_atual < regras.limite_inferior_instrumento) {this.nota_atual = regras.limite_inferior_instrumento;}
	}
	public void controla_limites_bpm() {
		if (this.bpm > regras.limite_superior_bpm) {this.bpm = regras.limite_superior_bpm;}
		else if (this.bpm < regras.limite_inferior_bpm) {this.bpm = regras.limite_inferior_bpm;}
	}
	public void controla_limites_volume() {
		if (this.nota_atual > regras.limite_superior_volume) {this.nota_atual = regras.limite_superior_volume;}
		else if (this.nota_atual < regras.limite_inferior_volume) {this.nota_atual = regras.limite_inferior_volume;}
	}
	public void reinicia_som() {
		this.nota_original = regras.nota_padrao;
		this.nota_atual = regras.nota_padrao;
		this.instrumento = regras.instrumento_padrao;
		this.volume = regras.volume_padrao;
		this.bpm = regras.bpm_padrao;
		this.silencio = false;			
	}
}
