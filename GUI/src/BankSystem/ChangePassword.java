package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class ChangePassword implements ActionListener{
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0, ip1, ip2, ip3;//������
    public JTextField pastpw, nowpw, comfirmpw;//�����ı���
    public JButton confirm, cancel;//��ť
    
    public ChangePassword(){
    	
    	iframe = new JFrame("�޸�����");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
        
    	ip0 = new JPanel();
    	ip0.add(new JLabel("ԭ���룺"));
    	pastpw = new JTextField(14);
    	ip0.add(pastpw);
    	
    	ip1 = new JPanel();
    	ip1.add(new JLabel("�����룺"));
    	nowpw = new JTextField(14);
    	ip1.add(nowpw);
    	
    	ip2 = new JPanel();
    	ip2.add(new JLabel("ȷ�������룺"));
    	comfirmpw = new JTextField(14);
    	ip2.add(comfirmpw);
    	
    	ip3 = new JPanel();
    	confirm = new JButton("ȷ��");
    	cancel = new JButton("ȡ��");
    	ip3.add(confirm);
    	ip3.add(new JPanel());
    	ip3.add(cancel);
    	ip3.add(new JPanel());
    	
    	iframe.add(ip0);
    	iframe.add(ip1);
    	iframe.add(ip2);
    	iframe.add(ip3);
    	

    	iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 70, 10));//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
        cancel.addActionListener(this);
        
        
    	
    	
    }

	public void actionPerformed(ActionEvent e) {
		
		String cmd=e.getActionCommand();
		if(cmd.equals("ȡ��"))
			iframe.setVisible(false);
		else {//����޸�����ɹ����
			
		}
		
		
	}
	
	public static void main(String[] args)
	{
		ChangePassword cp = new ChangePassword();
	}
	
	

}
