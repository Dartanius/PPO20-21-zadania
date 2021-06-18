package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RybyController {
	@FXML TableColumn <Fanatycy_Wedkarstwa, String>IMIE;
	@FXML TableColumn <Fanatycy_Wedkarstwa, String>NAZWISKO;
	@FXML TableColumn <Fanatycy_Wedkarstwa, String>DATAUZYSKANIALICENCJI;
	@FXML TableColumn <Fanatycy_Wedkarstwa, String>NALEZYDOZWIAZKU;
	
	@FXML TableColumn <Ryby, String>gatunek;
	@FXML TableColumn <Ryby, Integer>rozmiar;
	@FXML TableColumn <Ryby, String>zwer;
	@FXML TableColumn <Ryby, String>data;
	@FXML TableColumn <Ryby, Integer>kto;

	@FXML Button btZad;
	@FXML TableView <Object>tabelaRyb;
	@FXML TableView <Object>tableaFantykow;
	@FXML ObservableList <Object> ryby;
	@FXML ObservableList <Object> fanatycy;
	@FXML Button rybsko;
	@FXML ArrayList<String>  Gatunki;
	@FXML ArrayList<String>  cZYZWER;
	@FXML ArrayList<String>  dATPOW;
	@FXML ArrayList<String>  lOWC;
	
	
	
	public void zaladujListy() {
		ArrayList<String> gatunki = new ArrayList<>();
		ArrayList<String> CZYZWER = new ArrayList<>();
		ArrayList<String> DATPOW = new ArrayList<>();
		ArrayList<String> LOWC = new ArrayList<>();
		
		gatunki.add("OKON");
		gatunki.add("SUM");
		gatunki.add("KARP");
		gatunki.add("SANDACZ");
		gatunki.add("HALIBUT");
		CZYZWER.add("TAK");
		CZYZWER.add("NIE");
		CZYZWER.add("W TRAKCIE");
		DATPOW.add("24.05.2021");
		DATPOW.add("21.12.2021");
		DATPOW.add("24.04.2021");
		DATPOW.add("12.03.2021");
		DATPOW.add("24.03.2021");
		DATPOW.add("18.10.2021");
		DATPOW.add("19.10.2021");
		DATPOW.add("11.07.2021");
		DATPOW.add("09.08.2021");
		DATPOW.add("10.05.2021");
		DATPOW.add("16.08.2021");
		DATPOW.add("02.09.2021");
		DATPOW.add("12.08.2021");
		DATPOW.add("10.06.2021");
		DATPOW.add("16.06.2021");
		DATPOW.add("10.01.2021");
		LOWC.add("EWA");
		LOWC.add("MARIAN");
		LOWC.add("WIKTOR");
		LOWC.add("KONDRAD");
		LOWC.add("KAROL");
		
	 Gatunki = gatunki;
	 cZYZWER = CZYZWER; 
	 dATPOW =DATPOW ;
	 lOWC = LOWC;
	}
	
	public static class Ryby {
		private String gatunek;
		private Integer rozmiar;
		private String zwer;
		public String getZwer() {
			return zwer;
		}
		public void setZwer(String zwer) {
			this.zwer = zwer;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		
		private String data;
		private String kto;
		
		public String getKto() {
			return kto;
		}
		public void setKto(String kto) {
			this.kto = kto;
		}
		public String getGatunek() {
			return gatunek;
		}
		public void setGatunek(String gatunek) {
			this.gatunek = gatunek;
		}
		public int getRozmiar() {
			return rozmiar;
		}
		public void setRozmiar(int rozmiar) {
			this.rozmiar = rozmiar;
		}
	
		
		
	}
	
	public static class Fanatycy_Wedkarstwa {
		private String IMIE;
		private String NAZWISKO;
		private String DATAUZYSKANIALICENCJI;
		private String NALEZYDOZWIAZKU;
		
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
		public String getDATAUZYSKANIALICENCJI() {
			return DATAUZYSKANIALICENCJI;
		}
		public void setDATAUZYSKANIALICENCJI(String dATAUZYSKANIALICENCJI) {
			DATAUZYSKANIALICENCJI = dATAUZYSKANIALICENCJI;
		}
		public String getNALEZYDOZWIAZKU() {
			return NALEZYDOZWIAZKU;
		}
		public void setNALEZYDOZWIAZKU(String nALEZYDOZWIAZKU) {
			NALEZYDOZWIAZKU = nALEZYDOZWIAZKU;
		}
		
		
		
	}
	@FXML public void dodaj_rybsko() {
		try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")){
			Statement s = c.createStatement();
			Random rand = new Random();
			String k = Gatunki.get(rand.nextInt(Gatunki.size()));
			String l = cZYZWER.get(rand.nextInt(cZYZWER.size()));
			String a = dATPOW.get(rand.nextInt(dATPOW.size()));
			String b = lOWC.get(rand.nextInt(lOWC.size()));
			PreparedStatement ps = c.prepareStatement("INSERT INTO RYBY (GATUNEK, ROZMIAR, CZYZW, DAT,  LOWCA) SELECT ?, ?, ?, ?, ID FROM FANATYCY  WHERE IMIE = ?");
			ps.setString(1, k);
			ps.setInt(2, rand.nextInt(200));
			ps.setString(3, l );
			ps.setString(4, a );
			ps.setString(5,b );
			ps.execute(); 
			Dane2(s);
			Pokaz_Dane2();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@FXML public void zaladuj_dane() {
		
		try {
			Statement s = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "").createStatement();
			CreateTableFanatycy(s);
			CreateTableRyby(s);
			Dane1(s);
			Dane2(s);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		Pokaz_Dane();
		Pokaz_Dane2();
		zaladujListy();
		btZad.setDisable(true);
	}
		
	@FXML public ObservableList<Object> Dane1(Statement s) throws SQLException {
		
		ResultSet rs = s.executeQuery("SELECT * FROM FANATYCY");
		ObservableList<Object> listafanatyków = FXCollections.observableArrayList(); 
		
		while(rs.next()) {
			Fanatycy_Wedkarstwa F = new Fanatycy_Wedkarstwa();
			F.setIMIE(rs.getString("IMIE"));
			F.setNAZWISKO(rs.getString("NAZWISKO"));;
			F.setDATAUZYSKANIALICENCJI(rs.getString("DATAUZYSKANIALICENCJI"));
			F.setNALEZYDOZWIAZKU(rs.getString("NALEZYDOZWIAZKU"));
			listafanatyków.add(F);
		}
		return fanatycy = listafanatyków;
	}
	@FXML public ObservableList<Object> Dane2(Statement s) throws SQLException {
		
		ResultSet rs = s.executeQuery("SELECT GATUNEK, ROZMIAR, CZYZW, DAT, F.IMIE, F.NAZWISKO FROM RYBY R INNER JOIN FANATYCY F ON R.LOWCA = F.ID");
		ObservableList<Object> listaRYB = FXCollections.observableArrayList(); 
		
		while(rs.next()) {
			Ryby R = new Ryby();
			R.setGatunek(rs.getString("GATUNEK"));
			R.setRozmiar(rs.getInt("ROZMIAR"));
			R.setZwer(rs.getString("CZYZW"));
			R.setData(rs.getString("DAT"));
			R.setKto(rs.getString("IMIE")+" "+rs.getString("NAZWISKO"));
			listaRYB.add(R);
		}
		return ryby = listaRYB;
	}
	
	public void CreateTableFanatycy(Statement s) throws SQLException {
		 s.execute("DROP TABLE IF EXISTS FANATYCY");
		 s.execute("CREATE TABLE FANATYCY (ID INT IDENTITY PRIMARY KEY,IMIE VARCHAR(225), NAZWISKO VARCHAR(225), DATAUZYSKANIALICENCJI VARCHAR(225), NALEZYDOZWIAZKU VARCHAR(225))");
		 s.execute("INSERT INTO FANATYCY (IMIE, NAZWISKO, DATAUZYSKANIALICENCJI, NALEZYDOZWIAZKU) VALUES ('MARIAN', 'LOPATA', '03.04.2020R.','TAK')");
		 s.execute("INSERT INTO FANATYCY (IMIE, NAZWISKO, DATAUZYSKANIALICENCJI, NALEZYDOZWIAZKU) VALUES ('KAROL', 'MOTYKA','20.06.2015R.','NIE')");
		 s.execute("INSERT INTO FANATYCY (IMIE, NAZWISKO, DATAUZYSKANIALICENCJI, NALEZYDOZWIAZKU) VALUES ('EWA', 'BIELIK','24.05.2008R.','TAK')");
		 s.execute("INSERT INTO FANATYCY (IMIE, NAZWISKO, DATAUZYSKANIALICENCJI, NALEZYDOZWIAZKU) VALUES ('KONDRAD', 'MICKIEWICZ', '13.05.2021R.','NIE')");
		 s.execute("INSERT INTO FANATYCY (IMIE, NAZWISKO, DATAUZYSKANIALICENCJI, NALEZYDOZWIAZKU) VALUES ('WIKTOR', 'STAL', '07.12.2013R.','TAK')");
		 
	}
	
	public void CreateTableRyby(Statement s) throws SQLException {
		s.execute("DROP TABLE IF EXISTS RYBY");
		s.execute("CREATE TABLE RYBY (ID INT IDENTITY PRIMARY KEY, GATUNEK VARCHAR(225), ROZMIAR INT,  DAT VARCHAR(225), CZYZW VARCHAR(225), LOWCA INT, FOREIGN KEY (LOWCA) REFERENCES FANATYCY(ID))");
		s.execute("INSERT INTO RYBY (GATUNEK, ROZMIAR, CZYZW, DAT,  LOWCA) SELECT 'OKON', 178, 'TAK', '24.06.2021', ID FROM FANATYCY WHERE IMIE = 'MARIAN'");
		 s.execute("INSERT INTO RYBY (GATUNEK, ROZMIAR, CZYZW, DAT,  LOWCA) SELECT 'OKON', 128, 'TAK', '24.06.2021', ID FROM FANATYCY  WHERE IMIE = 'EWA'");
		 s.execute("INSERT INTO RYBY (GATUNEK, ROZMIAR, CZYZW,DAT,  LOWCA) SELECT 'SUM', 134, 'NIE', '14.05.2021', ID FROM FANATYCY  WHERE IMIE = 'KAROL'");
		 s.execute("INSERT INTO RYBY (GATUNEK, ROZMIAR, CZYZW,DAT,  LOWCA) SELECT 'KARP', 78, 'W TRAKCIE', '29.04.2021', ID FROM FANATYCY  WHERE IMIE = 'MARIAN'");
		 s.execute("INSERT INTO RYBY (GATUNEK, ROZMIAR, CZYZW,DAT,  LOWCA) SELECT 'SANDACZ', 98, 'TAK', '16.05.2021', ID FROM FANATYCY  WHERE IMIE = 'KONDRAD'");
		 s.execute("INSERT INTO RYBY (GATUNEK, ROZMIAR,CZYZW, DAT,  LOWCA) SELECT 'HALIBUT', 21, 'NIE', '15.04.2021', ID FROM FANATYCY  WHERE IMIE = 'WIKTOR'");
	 
	}

	


	@FXML public void Pokaz_Dane() {
		IMIE.setCellValueFactory(new PropertyValueFactory<>("IMIE"));
		NAZWISKO.setCellValueFactory(new PropertyValueFactory<>("NAZWISKO"));
		DATAUZYSKANIALICENCJI.setCellValueFactory(new PropertyValueFactory<>("DATAUZYSKANIALICENCJI"));
		NALEZYDOZWIAZKU.setCellValueFactory(new PropertyValueFactory<>("NALEZYDOZWIAZKU"));
		
		tableaFantykow.setItems(fanatycy);
			
	}
	
	@FXML public void Pokaz_Dane2() {
		zwer.setCellValueFactory(new PropertyValueFactory<>("zwer"));
		gatunek.setCellValueFactory(new PropertyValueFactory<>("gatunek"));
		rozmiar.setCellValueFactory(new PropertyValueFactory<>("rozmiar"));
		data.setCellValueFactory(new PropertyValueFactory<>("data"));
		kto.setCellValueFactory(new PropertyValueFactory<>("kto"));
		
		tabelaRyb.setItems(ryby);
			
	}


}
