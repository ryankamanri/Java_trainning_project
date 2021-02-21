package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Model.Account;
import Service.Bank;
import Service.Bank.Login;

public class LoanOut implements ActionListener{
	
	public JFrame iframe;//窗口变量
    public JPanel ip0, ip1;//面板变量
    public JTextField time;
    public JButton confirm;//按钮
    Account account;
	
	public LoanOut(Account account){
		
		this.account = account;
		
		//实例化窗口
        iframe=new JFrame("还款");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法
        
        //实例化按钮
        confirm = new JButton("点击还款");
        
        //实例化文本框
        time = new JTextField(15);
        
        //实例化面板
        ip0 = new JPanel();
        ip1 = new JPanel();
        
        ip0.add(new JLabel("输入还款时间："));
        ip0.add(time);
        ip1.add(confirm);
        
        iframe.add(ip0);
        iframe.add(ip1);

        
        iframe.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
        confirm.addActionListener(this);
       
	}

	public void actionPerformed(ActionEvent e) {
		
		int Time = Integer.valueOf(time.getText());
		int flag;
		
		String cmd=e.getActionCommand();
		if(cmd.equals("点击还款")) {
			try {
				Bank.Login login = new Bank.Login(account);
				flag = login.Repay(Time);
				if(flag == 0) {
					JOptionPane.showMessageDialog(null, "用户不存在！");
					return;
				}
				else if(flag == -1) {
					JOptionPane.showMessageDialog(null, "该账户已挂失,无法操作！");
					return;
				}else if(flag == -2) {
					JOptionPane.showMessageDialog(null, "违规操作！");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "还款成功！");
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
		LoanOut testOut = new LoanOut(null);
	}
	
}
