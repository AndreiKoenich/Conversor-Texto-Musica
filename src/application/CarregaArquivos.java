package application;

import java.io.File;

import javafx.scene.Group;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javax.sound.midi.*;

public class CarregaArquivos {

    public static void carregaArquivoTexto(Group raiz) {
        /* Cria o seletor de arquivos. */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione o arquivo .txt a ser lido");

        /* Mostra o seletor de arquivos e aguarda a seleção do usuário. */
        Stage stage = new Stage();
        File arquivoSelecionado = fileChooser.showOpenDialog(stage);
        
        if (arquivoSelecionado != null) {
            /* Verifica se o arquivo selecionado é um arquivo de texto. */
            if (arquivoSelecionado.getName().endsWith(".txt")) {
                try {
                    /* Lê o conteúdo do arquivo selecionado. */
                    String conteudo = Files.readString(arquivoSelecionado.toPath(), StandardCharsets.UTF_8);
                    
                    /* Imprime o conteúdo do arquivo na saída padrão. */
                    ((TextArea)raiz.lookup("#campo_texto")).setText(conteudo);
                    
                    Text mensagem_carregouTXT = new Text("Arquivo texto carregado com sucesso!");
                    mensagem_carregouTXT.setX(150);
                    mensagem_carregouTXT.setY(370);
                    mensagem_carregouTXT.setFill(Color.GREEN);
                    raiz.getChildren().add(mensagem_carregouTXT);                        
                    
                } catch (IOException excecao) {
                    excecao.printStackTrace();
                }
                
            } else {
                /* Se o arquivo selecionado não for um arquivo de texto, emite uma mensagem de erro. */
                Text mensagem_erroTXT = new Text("Erro: o arquivo selecionado não possui formato de texto!");
                mensagem_erroTXT.setX(100);
                mensagem_erroTXT.setY(370);
                mensagem_erroTXT.setFill(Color.RED);
                raiz.getChildren().add(mensagem_erroTXT);
            }
        }
    }
    
    public static void carregaArquivosMidi(Group raiz) {
        /* Cria o seletor de arquivos. */
        FileChooser selecionadorArquivos = new FileChooser();
        selecionadorArquivos.setTitle("Selecione a sequência MIDI a ser reproduzida");

        /* Mostra o seletor de arquivos e aguarda a seleção do usuário. */
        Stage estagio = new Stage();
        File arquivoSelecionado = selecionadorArquivos.showOpenDialog(estagio);
        
        if (arquivoSelecionado != null) {
            /* Verifica se o arquivo selecionado é um arquivo de sequência MIDI. */
            if (arquivoSelecionado.getName().endsWith(".mid")) {
                try {

                	Sequence sequencia = MidiSystem.getSequence(arquivoSelecionado);
                    
                    /* Obtém um sequenciador e abre ele. */
                    Sequencer sequenciador = MidiSystem.getSequencer();
                    sequenciador.open();

                    /* Configura o sequenciador com a sequência MIDI. */
                    sequenciador.setSequence(sequencia);

                    /* Inicia a reprodução do sequenciador. */
                    sequenciador.start();

                    /* Espera a reprodução terminar ou interrompe manualmente. */
                    while (sequenciador.isRunning()) {
                        Thread.sleep(1000);
                    }

                    sequenciador.stop();
                    sequenciador.close();
                    
                    Text mensagem_tocouMIDI = new Text("Sequência MIDI reproduzida com sucesso!");
                    mensagem_tocouMIDI.setX(150);
                    mensagem_tocouMIDI.setY(370);
                    mensagem_tocouMIDI.setFill(Color.GREEN);
                    raiz.getChildren().add(mensagem_tocouMIDI);
                }
                    
                catch (MidiUnavailableException | InvalidMidiDataException |  InterruptedException | IOException excecao) {
                	excecao.printStackTrace();
                }
                
            } else {
                /* Se o arquivo selecionado não for um arquivo de sequência MIDI, emite uma mensagem de erro. */
                Text mensagem_erroTXT = new Text("Erro: o arquivo selecionado não possui formato .mid!");
                mensagem_erroTXT.setX(100);
                mensagem_erroTXT.setY(370);
                mensagem_erroTXT.setFill(Color.RED);
                raiz.getChildren().add(mensagem_erroTXT);
            }
        }
    }
}
