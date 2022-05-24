package shopping;
import java.sql.*;
public class db_connection {
	static Connection con=null;
	public static Connection connection() throws Exception
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","Arunkumar12345");
		return con;
	}
	void createAccount(String name,String mail,String Address,String phno,String pass,String c_pass) throws Exception
	{
		if(pass.length()>=8 && phno.length()==10 && pass.equals(c_pass))
		{
			String sql="Insert into customer(Name,Email,Address,Phone_No,Password,Confirm_Password) values('"+name+"','"+mail+"','"+Address+"','"+phno+"','"+pass+"','"+c_pass+"')";
			Statement s=(Statement) con.createStatement();
			int conf=s.executeUpdate(sql);
			
			if(conf==1)
				System.out.println("Account Created Successfully");
			else
				System.out.println("Something went wrong try again after some time");
		}
		else
			System.out.println("Enter valid  details");
	}
	String checkUser(String user,String pass) throws Exception
	{
		String sql="Select * from customer where email='"+user+"' And Password='"+pass+"'";
		Statement s=(Statement) con.createStatement();
		ResultSet conf=s.executeQuery(sql);
		String role="";
		while(conf.next())
		{
			role=conf.getString(7);
		}
		return role;
	}
	String checkUser(String sec_key) throws Exception
	{
		String sql="Select * from Admin_details where Ad_Security_Key='"+sec_key+"'";
		Statement s=(Statement) con.createStatement();
		ResultSet conf=s.executeQuery(sql);
		String role="";
		while(conf.next())
		{
			role=conf.getString(7);
		}
		return role;
	}
	void view() throws Exception{
		String sql="Select * from products";
		Statement s=(Statement) con.createStatement();
		ResultSet conf=s.executeQuery(sql);
		System.out.println("----------------------------------------------------------------");
		System.out.printf("			Products\n\n");
		System.out.println("Product Id		Product Name		Price");
		while(conf.next())			
		{
			System.out.println(conf.getInt("prod_id")+"			"+conf.getString("prod_name")+"			"+conf.getInt("price"));
		}
		System.out.println("----------------------------------------------------------------");
	}
}
