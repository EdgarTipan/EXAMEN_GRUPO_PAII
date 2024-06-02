package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola mundo");
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituto","root","root");
			Statement statement = connection.createStatement();
			ResultSet result= statement.executeQuery("SELECT* FROM person");
			while(result.next())
				System.out.println("Persona:"+result.getString("name"));
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
