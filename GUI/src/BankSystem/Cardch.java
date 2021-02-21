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
		
		iframe=new JFrame("信用卡类型选择");
		iframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ip0=new JPanel();
		ip0.add(new JLabel("                 请选择你的信用卡类型:                    "));
		
		ip1=new JPanel();
		one =new JButton("普通卡");
		ip1.add(one);
		two =new JButton("金卡");
		ip1.add(two);
		three =new JButton("白银卡");
		ip1.add(three);
		four =new JButton("钻石卡");
		ip1.add(four);
		five =new JButton("黑卡");
		ip1.add(five);
		cancel=new JButton("取消");
		ip1.add(cancel);
		
		ip1.setLayout(new GridLayout(5,1,20,20));
		
		iframe.add(ip0);
   	 	iframe.add(ip1);
   	 	iframe.setLayout(new FlowLayout());
    	iframe.setVisible(true);
    	iframe.setBounds(600,300,350,300);
        one.addActionListener(this);//绑定监听器
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        cancel.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		creditAccount = (CreditAccount)account;
		
		if(e.getActionCommand().equals("普通卡")) {
			
//			creditAccount;
			
		}else if(e.getActionCommand().equals("金卡")) {
			
		}else if(e.getActionCommand().equals("白银卡")) {
			
		}else if(e.getActionCommand().equals("钻石卡")) {
			
		}else if(e.getActionCommand().equals("黑卡")) {
			
		}
		else 
			iframe.setVisible(false);
	}
	
	public static void main(String[] args) {
		
		Cardch cardch = new Cardch();

	}
}
