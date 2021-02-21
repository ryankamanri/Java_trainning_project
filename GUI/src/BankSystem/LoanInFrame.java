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
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1, ip2, ip3, ip4;//������
    public JTextField house, nation, security, stock;//�����ı���
    public JButton confirm, cancel;//��ť
    Account account;
	
	public LoanInFrame(Account account){
		
		this.account = account;
		
		//ʵ��������
        iframe=new JFrame("��Ѻ");
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
        
        //ʵ�����ı���
        house = new JTextField(20);
        nation = new JTextField(20);
        security = new JTextField(20);
        stock = new JTextField(20);
      
        ip0.add(new JLabel("��Ѻ��������"));
        ip0.add(house);
        ip1.add(new JLabel("��Ѻ��ծ����"));
        ip1.add(nation);
        ip2.add(new JLabel("��Ѻ֤ȯ����"));
        ip2.add(security);
        ip3.add(new JLabel("��Ѻ��Ʊ����"));
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
        
        iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 10));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
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
		if(cmd.equals("ȷ��")) {	
			try {
				
				Bank.Login login = new Bank.Login(account);
				login.Mortgage(house_num, nation_num, security_num, stock_num);
				LoanIn loanIn = new LoanIn(account);
				iframe.setVisible(false);

			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
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
