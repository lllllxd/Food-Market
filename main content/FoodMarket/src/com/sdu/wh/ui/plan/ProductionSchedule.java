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
		textArea.setText("\n     制定生产计划\n     Create production schedule");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//放center的panel
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
		scrollPane.getViewport().setOpaque(false);//将JScrollPane设置为透明
		scrollPane.setOpaque(false);//将中间的viewport设置为透明
		scrollPane.setViewportView(mTable);//装载表格
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		panel0.add(scrollPane,BorderLayout.CENTER);
		
		//放center面板底部的panel，确认订单加计划生产日期
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
		String info1="输入产品编号";
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
		String info2="输入生产数量";
		AmountField.setForeground(new Color(2, 81, 103));
		AmountField.setText(info2);
		AmountField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		AmountField.addFocusListener(new MyFocusListener(info2, AmountField));
		panel2.add(AmountField);
		
		JLabel DateLabel = new JLabel("计划生产日期：");
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
		
		JLabel NLabel = new JLabel("年");
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
		
		JLabel YLabel = new JLabel("月");
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
		
		JLabel RLabel = new JLabel("日");
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
		
		//提交生产计划
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
		ConfirmBtn.setText("提交生产计划");
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
		 * 将表格设置为透明，表格同样包括表格本身和其中的内容项
		 * 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		 * 内容项的设置是通过设置渲染器的透明来实现
		 */
		table.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false); 
		//render.setHorizontalAlignment(JLabel.CENTER);  
		//将渲染器设置为透明
		//将这个渲染器设置到table里。
		//这个设置在没有另外专门对column设置的情况下有效
		//若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		//因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		table.setDefaultRenderer(Object.class,render);
		
		//设置显示范围
		Dimension viewSize = new Dimension(); 
        viewSize.width = table.getColumnModel().getTotalColumnWidth(); ; 
        viewSize.height = 10 * table.getRowHeight(); 
        table.setPreferredScrollableViewportSize(viewSize); 
        
        //设置头部透明
        //头部实际上也是一个JTABLE，只有一行而已。
        JTableHeader header = table.getTableHeader();//获取头部 
        header.setPreferredSize(new Dimension(30, 26)); 
        header.setOpaque(false);//设置头部为透明
        header.getTable().setOpaque(false);//设置头部里面的表格透明
        
        /*
         * 头部的表格也像前面的表格设置一样，还需要将里面的单元项设置为透明
         * 因此同样需要对头部单元项进行透明度设置，这里还是用渲染器。
         * 但这里有个问题就是，若将头部渲染器直接像上文一样设置，则它的下面没有横线
         * 因此，我们需要一个专用的头部渲染器来手动画横线
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
