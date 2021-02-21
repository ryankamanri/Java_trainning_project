package BankSystem;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import Service.*;
import Service.Bank.Login;
import SQLConnection.SQLConnection;
public class managerLogin implements ActionListener {

	private JFrame frame;//���壻
	private JButton confirm;//��ť
	private JButton cancle;
	private JTextField name;
	private JTextField password;
	private JPanel p0,p1,p2,p3;
	
	public managerLogin() {
		frame = new JFrame("����Ա��¼");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		p0=new JPanel();
		p0.add(new JLabel("���¼���Ĺ���Ա�˻�"));
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
	@Override
	public void actionPerformed(ActionEvent e) {

		String ID, Password;
		ID = name.getText();
		long t;
		t = Integer.valueOf(ID);
		Password = password.getText();
				
		String c=e.getActionCommand();
		if(c.equals("ȷ��")) {
		Administrator AD=new Administrator();
		Account account = new Account(t, Password);
		Account flag = null;
			try {
				Bank.Login lo = new Bank.Login(account);
			    flag=lo.LogIn(t,Password);
			    if(flag==null) {
			    	JOptionPane.showMessageDialog(null, "��½ʧ��");
			    }
			    else {
			    	
			    	JOptionPane.showMessageDialog(null, "��½�ɹ�");
			    	managercheck  mc=new managercheck();
			    	frame.setVisible(false);
			    }
				
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
				
			}
			
		}
		else if(c.equals("ȡ��")) {
			Main in=new Main();
			frame.setVisible(false);
			
					
		}
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		managerLogin managerl=new managerLogin();
	}

}
