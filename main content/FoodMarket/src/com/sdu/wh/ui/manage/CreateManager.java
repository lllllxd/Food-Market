package com.sdu.wh.ui.manage;

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

import com.sdu.wh.bll.ManageDepartmentBLL;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.RoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 创建管理者窗口类
 * @author Gale
 *
 */
public class CreateManager extends JInternalFrame {

	private ManageDepartmentBLL mManageDepartmentBLL = new ManageDepartmentBLL();
	
	public CreateManager() {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 600, 400);
		setMaximizable(true);
	    setMaximizable(true);
	    setIconifiable(true);
	    setClosable(true);
	    setResizable(true);
		//contentPane = new JPanel();
		ImagePanel backpanel=new ImagePanel(new BorderLayout());
		getContentPane().add(backpanel);
		
		JTextArea txtrCreateA = new JTextArea();
		txtrCreateA.setEditable(false);
		txtrCreateA.setRows(3);
		txtrCreateA.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		txtrCreateA.setForeground(new Color(255, 255, 255));
		txtrCreateA.setOpaque(false);
		txtrCreateA.setText("\r\n     \u521B\u5EFA\u65B0\u7BA1\u7406\u5458\r\n     Create a NEW manager");
		backpanel.add(txtrCreateA, BorderLayout.NORTH);
		
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
		
		JLabel NameLabel = new JLabel("\u5458\u5DE5\u8D26\u53F7");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(70,25));
		//NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel.add(NameLabel);
		
		JTextField idTextField = new JTextField();
		idTextField.setBackground(new Color(2, 81, 103));
		idTextField.setOpaque(false);
		idTextField.setPreferredSize(new Dimension(150,35));
		idTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		idTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel.add(idTextField);
		
		//小面板2
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2);
		
		JLabel TelLabel = new JLabel("\u540D\u79F0");
		TelLabel.setForeground(new Color(2, 81, 103));
		TelLabel.setPreferredSize(new Dimension(70,25));
		panel2.add(TelLabel);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBackground(new Color(2, 81, 103));
		nameTextField.setOpaque(false);
		nameTextField.setPreferredSize(new Dimension(150,35));
		nameTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		nameTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel2.add(nameTextField);
		
		//小面板
		JPanel panel5 = new JPanel();
		panel5.setBackground(null);
		panel5.setOpaque(false);
		panel5.setLayout(new FlowLayout());
		panel0.add(panel5);
		
		JLabel CodeLabel = new JLabel("\u5BC6\u7801");
		CodeLabel.setForeground(new Color(2, 81, 103));
		CodeLabel.setPreferredSize(new Dimension(70,25));
		//NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel5.add(CodeLabel);
		
		JTextField passwordTextField = new JTextField();
		passwordTextField.setBackground(new Color(2, 81, 103));
		passwordTextField.setOpaque(false);
		passwordTextField.setPreferredSize(new Dimension(150,35));
		passwordTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		passwordTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel5.add(passwordTextField);
		
		//小面板3
		JPanel panel3 = new JPanel();
		panel3.setBackground(null);
		panel3.setOpaque(false);
		panel3.setLayout(new FlowLayout());
		panel0.add(panel3);
		
		JLabel LevelLabel = new JLabel("\u804C\u4F4D");
		LevelLabel.setForeground(new Color(2, 81, 103));
		LevelLabel.setPreferredSize(new Dimension(70,25));
		panel3.add(LevelLabel);
		
		JComboBox<String> levelComboBox = new JComboBox<String>();
		levelComboBox.addItem("系统管理员");
		levelComboBox.addItem("企业管理员");
		levelComboBox.addItem("销售部");
		levelComboBox.addItem("财务部");
		levelComboBox.addItem("生产计划科");
		levelComboBox.addItem("生产车间");
		levelComboBox.addItem("成品库");
		//LevelComboBox.setBackground(new Color(2, 81, 103));
		//LevelComboBox.setOpaque(false);
		levelComboBox.setPreferredSize(new Dimension(140,40));
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		levelComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		levelComboBox.setForeground(new Color(2, 81, 103));
		panel3.add(levelComboBox);
		
		//小面板4
		JPanel panel4 = new JPanel();
		panel4.setBackground(null);
		panel4.setOpaque(false);
		panel4.setLayout(new FlowLayout());
		panel0.add(panel4);
		
		JButton AddBtn=new JButton();
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean result = mManageDepartmentBLL.createManager(idTextField.getText(), nameTextField.getText(), passwordTextField.getText(), levelComboBox.getSelectedIndex());
				if (result == true) {
					JOptionPane.showMessageDialog(null, "创建成功！");
				} else {
					JOptionPane.showMessageDialog(null, "创建失败！");
				}
			}
		});
		AddBtn.setText("添加管理员");
		AddBtn.setForeground(new Color(2, 81, 103));
		AddBtn.setBackground(new Color(183, 223, 234));
		AddBtn.setOpaque(false);
		AddBtn.setPreferredSize(new Dimension(120,30));
		AddBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		AddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		panel4.add(AddBtn);
	}
}
