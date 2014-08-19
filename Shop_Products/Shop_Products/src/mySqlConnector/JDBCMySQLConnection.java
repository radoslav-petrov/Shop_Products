package mySqlConnector;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JDBCMySQLConnection {
	
	private Connection conn = null;

	public void dabaBaseConnection()
	{
	    
	    
	   
	    String url = "jdbc:mysql://localhost:3306/";
	    String dbName = "shop";
	    String driver = "com.mysql.jdbc.Driver";
	    String userName = "root";
	    String password = "";

	    try
	    {
	        Class.forName(driver).newInstance();
	        conn = DriverManager.getConnection(url+dbName,userName,password);
	        
	        System.out.println("Connected to the database");
	        
	        
//	        insetData();
//	        setFinalPrice(1.5);
	        
	        
	        
	    }
	    catch (SQLException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (Exception e)
	    {
	        System.out.println("NO CONNECTION");
	    }
	    finally{
	    	if(conn != null){
	    		try {
					conn.close();
			        System.out.println("Disconnected from database");
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
	    }
	}
	
	private void insetData() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {
			d = sdf.parse("21/12/2012");
		} catch (ParseException e) {
			System.out.println("wrong date format");
		}
		DataBaseFields dataFields = new DataBaseFields("kiselo mlqko", 0.47, 41, d,"Tonus");
		
		PreparedStatement stmt = null;
		String sql = "INSERT INTO data (product_name, a_price, quantity, manufacturer, id, date_of_arrival)" + 
				" VALUES (?, ?, ?, ?, ?, ?)";
		stmt = conn.prepareStatement(sql);
		
		stmt.setString(1, dataFields.getProductName());
		stmt.setDouble(2, dataFields.getaPrice());
		stmt.setInt(3, dataFields.getQuantity());
		stmt.setString(4, dataFields.getManufacturer());
		stmt.setString(5, dataFields.getId()); 
		stmt.setObject(6, dataFields.getDateOfArrival());
				
		int rows = stmt.executeUpdate();
		System.out.println("Rows impacted : " + rows );
		
		stmt.close();
		
	}
	
	private void setFinalPrice(double mult) throws SQLException {
		
		CallableStatement stmt = null;

	      System.out.println("Creating statement...");
	      String sql = "{call setFinalPrice (?)}";
	      stmt = conn.prepareCall(sql);
	      stmt.setDouble(1, mult);

	      System.out.println("Executing stored procedure..." );
	      stmt.execute();

	      stmt.close();
	}
}