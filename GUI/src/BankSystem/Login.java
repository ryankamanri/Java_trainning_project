package BankSystem;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Model.Account;
import Service.Bank;
//import Service.Bank.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
	
	private JFrame frame;
	private JButton confirm;
	private JButton cancle;
	private JTextField name;
	private JTextField password;
	private JPanel p0,p1,p2,p3;
	Account account;
	
	public Login() {
		
		
		frame = new JFrame("账户登录");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p0=new JPanel();
		p0.add(new JLabel("请登录您的账户"));
		frame.add(p0);
		
		p1=new JPanel();
		p1.add(new JLabel("\t账号："));
		name=new JTextField(20);
		p1.add(name);
		
		p2=new JPanel();
		p2.add(new JLabel("\t密码："));
		password=new JPasswordField(20);
		p2.add(password);
		
		p3=new JPanel();
		confirm=new JButton("确认");
		cancle=new JButton("取消");
		p3.add(confirm);
		p3.add(cancle);
		
		frame.pack();
		frame.add(p0);
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		frame.setVisible(true);
	    frame.setLayout(new FlowLayout());
		frame.setBounds(600,300,350,300);
		
		confirm.addActionListener(this);
		cancle.addActionListener(this);
		 
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String c=e.getActionCommand();
		if(c.equals("确认")) {
			//加控制条件，判断账户类型
			String Password;
			Password = password.getText();
			long ID = Long.valueOf(name.getText());
			System.out.println("ps"+Password);
			System.out.println("id"+ID);
			account = new Account(ID, Password);
			System.out.println("test");
			System.out.println(account.toString());
			account.toString();
			Account flag = null;
			Model.Account.AccountType type;
			
			try {

				flag = Bank.Login.LogIn(ID, Password);
				if(flag == null) {
					
					JOptionPane.showMessageDialog(null, "用户不存在");
					new Login();
				}
				else {
					type = flag.getAccountType();
					if(type == Model.Account.AccountType.LoansAccount){
						
						JOptionPane.showMessageDialog(null, "本账户为贷款账户");
						LoanFrame loanFrame = new LoanFrame(flag);
					}
					else if(type == Model.Account.AccountType.SavingAccount) {
						
						JOptionPane.showMessageDialog(null, "本账户为储蓄账户");
						choice ch = new choice(flag);
						
					}
					else {
						
						JOptionPane.showMessageDialog(null, "本账户为信用账户");
						choice ch = new choice(flag);
						
					}
				}
				
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			//choice是信用账户和储蓄账户的功能选择，贷款账户另外一个
//			choice sa=new choice();
			frame.setVisible(false);
		    
		}
		else if(c.equals("取消")) {
			Main in=new Main();
			frame.setVisible(false);	
		}
		
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		Login login=new Login();
	}

}
