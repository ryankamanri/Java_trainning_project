package BankSystem;

import javax.swing.*;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

public class Cardch implements ActionListener{

	public JFrame iframe;
	public JButton one,two,three,four,five,cancel;
	public JPanel ip0,ip1;
	Account account;
	CreditAccount creditAccount;
	
	public Cardch() {
		
		account = new Account();
		
		iframe=new JFrame("���ÿ�����ѡ��");
		iframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ip0=new JPanel();
		ip0.add(new JLabel("                 ��ѡ��������ÿ�����:                    "));
		
		ip1=new JPanel();
		one =new JButton("��ͨ��");
		ip1.add(one);
		two =new JButton("��");
		ip1.add(two);
		three =new JButton("������");
		ip1.add(three);
		four =new JButton("��ʯ��");
		ip1.add(four);
		five =new JButton("�ڿ�");
		ip1.add(five);
		cancel=new JButton("ȡ��");
		ip1.add(cancel);
		
		ip1.setLayout(new GridLayout(5,1,20,20));
		
		iframe.add(ip0);
   	 	iframe.add(ip1);
   	 	iframe.setLayout(new FlowLayout());
    	iframe.setVisible(true);
    	iframe.setBounds(600,300,350,300);
        one.addActionListener(this);//�󶨼�����
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        cancel.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		creditAccount = (CreditAccount)account;
		
		if(e.getActionCommand().equals("��ͨ��")) {
			
//			creditAccount;
			
		}else if(e.getActionCommand().equals("��")) {
			
		}else if(e.getActionCommand().equals("������")) {
			
		}else if(e.getActionCommand().equals("��ʯ��")) {
			
		}else if(e.getActionCommand().equals("�ڿ�")) {
			
		}
		else 
			iframe.setVisible(false);
	}
	
	public static void main(String[] args) {
		
		Cardch cardch = new Cardch();

	}
}
