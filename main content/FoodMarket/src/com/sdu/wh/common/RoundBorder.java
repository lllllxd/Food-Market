package com.sdu.wh.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

/**
 * Swing
 * 璁剧疆鍦嗚杈规锛堝彲浠ヨ嚜瀹氫箟杈规鐨勯鑹诧級
 * 鍙互涓篵utton锛屾枃鏈绛変汉浠ョ粍浠舵坊鍔犺竟妗�
 * 浣跨敤鏂规硶锛�
 * JButton close = new JButton(" 鍏� 闂� ");
 * close.setOpaque(false);// 璁剧疆鍘熸潵鎸夐挳鑳屾櫙閫忔槑
 * close.setBorder(new RoundBorder());榛戣壊鐨勫渾瑙掕竟妗�
 * close.setBorder(new RoundBorder(Color.RED)); 绾㈣壊鐨勫渾瑙掕竟妗�
 * 
 * @author Monsoons
 */

public class RoundBorder implements Border {
    private Color color;

    private int arcH = 15;
    private int arcW = 15;

    public RoundBorder() {
        this(Color.BLACK);
        // 濡傛灉瀹炰緥鍖栨椂锛屾病鏈変紶鍊�
        // 榛樿鏄粦鑹茶竟妗�
    }

    public RoundBorder(Color color) {
        this.color = color;
    }

    public Insets getBorderInsets(Component c) {

        // top:鍙互璋冭妭鍏夋爣涓庤竟鏋夌殑璺濈, 闂存帴褰卞搷楂樺害
        // left:鍙互璋冭妭鍏夋爣涓庤竟鏋夌殑璺濈
        // bottom:鍙互璋冭妭鍏夋爣涓庤竟鏋夌殑璺濈, 闂存帴褰卞搷楂樺害
        // right:鍙互璋冭妭鍏夋爣涓庤竟鏋夌殑璺濈
        return new Insets(10, 15, 10, 15);
    }

    public boolean isBorderOpaque() {
        return false;
    }

    // 瀹炵幇Border锛堢埗绫伙級鏂规硶
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        //        g.setColor(color);
        //        g.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, arcH, arcW);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, arcH, arcW);

        g2d.dispose();
    }
}