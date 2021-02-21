package BankSystem;
import javax.swing.*;

import Model.Account;
import SQLConnection.SQLConnection;
import Service.Bank;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Logout implements ActionListener{

	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1, ip2, ip3, ip4;//������
    public JTextField f0, f1, f2, f3;
    public JButton confirm, cancel;
    
    public Logout() {
    	
    	iframe = new JFrame("ע��");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
        
        ip0 = new JPanel();
        ip0.add(new JLabel("�˻�����"));
        f0 = new JTextField(14);
        ip0.add(f0);
        
        ip1 = new JPanel();
        ip1.add(new JLabel("���룺"));
        f1 = new JTextField(14);
        ip1.add(f1);

//        ip2 = new JPanel();
//        ip2.add(new JLabel("���֤���룺"));
//        f2 = new JTextField(14);
//        ip2.add(f2);
//        
//        ip3 = new JPanel();
//        ip3.add(new JLabel("�ֻ����룺"));
//        f3 = new JTextField(14);
//        ip3.add(f3);
        
        ip4 = new JPanel();
        confirm = new JButton("ȷ��");
        cancel = new JButton("ȡ��");
        ip4.add(confirm);
        ip4.add(cancel);
        ip4.add(new JPanel());
        ip4.add(new JPanel());
        
        iframe.pack();
        iframe.add(ip0);
        iframe.add(ip1);
//        iframe.add(ip2);
//        iframe.add(ip3);
        iframe.add(ip4);
        
        iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 65, 10));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        

    }
	
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		String ID, Password;
		ID = f0.getText();
		long t;
		t = Integer.valueOf(ID);
		Password = f1.getText();
		
		if(cmd.equals("ȡ��")) {
			
			iframe.setVisible(false);
		    Main main=new Main();
		    
		}
		else if(cmd.equals("ȷ��")) {
			Account account = new Account(t, Password);
			boolean flag = false;
			try {
//				Bank.Login lo = new Bank.Login(account);
			    flag=Bank.LogOut(t,Password);
			    if(!flag) {
			    	JOptionPane.showMessageDialog(null, "ע��ʧ��");
			    }
			    else {
			    	
			    	JOptionPane.showMessageDialog(null, "ע���ɹ�");
			    	Main  mc=new Main();
			    	iframe.setVisible(false);
			    }
				
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
				
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		Logout logout = new Logout();
	}
	

}
