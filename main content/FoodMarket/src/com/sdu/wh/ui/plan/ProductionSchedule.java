package com.sdu.wh.ui.plan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.sdu.wh.bll.PlanDepartmentBLL;
import com.sdu.wh.common.HeaderCellRenderer;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.common.MyFocusListener;
import com.sdu.wh.common.RoundBorder;

public class ProductionSchedule extends JInternalFrame {

	private PlanDepartmentBLL mPlanDepartmentBLL = new PlanDepartmentBLL();
	
	private JTable mTable;
	
	public ProductionSchedule() {
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
		textArea.setText("\n     �ƶ������ƻ�\n     Create production schedule");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//��center��panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new BorderLayout(0,0));
		backpanel.add(panel0, BorderLayout.CENTER);

		mTable=new JTable();
		mPlanDepartmentBLL.refreshTable(mTable, mPlanDepartmentBLL.mTemp);
		//mTable=refreshRefundTable(mTable,mData);
		initialTable(mTable);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		scrollPane.setOpaque(false);//���м��viewport����Ϊ͸��
		scrollPane.setViewportView(mTable);//װ�ر��
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		panel0.add(scrollPane,BorderLayout.CENTER);
		
		//��center���ײ���panel��ȷ�϶����Ӽƻ���������
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2,BorderLayout.SOUTH);
		
		JTextField PNoField = new JTextField();
		PNoField.setBackground(new Color(2, 81, 103));
		PNoField.setOpaque(false);
		PNoField.setPreferredSize(new Dimension(90,25));
		PNoField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		String info1="�����Ʒ���";
		PNoField.setForeground(new Color(2, 81, 103));
		PNoField.setText(info1);
		PNoField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		PNoField.addFocusListener(new MyFocusListener(info1, PNoField));
		panel2.add(PNoField);
		
		JTextField AmountField = new JTextField();
		AmountField.setBackground(new Color(2, 81, 103));
		AmountField.setOpaque(false);
		AmountField.setPreferredSize(new Dimension(90,25));
		AmountField.setBorder(new RoundBorder(new Color(2, 81, 103)));
		String info2="������������";
		AmountField.setForeground(new Color(2, 81, 103));
		AmountField.setText(info2);
		AmountField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		AmountField.addFocusListener(new MyFocusListener(info2, AmountField));
		panel2.add(AmountField);
		
		JLabel DateLabel = new JLabel("�ƻ��������ڣ�");
		DateLabel.setForeground(new Color(2, 81, 103));
		DateLabel.setPreferredSize(new Dimension(78,25));
		DateLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(DateLabel);
		
		JComboBox<String> NComboBox = new JComboBox<String>();
		NComboBox.addItem("2018");
		NComboBox.addItem("2019");
		NComboBox.setPreferredSize(new Dimension(75,25));
		NComboBox.setSelectedIndex(0);
		NComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		NComboBox.setForeground(new Color(2, 81, 103));
		panel2.add(NComboBox);
		
		JLabel NLabel = new JLabel("��");
		NLabel.setForeground(new Color(2, 81, 103));
		NLabel.setPreferredSize(new Dimension(15,25));
		NLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
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
		YComboBox.setPreferredSize(new Dimension(60,25));
		YComboBox.setSelectedIndex(0);
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		YComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		YComboBox.setForeground(new Color(2, 81, 103));
		panel2.add(YComboBox);
		
		JLabel YLabel = new JLabel("��");
		YLabel.setForeground(new Color(2, 81, 103));
		YLabel.setPreferredSize(new Dimension(15,25));
		YLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
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
		RComboBox.setPreferredSize(new Dimension(60,25));
		RComboBox.setSelectedIndex(0);
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		RComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		RComboBox.setForeground(new Color(2, 81, 103));
		panel2.add(RComboBox);
		
		JLabel RLabel = new JLabel("��");
		RLabel.setForeground(new Color(2, 81, 103));
		RLabel.setPreferredSize(new Dimension(15,25));
		RLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		panel2.add(RLabel);
		
		JButton AddBtn=new JButton();
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//get PNO,PAmount,PDate
				String PNO,PAmount,PDate;
				PNO=PNoField.getText();
				PAmount=AmountField.getText();
				PDate=NComboBox.getSelectedItem().toString()+"-"+YComboBox.getSelectedItem().toString()+"-"+RComboBox.getSelectedItem().toString();
				
				mPlanDepartmentBLL.mTemp = mPlanDepartmentBLL.addProductionSchedule(PNO,PAmount,PDate,mPlanDepartmentBLL.mTemp);
				mPlanDepartmentBLL.refreshTable(mTable, mPlanDepartmentBLL.mTemp);
				
				initialTable(mTable);
			}

			
		});
		AddBtn.setText("Add");
		AddBtn.setForeground(new Color(2, 81, 103));
		AddBtn.setBackground(new Color(183, 223, 234));
		AddBtn.setOpaque(false);
		AddBtn.setPreferredSize(new Dimension(55,25));
		AddBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		AddBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel2.add(AddBtn);
		
		//�ύ�����ƻ�
		JPanel panel3 = new JPanel();
		panel3.setBackground(null);
		panel3.setOpaque(false);
		panel3.setLayout(new FlowLayout());
		backpanel.add(panel3, BorderLayout.SOUTH);
		
		JButton ConfirmBtn=new JButton();
		ConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mPlanDepartmentBLL.createPlan();
			}
		});
		ConfirmBtn.setText("�ύ�����ƻ�");
		ConfirmBtn.setForeground(new Color(2, 81, 103));
		ConfirmBtn.setBackground(new Color(183, 223, 234));
		ConfirmBtn.setOpaque(false);
		ConfirmBtn.setPreferredSize(new Dimension(125,25));
		ConfirmBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		ConfirmBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		panel3.add(ConfirmBtn);
		
	}
	private void initialTable(JTable table) {
		// TODO Auto-generated method stub
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
        
        table.getColumnModel().getColumn(0).setPreferredWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        //table.getColumnModel().getColumn(3).setPreferredWidth(140);
        //table.getColumnModel().getColumn(4).setPreferredWidth(90);
		
	}
}
