package roottest;

import com.sun.javaws.IconUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    JPanel root = new JPanel();
    MyField inputField = new MyField("输入三角形三边", Color.BLUE);
    MyField walkingField = new MyField("当前执行路径", Color.ORANGE);
    MyField visitedField = new MyField("已执行路径", Color.green);
    MyField unvisitedField = new MyField("未执行路径", Color.RED);
    // MyField new_inputField = new MyField("输入三角形三条边阈值", Color.RED);
    MyPaint imageField = new MyPaint("执行路径图", Color.BLACK);
    JLabel a_label = new MyLabel("a的取值范围：");
    JLabel a_dash = new MyLabel("~");
    JLabel b_label = new MyLabel("b的取值范围：");
    JLabel b_dash = new MyLabel("~");
    JLabel c_label = new MyLabel("c的取值范围：");
    JLabel c_dash = new MyLabel("~");

    JComboBox cmb = new JComboBox();

    JLabel edgeA = new MyLabel("a边长：");
    JLabel edgeB = new MyLabel("b边长：");
    JLabel edgeC = new MyLabel("c边长：");

    // 边长区间
    JTextField a_min_text = new MyText();
    JTextField a_max_text = new MyText();
    JLabel error1 = new JLabel("");

    JTextField b_min_text = new MyText();
    JTextField b_max_text = new MyText();
    JLabel error2 = new JLabel("");


    JTextField c_min_text = new MyText();
    JTextField c_max_text = new MyText();
    JLabel error3 = new JLabel("");

    JTextField inputA = new MyText();
    JTextField inputB = new MyText();
    JTextField inputC = new MyText();
    JTextArea walkingPath = new MyArea();
    JTextArea hasWalkedPath = new MyArea();
    JTextArea nonWalkedPath = new MyArea();
    JButton doButton = new MyButton("执    行");
    JButton exitButton = new MyButton("退    出");

    int width = 1200 * 5 / 8;
    int height = 820;

    ArrayList<MyRect> myRectArrayList = new ArrayList<>();
    ArrayList<MyDiamond> myDiamondArrayList = new ArrayList<>();
    ArrayList<MyEdge> myEdgeArrayList = new ArrayList<>();

    // 流程图绘制
    // 执行矩形框绘制
    MyRect inputRect = new MyRect(width / 2 - 50, 30, "输入a,b,c", Color.BLACK, 0);
    MyRect initialRect = new MyRect(width / 2 - 50, 90, "match=0", Color.BLACK, 0);
    MyRect equalABRect = new MyRect(width * 3 / 4 - 50, 150, "match+=1", Color.BLACK, 2);
    MyRect equalACRect = new MyRect(width * 3 / 4 - 50, 210, "match+=2", Color.BLACK, 4);
    MyRect equalBCRect = new MyRect(width * 3 / 4 - 50, 270, "match+=3", Color.BLACK, 6);
    MyRect resultRect1 = new MyRect(30, height - 75, "等边三角形", Color.BLACK, 20);
    MyRect resultRect2 = new MyRect(30 + width / 4, height - 75, "等腰三角形", Color.BLACK, 15);
    MyRect resultRect3 = new MyRect(30 + width / 2, height - 75, "非三角形", Color.BLACK, 12);
    MyRect resultRect4 = new MyRect(30 + width * 3 / 4, height - 75, "不等边三角形", Color.BLACK, 11);

    // 条件判断框绘制
    MyDiamond checkABEqual = new MyDiamond(width / 2, 167, 15, "a=b?", Color.BLACK, 1);
    MyDiamond checkACEqual = new MyDiamond(width / 2, 227, 15, "a=c?", Color.BLACK, 3);
    MyDiamond checkBCEqual = new MyDiamond(width / 2, 287, 15, "b=c?", Color.BLACK, 5);
    MyDiamond checkMatchEqual0 = new MyDiamond(width / 2, 347, 25, "match=0?", Color.BLACK, 7);
    MyDiamond checkMatchEqual1 = new MyDiamond(width / 4 + 35, 397, 25, "match=1?", Color.BLACK, 13);
    MyDiamond checkMatchEqual2 = new MyDiamond(80, 447, 25, "match=2?", Color.BLACK, 16);
    MyDiamond checkMatchEqual3 = new MyDiamond(80, 567, 25, "match=3?", Color.BLACK, 18);
    MyDiamond checkACB1 = new MyDiamond(width / 4 + 35, 507, 25, "a+c<=b?", Color.BLACK, 17);
    MyDiamond checkBCA1 = new MyDiamond(width / 4 - 15, 627, 25, "b+c<=a?", Color.BLACK, 19);
    MyDiamond checkABC1 = new MyDiamond(width / 2, 447, 25, "a+b<=c?", Color.BLACK, 14);
    MyDiamond checkABC2 = new MyDiamond(80 + width * 3 / 4, 447, 25, "a+b<=c?", Color.BLACK, 8);
    MyDiamond checkBCA2 = new MyDiamond(80 + width * 3 / 4, 537, 25, "b+c<=a?", Color.BLACK, 9);
    MyDiamond checkACB2 = new MyDiamond(80 + width * 3 / 4, 627, 25, "a+c<=b?", Color.BLACK, 10);

    //路径绘制
    MyEdge inputToInitial = new MyEdge(width / 2, 65, width / 2, 90, Color.BLACK, 0, 0);
    MyEdge initialToCheckABEqual = new MyEdge(width / 2, 125, width / 2, 150, Color.BLACK, 0, 1);
    MyEdge checkABEqualToCheckACEqual = new MyEdge(width / 2, 184, width / 2, 210, Color.BLACK, 1, 3);
    MyEdge checkACEqualToCheckBCEqual = new MyEdge(width / 2, 244, width / 2, 270, Color.BLACK, 3, 5);
    MyEdge checkABEqualToEqualAB = new MyEdge(width / 2 + 50, 167, width * 3 / 4 - 50, 167, Color.BLACK, 1, 2);
    MyEdge EqualABToCheckACEqual = new MyEdge(width * 3 / 4, 185, width / 2, 210, Color.BLACK, 2, 3);
    MyEdge checkACEqualToEqualAC = new MyEdge(width / 2 + 50, 227, width * 3 / 4 - 50, 227, Color.BLACK, 3, 4);
    MyEdge EqualACToCheckBCEqual = new MyEdge(width * 3 / 4, 245, width / 2, 270, Color.BLACK, 4, 5);
    MyEdge checkBCEqualToEqualBC = new MyEdge(width / 2 + 50, 287, width * 3 / 4 - 50, 287, Color.BLACK, 5, 6);
    MyEdge EqualBCToCheckMatchEqual0 = new MyEdge(width * 3 / 4, 305, width / 2, 330, Color.BLACK, 6, 7);
    MyEdge checkBCEqualToCheckMatchEqual0 = new MyEdge(width / 2, 304, width / 2, 330, Color.BLACK, 5, 7);
    MyEdge checkMatchEqual0ToCheckMatchEqual1 = new MyEdge(width / 2 - 50, 347, width / 4 + 35, 380, Color.BLACK, 7, 13);
    MyEdge checkMatchEqual1ToCheckMatchEqual2 = new MyEdge(width / 4 - 15, 397, 80, 430, Color.BLACK, 13, 16);
    MyEdge checkMatchEqual2ToCheckMatchEqual3 = new MyEdge(80, 464, 80, 550, Color.BLACK, 16, 18);
    MyEdge checkMatchEqual0ToCheckABC2 = new MyEdge(width / 2 + 50, 347, 80 + width * 3 / 4, 430, Color.BLACK, 7, 8);
    MyEdge checkMatchEqual1ToCheckABC1 = new MyEdge(width / 4 + 85, 397, width / 2, 430, Color.BLACK, 13, 14);
    MyEdge checkMatchEqual2ToCheckACB1 = new MyEdge(130, 447, width / 4 + 35, 490, Color.BLACK, 16, 17);
    MyEdge checkMatchEqual3ToCheckBCA1 = new MyEdge(130, 567, width / 4 - 15, 610, Color.BLACK, 18, 19);
    MyEdge checkABC2ToCheckBCA2 = new MyEdge(80 + width * 3 / 4, 464, 80 + width * 3 / 4, 520, Color.BLACK, 8, 9);
    MyEdge checkBCA2ToCheckACB2 = new MyEdge(80 + width * 3 / 4, 554, 80 + width * 3 / 4, 610, Color.BLACK, 9, 10);
    MyEdge checkMatchEqual3ToResultRect1 = new MyEdge(80, 584, 80, height - 75, Color.BLACK, 18, 20);
    MyEdge checkACB1ToResultRect2 = new MyEdge(width / 4 + 35, 524, 80 + width / 4, height - 75, Color.BLACK, 17, 15);
    MyEdge checkBCA1ToResultRect2 = new MyEdge(width / 4 - 15, 644, 80 + width / 4, height - 75, Color.BLACK, 19, 15);
    MyEdge checkABC1ToResultRect2 = new MyEdge(width / 2, 464, 80 + width / 4, height - 75, Color.BLACK, 14, 15);
    MyEdge checkACB1ToResultRect3 = new MyEdge(width / 4 + 85, 507, 80 + width / 2, height - 75, Color.BLACK, 17, 12);
    MyEdge checkBCA1ToResultRect3 = new MyEdge(width / 4 + 35, 627, 80 + width / 2, height - 75, Color.BLACK, 19, 12);
    MyEdge checkABC1ToResultRect3 = new MyEdge(width / 2 + 50, 447, 80 + width / 2, height - 75, Color.BLACK, 14, 12);
    MyEdge checkABC2ToResultRect3 = new MyEdge(width * 3 / 4 + 30, 447, 80 + width / 2, height - 75, Color.BLACK, 8, 12);
    MyEdge checkBCA2ToResultRect3 = new MyEdge(width * 3 / 4 + 30, 537, 80 + width / 2, height - 75, Color.BLACK, 9, 12);
    MyEdge checkACB2ToResultRect3 = new MyEdge(width * 3 / 4 + 30, 627, 80 + width / 2, height - 75, Color.BLACK, 10, 12);
    MyEdge checkACB2ToResultRect4 = new MyEdge(80 + width * 3 / 4, 644, 80 + width * 3 / 4, height - 75, Color.BLACK, 10, 11);

