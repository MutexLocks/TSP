import View.InfoBean;
import View.MyFrame;
import View.TSPView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import java.sql.SQLType;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class test {
    public static java.util.List<String[]> partiallyMatchedCrossover() {
        int cityLen = 7;
        Random random = new Random();
        int mid = 1;
        for (int i = 0; i < mid; i++) {
            String[] fatherCities = "北京 银川 重庆 乌鲁木齐 拉萨 上海 天津".split(" ");
            String[] motherCities = "北京 重庆 天津 上海 乌鲁木齐 银川 拉萨".split(" ");
            String[] child1 = new String[cityLen];
            String[] child2 = new String[cityLen];
            int cutPoint1 = 1;
            int cutPoint2 = 5;

            if (cutPoint1 != cutPoint2) {

                //复制前面部分
                for (int j = 0; j < cutPoint1; j++) {
                    child1[j] = fatherCities[j];
                    child2[j] = motherCities[j];
                }

                //交换中间部分
                Map<String, String> saveChild1SawpCity = new HashMap<>();
                Map<String, String> saveChild2SawpCity = new HashMap<>();
                List<String> child1MiddlePart = new ArrayList<>();
                List<String> child2MiddlePart = new ArrayList<>();

                for (int j = cutPoint1; j < cutPoint2; j++) {
                    child1[j] = motherCities[j];
                    child1MiddlePart.add(motherCities[j]);

                    child2[j] = fatherCities[j];
                    child2MiddlePart.add(fatherCities[j]);

                    saveChild1SawpCity.put(motherCities[j], fatherCities[j]);
                    saveChild2SawpCity.put(fatherCities[j], motherCities[j]); //保存交换城市的映射
                }

                //复制后面部分
                for (int j = cutPoint2; j < cityLen; j++) {
                    child1[j] = fatherCities[j];
                    child2[j] = motherCities[j];
                }

                //消除child重复部分
                for (int k = 0; k < cutPoint1; k++) {
                    if (child1MiddlePart.contains(child1[k])) {
                        while (true) {
                            String value = saveChild1SawpCity.get(child1[k]);
                            if (!child1MiddlePart.contains(value)) {
                                child1[k] = value;
                                break;
                            }
                            child1[k] = value;
                        }
                    }

                    if (child2MiddlePart.contains(child2[k])) {
                        while (true) {
                            String value = saveChild2SawpCity.get(child2[k]);
                            if (!child2MiddlePart.contains(value)) {
                                child2[k] = value;
                                break;
                            }
                            child2[k] = value;
                        }
                    }
                }
                for (int k = cutPoint2; k < cityLen; k++) {
                    if (child1MiddlePart.contains(child1[k])) {
                        while (true) {
                            String value = saveChild1SawpCity.get(child1[k]);
                            if (!child1MiddlePart.contains(value)) {
                                child1[k] = value;
                                break;
                            }
                            child1[k] = value;
                        }
                    }

                    if (child2MiddlePart.contains(child2[k])) {
                        while (true) {
                            String value = saveChild2SawpCity.get(child2[k]);
                            if (!child2MiddlePart.contains(value)) {
                                child2[k] = value;
                                break;
                            }
                            child2[k] = value;
                        }
                    }
                }
            }


            Set<String> citySet = new HashSet<>();
            for (String child : child2) {
                citySet.add(child);
            }

                System.out.println("CutPoint1 :" + String.valueOf(cutPoint1));
                System.out.println("CutPoint2 :" + String.valueOf(cutPoint2));
                System.out.println("Father :");
                for (String father : fatherCities) {
                    System.out.print(father + " ");
                }

                System.out.println("\nMother :");
                for (String mother : motherCities) {
                    System.out.print(mother + " ");
                }
                System.out.println("\nChild :");
                for (String child : child2) {
                    System.out.print(child + " ");
                }
                System.out.println("\n\n");
            }





//        for (String[] cities : newPopulation) {
//            Set<String> set = new HashSet<>();
//            for (String city : cities) {
//                set.add(city);
//            }
//            for (String s : set) {
//                System.out.print(s + " ");
//            }
//            System.out.println();
//        }


        return null;
    }
    public static void main(String[] args){
        String[] a = {"1", "2"};
        String[] b = {"3", "4"};
        String[] c= a.clone();

        a[0] = "111";
        System.out.println(c[0]);
        System.out.println(a[0]);

 //       InfoBean myInfoBean = new TSPView().initComponents();

//        System.out.println(myInfoBean.getStartCity());
//        System.out.println();
//        for (String city : myInfoBean.getPassCity()) {
//            System.out.print(city + " ");
//        }
//        System.out.println(myInfoBean.getCaseNumber());

//        JFrame f=new JFrame("JComboBox1");
//        Container contentPane=f.getContentPane();
//        contentPane.setLayout(new GridLayout(1,2));
//        String[] s = {"美国","日本","大陆","英国","法国","意大利","澳洲","韩国"};
//        Vector v=new Vector();
//        v.addElement("Nokia 8850");
//        v.addElement("Nokia 8250");
//        v.addElement("Motorola v8088");
//        v.addElement("Motorola v3850");
//        v.addElement("Panasonic 8850");
//        v.addElement("其它");
//
//        JComboBox combo1=new JComboBox(s);
//        combo1.addItem("中国");//利用JComboBox类所提供的addItem()方法，加入一个项目到此JComboBox中。
//        combo1.setBorder(BorderFactory.createTitledBorder("你最喜欢到哪个国家玩呢?"));
//        JComboBox combo2=new JComboBox(v);
//        combo2.setBorder(BorderFactory.createTitledBorder("你最喜欢哪一种手机呢？"));
//        contentPane.add(combo1);
//        contentPane.add(combo2);
//        f.pack();
//        f.show();
//        f.addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//                System.exit(0);
//            }
//        });
    }
}