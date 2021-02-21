package BankSystem;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Query implements ActionListener{
	
	public JFrame iframe;//窗口变量
    public JPanel ip0,ip1,ip2,ip3;//面板变量
    public JTextArea inquryresult;//单行文本域
    public JButton confirm, ret;//按钮
    
    
    public Query() {
    	
    	iframe = new JFrame("流水");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法
    	
    	ip0 = new JPanel();
    	ip0.add(new JLabel("账户名："));//添加该账户的账户名
    	
    	ip1 = new JPanel();
    	ip1.add(new JLabel("余额："));//添加该账户的余额
    	
    	ip2 = new JPanel();
    	ip2.add(new JTextArea(10, 30));
    	
    	ip3 = new JPanel();
    	confirm = new JButton("查询");
    	ret = new JButton("返回");
    	ip3.add(confirm);
    	ip3.add(new JPanel());
    	ip3.add(new JPanel());
    	ip3.add(ret);
    	
    	iframe.add(ip0);
    	iframe.add(ip1);
    	iframe.add(ip2);
    	iframe.add(ip3);
    	
    	iframe.setLayout(new FlowLayout());//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
        confirm.addActionListener(this);
        ret.addActionListener(this);
    	
    }

	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("返回"))
			iframe.setVisible(false);
		else {//添加显示流水记录的语句
			
		}
			
		
	}
	
	public static void main(String[] args)
	{
		Query query = new Query();
	}
	
	

}
