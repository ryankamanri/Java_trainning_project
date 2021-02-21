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

	public JFrame iframe;//窗口变量
    public JPanel ip0, ip1, ip2, ip3, ip4;//面板变量
    public JTextField f0, f1, f2, f3;
    public JButton confirm, cancel;
    
    public Logout() {
    	
    	iframe = new JFrame("注销");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法
        
        ip0 = new JPanel();
        ip0.add(new JLabel("账户名："));
        f0 = new JTextField(14);
        ip0.add(f0);
        
        ip1 = new JPanel();
        ip1.add(new JLabel("密码："));
        f1 = new JTextField(14);
        ip1.add(f1);

//        ip2 = new JPanel();
//        ip2.add(new JLabel("身份证号码："));
//        f2 = new JTextField(14);
//        ip2.add(f2);
//        
//        ip3 = new JPanel();
//        ip3.add(new JLabel("手机号码："));
//        f3 = new JTextField(14);
//        ip3.add(f3);
        
        ip4 = new JPanel();
        confirm = new JButton("确定");
        cancel = new JButton("取消");
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
        
        iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 65, 10));//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
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
		
		if(cmd.equals("取消")) {
			
			iframe.setVisible(false);
		    Main main=new Main();
		    
		}
		else if(cmd.equals("确定")) {
			Account account = new Account(t, Password);
			boolean flag = false;
			try {
//				Bank.Login lo = new Bank.Login(account);
			    flag=Bank.LogOut(t,Password);
			    if(!flag) {
			    	JOptionPane.showMessageDialog(null, "注销失败");
			    }
			    else {
			    	
			    	JOptionPane.showMessageDialog(null, "注销成功");
			    	Main  mc=new Main();
			    	iframe.setVisible(false);
			    }
				
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		Logout logout = new Logout();
	}
	

}
