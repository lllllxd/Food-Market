package com.sdu.wh.ui.marketing;
import com.sdu.wh.bll.MarketingDepartmentBLL;
import com.sdu.wh.common.*;
import com.sdu.wh.dal.SqlExecute;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * 创建新客户窗口
 * 
 * @author 
 *
 */
public class CreateCustomer extends JInternalFrame {

	private MarketingDepartmentBLL mMarketingBLL = new MarketingDepartmentBLL();
	
	public CreateCustomer() {
		setTitle("\u521B\u5EFA\u5BA2\u6237");
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
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(3);
		textArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setOpaque(false);
		textArea.setText("\n     创建一个新客户\n     Create a NEW customer");
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
		
		JLabel NameLabel = new JLabel("客户名称");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(70,25));
		//NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel.add(NameLabel);
		
		JTextField NametextField = new JTextField();
		NametextField.setBackground(new Color(2, 81, 103));
		NametextField.setOpaque(false);
		NametextField.setPreferredSize(new Dimension(150,35));
		NametextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		NametextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel.add(NametextField);
		
		//小面板2
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2);
		
		JLabel TelLabel = new JLabel("联系方式");
		TelLabel.setForeground(new Color(2, 81, 103));
		TelLabel.setPreferredSize(new Dimension(70,25));
		panel2.add(TelLabel);
		
		JTextField TeltextField = new JTextField();
		TeltextField.setBackground(new Color(2, 81, 103));
		TeltextField.setOpaque(false);
		TeltextField.setPreferredSize(new Dimension(150,35));
		TeltextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		TeltextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel2.add(TeltextField);
		
		//小面板3
		JPanel panel3 = new JPanel();
		panel3.setBackground(null);
		panel3.setOpaque(false);
		panel3.setLayout(new FlowLayout());
		panel0.add(panel3);
		
		JLabel LevelLabel = new JLabel("客户等级");
		LevelLabel.setForeground(new Color(2, 81, 103));
		LevelLabel.setPreferredSize(new Dimension(70,25));
		panel3.add(LevelLabel);
		
		JComboBox<String> LevelComboBox = new JComboBox<String>();
		LevelComboBox.addItem("普通会员");
		LevelComboBox.addItem("白金会员");
		LevelComboBox.addItem("钻石会员");
		//LevelComboBox.setBackground(new Color(2, 81, 103));
		//LevelComboBox.setOpaque(false);
		LevelComboBox.setPreferredSize(new Dimension(150,40));
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		LevelComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		LevelComboBox.setForeground(new Color(2, 81, 103));
		panel3.add(LevelComboBox);
		
		//小面板4
		JPanel panel4 = new JPanel();
		panel4.setBackground(null);
		panel4.setOpaque(false);
		panel4.setLayout(new FlowLayout());
		panel0.add(panel4);
		
		JButton AddBtn=new JButton();
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String no = null;
				no = mMarketingBLL.createCustomer(NametextField.getText(), TeltextField.getText(), LevelComboBox.getSelectedIndex());
				if (no != null) {
					JOptionPane.showMessageDialog(null, "新建客户编号为：" +  no);
				}
				
			}
		});
		AddBtn.setText("确认添加客户");
		AddBtn.setForeground(new Color(2, 81, 103));
		AddBtn.setBackground(new Color(183, 223, 234));
		AddBtn.setOpaque(false);
		AddBtn.setPreferredSize(new Dimension(120,30));
		AddBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		AddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		panel4.add(AddBtn);
		
	}

}
