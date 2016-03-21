/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
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
public class QueryDoc {
     static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo=new DefaultTableModel();
    
    
    public void Agregar( String rfc,String nombre,FileInputStream ruta,String descripcion,
            String id_entrega,int longitud){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            String sql="INSERT INTO documento(rfc,nombre,ruta,descripcion,id_entrega) "
                    + "values(?,?,?,?,?)";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1,rfc);
            pst.setString(2,nombre);
            pst.setBlob(3, ruta, longitud);
            pst.setString(4, descripcion);
            pst.setString(5,id_entrega);
          
            pst.execute();
            JOptionPane.showMessageDialog(null,"EL Registro Fue Exitoso","Adiccion exitosa",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            System.out.println(e);
        
    }
    }
    
    public void arc(){
        Blob resul=null;
        try{
            String sql = "SELECT DOC FROM documento WHERE id_dOc=1";
            
            cn=conexionBD.getIntance().getConnection();
             
            s=cn.createStatement();
             
            rs=s.executeQuery(sql);
            if(rs.next()){
               resul= rs.getBlob(1);
               //rs.getBinaryStream(1);
                
            }else{
                
            }
            FileInputStream r=(FileInputStream) resul.getBinaryStream();
            //File f=new File(r);
            System.out.println(r);
            System.out.println(resul+"--");
            
            Runtime.getRuntime().exec("cmd /c start "+r);
            
        }catch(Exception e){
            
        }
    }
    
    public void Modificar( String rfc,String nombre,String clave_ser,String curp,String domicilio,
            String localidad,String municipio,String cp,String estado_c,String lugar_n,String correo,
            String tel_part,String tel_c,String tel_lab){
        try{
            
            cn=conexionBD.getIntance().getConnection();
            String sql="UPDATE Datos_Generales SET"
                    + " nom=?,clave_servidor_publico=?,curp=?,domicilio=?,localidad=?,"
                    + "municipio=?,codigo_postal=?,estado_civil=?,lugar_nacimiento=?,"
                    + "correo_electronico=?,tel_particular=?,tel_celular=?,tel_laboral=? WHERE id=?";
            
            PreparedStatement pst = cn.prepareStatement(sql);
            
            pst.setString(1,nombre);
            pst.setString(2,clave_ser);
            pst.setString(3, curp);
            pst.setString(4,domicilio);
            pst.setString(5,localidad);
            pst.setString(6, municipio);
            pst.setString(7, cp);
            pst.setString(8, estado_c);
            pst.setString(9,lugar_n);
            pst.setString(10, correo);
            pst.setString(11, tel_part);
            pst.setString(12, tel_c);
            pst.setString(13, tel_lab);
            pst.setString(14,rfc);
 
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
