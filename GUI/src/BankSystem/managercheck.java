 package BankSystem;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import SQLConnection.SQLConnection;
public class managercheck implements ActionListener {

	private JFrame frame;//窗体；
	 private JButton ca;//按钮
	 private JButton cj;
	 private JButton ct;
	 private JButton cd;
	 private JButton cp;
	 private JButton exist;
	 private JTextArea caccount,cmoney,crediting,loanmoney,ranking;
	 private JPanel p0,p1,p2,p3,p4,p5;//
	//frame = new JFrame("请选择账户类型");
	
	 public managercheck() {
		 frame = new JFrame("管理员功能选择");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JButton ca=new JButton("账户信息");
		 JButton cj=new JButton("账户金额");
		 JButton ct=new JButton("透支金额");
		 JButton cd=new JButton("贷款金额");
		 JButton cp=new JButton("资产排名");
		 JButton exist=new JButton("返回主页面");
		 p0=new JPanel();
		 p0.add(new JLabel("\t账户信息："));
		 caccount=new JTextArea(18,50);
		 caccount.setLineWrap(true);
		 p0.add(caccount);
		 
		 p1=new JPanel();
		 p1.add(new JLabel("\t账户金额："));
		 cmoney=new JTextArea(18,50);
		 cmoney.setLineWrap(true);
		 p1.add(cmoney);
		 
		 p2=new JPanel();
		 p2.add(new JLabel("\t透支金额："));
		 crediting=new JTextArea(18,50);
		 crediting.setLineWrap(true);
		 p2.add(crediting);
		 
		 p3=new JPanel();
		 p3.add(new JLabel("\t贷款金额："));
		 loanmoney=new JTextArea(18,50);
		 loanmoney.setLineWrap(true);
		 p3.add(loanmoney);
		 
		 p4=new JPanel();
		 p4.add(new JLabel("\t资产排名："));
		 ranking=new JTextArea(18,50);
		 ranking.setLineWrap(true);
		 p4.add(ranking);
		 
		 p5=new JPanel();
		 p5.add(ca);
		 p5.add(cj);
		 p5.add(ct);
		 p5.add(cd);
		 p5.add(cp);
		 p5.add(exist);
		 p5.setLayout(new FlowLayout());
		 frame.add(p0); 
		 frame.add(p1); 
		 frame.add(p2);
		 frame.add(p3);
		 frame.add(p4);
		 frame.add(p5);
		 
		 frame.pack();
	     frame.setVisible(true);
	     frame.setLayout(new FlowLayout());
		 frame.setBounds(600,300,700,500);
		 ca.addActionListener(this);
		 cj.addActionListener(this);
		 ct.addActionListener(this);
		 cd.addActionListener(this);
		 cp.addActionListener(this);
		 exist.addActionListener(this);
		 p1.setVisible(false);
		 p2.setVisible(false);
		 p3.setVisible(false);
		 p4.setVisible(false);
		 
		 
		 
		 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		 
		String an=e.getActionCommand();
		if(an.equals("账户信息")) {
			p0.setVisible(true);
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			Administrator t=new Administrator();
		
			SQLConnection sqlc = new SQLConnection();
	        Account[] a = sqlc.SelectAllAccount();
	        for(int i=0;i<sqlc.Number();i++) {
	            System.out.println(a[i]);
	            caccount.append(a[i].toString());
	            caccount.append("\r\n");
//	            caccount.append("\r\n");
	        }
	        
			//caccount.setText("aaa:"+t.Query());
//			SavingAccountLogin savinglogin=new SavingAccountLogin();
//			frame.setVisible(false);
		}
		else if(an.equals("账户金额")) {
			p0.setVisible(false);
			p1.setVisible(true);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
 		    Administrator c=new Administrator();
//			double temp = a.CountBalance();
  		    Account account=new Account();
 		   
			 double balance = 0;
		     SQLConnection sqlc = new SQLConnection();
		     Account[] a = sqlc.SelectAllAccount();
		     for(int i=0;i<sqlc.Number();i++) {
		          balance+=a[i].getBalance();
		          cmoney.append("账户 "+a[i].getId()+" 的金额为："+String.valueOf(balance));
		          cmoney.append("\r\n");
//		          cmoney.append("\r\n");
		        }
		     

		}
		else if(an.equals("透支金额")) {
			p0.setVisible(false);
			p1.setVisible(false);
			p2.setVisible(true);
			p3.setVisible(false);
			p4.setVisible(false);
 
			 double creditLine = 0;
		     SQLConnection sqlc = new SQLConnection();
		     Account[] a = sqlc.SelectAllAccount();
		     for(int i=0;i<sqlc.Number();i++) {
		       creditLine+=((CreditAccount) a[i]).getCreditLine();
		       crediting.append("账户 "+a[i].getId()+" 的透支金额为："+String.valueOf(creditLine));
		       crediting.append("\r\n");
//		       crediting.append("\r\n");
		     }
			
		}
		else if(an.equals("贷款金额")) {
			p0.setVisible(false);
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(true);
			p4.setVisible(false);
 
			double loans=0;;
	        SQLConnection sqlc = new SQLConnection();
	        Account[] a = sqlc.SelectAllAccount();
	        for(int i=0;i<sqlc.Number();i++) {
	            loans+=((LoansAccount) a[i]).getLoans();
	            loanmoney.append("账户 "+a[i]+" 的贷款金额为："+String.valueOf(loans));
	            loanmoney.append("\r\n");
//	            loanmoney.append("\r\n");
	        }
			
		}
		else if(an.equals("资产排名")) {
			p0.setVisible(false);
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(true);
			Administrator d = new Administrator();
			SQLConnection sqlc = new SQLConnection();
	        Account[] a = sqlc.SelectAllAccount();
	        for(int i=0;i<sqlc.Number();i++) {
	            for(int j=0;j<sqlc.Number();j++) {
	                if(a[j].getBalance()<a[j+1].getBalance()) {
	                    Account b=a[j];
	                    a[j]=a[j+1];
	                    a[j+1]=b;
	                }
	            }
	        }
	        for(int i=0;i<sqlc.Number();i++) {
	            System.out.println(a[i].getId()+" "+a[i].getBalance());
	            ranking.append("账户的资产排名为：");
	            ranking.append(String.valueOf(a[i].getId()+" "+a[i].getBalance()));
	            ranking.append("\r\n");
//	            ranking.append("\r\n");
	        }

	    }
			
			 
		
		
		else if(an.equals("返回主页面")) {
			frame.setVisible(false);
			Main main = new Main();
        }
		
	}

	private Object Administrator() {
		// TODO 自动生成的方法存根
		return null;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		managercheck mK=new managercheck();
		
	}

}
