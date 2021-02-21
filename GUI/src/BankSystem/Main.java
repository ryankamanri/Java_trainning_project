package BankSystem;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
 
public class Main implements ActionListener {

	 private JFrame frame;//���壻
	 private JButton register;//��ť
	 private JButton login;
	 private JButton manager;
	 private JButton exist;
	 private JButton guashi;
	 private JPanel p0,p1;
	//frame = new JFrame("��ѡ���˻�����");
	
	 public Main() {
		 frame = new JFrame("�������С������̵�������");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton register=new JButton("����");
		 JButton login=new JButton("��¼");
		 JButton manager=new JButton("����Ա");
		 JButton exist=new JButton("�˳�ϵͳ");
		 JButton guashi=new JButton("��ʧ");
		 p0=new JPanel();
		 p0.add(new JLabel("          ��ѡ����:               "));
		 frame.add(p0);
		 p1=new JPanel();
		 p1.add(manager);
		 p1.add(login);
		 p1.add(register);
		 p1.add(guashi);
		 p1.add(exist);
		 
		 p1.setLayout(new GridLayout(5,1,20,25));
		 frame.add(p1);
		 frame.add(new JPanel());
		 frame.add(new JPanel());
		 frame.add(new JPanel());
		 //frame.pack();
	     frame.setVisible(true);
	     frame.setLayout(new FlowLayout());
		 frame.setBounds(600,300,350,300);
		 register.addActionListener(this);
		 manager.addActionListener(this);
		 exist.addActionListener(this);
		 guashi.addActionListener(this);
		 login.addActionListener(this);
		 
		 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������

		String an=e.getActionCommand();
		if(an.equals("����")) {
			registerchoice rc=new registerchoice();
			frame.setVisible(false);
		}
		else if(an.equals("��¼")) {
			Login login=new Login();
			frame.setVisible(false);
		}
		
		else if(an.equals("����Ա")) {
			managerLogin ml=new managerLogin();
			frame.setVisible(false);
		}
		else if(an.equals("��ʧ")) {
			Guash guash=new Guash();
			frame.setVisible(false);
		}
		else if(an.equals("�˳�ϵͳ")) {
			frame.setVisible(false);
			JOptionPane.showMessageDialog(null, "��ȡ��");
			frame.dispose();
        }
		
	}

	public static void main(String[] args) {
	
		Main in=new Main();

	}

}
