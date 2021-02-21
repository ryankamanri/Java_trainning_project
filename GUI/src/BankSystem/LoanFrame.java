package BankSystem;
import javax.swing.*;

import Model.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class LoanFrame implements ActionListener{
	
	public JFrame iframe;//窗口变量
    public JPanel ip0, ip1, ip2;//面板变量
    public JButton InLoan, OutLoan, Inmoney, Outmoney, Cancle;//按钮
    Account account;
    
    public LoanFrame(Account account){//构造方法
    	
    	this.account = account;
    	
    	//实例化窗口
        iframe=new JFrame("贷还款");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法
        
        //实例化按钮
        InLoan = new JButton("贷款");
        OutLoan = new JButton("还款");
        Inmoney = new JButton("存款");
        Outmoney = new JButton("取款");
        Cancle = new JButton("退出");
        
        //实例化面板
        ip0 = new JPanel();
        ip0.add(InLoan);
        ip0.add(OutLoan);
        ip0.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        
        ip1 = new JPanel();
        ip1.add(Inmoney);
        ip1.add(Outmoney);
        ip1.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
        
        ip2 = new JPanel();
        ip2.add(Cancle);
        
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        
        iframe.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
        InLoan.addActionListener(this);
        OutLoan.addActionListener(this);
        Cancle.addActionListener(this);
       
    }

	public void actionPerformed(ActionEvent e) {

		String cmd=e.getActionCommand();
		if(cmd.equals("贷款")) {
			LoanInFrame loanInFrame = new LoanInFrame(account);
		} else if(cmd.equals("还款")){
			try {
				LoanOutFrame loanOutFrame = new LoanOutFrame(account);
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		else if(cmd.equals("取款")) {
			
		}
		else if(cmd.equals("存款")) {
			
		}
		else {
			iframe.setVisible(false);
			JOptionPane.showMessageDialog(null, "慢走");
			iframe.dispose();
		}
	}
	
	public static void main(String[] args)
	{
		Account account = new Account(11, "123456");
		LoanFrame loanFrame = new LoanFrame(account);
	}
	
	
	

}
