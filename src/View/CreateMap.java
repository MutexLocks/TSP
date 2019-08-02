package View;
import GA.CalculateDistance;
import GA.TSP;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateMap {

    private  static Map<String, CityInfo> cityNameCoordinateMap;
    private static InfoBean clientChoice;
    private static Object[] resultCity;
    private static String[] cityOrder;
    private static String[] secondOrder;
    private static int standerX = 88;
    private static int standerY = 44;
    public CreateMap(InfoBean clientChoice) {
        this.clientChoice = clientChoice;
        this.cityNameCoordinateMap = getCityInfo();
        executeGAProgram();
    }
    public void executeGAProgram() {
        /*
         * 在 AWT 的事件队列线程中创建窗口和组件, 确保线程安全,
         * 即 组件创建、绘制、事件响应 需要处于同一线程。
         */
        this.resultCity = new TSP().tsp(clientChoice);
        this.cityOrder = (String []) resultCity[0];
        this.secondOrder = (String[]) resultCity[1];
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建窗口对象
                MyFrame frame = new MyFrame();
                // 显示窗口
                frame.setVisible(true);
            }
        });
    }

    /**
     * 窗口
     */
    public static class MyFrame extends JFrame {

        public static final String TITLE = "Java图形绘制";

        public static final int WIDTH = 1300;
        public static final int HEIGHT = 900;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            // 设置 窗口标题 和 窗口大小
            setTitle(TITLE);
            setSize(WIDTH, HEIGHT);

            // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // 把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);

            // 设置窗口的内容面板
            MyPanel panel = new MyPanel(this);
            setContentPane(panel);
        }

    }

    /**
     * 内容面板
     */
    public static class MyPanel extends JPanel {

        private MyFrame frame;

        public MyPanel(MyFrame frame) {
            super();
            this.frame = frame;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawPoin(g);
            drawString(g);
            // 1. 线段 / 折线
            if (clientChoice.getCaseNumber() == 2) {
                drawLine(g);
                drawSecondLine(g);
            } else {
                drawLine(g);
            }

           // drawOrderNumber(g,1, 100, 80);
            // 6. 文本
            //drawImage(g);

        }

        /**
         * 0.描点
         */
        private void drawPoin(Graphics g) {
            frame.setTitle("1. 线段 / 折线");

            // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
            // 这里必须使用副本, 避免影响到 Graphics 原有的设置
            Graphics2D g2d = (Graphics2D) g.create();

            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 设置画笔颜色
            g2d.setColor(Color.RED);

            // 1. 两点绘制线段: 点(20, 50), 点(200, 50)
            BasicStroke bs1 = new BasicStroke(5);       // 笔画的轮廓（画笔宽度/线宽为5px）
            g2d.setStroke(bs1);



           // System.out.println(cityNameCoordinateMap.get("北京").getY());
            for (CityInfo cityInfo1 : cityNameCoordinateMap.values()) {
                int x = Math.round(cityInfo1.getX());
                int y = Math.round(cityInfo1.getY());
                int mapX = x + 27 * Math.abs(standerX - x);
                int mapY = y + 27 * Math.abs(standerY - y);
                g2d.drawLine(mapX, mapY, mapX, mapY);
            }
            g2d.dispose();
        }

        /**
         * 1. 线段 / 折线
         */
        private void drawSecondLine(Graphics g) {
            frame.setTitle("TSP");

            // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
            // 这里必须使用副本, 避免影响到 Graphics 原有的设置
            Graphics2D g2d = (Graphics2D) g.create();

            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 设置画笔颜色
            g2d.setColor(Color.BLUE);


            String startCity = secondOrder[0];
            int previousCityX = Math.round(cityNameCoordinateMap.get(startCity).getX());

            int previousCityY = Math.round(cityNameCoordinateMap.get(startCity).getY());
            int previousCityMapX = previousCityX + 27 * Math.abs(standerX - previousCityX);
            int previousCityMapY = previousCityY + 27 * Math.abs(standerY - previousCityY);
            System.out.println(previousCityY);
            int passCityMapX = 0;
            int passCityMapY = 0;
            int i = 0;
            for (i = 1; i < secondOrder.length; i++) {
                String passCity = secondOrder[i];
                int passCityX = Math.round(cityNameCoordinateMap.get(passCity).getX());
                int passCityY = Math.round(cityNameCoordinateMap.get(passCity).getY());
                passCityMapX = passCityX + 27 * Math.abs(standerX - passCityX);
                passCityMapY = passCityY + 27 * Math.abs(standerY - passCityY);
                g2d.drawLine(previousCityMapX, previousCityMapY, passCityMapX, passCityMapY);


                //顺序标号
                int midX = Math.abs(previousCityMapX + passCityMapX) / 2;
                int midY = Math.abs(previousCityMapY + passCityMapY) / 2;
                g2d.setFont(new Font(null, Font.PLAIN, 16));
                g2d.drawString(String.valueOf(i), midX, midY);



                previousCityMapX = passCityMapX;
                previousCityMapY = passCityMapY;
            }
            previousCityMapX = previousCityX + 27 * Math.abs(standerX - previousCityX);
            previousCityMapY = previousCityY + 27 * Math.abs(standerY - previousCityY);

            int midX = Math.abs(previousCityMapX + passCityMapX) / 2;
            int midY = Math.abs(previousCityMapY + passCityMapY) / 2;

            g2d.drawLine(previousCityMapX, previousCityMapY, passCityMapX, passCityMapY);
            g2d.drawString(String.valueOf(i), midX, midY);
            for (String city : cityOrder) {
                System.out.println(city);
            }

            double distance = CalculateDistance.calculateCityDistance(secondOrder, TSP.cityNameCoordinateMap);
            String path = "";
            int pathIndex = 0;
            for (pathIndex = 0; pathIndex < secondOrder.length; pathIndex ++) {
                path = path + secondOrder[pathIndex] + " -> ";
            }
            path = path + secondOrder[0];

            g2d.drawString("蓝线距离： " + String.valueOf(distance), 50, 770);
            g2d.drawString(path , 50, 800);
            // 1. 两点绘制线段: 点(20, 50), 点(200, 50)

//            // 3. 两点绘制线段（设置线宽为5px）: 点(50, 150), 点(200, 150)
//            BasicStroke bs1 = new BasicStroke(5);       // 笔画的轮廓（画笔宽度/线宽为5px）
//            g2d.setStroke(bs1);
//            g2d.drawLine(50, 150, 200, 150);
//
//
            g2d.dispose();
        }
        private void drawLine(Graphics g) {
            frame.setTitle("TSP");

            // 创建 Graphics 的副本, 需要改变 Graphics 的参数,
            // 这里必须使用副本, 避免影响到 Graphics 原有的设置
            Graphics2D g2d = (Graphics2D) g.create();

            // 抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 设置画笔颜色
            g2d.setColor(Color.RED);


            String startCity = cityOrder[0];
            int previousCityX = Math.round(cityNameCoordinateMap.get(startCity).getX());

            int previousCityY = Math.round(cityNameCoordinateMap.get(startCity).getY());
            int previousCityMapX = previousCityX + 27 * Math.abs(standerX - previousCityX);
            int previousCityMapY = previousCityY + 27 * Math.abs(standerY - previousCityY);
            System.out.println(previousCityY);
            int passCityMapX = 0;
            int passCityMapY = 0;
            int i = 0;
            for (i = 1; i < cityOrder.length; i++) {
                String passCity = cityOrder[i];
                int passCityX = Math.round(cityNameCoordinateMap.get(passCity).getX());
                int passCityY = Math.round(cityNameCoordinateMap.get(passCity).getY());
                passCityMapX = passCityX + 27 * Math.abs(standerX - passCityX);
                passCityMapY = passCityY + 27 * Math.abs(standerY - passCityY);
                g2d.drawLine(previousCityMapX, previousCityMapY, passCityMapX, passCityMapY);


                //顺序标号
                int midX = Math.abs(previousCityMapX + passCityMapX) / 2;
                int midY = Math.abs(previousCityMapY + passCityMapY) / 2;
                g2d.setFont(new Font(null, Font.PLAIN, 16));
                g2d.drawString(String.valueOf(i), midX, midY);



                previousCityMapX = passCityMapX;
                previousCityMapY = passCityMapY;
            }
            previousCityMapX = previousCityX + 27 * Math.abs(standerX - previousCityX);
            previousCityMapY = previousCityY + 27 * Math.abs(standerY - previousCityY);

            int midX = Math.abs(previousCityMapX + passCityMapX) / 2;
            int midY = Math.abs(previousCityMapY + passCityMapY) / 2;

            g2d.drawLine(previousCityMapX, previousCityMapY, passCityMapX, passCityMapY);
            g2d.drawString(String.valueOf(i), midX, midY);
            for (String city : cityOrder) {
                System.out.println(city);
            }

            double distance = CalculateDistance.calculateCityDistance(cityOrder, TSP.cityNameCoordinateMap);
            String path = "";
            int pathIndex = 0;
            for (pathIndex = 0; pathIndex < cityOrder.length; pathIndex ++) {
                path = path + cityOrder[pathIndex] + " -> ";
            }
            path = path + cityOrder[0];

            g2d.drawString("红线距离： " + String.valueOf(distance), 50, 700);
            g2d.drawString(path , 50, 730);
            // 1. 两点绘制线段: 点(20, 50), 点(200, 50)

//            // 3. 两点绘制线段（设置线宽为5px）: 点(50, 150), 点(200, 150)
//            BasicStroke bs1 = new BasicStroke(5);       // 笔画的轮廓（画笔宽度/线宽为5px）
//            g2d.setStroke(bs1);
//            g2d.drawLine(50, 150, 200, 150);
//
//
            g2d.dispose();
        }
        /**
         * 6. 文本
         */
        private void drawString(Graphics g) {
            frame.setTitle("6. 文本");
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 设置字体样式, null 表示使用默认字体, Font.PLAIN 为普通样式, 大小为 25px
            g2d.setFont(new Font(null, Font.PLAIN, 16));

            // 绘制文本, 其中坐标参数指的是文本绘制后的 左下角 的位置
            // 首次绘制需要初始化字体, 可能需要较耗时
            int standerX = 88;
            int standerY = 44;
            for (CityInfo cityInfo1 : cityNameCoordinateMap.values()) {
                int x = Math.round(cityInfo1.getX());
                int y = Math.round(cityInfo1.getY());

                int mapX = x + 27 * Math.abs(standerX - x);
                int mapY = y + 27 * Math.abs(standerY - y);
                g2d.drawString(cityInfo1.getCityName(), mapX, mapY);
            }
            g2d.dispose();
        }
    }

    /**
     * draw Result
     * @return
     */

    private static Map<String, CityInfo> getCityInfo() {
        String path = "data/data.txt";
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        Map <String, CityInfo> cityNameCoordinateMap = new HashMap<>();
        try {
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            br = new BufferedReader(inputStreamReader);
            String line = "";
            String[] arrs = null;
            while ((line = br.readLine()) != null) {
                arrs = line.split(",");
                CityInfo cityInfo = new CityInfo();
                cityInfo.setCityName(arrs[0]);
                cityInfo.setX(Float.valueOf(arrs[1]));
                cityInfo.setY(Float.valueOf(arrs[2]));
                cityNameCoordinateMap.put(arrs[0], cityInfo);
                //   System.out.println(cityNameCoordinateMap.get(arrs[0]));
            }
            return cityNameCoordinateMap;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return cityNameCoordinateMap;
    }
}
class CityInfo {
    private String cityName;
    private float X;
    private float Y;

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setX(float x) {
        X = x;
    }

    public void setY(float y) {
        Y = y;
    }
}