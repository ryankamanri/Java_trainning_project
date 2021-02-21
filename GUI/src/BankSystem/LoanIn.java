package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Model.Account;
import Model.LoansAccount;

public class LoanIn implements ActionListener{
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1, ip2, ip3, ip4, ip5;//������
    public JTextField overInt, normalInt, money, needMoney, time;//�����ı���
    public JButton confirm, cancel;//��ť
    Account account;
    LoansAccount loansAccount;
    
    public LoanIn(Account account){
    	
    	this.account = account;
    	loansAccount = (LoansAccount)this.account;
		double Money = loansAccount.getLoans();
		double over = loansAccount.getOverdueInterest();
		double normal = loansAccount.getBasicInterest();
		double yue = loansAccount.getBalance();
		
    	
    	//ʵ��������
        iframe=new JFrame("����");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
        
        //ʵ������ť
        confirm = new JButton("ȷ��");
        cancel = new JButton("ȡ��");
        
        //ʵ�������
        ip0 = new JPanel();
        ip1 = new JPanel();
        ip2 = new JPanel();
        ip3 = new JPanel();
        ip4 = new JPanel();
        ip5 = new JPanel();
        
        //ʵ�����ı���
        overInt = new JTextField(20);
        normalInt = new JTextField(20);
        money = new JTextField(20);
        needMoney = new JTextField(20);
        time = new JTextField(20);
      
        ip0.add(new JLabel("��Ϣ��"+normal));
        ip1.add(new JLabel("������Ϣ��"+over));
        ip2.add(new JLabel("��"+yue));
        ip3.add(new JLabel("�����"+Money));
        ip4.add(new JLabel("����ʱ����"));
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
        
        iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
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
		if(cmd.equals("ȡ��"))
			iframe.setVisible(false);
		else {
			
			loansAccount.setLoansTime(Time);
			
			JOptionPane.showMessageDialog(null, "����ɹ���");
			iframe.setVisible(false);
		}
		
	}
	
	
	public static void main(String[] args)
	{
		Account account = new Account(11, "123456");
		LoanIn loanIn = new LoanIn(account);
	}
	
	

}
