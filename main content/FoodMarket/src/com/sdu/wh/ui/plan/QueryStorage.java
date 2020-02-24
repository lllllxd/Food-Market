package com.sdu.wh.ui.plan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.sdu.wh.bll.PlanDepartmentBLL;
import com.sdu.wh.common.HeaderCellRenderer;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.RoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 查询库存窗口类
 * 
 * @author Gale
 *
 */
public class QueryStorage extends JInternalFrame {

	private PlanDepartmentBLL mPlanDepartmentBLL = new PlanDepartmentBLL();
	
	private JTable mTable;
	
	public QueryStorage() {
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
		textArea.setText("\n     查看库存\n     Query Storage");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//按订单编号查询
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new BorderLayout(0,0));
		backpanel.add(panel0, BorderLayout.CENTER);

		JPanel panel1 = new JPanel();
		panel1.setBackground(null);
		panel1.setOpaque(false);
		panel1.setLayout(new BorderLayout(0,0));
		panel0.add(panel1,BorderLayout.NORTH);
		
		JPanel panel1_2 = new JPanel();
		panel1_2.setBackground(null);
		panel1_2.setOpaque(false);
		panel1_2.setLayout(new FlowLayout());
		panel1.add(panel1_2,BorderLayout.EAST);
		
		
		JButton QBtn=new JButton();
		QBtn.setText("刷新");
		QBtn.setForeground(new Color(2, 81, 103));
		QBtn.setBackground(new Color(183, 223, 234));
		QBtn.setOpaque(false);
		QBtn.setPreferredSize(new Dimension(55,25));
		QBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		QBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel1_2.add(QBtn);
		
		//查询生成表
		mTable=new JTable();
		try {
			mPlanDepartmentBLL.refreshStockTable(mTable);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		initialTable(mTable);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//将JScrollPane设置为透明
		scrollPane.setOpaque(false);//将中间的viewport设置为透明
		scrollPane.setViewportView(mTable);//装载表格
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//设置头部（HeaderView部分）
		scrollPane.getColumnHeader().setOpaque(false);//再取出头部，并设置为透明
		panel0.add(scrollPane,BorderLayout.CENTER);
		QBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mPlanDepartmentBLL.refreshStockTable(mTable);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				initialTable(mTable);
			}
		});
		
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
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
	}
}
