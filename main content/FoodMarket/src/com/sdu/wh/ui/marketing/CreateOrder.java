package com.sdu.wh.ui.marketing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.sdu.wh.bll.MarketingDepartmentBLL;
import com.sdu.wh.common.CheckBoxEditor;
import com.sdu.wh.common.CheckBoxRenderer;
import com.sdu.wh.common.HeaderCellRenderer;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.common.MyFocusListener;
import com.sdu.wh.common.RoundBorder;
import com.sdu.wh.ui.MainForm;

import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * �����¶�������
 * 
 * @author Gale 
 *
 */
public class CreateOrder extends JInternalFrame {

	private MarketingDepartmentBLL mMarketingDepartmentBLL = new MarketingDepartmentBLL();
	private JTable mTable = new JTable();
	
	public CreateOrder() {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 650, 400);
		setMinimumSize(new Dimension(650,400));
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
		textArea.setText("\n     �����¶���\n     Create a NEW oder");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//��center��panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new BorderLayout(0,0));
		backpanel.add(panel0, BorderLayout.CENTER);
		
		//�ͻ���š��ͻ����ơ���ѯ�򡢲�ѯ��ť����ѯ����������
		JPanel panel1 = new JPanel();
		panel1.setBackground(null);
		panel1.setOpaque(false);
		panel1.setLayout(new BorderLayout(0,0));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel11 = new JPanel();
		panel11.setBackground(null);
		panel11.setOpaque(false);
		panel11.setLayout(new FlowLayout());
		panel1.add(panel11,BorderLayout.EAST);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBackground(new Color(2, 81, 103));
		nameTextField.setOpaque(false);
		nameTextField.setPreferredSize(new Dimension(110,30));
		nameTextField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		String info1="������ͻ����";
		nameTextField.setForeground(new Color(2, 81, 103));
		nameTextField.setText("1");
		nameTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		nameTextField.addFocusListener(new MyFocusListener(info1, nameTextField));
		panel11.add(nameTextField);
		
