package br.com.cinemafx.control;

import br.com.cinemafx.bean.Filme;
import br.com.cinemafx.util.MaskedTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAnchorPaneCadastrosFilmesDialogController implements Initializable {

    @FXML
    private Label labelFilmeNome;
    @FXML
    private Label labelFilmeClassificacao;
    @FXML
    private Label labelFilmeDuracao;
    @FXML
    private Label labelFilmeCartaz;
    @FXML
    private Label labelFilmeVigencia;
    @FXML
    private Label labelFilmeCategoria;
    @FXML
    private TextField textFieldFilmeNome;
    @FXML
    private MaskedTextField textFieldFilmeClassificacao;
    @FXML
    private MaskedTextField textFieldFilmeDuracao;
    @FXML
    private TextField textFieldFilmeCartaz;
    @FXML
    private MaskedTextField textFieldFilmeVigencia;
    @FXML
    private TextField textFieldFilmeCategoria;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Filme f;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Filme getFilme() {
        return f;
    }

    public void setFilme(Filme f) {
        this.f = f;
        this.textFieldFilmeNome.setText(f.getNomeFilme());
        this.textFieldFilmeClassificacao.setText(String.valueOf(f.getClassificacao()));
        this.textFieldFilmeDuracao.setText(String.valueOf(f.getDuracao()));
        this.textFieldFilmeCartaz.setText(String.valueOf(f.isCartaz()));
        this.textFieldFilmeVigencia.setText(String.valueOf(f.getVigencia()));
        this.textFieldFilmeCategoria.setText(f.getCategoria());
    }
    
    @FXML
    public void handleButtonConfirmar(){
        if(validarEntradaDeDados()){
            System.out.println(textFieldFilmeNome.getText());
            f.setNomeFilme(textFieldFilmeNome.getText());
            System.out.println(textFieldFilmeClassificacao.getText());
            f.setClassificacao(Integer.valueOf(textFieldFilmeClassificacao.getText()));
            f.setDuracao(Float.valueOf(textFieldFilmeDuracao.getText()));
            f.setCartaz(Boolean.valueOf(textFieldFilmeCartaz.getText()));
            f.setVigencia(Date.valueOf(textFieldFilmeVigencia.getText()));
            f.setCategoria(textFieldFilmeCategoria.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
    private boolean validarEntradaDeDados(){
        String errorMessage = "";
        
        if(textFieldFilmeNome.getText() == null || textFieldFilmeNome.getText().length() == 0){
            errorMessage += "Nome invalido!\n";
        }
        if(textFieldFilmeClassificacao.getText() == null || textFieldFilmeClassificacao.getText().length() == 0){
            errorMessage += "Classificacao invalida!\n";
        }
        if(textFieldFilmeDuracao.getText() == null || textFieldFilmeDuracao.getText().length() == 0){
            errorMessage += "Duracao invalida!\n";
        }
        if(textFieldFilmeCartaz.getText() == null || textFieldFilmeCartaz.getText().length() == 0){
            errorMessage += "Cartaz sem resposta!\n";
        }
        if(textFieldFilmeVigencia.getText() == null || textFieldFilmeVigencia.getText().length() == 0){
            errorMessage += "Vigencia invalida!\n";
        }
        if(textFieldFilmeCategoria.getText() == null || textFieldFilmeCategoria.getText().length() == 0){
            errorMessage += "Categoria invalida!\n";
        }
        
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos invalidos, por favor corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
}
