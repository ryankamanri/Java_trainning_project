package BankSystem;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionListener;

public class registerchoice implements ActionListener {

	 private JFrame frame;//���壻
	 private JButton savingaccount;//��ť
	 private JButton creditaccount;
	 private JButton loanaccount;
	 private JButton exist;
	 private JPanel p0,p1;
	 
	 /*
	  * typeֵ˵����
	  * 0 �����˻�
	  * 1 �����˻�
	  * 2 �����˻�
	  */
	 public int type;
	 
	 public registerchoice() {
		 frame = new JFrame("����ѡ��");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton savingaccount=new JButton("�����˻�");
		 JButton creditaccount=new JButton("�����˻�");
		 JButton loanaccount=new JButton("�����˻�");
		 JButton exist=new JButton("����");
		 p0=new JPanel();
		 p0.add(new JLabel("  ��ѡ���˻�����:      "));
		 p0.setLayout(new FlowLayout());
		 frame.add(p0);
		 p1=new JPanel();
		 p1.add(savingaccount);
		 p1.add(creditaccount);
		 p1.add(loanaccount);
		 p1.add(exist);
		 p1.setLayout(new GridLayout(5,1,20,25));
		 
		 frame.add(p1);
		 //frame.pack();
	     frame.setVisible(true);
	     frame.setLayout(new FlowLayout());
		 frame.setBounds(600,300,350,300);
		 savingaccount.addActionListener(this);
		 creditaccount.addActionListener(this);
		 exist.addActionListener(this);
		 loanaccount.addActionListener(this);
		  
		 
		 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������

		String an=e.getActionCommand();
		if(an.equals("�����˻�")) {
			type = 0;
			OpenAccount oa=new OpenAccount(type);
			frame.setVisible(false);
		}
		else if(an.equals("�����˻�")) {
			type = 1;
			Cardch cardch  = new Cardch();
			frame.setVisible(false);
		}
		
		else if(an.equals("�����˻�")) {
			type = 2;
			OpenAccount oa=new OpenAccount(type);
			frame.setVisible(false);
		}
		else if(an.equals("����")) {
			frame.setVisible(false);
			 Main main=new Main();
			//frame.dispose();
        }
		
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		registerchoice rcRegisterchoice=new registerchoice();
	}

}
