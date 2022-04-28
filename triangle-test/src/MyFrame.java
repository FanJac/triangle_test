import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.io.*;

public class MyFrame extends JFrame {

    JPanel myPanel = new JPanel();

    JTextField a_min_text = new JTextField();
    JTextField a_max_text = new JTextField();
    JLabel error1 = new JLabel("");

    JTextField b_min_text = new JTextField();
    JTextField b_max_text = new JTextField();
    JLabel error2 = new JLabel("");


    JTextField c_min_text = new JTextField();
    JTextField c_max_text = new JTextField();
    JLabel error3 = new JLabel("");

    int[] inputArray = new int[6];


    JComboBox cmb = new JComboBox();
    JButton generate_btn = new JButton("生成测试结果");
    JButton clear_btn = new JButton("清除数据");

    JButton exit_btn = new JButton("退出");
    JButton save_btn = new JButton("保存结果");
    
    String result = "";

    public MyFrame(String title) {
        super(title);

        this.setSize(870, 700);
        InitGlobalFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        this.setContentPane(myPanel);

        myPanel.setLayout(null);

        JLabel a_label = new JLabel("请输入a的取值范围：");
        a_label.setBounds(250, 10, 150, 20);
        myPanel.add(a_label);
        a_min_text.setBounds(380, 10, 80, 20);
        myPanel.add(a_min_text);
        JLabel a_dash = new JLabel("~");
        a_dash.setBounds(465, 10, 10, 20);
        myPanel.add(a_dash);
        a_max_text.setBounds(478, 10, 80, 20);
        myPanel.add(a_max_text);
        error1.setBounds(580, 10, 200, 20);
        error1.setForeground(Color.RED);
        myPanel.add(error1);

        JLabel b_label = new JLabel("请输入b的取值范围：");
        b_label.setBounds(250, 40, 150, 20);
        myPanel.add(b_label);
        b_min_text.setBounds(380, 40, 80, 20);
        myPanel.add(b_min_text);
        JLabel b_dash = new JLabel("~");
        b_dash.setBounds(465, 40, 10, 20);
        myPanel.add(b_dash);
        b_max_text.setBounds(478, 40, 80, 20);
        myPanel.add(b_max_text);
        error2.setBounds(580, 40, 200, 20);
        error2.setForeground(Color.RED);
        myPanel.add(error2);
        
        JLabel c_label = new JLabel("请输入c的取值范围：");
        c_label.setBounds(250, 70, 150, 20);
        myPanel.add(c_label);
        c_min_text.setBounds(380, 70, 80, 20);
        myPanel.add(c_min_text);
        JLabel c_dash = new JLabel("~");
        c_dash.setBounds(465, 70, 10, 20);
        myPanel.add(c_dash);
        c_max_text.setBounds(478, 70, 80, 20);
        myPanel.add(c_max_text);
        error3.setBounds(580, 70, 200, 20);
        error3.setForeground(Color.RED);
        myPanel.add(error3);

        JLabel type_label = new JLabel("请选择测试类型：");
        type_label.setBounds(250, 100, 150, 20);
        myPanel.add(type_label);
        cmb.addItem("边界值测试");
        cmb.addItem("健壮性边界值测试");
        cmb.addItem("最坏情况边界值测试");
        cmb.addItem("健壮性最坏情况边界值测试");
        cmb.setBounds(380, 100, 180, 20);
        myPanel.add(cmb);
        

        String initial_str = "";
        String split_line = "+---------------------------------------------------------------------------------------+";
        initial_str += split_line;
        initial_str += '\n';
        initial_str += "｜序号\t｜a边长\t｜b边长\t｜c边长\t｜三角形类型\t｜";
        initial_str += '\n';
        initial_str += split_line;
        JTextArea resultTextArea = new JTextArea(initial_str);
        resultTextArea.setBorder(BorderFactory.createEtchedBorder());
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBounds(140, 180, 580, 400);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myPanel.add(scrollPane);


        generate_btn.setBounds(250, 130, 120, 20);
        generate_btn.setFont(new java.awt.Font("仿宋", 1, 13));
        myPanel.add(generate_btn);
        generate_btn.addActionListener((e) -> {
            try {
                getInput();
                int test_type = cmb.getSelectedIndex();
                ArrayList<String> output = MyFunc.generate_test_result(inputArray, test_type);
                String output_str = "";
                for (String s : output) {
                    output_str += s;
                    output_str += '\n';
                }
                resultTextArea.setText(output_str);
                result = resultTextArea.getText();
            } catch (MyException ex) {
                ex.printStackTrace();
            }

        });

        // 清除当前测试用例
        JLabel suc = new JLabel("保存成功!");
        suc.setBounds(326, 620, 100, 20);
        clear_btn.setBounds(440, 130, 120, 20);
        clear_btn.setFont(new java.awt.Font("仿宋", 1, 13));
        myPanel.add(clear_btn);
        String finalInitial_str = initial_str;
        clear_btn.addActionListener((e) -> {
            clearAll();
            suc.setText("");
            myPanel.updateUI();
            resultTextArea.setText(finalInitial_str);
        });


        exit_btn.setBounds(440, 600, 100, 20);
        exit_btn.setFont(new java.awt.Font("仿宋", 1, 13));
        myPanel.add(exit_btn);
        exit_btn.addActionListener((e) -> {
        	
            System.exit(0);
        });

        // 保存测试用例
        save_btn.setBounds(320, 600, 100, 20);
        save_btn.setFont(new java.awt.Font("仿宋", 1, 13));
        myPanel.add(save_btn);
        save_btn.addActionListener((e)->{

            myPanel.add(suc);
            myPanel.updateUI();

        	generate_txt();
        });

        this.setVisible(true);
    }


    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }


    public void getInput() throws MyException {
        int a_min = 0, a_max = 0, b_min = 0, b_max = 0, c_min = 0, c_max = 0;
        if (a_min_text.getText().contains(".") || a_max_text.getText().contains(".")) {
            error1.setText("不支持浮点数，请输入整数");
            throw new MyException();
        }
        try {
            a_min = Integer.valueOf(a_min_text.getText()).intValue();
            a_max = Integer.valueOf(a_max_text.getText()).intValue();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (a_min <= 0 || a_max <= 0) {
            error1.setText("请输入大于0的整数");
            throw new MyException();
        }
        if (a_min >= a_max) {
            error1.setText("请输入合法区间");
            throw new MyException();
        }
        else {
            error1.setText("");
        }

        inputArray[0] = a_min;
        inputArray[1] = a_max;

        if (b_min_text.getText().contains(".") || b_max_text.getText().contains(".")) {
            error2.setText("不支持浮点数，请输入整数");
            throw new MyException();
        }
        try {
            b_min = Integer.valueOf(b_min_text.getText()).intValue();
            b_max = Integer.valueOf(b_max_text.getText()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (b_min <= 0 || b_max <= 0) {
            error2.setText("请输入大于0的整数");
            throw new MyException();
        }
        if (b_min >= b_max) {
            error2.setText("请输入合法区间");
            throw new MyException();
        }
        else {
            error2.setText("");
        }

        inputArray[2] = b_min;
        inputArray[3] = b_max;


        if (c_min_text.getText().contains(".") || c_max_text.getText().contains(".")) {
            error3.setText("不支持浮点数，请输入整数");
            throw new MyException();
        }
        try {
            c_min = Integer.valueOf(c_min_text.getText()).intValue();
            c_max = Integer.valueOf(c_max_text.getText()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c_min <= 0 || c_max <= 0) {
            error3.setText("请输入大于0的整数");
            throw new MyException();
        }
        if (c_min >= c_max) {
            error3.setText("请输入合法区间");
            throw new MyException();
        }
        else {
            error3.setText("");
        }

        inputArray[4] = c_min;
        inputArray[5] = c_max;
    }

    public void clearAll() {
        a_min_text.setText("");
        a_max_text.setText("");
        b_min_text.setText("");
        b_max_text.setText("");
        c_min_text.setText("");
        c_max_text.setText("");
    }
    
    public void generate_txt() {
    	String filePath = "./result.txt";
    	String context = result;
    	FileWriter fw = null;
    	try {
    		File file = new File(filePath);
    		if(!file.exists()) {
    			file.createNewFile();
    		}
    		fw = new FileWriter(filePath);
    		fw.write(context);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			fw.close();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
}
