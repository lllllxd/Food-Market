package com.sdu.wh.ui.boss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.sdu.wh.bll.BossDepartment;
import com.sdu.wh.common.HeaderCellRenderer;
import com.sdu.wh.common.ImagePanel;
import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.common.RoundBorder;

/**
 * ��ѯ��������
 * 
 * @author Gale
 *
 */
public class Query extends JInternalFrame {

	private BossDepartment mBossDepartment = new BossDepartment();

	private JTable mTable;
	
	public Query() {
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
		textArea.setText("\n     ��ѯ����¼\n     Query  Form");
		backpanel.add(textArea, BorderLayout.NORTH);
		
		//��center��panel
		JPanel panel0 = new JPanel();
		panel0.setBackground(null);
		panel0.setOpaque(false);
		panel0.setLayout(new BorderLayout(0,0));
		backpanel.add(panel0, BorderLayout.CENTER);

		//��ѯ��������ѯ��ť
		JPanel panel = new JPanel();
		panel.setBackground(null);
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout());
		panel0.add(panel,BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(null);
		panel1.setOpaque(false);
		panel1.setLayout(new FlowLayout());
		panel.add(panel1,BorderLayout.EAST);
		
		JComboBox<String> LevelComboBox = new JComboBox<String>();
		LevelComboBox.addItem("������¼");
		LevelComboBox.addItem("�������¼");
		LevelComboBox.addItem("�����ƻ���¼");
		LevelComboBox.addItem("����¼");
		LevelComboBox.addItem("�����¼");
		LevelComboBox.addItem("������¼");
		LevelComboBox.addItem("���");
		//LevelComboBox.setBackground(new Color(2, 81, 103));
		//LevelComboBox.setOpaque(false);
		LevelComboBox.setPreferredSize(new Dimension(140,40));
		//LevelComboBox.setBorder(new RoundBorder(new Color(2, 81, 103)));
		LevelComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		LevelComboBox.setForeground(new Color(2, 81, 103));
		panel1.add(LevelComboBox);
		
		JButton QBtn=new JButton();
		QBtn.setText("��ѯ");
		QBtn.setForeground(new Color(2, 81, 103));
		QBtn.setBackground(new Color(183, 223, 234));
		QBtn.setOpaque(false);
		QBtn.setPreferredSize(new Dimension(55,25));
		QBtn.setBorder(new RoundBorder(new Color(183, 223, 234)));
		QBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		panel1.add(QBtn);
		
		//���Ĳ�ѯ�ı�
		

		mTable=new JTable();
		
		QBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mBossDepartment.refreashTable(mTable, LevelComboBox.getSelectedItem().toString());
				initialTable(mTable);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setOpaque(false);//��JScrollPane����Ϊ͸��
		scrollPane.setOpaque(false);//���м��viewport����Ϊ͸��
		scrollPane.setViewportView(mTable);//װ�ر��
		scrollPane.setColumnHeaderView(mTable.getTableHeader());//����ͷ����HeaderView���֣�
		scrollPane.getColumnHeader().setOpaque(false);//��ȡ��ͷ����������Ϊ͸��
		panel0.add(scrollPane,BorderLayout.CENTER);
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
        
        /*
        table.getColumnModel().getColumn(0).setPreferredWidth(140);
        table.getColumnModel().getColumn(1).setPreferredWidth(140);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(140);
        table.getColumnModel().getColumn(4).setPreferredWidth(90);
		*/
	}
}

