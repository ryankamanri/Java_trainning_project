package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Account;
import Model.LoansAccount;

public class LoanIn implements ActionListener{
	
	public JFrame iframe;//窗口变量
    public JPanel ip0, ip1, ip2, ip3, ip4, ip5;//面板变量
    public JTextField overInt, normalInt, money, needMoney, time;//单行文本域
    public JButton confirm, cancel;//按钮
    Account account;
    LoansAccount loansAccount;
    
    public LoanIn(Account account){
    	
    	this.account = account;
    	loansAccount = (LoansAccount)this.account;
		double Money = loansAccount.getLoans();
		double over = loansAccount.getOverdueInterest();
		double normal = loansAccount.getBasicInterest();
		double yue = loansAccount.getBalance();
		
    	
    	//实例化窗口
        iframe=new JFrame("贷款");
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
        ip5 = new JPanel();
        
        //实例化文本域
        overInt = new JTextField(20);
        normalInt = new JTextField(20);
        money = new JTextField(20);
        needMoney = new JTextField(20);
        time = new JTextField(20);
      
        ip0.add(new JLabel("利息："+normal));
        ip1.add(new JLabel("逾期利息："+over));
        ip2.add(new JLabel("余额："+yue));
        ip3.add(new JLabel("贷款金额："+Money));
        ip4.add(new JLabel("贷款时长："));
        ip4.add(time);


        
        ip5.add(confirm);
        ip5.add(new JPanel());
        ip5.add(cancel);
        ip5.add(new JPanel());
        ip5.add(new JPanel());
        ip5.add(new JPanel());
        ip5.add(new JPanel());

        
        
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(ip2);
        iframe.add(ip3);
        iframe.add(ip4);
        iframe.add(ip5);
        
        iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
        confirm.addActionListener(this);
        cancel.addActionListener(this);
    	
    }

	public void actionPerformed(ActionEvent e) {
		
		int Time;
		Time = Integer.valueOf(time.getText());
		
//		long ID = account.getId();
//		String Password = account.getPassword();
//		String Name = account.getName();
//		String PersonID = account.getPersonID();
//		String phnum = account.getPhoneNumber();
//		double Balance = account.getBalance();
//		boolean IsLoss = account.isLoss();
		
		
		String cmd=e.getActionCommand();
		if(cmd.equals("取消"))
			iframe.setVisible(false);
		else {
			
			loansAccount.setLoansTime(Time);
			
			JOptionPane.showMessageDialog(null, "贷款成功！");
			iframe.setVisible(false);
		}
		
	}
	
	
	public static void main(String[] args)
	{
		Account account = new Account(11, "123456");
		LoanIn loanIn = new LoanIn(account);
	}
	
	

}