		JLabel NameLabel = new JLabel(" �ͻ�����");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(50,25));
		NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel11.add(NameLabel);
		
		/*String Name="�˴���ʾ�ͻ�����";
		JLabel nameLabel = new JLabel("<html><u>"+Name+"<html><u>");
		nameLabel.setForeground(UIManager.getColor("List.dropLineColor"));
		nameLabel.setPreferredSize(new Dimension(90,25));
		nameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel11.add(nameLabel);
		*/
		
		/*JLabel Gap=new JLabel();
		Gap.setForeground(new Color(2, 81, 103));
		Gap.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		Gap.setPreferredSize(new Dimension(15,10));
		Gap.setText(" |");
		panel1.add(Gap);
		String info2="�������ѯ����";*/
		
		mTable = new JTable() {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
				
			}
		};
		//װmTable��ScrollPane
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		panel0.add(scrollPane, BorderLayout.CENTER);*/
		
		
		/*
		 * ���ｫJScrollPane����Ϊ͸���ġ�
		 * JScrollPane�����ü������֣�һ���Ǳ�����һ�����м��viewport������ͷ����headerview��
		 * ��ߵ�rowview���ұߺ�����Ĺ�������
		 * ����һ��Ĭ�����jtable��JScrollPane�������ٰ���JScrollPane�Լ��ı�Ե��ͷ���ı�����м��JTABLE
		 * ��ֻ��JScrollPane����Ϊ͸������ֻ�Ǳ�Ե͸�����м��viewport��Ҳ�������ɱ��ĵط�����ͷ����Ȼ��͸��
		 * �����Ҫ�����Ƕ�����Ϊ͸����������Ҫע����ǣ�ͷ��Ҫ���ֶ����jtable��ͷ����Ȼ����ȡ��ͷ����������Ϊ͸��
		 * ���򣬻ᱨ��ָ�����
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		scrollPane.setOpaque(false);//���м��viewport����Ϊ͸��
		scrollPane.setViewportView(mTable);//װ�ر��
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		panel0.add(scrollPane,BorderLayout.CENTER);

		//��center���ײ���panel��ȷ�϶��������������
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2,BorderLayout.SOUTH);
		
		JLabel DateLabel = new JLabel("�������");
		DateLabel.setForeground(new Color(2, 81, 103));
		DateLabel.setPreferredSize(new Dimension(50,25));
		DateLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(DateLabel);
		
		JComboBox<String> NComboBox = new JComboBox<String>();
		NComboBox.addItem("2018");
		NComboBox.addItem("2019");
		NComboBox.addItem("2020");
		NComboBox.setPreferredSize(new Dimension(75,25));
		NComboBox.setSelectedIndex(0);
		NComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		NComboBox.setForeground(new Color(2, 81, 103));
		panel2.add(NComboBox);
		
		JLabel NLabel = new JLabel("��");
		NLabel.setForeground(new Color(2, 81, 103));
		NLabel.setPreferredSize(new Dimension(20,25));
		NLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(NLabel);
		
		JComboBox<String> YComboBox = new JComboBox<String>();
		YComboBox.addItem("1");
		YComboBox.addItem("2");
		YComboBox.addItem("3");
		YComboBox.addItem("4");
		YComboBox.addItem("5");
		YComboBox.addItem("6");
		YComboBox.addItem("7");
		YComboBox.addItem("8");
		YComboBox.addItem("9");
		YComboBox.addItem("10");
		YComboBox.addItem("11");
		YComboBox.addItem("12");
		YComboBox.setPreferredSize(new Dimension(65,25));
		YComboBox.setSelectedIndex(0);
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		YComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		YComboBox.setForeground(new Color(2, 81, 103));
		panel2.add(YComboBox);
		
		JLabel YLabel = new JLabel("��");
		YLabel.setForeground(new Color(2, 81, 103));
		YLabel.setPreferredSize(new Dimension(20,25));
		YLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(YLabel);
		
		JComboBox<String> RComboBox = new JComboBox<String>();
		RComboBox.addItem("1");
		RComboBox.addItem("2");
		RComboBox.addItem("3");
		RComboBox.addItem("4");
		RComboBox.addItem("5");
		RComboBox.addItem("6");
		RComboBox.addItem("7");
		RComboBox.addItem("8");
		RComboBox.addItem("9");
		RComboBox.addItem("10");
		RComboBox.addItem("11");
		RComboBox.addItem("12");
		RComboBox.addItem("13");
		RComboBox.addItem("14");
		RComboBox.addItem("15");
		RComboBox.addItem("16");
		RComboBox.addItem("17");
		RComboBox.addItem("18");
		RComboBox.addItem("19");
		RComboBox.addItem("20");
		RComboBox.addItem("21");
		RComboBox.addItem("22");
		RComboBox.addItem("23");
		RComboBox.addItem("24");
		RComboBox.addItem("25");
		RComboBox.addItem("26");
		RComboBox.addItem("27");
		RComboBox.addItem("28");
		RComboBox.addItem("29");
		RComboBox.addItem("30");
		RComboBox.addItem("31");
		RComboBox.setPreferredSize(new Dimension(65,25));
		RComboBox.setSelectedIndex(0);
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		RComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		RComboBox.setForeground(new Color(2, 81, 103));
		panel2.add(RComboBox);
		
		JLabel RLabel = new JLabel("��");
		RLabel.setForeground(new Color(2, 81, 103));
		RLabel.setPreferredSize(new Dimension(20,25));
		RLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(RLabel);
		
		JButton ConfirmBtn=new JButton();
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				Calendar date = Calendar.getInstance();
				date.set(Integer.parseInt(NComboBox.getSelectedItem().toString()), 
						Integer.parseInt(YComboBox.getSelectedItem().toString()), 
						Integer.parseInt(RComboBox.getSelectedItem().toString()));
				mMarketingDepartmentBLL.getOrderForm().setTakingDate(date);
				mMarketingDepartmentBLL.getOrderForm().setCustomerNo(nameTextField.getText());
				mMarketingDepartmentBLL.getOrderForm().setPersonId(MainForm.getPersonId());
				mMarketingDepartmentBLL.getOrderForm().setPersonName(MainForm.getPersonName());
				mMarketingDepartmentBLL.getOrderForm().setDate();
				mMarketingDepartmentBLL.setOrderFormData(mTable);
				
				ConfirmBill cb = new ConfirmBill(mMarketingDepartmentBLL, CreateOrder.this);
				CreateOrder.this.setVisible(false);
				MainForm.desktop.add(cb);
				
			}
		});
		ConfirmBtn.setText("ȷ�϶���");
		ConfirmBtn.setForeground(new Color(2, 81, 103));
		ConfirmBtn.setBackground(new Color(183, 223, 234));
		ConfirmBtn.setOpaque(false);
		ConfirmBtn.setPreferredSize(new Dimension(95,25));
		ConfirmBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		ConfirmBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(ConfirmBtn);
		
		mMarketingDepartmentBLL.refreashDate(mTable);
        initialTable(mTable);
	}
	
	private void initialTable(JTable table) {
		
		table.setRowHeight(25); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); 
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		table.setIntercellSpacing(new Dimension(0, 0)); 
		table.setRowSelectionAllowed(false);
		
		/*
		 * ���������Ϊ͸�������ͬ���������������е�������
		 * ���������������Ϊ͸��Ҳû���ã�Ӧ�ý����е�������Ҳ����Ϊ͸��
		 * �������������ͨ��������Ⱦ����͸����ʵ��
		 */
		table.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false); 
		//render.setHorizontalAlignment(JLabel.CENTER);  
		//����Ⱦ������Ϊ͸��
		//�������Ⱦ�����õ�table�
		//���������û������ר�Ŷ�column���õ��������Ч
		//�����ĳ��column����ָ������Ⱦ������������column������������render��Ⱦ��
		//���Ϊ�˱�֤͸����������column����ָ������Ⱦ������ô�ڶ������Ⱦ����ҲӦ������͸��
		table.setDefaultRenderer(Object.class,render);
		
		//������ʾ��Χ
		Dimension viewSize = new Dimension(); 
        viewSize.width = table.getColumnModel().getTotalColumnWidth(); ; 
        viewSize.height = 10 * table.getRowHeight(); 
        table.setPreferredScrollableViewportSize(viewSize); 
        
        //����ͷ��͸��
        //ͷ��ʵ����Ҳ��һ��JTABLE��ֻ��һ�ж��ѡ�
        JTableHeader header = table.getTableHeader();//��ȡͷ�� 
        header.setPreferredSize(new Dimension(30, 26)); 
        header.setOpaque(false);//����ͷ��Ϊ͸��
        header.getTable().setOpaque(false);//����ͷ������ı��͸��
        
        /*
         * ͷ���ı��Ҳ��ǰ��ı������һ��������Ҫ������ĵ�Ԫ������Ϊ͸��
         * ���ͬ����Ҫ��ͷ����Ԫ�����͸�������ã����ﻹ������Ⱦ����
         * �������и�������ǣ�����ͷ����Ⱦ��ֱ��������һ�����ã�����������û�к���
         * ��ˣ�������Ҫһ��ר�õ�ͷ����Ⱦ�����ֶ�������
         */
        header.setDefaultRenderer(new HeaderCellRenderer());
        TableCellRenderer headerRenderer = header.getDefaultRenderer(); 
        if (headerRenderer instanceof JLabel) 
        {
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER); 
            ((JLabel) headerRenderer).setOpaque(false);	
        }
        
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(190);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}
 
	public void mouseEntered(MouseEvent arg0) {
	}
 
	public void mouseExited(MouseEvent arg0) {
	}
 
	public void mousePressed(MouseEvent arg0) {
	}
 
	public void mouseReleased(MouseEvent arg0) {
	}

	
}
