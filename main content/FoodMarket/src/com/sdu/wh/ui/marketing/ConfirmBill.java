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
 * 确认订单窗口类
 * 
 * @author Gale
 *
 */
public class ConfirmBill extends JInternalFrame {

	private MarketingDepartmentBLL mMarketingDepartmentBLL;
	private CreateOrder mCreateOrder;
	
	private String mCustomerNo="客户编号0000001";
	private String mCustomerName="客户名称";
	private String mPersonId="管理员编号0000001";
	private String mPersonName="负责人姓名";
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
		textArea.setText("\n     订货单\n  Oder Form");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//放center的panel
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
		
		//客户编号、名称；提货日期；
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
		
		JLabel Date0Label = new JLabel("提货日期");
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
		//装table的滚动板
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//将JScrollPane设置为透明
		scrollPane.setOpaque(false);//将中间的viewport设置为透明
		scrollPane.setViewportView(mTable);//装载表格
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		panel0.add(scrollPane,BorderLayout.CENTER);
		
		//底部panel
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
		
		//日期，负责人姓名、编号
		JLabel CreateDate = new JLabel("订单创建日期：");
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
		
		JLabel MngLabel = new JLabel("负责人：");
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
		
		//最底部我再想想、提交订单按钮
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
		BackBtn.setText("返回上层");
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
				 * 调用函数创建新订单
				 */
				mMarketingDepartmentBLL.createOrderForm();
				
				mCreateOrder.setVisible(true);
				dispose();
			}
		});
		ConfirmBtn.setText("提交订单");
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
		dm.setDataVector(mData,new String[]{"  产品编号  " , "  产品名称  " , "  产品价格  " , "  选购数量  " , "单项总价"});
		mTable = new JTable(dm);
		mTable.setRowHeight(25); 
		mTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); 
		mTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		mTable.setIntercellSpacing(new Dimension(0, 0)); 
		mTable.setRowSelectionAllowed(false);
		
		/*
		 * 将表格设置为透明，表格同样包括表格本身和其中的内容项
		 * 仅仅将表格本身设置为透明也没有用，应该将其中的内容项也设置为透明
		 * 内容项的设置是通过设置渲染器的透明来实现
		 */
		mTable.setOpaque(false);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer(); 
		render.setOpaque(false); 
		//render.setHorizontalAlignment(JLabel.CENTER);  
		//将渲染器设置为透明
		//将这个渲染器设置到mTable里。
		//这个设置在没有另外专门对column设置的情况下有效
		//若你对某个column特殊指定了渲染器，则对于这个column，它将不调用render渲染器
		//因此为了保证透明，如果你对column额外指定了渲染器，那么在额外的渲染器里也应该设置透明
		mTable.setDefaultRenderer(Object.class,render);
		
		//设置显示范围
		Dimension viewSize = new Dimension(); 
        viewSize.width = mTable.getColumnModel().getTotalColumnWidth(); ; 
        viewSize.height = 10 * mTable.getRowHeight(); 
        mTable.setPreferredScrollableViewportSize(viewSize); 
        
        //设置头部透明
        //头部实际上也是一个JTABLE，只有一行而已。
        JTableHeader header = mTable.getTableHeader();//获取头部 
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
