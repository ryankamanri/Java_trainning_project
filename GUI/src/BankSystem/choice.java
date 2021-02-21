package BankSystem;


import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Model.Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class choice implements ActionListener {

	private JFrame frame;//���壻
	 private JButton inmoney;//��ť
	 private JButton outmoney;
	 private JButton transfer;
	 private JButton query;
	 private JButton exist;
	 private JButton logout;
	 private JPanel p0,p1,p2;//
	 private Account account;
	//frame = new JFrame("��ѡ���˻�����");
	
	 public choice(Account account) {
		 frame = new JFrame("ѡ�����");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton inmoney=new JButton("���");
		 JButton outmoney=new JButton("ȡ��");
		 JButton query=new JButton("��ѯ");
		 JButton transfer=new JButton("ת��");
		 JButton logout=new JButton("ע��");
		 JButton exist=new JButton("������ҳ��");
		 p0=new JPanel();
		 p0.add(new JLabel("           ��ѡ����:         "));
		 frame.add(p0);
//		 p2=new JPanel();
//		 p2.add(new JLabel("           sss "));
//		 frame.add(p2);
		 p1=new JPanel();
		 p1.add(inmoney);
		 p1.add(outmoney);
		 p1.add(query);
		 p1.add(transfer);
		 p1.add(logout);
		 p1.add(exist);
		 p1.setLayout(new GridLayout(5,1,50,30 ));
		 frame.add(p1);
		 //frame.pack();
	     frame.setVisible(true);
	     frame.setLayout(new FlowLayout());
		 frame.setBounds(600,300,350,300);
		 inmoney.addActionListener(this);
		 outmoney.addActionListener(this);
		 query.addActionListener(this);
		 transfer.addActionListener(this);
		 logout.addActionListener(this);
		 exist.addActionListener(this);
		 this.account=account;
		 
		 
		 }
	 public Account getaccount() {
		 return this.account;
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������

		String b=e.getActionCommand();
		if(b.equals("���")) {
			InMoney inmoney = new InMoney(account);
			
		}
		else if(b.equals("ȡ��")) {
			OutMoney outMoney = new OutMoney(account);
			
		}
		
		else if(b.equals("ת��")) {
			transfer trans = new transfer(account);
			
		}
		else if(b.equals("��ѯ")) {
			Query query = new Query();
			
		}
		else if(b.equals("ע��")) {
			Logout logout = new Logout();
			
		}
		else if(b.equals("������ҳ��")) {
			frame.setVisible(false);
			Main main = new Main();
		}
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//new Main();
		choice in=new choice(new Account(1,"w"));

	}

	
	

}
