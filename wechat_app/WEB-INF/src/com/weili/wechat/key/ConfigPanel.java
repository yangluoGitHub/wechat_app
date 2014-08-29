package com.weili.wechat.key;


import java.io.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;



public class ConfigPanel extends JFrame {
	private JTextField userNameField;
	private JPasswordField pwdField1;
	private JPasswordField pwdField2;
	private JComboBox DBList;
	
	private String userName = "";
	
	private String pwd = "";
	
	private String DbType= "";
	
	public ConfigPanel(){
		this.setTitle("ConfigPanel");
		this.setSize(400, 300);
		this.setLocation(300, 200);
		
		JPanel panel = new JPanel();
		
		panel.add(new JLabel("Choose System database Type:"));
		String petStrings[] = {"P","V"};
		DBList = new JComboBox(petStrings);
		panel.add(DBList);
		
		panel.add(new JLabel("端数据库                "));
		
		panel.add(new JLabel("Input database user name:"));
		userNameField = new JTextField("",20);
		panel.add(userNameField);
		
		panel.add(new JLabel("Input database password1:"));
		pwdField1 = new JPasswordField("",20);
		panel.add(pwdField1);
		
		panel.add(new JLabel("Input database password2:"));
		pwdField2 = new JPasswordField("",20);
		panel.add(pwdField2);
		
		JPanel panel2 = new JPanel();
		JButton btn1 = new JButton("保存");
		btn1.addActionListener(EventHandler.create(ActionListener.class, this, "saveHandler"));
		btn1.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					saveHandler();
				}
			}
		});
		panel2.add(btn1);
		
		JButton btn2 = new JButton("退出");
		btn2.addActionListener(EventHandler.create(ActionListener.class, this, "quit"));
		btn2.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					quit();
				}
			}
		});
		panel2.add(btn2);
		
		this.add(panel,BorderLayout.CENTER);
		this.add(panel2,BorderLayout.AFTER_LAST_LINE);
		
	}
	
	public void saveHandler(){
		String filename = null;
		this.userName = this.userNameField.getText();
		this.pwd = new String(this.pwdField1.getPassword())+new String(this.pwdField2.getPassword());
		this.DbType=this.DBList.getSelectedItem().toString();
		//FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
		
		JFileChooser chooser = new JFileChooser();
		//chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("."));

		int result = chooser.showSaveDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			filename = chooser.getSelectedFile().getPath();
		}else{
			JOptionPane.showMessageDialog(this,"选择保存文件出错","错误",JOptionPane.ERROR_MESSAGE);  
			System.exit(-1);	
		}
		
		String enUserName = ProxoolUtil.encryptData(this.userName);
		if(enUserName==null){
			JOptionPane.showMessageDialog(this,"加密数据出错","错误",JOptionPane.ERROR_MESSAGE);  
			System.exit(-1);	
		}
		
		String enPwd = ProxoolUtil.encryptData(this.pwd);
		if(enPwd==null){
			JOptionPane.showMessageDialog(this,"加密数据出错","错误",JOptionPane.ERROR_MESSAGE);  
			System.exit(-1);	
		}
		
		try{
			ProxoolUtil.replaceFileData(filename,enUserName,enPwd,DbType);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"保存数据到文件出错","错误",JOptionPane.ERROR_MESSAGE);  
			System.exit(-1);
		}
		
		JOptionPane.showMessageDialog(this,"保存数据成功","成功",JOptionPane.INFORMATION_MESSAGE);  
	}
	
	public void quit(){
		System.exit(0);
	}
	
	public static void main(String[] args) throws Exception{
		ConfigPanel cfg = new ConfigPanel();
		cfg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cfg.setVisible(true);
	}

}
