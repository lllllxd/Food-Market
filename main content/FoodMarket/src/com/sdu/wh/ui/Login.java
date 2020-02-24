package com.sdu.wh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sdu.wh.bll.LoginBLL;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.RoundBorder;
import com.sdu.wh.dal.LoginDAL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JInternalFrame {

	private LoginBLL mLoginBLL = new LoginBLL();
	private MainForm mMainForm;
	
	public Login(MainForm mainForm) {
		mMainForm = mainForm;
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 350, 400);
		setMinimumSize(new Dimension(350,400));
		setMaximizable(true);
	    setMaximizable(true);
	    setIconifiable(true);
	    setClosable(true);
	    setResizable(true);
		//contentPane = new JPanel();
		ImagePanel backpanel=new ImagePanel(new BorderLayout());
		getContentPane().add(backpanel);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(3);
		textArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setOpaque(false);
		textArea.setText("\n     登  录\n   Sign  in");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//放center的panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new GridLayout(6,1));
		backpanel.add(panel0, BorderLayout.CENTER);
		
		//排格式的面板
		JPanel pane = new JPanel();
		pane.setBackground(null);
		pane.setOpaque(false);
		pane.setLayout(new FlowLayout());
		panel0.add(pane);
		
		//小面板1
		JPanel panel = new JPanel();
		panel.setBackground(null);
		panel.setOpaque(false);
		panel.setLayout(new FlowLayout());
		panel0.add(panel);
		
		JLabel NameLabel = new JLabel("账号");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(60,25));
		//NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel.add(NameLabel);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBackground(new Color(2, 81, 103));
		nameTextField.setOpaque(false);
		nameTextField.setPreferredSize(new Dimension(140,35));
		nameTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		nameTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel.add(nameTextField);
		
		//小面板2
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2);
		
		JLabel TelLabel = new JLabel("密码");
		TelLabel.setForeground(new Color(2, 81, 103));
		TelLabel.setPreferredSize(new Dimension(60,25));
		panel2.add(TelLabel);
		
		JTextField passwordTextField = new JTextField();
		passwordTextField.setBackground(new Color(2, 81, 103));
		passwordTextField.setOpaque(false);
		passwordTextField.setPreferredSize(new Dimension(140,35));
		passwordTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		passwordTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel2.add(passwordTextField);
		
		//小面板3
		JPanel panel3 = new JPanel();
		panel3.setBackground(null);
		panel3.setOpaque(false);
		panel3.setLayout(new FlowLayout());
		panel0.add(panel3);
		
		//小面板4
		JPanel panel4 = new JPanel();
		panel4.setBackground(null);
		panel4.setOpaque(false);
		panel4.setLayout(new FlowLayout());
		panel0.add(panel4);
		
		JButton AddBtn=new JButton();
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean res = mLoginBLL.UserLogin(nameTextField.getText(), passwordTextField.getText());
				if (res) {
					JOptionPane.showMessageDialog(null, "登录成功！");
					MainForm.sLogined = true;
					mMainForm.changeButton(true);
					mMainForm.Admin.setText("<html><u>" + MainForm.getPersonName() + "<html><u>");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "登录失败！");
				}
			}
		});
		AddBtn.setText("登录");
		AddBtn.setForeground(new Color(2, 81, 103));
		AddBtn.setBackground(new Color(183, 223, 234));
		AddBtn.setOpaque(false);
		AddBtn.setPreferredSize(new Dimension(90,30));
		AddBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		AddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		panel4.add(AddBtn);

	}

}
