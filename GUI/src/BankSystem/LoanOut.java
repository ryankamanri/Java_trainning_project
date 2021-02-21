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
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1;//������
    public JTextField time;
    public JButton confirm;//��ť
    Account account;
	
	public LoanOut(Account account){
		
		this.account = account;
		
		//ʵ��������
        iframe=new JFrame("����");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
        
        //ʵ������ť
        confirm = new JButton("�������");
        
        //ʵ�����ı���
        time = new JTextField(15);
        
        //ʵ�������
        ip0 = new JPanel();
        ip1 = new JPanel();
        
        ip0.add(new JLabel("���뻹��ʱ�䣺"));
        ip0.add(time);
        ip1.add(confirm);
        
        iframe.add(ip0);
        iframe.add(ip1);

        
        iframe.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
        confirm.addActionListener(this);
       
	}

	public void actionPerformed(ActionEvent e) {
		
		int Time = Integer.valueOf(time.getText());
		int flag;
		
		String cmd=e.getActionCommand();
		if(cmd.equals("�������")) {
			try {
				Bank.Login login = new Bank.Login(account);
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

	public static void main(String[] args)
	{
		LoanOut testOut = new LoanOut(null);
	}
	
}
