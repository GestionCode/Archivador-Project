/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pxblo
 */
public class Querys {
    
     static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo=new DefaultTableModel();
    
    
    public void Agregar( String nombre,String tipo){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            String sql="INSERT INTO documento(nombre,tipo) "
                    + "values (?,?)";
            
            PreparedStatement pst = cn.prepareStatement(sql);
           
            
            pst.setString(1,nombre);
            pst.setString(2,tipo);
            pst.execute();
            JOptionPane.showMessageDialog(null,"EL Registro Fue Exitoso","Adiccion exitosa",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void Modificar( String rfc,String nombre){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            String sql="UPDATE documentos SET"
                    + " nombre=?,tipo=? WHERE id=?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1,nombre);
            
           
 
            pst.execute();
            JOptionPane.showMessageDialog(null,"EL Registro Fue Exitoso","Adiccion exitosa",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public DefaultTableModel lista(){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            Statement s=cn.createStatement();
            
            String sql="SELECT * FROM Datos_Generales";
            rs=s.executeQuery(sql);
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int numColum=rsmd.getColumnCount();
            for(int i=1;i<=numColum;i++){
               modelo.addColumn(rsmd.getColumnLabel(i));
            }
            while(rs.next()){
                Object [] fila=new Object[numColum];
                for(int p=0;p<numColum;p++){
                    fila[p]=rs.getObject(p+1);
                }
                modelo.addRow(fila);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }
    
    public DefaultTableModel busqueda1(String id){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            Statement s=cn.createStatement();
            
            String sql="SELECT * FROM corte WHERE idcorte="+id;
            rs=s.executeQuery(sql);
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int numColum=rsmd.getColumnCount();
            //System.out.println(rsmd.getColumnCount()+"-1");
            for(int i=1;i<=numColum;i++){
               modelo.addColumn(rsmd.getColumnLabel(i));
              // System.out.println(rsmd.getColumnLabel(i));
            }
            while(rs.next()){
                Object [] fila=new Object[numColum];
                for(int p=0;p<numColum;p++){
                    fila[p]=rs.getObject(p+1);
                    //System.out.println(fila[p].toString()+"-3");
                }
                
                modelo.addRow(fila);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }
     
     public void Eliminar(String id){
        try {
            Statement s= cn.createStatement();
            
            String sql="DELETE FROM Datos_Generales WHERE RFC="+id;
            
            s.executeUpdate(sql);
            //s.close();
            //cn.close();
            JOptionPane.showMessageDialog(null,"El Registro eliminado","Eliminación exitosa",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(QueryEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        
    }//Fin método eliminar
     
     
     
     public DefaultTableModel predictiva(String id){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            Statement s=cn.createStatement();
            
            String sql="SELECT * FROM corte WHERE idcorte LIKE '%"+id+"%'";
            rs=s.executeQuery(sql);
            
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int numColum=rsmd.getColumnCount();
            //System.out.println(rsmd.getColumnCount()+"-1");
            for(int i=1;i<=numColum;i++){
               modelo.addColumn(rsmd.getColumnLabel(i));
              // System.out.println(rsmd.getColumnLabel(i));
            }
            while(rs.next()){
                Object [] fila=new Object[numColum];
                for(int p=0;p<numColum;p++){
                    fila[p]=rs.getObject(p+1);
                    //System.out.println(fila[p].toString()+"-3");
                }
                
                modelo.addRow(fila);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }
     
    public String valorMaximo(){
        String resul=null;
        try{
            String sql="Select Max(idcorte) from corte";
            
            cn=conexionBD.getIntance().getConnection();
            s=cn.createStatement();
            rs=s.executeQuery(sql);
            if(rs.next()){
               resul= rs.getString(1);
                
            }else{
                
            }
            
        }catch(Exception e){
            
        }
        return resul;
    }
    
    
    
}
