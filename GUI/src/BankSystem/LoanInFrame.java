package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Model.Account;
import Model.LoansAccount;
import Service.Bank;
import Service.Bank.Login;

public class LoanInFrame implements ActionListener{
	
	public JFrame iframe;//窗口变量
    public JPanel ip0, ip1, ip2, ip3, ip4;//面板变量
    public JTextField house, nation, security, stock;//单行文本域
    public JButton confirm, cancel;//按钮
    Account account;
	
	public LoanInFrame(Account account){
		
		this.account = account;
		
		//实例化窗口
        iframe=new JFrame("抵押");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法
        
        //实例化按钮
        confirm = new JButton("确认");
        cancel = new JButton("取消");
        
        //实例化面板
        ip0 = new JPanel();
        ip1 = new JPanel();
        ip2 = new JPanel();
        ip3 = new JPanel();
        ip4 = new JPanel();
        
        //实例化文本域
        house = new JTextField(20);
        nation = new JTextField(20);
        security = new JTextField(20);
        stock = new JTextField(20);
      
        ip0.add(new JLabel("抵押房产数："));
        ip0.add(house);
        ip1.add(new JLabel("抵押国债数："));
        ip1.add(nation);
        ip2.add(new JLabel("抵押证券数："));
        ip2.add(security);
        ip3.add(new JLabel("抵押股票数："));
        ip3.add(stock);
        
        ip4.add(confirm);
        ip4.add(new JPanel());
        ip4.add(cancel);
        ip4.add(new JPanel());
        ip4.add(new JPanel());
        ip4.add(new JPanel());
        ip4.add(new JPanel());

        
        
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        iframe.add(ip3);
        iframe.add(ip4);
        
        iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
        confirm.addActionListener(this);
        cancel.addActionListener(this);
        
	}

	public void actionPerformed(ActionEvent e) {
		
		int house_num, nation_num, security_num, stock_num;
		house_num = Integer.valueOf(house.getText());
		nation_num = Integer.valueOf(nation.getText());
		security_num = Integer.valueOf(security.getText());
		stock_num = Integer.valueOf(stock.getText());
		
		
		String cmd=e.getActionCommand();
		if(cmd.equals("确认")) {	
			try {
				
				Bank.Login login = new Bank.Login(account);
				login.Mortgage(house_num, nation_num, security_num, stock_num);
				LoanIn loanIn = new LoanIn(account);
				iframe.setVisible(false);

			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}
		else {
			iframe.setVisible(false);
		}
				
	}
	
	public static void main(String[] args)
	{
		LoanInFrame testIn = new LoanInFrame(null);
	}
	
	

}
