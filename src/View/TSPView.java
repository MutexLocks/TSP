package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import javax.swing.*;

import javafx.scene.control.CheckBox;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Wed May 08 08:39:06 CST 2019
 */



/**
 * @author Brainrain
 */
public class TSPView extends JFrame {
    String[] passCities = {"北京","天津","上海","重庆","拉萨","乌鲁木齐","银川","呼和浩特","南宁","哈尔滨","长春","沈阳"
            ,"石家庄","太原","西宁","济南","郑州","南京","合肥","杭州","福州","南昌","长沙","武汉","广州", "台北", "海口"
            ,"兰州","西安","成都","贵阳","昆明","香港","澳门"};
    public void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        label1 = new JLabel();
        comboBox1 = new JComboBox(passCities);
        label3 = new JLabel();
        spinner1 = new JSpinner();
        spinner1.setValue(1);
        ArrayList<JCheckBox> jCheckBoxList = new ArrayList<JCheckBox>();

        label2 = new JLabel();
        checkBox1 = new JCheckBox();
        checkBox14 = new JCheckBox();
        checkBox7 = new JCheckBox();
        checkBox13 = new JCheckBox();
        checkBox25 = new JCheckBox();
        checkBox31 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox15 = new JCheckBox();
        checkBox8 = new JCheckBox();
        checkBox20 = new JCheckBox();
        checkBox26 = new JCheckBox();
        checkBox32 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox16 = new JCheckBox();
        checkBox9 = new JCheckBox();
        checkBox21 = new JCheckBox();
        checkBox27 = new JCheckBox();
        checkBox33 = new JCheckBox();
        checkBox4 = new JCheckBox();
        checkBox17 = new JCheckBox();
        checkBox10 = new JCheckBox();
        checkBox22 = new JCheckBox();
        checkBox28 = new JCheckBox();
        checkBox34 = new JCheckBox();
        checkBox5 = new JCheckBox();
        checkBox18 = new JCheckBox();
        checkBox11 = new JCheckBox();
        checkBox23 = new JCheckBox();
        checkBox29 = new JCheckBox();
        checkBox6 = new JCheckBox();
        checkBox19 = new JCheckBox();
        checkBox12 = new JCheckBox();
        checkBox24 = new JCheckBox();
        checkBox30 = new JCheckBox();
        button1 = new JButton();
        jCheckBoxList.add(checkBox1);
        jCheckBoxList.add(checkBox2);
        jCheckBoxList.add(checkBox3);
        jCheckBoxList.add(checkBox4);
        jCheckBoxList.add(checkBox5);
        jCheckBoxList.add(checkBox6);
        jCheckBoxList.add(checkBox7);
        jCheckBoxList.add(checkBox8);
        jCheckBoxList.add(checkBox9);
        jCheckBoxList.add(checkBox10);
        jCheckBoxList.add(checkBox11);
        jCheckBoxList.add(checkBox12);
        jCheckBoxList.add(checkBox13);
        jCheckBoxList.add(checkBox14);
        jCheckBoxList.add(checkBox15);
        jCheckBoxList.add(checkBox16);
        jCheckBoxList.add(checkBox17);
        jCheckBoxList.add(checkBox18);
        jCheckBoxList.add(checkBox19);
        jCheckBoxList.add(checkBox20);
        jCheckBoxList.add(checkBox21);
        jCheckBoxList.add(checkBox22);
        jCheckBoxList.add(checkBox23);
        jCheckBoxList.add(checkBox24);
        jCheckBoxList.add(checkBox25);
        jCheckBoxList.add(checkBox26);
        jCheckBoxList.add(checkBox27);
        jCheckBoxList.add(checkBox28);
        jCheckBoxList.add(checkBox29);
        jCheckBoxList.add(checkBox30);
        jCheckBoxList.add(checkBox31);
        jCheckBoxList.add(checkBox32);
        jCheckBoxList.add(checkBox33);
        jCheckBoxList.add(checkBox34);
        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("\u51fa\u53d1\u57ce\u5e02\uff1a");
        contentPane.add(label1, "cell 1 1");
        contentPane.add(comboBox1, "cell 2 1");

