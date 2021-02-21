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
 
public class InMoney implements ActionListener{
    public JTextField money;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    private Account account;
    public InMoney(Account account) {
    	
    	this.account=account;
    	
        iframe=new JFrame("存款");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法

        ip0=new JPanel();
        ip0.add(new JLabel("账户id:"+account.getId()));
    
        
        ip1=new JPanel();
        yue=new JLabel("账户余额:"+account.getBalance());
       
        
        ip1.add(yue);
 
        ip2=new JPanel();
        ip2.add(new JLabel("存款金额:"));
        money=new JTextField(20);
        ip2.add(money);
 
        ip3=new JPanel();
        confirm=new JButton("确定");
        ip3.add(confirm);
        cancel=new JButton("返回");
        ip3.add(cancel);
 
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        iframe.add(confirm);
        iframe.add(cancel);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);
 
        iframe.setBounds(600,300,350,300);
        
        confirm.addActionListener(this);//绑定监听器
        cancel.addActionListener(this);
 
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("确定"))//按下确定按钮
        {
     	
        	Model.Account.AccountType type;
			type = account.getAccountType();
			try {
				Bank.Login login=new Bank.Login(account);
				if(type == Model.Account.AccountType.SavingAccount) {
					int x=login.Saving(Double.parseDouble(money.getText()));
					if(x==0) {
						JOptionPane.showMessageDialog(null, "该账户已挂失,无法操作");
						iframe.setVisible(false);
		        		 new InMoney(account);
					}
					if(x==-1) {
						JOptionPane.showMessageDialog(null, "存款余额不得小于等于0");
						iframe.setVisible(false);
		        		 new InMoney(account);
					}
					if(x==1) {
						JOptionPane.showMessageDialog(null, "存款成功");//弹窗
		        		yue.setText("账户余额:"+account.getBalance());
		        		iframe.setVisible(false);
		        		new InMoney(account);
					}
				}
				else {
					int x=login.Saving(Double.parseDouble(money.getText()),3);
					if(x==0) {
						JOptionPane.showMessageDialog(null, "该账户已挂失,无法操作");
						iframe.setVisible(false);
		        		 new InMoney(account);
					}
					if(x==-1) {
						JOptionPane.showMessageDialog(null, "存款余额不得小于等于0");
						iframe.setVisible(false);
		        		 new InMoney(account);
					}
					if(x==1) {
						JOptionPane.showMessageDialog(null, "存款成功");//弹窗
		        		yue.setText("账户余额:"+account.getBalance());
		        		iframe.setVisible(false);
		        		new InMoney(account);
					}
				}
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        }
        else
        {
        iframe.setVisible(false);//隐藏
 
        }
    }
    
    public static void main(String[] args) {
		
		InMoney inMoney = new InMoney(new Account(3,"123"));

	}
}
