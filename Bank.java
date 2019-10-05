import java.awt.*;
import java.applet.Applet;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
//import java.lang.*;
import java.util.*; //for random function
import java.util.Date;

/**
* <h1>Internet Banking</h1>
* The Bank program implements an JAVA applet for internet banking.
* <p>
*
* @author  Venkat
* @version 1.0
* @since   2017-08-31
*/


public class Bank extends Applet
{
	
	
	public static void main(String args[])
	{
		/*This is the main method in this method we create a window "THE BANK" */
		JFrame window1=new JFrame("THE BANK");
		window1.setLayout(null);
		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window1.setBounds(250, 50, 900, 600);
		JLabel lock;
		lock=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\lock - Copy.PNG"));
		lock.setBounds(230,145,40,40);
		window1.add(lock);
		

		JLabel developer=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\Avengers3.png"));
		developer.setBounds(425, 520, 35, 35);
		window1.add(developer);
		
		JLabel devBy1,devBy2,time;
		devBy1=new JLabel("DEVELOPED  BY : ");
		devBy1.setBounds(305, 520, 125, 30);
		devBy1.setFont(new Font("",Font.ITALIC,14));
		window1.add(devBy1);
		
		devBy2=new JLabel("J-04");
		devBy2.setBounds(460, 530, 110, 30);
		//devBy2.setForeground(Color.BLUE);
		devBy2.setFont(new Font("",Font.ITALIC,18));
		window1.add(devBy2);
		
		@SuppressWarnings("deprecation")
		String d=new Date().toLocaleString();
		time=new JLabel("LAST UPDATE : "+d);
		time.setBounds(580,520,300,30);
		time.setFont(new Font("",Font.ITALIC,14));
		window1.add(time);
		
		JButton login,reset,signup,forgot;
		
		login =new JButton("LOGIN");
		login.setBounds(300, 300, 120, 30);
		window1.add(login);
		
		reset =new JButton("RESET");
		reset.setBounds(450, 300, 120, 30);
		window1.add(reset);
		
		JButton reset1;
		reset1=new JButton("RESET");
		
		JButton reset2;
		reset2=new JButton("RESET");
		
		signup=new JButton("SIGN UP");
		signup.setBounds(450,385, 120, 30);
		window1.add(signup);
		
		forgot =new JButton("FORGOT  ?");
		forgot.setBounds(600, 250, 120, 30);
		window1.add(forgot);
		
		JLabel username,password,locktext,newuser;
		
		username=new JLabel("USERNAME* ");
		username.setBounds(210,200,200,30);
		username.setFont(new Font("",Font.BOLD,14));  //IN FONT CLASS
		window1.add(username);
		
		password=new JLabel("PASSWORD* ");
		password.setBounds(210, 250, 200, 30);
		password.setFont(new Font("",Font.BOLD,14));
		window1.add(password);
		
		locktext=new JLabel("LOGIN TO THE INTERNET BANKING");
		locktext.setBounds(300,140,270,50);
		locktext.setFont(new Font("",Font.BOLD,15));
		window1.add(locktext);
		
		newuser=new JLabel("NO ACCOUNT ? SIGN UP NOW >>>");
		newuser.setBounds(210,375,270,50);
		newuser.setFont(new Font("",Font.BOLD,14));
		window1.add(newuser);
		
		JTextField logtext=new JTextField();
		logtext.setBounds(300, 200, 270, 30);
		window1.add(logtext);
		
		JPasswordField passtext=new JPasswordField();
		passtext.setBounds(300, 250, 270, 30);
		window1.add(passtext);
		/*This is the method that reset the username and password textfiele */
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				logtext.setText("");
				passtext.setText("");
			}
		});
		reset1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				logtext.setText("");
				passtext.setText("");
			}
		});
		/*This Inner Class that create the connection between sql and java using JDBC driver */
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String entereduser,enteredpass;
				entereduser=logtext.getText();
				enteredpass=String.copyValueOf(passtext.getPassword());
				String url="jdbc:mysql://localhost:3306/test";
				ResultSet res;
				try
				{					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection(url);
					Statement st=con.createStatement();
					Statement st1=con.createStatement();
					int flag=0;
					
					res=st.executeQuery("SELECT ACCNO,NAME,USERNAME,PASSWORD,BALANCE,ACCTYPE FROM BANK;");
					while (res.next())
					{
						if (entereduser.equals(res.getString("USERNAME"))&&enteredpass.equals(res.getString("PASSWORD")))
						{							
							flag=1;
							int bal=res.getInt("BALANCE");
							window1.dispose();
							JFrame window2=new JFrame("WELCOME "+res.getString("NAME")+"");
							window2.setLayout(null);
							window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							window2.setBounds(250, 50, 900, 600);
							window2.getContentPane().setBackground(Color.WHITE);
							
							JButton logout=new JButton("LOGOUT");
							logout.setBounds(700, 50, 90, 30);
							window2.add(logout);							
							
							JLabel accno,name,amt,type,limit;
							
							name=new JLabel("NAME                                :     "+res.getString("NAME")+"");
							name.setBounds(250, 150, 250, 30);							
							window2.add(name);
							
							accno=new JLabel("ACCOUNT NUMBER       :     "+res.getString("ACCNO")+"");
							accno.setBounds(250, 200, 250, 30);
							window2.add(accno);
							
							type=new JLabel("ACCOUNT TYPE              :     "+res.getString("ACCTYPE")+" ");
							type.setBounds(250, 250, 250, 30);
							window2.add(type);
							
							amt=new JLabel("DEPOSITION  AMOUNT : Rs.");
							amt.setBounds(250, 300, 160, 30);
							window2.add(amt);
							
							JTextField amount=new JTextField("");
							amount.setBounds(410, 300, 120, 30);
							window2.add(amount);
							
							limit=new JLabel("( LIMIT Rs.50000 )");
							limit.setBounds(540, 300, 100, 30);
							window2.add(limit);
							
							JButton deposit=new JButton("DEPOSIT");
							deposit.setBounds(410, 340, 120, 30);
							window2.add(deposit);							
							
							deposit.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									int amt=Integer.parseInt(amount.getText());
									if (amt<=50000)
									{
										try
										{									
											st1.executeUpdate("UPDATE BANK SET BALANCE ="+(bal+amt)+" WHERE USERNAME='"+entereduser+"';");
											con.close();
											JFrame window3=new JFrame("DEPOSITION SUCCESSFUL");							
											window3.setLayout(null);
											window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
											window3.setBounds(475, 250, 400, 250);
											window3.getContentPane().setBackground(Color.WHITE);
											
											JLabel success=new JLabel("MONEY SUCCESSFULLY DEPOSITED  !");;
											success.setBounds(80, 30, 350, 30);
											window3.add(success);
											
											JButton ok=new JButton("OK");
											ok.setBounds(150, 100, 80, 30);
											window3.add(ok);
											
											ok.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													window2.dispose();
													window3.dispose();
												}
											});
											
											window3.setVisible(true);
										}
										catch(SQLException e1)
										{
											e1.printStackTrace();
										}
									}												
									
								}
							});
							
							logout.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									window2.dispose();
									logtext.setText("");
									passtext.setText("");
									window1.setVisible(true);
								}
							});
				
							
							JLabel img=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\background4.PNG"));
							img.setBounds(0,0, 900, 600);
							window2.getContentPane().add(img);
							
							window2.setVisible(true);
							break; //TO BREAK OUT OF THE WHILE LOOP WHICH CHECKS 3 TIMES AND PRODUCES 3 window3 FRAMES
						}						
					}
					if (flag==0)
					{
						window1.setVisible(false);
						JFrame window3=new JFrame("ERROR !!!");							
						window3.setLayout(null);
						window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						window3.setBounds(475, 250, 400, 250);
						window3.getContentPane().setBackground(Color.WHITE);
						
						JLabel wrong1=new JLabel(" WRONG CREDENTIAL(S)  !!!");
						wrong1.setBounds(115, 30, 350, 30);
						window3.add(wrong1);
						
						JLabel wrong2=new JLabel(" ENTER CORRECT USERNAME AND PASSWORD ");
						wrong2.setBounds(55, 70, 350, 30);
						window3.add(wrong2);
						
						JButton ok=new JButton("OK");
						ok.setBounds(150, 120, 80, 30);
						window3.add(ok);
						
						ok.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								logtext.setText("");
								passtext.setText("");
								window3.dispose();
								window1.setVisible(true);
							}
						});
						
						window3.setVisible(true);
					}
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				catch(ClassNotFoundException e2)
				{
					e2.printStackTrace();
				}
			}
		});		
		
		signup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				logtext.setText("");
				passtext.setText("");
				
				window1.setVisible(false);
				JFrame window2=new JFrame("WELCOME ");
				window2.setLayout(null);
				window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window2.setBounds(250, 50, 900, 630);
				window2.getContentPane().setBackground(Color.WHITE);
				
				JLabel lbacc,lbname,lbuser,lbpass,lbadd,lbmob,lbbal,lbacctype;
				lbacc=new JLabel("ENTER YOUR NEW ACCOUNT NUMBER : ");
				lbacc.setBounds(150, 50, 250, 30);
				window2.add(lbacc);
				
				lbname=new JLabel("ENTER YOUR NAME                                    : ");
				lbname.setBounds(150, 90, 250, 30);
				window2.add(lbname);
				
				lbuser=new JLabel("SET YOUR USERNAME                               : ");
				lbuser.setBounds(150, 130, 250, 30);
				window2.add(lbuser);
				
				lbpass=new JLabel("SET YOUR PASSWORD                             : ");
				lbpass.setBounds(150, 170, 250, 30);
				window2.add(lbpass);
				
				lbadd=new JLabel("ENTER YOUR ADDRESS                             : ");
				lbadd.setBounds(150, 210, 250, 30);
				window2.add(lbadd);
				
				lbmob=new JLabel("ENTER YOUR MOBILE NUMBER               : ");
				lbmob.setBounds(150, 250, 250, 30);
				window2.add(lbmob);
				
				lbbal=new JLabel("SET YOUR BALANCE                                  : ");
				lbbal.setBounds(150, 290, 250, 30);
				window2.add(lbbal);
				
				lbacctype=new JLabel("ENTER THE TYPE OF ACCOUNT (S/C)     : ");
				lbacctype.setBounds(150, 330, 250, 30);
				window2.add(lbacctype);
				
				JPasswordField textpass,A1,A2,A3;
				textpass=new JPasswordField();
				textpass.setBounds(390, 170, 250, 30);
				window2.add(textpass);
				
				A1=new JPasswordField();
				A1.setBounds(480, 390, 325, 30);
				window2.add(A1);
				
				A2=new JPasswordField();
				A2.setBounds(480, 430, 325, 30);
				window2.add(A2);
				
				A3=new JPasswordField();
				A3.setBounds(480, 470, 325, 30);
				window2.add(A3);
				
				JTextField textacc,textname,textuser,textadd,textmob,textbal,textacctype;
				textacc=new JTextField();
				textacc.setBounds(390,50,250,30);
				window2.add(textacc);

				textname=new JTextField();
				textname.setBounds(390,90,250,30);
				window2.add(textname);

				textuser=new JTextField();
				textuser.setBounds(390,130,250,30);
				window2.add(textuser);

				textadd=new JTextField();
				textadd.setBounds(390,210,250,30);
				window2.add(textadd);

				textmob=new JTextField();
				textmob.setBounds(390,250,250,30);
				window2.add(textmob);

				textbal=new JTextField();
				textbal.setBounds(390,290,250,30);
				window2.add(textbal);

				textacctype=new JTextField();
				textacctype.setBounds(390,330,250,30);
				window2.add(textacctype);
				
				JLabel 	Q1,Q2,Q3,secret;
				secret=new JLabel("ANSWER ALL THE 3 SECRET QUESTIONS");
				secret.setBounds(325,360, 400, 30);
				secret.setFont(new Font("",Font.BOLD,14));
				secret.setForeground(Color.RED);
				window2.add(secret);
				
				Q1=new JLabel("Q1.  	WHICH IS YOUR FAVOURITE DESTINATION ABROAD? ");
				Q1.setBounds(150,390, 400, 30);
				window2.add(Q1);
				
				Q2=new JLabel("Q2.  	WHAT IS YOUR MOTHER TONGUE ? ");
				Q2.setBounds(150,430, 400, 30);
				window2.add(Q2);
				
				Q3=new JLabel("Q3.  	WHERE IS YOUR NATIVE PLACE ? ");
				Q3.setBounds(150,470, 400, 30);
				window2.add(Q3);
				
				JButton sign,back;
				sign=new JButton("SIGN UP");
				sign.setBounds(340, 520, 120, 30);		
				window2.add(sign);
				reset1.setBounds(470, 520, 120, 30);
				window2.add(reset1);
				
				back=new JButton(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\back.PNG"));
				back.setBounds(30, 25, 50, 30);
				window2.getContentPane().add(back);
				
				back.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
					//	window1.add(reset);
					//	reset.setBounds(450, 300, 120, 30);
						window2.dispose();
						
						window1.setVisible(true);
					}
				});
				
				reset.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						textpass.setText("");
						textacc.setText("");
						textname.setText("");
						textuser.setText("");
						textadd.setText("");
						textmob.setText("");
						textbal.setText("");
						textacctype.setText("");
					}
				});
				
				sign.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String url="jdbc:mysql://localhost:3306/test";
						try
						{					
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection(url);
							Statement st=con.createStatement();
							st.executeUpdate("INSERT INTO BANK VALUES ("+Integer.parseInt(textacc.getText())+",'"+textname.getText()+"','"+textuser.getText()+"','"+String.copyValueOf(textpass.getPassword())+"','"+textadd.getText()+"',"+textmob.getText()+","+Integer.parseInt(textbal.getText())+",'"+textacctype.getText()+"','"+String.copyValueOf(A1.getPassword())+"','"+String.copyValueOf(A2.getPassword())+"','"+String.copyValueOf(A3.getPassword())+"');");
							con.close();
							
							window2.dispose();
							
							JFrame window3=new JFrame("SIGNED UP");							
							window3.setLayout(null);
							window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							window3.setBounds(475, 250, 400, 250);
							window3.getContentPane().setBackground(Color.WHITE);
							
							JLabel success=new JLabel(" YOU HAVE SUCCESSFULLY CREATED YOUR ACCOUNT  !");;
							success.setBounds(20, 30, 350, 30);
							window3.add(success);
							
							JButton ok=new JButton("OK");
							ok.setBounds(150, 100, 80, 30);
							window3.add(ok);
							
							ok.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									window3.dispose();
									reset.setBounds(450, 300, 120, 30);
							//		window1.add(reset);									
							//		window1.setVisible(true);
								}
							});
							
							window3.setVisible(true);
							
						}
						catch(SQLException e1)
						{
							e1.printStackTrace();
						}
						catch(ClassNotFoundException e2)
						{
							e2.printStackTrace();
						}
					}
				});
				JLabel img=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\background4 - Copy.PNG"));
				img.setBounds(0,0, 900, 630);
				window2.getContentPane().add(img);
				
				window2.setVisible(true);
				
			}
		});
		
		forgot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				window1.setVisible(false);
				JFrame window2=new JFrame("FORGOT");
				window2.setLayout(null);
				window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				window2.setBounds(375, 100, 650, 550);
				window2.getContentPane().setBackground(Color.WHITE);
				
				JPasswordField []t=new JPasswordField[3];
				int i,y=100;
				for (i=0;i<3;i++,y=y+80)
				{
					t[i]=new JPasswordField();
					t[i].setBounds(125, y, 400, 30);
					window2.add(t[i]);
				}
				
				JTextField checkacc=new JTextField();
				checkacc.setBounds(350, 20, 175, 30);
				window2.add(checkacc);
				
				JLabel 	Q1,Q2,Q3,c1,chAcc;
				Q1=new JLabel("Q1.  	WHICH IS YOUR FAVOURITE DESTINATION ABROAD? ");
				Q1.setBounds(100,60, 400, 30);
				Q1.setFont(new Font("",Font.BOLD,14));
				window2.add(Q1);
				
				Q2=new JLabel("Q2.  	WHAT IS YOUR MOTHER TONGUE ? ");
				Q2.setBounds(100,140, 400, 30);
				Q2.setFont(new Font("",Font.BOLD,14));
				window2.add(Q2);
				
				Q3=new JLabel("Q3.  	WHERE IS YOUR NATIVE PLACE ? ");
				Q3.setBounds(100,220, 400, 30);
				Q3.setFont(new Font("",Font.BOLD,14));
				window2.add(Q3);
				
				c1=new JLabel("ENTER THE CAPTCHA  : ");
				c1.setForeground(Color.RED);
				c1.setBounds(125,310, 400, 30);
				c1.setFont(new Font("",Font.BOLD,14));
				window2.add(c1);
				
				chAcc=new JLabel("ENTER THE ACCOUNT NUMBER : ");
				chAcc.setBounds(100,20, 400, 30);
				chAcc.setFont(new Font("",Font.BOLD,14));
				window2.add(chAcc);
				
				Random r=new Random();
				int index=r.nextInt(10); //TO CHECK WITH THE DATABASE TABLE 
				
				JLabel captcha=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\captcha\\captcha"+(1+index)+".PNG"));
				captcha.setBounds(250, 300, 300, 100);
				window2.add(captcha);
				
				JTextField cap=new JTextField("");
				cap.setBounds(125, 360, 150, 30);
				window2.add(cap);
				
				JButton proceed=new JButton("PROCEED");
				proceed.setBounds(175, 430, 120, 30);
				window2.add(proceed);
				
				reset1.setBounds(315, 430, 120, 30);
				window2.add(reset1);
				
				reset1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						cap.setText("");
						t[0].setText("");
						t[1].setText("");
						t[2].setText("");
						checkacc.setText("");
					}
				});
				
				proceed.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						window1.setVisible(false);				
						
						try
						{
							int flag=0;
							ResultSet res;
							String url="jdbc:mysql://localhost:3306/test";
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection(url);
							Statement st=con.createStatement(); 
							res=st.executeQuery("SELECT * FROM CAPTCHA WHERE NUMBER="+(index)+";");
							
							while (res.next())
							{				
								System.out.print("\n INDEX = "+res.getString("VALUE"));							
								
								if (res.getString("VALUE").equals(cap.getText()))
								{								
									ResultSet res1;
									Statement st1=con.createStatement();
									res1=st1.executeQuery("SELECT * FROM BANK WHERE ACCNO="+(checkacc.getText())+";");
									
									while (res1.next())
									{
										//System.out.print("\n INDEX = "+cap.getText());
										//System.out.print("\n DATABSE INDEX = "+res.getString("VALUE"));
										System.out.print("\n A1="+String.copyValueOf(t[0].getPassword()));
										System.out.print("\n A2="+String.copyValueOf(t[1].getPassword()));
										System.out.print("\n A3="+String.copyValueOf(t[2].getPassword()));
										System.out.print("\n DATABSE A1="+res1.getString("Q1"));
										System.out.print("\n DATABSE A2="+res1.getString("Q2"));
										System.out.print("\n DATABSE A3="+res1.getString("Q3"));

										//System.out.println(x);
										if(res1.getString("Q1").equals(String.copyValueOf(t[0].getPassword()))&&res1.getString("Q2").equals(String.copyValueOf(t[1].getPassword()))&&res1.getString("Q3").equals(String.copyValueOf(t[2].getPassword())))
										{											
											flag=1;
											window2.dispose();
											JFrame window3=new JFrame("NEW CREDENTIALS");
											window3.setLayout(null);
											window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
											window3.setBounds(400, 100, 700, 500);
											window3.getContentPane().setBackground(Color.WHITE);
											
											JLabel acc,newuser,newpass;
											int a=Integer.parseInt(res1.getString("ACCNO"));
											acc=new JLabel("ACCOUNT NUMBER             :          "+a+"");
											acc.setBounds(100, 100, 700, 30);
											window3.add(acc);
											
											newuser=new JLabel("SET THE NEW USERNAME : ");
											newuser.setBounds(100, 150, 200, 30);
											window3.add(newuser);

											newpass=new JLabel("SET THE NEW PASSWORD : ");
											newpass.setBounds(100, 200, 200, 30);
											window3.add(newpass);
											
											JTextField newuserfield=new JTextField();
											newuserfield.setBounds(280, 150, 300, 30);
											window3.add(newuserfield);										
											
											JPasswordField newpassfield=new JPasswordField();
											newpassfield.setBounds(280, 200, 300, 30);
											window3.add(newpassfield);
											
											JButton ok=new JButton("CONFIRM");
											ok.setBounds(260, 280, 100, 30);
											window3.add(ok);
											
											reset2.setBounds(380, 280,100, 30);
											window3.add(reset2);
											
											reset2.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													newuserfield.setText("");
													newpassfield.setText("");
												}
											});
											
											ok.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													try
													{
														System.out.println("\n\n NEW USER ="+newuserfield.getText());
														System.out.println("\n\n NEW PASS ="+String.copyValueOf(newpassfield.getPassword()));
														st1.executeUpdate("UPDATE BANK SET USERNAME='"+newuserfield.getText()+"',PASSWORD='"+String.copyValueOf(newpassfield.getPassword())+"' WHERE ACCNO="+a+";");
													}
													catch(SQLException p)
													{
														p.printStackTrace();
													}
													logtext.setText("");
													passtext.setText("");
													
													/*reset.setBounds(450, 300, 120, 30);
													window1.add(reset);*/
													//window1.dispose();
													window1.setVisible(true);
													
													window3.dispose();
													
												}
											});
											window3.setVisible(true);
										}
										break;
									}									
								}
							}
							if (flag==0)
							{
								window1.setVisible(false);
								JFrame window4=new JFrame("ERROR !!!");							
								window4.setLayout(null);
								window4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								window4.setBounds(515, 250, 400, 250);
								window4.getContentPane().setBackground(Color.WHITE);
								
								JLabel wrong1=new JLabel(" WRONG CREDENTIAL(S)  !!!");
								wrong1.setBounds(115, 30, 350, 30);
								window4.add(wrong1);
								
								JLabel wrong2=new JLabel(" WARNING : YOUR ACCOUNT CAN GET LOCKED ! ");
								wrong2.setBounds(55, 70, 350, 30);
								window4.add(wrong2);
								
								JButton ok=new JButton("OK");
								ok.setBounds(150, 120, 80, 30);
								window4.add(ok);
								
								ok.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
									{
										logtext.setText("");
										passtext.setText("");
										window2.dispose();
										
										window1.setVisible(true);
										window1.add(reset1);
										reset1.setBounds(470, 520, 120, 30);
										
										window4.dispose();
									}
								});
								window4.setVisible(true);
							}
						}
						catch(SQLException p1)
						{
							p1.printStackTrace();
						}
						catch(ClassNotFoundException p2)
						{
							p2.printStackTrace();
						}								
					}
				});
				
				JLabel img=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\background4 - Copy (2).PNG"));
				img.setBounds(0,0, 650,550);
				window2.getContentPane().add(img);
				
				window2.setVisible(true);
			}
		});
		
		JLabel l=new JLabel(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\JAVA_IMG\\background4.PNG"));
		l.setBounds(0, 0, 900, 600);
		window1.add(l);
		
		window1.setVisible(true);
	}
}