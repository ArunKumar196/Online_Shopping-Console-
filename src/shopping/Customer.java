package shopping;
import java.sql.*;
public class Customer {
	public void addToCart(int p, int q) throws Exception
	{
		db_connection.connection();
		String s1="select * from products where prod_id=('"+p+"')";
		Statement s2=(Statement)db_connection.con.createStatement();
		ResultSet rs=s2.executeQuery(s1);
		int prod_id=0,price=0;
		String s3="";
		while(rs.next())
		{
			prod_id=rs.getInt("prod_id");
			s3=rs.getString("prod_name");
			price=rs.getInt("price")*q;
			String sql="Insert into cart values('"+prod_id+"', '"+s3+"', '"+price+"', '"+q+"')";
			Statement s=(Statement) db_connection.con.createStatement();
			int conf=s.executeUpdate(sql);
			if(conf==1)
				System.out.println("Product Added to Cart Successfully");
			
			else
				System.out.println("Something went wrong try again");
		}
	}
	public void viewCart() throws Exception{
		db_connection.connection();
		String sql="Select * from cart";
		Statement s=(Statement) db_connection.con.createStatement();
		ResultSet conf=s.executeQuery(sql);
		System.out.println("-------------------------------------------------------------------------");
		System.out.printf("			Cart\n\n");
		System.out.println("Product Id		Product Name		Price		Quantity");
		while(conf.next())			
		{
			System.out.println(conf.getInt("prod_id")+"			"+conf.getString("prod_name")+"			"+conf.getInt("price")+"		"+conf.getInt("quantity"));
		}
		System.out.println("--------------------------------------------------------------------------");
	}
	public void removeCart(int p) throws Exception{
		db_connection.connection();
		String sql="Delete from cart where prod_id='"+p+"'";
		Statement s=(Statement) db_connection.con.createStatement();
		int conf=s.executeUpdate(sql);
		if(conf==1)
			System.out.println("Deleted Successfully");
		else
			System.out.println("Enter valid product id");
	}
	public void bill() throws Exception{
		db_connection.connection();
		String sql="Select * from cart";
		Statement s=(Statement) db_connection.con.createStatement();
		ResultSet conf=s.executeQuery(sql);
		System.out.println("-------------------------------------------------------------------------");
		System.out.printf("			Total Bill\n\n");
		System.out.println("Product Id		Product Name		Quantity	Price");
		int f=0;
		while(conf.next())			
		{
			f=1;
			System.out.println(conf.getInt("prod_id")+"			"+conf.getString("prod_name")+"			"+conf.getInt("quantity")+"		"+conf.getInt("price"));
		}
		if(f!=0)
		{
			String sql2="Select sum(price) as sum from cart";
			ResultSet rs=s.executeQuery(sql2);
			System.out.println();
			while(rs.next())
				System.out.println("					Total Amount		"+rs.getInt("sum"));
		}
		System.out.println("--------------------------------------------------------------------------");
	}
	public void placeOrder(String email) throws Exception{
		String sql="select * from customer where email='"+email+"'";
		Statement s=(Statement) db_connection.con.createStatement();
		ResultSet rs=s.executeQuery(sql);
		while(rs.next())
		{
			String name=rs.getString("name");
			String address=rs.getString("address");
			String phno=rs.getString("phone_no");
			String sql1="select prod_name from cart";
			String con="";
			Statement s2=(Statement) db_connection.con.createStatement();
			ResultSet rs2=s2.executeQuery(sql1);
			while(rs2.next())
			{
				con=con+" "+rs2.getString("prod_name");
			}
			String sql2="Select sum(price) as sum from cart";
			Statement s3=(Statement) db_connection.con.createStatement();
			ResultSet rs1=s3.executeQuery(sql2);
			int sum=0;
			while(rs1.next())
			{
				sum=rs1.getInt("sum");
			}
			String sql3="Insert into placeorder(name, phno, address, products , total_amount) values('"+name+"','"+phno+"','"+address+"','"+con+"','"+sum+"')";
			Statement s1=(Statement) db_connection.con.createStatement();
			int conf=s.executeUpdate(sql3);
			if(conf==1)
				System.out.println("Thank You For Ordering Products From Our Store :)");
			else
				System.out.println("blah");
			break;
		}
	}
}
