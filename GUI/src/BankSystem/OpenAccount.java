package BankSystem;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//�����Զ���İ�
import Model.*;
import Program.*;
import Service.Bank;
import SQLConnection.*;

public class OpenAccount implements ActionListener {

	private JFrame frame;
	private JButton confirm;
	private JButton cancle;
	private JTextField name;
	private JTextField password;
	private JTextField ID;
	private JTextField phnum;
	private JTextField confirmpassword;
	private JPanel p1,p2,p3,p4,p5,p6;
	int type;
	
	public OpenAccount(int type) {
		
		type = this.type;
		
		frame = new JFrame("����");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		p1=new JPanel();
		p1.add(new JLabel("\t   ���֤�ţ� "));
		ID=new JTextField(20);
		p1.add(ID);
		
		p2=new JPanel();
		p2.add(new JLabel("\t    ������       "));
		name=new JTextField(20);
		p2.add(name);
		
		p3=new JPanel();
		p3.add(new JLabel("\t    �绰���룺"));
		phnum=new JTextField(20);
		p3.add(phnum);
		
		p4=new JPanel(); 
		p4.add(new JLabel("\t    ���룺        "));
		password=new JPasswordField(20);
		p4.add(password);
		
		p5=new JPanel();
		p5.add(new JLabel("\t�ٴ�ȷ������:"));
		confirmpassword=new JPasswordField(20);
		p5.add(confirmpassword);
		
		p6=new JPanel();
		confirm=new JButton("ȷ��");
		cancle=new JButton("ȡ��");
		p6.add(confirm);
		p6.add(cancle);
		frame.pack();
		
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		frame.add(p4);
		frame.add(p5);
		frame.add(p6);
		
		frame.setVisible(true);
	    frame.setLayout(new FlowLayout());
		frame.setBounds(600,300,350,300);
		confirm.addActionListener(this);
		cancle.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {

		String c=e.getActionCommand();
		if(c.equals("ȷ��")) {
			
//			�ı�����Ϊ��
			 if(ID.getText().equals("")&&name.getText().equals("")&&phnum.getText().equals("")&&password.getText().equals("")&&confirmpassword.getText().equals(""))
			 {
				 JOptionPane.showMessageDialog(null, "��Ϣ�����пհ�");//����
                 return;
			 }
			 
			 
			 
//			�����ȷ������һ��
			 if(!password.getText().equals(confirmpassword.getText()))
			 {
				 JOptionPane.showMessageDialog(null, "�����ȷ�����벻һ��");//����
                 return;
			 }

//			����  ���Կ���
			String pass, confirmPass, Name, personID, phoneNumber;
			double balance = 0;//������
			
			pass = password.getText();
			confirmPass = confirmpassword.getText();
			System.out.println("pass = "+pass);
			System.out.println("confirmPass = "+confirmPass);
			Name = name.getText();
			personID = ID.getText();
			phoneNumber = phnum.getText();
			Bank bank = new Bank();
			Account flag = null;
			
//			����
			try {//���һ������Ϊ�˻����� �쳣Ϊ�Զ�����
				flag = bank.register(pass, confirmPass, Name, personID, phoneNumber, 0, 1);
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			if(flag != null) {
				JOptionPane.showMessageDialog(null, "�����ɹ���������˻���¼���˺�Ϊ��"+flag.getId());//����
			}			
			else {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ���������д��Ϣ");//����
				return;
			}
			
			frame.setVisible(false);
			Main main=new Main();
		}
		else if(c.equals("ȡ��")) {
			frame.setVisible(false);
			registerchoice re=new registerchoice();
		}
	}


//����
	public static void main(String[] args) throws SQLException {
		// TODO �Զ����ɵķ������

		Bank bank = new Bank();
//		bank.register("123","123","duqingyue","430122","199",2000,2);
		OpenAccount oa=new OpenAccount(0);
	}

}
