package BankSystem;
import javax.swing.*;

import Model.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class LoanFrame implements ActionListener{
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1, ip2;//������
    public JButton InLoan, OutLoan, Inmoney, Outmoney, Cancle;//��ť
    Account account;
    
    public LoanFrame(Account account){//���췽��
    	
    	this.account = account;
    	
    	//ʵ��������
        iframe=new JFrame("������");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
        
        //ʵ������ť
        InLoan = new JButton("����");
        OutLoan = new JButton("����");
        Inmoney = new JButton("���");
        Outmoney = new JButton("ȡ��");
        Cancle = new JButton("�˳�");
        
        //ʵ�������
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
        
        iframe.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 30));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
        InLoan.addActionListener(this);
        OutLoan.addActionListener(this);
        Cancle.addActionListener(this);
       
    }

	public void actionPerformed(ActionEvent e) {

		String cmd=e.getActionCommand();
		if(cmd.equals("����")) {
			LoanInFrame loanInFrame = new LoanInFrame(account);
		} else if(cmd.equals("����")){
			try {
				LoanOutFrame loanOutFrame = new LoanOutFrame(account);
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		else if(cmd.equals("ȡ��")) {
			
		}
		else if(cmd.equals("���")) {
			
		}
		else {
			iframe.setVisible(false);
			JOptionPane.showMessageDialog(null, "����");
			iframe.dispose();
		}
	}
	
	public static void main(String[] args)
	{
		Account account = new Account(11, "123456");
		LoanFrame loanFrame = new LoanFrame(account);
	}
	
	
	

}
