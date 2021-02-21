package BankSystem;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//引入自定义的包
import Model.*;
import Program.*;
import Service.Bank;
import SQLConnection.*;

public class OpenAccount implements ActionListener {

	private JFrame frame;
	private JButton confirm;
	private JButton cancle;
	private JTextField name;
	private JTextField password;
	private JTextField ID;
	private JTextField phnum;
	private JTextField confirmpassword;
	private JPanel p1,p2,p3,p4,p5,p6;
	int type;
	
	public OpenAccount(int type) {
		
		type = this.type;
		
		frame = new JFrame("开户");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		p1=new JPanel();
		p1.add(new JLabel("\t   身份证号： "));
		ID=new JTextField(20);
		p1.add(ID);
		
		p2=new JPanel();
		p2.add(new JLabel("\t    姓名：       "));
		name=new JTextField(20);
		p2.add(name);
		
		p3=new JPanel();
		p3.add(new JLabel("\t    电话号码："));
		phnum=new JTextField(20);
		p3.add(phnum);
		
		p4=new JPanel(); 
		p4.add(new JLabel("\t    密码：        "));
		password=new JPasswordField(20);
		p4.add(password);
		
		p5=new JPanel();
		p5.add(new JLabel("\t再次确认密码:"));
		confirmpassword=new JPasswordField(20);
		p5.add(confirmpassword);
		
		p6=new JPanel();
		confirm=new JButton("确认");
		cancle=new JButton("取消");
		p6.add(confirm);
		p6.add(cancle);
		frame.pack();
		
		frame.add(p1);
		frame.add(p2);
		frame.add(p3);
		frame.add(p4);
		frame.add(p5);
		frame.add(p6);
		
		frame.setVisible(true);
	    frame.setLayout(new FlowLayout());
		frame.setBounds(600,300,350,300);
		confirm.addActionListener(this);
		cancle.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {

		String c=e.getActionCommand();
		if(c.equals("确认")) {
			
//			文本框不能为空
			 if(ID.getText().equals("")&&name.getText().equals("")&&phnum.getText().equals("")&&password.getText().equals("")&&confirmpassword.getText().equals(""))
			 {
				 JOptionPane.showMessageDialog(null, "信息不能有空白");//弹窗
                 return;
			 }
			 
			 
			 
//			密码和确认密码一致
			 if(!password.getText().equals(confirmpassword.getText()))
			 {
				 JOptionPane.showMessageDialog(null, "密码和确认密码不一致");//弹窗
                 return;
			 }

//			否则  可以开户
			String pass, confirmPass, Name, personID, phoneNumber;
			double balance = 0;//开户余额？
			
			pass = password.getText();
			confirmPass = confirmpassword.getText();
			System.out.println("pass = "+pass);
			System.out.println("confirmPass = "+confirmPass);
			Name = name.getText();
			personID = ID.getText();
			phoneNumber = phnum.getText();
			Bank bank = new Bank();
			Account flag = null;
			
//			开户
			try {//最后一个参数为账户类型 异常为自动生成
				flag = bank.register(pass, confirmPass, Name, personID, phoneNumber, 0, 1);
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			if(flag != null) {
				JOptionPane.showMessageDialog(null, "开户成功，请进入账户登录！账号为："+flag.getId());//弹窗
			}			
			else {
				JOptionPane.showMessageDialog(null, "开户失败，请重新填写信息");//弹窗
				return;
			}
			
			frame.setVisible(false);
			Main main=new Main();
		}
		else if(c.equals("取消")) {
			frame.setVisible(false);
			registerchoice re=new registerchoice();
		}
	}


//测试
	public static void main(String[] args) throws SQLException {
		// TODO 自动生成的方法存根

		Bank bank = new Bank();
//		bank.register("123","123","duqingyue","430122","199",2000,2);
		OpenAccount oa=new OpenAccount(0);
	}

}
