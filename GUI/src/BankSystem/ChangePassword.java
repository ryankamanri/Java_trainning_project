package BankSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class ChangePassword implements ActionListener{
	
	public JFrame iframe;//窗口变量
    public JPanel ip0, ip1, ip2, ip3;//面板变量
    public JTextField pastpw, nowpw, comfirmpw;//单行文本域
    public JButton confirm, cancel;//按钮
    
    public ChangePassword(){
    	
    	iframe = new JFrame("修改密码");
        iframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口关闭的方法
        
    	ip0 = new JPanel();
    	ip0.add(new JLabel("原密码："));
    	pastpw = new JTextField(14);
    	ip0.add(pastpw);
    	
    	ip1 = new JPanel();
    	ip1.add(new JLabel("新密码："));
    	nowpw = new JTextField(14);
    	ip1.add(nowpw);
    	
    	ip2 = new JPanel();
    	ip2.add(new JLabel("确认新密码："));
    	comfirmpw = new JTextField(14);
    	ip2.add(comfirmpw);
    	
    	ip3 = new JPanel();
    	confirm = new JButton("确认");
    	cancel = new JButton("取消");
    	ip3.add(confirm);
    	ip3.add(new JPanel());
    	ip3.add(cancel);
    	ip3.add(new JPanel());
    	
    	iframe.add(ip0);
    	iframe.add(ip1);
    	iframe.add(ip2);
    	iframe.add(ip3);
    	

    	iframe.setLayout(new FlowLayout(FlowLayout.RIGHT, 70, 10));//设置窗口为流式布局管理
        iframe.setVisible(true);//窗口可见
        iframe.setBounds(600,300,350,300);//窗口大小
        
        //绑定监听器
        cancel.addActionListener(this);
        
        
    	
    	
    }

	public void actionPerformed(ActionEvent e) {
		
		String cmd=e.getActionCommand();
		if(cmd.equals("取消"))
			iframe.setVisible(false);
		else {//添加修改密码成功语句
			
		}
		
		
	}
	
	public static void main(String[] args)
	{
		ChangePassword cp = new ChangePassword();
	}
	
	

}
