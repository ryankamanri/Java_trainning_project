package BankSystem;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Query implements ActionListener{
	
	public JFrame iframe;//���ڱ���
    public JPanel ip0,ip1,ip2,ip3;//������
    public JTextArea inquryresult;//�����ı���
    public JButton confirm, ret;//��ť
    
    
    public Query() {
    	
    	iframe = new JFrame("��ˮ");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ô��ڹرյķ���
    	
    	ip0 = new JPanel();
    	ip0.add(new JLabel("�˻�����"));//��Ӹ��˻����˻���
    	
    	ip1 = new JPanel();
    	ip1.add(new JLabel("��"));//��Ӹ��˻������
    	
    	ip2 = new JPanel();
    	ip2.add(new JTextArea(10, 30));
    	
    	ip3 = new JPanel();
    	confirm = new JButton("��ѯ");
    	ret = new JButton("����");
    	ip3.add(confirm);
    	ip3.add(new JPanel());
    	ip3.add(new JPanel());
    	ip3.add(ret);
    	
    	iframe.add(ip0);
    	iframe.add(ip1);
    	iframe.add(ip2);
    	iframe.add(ip3);
    	
    	iframe.setLayout(new FlowLayout());//���ô���Ϊ��ʽ���ֹ���
        iframe.setVisible(true);//���ڿɼ�
        iframe.setBounds(600,300,350,300);//���ڴ�С
        
        //�󶨼�����
        confirm.addActionListener(this);
        ret.addActionListener(this);
    	
    }

	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("����"))
			iframe.setVisible(false);
		else {//�����ʾ��ˮ��¼�����
			
		}
			
		
	}
	
	public static void main(String[] args)
	{
		Query query = new Query();
	}
	
	

}
