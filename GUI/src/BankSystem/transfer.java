package BankSystem;

import javax.swing.*;

import Model.Account;
import Model.CreditAccount;
import Service.Bank;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.SQLException;

public class transfer implements ActionListener {

	public JTextField money,people;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3,ip4;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    private Account account;
    
    public transfer(Account account) {
    	
    	 this.account=account;
    	 iframe=new JFrame("转账");
    	 
    	 ip0=new JPanel();
    	 ip0.add(new JLabel("账户id:"+account.getId()));
    	 
    	 ip1=new JPanel();
    	 yue=new JLabel("账户余额:"+account.getBalance());
    	 ip1.add(yue);
    	 
    	 ip2=new JPanel();
    	 ip2.add(new JLabel("收钱账户id:"));
    	 people=new JTextField(20);
    	 ip2.add(people);
    	 
    	 ip3=new JPanel();
    	 ip3.add(new JLabel("转账金额："));
    	 money=new JTextField(20);
    	 ip3.add(money);
    	 
    	 confirm=new JButton("确认");
    	 cancel=new JButton("取消");
    	 ip4=new JPanel();
    	 ip4.add(confirm);
    	 ip4.add(cancel);
    	 
    	 iframe.add(ip0);
    	 iframe.add(ip1);
    	 iframe.add(ip2);
    	 iframe.add(ip3);
    	 iframe.add(ip4);
    	 iframe.setLayout(new FlowLayout());
     	 iframe.setVisible(true);
     	 iframe.setBounds(600,300,350,300);
         confirm.addActionListener(this);//绑定监听器
         cancel.addActionListener(this);
    	 
    	 
    	 
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("确认")) {
			
			String people1,money1;
			people1=people.getText();
			money1=money.getText();
			double mon=Double.parseDouble(money1);
			long rec=Integer.valueOf(people1);
			
			Model.Account.AccountType type;
			type = account.getAccountType();
			try {
				Bank.Login login=new Bank.Login(account);
				int x=login.Transfer(rec,mon);
				if(x==0) {
					JOptionPane.showMessageDialog(null, "余额不足");
					iframe.setVisible(false);
	        		 new transfer(account);
				}
				if(x==-1) {
					JOptionPane.showMessageDialog(null, "转账余额不得小于等于0");
					iframe.setVisible(false);
					new transfer(account);
				}
				if(x==1) {
					JOptionPane.showMessageDialog(null, "转账成功");//弹窗
	        		yue.setText("账户余额:"+account.getBalance());
	        		iframe.setVisible(false);
	        		new transfer(account);
				}
				if(x==-2) {
					JOptionPane.showMessageDialog(null, "该账户已挂失,无法操作");//弹窗
	        		iframe.setVisible(false);
	        		new transfer(account);
				}
				if(x==-3) {
					JOptionPane.showMessageDialog(null, "对方账户不存在,转账失败");//弹窗
	        		iframe.setVisible(false);
	        		new transfer(account);
				}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			if(type == Model.Account.AccountType.SavingAccount) {
//				if(account.Transfer(rec,mon)==1) {
//					JOptionPane.showMessageDialog(null, "转款成功");
//					yue.setText("账户余额:"+account.getBalance());
//					iframe.setVisible(false);
//				}
//				else if(account.Transfer(rec,mon)==-1) {
//	        		 JOptionPane.showMessageDialog(null, "转款金额不得小于0");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//				else {
//	        		 JOptionPane.showMessageDialog(null, "余额不足");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//			}
//			if(type == Model.Account.AccountType.CreditAccount) {
//				CreditAccount t=(CreditAccount)account;
//				if(t.Transfer(rec,mon)==1) {
//					JOptionPane.showMessageDialog(null, "转款成功");
//					yue.setText("账户余额:"+t.getBalance());
//					iframe.setVisible(false);
//				}
//				else if(t.Transfer(rec,mon)==0){
//	        		 JOptionPane.showMessageDialog(null, "余额不足");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//				else {
//	        		 JOptionPane.showMessageDialog(null, "转款金额不得小于0");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//			}
			
			
			
//			try {
//				
////              Test.currentAccount.transfer(Integer.parseInt(money.getText()),other.getText());
//				JOptionPane.showMessageDialog(null, "转账成功");
////              yue.setText("账户余额:"+Test.currentAccount.money);//更新面板上的余额
//			}catch (ClassCastException e1)//捕获当前登录账户中inmoney函数中的异常。类型转换异常
//            {
// 
//                JOptionPane.showMessageDialog(null, "输入数据类型错误，请输入正确的数");
// 
//            }
//            catch (Exception e1)//
//            {
//                JOptionPane.showMessageDialog(null, e1.getMessage());
//            }
		}
		else iframe.setVisible(false);//隐藏
	}
	public static void main(String[] args) {
		
		transfer Out = new transfer(new Account(3,"123"));

	}
    
}