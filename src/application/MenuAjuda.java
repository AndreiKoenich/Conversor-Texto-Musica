package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/* Exibe as informações sobre o funcionamento do programa, para o usuário. */
public class MenuAjuda {
    public static void exibeAjuda() {
        Stage janelaAjuda = new Stage();

        /* Impede que o usuário interaja com a janela principal enquanto a janela de ajuda estiver aberta. */
        janelaAjuda.initModality(Modality.APPLICATION_MODAL);

        /* Define a string contendo o texto de ajuda completo. */
        String texto_ajuda = "Conversor de Texto para Música - Autor: Andrei Pochmann Koenich\n\n"
        		+ "Esse programa converte um texto em um conjunto de notas musicais, segundo um mapeamento pré-definido.\n\n"
        		+ "Para usar o programa, você pode digitar o texto na caixa de texto ou carregar um arquivo contendo o texto a ser convertido.\n\n"
        		+ "Para tocar a música gerada, clique no botão 'Tocar Música'.\n\n"
        		+ "Também é possível carregar um arquivo MIDI já existente, cujo som será reproduzido imediatamente após ser carregado.\n\n"
        		+ "Você também pode exportar um arquivo MIDI com o som criado a partir do texto recebido.\n\n"
        		+ "Abaixo, segue a descrição completa sobre como ocorre o mapeamento entre texto e música.\n\n"
                + "Letras H a M: Nota vira bemol | Letras N a S: Nota não é alterada | Letras T a Z: Nota vira sustenida | "
                + "Letras abcdef: Repete nota anterior | Dígito 1 a 9: Troca instrumento | Dígito 0: Volta para instrumento padrão | "
                + "+: Dobra volume | -: Volume volta a ser padrão | !: Sobe oitava | ?: Desce oitava | .: Reseta oitava | "
                + "Letra não precedida de nota: Pausa | ;: Flauta | ,: Órgão | Espaço: Piano | Nova Linha: Bells | BPM+: BPM+50 | "
                + "BPM-: BPM-50 | else: Repete nota anterior ou pausa\n\n"
                + "Para mais detalhes, visite: https://github.com/AndreiKoenich/Conversor-Texto-Musica";

        Text mensagem = new Text(texto_ajuda); /* Inicializa o objeto referente à mensagem a ser impressa na tela. */
        mensagem.setWrappingWidth(400);

        Button botaoFechar = new Button("Fechar"); /* Cria o botão para fechar a janela de ajuda. */
        botaoFechar.setOnAction(event -> janelaAjuda.close());

        VBox layoutAjuda = new VBox(20); /* Definição dos aspectos do layout da janela de ajuda, e da formatação do texto. */
        layoutAjuda.getChildren().addAll(mensagem, botaoFechar);
        layoutAjuda.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene cenaAjuda = new Scene(layoutAjuda, 500, 550); /* Definição da janela de ajuda em si. */
        janelaAjuda.setScene(cenaAjuda);
        janelaAjuda.setTitle("Ajuda");
        janelaAjuda.showAndWait();
    }
}
