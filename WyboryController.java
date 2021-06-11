package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.sql.DataSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class WyboryController {
	@FXML Button zatwierdź_I_bT;
	@FXML TableView<Object> tabela;
	@FXML Button druga_tura_bt;
	@FXML Button pierwsza_tura_bt;
	@FXML Button załaduj_wstepne_dane;
	@FXML Button Zatwierdź_II_bt;
	@FXML TableColumn<KANDYDAT, Integer> ID;
	@FXML TableColumn<KANDYDAT, String> IMIE;
	@FXML TableColumn<KANDYDAT, String> NAZWISKO;
	@FXML TableColumn<KANDYDAT, Integer> WYNIK_I_TURA;
	@FXML TableColumn<KANDYDAT, Integer> WYNIK_II_TURA;
	@FXML TableColumn<KANDYDAT, Integer> WYGRANA;
	@FXML Button Pokaz_Dane;
	@FXML ObservableList<Object> KANDYDACI;
	@FXML DataSource s;
	@FXML int Biggest1;
	@FXML int Biggest2;
	
	public class KANDYDAT {
		private	int ID;
		private String IMIE;
		private String NAZWISKO;
		private int WYNIK_1_TURA;
		private int WYNIK_2_TURA;
		private int WYGRANA;
		
		
		public int getID() {
			return ID;
		}
		public void setId(int id) {
			 ID =  id;
		}
		public String getIMIE() {
			return IMIE;
		}
		public void setIMIE(String iMIE) {
			IMIE = iMIE;
		}
		public String getNAZWISKO() {
			return NAZWISKO;
		}
		public void setNAZWISKO(String nAZWISKO) {
			NAZWISKO = nAZWISKO;
		}
		public int getWYNIK_1_TURA() {
			return WYNIK_1_TURA;
		}
		public void setWYNIK_1_TURA(int wYNIK_1_TURA) {
			WYNIK_1_TURA = wYNIK_1_TURA;
		}
		public int getWYNIK_2_TURA() {
			return WYNIK_2_TURA;
		}
		public void setWYNIK_2_TURA(int wYNIK_2_TURA) {
			WYNIK_2_TURA = wYNIK_2_TURA;
		}
		public int getWYGRANA() {
			return WYGRANA;
		}
		public void setWYGRANA(int wYGRANA) {
			WYGRANA = wYGRANA;
		}

	    
		
		
	}
	

	
	

	private static void CreateTableOfCandidates(Statement s) throws SQLException {
		
		 s.execute("DROP TABLE IF EXISTS WYBORY");
		 s.execute("CREATE TABLE WYBORY (ID INT IDENTITY PRIMARY KEY,IMIE VARCHAR(225), NAZWISKO VARCHAR(225), WYNIK_1_TURA INT, WYNIK_2_TURA INT, WYGRANA INT)");
		 s.execute("INSERT INTO WYBORY (IMIE, NAZWISKO, WYNIK_1_TURA, WYNIK_2_TURA, WYGRANA) VALUES ('MICHAL', 'KOPARKA', 0 ,0 ,-1)");
		 s.execute("INSERT INTO WYBORY (IMIE, NAZWISKO, WYNIK_1_TURA, WYNIK_2_TURA, WYGRANA) VALUES ('KAROL', 'MOTYKA', 0 ,0 ,-1)");
		 s.execute("INSERT INTO WYBORY (IMIE, NAZWISKO, WYNIK_1_TURA, WYNIK_2_TURA, WYGRANA) VALUES ('EWA', 'BIELIK', 0 ,0 ,-1)");
		 s.execute("INSERT INTO WYBORY (IMIE, NAZWISKO, WYNIK_1_TURA, WYNIK_2_TURA, WYGRANA) VALUES ('KONDRAD', 'MICKIEWICZ', 0 ,0 ,-1)");
		 s.execute("INSERT INTO WYBORY (IMIE, NAZWISKO, WYNIK_1_TURA, WYNIK_2_TURA, WYGRANA) VALUES ('WIKTOR', 'STAL', 0 ,0 ,-1)");
		 
	}
	

	@FXML public ObservableList<Object> załaduj_wstepne_dane() throws SQLException, ClassNotFoundException{
		
			Zatwierdź_II_bt.setDisable(true);
			zatwierdź_I_bT.setDisable(true);
			druga_tura_bt.setDisable(true);
			pierwsza_tura_bt.setDisable(true);
			Statement s = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "").createStatement();
			CreateTableOfCandidates(s);
			ResultSet rs = s.executeQuery("SELECT * FROM WYBORY");
			ObservableList<Object> listaKand = FXCollections.observableArrayList(); 
			
			while(rs.next()){
				KANDYDAT k = new KANDYDAT();
				k.setId(rs.getInt("ID"));
				k.setIMIE( rs.getString("IMIE"));
				k.setNAZWISKO(rs.getString("NAZWISKO"));
				k.setWYNIK_1_TURA(rs.getInt("WYNIK_1_TURA"));
				k.setWYNIK_2_TURA(rs.getInt("WYNIK_2_TURA"));
				k.setWYGRANA(rs.getInt("WYGRANA"));
				listaKand.add(k);
				}
			return KANDYDACI = listaKand;
    		            
    }
    		
	public static int[] n_random(int count, int finalSum)
	{
	    Random r = new Random();
	    int numbers[] = new int[count];
	    int sum = 0;
	    for (int i = 0; i < count - 1; i++)
	    {
	        numbers[i] = r.nextInt((finalSum - sum) / 2) + 1;
	        sum += numbers[i];
	    }
	    numbers[count - 1] = finalSum - sum;

	   return numbers;
	}
	
	@FXML public void przeprowadz_druga_tura() throws SQLException {
		


		int votes[] = n_random(2,100);
		Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
		
		
			try (PreparedStatement ps =  c.prepareStatement("UPDATE WYBORY SET WYNIK_2_TURA = ? WHERE WYNIK_1_TURA = ? ")){
				ps.setInt(1, votes[0]);
				ps.setInt(2, Biggest1);
				ps.execute();
				ps.setInt(1, votes[1]);
				ps.setInt(2, Biggest2);
				ps.execute();
		
			} catch (Exception e) {
				
			}
			
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM WYBORY");
			ObservableList<Object> listaKand = FXCollections.observableArrayList(); 
			
			while(rs.next()){
				KANDYDAT k = new KANDYDAT();
				k.setId(rs.getInt("ID"));
				k.setIMIE( rs.getString("IMIE"));
				k.setNAZWISKO(rs.getString("NAZWISKO"));
				k.setWYNIK_1_TURA(rs.getInt("WYNIK_1_TURA"));
				k.setWYNIK_2_TURA(rs.getInt("WYNIK_2_TURA"));
				k.setWYGRANA(rs.getInt("WYGRANA"));
				listaKand.add(k);
				}
			
			KANDYDACI = listaKand;
			Zatwierdź_II_bt.setDisable(false);
			
			Pokaz_Dane();
	}
		



	@FXML public  void przeprowadz_pierwsza_tura() throws SQLException {
		int votes[] = n_random(5,100);
		Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
			
		
			try (PreparedStatement ps =  c.prepareStatement("UPDATE WYBORY SET WYNIK_1_TURA = ? WHERE IMIE = ?")){
				ps.setInt(1, votes[0]);
				Biggest1 = votes[0];
				ps.setString(2,"EWA");
				ps.execute();
				ps.setInt(1, votes[1]);
				Biggest2 = votes[1];
				ps.setString(2,"WIKTOR");
				ps.execute();
				ps.setInt(1, votes[2]);
				Czy_Większa(votes[2]);
				ps.setString(2,"KAROL");
				ps.execute();
				ps.setInt(1, votes[3]);
				Czy_Większa(votes[3]);
				ps.setString(2,"MICHAL");
				ps.execute();
				ps.setInt(1, votes[4]);
				Czy_Większa(votes[4]);
				ps.setString(2,"KONDRAD");
				ps.execute();
				
			} catch (Exception e) {
				
			}
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM WYBORY");
			ObservableList<Object> listaKand = FXCollections.observableArrayList(); 
			
			while(rs.next()){
				KANDYDAT k = new KANDYDAT();
				k.setId(rs.getInt("ID"));
				k.setIMIE( rs.getString("IMIE"));
				k.setNAZWISKO(rs.getString("NAZWISKO"));
				k.setWYNIK_1_TURA(rs.getInt("WYNIK_1_TURA"));
				k.setWYNIK_2_TURA(rs.getInt("WYNIK_2_TURA"));
				k.setWYGRANA(rs.getInt("WYGRANA"));
				listaKand.add(k);
				}
			 KANDYDACI = listaKand;
			 Pokaz_Dane();
			 zatwierdź_I_bT.setDisable(false);
			
	}

	@FXML public void Zatwierdz_Pierwsza_Tura() {
		pierwsza_tura_bt.setDisable(true);
		druga_tura_bt.setDisable(false);
		zatwierdź_I_bT.setDisable(true);
	}

	@FXML public void Zatwierdz_Druga_Tura() throws SQLException {
		druga_tura_bt.setDisable(true);
	
		Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
		c.createStatement().execute("UPDATE WYBORY SET WYGRANA = 1 WHERE WYNIK_2_TURA = (SELECT TOP 1 WYNIK_2_TURA FROM WYBORY ORDER BY WYNIK_2_TURA DESC )");
		c.createStatement().execute("UPDATE WYBORY SET WYGRANA = 0 WHERE WYGRANA = -1 ");
	
			
		ResultSet rs = c.createStatement().executeQuery("SELECT * FROM WYBORY");
		ObservableList<Object> listaKand = FXCollections.observableArrayList(); 
		
		while(rs.next()){
			KANDYDAT k = new KANDYDAT();
			k.setId(rs.getInt("ID"));
			k.setIMIE( rs.getString("IMIE"));
			k.setNAZWISKO(rs.getString("NAZWISKO"));
			k.setWYNIK_1_TURA(rs.getInt("WYNIK_1_TURA"));
			k.setWYNIK_2_TURA(rs.getInt("WYNIK_2_TURA"));
			k.setWYGRANA(rs.getInt("WYGRANA"));
			listaKand.add(k);
			}
		KANDYDACI = listaKand;
		Pokaz_Dane();
	}

		
	
	
	public void Czy_Większa(int x) {
		if (x < Biggest1) {
			if (x > Biggest2) {
				Biggest2 = x;
			}	
		} else {
			Biggest1 = x;
		}
	}

	@FXML public void Pokaz_Dane() {
		ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		IMIE.setCellValueFactory(new PropertyValueFactory<>("IMIE"));
		NAZWISKO.setCellValueFactory(new PropertyValueFactory<>("NAZWISKO"));;
		WYNIK_I_TURA.setCellValueFactory(new PropertyValueFactory<>("WYNIK_1_TURA"));
		WYNIK_II_TURA.setCellValueFactory(new PropertyValueFactory<>("WYNIK_2_TURA"));
		WYGRANA.setCellValueFactory(new PropertyValueFactory<>("WYGRANA"));
		tabela.setItems(KANDYDACI);
		
		 
	}
	@FXML public void Pokaz_Dane_Z_Bazy() {
			Pokaz_Dane();
			pierwsza_tura_bt.setDisable(false);
			załaduj_wstepne_dane.setDisable(true);
			Pokaz_Dane.setDisable(true);
	}
}
	

