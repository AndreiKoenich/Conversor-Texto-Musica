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
    	Group raiz = MenuPrincipal.criarInterface(estagio);
    	ControleMenu.testaBotoes(raiz);
    } 
}