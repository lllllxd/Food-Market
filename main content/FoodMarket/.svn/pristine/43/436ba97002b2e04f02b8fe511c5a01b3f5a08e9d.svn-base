package com.sdu.wh.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
 
public class ImagePanel extends JPanel {
 
    public ImagePanel(LayoutManager lm) {
        super(lm);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isOpaque()) {
            return;
        }
       
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gradientPaint =new GradientPaint(0, 0, new Color(97,183,207), 0, height/3,Color.white,false);
       
       
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, width, height);
       
    }
   
   
    public static void main(String args[]){
           JFrame jf=new JFrame("GradientPanelTest");
           jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
           ImagePanel gp=new ImagePanel(new BorderLayout());
           jf.getContentPane().add(gp);
           jf.setSize(500,400);
           jf.setVisible(true);
    }
}