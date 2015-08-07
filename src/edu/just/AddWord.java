package edu.just;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class AddWord {
	/**
	 * �����ַ����ĳ��ȣ�һ�������������ַ���
	 * @param text ��ӵ��ַ���
	 * @return
	 */
    public final static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }
    /**
     * 
     * @param txt 			��ӵ�ˮӡ����
     * @param image			Դͼ��
     * @param destImage		Ŀ��ͼ��
     * @param color			������ɫ
     * @param fontname		��������
     * @param fonttype		�����ʽ
     * @param fontsize		�����С
     * @param x				ˮӡ���ں�����
     * @param y				ˮӡ����������
     * @param alpha			����͸����
     */
	public static void getNewImage(String txt,Image image,File destImage,Color color,
			String fontname,int fonttype,int fontsize,
			int x,int y,float alpha){
		int height=image.getHeight(null);
		int width=image.getWidth(null);
		BufferedImage bImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d=bImage.createGraphics();
		g2d.drawImage(image,0,0,width,height,null);
		g2d.setColor(color);
		g2d.setFont(new Font(fontname,fonttype,fontsize));
		//��������Ŀ���ص��Ļ�ϴ���
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
		//��ָ�����괦����ˮӡ����
		g2d.drawString(txt,  (width-(getLength(txt) * fontsize))/ 2 + x, (height - fontsize) / 2 +y);
		//�ͷ���Դ
		g2d.dispose();
		try {
			ImageIO.write(bImage, "JPEG", destImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Image image = null;
		try {
			image = ImageIO.read(new File("image\\0023ae6ce80215c12a500c.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File destImage=new File("1.jpeg");
		getNewImage("��˳��", image, destImage, Color.WHITE,"����", Font.ITALIC, 100, -100, -100, 0.4f);
	}
	
}