//    int[] inputArray = new int[3];
    int[] inputArray = new int[6];
    ArrayList<String> nonWalkedPathList = new ArrayList<>();
    ArrayList<String> hasWalkedPathList = new ArrayList<>();
    ArrayList<Integer> colorChangedParts = new ArrayList<>();
    ArrayList<int[]> colorChangedEdges = new ArrayList<>();

    public MyFrame(String title) {
        super(title);

        this.setContentPane(root);
        this.setLayout(new TotalPositionLayout());

        // 添加各项组件
        // root.add(new_inputField);
        root.add(inputField);
        inputField.add(edgeA);
        inputField.add(a_label);
        inputField.add(a_dash);

        inputField.add(edgeB);
        inputField.add(b_label);
        inputField.add(b_dash);

        inputField.add(edgeC);
        inputField.add(c_label);
        inputField.add(c_dash);

        inputField.add(inputA);
        inputField.add(a_min_text);
        inputField.add(a_max_text);

        inputField.add(inputB);
        inputField.add(b_min_text);
        inputField.add(b_max_text);

        inputField.add(inputC);
        inputField.add(c_min_text);
        inputField.add(c_max_text);

        inputField.add(cmb);

        cmb.addItem("边界值测试");
        cmb.addItem("健壮性边界值测试");
        cmb.addItem("最坏情况边界值测试");
        cmb.addItem("健壮性最坏情况边界值测试");

        // root.add(cmb);

        inputField.add(doButton);
        inputField.setLayout(new InputFieldLayout());
        root.add(walkingField);
        walkingField.add(walkingPath);
        walkingField.setLayout(new WalkingFieldLayout());
        root.add(visitedField);
        visitedField.add(hasWalkedPath);
        visitedField.setLayout(new VisitedFieldLayout());
        root.add(unvisitedField);
        unvisitedField.add(nonWalkedPath);
        unvisitedField.setLayout(new UnvisitedFieldLayout());
        root.add(exitButton);
        root.add(imageField);

        myRectArrayList.add(inputRect);
        myRectArrayList.add(initialRect);
        myRectArrayList.add(equalABRect);
        myRectArrayList.add(equalACRect);
        myRectArrayList.add(equalBCRect);
        myRectArrayList.add(resultRect1);
        myRectArrayList.add(resultRect2);
        myRectArrayList.add(resultRect3);
        myRectArrayList.add(resultRect4);

        myDiamondArrayList.add(checkABEqual);
        myDiamondArrayList.add(checkACEqual);
        myDiamondArrayList.add(checkBCEqual);
        myDiamondArrayList.add(checkMatchEqual0);
        myDiamondArrayList.add(checkMatchEqual1);
        myDiamondArrayList.add(checkMatchEqual2);
        myDiamondArrayList.add(checkMatchEqual3);
        myDiamondArrayList.add(checkABC1);
        myDiamondArrayList.add(checkABC2);
        myDiamondArrayList.add(checkBCA1);
        myDiamondArrayList.add(checkBCA2);
        myDiamondArrayList.add(checkACB1);
        myDiamondArrayList.add(checkACB2);

        myEdgeArrayList.add(inputToInitial);
        myEdgeArrayList.add(initialToCheckABEqual);
        myEdgeArrayList.add(checkABEqualToCheckACEqual);
        myEdgeArrayList.add(checkACEqualToCheckBCEqual);
        myEdgeArrayList.add(checkABEqualToEqualAB);
        myEdgeArrayList.add(EqualABToCheckACEqual);
        myEdgeArrayList.add(checkACEqualToEqualAC);
        myEdgeArrayList.add(EqualACToCheckBCEqual);
        myEdgeArrayList.add(checkBCEqualToEqualBC);
        myEdgeArrayList.add(EqualBCToCheckMatchEqual0);
        myEdgeArrayList.add(checkBCEqualToCheckMatchEqual0);
        myEdgeArrayList.add(checkMatchEqual0ToCheckMatchEqual1);
        myEdgeArrayList.add(checkMatchEqual1ToCheckMatchEqual2);
        myEdgeArrayList.add(checkMatchEqual2ToCheckMatchEqual3);
        myEdgeArrayList.add(checkMatchEqual0ToCheckABC2);
        myEdgeArrayList.add(checkMatchEqual1ToCheckABC1);
        myEdgeArrayList.add(checkMatchEqual2ToCheckACB1);
        myEdgeArrayList.add(checkMatchEqual3ToCheckBCA1);
        myEdgeArrayList.add(checkABC2ToCheckBCA2);
        myEdgeArrayList.add(checkBCA2ToCheckACB2);
        myEdgeArrayList.add(checkMatchEqual3ToResultRect1);
        myEdgeArrayList.add(checkACB1ToResultRect2);
        myEdgeArrayList.add(checkBCA1ToResultRect2);
        myEdgeArrayList.add(checkABC1ToResultRect2);
        myEdgeArrayList.add(checkACB1ToResultRect3);
        myEdgeArrayList.add(checkBCA1ToResultRect3);
        myEdgeArrayList.add(checkABC1ToResultRect3);
        myEdgeArrayList.add(checkACB2ToResultRect3);
        myEdgeArrayList.add(checkBCA2ToResultRect3);
        myEdgeArrayList.add(checkABC2ToResultRect3);
        myEdgeArrayList.add(checkACB2ToResultRect4);




        initialNonWalkedPath();
        StringBuilder tmp = new StringBuilder();
        for(String path: nonWalkedPathList) {
            tmp.append(path);
            tmp.append('\n');
        }
        nonWalkedPath.setText(String.valueOf(tmp));


        doButton.addActionListener(e -> {
            try {
                int match = 0;
                String currentPath = "";

                getInput();
                // 图重置颜色
                resetColor();
                imageField.repaint();

                // 获取三角形三边值
                int a = inputArray[0], b = inputArray[1], c = inputArray[2], d = inputArray[3], e1 = inputArray[4], f = inputArray[5];

                // 执行路径测试
                walkingPath.setText("运行中。。。");
                inputRect.changeColor();
                imageField.repaint();
                inputToInitial.changeColor();
                imageField.repaint();
                initialRect.changeColor();
                imageField.repaint();
                initialToCheckABEqual.changeColor();
                imageField.repaint();
                checkABEqual.changeColor();
                imageField.repaint();
                currentPath += "1-";
                colorChangedParts.add(checkABEqual.index);
                if(a == b) {
                    checkABEqualToEqualAB.changeColor();
                    match += 1;
                    currentPath += "2-";
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkABEqualToEqualAB.tailIndex, checkABEqualToEqualAB.headIndex});
                    equalABRect.changeColor();
                    imageField.repaint();
                    colorChangedParts.add(equalABRect.index);
                    EqualABToCheckACEqual.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {EqualABToCheckACEqual.tailIndex, EqualABToCheckACEqual.headIndex});
                }
                else {
                    checkABEqualToCheckACEqual.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkABEqualToCheckACEqual.tailIndex, checkABEqualToCheckACEqual.headIndex});
                }
                checkACEqual.changeColor();
                imageField.repaint();
                currentPath += "3-";
                colorChangedParts.add(checkACEqual.index);
                if(a == c) {
                    checkACEqualToEqualAC.changeColor();
                    match += 2;
                    currentPath += "4-";
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkACEqualToEqualAC.tailIndex, checkACEqualToEqualAC.headIndex});
                    equalACRect.changeColor();
                    imageField.repaint();
                    colorChangedParts.add(equalACRect.index);
                    EqualACToCheckBCEqual.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {EqualACToCheckBCEqual.tailIndex, EqualACToCheckBCEqual.headIndex});
                }
                else {
                    checkACEqualToCheckBCEqual.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkACEqualToCheckBCEqual.tailIndex, checkACEqualToCheckBCEqual.headIndex});
                }
                checkBCEqual.changeColor();
                imageField.repaint();
                currentPath += "5-";
                colorChangedParts.add(checkBCEqual.index);
                if(b == c) {
                    checkBCEqualToEqualBC.changeColor();
                    match += 3;
                    currentPath += "6-";
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkBCEqualToEqualBC.tailIndex, checkBCEqualToEqualBC.headIndex});
                    equalBCRect.changeColor();
                    imageField.repaint();
                    colorChangedParts.add(equalBCRect.index);
                    EqualBCToCheckMatchEqual0.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {EqualBCToCheckMatchEqual0.tailIndex, EqualBCToCheckMatchEqual0.headIndex});
                }
                else {
                    checkBCEqualToCheckMatchEqual0.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkBCEqualToCheckMatchEqual0.tailIndex, checkBCEqualToCheckMatchEqual0.headIndex});
                }
                checkMatchEqual0.changeColor();
                imageField.repaint();
                currentPath += "7-";
                colorChangedParts.add(checkMatchEqual0.index);
                if(match == 0) {
                    checkMatchEqual0ToCheckABC2.changeColor();
                    imageField.repaint();
                    colorChangedEdges.add(new int[] {checkMatchEqual0ToCheckABC2.tailIndex, checkMatchEqual0ToCheckABC2.headIndex});
                    checkABC2.changeColor();
                    imageField.repaint();
                    colorChangedParts.add(checkABC2.index);
                    currentPath += "8-";
                    if(a + b <= c) {
                        checkABC2ToResultRect3.changeColor();
                        imageField.repaint();
                        colorChangedEdges.add(new int[] {checkABC2ToResultRect3.tailIndex, checkABC2ToResultRect3.headIndex});
                        resultRect3.changeColor();
                        imageField.repaint();
                        currentPath += "12 非三角形(a+b<=c)";
                        colorChangedParts.add(resultRect3.index);
                    }
                    else {
                        checkABC2ToCheckBCA2.changeColor();
                        imageField.repaint();
                        currentPath += "9-";
                        colorChangedEdges.add(new int[] {checkABC2ToCheckBCA2.tailIndex, checkABC2ToCheckBCA2.headIndex});
                        checkBCA2.changeColor();
                        imageField.repaint();
                        colorChangedParts.add(checkBCA2.index);
                        if(b + c <= a) {
                            checkBCA2ToResultRect3.changeColor();
                            imageField.repaint();
                            currentPath += "12 非三角形(b+c<=a)";
                            colorChangedEdges.add(new int[] {checkBCA2ToResultRect3.tailIndex, checkBCA2ToResultRect3.headIndex});
                            resultRect3.changeColor();
                            imageField.repaint();
                            colorChangedParts.add(resultRect3.index);
                        }
                        else {
                            checkBCA2ToCheckACB2.changeColor();
                            imageField.repaint();
                            currentPath += "10-";
                            colorChangedEdges.add(new int[] {checkBCA2ToCheckACB2.tailIndex, checkBCA2ToCheckACB2.headIndex});
                            checkACB2.changeColor();
                            imageField.repaint();
                            colorChangedParts.add(checkACB2.index);
                            if(a + c <= b) {
                                checkACB2ToResultRect3.changeColor();
                                imageField.repaint();
                                currentPath += "12 非三角形(a+c<=b)";
                                colorChangedEdges.add(new int[] {checkACB2ToResultRect3.tailIndex, checkACB2ToResultRect3.headIndex});
                                resultRect3.changeColor();
                                imageField.repaint();
                                colorChangedParts.add(resultRect3.index);
                            }
                            else {
                                checkACB2ToResultRect4.changeColor();
                                imageField.repaint();
                                currentPath += "11 不等边三角形";
                                colorChangedEdges.add(new int[] {checkACB2ToResultRect4.tailIndex, checkACB2ToResultRect4.headIndex});
                                resultRect4.changeColor();
                                imageField.repaint();
                                colorChangedParts.add(resultRect4.index);
                            }
                        }
                    }
                }
                else {
                    checkMatchEqual0ToCheckMatchEqual1.changeColor();
                    imageField.repaint();
                    currentPath += "13-";
                    colorChangedEdges.add(new int[] {checkMatchEqual0ToCheckMatchEqual1.tailIndex, checkMatchEqual0ToCheckMatchEqual1.headIndex});
                    checkMatchEqual1.changeColor();
                    imageField.repaint();
                    colorChangedParts.add(checkMatchEqual1.index);
                    if(match == 1) {
                        checkMatchEqual1ToCheckABC1.changeColor();
                        imageField.repaint();
                        currentPath += "14-";
                        colorChangedEdges.add(new int[] {checkMatchEqual1ToCheckABC1.tailIndex, checkMatchEqual1ToCheckABC1.headIndex});
                        checkABC1.changeColor();
                        imageField.repaint();
                        colorChangedParts.add(checkABC1.index);
                        if(a + b <= c) {
                            checkABC1ToResultRect3.changeColor();
                            imageField.repaint();
                            currentPath += "12 非三角形(a=b)";
                            colorChangedEdges.add(new int[] {checkABC1ToResultRect3.tailIndex, checkABC1ToResultRect3.headIndex});
                            resultRect3.changeColor();
                            imageField.repaint();
                            colorChangedParts.add(resultRect3.index);
                        }
                        else {
                            checkABC1ToResultRect2.changeColor();
                            imageField.repaint();
                            currentPath += "15 等腰三角形(a=b)";
                            colorChangedEdges.add(new int[] {checkABC1ToResultRect2.tailIndex, checkABC1ToResultRect2.headIndex});
                            resultRect2.changeColor();
                            imageField.repaint();
                            colorChangedParts.add(resultRect2.index);
                        }
                    }
                    else {
                        checkMatchEqual1ToCheckMatchEqual2.changeColor();
                        imageField.repaint();
                        currentPath += "16-";
                        colorChangedEdges.add(new int[] {checkMatchEqual1ToCheckMatchEqual2.tailIndex, checkMatchEqual1ToCheckMatchEqual2.headIndex});
                        checkMatchEqual2.changeColor();
                        imageField.repaint();
                        colorChangedParts.add(checkMatchEqual2.index);
                        if(match == 2) {
                            checkMatchEqual2ToCheckACB1.changeColor();
                            imageField.repaint();
                            currentPath += "17-";
                            colorChangedEdges.add(new int[] {checkMatchEqual2ToCheckACB1.tailIndex, checkMatchEqual2ToCheckACB1.headIndex});
                            checkACB1.changeColor();
                            imageField.repaint();
                            colorChangedParts.add(checkACB1.index);
                            if(a + c <= b) {
                                checkACB1ToResultRect3.changeColor();
                                imageField.repaint();
                                currentPath += "12 非三角形(a=c)";
                                colorChangedEdges.add(new int[] {checkACB1ToResultRect3.tailIndex, checkACB1ToResultRect3.headIndex});
                                resultRect3.changeColor();
                                imageField.repaint();
                                colorChangedParts.add(resultRect3.index);
                            }
                            else {
                                checkACB1ToResultRect2.changeColor();
                                imageField.repaint();
                                currentPath += "15 等腰三角形(a=c)";
                                colorChangedEdges.add(new int[] {checkACB1ToResultRect2.tailIndex, checkACB1ToResultRect2.headIndex});
                                resultRect2.changeColor();
                                imageField.repaint();
                                colorChangedParts.add(resultRect2.index);
                            }
                        }
                        else {
                            checkMatchEqual2ToCheckMatchEqual3.changeColor();
                            imageField.repaint();
                            currentPath += "18-";
                            colorChangedEdges.add(new int[] {checkMatchEqual2ToCheckMatchEqual3.tailIndex, checkMatchEqual2ToCheckMatchEqual3.headIndex});
                            checkMatchEqual3.changeColor();
                            imageField.repaint();
                            colorChangedParts.add(checkMatchEqual3.index);
                            if(match == 3){
                                checkMatchEqual3ToCheckBCA1.changeColor();
                                imageField.repaint();
                                currentPath += "19-";
                                colorChangedEdges.add(new int[] {checkMatchEqual3ToCheckBCA1.tailIndex, checkMatchEqual3ToCheckBCA1.headIndex});
                                checkBCA1.changeColor();
                                imageField.repaint();
                                colorChangedParts.add(checkBCA1.index);
                                if(b + c <= a) {
                                    checkBCA1ToResultRect3.changeColor();
                                    imageField.repaint();
                                    currentPath += "12 非三角形(b=c)";
                                    colorChangedEdges.add(new int[] {checkBCA1ToResultRect3.tailIndex, checkBCA1ToResultRect3.headIndex});
                                    resultRect3.changeColor();
                                    imageField.repaint();
                                    colorChangedParts.add(resultRect3.index);
                                }
                                else {
                                    checkBCA1ToResultRect2.changeColor();
                                    imageField.repaint();
                                    currentPath += "15 等腰三角形(b=c)";
                                    colorChangedEdges.add(new int[] {checkBCA1ToResultRect2.tailIndex, checkBCA1ToResultRect2.headIndex});
                                    resultRect2.changeColor();
                                    imageField.repaint();
                                    colorChangedParts.add(resultRect2.index);
                                }
                            }
                            else {
                                checkMatchEqual3ToResultRect1.changeColor();
                                imageField.repaint();
                                currentPath += "20 等边三角形";
                                colorChangedEdges.add(new int[] {checkMatchEqual3ToResultRect1.tailIndex, checkMatchEqual3ToResultRect1.headIndex});
                                resultRect1.changeColor();
                                imageField.repaint();
                                colorChangedParts.add(resultRect1.index);
                            }
                        }
                    }
                }

                // 更新左端路径记录
                walkingPath.setText(currentPath);
                hasWalkedPathList.add(currentPath);
                hasWalkedPath.setText(modifyHasWalkedPath(currentPath));
                nonWalkedPath.setText(modifyNonWalkedPath());
            }
            catch (MyException myException) {
                myException.printStackTrace();
            }
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        this.setVisible(true);
    }

    // 获取输入
    public void getInput() throws MyException {
        if(hasIllegalCharacter()) {
            JOptionPane.showMessageDialog(root, "非法字符，请输入大于0的整数", "", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException();
        }
        if(inputHasFloat()) {
            JOptionPane.showMessageDialog(root, "不支持浮点数，请输入大于0的整数", "", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException();
        }
        if(!inputOverZero()) {
            JOptionPane.showMessageDialog(root, "请输入大于0的整数", "", JOptionPane.INFORMATION_MESSAGE);
            throw new MyException();
        }
        inputArray[0] = Integer.parseInt(a_min_text.getText());
        inputArray[1] = Integer.parseInt(a_max_text.getText());
        inputArray[2] = Integer.parseInt(b_min_text.getText());
        inputArray[3] = Integer.parseInt(b_max_text.getText());
        inputArray[4] = Integer.parseInt(c_min_text.getText());
        inputArray[5] = Integer.parseInt(c_max_text.getText());
    }

    public boolean inputHasFloat() {
        return a_min_text.getText().contains(".") || a_max_text.getText().contains(".") || b_min_text.getText().contains(".") || b_max_text.getText().contains(".")
                || c_min_text.getText().contains(".") || c_max_text.getText().contains(".");
    }

    public boolean inputOverZero() {
        return Integer.parseInt(a_min_text.getText()) > 0
                && Integer.parseInt(a_max_text.getText()) > 0
                && Integer.parseInt(b_min_text.getText()) > 0
                && Integer.parseInt(b_max_text.getText()) > 0
                && Integer.parseInt(c_min_text.getText()) > 0
                && Integer.parseInt(c_max_text.getText()) > 0;
    }

    public boolean hasIllegalCharacter() {

        for(int i=0; i<a_min_text.getText().length(); i++) {
            if(!(a_min_text.getText().charAt(i) >= '0' && a_min_text.getText().charAt(i) <= '9')) {
                return true;
            }
        }
        for(int i=0; i<a_max_text.getText().length(); i++) {
            if(!(a_max_text.getText().charAt(i) >= '0' && a_max_text.getText().charAt(i) <= '9')) {
                return true;
            }
        }
        for(int i=0; i<b_min_text.getText().length(); i++) {
            if (!(b_min_text.getText().charAt(i) >= '0' && b_min_text.getText().charAt(i) <= '9')) {
                return true;
            }
        }
        for(int i=0; i<b_max_text.getText().length(); i++) {
            if (!(b_max_text.getText().charAt(i) >= '0' && b_max_text.getText().charAt(i) <= '9')) {
                return true;
            }
        }
        for(int i=0; i<c_min_text.getText().length(); i++) {
            if (!(c_min_text.getText().charAt(i) >= '0' && c_min_text.getText().charAt(i) <= '9')) {
                return true;
            }
        }
        for(int i=0; i<c_max_text.getText().length(); i++) {
            if (!(c_max_text.getText().charAt(i) >= '0' && c_max_text.getText().charAt(i) <= '9')) {
                return true;
            }
        }
        return false;
    }

    public void initialNonWalkedPath() {
        nonWalkedPathList.add("1-2-3-4-5-6-7-13-16-18-20 等边三角形");
        nonWalkedPathList.add("1-3-5-6-7-13-16-18-19-15 等腰三角形(b=c)");
        nonWalkedPathList.add("1-3-5-6-7-13-16-18-19-12 非三角形(b=c)");
        nonWalkedPathList.add("1-3-4-5-7-13-16-17-15 等腰三角形(a=c)");
        nonWalkedPathList.add("1-3-4-5-7-13-16-17-12 非三角形(a=c)");
        nonWalkedPathList.add("1-2-3-5-7-13-14-15 等腰三角形(a=b)");
        nonWalkedPathList.add("1-2-3-5-7-13-14-12 非三角形(a=b)");
        nonWalkedPathList.add("1-3-5-7-8-12 非三角形(a+b<=c)");
        nonWalkedPathList.add("1-3-5-7-8-9-12 非三角形(b+c<=a)");
        nonWalkedPathList.add("1-3-5-7-8-9-10-12 非三角形(a+c<=b)");
        nonWalkedPathList.add("1-3-5-7-8-9-10-11 不等边三角形");
    }

    public String modifyHasWalkedPath(String currentPath) {
        String res = hasWalkedPath.getText();
        if(hasWalkedPath.getText().equals("")) {
            return currentPath;
        }
        else {
            if(!res.contains(currentPath)) {
                res += '\n';
                res += currentPath;
            }
            return res;
        }
    }

    public String modifyNonWalkedPath() {
        StringBuilder res = new StringBuilder();
        for(String s: nonWalkedPathList) {
            if(!hasWalkedPathList.contains(s)) {
                res.append(s);
                res.append('\n');
            }
        }
        return res.toString();
    }

    public void resetColor() {
        for(MyRect rect: myRectArrayList) {
            rect.resetColor();
        }
        for(MyDiamond diamond: myDiamondArrayList) {
            diamond.resetColor();
        }
        for(MyEdge edge: myEdgeArrayList) {
            edge.resetColor();
        }
    }

    // 窗体布局设置
    private class TotalPositionLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        // 页面内容布局设置
        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            inputField.setLocation(10, 10);
            inputField.setSize(new Dimension(width/2, height/3));

//            walkingField.setLocation(10, 10 + height/4);
//            walkingField.setSize(new Dimension(width/4, height/8 - 5));

            visitedField.setLocation(10, 15 + height/3);
            visitedField.setSize(new Dimension(width/4, height*3/10 + 10));

            unvisitedField.setLocation(10, 35 + height*5/8);
            unvisitedField.setSize(new Dimension(width/4, height/4 + 10));

//            new_inputField.setLocation(10 + width/4,10);
//            new_inputField.setSize(new Dimension(width/4, height/4 - 10));

//            cmb.setLocation(10 + width/8, 10 + height/4);
//            cmb.setSize(new Dimension(width/4, 20));

            exitButton.setLocation((int) (10 + width/6 - exitButton.getPreferredSize().getWidth()/2),
                    (int) (height - (height/8 - 5)/2 - exitButton.getPreferredSize().getHeight()/2) + 20);
            exitButton.setSize(exitButton.getPreferredSize());

            imageField.setLocation(20 + width/2, 10);
            imageField.setSize(new Dimension(width*5/8, height-30));
        }
    }

    private class InputFieldLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            a_label.setLocation(30, 30);
            a_label.setSize(a_label.getPreferredSize());

            b_label.setLocation(30, height*3/8 - b_label.getPreferredSize().height/2);
            b_label.setSize(b_label.getPreferredSize());

            c_label.setLocation(30, height*6/8 - 30 - c_label.getPreferredSize().height);
            c_label.setSize(c_label.getPreferredSize());

            a_min_text.setLocation(30 + a_label.getPreferredSize().width, 30);
            a_min_text.setSize(new Dimension(width/3, a_label.getPreferredSize().height));
            a_dash.setLocation(30 + a_label.getPreferredSize().width + 10 + a_min_text.getWidth(),30);
            a_dash.setSize(a_dash.getPreferredSize());
            a_max_text.setLocation(30 + a_label.getPreferredSize().width + 30 + a_min_text.getWidth(), 30);
            a_max_text.setSize(new Dimension(width/3, a_label.getPreferredSize().height));

            b_min_text.setLocation(30 + b_label.getPreferredSize().width, height*3/8 - b_label.getPreferredSize().height/2);
            b_min_text.setSize(new Dimension(width/3, b_label.getPreferredSize().height));
            b_dash.setLocation(30 + b_label.getPreferredSize().width + 10 + b_min_text.getWidth(),height*3/8 - b_label.getPreferredSize().height/2);
            b_dash.setSize(a_dash.getPreferredSize());
            b_max_text.setLocation(30 + b_label.getPreferredSize().width + 30 + b_min_text.getWidth(), height*3/8 - b_label.getPreferredSize().height/2);
            b_max_text.setSize(new Dimension(width/3, b_label.getPreferredSize().height));

            c_min_text.setLocation(30 + c_label.getPreferredSize().width, height*6/8 - 30 - c_label.getPreferredSize().height);
            c_min_text.setSize(new Dimension(width/3, c_label.getPreferredSize().height));
            c_dash.setLocation(30 + c_label.getPreferredSize().width + 10 + c_min_text.getWidth(),height*6/8 - 30 - c_label.getPreferredSize().height);
            c_dash.setSize(a_dash.getPreferredSize());
            c_max_text.setLocation(30 + c_label.getPreferredSize().width + 30 + c_min_text.getWidth(), height*6/8 - 30 - c_label.getPreferredSize().height);
            c_max_text.setSize(new Dimension(width/3, c_label.getPreferredSize().height));
