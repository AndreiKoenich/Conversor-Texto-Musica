package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;

public class Main extends Application 
{	
    public static void main(String[] args){
        launch(args);
    }
    
    public void start(Stage estagio){
    	Group raiz = MenuPrincipal.criarInterface(estagio); /* Exibe a interface principal ao usuário, para iniciar o programa. */
    	ControleMenu.testaBotoes(raiz); /* Aguarda o usuário pressionar algum botão, para ativar alguma funcionalidade. */
    } 
}