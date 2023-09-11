package com.myspring.xixi.utils;

import cn.hutool.crypto.SecureUtil;
import com.myspring.xixi.common.lang.VerifyCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class GetVerifyCode {

    private static final Integer WIDTH = 40;
    private static final Integer HEIGHT = 20;
    private static final Integer NUMBER = 4;
    private static final String CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getCode(String num, OutputStream os) throws IOException {

        //对字符进行加密
        num = SecureUtil.md5(num);

        //创建一张图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        //创建白色背景
        g.setColor(Color.white);
        g.fillRect(0,0, WIDTH, HEIGHT);

        //画黑边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        Random random = new Random();

        //每个字符占据的宽度
        int x = (WIDTH - 1) / NUMBER;
        int y = HEIGHT - 4;

        StringBuffer sb = new StringBuffer();

        //生成随机字符串
        for (int i = 0; i < NUMBER; i++) {
            String code = String.valueOf(num.charAt(i));
            g.setColor(randomCodeColor());
            Font font = new Font("Arial", Font.PLAIN, random.nextInt(10)+10);
            g.setFont(font);
            g.drawString(code, x * i + 1, y);

            sb.append(code);
        }

        //生成随机线条
        for (int i = 0; i < 5; i++) {
            g.setColor(randomLineColor());
            int m = random.nextInt(WIDTH);
            int n = random.nextInt(HEIGHT);
            int m1 = random.nextInt(WIDTH);
            int n1 = random.nextInt(HEIGHT);
            g.drawLine(m, n, m1, n1);
        }

        ImageIO.write(image, "JPEG", os);

        return sb.toString();
    }

    public static VerifyCode getVerify(String num) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String code = getCode(num, os);
        verifyCode.setCode(code);
        verifyCode.setImgBytes(os.toByteArray());
        return verifyCode;
    }

    private static Color randomCodeColor(){
        Random random = new Random();
        int r = random.nextInt(155);
        int g = random.nextInt(155);
        int b = random.nextInt(155);
        return new Color(r, g, b);
    }
    private static Color randomLineColor(){
        Random random = new Random();
        int r = random.nextInt(155) + 50;
        int g = random.nextInt(155) + 50;
        int b = random.nextInt(155) + 50;
        return new Color(r, g, b);
    }
}
