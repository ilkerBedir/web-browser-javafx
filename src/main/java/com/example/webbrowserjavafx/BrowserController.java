package com.example.webbrowserjavafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class BrowserController implements Initializable {
  @FXML
  private WebView webView;

  @FXML
  private TextField textField;

  private WebEngine webEngine;

  private String homePage;

  private double webZoom;

  private WebHistory history;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    webEngine = webView.getEngine();
    homePage = "www.google.com";
    textField.setText(homePage);
    loadPage();
  }

  public void loadPage() {
//    webEngine.load("https://www.google.com/");
    webEngine.load("https://" + textField.getText());
  }

  public void refreshPage() {
    webEngine.reload();
  }

  public void zoomIn() {
    webZoom += 0.25;
    webView.setZoom(webZoom);
  }

  public void zoomOut() {
    webZoom -= 0.25;
    webView.setZoom(webZoom);
  }

  public void displayHistory(){
    history=webEngine.getHistory();
    ObservableList<WebHistory.Entry> entries = history.getEntries();
    entries.forEach(entry-> System.out.println(entry));
  }

  public void back(){
    history=webEngine.getHistory();
    ObservableList<WebHistory.Entry> entries = history.getEntries();
    history.go(-1);
    textField.setText(entries.get(history.getCurrentIndex()).getUrl());
  }
  public void forward() {
    history = webEngine.getHistory();
    ObservableList<WebHistory.Entry> entries = history.getEntries();
    history.go(+1);
    textField.setText(entries.get(history.getCurrentIndex()).getUrl());
  }
  public void executeJS(){
    webEngine.executeScript("window.location=\"https://youtube.com\";");
  }
}