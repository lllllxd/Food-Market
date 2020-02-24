package com.sdu.wh.ui.marketing;

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

import org.eclipse.swt.widgets.Scale;

import com.sdu.wh.bll.MarketingDepartmentBLL;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.RoundBorder;
import com.sdu.wh.ui.MainForm;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 指定销售策略窗口类
 * 
 * @author Gale
 *
 */
public class SalesStrategy extends JInternalFrame {

	private MarketingDepartmentBLL mMarketingDepartmentBLL = new MarketingDepartmentBLL();
	
	public SalesStrategy() {
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
		textArea.setText("\n    制定营销策略\n   Sale   Strategy");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//放center的panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new GridLayout(5,1));
		backpanel.add(panel0, BorderLayout.CENTER);
		
		//排格式的面板
		JPanel pane = new JPanel();
		pane.setBackground(null);
		pane.setOpaque(false);
		pane.setLayout(new FlowLayout());
		panel0.add(pane);
		
		//小面板1
		JPanel panel3 = new JPanel();
		panel3.setBackground(null);
		panel3.setOpaque(false);
		panel3.setLayout(new FlowLayout());
		panel0.add(panel3);
		
		JLabel LevelLabel = new JLabel("客户等级");
		LevelLabel.setForeground(new Color(2, 81, 103));
		LevelLabel.setPreferredSize(new Dimension(60,25));
		panel3.add(LevelLabel);
		
		JComboBox<String> LevelComboBox = new JComboBox<String>();
		LevelComboBox.addItem("普通会员");
		LevelComboBox.addItem("白金会员");
		LevelComboBox.addItem("钻石会员");
		//LevelComboBox.setBackground(new Color(2, 81, 103));
		//LevelComboBox.setOpaque(false);
		LevelComboBox.setPreferredSize(new Dimension(120,40));
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		LevelComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		LevelComboBox.setForeground(new Color(2, 81, 103));
		panel3.add(LevelComboBox);
		
		//小面板3
		JPanel panel = new JPanel();
		panel.setBackground(null);
		panel.setOpaque(false);
		panel.setLayout(new FlowLayout());
		panel0.add(panel);
		
		JLabel NameLabel = new JLabel("商品折扣");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(65,25));
		//NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		panel.add(NameLabel);
		
		JTextField DiscountTextField = new JTextField();
		DiscountTextField.setBackground(new Color(2, 81, 103));
		DiscountTextField.setOpaque(false);
		DiscountTextField.setPreferredSize(new Dimension(120,30));
		DiscountTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		DiscountTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel.add(DiscountTextField);
		
		//小面板2
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2);
		
		JLabel TelLabel = new JLabel("预付款比例");
		TelLabel.setForeground(new Color(2, 81, 103));
		TelLabel.setPreferredSize(new Dimension(73,25));
		panel2.add(TelLabel);
		
		JTextField ScaleTextField = new JTextField();
		ScaleTextField.setBackground(new Color(2, 81, 103));
		ScaleTextField.setOpaque(false);
		ScaleTextField.setPreferredSize(new Dimension(120,30));
		ScaleTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		ScaleTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel2.add(ScaleTextField);
		
		//小面板4
		JPanel panel4 = new JPanel();
		panel4.setBackground(null);
		panel4.setOpaque(false);
		panel4.setLayout(new FlowLayout());
		panel0.add(panel4);
		
		JButton AddBtn=new JButton();
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean result = false;
				try {
					result = mMarketingDepartmentBLL.changeSalesStrategy(
							LevelComboBox.getSelectedIndex() + 1, 
							DiscountTextField.getText(), 
							ScaleTextField.getText(),
							Integer.parseInt(MainForm.getPersonId()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				} else {
					JOptionPane.showMessageDialog(null, "修改失败！");
				}
			}
		});
		AddBtn.setText("\u786E\u8BA4\u4FEE\u6539");
		AddBtn.setForeground(new Color(2, 81, 103));
		AddBtn.setBackground(new Color(183, 223, 234));
		AddBtn.setOpaque(false);
		AddBtn.setPreferredSize(new Dimension(90,30));
		AddBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		AddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		panel4.add(AddBtn);

	}
}
