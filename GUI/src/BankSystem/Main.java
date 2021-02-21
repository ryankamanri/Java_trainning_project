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

	 private JFrame frame;//窗体；
	 private JButton register;//按钮
	 private JButton login;
	 private JButton manager;
	 private JButton exist;
	 private JButton guashi;
	 private JPanel p0,p1;
	//frame = new JFrame("请选择账户类型");
	
	 public Main() {
		 frame = new JFrame("传世银行——即刻第六分行");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton register=new JButton("开户");
		 JButton login=new JButton("登录");
		 JButton manager=new JButton("管理员");
		 JButton exist=new JButton("退出系统");
		 JButton guashi=new JButton("挂失");
		 p0=new JPanel();
		 p0.add(new JLabel("          请选择功能:               "));
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
		// TODO 自动生成的方法存根

		String an=e.getActionCommand();
		if(an.equals("开户")) {
			registerchoice rc=new registerchoice();
			frame.setVisible(false);
		}
		else if(an.equals("登录")) {
			Login login=new Login();
			frame.setVisible(false);
		}
		
		else if(an.equals("管理员")) {
			managerLogin ml=new managerLogin();
			frame.setVisible(false);
		}
		else if(an.equals("挂失")) {
			Guash guash=new Guash();
			frame.setVisible(false);
		}
		else if(an.equals("退出系统")) {
			frame.setVisible(false);
			JOptionPane.showMessageDialog(null, "请取卡");
			frame.dispose();
        }
		
	}

	public static void main(String[] args) {
	
		Main in=new Main();

	}

}
