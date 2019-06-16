package br.com.cinemafx.control;

import br.com.cinemafx.bean.Sala;
import br.com.cinemafx.util.MaskedTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAnchorPaneCadastrosSalasDialogController implements Initializable {

    @FXML
    private TextField textFieldSalasTipoImagem;
    @FXML
    private MaskedTextField textFieldSalasLotacao;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Sala s;
    
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

    public Sala getSala() {
        return s;
    }

    public void setSala(Sala s) {
        this.s = s;
        this.textFieldSalasTipoImagem.setText(s.getTipoImagem());
        this.textFieldSalasLotacao.setText(String.valueOf(s.getQtdMax()));
    }
    
    @FXML
    public void handleButtonConfirmar(){
        if(validarEntradaDeDados()){
            s.setTipoImagem(textFieldSalasTipoImagem.getText());
            s.setQtdMax(Integer.valueOf(textFieldSalasLotacao.getText()));

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
        
        if(textFieldSalasTipoImagem.getText() == null || textFieldSalasTipoImagem.getText().length() == 0){
            errorMessage += "Tipo de imagem invalido!\n";
        }
        if(textFieldSalasLotacao.getText() == null || textFieldSalasLotacao.getText().length() == 0){
            errorMessage += "Quantidade invalida!\n";
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
