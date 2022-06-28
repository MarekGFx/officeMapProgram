package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Person;

public class MainWindowController {

	private Main main;
	private ObservableList<Person> personList =
			FXCollections.observableArrayList();
	
	@FXML private Button read;
	@FXML private Button save;
	@FXML private Button add;
	@FXML private Button report;
	
	@FXML private TableView<Person>  tableView;
	@FXML private TableColumn<Person, String> firstNameColumn;
	@FXML private TableColumn<Person, String> lastNameColumn;
	@FXML private TableColumn<Person, Integer> roomNumberColumn;
	
	@FXML private TextField firstNameText;
	@FXML private TextField lastNameText;
	@FXML private TextField roomNumberText;
	@FXML private TextField startHourText;
	@FXML private TextField endHourText;
	
	@FXML private ImageView imageView;
	
	private void setList() {

		Scanner in = null;
		int roomNumber;
		String firstName;
		String lastName;
		int startHour;
		int endHour;
	
		try {
			in = new Scanner(Paths.get("lista.txt"));
			
			while(in.hasNext()) {
				firstName=in.next();
				lastName=in.next();
				roomNumber=in.nextInt();
				startHour= in.nextInt();
				endHour=in.nextInt();
				personList.add(new Person(roomNumber, firstName, lastName,startHour,endHour));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			if(in!=null)
				in.close();
		}
	}
	
	private void saveToFileList() {
		PrintWriter out = null;
		try {
			out = new PrintWriter("lista.txt");
			for(Person person: personList) {
				out.printf("%s %s %d %d %d\n",
				person.getFirstName(), person.getLastName(),
				person.getRoomNumber(), person.getStartHour(), person.getEndHour());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null)
				out.close();
		}
	}
	private void addPersonToList() {
		try {
			if (Integer.parseInt(roomNumberText.getText()) >= 120 && Integer.parseInt(roomNumberText.getText()) <= 131 
					&& Integer.parseInt(startHourText.getText())<Integer.parseInt(endHourText.getText()) ) {
			Person person = new Person(Integer.parseInt(roomNumberText.getText()), 
					firstNameText.getText(), lastNameText.getText(), 
					Integer.parseInt(startHourText.getText()),
					Integer.parseInt(endHourText.getText()));
			 ObservableList<Person> personList = tableView.getItems();
			 personList.add(person);
			 tableView.setItems(personList);
			 	}
			else if (Integer.parseInt(startHourText.getText())>=Integer.parseInt(endHourText.getText())){
					Alert alert = new Alert(AlertType.ERROR,"Godzina rozpoczęcia pracy "
									+ "nie może być większa od godziny zakończenia");
					alert.show();
			 		}
			else { 	
					Alert alert = new Alert(AlertType.ERROR,"Pokój o podanym numerze nie istnieje, "
														+ "wprowadź numer pokoju od 120 do 131");
					alert.show();
		 		}
		
			} catch(NumberFormatException e){  
						Alert alert = new Alert(AlertType.ERROR,"Pole z numerem pokoju i godzinami, "
						+ "powinny zawierać tylko wartości liczbowe");
						alert.show();
			}
		}  
	
	
	private void setRaport() {
		ObservableList<Person> personList_Raport = 
				FXCollections.observableArrayList();
		
		for(int i = 0;i<personList.size(); i++)
			personList_Raport.add(personList.get(i));

		Collections.sort(personList_Raport);
	
		PrintWriter out = null;
		try {
			out = new PrintWriter("raport.txt");
			for(Person person:personList_Raport) {
				out.printf("%s %s %d %d %d\n",
				person.getFirstName(), person.getLastName(),
				person.getRoomNumber(), person.getStartHour(), person.getEndHour());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null)
				out.close();
		}
	}
	
	
	public void setMain(Main main) {
		this.main = main;
		
	}
	
	public void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));
		roomNumberColumn.setCellValueFactory(new PropertyValueFactory<Person,Integer>("roomNumber"));
		tableView.getSelectionModel().selectedItemProperty().addListener((ov,old_value,new_value) -> {
			firstNameText.setText(new_value.getFirstName());
			lastNameText.setText(new_value.getLastName());
			roomNumberText.setText(String.valueOf(new_value.getRoomNumber()));
			startHourText.setText(String.valueOf(new_value.getStartHour()));
			endHourText.setText(String.valueOf(new_value.getEndHour()));
			
			int jpg = Integer.parseInt(roomNumberText.getText());
			
			if	(Integer.parseInt(roomNumberText.getText())== jpg) {
				Image image = new Image(getClass().getResourceAsStream("/controller/officemapjpg/"+jpg+".jpg"));
				imageView.setImage(image);
				
			startHourText.setText(String.valueOf(new_value.getStartHour()));
			endHourText.setText(String.valueOf(new_value.getEndHour()));
			
			}});
		}
	
	@FXML
	public void readButton() {	
		setList();
		Image biuro = new Image(getClass().getResourceAsStream("biuro.jpg"));
		imageView.setImage(biuro);
		tableView.setItems(personList);
	}
	
	@FXML
	public void saveButton() {	
		saveToFileList();		
	}
	
	@FXML
	public void addPerson() {
		addPersonToList();
	}

	@FXML
	public void reportPerson() {
		setRaport();
	}	

}
