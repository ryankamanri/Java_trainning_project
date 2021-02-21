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
		
		
		frame = new JFrame("�˻���¼");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p0=new JPanel();
		p0.add(new JLabel("���¼�����˻�"));
		frame.add(p0);
		
		p1=new JPanel();
		p1.add(new JLabel("\t�˺ţ�"));
		name=new JTextField(20);
		p1.add(name);
		
		p2=new JPanel();
		p2.add(new JLabel("\t���룺"));
		password=new JPasswordField(20);
		p2.add(password);
		
		p3=new JPanel();
		confirm=new JButton("ȷ��");
		cancle=new JButton("ȡ��");
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
		if(c.equals("ȷ��")) {
			//�ӿ����������ж��˻�����
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
					
					JOptionPane.showMessageDialog(null, "�û�������");
					new Login();
				}
				else {
					type = flag.getAccountType();
					if(type == Model.Account.AccountType.LoansAccount){
						
						JOptionPane.showMessageDialog(null, "���˻�Ϊ�����˻�");
						LoanFrame loanFrame = new LoanFrame(flag);
					}
					else if(type == Model.Account.AccountType.SavingAccount) {
						
						JOptionPane.showMessageDialog(null, "���˻�Ϊ�����˻�");
						choice ch = new choice(flag);
						
					}
					else {
						
						JOptionPane.showMessageDialog(null, "���˻�Ϊ�����˻�");
						choice ch = new choice(flag);
						
					}
				}
				
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
			//choice�������˻��ʹ����˻��Ĺ���ѡ�񣬴����˻�����һ��
//			choice sa=new choice();
			frame.setVisible(false);
		    
		}
		else if(c.equals("ȡ��")) {
			Main in=new Main();
			frame.setVisible(false);	
		}
		
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		Login login=new Login();
	}

}
