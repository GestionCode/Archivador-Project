/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Laptop
 */
public class conexionBD {
    Connection connection= null;
    static conexionBD instance=null;

    public conexionBD() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/personal","root","root");
    }
    public static conexionBD getIntance() throws Exception{
        if(instance==null){
            instance=new conexionBD();
        }
        return instance;
    }
    @Override
    public void finalize()throws Exception{
        if(connection.isClosed()){
            connection=null;
            connection.isClosed();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