        //---- label3 ----
        label3.setText("\u8f93\u51fa\u53ef\u884c\u65b9\u6848\u4e2a\u6570\uff1a");
        contentPane.add(label3, "cell 3 1");
        contentPane.add(spinner1, "cell 4 1");

        //---- label2 ----
        label2.setText("\u7ecf\u8fc7\u57ce\u5e02\uff1a");
        contentPane.add(label2, "cell 1 2");

        //---- checkBox1 ----
        checkBox1.setText("\u5929\u6d25");
        contentPane.add(checkBox1, "cell 2 2");

        //---- checkBox14 ----
        checkBox14.setText("\u547c\u548c\u6d69\u7279");
        contentPane.add(checkBox14, "cell 2 2");

        //---- checkBox7 ----
        checkBox7.setText("\u592a\u539f");
        contentPane.add(checkBox7, "cell 3 2");

        //---- checkBox13 ----
        checkBox13.setText("\u676d\u5dde");
        contentPane.add(checkBox13, "cell 3 2");

        //---- checkBox25 ----
        checkBox25.setText("\u53f0\u5317");
        contentPane.add(checkBox25, "cell 4 2");

        //---- checkBox31 ----
        checkBox31.setText("\u6606\u660e");
        contentPane.add(checkBox31, "cell 4 2");

        //---- checkBox2 ----
        checkBox2.setText("\u4e0a\u6d77");
        contentPane.add(checkBox2, "cell 2 3");

        //---- checkBox15 ----
        checkBox15.setText("\u5357\u5b81       ");
        contentPane.add(checkBox15, "cell 2 3");

        //---- checkBox8 ----
        checkBox8.setText("\u897f\u5b81");
        contentPane.add(checkBox8, "cell 3 3");

        //---- checkBox20 ----
        checkBox20.setText("\u798f\u5dde");
        contentPane.add(checkBox20, "cell 3 3");

        //---- checkBox26 ----
        checkBox26.setText("\u6d77\u53e3");
        contentPane.add(checkBox26, "cell 4 3");

        //---- checkBox32 ----
        checkBox32.setText("\u9999\u6e2f");
        contentPane.add(checkBox32, "cell 4 3");

        //---- checkBox3 ----
        checkBox3.setText("\u91cd\u5e86");
        contentPane.add(checkBox3, "cell 2 4");

        //---- checkBox16 ----
        checkBox16.setText("\u54c8\u5c14\u6ee8   ");
        contentPane.add(checkBox16, "cell 2 4");

        //---- checkBox9 ----
        checkBox9.setText("\u6d4e\u5357");
        contentPane.add(checkBox9, "cell 3 4");

        //---- checkBox21 ----
        checkBox21.setText("\u5357\u660c");
        contentPane.add(checkBox21, "cell 3 4");

        //---- checkBox27 ----
        checkBox27.setText("\u5170\u5dde");
        contentPane.add(checkBox27, "cell 4 4");

        //---- checkBox33 ----
        checkBox33.setText("\u6fb3\u95e8");
        contentPane.add(checkBox33, "cell 4 4");

        //---- checkBox4 ----
        checkBox4.setText("\u62c9\u8428");
        contentPane.add(checkBox4, "cell 2 5");

        //---- checkBox17 ----
        checkBox17.setText("\u957f\u6625      ");
        contentPane.add(checkBox17, "cell 2 5");

        //---- checkBox10 ----
        checkBox10.setText("\u90d1\u5dde");
        contentPane.add(checkBox10, "cell 3 5");

        //---- checkBox22 ----
        checkBox22.setText("\u957f\u6c99");
        contentPane.add(checkBox22, "cell 3 5");

        //---- checkBox28 ----
        checkBox28.setText("\u897f\u5b89");

        contentPane.add(checkBox28, "cell 4 5");

        //---- checkBox34 ----
        checkBox34.setText("\u5317\u4eac");
        contentPane.add(checkBox34, "cell 4 5");

        //---- checkBox5 ----
        checkBox5.setText("\u4e4c\u9c81\u6728\u9f50");
        contentPane.add(checkBox5, "cell 2 6");

