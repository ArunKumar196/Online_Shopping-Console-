package shopping;
import java.util.*;
class Main {
	static String email="";
	public static void main(String[] args) throws Exception {
		db_connection d=new db_connection();
		db_connection.connection();
		Scanner sc=new Scanner(System.in);
		String check="";
		
		int choice=0;
		do {
			System.out.println("Press 1 for Existing User");
			System.out.println("Press 2 to login as Admin");
			System.out.println("Press 3 to Create a new Account");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:{
				System.out.print("Enter Email: ");
				sc.nextLine();
				String User=sc.nextLine();
				System.out.print("Password: ");
				String Pass=sc.nextLine();
				check=d.checkUser(User,Pass);
				if(check.equals("Customer"))
					email=User;
				break;
			}
			case 2:
			{
				System.out.print("Enter Your Security Key: ");
				String security_num=sc.next();
				check=d.checkUser(security_num);
				if(check=="")
					System.out.println("Enter valid security number");
				break;
			}
			case 3:
			{
				System.out.print("Name: ");
				sc.nextLine();
				String U_name=sc.nextLine();
				System.out.print("Email Id: ");
				String email=sc.nextLine();
				System.out.print("Addresss: ");
				String address=sc.nextLine();
				System.out.print("Phone Number: ");
				String phno=sc.nextLine();
				System.out.print("Password: ");
				String pass=sc.nextLine();
				System.out.print("Confirm Password: ");
				String c_pass=sc.nextLine();
				d.createAccount(U_name,email,address,phno,pass,c_pass);
				break;
			}
			default:
				System.out.println("Choose Between 1 and 2");
			}
			if(check!="")
			{
				switch(check)
				{
					case "Admin":
					{
						Admin a=new Admin();
						int ad_choice=0;
						System.out.println("Welcome Admin");
						do {
						System.out.println("1 -> Add");
						System.out.println("2 -> Update");
						System.out.println("3 -> Remove");
						System.out.println("4 -> View");
						System.out.println("0 -> Exit");
						ad_choice=sc.nextInt();
						if(ad_choice==1)
						{
							System.out.print("Enter the product name: ");
							String s1=sc.next();
							System.out.print("Enter the product price: ");
							int p=sc.nextInt();
							a.addProd(s1,p);
						}
						else if(ad_choice==2)
						{
							d.view();
							System.out.print("Enter product id: ");
							int id=sc.nextInt();
							System.out.print("Enter the price to be updated: ");
							int price=sc.nextInt();
							if(price!=0)
								a.updateProd(id,price);
							else
								System.out.println("Price should be greater than zero");
						}
						else if(ad_choice==3)
						{
							d.view();
							System.out.print("Enter the product id: ");
							int id=sc.nextInt();
							a.deleteProd(id);
						}
						else if(ad_choice==4)
						{
							d.view();
						}
						else if(ad_choice>5)
							System.out.println("Enter Your Choice Between 1,2 and 3");	
						else if(ad_choice==0)
							break;
						}while(ad_choice!=0);
						break;
					}
					case "Customer":
					{
						System.out.println("Welcome user");
						Customer c=new Customer();
						int choi=0;
						do {
							System.out.println("1 -> Add to Cart");
							System.out.println("2 -> View Cart");
							System.out.println("3 -> Remove From Cart");
							System.out.println("4 -> Bill");
							System.out.println("5 -> Place Order");
							System.out.println("0 -> Exit!");
							choi=sc.nextInt();
							switch(choi)
							{
								case 1: 
									d.view();
									System.out.print("Enter Product Id to add to cart: ");
									int prod_id=sc.nextInt();
									System.out.print("Quantiy in Kg: ");
									int quan=sc.nextInt();
									c.addToCart(prod_id,quan);
									break;
								case 2:
									c.viewCart();
									break;
								case 3:
									c.viewCart();
									System.out.print("Enter the Prod_id from the cart to remove: ");
									int p=sc.nextInt();
									c.removeCart(p);
									break;
								case 4:
									c.bill();
									break;
								case 5:
									c.placeOrder(email);
									break;
								case 0:
									break;
							}
						}while(choi!=0);
					}
				}
			}
		}while(choice!=0);
	}
}
