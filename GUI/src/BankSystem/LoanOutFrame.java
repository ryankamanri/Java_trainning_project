package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import Model.Account;
import Model.LoansAccount;
import Service.Bank;

public class LoanOutFrame implements ActionListener{
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1;//������
    public JTextField loanTime;//�����ı���
    public JButton confirm, cancel;//��ť
    public JLabel time;
    Bank.Login login;
	
	public LoanOutFrame(Account account) throws SQLException {
		
		login = new Bank.Login(account);
		
		//ʵ��������
        iframe=new JFrame("����");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
        //ʵ������ť
        confirm = new JButton("ȷ��");
        cancel = new JButton("ȡ��");
        //ʵ�������
        ip0 = new JPanel();
        ip1 = new JPanel();
        //ʵ������ǩ
        time = new JLabel("�������㻹���ʱ�䣺");
        ip0.add(time);
        //ʵ�����ı���
        loanTime = new JTextField(20);
        ip1.add(loanTime);
        
        iframe.add(ip0);
        iframe.add(ip1);
        iframe.add(confirm);
        iframe.add(cancel);
        
        iframe.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 20));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
        confirm.addActionListener(this);
        cancel.addActionListener(this);

	}
	
	public void actionPerformed(ActionEvent e) {
		
		int Time = Integer.valueOf(time.getText());
		int flag;
		
		String cmd=e.getActionCommand();
		if(cmd.equals("�������")) {
			try {
				flag = login.Repay(Time);
				if(flag == 0) {
					JOptionPane.showMessageDialog(null, "�û������ڣ�");
					return;
				}
				else if(flag == -1) {
					JOptionPane.showMessageDialog(null, "���˻��ѹ�ʧ,�޷�������");
					return;
				}else if(flag == -2) {
					JOptionPane.showMessageDialog(null, "Υ�������");
					return;
				}else {
					JOptionPane.showMessageDialog(null, "����ɹ���");
					iframe.setVisible(false);
				}
				
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
			
		
	}
	
	public static void main(String[] args) throws SQLException
	{
		LoanOutFrame testOut = new LoanOutFrame(null);
	}
	

}
