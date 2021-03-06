
package example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JDBC {

  static int lport=;
  static String rhost="127.0.0.1";
  static String host="";
  static int rport=;
  static String user="";//ssh user
  static String password="";//ssh pass
  
  static String dbuserName = "";//mysql user
  static String dbpassword = ""; //mysql pass
  static String url = "jdbc:mysql://localhost:";
  static String driverName="com.mysql.jdbc.Driver";
  static Connection conn = null;
  //static Session session= null;

  
  public JDBC() throws JSchException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
	  Properties config = new Properties();
	  config.put("StrictHostKeyChecking", "no");
	  JSch jsch = new JSch();  
	  Session session = jsch.getSession(user, host, 22);
	  session.setPassword(password);  

	  session.setConfig(config);
	  session.connect();
	  session.setPortForwardingL(lport, "localhost", 5840);
	  makeConnection(session);
  }
  
  public static void closeConnection(){
  try{
		conn.close();
   } catch(Exception ex){
     System.err.println(ex);
   }
  }
	
	public static Connection makeConnection(Session session) throws JSchException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		try {
			System.out.println(session.toString());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:8845/taskManager?verifyServerCertificate=false&useSSL=true", dbuserName,
					dbpassword);
			// Do something with the Connection
			System.out.println("Database [taskManager] connection succeeded!");
			System.out.println();
			return conn;
		} catch (SQLException ex) {
			// handle any errors
			System.err.println("SQLException: " + ex.getMessage());
			System.err.println("SQLState: " + ex.getSQLState());
			System.err.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}
	
	public static String runQuery(String sqlStmt) {
		Statement stmt = null;
		ResultSet rs = null;
    String s = "";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlStmt);
      ResultSetMetaData rsmd =rs.getMetaData();
      int columnCount = rsmd.getColumnCount();
			// Now do something with the ResultSet ....
			boolean rowsLeft = true;
			rs.first();
      //rowsLeft = rs.next();
			while (rowsLeft) {
        for(int i = 1; i<= columnCount; i++){
        			s += rs.getString(i) + " ";     
        }
        s+="\n";
				rowsLeft = rs.next();
			}
      return s;
		} catch (SQLException ex) {
			// handle any errors
//			System.err.println("SQLException: " + ex.getMessage());
//			System.err.println("SQLState: " + ex.getSQLState());
//			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
   return s;
	}
 
 public static void runQueryNoReturn(String sqlStmt) {
		Statement stmt = null;
		ResultSet rs = null;
    int execute=0;
    String s = "";
		try {
			stmt = conn.createStatement();
			execute = stmt.executeUpdate(sqlStmt);
		} catch (SQLException ex) {
			// handle any errors
//			System.err.println("SQLException: " + ex.getMessage());
//			System.err.println("SQLState: " + ex.getSQLState());
//			System.err.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release resources in a finally{} block
			// in reverse-order of their creation if they are no-longer needed
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}
}
