package model.dao;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateImage {

    //str为随机字库，random为随机数
    String str = "23456789"+"ABCDEFGHJKLMNPQRSTUVWXYZ"+"abcdefghijklmnpqrstuvwxyz";
    Random random = new Random();

    private static final int LENGTH = 4;
    private static final int HEIGHT = 30;
    private static final int WIDTH = 100;
    private static final int LINECOUNT = 10;



    public String CreateCode(){
        String code = "";
        for(int i=0; i<LENGTH; i++){
            code += str.charAt(random.nextInt(str.length()));
        }
        return code;
    }


    public BufferedImage CreateImage(String code){
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = image.getGraphics();//产生画笔(从BufferedImage对象的getGraphics方法获取)
        graphics.setColor(Color.white);//先给画笔上色
        graphics.fillRect(0,0,WIDTH,HEIGHT);//然后画背景
        DrawCode(graphics,code);//画字符
        for (int i=0; i<LINECOUNT; i++){
            DrawLine(graphics);//干扰线要一条一条的画，所以要用到for循环
        }
        graphics.dispose();
        return image;
    }

    public void DrawCode(Graphics graphics,String code){
        for (int i=0; i<LENGTH; i++){
            //画code的时候，要一个字符一个字符的画
            char ch = code.charAt(i);
            //先给画笔上色，然后才能开始画
            graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            graphics.drawString(ch+"",20*i+10,15);
        }
    }

    public void DrawLine(Graphics graphics){
        int x = random.nextInt(WIDTH);
        int y = random.nextInt(HEIGHT);
        int x1 = random.nextInt(10);
        int y1 = random.nextInt(10);
        graphics.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        graphics.drawLine(x,y,x+x1,y+y1);
    }
}
