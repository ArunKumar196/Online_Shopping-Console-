package shopping;
import java.sql.*;
public class Admin {
	public void addProd(String s1,int p) throws Exception{
		db_connection.connection();
		String sql="Insert into products(prod_name,price) values('"+s1+"','"+p+"')";
		Statement s=(Statement) db_connection.con.createStatement();
		int conf=s.executeUpdate(sql);
		
		if(conf==1)
			System.out.println("Product Added Successfully");
		
		else
			System.out.println("Something went wrong try again");
	}
	public void deleteProd(int id) throws Exception{
		db_connection.connection();
		String sql="Delete from products where prod_id='"+id+"'";
		Statement s=(Statement) db_connection.con.createStatement();
		int conf=s.executeUpdate(sql);
		if(conf==1)
			System.out.println("Deleted Successfully");
		else
			System.out.println("Enter valid product id");
	}
	public void updateProd(int id,int price) throws Exception{
		db_connection.connection();
		String sql="update products set price='"+price+"' where prod_id='"+id+"'";
		Statement s=(Statement) db_connection.con.createStatement();
		int conf=s.executeUpdate(sql);
		if(conf==1)
			System.out.println("Updated Successfully");
		else
			System.out.println("Enter valid product id");
	}
}
