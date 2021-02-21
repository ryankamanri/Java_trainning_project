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

public class OutMoney implements ActionListener{

	public JTextField money;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    private Account account;
    
    public OutMoney(Account account) {
    	
    	this.account=account;
    	
    	iframe=new JFrame("取款");
    	iframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	ip0=new JPanel();
    	ip0.add(new JLabel("账户id:"+account.getId()));
    	
    	ip1=new JPanel();
    	yue=new JLabel("账户余额:"+account.getBalance());
    	ip1.add(yue);
    	
    	ip2=new JPanel();
    	ip2.add(new JLabel("取款金额"));
    	money=new JTextField(20);
    	ip2.add(money);
    	
    	ip3=new JPanel();
    	confirm=new JButton("确定");
    	cancel=new JButton("取消");
    	ip3.add(confirm);
    	ip3.add(cancel);
    	
    	iframe.add(ip0);
    	iframe.add(ip1);
    	iframe.add(ip2);
    	iframe.add(ip3);
    	iframe.setLayout(new FlowLayout());
    	iframe.setVisible(true);
    	iframe.setBounds(600,300,350,300);
        confirm.addActionListener(this);//绑定监听器
 
        cancel.addActionListener(this);
        
        
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		double Money = Double.parseDouble(money.getText());
		if(e.getActionCommand().equals("确定")) {
			
			Model.Account.AccountType type;
			type = account.getAccountType();
			
			try {
				Bank.Login login=new Bank.Login(account);
				if(type == Model.Account.AccountType.SavingAccount) {
					
					int x=login.Withdrawal(Money);
					
					if(Money <= 0) {
						JOptionPane.showMessageDialog(null, "取款金额应大于零");
						return;
					}
						
					if(x==0) {
						JOptionPane.showMessageDialog(null, "该账户已挂失,无法操作");
						return;
					}
					if(x==-1) {
						JOptionPane.showMessageDialog(null, "取款余额不得小于等于0");
						return;
					}
					if(x==1) {
						System.out.println("test");
						JOptionPane.showMessageDialog(null, "取款成功");//弹窗
		        		yue.setText("账户余额:"+account.getBalance());
		        		iframe.setVisible(false);
		        		new OutMoney(account);
					}
				}
				else {
					int x=login.Withdrawal(Double.parseDouble(money.getText()),3);
					if(Double.parseDouble(money.getText())<=0) {
						JOptionPane.showMessageDialog(null, "取款余额不得小于等于0");
						iframe.setVisible(false);
		        		 new OutMoney(account);
					}
					if(x==0) {
						JOptionPane.showMessageDialog(null, "该账户已挂失,无法操作");
						iframe.setVisible(false);
		        		 new OutMoney(account);
					}
					if(x==-1) {
						JOptionPane.showMessageDialog(null, "余额不足");
						iframe.setVisible(false);
		        		 new OutMoney(account);
					}
					if(x==1) {
						JOptionPane.showMessageDialog(null, "取款成功");//弹窗
		        		yue.setText("账户余额:"+account.getBalance());
		        		iframe.setVisible(false);
		        		new OutMoney(account);
					}
				}
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			
//			Model.Account.AccountType type;
//			type = account.getAccountType();
//			if(type == Model.Account.AccountType.SavingAccount) {
//				if(account.Withdrawal(Double.parseDouble(money.getText()))) {
//					JOptionPane.showMessageDialog(null, "取款成功");
//					yue.setText("账户余额:"+account.getBalance());
//					iframe.setVisible(false);
//				}
//				else {
//	        		 JOptionPane.showMessageDialog(null, "余额不足");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//			}
////			if(type == Model.Account.AccountType.CreditAccount)
//			else
//			{
//				CreditAccount t=(CreditAccount)account;
//				if(t.Withdrawal(Double.parseDouble(money.getText()),3)) {
//					JOptionPane.showMessageDialog(null, "取款成功");
//					yue.setText("账户余额:"+t.getBalance());
//					iframe.setVisible(false);
//				}
//				else {
//	        		 JOptionPane.showMessageDialog(null, "余额不足");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//			}
//			
//			
			
//			try {
//				
////              Test.currentAccount.outMoney(Integer.parseInt(money.getText()));
//                
//				JOptionPane.showMessageDialog(null, "取款成功");
//				
////              yue.setText("账户余额:"+Test.currentAccount.money);//更新余额显示
//			}
//			catch (ClassCastException e1)//捕获当前登录账户中inmoney函数中的异常。类型转换异常
//            {
// 
//                JOptionPane.showMessageDialog(null, "输入数据类型错误，请输入整数");
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
		
//		OutMoney Out = new OutMoney(new Account(3,"123"));

	}
}