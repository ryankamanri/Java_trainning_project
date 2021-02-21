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

	private JFrame frame;//窗体；
	 private JButton inmoney;//按钮
	 private JButton outmoney;
	 private JButton transfer;
	 private JButton query;
	 private JButton exist;
	 private JButton logout;
	 private JPanel p0,p1,p2;//
	 private Account account;
	//frame = new JFrame("请选择账户类型");
	
	 public choice(Account account) {
		 frame = new JFrame("选择操作");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton inmoney=new JButton("存款");
		 JButton outmoney=new JButton("取款");
		 JButton query=new JButton("查询");
		 JButton transfer=new JButton("转账");
		 JButton logout=new JButton("注销");
		 JButton exist=new JButton("返回主页面");
		 p0=new JPanel();
		 p0.add(new JLabel("           请选择功能:         "));
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
		// TODO 自动生成的方法存根

		String b=e.getActionCommand();
		if(b.equals("存款")) {
			InMoney inmoney = new InMoney(account);
			
		}
		else if(b.equals("取款")) {
			OutMoney outMoney = new OutMoney(account);
			
		}
		
		else if(b.equals("转账")) {
			transfer trans = new transfer(account);
			
		}
		else if(b.equals("查询")) {
			Query query = new Query();
			
		}
		else if(b.equals("注销")) {
			Logout logout = new Logout();
			
		}
		else if(b.equals("返回主页面")) {
			frame.setVisible(false);
			Main main = new Main();
		}
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//new Main();
		choice in=new choice(new Account(1,"w"));

	}

	
	

}