        //---- checkBox18 ----
        checkBox18.setText("\u6c88\u9633             ");
        contentPane.add(checkBox18, "cell 2 6");

        //---- checkBox11 ----
        checkBox11.setText("\u5357\u4eac");
        contentPane.add(checkBox11, "cell 3 6");

        //---- checkBox23 ----
        checkBox23.setText("\u6b66\u6c49");
        contentPane.add(checkBox23, "cell 3 6");

        //---- checkBox29 ----
        checkBox29.setText("\u6210\u90fd");
        contentPane.add(checkBox29, "cell 4 6");

        //---- checkBox6 ----
        checkBox6.setText("\u94f6\u5ddd");
        contentPane.add(checkBox6, "cell 2 7");
        //---- checkBox19 ----
        checkBox19.setText("\u77f3\u5bb6\u5e84  ");
        contentPane.add(checkBox19, "cell 2 7");

        //---- checkBox12 ----
        checkBox12.setText("\u5408\u80a5");
        contentPane.add(checkBox12, "cell 3 7");

        //---- checkBox24 ----
        checkBox24.setText("\u5e7f\u5dde");
        contentPane.add(checkBox24, "cell 3 7");

        //---- checkBox30 ----
        checkBox30.setText("\u8d35\u9633");
        contentPane.add(checkBox30, "cell 4 7");

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        contentPane.add(button1, "cell 3 8");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        JFrame frame = new MyFrame().getFrame();
        frame.setContentPane(contentPane);

        InfoBean myInfoBean = new InfoBean();
        button1.addActionListener(new ActionListener() {//给按钮添加事件接收器
            @Override
            public void actionPerformed(ActionEvent e) {//接受到事件后,进行下面的处理
                //System.out.println("Hello World");// 控制台打印输出
                //frame.setVisible(false);
                // frame.setVisible(false);
                int caseNumber = (int)spinner1.getValue();
                //存储输出结果个数
                myInfoBean.setCaseNumber(caseNumber);
                //存储出发城市
                String startCity =(String) comboBox1.getSelectedItem();
                startCity = startCity.trim();
                myInfoBean.setStartCity(startCity);
                List<String> cities = new ArrayList<>();
                //判断经过城市是否被选中
                for (JCheckBox checkBox : jCheckBoxList) {
                    if (checkBox.getText().equals(startCity)) {
                        checkBox.setSelected(false);
                    }
                    if (checkBox.isSelected()) {
                        cities.add(checkBox.getText());
                    }
                }
                String[] passCities = new String[cities.size()];
                for (int i = 0 ; i < passCities.length; i++) {
                    passCities[i] = cities.get(i).trim();
                }
                //存储经过城市
                myInfoBean.setPassCity(passCities);
                new CreateMap(myInfoBean);
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JComboBox comboBox1;
    private JLabel label3;
    private JSpinner spinner1;
    private JLabel label2;
    private JCheckBox checkBox1;
    private JCheckBox checkBox14;
    private JCheckBox checkBox7;
    private JCheckBox checkBox13;
    private JCheckBox checkBox25;
    private JCheckBox checkBox31;
    private JCheckBox checkBox2;
    private JCheckBox checkBox15;
    private JCheckBox checkBox8;
    private JCheckBox checkBox20;
    private JCheckBox checkBox26;
    private JCheckBox checkBox32;
    private JCheckBox checkBox3;
    private JCheckBox checkBox16;
    private JCheckBox checkBox9;
    private JCheckBox checkBox21;
    private JCheckBox checkBox27;
    private JCheckBox checkBox33;
    private JCheckBox checkBox4;
    private JCheckBox checkBox17;
    private JCheckBox checkBox10;
    private JCheckBox checkBox22;
    private JCheckBox checkBox28;
    private JCheckBox checkBox34;
    private JCheckBox checkBox5;
    private JCheckBox checkBox18;
    private JCheckBox checkBox11;
    private JCheckBox checkBox23;
    private JCheckBox checkBox29;
    private JCheckBox checkBox6;
    private JCheckBox checkBox19;
    private JCheckBox checkBox12;
    private JCheckBox checkBox24;
    private JCheckBox checkBox30;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
