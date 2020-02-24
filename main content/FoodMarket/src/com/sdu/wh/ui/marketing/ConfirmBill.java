package com.sdu.wh.ui.marketing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.sdu.wh.bll.MarketingDepartmentBLL;
import com.sdu.wh.common.CheckBoxEditor;
import com.sdu.wh.common.CheckBoxRenderer;
import com.sdu.wh.common.HeaderCellRenderer;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.RoundBorder;

/**
 * ȷ�϶���������
 * 
 * @author Gale
 *
 */
public class ConfirmBill extends JInternalFrame {

	private MarketingDepartmentBLL mMarketingDepartmentBLL;
	private CreateOrder mCreateOrder;
	
	private String mCustomerNo="�ͻ����0000001";
	private String mCustomerName="�ͻ�����";
	private String mPersonId="����Ա���0000001";
	private String mPersonName="����������";
	private String mDate;
	private Object[][] mData;
	
	private JTable mTable;

	public ConfirmBill() {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 450, 400);
		setMinimumSize(new Dimension(450,400));
		setMaximizable(true);
	    setMaximizable(true);
	    setIconifiable(true);
	    setClosable(true);
	    setResizable(true);
		initial();
	}

	public ConfirmBill(MarketingDepartmentBLL marketingDepartmentBLL, CreateOrder createOrder) {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 450, 400);
		setMinimumSize(new Dimension(450,400));
		setMaximizable(true);
	    setMaximizable(true);
	    setIconifiable(true);
	    setClosable(true);
	    setResizable(true);
		
	    mCreateOrder = createOrder;
		mMarketingDepartmentBLL = marketingDepartmentBLL;
		Calendar date = mMarketingDepartmentBLL.getOrderForm().getTakingDate();
		mDate = date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DATE);
		mCustomerNo = mMarketingDepartmentBLL.getOrderForm().getCustomerNo();
		mCustomerName = mMarketingDepartmentBLL.getOrderForm().getCustomerName();
		mPersonId = mMarketingDepartmentBLL.getOrderForm().getPersonId();
		mPersonName= mMarketingDepartmentBLL.getOrderForm().getPersonName();
		mData = mMarketingDepartmentBLL.getOrderForm().getData();

		initial();
	}

	private void initial() {
		ImagePanel backpanel=new ImagePanel(new BorderLayout());
		getContentPane().add(backpanel);

		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setRows(3);
		textArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setOpaque(false);
		textArea.setText("\n     ������\n  Oder Form");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//��center��panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new BorderLayout(0,0));
		backpanel.add(panel0, BorderLayout.CENTER);
		
		// TODO Auto-generated method stub
		JPanel panel1 = new JPanel();
		panel1.setBackground(null);
		panel1.setOpaque(false);
		panel1.setLayout(new BorderLayout(0,0));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel1_1 = new JPanel();
		panel1_1.setBackground(null);
		panel1_1.setOpaque(false);
		panel1_1.setLayout(new FlowLayout());
		panel1.add(panel1_1,BorderLayout.WEST);
		
		JPanel panel1_2 = new JPanel();
		panel1_2.setBackground(null);
		panel1_2.setOpaque(false);
		panel1_2.setLayout(new FlowLayout());
		panel1.add(panel1_2,BorderLayout.EAST);
		
		//�ͻ���š����ƣ�������ڣ�
		JLabel NoLabel = new JLabel("<html><u>"+mCustomerNo+"<html><u>");
		NoLabel.setForeground(new Color(2, 81, 103));
		NoLabel.setPreferredSize(new Dimension(100,25));
		NoLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel1_1.add(NoLabel);
		
		JLabel NameLabel = new JLabel("<html><u>"+mCustomerName+"<html><u>");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(70,25));
		NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel1_1.add(NameLabel);
		
		JLabel Date0Label = new JLabel("�������");
		Date0Label.setForeground(new Color(2, 81, 103));
		Date0Label.setPreferredSize(new Dimension(50,25));
		Date0Label.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel1_2.add(Date0Label);
		
		JLabel DateLabel = new JLabel(mDate);
		DateLabel.setForeground(new Color(2, 81, 103));
		DateLabel.setPreferredSize(new Dimension(90,25));
		DateLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel1_2.add(DateLabel);
		
		//JTable
		initialTable();
		//װtable�Ĺ�����
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		scrollPane.setOpaque(false);//���м��viewport����Ϊ͸��
		scrollPane.setViewportView(mTable);//װ�ر��
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		panel0.add(scrollPane,BorderLayout.CENTER);
		
		//�ײ�panel
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new BorderLayout(0,0));
		panel0.add(panel2,BorderLayout.SOUTH);
		
		
		JPanel panel2_1 = new JPanel();
		panel2_1.setBackground(null);
		panel2_1.setOpaque(false);
		panel2_1.setLayout(new FlowLayout());
		panel2.add(panel2_1,BorderLayout.WEST);
		
		JPanel panel2_2 = new JPanel();
		panel2_2.setBackground(null);
		panel2_2.setOpaque(false);
		panel2_2.setLayout(new FlowLayout());
		panel2.add(panel2_2,BorderLayout.EAST);
		
		//���ڣ����������������
		JLabel CreateDate = new JLabel("�����������ڣ�");
		CreateDate.setForeground(new Color(2, 81, 103));
		CreateDate.setPreferredSize(new Dimension(75,25));
		CreateDate.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		panel2_1.add(CreateDate);
		
		String today;
		Calendar now = Calendar.getInstance();
		today=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+"-"+now.get(Calendar.DAY_OF_MONTH);
		
		JLabel todayLabel = new JLabel(today);
		todayLabel.setForeground(new Color(2, 81, 103));
		todayLabel.setPreferredSize(new Dimension(80,25));
		todayLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		panel2_1.add(todayLabel);
		
		JLabel MngLabel = new JLabel("�����ˣ�");
		MngLabel.setForeground(new Color(2, 81, 103));
		MngLabel.setPreferredSize(new Dimension(50,25));
		MngLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		panel2_2.add(MngLabel);
		
		JLabel MngNOLabel = new JLabel(mPersonId);
		MngNOLabel.setForeground(new Color(2, 81, 103));
		MngNOLabel.setPreferredSize(new Dimension(95,25));
		MngNOLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		panel2_2.add(MngNOLabel);
		
		JLabel MngName = new JLabel(mPersonName);
		MngName.setForeground(new Color(2, 81, 103));
		MngName.setPreferredSize(new Dimension(60,25));
		MngName.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		panel2_2.add(MngName);
		
		//��ײ��������롢�ύ������ť
		JPanel panel3 = new JPanel();
		panel3.setBackground(null);
		panel3.setOpaque(false);
		panel3.setLayout(new FlowLayout());
		backpanel.add(panel3,BorderLayout.SOUTH);
		
		JButton BackBtn=new JButton();
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mCreateOrder.setVisible(true);
				dispose();
			}
		});
		BackBtn.setText("�����ϲ�");
		BackBtn.setForeground(new Color(2, 81, 103));
		BackBtn.setBackground(new Color(183, 223, 234));
		BackBtn.setOpaque(false);
		BackBtn.setPreferredSize(new Dimension(95,25));
		BackBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		BackBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel3.add(BackBtn);
		
		JButton ConfirmBtn=new JButton();
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * ���ú��������¶���
				 */
				mMarketingDepartmentBLL.createOrderForm();
				
				mCreateOrder.setVisible(true);
				dispose();
			}
		});
		ConfirmBtn.setText("�ύ����");
		ConfirmBtn.setForeground(new Color(2, 81, 103));
		ConfirmBtn.setBackground(new Color(183, 223, 234));
		ConfirmBtn.setOpaque(false);
		ConfirmBtn.setPreferredSize(new Dimension(95,25));
		ConfirmBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		ConfirmBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel3.add(ConfirmBtn);
		
	}

	private void initialTable() {

		DefaultTableModel dm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dm.setDataVector(mData,new String[]{"  ��Ʒ���  " , "  ��Ʒ����  " , "  ��Ʒ�۸�  " , "  ѡ������  " , "�����ܼ�"});
		mTable = new JTable(dm);
		mTable.setRowHeight(25); 
		mTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); 
		mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		mTable.setIntercellSpacing(new Dimension(0, 0)); 
		mTable.setRowSelectionAllowed(false);
		
		/*
		 * ���������Ϊ͸�������ͬ���������������е�������
		 * ���������������Ϊ͸��Ҳû���ã�Ӧ�ý����е�������Ҳ����Ϊ͸��
		 * �������������ͨ��������Ⱦ����͸����ʵ��
		 */
		mTable.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false); 
		//render.setHorizontalAlignment(JLabel.CENTER);  
		//����Ⱦ������Ϊ͸��
		//�������Ⱦ�����õ�mTable�
		//���������û������ר�Ŷ�column���õ��������Ч
		//�����ĳ��column����ָ������Ⱦ������������column������������render��Ⱦ��
		//���Ϊ�˱�֤͸����������column����ָ������Ⱦ������ô�ڶ������Ⱦ����ҲӦ������͸��
		mTable.setDefaultRenderer(Object.class,render);
		
		//������ʾ��Χ
		Dimension viewSize = new Dimension(); 
        viewSize.width = mTable.getColumnModel().getTotalColumnWidth(); ; 
        viewSize.height = 10 * mTable.getRowHeight(); 
        mTable.setPreferredScrollableViewportSize(viewSize); 
        
        //����ͷ��͸��
        //ͷ��ʵ����Ҳ��һ��JTABLE��ֻ��һ�ж��ѡ�
        JTableHeader header = mTable.getTableHeader();//��ȡͷ�� 
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
        
        
        mTable.getColumnModel().getColumn(0).setPreferredWidth(90);
        mTable.getColumnModel().getColumn(1).setPreferredWidth(170);
        mTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        mTable.getColumnModel().getColumn(3).setPreferredWidth(90);
	}
	
	@Override
	public void doDefaultCloseAction() {
	    mCreateOrder.setVisible(true);
	    super.doDefaultCloseAction();
	}
	
}
