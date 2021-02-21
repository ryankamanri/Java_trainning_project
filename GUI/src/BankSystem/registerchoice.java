package BankSystem;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionListener;

public class registerchoice implements ActionListener {

	 private JFrame frame;//窗体；
	 private JButton savingaccount;//按钮
	 private JButton creditaccount;
	 private JButton loanaccount;
	 private JButton exist;
	 private JPanel p0,p1;
	 
	 /*
	  * type值说明：
	  * 0 储蓄账户
	  * 1 信用账户
	  * 2 贷款账户
	  */
	 public int type;
	 
	 public registerchoice() {
		 frame = new JFrame("开户选择");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton savingaccount=new JButton("储蓄账户");
		 JButton creditaccount=new JButton("信用账户");
		 JButton loanaccount=new JButton("贷款账户");
		 JButton exist=new JButton("返回");
		 p0=new JPanel();
		 p0.add(new JLabel("  请选择账户类型:      "));
		 p0.setLayout(new FlowLayout());
		 frame.add(p0);
		 p1=new JPanel();
		 p1.add(savingaccount);
		 p1.add(creditaccount);
		 p1.add(loanaccount);
		 p1.add(exist);
		 p1.setLayout(new GridLayout(5,1,20,25));
		 
		 frame.add(p1);
		 //frame.pack();
	     frame.setVisible(true);
	     frame.setLayout(new FlowLayout());
		 frame.setBounds(600,300,350,300);
		 savingaccount.addActionListener(this);
		 creditaccount.addActionListener(this);
		 exist.addActionListener(this);
		 loanaccount.addActionListener(this);
		  
		 
		 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根

		String an=e.getActionCommand();
		if(an.equals("储蓄账户")) {
			type = 0;
			OpenAccount oa=new OpenAccount(type);
			frame.setVisible(false);
		}
		else if(an.equals("信用账户")) {
			type = 1;
			Cardch cardch  = new Cardch();
			frame.setVisible(false);
		}
		
		else if(an.equals("贷款账户")) {
			type = 2;
			OpenAccount oa=new OpenAccount(type);
			frame.setVisible(false);
		}
		else if(an.equals("返回")) {
			frame.setVisible(false);
			 Main main=new Main();
			//frame.dispose();
        }
		
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		registerchoice rcRegisterchoice=new registerchoice();
	}

}
