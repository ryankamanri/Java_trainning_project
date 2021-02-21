package BankSystem;

import javax.swing.*;

import Model.Account;
import Service.Bank;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.SQLException;

public class Guash implements ActionListener{

	public JTextField id;
	public JPasswordField word=new JPasswordField(20);
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3,ip4;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    
    public Guash() {
    	
    	iframe = new JFrame("��ʧ");
    	iframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	id=new JTextField(20);
    	
    	ip0=new JPanel();
    	ip0.add(new JLabel("��������Ҫ��ʧ���˺ź�����"));
    	
    	ip1=new JPanel();
    	ip1.add(new JLabel("�˺ţ�"));
    	ip1.add(id);
    	
    	ip2=new JPanel();
    	ip2.add(new JLabel("���룺"));
    	ip2.add(word);
    	
    	ip3=new JPanel();
    	confirm=new JButton("ȷ��");
    	cancel=new JButton("ȡ��");
    	ip3.add(confirm);
    	ip3.add(cancel);
    	
    	iframe.add(ip0);
   	 	iframe.add(ip1);
   	 	iframe.add(ip2);
   	 	iframe.add(ip3);
   	 	iframe.setLayout(new FlowLayout());
    	iframe.setVisible(true);
    	iframe.setBounds(600,300,350,300);
        confirm.addActionListener(this);//�󶨼�����
        cancel.addActionListener(this);
    	
    }

	
	public void actionPerformed(ActionEvent e) {
		
		boolean flag = false;
		
		if(e.getActionCommand().equals("ȷ��")) {

			    String ID=id.getText();
			    int Id=Integer.valueOf(ID);
				String pass=word.getText();
				
				try {
					flag = Bank.Loss(Id,pass);
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
				if(flag)
					JOptionPane.showMessageDialog(null, "��ʧ�ɹ�");
				else {
					JOptionPane.showMessageDialog(null, "�˻���������������������ȷ��");
				}
				
		}
		else {
			iframe.setVisible(false);
			Main main = new Main();
		}
	}
	
	public static void main(String[] args) throws SQLException {
	
		Guash guash = new Guash();
//		Bank bank = new Bank();
//		bank.Loss(3, "55555");
		
	}
}
