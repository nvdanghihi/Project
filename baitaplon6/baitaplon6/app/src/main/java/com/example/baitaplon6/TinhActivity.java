package com.example.baitaplon6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TinhActivity extends AppCompatActivity {
    // Khai báo các số 0-9...
    private Button num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
    // Khai báo các nút chức năng
    private Button sin, cos, tan, cot, mod, cong, tru, nhan, chia, bang, dot, back, clear, dau;
    // TextView để hiển thị phép tính và kết quả
    private TextView pheptinh, ketqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.maytinh_layout);
        khaibao();

        bang.setOnClickListener(view -> ketqua.setVisibility(View.VISIBLE));

        setNumberButtonListeners();
        setOperationButtonListeners();
    }

    //Phương thức khaibao để khởi tạo các biến.
    private void khaibao() {
        num0 = findViewById(R.id.btn0);
        num1 = findViewById(R.id.btn1);
        num2 = findViewById(R.id.btn2);
        num3 = findViewById(R.id.btn3);
        num4 = findViewById(R.id.btn4);
        num5 = findViewById(R.id.btn5);
        num6 = findViewById(R.id.btn6);
        num7 = findViewById(R.id.btn7);
        num8 = findViewById(R.id.btn8);
        num9 = findViewById(R.id.btn9);
        sin = findViewById(R.id.btnsin);
        cot = findViewById(R.id.btncot);
        tan = findViewById(R.id.btntan);
        mod = findViewById(R.id.btnmod);
        cos = findViewById(R.id.btncos);
        cong = findViewById(R.id.btncong);
        tru = findViewById(R.id.btntru);
        nhan = findViewById(R.id.btnnhan);
        chia = findViewById(R.id.btnchia);
        back = findViewById(R.id.btnback);
        bang = findViewById(R.id.btnbang);
        dot = findViewById(R.id.btnDot);
        clear = findViewById(R.id.btnclear);
        dau = findViewById(R.id.btndoidau);
        pheptinh = findViewById(R.id.txtpheptinh);
        ketqua = findViewById(R.id.txtketqua);
    }
    //Xét các sự kiện cho number và dot.
    private void setNumberButtonListeners() {
        View.OnClickListener numberListener = v -> {
            String input = pheptinh.getText().toString();
            pheptinh.setText(input + ((Button) v).getText().toString());
        };

        num0.setOnClickListener(numberListener);
        num1.setOnClickListener(numberListener);
        num2.setOnClickListener(numberListener);
        num3.setOnClickListener(numberListener);
        num4.setOnClickListener(numberListener);
        num5.setOnClickListener(numberListener);
        num6.setOnClickListener(numberListener);
        num7.setOnClickListener(numberListener);
        num8.setOnClickListener(numberListener);
        num9.setOnClickListener(numberListener);

        dot.setOnClickListener(view -> {
            String input = pheptinh.getText().toString();
            if (!input.contains(".")) {
                pheptinh.setText(input + ".");
            }
        });
    }
    //Tạo sự kiện cho nút tính toán,lượng giác,...
    private void setOperationButtonListeners() {
        cong.setOnClickListener(v -> addOperator("+"));
        tru.setOnClickListener(v -> addOperator("-"));
        nhan.setOnClickListener(v -> addOperator("*"));
        chia.setOnClickListener(v -> addOperator("/"));
        mod.setOnClickListener(v -> addOperator("mod"));
        sin.setOnClickListener(v -> addFunction("sin"));
        cos.setOnClickListener(v -> addFunction("cos"));
        tan.setOnClickListener(v -> addFunction("tan"));
        cot.setOnClickListener(v -> addFunction("cot"));

        bang.setOnClickListener(v -> calculateResult());

        dau.setOnClickListener(v -> {
            String input = pheptinh.getText().toString();
            //Kiểm tra chuỗi
            if (input.isEmpty() || input.endsWith("+") || input.endsWith("-") || input.endsWith("*") || input.endsWith("/") || input.endsWith("mod")) {
                pheptinh.setText(input + "-");
            } else {
                // Neu biêu thuc bat dau bang dau tru thi loai bo dau
                if (input.startsWith("-")) {
                    pheptinh.setText(input.substring(1));
                } else {
                    pheptinh.setText("-" + input);
                }
            }
        });
        // Xoa toan bo bieu thuc và kết quả hiện tại.
        clear.setOnClickListener(v -> {
            pheptinh.setText("");
            ketqua.setText("");
        });

        back.setOnClickListener(v -> {
            String input = pheptinh.getText().toString();
            //Ktra chuỗi
            if (!input.isEmpty()) {
                //Xoa ki tự cuối cùng của chuỗi.
                input = input.substring(0, input.length() - 1);
                pheptinh.setText(input);
            }
        });
    }
    //Thêm toán tử
    private void addOperator(String operator) {
        String input = pheptinh.getText().toString();
        //Kiểm tra nếu ký tự cuối cùng này không phải là một trong các toán tử (+, -, *, /).
        //Không có 2 toán tử liền nhau.
        if (!input.isEmpty()) {
            char lastChar = input.charAt(input.length() - 1);
            if (lastChar != '+' && lastChar != '-' && lastChar != '*' && lastChar != '/') {
                //Thêm các toán tử vào chuỗi
                pheptinh.setText(input + operator);
            }
        }
    }
    //Thêm hàm
    private void addFunction(String function) {
        String input = pheptinh.getText().toString();
        pheptinh.setText(input + function);
    }
    //Tính toán và hiển thị kết quả.
    private void calculateResult() {
        String input = pheptinh.getText().toString();
        String result;

        if (input.contains("mod")) {
            // Xử lý  tính mod
            String[] parts = input.split("mod");
            if (parts.length == 2) {
                try {
                    double a = Double.parseDouble(parts[0].trim());
                    double b = Double.parseDouble(parts[1].trim());
                    double modResult = modresult(a, b);
                    result = String.valueOf(modResult);
                } catch (NumberFormatException e) {
                    result = "Error";
                }
            } else {
                result = "Error";
            }
        } else {
            // Thay thế các toán tử
            String replacedString = input.replace('÷', '/').replace('×', '*');
            try {
                double evalResult = eval(replacedString);
                result = String.valueOf(evalResult);
            } catch (Exception e) {
                result = "Error";
            }
        }

        pheptinh.setText(input);
        ketqua.setText(result);
    }
    //Tính toán kqua của phép toán mod.
    public double modresult(double a, double b) {

        return a % b;
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();

                    if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("cot")) x = 1 / Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
