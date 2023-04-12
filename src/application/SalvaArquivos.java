package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SalvaArquivos {
	
	public static void salvaArquivoTexto(String texto) {
		/* Cria um FileChooser para permitir que o usuário escolha o diretório. */
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Escolha o diretório para salvar o texto digitado no campo");

		/* Define o filtro de extensão para apenas arquivos .txt */
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo de texto (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		/* Abre o FileChooser e aguarda o usuário selecionar um diretório e digitar um nome de arquivo. */
		Stage stage = new Stage();
		File arquivo = fileChooser.showSaveDialog(stage);
		if (arquivo != null) {
		    try {
		        /* Cria um BufferedWriter e um PrintWriter para escrever no arquivo texto. */
		        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));
		        PrintWriter printWriter = new PrintWriter(writer);

		        printWriter.println(texto);

		        /* Fecha o BufferedWriter e o PrintWriter. */
		        printWriter.close();
		        writer.close();
		    } catch (IOException ex) {
		        /* Trata exceções de I/O. */
		        ex.printStackTrace();
		    }
		}
	}
	
	
}
