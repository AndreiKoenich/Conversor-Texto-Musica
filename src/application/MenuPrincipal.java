package application;
	
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MenuPrincipal {
	public static Group criarInterface(Stage estagio) {
		Group raiz = new Group();
		estagio.setTitle("Conversor de Texto para Música");
		
		criarBotoes(estagio,raiz);
		criarCampoTexto(estagio,raiz);
		
	    Scene cena = new Scene(raiz,500,400,Color.WHITE);
	    estagio.setResizable(false);
	    estagio.setScene(cena);
	    estagio.show();
	    
		return raiz;
	}
	
	public static void criarBotoes(Stage estagio, Group raiz) {
    	Button botao_salvartexto = new Button("Salvar TXT");
    	botao_salvartexto.setId("botao_salvartexto");
        raiz.getChildren().add(botao_salvartexto);
        botao_salvartexto.setPrefSize(100,30);
        botao_salvartexto.setLayoutX(370);
        botao_salvartexto.setLayoutY(30);
        
    	Button botao_carregartexto = new Button("Carregar TXT");
    	botao_carregartexto.setId("botao_carregartexto");
        raiz.getChildren().add(botao_carregartexto);
        botao_carregartexto.setPrefSize(100,30);
        botao_carregartexto.setLayoutX(370);
        botao_carregartexto.setLayoutY(80);
        
    	Button botao_salvarmidi = new Button("Salvar MIDI");
    	botao_salvarmidi.setId("botao_salvarmidi");
        raiz.getChildren().add(botao_salvarmidi);
        botao_salvarmidi.setPrefSize(100,30);
        botao_salvarmidi.setLayoutX(370);
        botao_salvarmidi.setLayoutY(130);
        
    	Button botao_carregarmidi = new Button("Carregar MIDI");
    	botao_carregarmidi.setId("botao_carregarmidi");
        raiz.getChildren().add(botao_carregarmidi);
        botao_carregarmidi.setPrefSize(100,30);
        botao_carregarmidi.setLayoutX(370);
        botao_carregarmidi.setLayoutY(180);
       
    	Button botao_ajuda = new Button("Ajuda");
    	botao_ajuda.setId("botao_ajuda");
        raiz.getChildren().add(botao_ajuda);
        botao_ajuda.setPrefSize(100,30);
        botao_ajuda.setLayoutX(370);
        botao_ajuda.setLayoutY(230);
        
    	Button botao_tocarmusica = new Button("Tocar Música");
    	botao_tocarmusica.setId("botao_tocarmusica");
        raiz.getChildren().add(botao_tocarmusica); 
        botao_tocarmusica.setPrefSize(460,30);
        botao_tocarmusica.setLayoutX(20);
        botao_tocarmusica.setLayoutY(300);
    }
    
    public static void criarCampoTexto(Stage estagio, Group raiz) {
    	TextArea campo_texto = new TextArea();
    	campo_texto.setId("campo_texto");
        campo_texto.setPromptText("Digite o texto a ser convertido...");
        campo_texto.setLayoutX(20);
        campo_texto.setLayoutY(15);
        campo_texto.setPrefColumnCount(25);
        campo_texto.setPrefRowCount(15);
        campo_texto.setWrapText(true);
    	
        raiz.getChildren().add(campo_texto);
    }
}