//            inputA.setLocation(30 + a_label.getPreferredSize().width, 30);
//            inputA.setSize(new Dimension(width/3, a_label.getPreferredSize().height));
//            inputB.setLocation(30 + b_label.getPreferredSize().width, height/2 - b_label.getPreferredSize().height/2);
//            inputB.setSize(new Dimension(width/3, b_label.getPreferredSize().height));
//            inputC.setLocation(30 + c_label.getPreferredSize().width, height - 30 - c_label.getPreferredSize().height);
//            inputC.setSize(new Dimension(width/3, c_label.getPreferredSize().height));
            cmb.setLocation(width/3,height - 30 - c_label.getPreferredSize().height);
            cmb.setSize(new Dimension(width/3, c_label.getPreferredSize().height));


            doButton.setLocation(width-20-doButton.getPreferredSize().width,
                    height-20-doButton.getPreferredSize().height);
            doButton.setSize(doButton.getPreferredSize());
        }
    }

    private class WalkingFieldLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            walkingPath.setLocation(10, 25);
            walkingPath.setSize(width-20, height-35);
            walkingPath.setEditable(false);
        }
    }

    private class VisitedFieldLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            hasWalkedPath.setLocation(10, 20);
            hasWalkedPath.setSize(width-20, height-25);

            hasWalkedPath.setEditable(false);
            hasWalkedPath.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        }
    }

    private class UnvisitedFieldLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            nonWalkedPath.setLocation(10, 20);
            nonWalkedPath.setSize(width-20, height-25);
            nonWalkedPath.setEditable(false);
            nonWalkedPath.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        }
    }

    private static class MyButton extends JButton {
        MyButton(String text) {
            this.setText(text);
            this.setPreferredSize(new Dimension(80, 30));
        }
    }

    private static class MyLabel extends JLabel {
        MyLabel(String text) {
            this.setText(text);
            this.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
            this.setPreferredSize(new Dimension(100, 30));
        }
    }

    private static class MyText extends JTextField {
        MyText() {
            this.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        }
    }

    private static class MyArea extends JTextArea {
        MyArea() {
            this.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        }
    }

    // 画笔类
    private class MyPaint extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            int width =  740 ;//this.getWidth();
            int height =  830 ;//this.getHeight();

            g.setColor(Color.WHITE);
            g.fillRect(10, 20, width-20, height-30);

            // 执行矩形框绘制
            inputRect.drawMyRect(g);
            initialRect.drawMyRect(g);
            equalABRect.drawMyRect(g);
            equalACRect.drawMyRect(g);
            equalBCRect.drawMyRect(g);
            resultRect1.drawMyRect(g);
            resultRect2.drawMyRect(g);
            resultRect3.drawMyRect(g);
            resultRect4.drawMyRect(g);

            // 条件判断框绘制
            checkABEqual.drawMyDiamond(g);
            checkACEqual.drawMyDiamond(g);
            checkBCEqual.drawMyDiamond(g);
            checkMatchEqual0.drawMyDiamond(g);
            checkMatchEqual1.drawMyDiamond(g);
            checkMatchEqual2.drawMyDiamond(g);
            checkMatchEqual3.drawMyDiamond(g);
            checkACB1.drawMyDiamond(g);
            checkBCA1.drawMyDiamond(g);
            checkABC1.drawMyDiamond(g);
            checkABC2.drawMyDiamond(g);
            checkBCA2.drawMyDiamond(g);
            checkACB2.drawMyDiamond(g);

            //路径绘制
            inputToInitial.drawMyEdge(g);
            initialToCheckABEqual.drawMyEdge(g);
            checkABEqualToCheckACEqual.drawMyEdge(g);
            checkACEqualToCheckBCEqual.drawMyEdge(g);
            checkABEqualToEqualAB.drawMyEdge(g);
            EqualABToCheckACEqual.drawMyEdge(g);
            checkACEqualToEqualAC.drawMyEdge(g);
            EqualACToCheckBCEqual.drawMyEdge(g);
            checkBCEqualToEqualBC.drawMyEdge(g);
            EqualBCToCheckMatchEqual0.drawMyEdge(g);
            checkBCEqualToCheckMatchEqual0.drawMyEdge(g);
            checkMatchEqual0ToCheckMatchEqual1.drawMyEdge(g);
            checkMatchEqual1ToCheckMatchEqual2.drawMyEdge(g);
            checkMatchEqual2ToCheckMatchEqual3.drawMyEdge(g);
            checkMatchEqual0ToCheckABC2.drawMyEdge(g);
            checkMatchEqual1ToCheckABC1.drawMyEdge(g);
            checkMatchEqual2ToCheckACB1.drawMyEdge(g);
            checkMatchEqual3ToCheckBCA1.drawMyEdge(g);
            checkABC2ToCheckBCA2.drawMyEdge(g);
            checkBCA2ToCheckACB2.drawMyEdge(g);
            checkMatchEqual3ToResultRect1.drawMyEdge(g);
            checkACB1ToResultRect2.drawMyEdge(g);
            checkBCA1ToResultRect2.drawMyEdge(g);
            checkABC1ToResultRect2.drawMyEdge(g);
            checkACB1ToResultRect3.drawMyEdge(g);
            checkBCA1ToResultRect3.drawMyEdge(g);
            checkABC1ToResultRect3.drawMyEdge(g);
            checkABC2ToResultRect3.drawMyEdge(g);
            checkBCA2ToResultRect3.drawMyEdge(g);
            checkACB2ToResultRect3.drawMyEdge(g);
            checkACB2ToResultRect4.drawMyEdge(g);

            g.setColor(Color.BLACK);

            // 绘制编号
            g.drawString("1", width / 2 - 60, 167);
            g.drawString("2", width * 3 / 4 + 70, 167);
            g.drawString("3", width / 2 - 60, 227);
            g.drawString("4", width * 3 / 4 + 70, 227);
            g.drawString("5", width / 2 - 60, 287);
            g.drawString("6", width * 3 / 4 + 70, 287);
            g.drawString("7", width / 2 - 20, 330);
            g.drawString("8", width * 3 / 4 + 100, 430);
            g.drawString("9", width * 3 / 4 + 100, 520);
            g.drawString("10", width * 3 / 4 + 100, 610);
            g.drawString("11", width * 3 / 4 + 85, height - 25);
            g.drawString("12", width / 2 + 75, height - 25);
            g.drawString("13", width / 4 + 15, 380);
            g.drawString("14", width / 2 + 20, 430);
            g.drawString("15", width / 4 + 75, height - 25);
            g.drawString("16", 60, 430);
            g.drawString("17", width / 4 + 40, 490);
            g.drawString("18", 60, 550);
            g.drawString("19", width / 4 - 10, 610);
            g.drawString("20", 70, height - 25);

            // 绘制路径正误
            g.drawString("Y", width / 2 + 60, 160);
            g.drawString("Y", width / 2 + 60, 220);
            g.drawString("Y", width / 2 + 60, 280);
            g.drawString("Y", width / 2 + 60, 345);
            g.drawString("Y", width / 2 + 60, 447);
            g.drawString("Y", width * 3 / 4 + 25, 447);
            g.drawString("Y", width * 3 / 4 + 25, 537);
            g.drawString("Y", width * 3 / 4 + 25, 627);
            g.drawString("Y", width / 4 + 90, 395);
            g.drawString("Y", width / 4 + 90, 505);
            g.drawString("Y", 140, 447);
            g.drawString("Y", 140, 567);
            g.drawString("Y", width / 4 + 40, 627);

            g.drawString("N", width / 2 - 10, 195);
            g.drawString("N", width / 2 - 10, 255);
            g.drawString("N", width / 2 - 10, 315);
            g.drawString("N", width / 2 + 10, 475);
            g.drawString("N", width * 3 / 4 + 100, 475);
            g.drawString("N", width * 3 / 4 + 100, 565);
            g.drawString("N", width * 3 / 4 + 100, 655);
            g.drawString("N", 65, 475);
            g.drawString("N", 65, 595);
            g.drawString("N", width / 2 - 60, 345);
            g.drawString("N", width / 4 - 30, 395);
            g.drawString("N", width / 4 + 25, 535);
            g.drawString("N", width / 4 - 15, 665);
        }
        MyPaint(String title, Color titleColor) {
            this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    title, TitledBorder.LEFT, TitledBorder.CENTER, new Font("宋体", Font.BOLD, 16), titleColor));
        }
    }

    // 矩形类
    private static class MyRect {
        private final int x;
        private final int y;
        private final String content;
        private Color color;
        private final int index;
        MyRect(int x, int y, String content, Color color, int index) {
            this.x = x;
            this.y = y;
            this.content = content;
            this.color = color;
            this.index = index;
        }

        public void drawMyRect(Graphics g) {
            g.setColor(this.color);
            g.drawRect(this.x, this.y, 100, 35);
            g.setColor(Color.BLACK);
            g.drawString(this.content, this.x+20, this.y+20);
        }

        public void changeColor() {
            this.color = Color.RED;
        }

        public void resetColor() {
            this.color = Color.BLACK;
        }
    }

    // 菱形类
    private static class MyDiamond {
        private final int centerX;
        private final int centerY;
        private final int startX;
        private final String content;
        private Color color;
        private final int index;

        MyDiamond(int centerX, int centerY, int startX, String content, Color color, int index) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.startX = startX;
            this.content = content;
            this.color = color;
            this.index = index;
        }

        public void drawMyDiamond(Graphics g) {
            g.setColor(this.color);
            Polygon diamond = new Polygon();
            diamond.addPoint(this.centerX, this.centerY-17);
            diamond.addPoint(this.centerX+50, this.centerY);
            diamond.addPoint(this.centerX, this.centerY+17);
            diamond.addPoint(this.centerX-50, this.centerY);
            g.drawPolygon(diamond);
            g.setColor(Color.BLACK);
            g.drawString(this.content, this.centerX-this.startX, this.centerY+5);
        }

        public void changeColor() {
            this.color = Color.RED;
        }

        public void resetColor() {
            this.color = Color.BLACK;
        }
    }

    // 执行路径类
    private static class MyEdge {
        private final int tailX;
        private final int tailY;
        private final int headX;
        private final int headY;
        private Color color;
        private final int tailIndex;
        private final int headIndex;

        MyEdge(int tailX, int tailY, int headX, int headY, Color color, int tailIndex, int headIndex) {
            this.tailX = tailX;
            this.tailY = tailY;
            this.headX = headX;
            this.headY = headY;
            this.color = color;
            this.tailIndex = tailIndex;
            this.headIndex = headIndex;
        }

        public void drawMyEdge(Graphics g) {
            g.setColor(this.color);
            g.drawLine(this.tailX, this.tailY, this.headX, this.headY);
        }

        public void changeColor() {
            this.color = Color.RED;
        }

        public void resetColor() {
            this.color = Color.BLACK;
        }
    }
}

