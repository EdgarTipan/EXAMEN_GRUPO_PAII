package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOPerson implements IDAO{
	Connection connection;
		public DAOPerson() throws SQLException{
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","labcom,2015");
	
}
		public void closeConnection() throws SQLException {
			if (connection!= null) {
				connection.close();
			}
				
		}
	
	public void createPerson(Person person) throws SQLException {
		
		PreparedStatement ps=connection.prepareStatement("INSERT INTO person* FROM person VALUES(?,?,?)");
		ps.setInt(1,person.getId());
		ps.setString(2, person.getName());
		ps.setInt(3, person.getAge());
		ps.execute();
		ps.close();
		
	}
	
	public Person readPerson(int id) {
		
		return null;
		
	}
	
	public void updatePerson(Person person) {
		
	}
	public void deletePerson(Person person) {
		
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}


}
