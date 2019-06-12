package br.com.cinemafx.control;

import br.com.cinemafx.bean.Filme;
import br.com.cinemafx.dao.FilmeDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLAnchorPaneCadastrosFilmesController implements Initializable {

    @FXML
    private TableView<Filme> tableViewFilmes;
    @FXML
    private TableColumn<Filme, String> tableColumnFilmeNome;
    @FXML
    private TableColumn<Filme, String> tableColumnFilmeCategoria;
    @FXML
    private Button buttonFilmeInserir;
    @FXML
    private Button buttonFilmeAlterar;
    @FXML
    private Button buttonFilmeRemover;
    @FXML
    private Label labelFilmeCodigo;
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
    
    private List<Filme> listFilmes;
    private ObservableList<Filme> observableListFilmes;
    
    private final FilmeDAO filmeDAO = new FilmeDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewFilme();
        
        //Listen acionado diante de quaisquer alteracoes na selecao de itens do TableView
        tableViewFilmes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewFilmes(newValue));
    }    
    
    public void carregarTableViewFilme(){
        tableColumnFilmeNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnFilmeCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        
        listFilmes = filmeDAO.select();
        
        observableListFilmes = FXCollections.observableArrayList(listFilmes);
        tableViewFilmes.setItems(observableListFilmes);
    }
    
    public void selecionarItemTableViewFilmes(Filme f){
        if(f != null){
            labelFilmeCodigo.setText(String.valueOf(f.getIdFilme()));
            labelFilmeNome.setText(f.getNomeFilme());
            labelFilmeClassificacao.setText(String.valueOf(f.getClassificacao()));
            labelFilmeDuracao.setText(String.valueOf(f.getDuracao()));
            labelFilmeCartaz.setText(String.valueOf(f.isCartaz()));
            labelFilmeVigencia.setText(String.valueOf(f.getVigencia()));
            labelFilmeCategoria.setText(f.getCategoria());
        }else{
            labelFilmeCodigo.setText("");
            labelFilmeNome.setText("");
            labelFilmeClassificacao.setText("");
            labelFilmeDuracao.setText("");
            labelFilmeCartaz.setText("");
            labelFilmeVigencia.setText("");
            labelFilmeCategoria.setText("");
        }
    }
    
}
