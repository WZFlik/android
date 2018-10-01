package comh.example.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.xml.transform.stream.StreamResult;
/*test string for git */

public class MainActivity extends AppCompatActivity {
    //之定义了数字和加号,等号和各个数字按钮
    private Button bt_0,bt_1,bt_2,bt_3,bt_4,bt_5,bt_6,bt_7,bt_8,bt_9,bt_plus,bt_equel,bt_ac;
    private EditText et_result;//输入框
    private final String TAG="MYIFO";//输出日志
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//绑定各个按钮变量
        bt_0=(Button)findViewById(R.id.bt5_1);
        bt_1=(Button)findViewById(R.id.bt4_1);
        bt_2=(Button)findViewById(R.id.bt4_2);
        bt_3=(Button)findViewById(R.id.bt4_3);
        bt_4=(Button)findViewById(R.id.bt3_1);
        bt_5=(Button)findViewById(R.id.bt3_2);
        bt_6=(Button)findViewById(R.id.bt3_3);
        bt_7=(Button)findViewById(R.id.bt2_1);
        bt_8=(Button)findViewById(R.id.bt2_2);
        bt_9=(Button)findViewById(R.id.bt2_3);
        bt_plus=(Button)findViewById(R.id.bt4_4);
        bt_equel=(Button)findViewById(R.id.bt5_3);
        et_result=(EditText) findViewById(R.id.text);
        bt_ac=(Button)findViewById(R.id.bt1_1);
//阻册监听器
        MyonClickLis myonclicklis=new MyonClickLis();
        bt_0.setOnClickListener(myonclicklis);
        bt_1.setOnClickListener(myonclicklis);
        bt_2.setOnClickListener(myonclicklis);
        bt_3.setOnClickListener(myonclicklis);
        bt_4.setOnClickListener(myonclicklis);
        bt_5.setOnClickListener(myonclicklis);
        bt_6.setOnClickListener(myonclicklis);
        bt_7.setOnClickListener(myonclicklis);
        bt_8.setOnClickListener(myonclicklis);
        bt_9.setOnClickListener(myonclicklis);
        bt_plus.setOnClickListener(myonclicklis);
        bt_equel.setOnClickListener(myonclicklis);
        bt_ac.setOnClickListener(myonclicklis);

    }


    //自定义一个监听器
    public class MyonClickLis implements View.OnClickListener{
//定义必要的私有变量
        private String strOp1="0";//第一个输入的操作数
        private String strOp2="";//第二输入的操作数
        private  String operation="";//当前使用的运算符
        private String  strResult="0";//当前的运行结果
        private String lastInputType="0";//最后输入的运算符，0=数字，1=运算符；

        /**
         * 输入数字时的操作方法
         */
        private void NumInput(int num){
            Log.i(TAG, "输入了: num");
            if(strResult.equals("0")){   //如果输入数字是文本框内是0，直接主
                strResult=""+num;
            }
            else if(lastInputType=="0"){//如果上次输入的是数字直接追加到其后
                strResult=strResult+num;
            }
            else if(lastInputType=="1"){//如果上次输入的是运算符，输入框显示为当前数字
                strResult=""+num;
            }
            et_result.setText(strResult);//更新输入框显示内容
            lastInputType="0";//将最后输入符号变为数字
        }

        /**
         * 处理运算符运算 + - * /
         * @param
         */
        private void operationInout(String opt){
            //如果上一个输入的是运算符,代替上一个运算符
            if(lastInputType=="1"){
                operation=opt;
                lastInputType="1";
                strOp1=strResult;
                return;
            }
            //如果上一个operation为空则，设置为当前opt
            if(operation.isEmpty()){
                operation=opt;
                lastInputType="1";
                strOp1=strResult;
            }else if(!strOp1.isEmpty()){ //比如输入了1+2+ +是当前输入的，1在输入第一个+时付给StrOp1；
             strOp2= strResult;//设置第二个操作数的值
                //处理加法运算
                if(operation.equals("+")){
                double opt1=Double.parseDouble(strOp1);
                double opt2=Double.parseDouble(strOp2);
                strResult= String.valueOf(opt1+opt2);
                et_result.setText(strResult);
                strOp1=strResult;//设置当前值为第一个操作数
                strOp2="";//设置第二操作数格式化
                    operation=opt;
                }
            }
             //待添加算法逻辑（-、*、/）
        }

        /**
         * 处理%运算符
         * @param
         */
        private void percentInput(){
            strResult=String.valueOf(Double.parseDouble(strResult)/100);
            et_result.setText(strResult);

        }

        /**
         * 处理AC键
         * @param
         */
        private void ACInput(){
        strResult="0";
        lastInputType="0";
        strOp2=strOp1="0";
        et_result.setText(strResult);

        }

        /**
         * 处理点号  .
         * @param v
         */
      private void dotInput(){
    if(strResult.indexOf('.')==-1){
        strResult=strResult+".";
        et_result.setText(strResult);
    }
      }

        /**
         * 处理等号运算符
         * @param
         */
      private void equalInput( ){
         if(operation.isEmpty()){//没有运算符输入时直接返回
             return;
         }
         if(lastInputType=="1"&&!strOp1.isEmpty()){//最后输入的是运算符，第一个操作数不为空时，如1+=
           if(operation.equals("+")){
               double op1= Double.parseDouble(strOp1);
               double op2=Double.parseDouble(strResult);
               strResult=String.valueOf(op1+op2);
               et_result.setText(strResult);
           }


         }
         else if(!strOp1.isEmpty()&&lastInputType=="0"){//处理正常1+2=，=是当前输入
             strOp2=strResult;
             if (operation.equals("+")){
                 double op1= Double.parseDouble(strOp1);
                 double op2=Double.parseDouble(strResult);
                 strResult=String.valueOf(op1+op2);
                 et_result.setText(strResult);//出结果并清零
                 strOp2=strOp1="";
                 operation="";
             }
         }
      }


        //重写方法
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt5_1:
                    NumInput(0);
                    break;
                case R.id.bt4_1:
                    NumInput(1);
                    break;
                case R.id.bt4_2:
                    NumInput(2);
                    break;
                case R.id.bt4_3:
                    NumInput(3);
                    break;
                case R.id.bt3_1:
                    NumInput(4);
                    break;
                case R.id.bt3_2:
                    NumInput(5);
                    break;
                case R.id.bt3_3://6
                    NumInput(6);
                    break;
                case R.id.bt2_1:
                    NumInput(7);
                    break;
                case R.id.bt2_2:
                    NumInput(8);
                    break;
                case R.id.bt2_3://9
                    NumInput(9);
                    break;
                case R.id.bt4_4:
                    operationInout("+");
                    break;
                case R.id.bt5_3://BUTTON equel;
                    equalInput();
                    break;
                case R.id.bt1_1:
                    ACInput();
                    break;
                default:
                    break;




            }
        }
    }












//overrid  eonCreateOptionsMenu  function

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //menu item响应

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Item_f:
                Toast.makeText(this,"you clicked copy!",Toast.LENGTH_LONG).show();
                break;
            case R.id.Item_p:
                Toast.makeText(this,"you clicked paste!",Toast.LENGTH_LONG).show();
                break;
            case R.id.Item_j:
                Toast.makeText(this,"you clicked cut !",Toast.LENGTH_LONG).show();
                break;
                default:;
        }
        return true;
    }
}
