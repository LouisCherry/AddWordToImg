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
	 * 计算字符串的长度（一个中文算两个字符）
	 * @param text 添加的字符串
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
     * @param txt 			添加的水印文字
     * @param image			源图像
     * @param destImage		目标图像
     * @param color			字体颜色
     * @param fontname		字体名称
     * @param fonttype		字体格式
     * @param fontsize		字体大小
     * @param x				水印所在横坐标
     * @param y				水印所在纵坐标
     * @param alpha			字体透明度
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
		//设置两个目标重叠的混合处理
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
		//在指定坐标处绘制水印文字
		g2d.drawString(txt,  (width-(getLength(txt) * fontsize))/ 2 + x, (height - fontsize) / 2 +y);
		//释放资源
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
		getNewImage("潘顺兴", image, destImage, Color.WHITE,"宋体", Font.ITALIC, 100, -100, -100, 0.4f);
	}
	
}
