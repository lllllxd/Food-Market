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
 * 创建新订单窗口
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
		textArea.setText("\n     创建新订单\n     Create a NEW oder");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//放center的panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new BorderLayout(0,0));
		backpanel.add(panel0, BorderLayout.CENTER);
		
		//客户编号、客户名称、查询框、查询按钮、查询条件下拉框
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
		String info1="请输入客户编号";
		nameTextField.setForeground(new Color(2, 81, 103));
		nameTextField.setText("1");
		nameTextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 10));
		nameTextField.addFocusListener(new MyFocusListener(info1, nameTextField));
		panel11.add(nameTextField);
		
		JLabel NameLabel = new JLabel(" 客户名称");
		NameLabel.setForeground(new Color(2, 81, 103));
		NameLabel.setPreferredSize(new Dimension(50,25));
		NameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel11.add(NameLabel);
		
		/*String Name="此处显示客户名称";
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
		String info2="请输入查询条件";*/
		
		mTable = new JTable() {
			public void tableChanged(TableModelEvent e) {
				super.tableChanged(e);
				repaint();
				
			}
		};
		//装mTable的ScrollPane
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		panel0.add(scrollPane, BorderLayout.CENTER);*/
		
		
		/*
		 * 这里将JScrollPane设置为透明的。
		 * JScrollPane包括好几个部分，一个是本身，另一个是中间的viewport，还有头部的headerview，
		 * 左边的rowview，右边和下面的滚动条。
		 * 对于一个默认添加jtable的JScrollPane，它至少包括JScrollPane自己的边缘、头部的标题和中间的JTABLE
		 * 若只将JScrollPane设置为透明，则只是边缘透明，中间的viewport（也就是容纳表格的地方）和头部依然不透明
		 * 因此需要将它们都设置为透明，但是需要注意的是，头部要先手动添加jtable的头部，然后再取出头部，再设置为透明
		 * 否则，会报空指针错误
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//将JScrollPane设置为透明
		scrollPane.setOpaque(false);//将中间的viewport设置为透明
		scrollPane.setViewportView(mTable);//装载表格
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		panel0.add(scrollPane,BorderLayout.CENTER);

		//放center面板底部的panel，确认订单加提提货日期
		JPanel panel2 = new JPanel();
		panel2.setBackground(null);
		panel2.setOpaque(false);
		panel2.setLayout(new FlowLayout());
		panel0.add(panel2,BorderLayout.SOUTH);
		
		JLabel DateLabel = new JLabel("提货日期");
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
		
		JLabel NLabel = new JLabel("年");
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
		
		JLabel YLabel = new JLabel("月");
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
		
		JLabel RLabel = new JLabel("日");
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
		ConfirmBtn.setText("确认订单");
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
