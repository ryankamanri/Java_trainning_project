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

public class transfer implements ActionListener {

	public JTextField money,people;
    public JFrame iframe;
    public JPanel ip0,ip1,ip2,ip3,ip4;
    public JButton confirm,cancel,exit;
    public JLabel yue;
    private Account account;
    
    public transfer(Account account) {
    	
    	 this.account=account;
    	 iframe=new JFrame("ת��");
    	 
    	 ip0=new JPanel();
    	 ip0.add(new JLabel("�˻�id:"+account.getId()));
    	 
    	 ip1=new JPanel();
    	 yue=new JLabel("�˻����:"+account.getBalance());
    	 ip1.add(yue);
    	 
    	 ip2=new JPanel();
    	 ip2.add(new JLabel("��Ǯ�˻�id:"));
    	 people=new JTextField(20);
    	 ip2.add(people);
    	 
    	 ip3=new JPanel();
    	 ip3.add(new JLabel("ת�˽�"));
    	 money=new JTextField(20);
    	 ip3.add(money);
    	 
    	 confirm=new JButton("ȷ��");
    	 cancel=new JButton("ȡ��");
    	 ip4=new JPanel();
    	 ip4.add(confirm);
    	 ip4.add(cancel);
    	 
    	 iframe.add(ip0);
    	 iframe.add(ip1);
    	 iframe.add(ip2);
    	 iframe.add(ip3);
    	 iframe.add(ip4);
    	 iframe.setLayout(new FlowLayout());
     	 iframe.setVisible(true);
     	 iframe.setBounds(600,300,350,300);
         confirm.addActionListener(this);//�󶨼�����
         cancel.addActionListener(this);
    	 
    	 
    	 
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("ȷ��")) {
			
			String people1,money1;
			people1=people.getText();
			money1=money.getText();
			double mon=Double.parseDouble(money1);
			long rec=Integer.valueOf(people1);
			
			Model.Account.AccountType type;
			type = account.getAccountType();
			try {
				Bank.Login login=new Bank.Login(account);
				int x=login.Transfer(rec,mon);
				if(x==0) {
					JOptionPane.showMessageDialog(null, "����");
					iframe.setVisible(false);
	        		 new transfer(account);
				}
				if(x==-1) {
					JOptionPane.showMessageDialog(null, "ת������С�ڵ���0");
					iframe.setVisible(false);
					new transfer(account);
				}
				if(x==1) {
					JOptionPane.showMessageDialog(null, "ת�˳ɹ�");//����
	        		yue.setText("�˻����:"+account.getBalance());
	        		iframe.setVisible(false);
	        		new transfer(account);
				}
				if(x==-2) {
					JOptionPane.showMessageDialog(null, "���˻��ѹ�ʧ,�޷�����");//����
	        		iframe.setVisible(false);
	        		new transfer(account);
				}
				if(x==-3) {
					JOptionPane.showMessageDialog(null, "�Է��˻�������,ת��ʧ��");//����
	        		iframe.setVisible(false);
	        		new transfer(account);
				}
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			if(type == Model.Account.AccountType.SavingAccount) {
//				if(account.Transfer(rec,mon)==1) {
//					JOptionPane.showMessageDialog(null, "ת��ɹ�");
//					yue.setText("�˻����:"+account.getBalance());
//					iframe.setVisible(false);
//				}
//				else if(account.Transfer(rec,mon)==-1) {
//	        		 JOptionPane.showMessageDialog(null, "ת�����С��0");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//				else {
//	        		 JOptionPane.showMessageDialog(null, "����");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//			}
//			if(type == Model.Account.AccountType.CreditAccount) {
//				CreditAccount t=(CreditAccount)account;
//				if(t.Transfer(rec,mon)==1) {
//					JOptionPane.showMessageDialog(null, "ת��ɹ�");
//					yue.setText("�˻����:"+t.getBalance());
//					iframe.setVisible(false);
//				}
//				else if(t.Transfer(rec,mon)==0){
//	        		 JOptionPane.showMessageDialog(null, "����");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//				else {
//	        		 JOptionPane.showMessageDialog(null, "ת�����С��0");
//	        		 iframe.setVisible(false);
//	        		 new InMoney(account);
//	        	}
//			}
			
			
			
//			try {
//				
////              Test.currentAccount.transfer(Integer.parseInt(money.getText()),other.getText());
//				JOptionPane.showMessageDialog(null, "ת�˳ɹ�");
////              yue.setText("�˻����:"+Test.currentAccount.money);//��������ϵ����
//			}catch (ClassCastException e1)//����ǰ��¼�˻���inmoney�����е��쳣������ת���쳣
//            {
// 
//                JOptionPane.showMessageDialog(null, "�����������ʹ�����������ȷ����");
// 
//            }
//            catch (Exception e1)//
//            {
//                JOptionPane.showMessageDialog(null, e1.getMessage());
//            }
		}
		else iframe.setVisible(false);//����
	}
	public static void main(String[] args) {
		
		transfer Out = new transfer(new Account(3,"123"));

	}
    
}