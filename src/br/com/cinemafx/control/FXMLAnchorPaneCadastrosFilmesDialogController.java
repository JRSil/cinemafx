package br.com.cinemafx.control;

import br.com.cinemafx.bean.Filme;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField textFieldFilmeClassificacao;
    @FXML
    private TextField textFieldFilmeDuracao;
    @FXML
    private TextField textFieldFilmeCartaz;
    @FXML
    private TextField textFieldFilmeVigencia;
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
    }
    
    @FXML
    public void handleButtonConfirmar(){
        f.setNomeFilme(textFieldFilmeNome.getText());
        f.setClassificacao(Integer.valueOf(textFieldFilmeClassificacao.getText()));
        f.setDuracao(Float.valueOf(textFieldFilmeDuracao.getText()));
        f.setCartaz(Boolean.valueOf(textFieldFilmeCartaz.getText()));
        f.setVigencia(Date.valueOf(textFieldFilmeVigencia.getText()));
        f.setCategoria(textFieldFilmeCategoria.getText());
        
        buttonConfirmarClicked = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar(){
        dialogStage.close();
    }
    
}
