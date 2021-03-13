package com.fanxiaoyudemo.magicalwardrobe.Tool;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by fanxi on 2020/2/24.
 */

public class UserData extends Application {
    private String PhoneNum = "15730156425";
    private String UserName = "啦啦啦开心的大帅";
    private File Avater;
    private String Password = "123456";
    private String Sex = "0";
    private List<String> Style=new ArrayList<String>();
    private String Requestion = "我叫什么名字";
    private String Answer = "你猜";
    private List<Cloth> ClothList_0=new ArrayList<Cloth>();
    private List<Cloth> ClothList_1=new ArrayList<Cloth>();
    private List<Cloth> ClothList_2=new ArrayList<Cloth>();
    private List<Cloth> ClothList_3=new ArrayList<Cloth>();
    private List<Cloth> ClothList_4=new ArrayList<Cloth>();
    private List<Cloth> ClothList_5=new ArrayList<Cloth>();
    private String ModelURL ="https://www.baidu.com/";
    private String ClassifyCode="0";
    private int ItemCurrent=0;

    String BOUNDARY = java.util.UUID.randomUUID().toString();
    String PREFIX = "--", LINEND = "\r\n";


    public String getPhoneNum() {
        return this.PhoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.PhoneNum = phoneNum;
    }

    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public File getAvater() {
        return this.Avater;
    }
    public void setAvater(File avater) {
        this.Avater = avater;
    }

    public String getPassword() {
        return this.Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }

    public String getSex() {
        return this.Sex;
    }
    public void setSex(String sex) {
        this.Sex = sex;
    }

    public List getStyle() {
        return this.Style;
    }
    public void setStyle(List style) {
        this.Style = style;
    }

    public String getRequestion() {
        return this.Requestion;
    }
    public void setRequestion(String requestion) {
        this.Requestion = requestion;
    }

    public String getAnswer() {
        return this.Answer;
    }
    public void setAnswer(String answer) {
        this.Answer = answer;
    }

    public String getClassifyCode() {
        return this.ClassifyCode;
    }
    public void setClassifyCode(String classifyCode) {
        this.ClassifyCode=classifyCode;
    }

    public String getModelURL() {
        return this.ModelURL;
    }
    public void setModelURL(String modelURL) {
        this.ModelURL = modelURL;
    }

    public List getClothList0() {
        return this.ClothList_0;
    }
    public void setClothList0(List clothList) {
        this.ClothList_0 = clothList;
    }

    public List getClothList1() {
        return this.ClothList_1;
    }
    public void setClothList1(List clothList) {
        this.ClothList_1 = clothList;
    }

    public List getClothList2() {
        return this.ClothList_2;
    }
    public void setClothList2(List clothList) {
        this.ClothList_2 = clothList;
    }

    public List getClothList3() {
        return this.ClothList_3;
    }
    public void setClothList3(List clothList) {
        this.ClothList_3 = clothList;
    }

    public List getClothList4() {
        return this.ClothList_4;
    }
    public void setClothList4(List clothList) {
        this.ClothList_4 = clothList;
    }

    public List getClothList5() {
        return this.ClothList_5;
    }
    public void setClothList5(List clothList) {
        this.ClothList_5 = clothList;
    }

    public int getCurrentItem() {
        return this.ItemCurrent;
    }
    public void setCurrentItem(int itemCurrent) {
        this.ItemCurrent=itemCurrent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //接口函数
    //登陆函数(POST)
    public int login_function(String phoneNum, String password) throws Exception {
        String path = "http://47.101.147.32:8000/user/login";
        //String path="https://www.studyinghome.com/mock/5e6912288f77ec19c7ddc792/FanXiaoyu/user/login";
        int code = -1;
        URL url = new URL(path);
        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum", phoneNum);
        json_1.put("Password", password);
        json.put("data", json_1);

        String s = json.toString();

        conn.setRequestMethod("POST");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        //界面上所有的参数名加上他的值
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //设置允许对外输出数据


        byte[] writebytes = s.getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
        OutputStream out = conn.getOutputStream();
        out.write(s.getBytes());
        out.flush();
        out.close();

        int responseCode=conn.getResponseCode();
        if (responseCode == 200) {
            //用io流与web后台进行数据交互
            InputStream is = conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
            code = parse_login(str);
            return code;
        }
        return code;
    }

    //找回密码1
    public int find_password_function(String phoneNum) throws Exception {
        String path = "http://47.101.147.32:8000/user/findpassword1";
        int code = -1;
        URL url = new URL(path);
        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum", phoneNum);
        json.put("data", json_1);

        String s = json.toString();

        conn.setRequestMethod("POST");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        //界面上所有的参数名加上他的值
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //设置允许对外输出数据

        byte[] writebytes = s.getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
        OutputStream out = conn.getOutputStream();
        out.write(s.getBytes());
        out.flush();
        out.close();

        int responseCode=conn.getResponseCode();
        if (responseCode == 200) {
            //用io流与web后台进行数据交互
            InputStream is = conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
            code = parse_find_password(str);
            return code;
        }
        return code;
    }

    //找回密码2
    public int find_password_function_2(String phoneNum, String answer) throws Exception {
        String path = "http://47.101.147.32:8000/user/findpassword2";
        int code = -1;
        URL url = new URL(path);
        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum", phoneNum);
        json_1.put("Answer",answer);
        json.put("data", json_1);

        String s = json.toString();

        conn.setRequestMethod("POST");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        //界面上所有的参数名加上他的值
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //设置允许对外输出数据

        byte[] writebytes = s.getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
        OutputStream out = conn.getOutputStream();
        out.write(s.getBytes());
        out.flush();
        out.close();

        int responseCode=conn.getResponseCode();
        if (responseCode == 200) {
            //用io流与web后台进行数据交互
            InputStream is = conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
            code = parse_find_password_2(str);
            return code;
        }
        return code;
    }

//    注册用户信息
    public int register_function(String phoneNum, String password, String userName, String sex, List<String> style, String requestion, String answer, File avater) throws Exception {
        String path = "http://47.101.147.32:8000/user/register";
        int code = -1;
        int TIME_OUT = 10000; //超时时间
        String CHARSET = "utf-8"; //编码格式
        String FAILURE = "FAILURE";
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        JSONObject json_2=new JSONObject();
        JSONArray json_style=new JSONArray();
        for(int i=0;i<style.size();i++)
            json_style.put(style.get(i));
        json_1.put("PhoneNum",phoneNum);
        json_1.put("Password",password);
        json_1.put("UserName",userName);
        json_1.put("Sex",sex);
        json_1.put("Style",json_style);
        json_1.put("Requestion",requestion);
        json_1.put("Answer",answer);
        json.put("data",json_1);
        String s = json.toString();

        //边界标识 随机生成，这个作为boundary的主体内容
        String BOUNDARY = UUID.randomUUID().toString();

        String PREFIX = "--";
        //回车换行，用于调整协议头的格式
        String LINE_END = "\r\n";
        //格式的内容信息
        String CONTENT_TYPE = "multipart/form-data";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时时间
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            //允许输入流
            conn.setDoInput(true);
            //允许输出流
            conn.setDoOutput(true);
            //不允许使用缓存
            conn.setUseCaches(false);
            //请求方式
            conn.setRequestMethod("POST");
            //设置编码 utf-8
            conn.setRequestProperty("Charset", CHARSET);
            //设置为长连接
            conn.setRequestProperty("connection", "keep-alive");

            //这里设置请求方式以及boundary的内容，即上面生成的随机字符串
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);
            if (avater != null) {
                //当文件不为空，把文件包装并且上传
                //这里定义输出流，用于之后向服务器发起请求
                OutputStream outputSteam = conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputSteam);

                //这里的StringBuffer 用来拼接我们的协议头
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                sb.append("Content-Disposition: form-data; name=\"data\"" + LINE_END + LINE_END);
                sb.append(s + LINE_END);
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"Avater\"; filename=\"" + avater.getName() + "\"" + LINE_END);

                //这里Content-Type 传给后台一个mime类型的编码字段，用于识别扩展名
                sb.append("Content-Type: image/jpeg; charset=" + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(avater);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
            }
        int res = conn.getResponseCode();
        if (res == 200) {
            //用io流与web后台进行数据交互
            InputStream is_2=conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is_2);//写个工具类流转换成字符串
            code=parse_login(str);
            return code;
        }
        return code;
    }

    //修改用户信息
    public int edit_info_function(String phoneNum, String password, String userName, String sex, List<String> style, String requestion, String answer, File avater) throws Exception {
        String path = "http://47.101.147.32:8000/user/editinfo";
        int code = -1;
        int TIME_OUT = 10000; //超时时间
        String CHARSET = "utf-8"; //编码格式
        String FAILURE = "FAILURE";
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        JSONObject json_2=new JSONObject();
        JSONArray json_style=new JSONArray();
        for(int i=0;i<style.size();i++)
            json_style.put(style.get(i));
        json_1.put("PhoneNum",phoneNum);
        json_1.put("Password",password);
        json_1.put("UserName",userName);
        json_1.put("Sex",sex);
        json_1.put("Style",json_style);
        json_1.put("Requestion",requestion);
        json_1.put("Answer",answer);
        json.put("data",json_1);
        String s = json.toString();

        //边界标识 随机生成，这个作为boundary的主体内容
        String BOUNDARY = UUID.randomUUID().toString();

        String PREFIX = "--";
        //回车换行，用于调整协议头的格式
        String LINE_END = "\r\n";
        //格式的内容信息
        String CONTENT_TYPE = "multipart/form-data";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时时间
        conn.setReadTimeout(TIME_OUT);
        conn.setConnectTimeout(TIME_OUT);
        //允许输入流
        conn.setDoInput(true);
        //允许输出流
        conn.setDoOutput(true);
        //不允许使用缓存
        conn.setUseCaches(false);
        //请求方式
        conn.setRequestMethod("POST");
        //设置编码 utf-8
        conn.setRequestProperty("Charset", CHARSET);
        //设置为长连接
        conn.setRequestProperty("connection", "keep-alive");

        //这里设置请求方式以及boundary的内容，即上面生成的随机字符串
        conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                + BOUNDARY);
        if (avater != null) {
            OutputStream outputSteam = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputSteam);

            //这里的StringBuffer 用来拼接我们的协议头
            StringBuffer sb = new StringBuffer();
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            sb.append("Content-Disposition: form-data; name=\"data\"" + LINE_END + LINE_END);
            sb.append(s + LINE_END);
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            /**
             * 这里重点注意：
             * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
             * filename是文件的名字，包含后缀名的 比如:abc.png
             */
            sb.append("Content-Disposition: form-data; name=\"Avater\"; filename=\"" + avater.getName() + "\"" + LINE_END);

            //这里Content-Type 传给后台一个mime类型的编码字段，用于识别扩展名
            sb.append("Content-Type: image/jpeg; charset=" + CHARSET + LINE_END);
            sb.append(LINE_END);
            dos.write(sb.toString().getBytes());
            InputStream is = new FileInputStream(avater);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
            }
            is.close();
            dos.write(LINE_END.getBytes());
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
            dos.write(end_data);
            dos.flush();
        }
        int res = conn.getResponseCode();
        if (res == 200) {
            //用io流与web后台进行数据交互
            InputStream is_2=conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is_2);//写个工具类流转换成字符串
            code=parse_login(str);
            return code;
        }
        return code;
    }


    //获取用户信息
    public int get_information_function(String phoneNum) throws Exception {
        int code=-1;
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum",phoneNum);
        json.put("data",json_1);
        String S = json.toString();
        String s=URLEncoder.encode(S,"UTF-8");
        //获取网络上get方式提交的整个路径
        String path="http://47.101.147.32:8000/user/getinfo?data="+s;
        URL url = new URL(path);

        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        conn.setRequestMethod("GET");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(6000);
        conn.connect();

        int responseCode=conn.getResponseCode();
        //获取结果码
        if(responseCode==200){
            //用io流与web后台进行数据交互
            InputStream is=conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串

            code=parse_get_information(str);
            return code;
        }
        return code;
    }

    //模型获取和存储
//    public int get_and_save_model_function(String phoneNum,String modelURL) throws Exception {
//        String path="http://47.101.147.32:8080/user/addmodel";
//        int code = -1;
//        URL url = new URL(path);
//        //打开网络连接
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        //设置提交方式
//        JSONObject json = new JSONObject();
//        JSONObject json_1 = new JSONObject();
//        json_1.put("PhoneNum", phoneNum);
//        json_1.put("Model", modelURL);
//        json.put("data", json_1);
//
//        String s = json.toString();
//
//        conn.setRequestMethod("POST");
//        //设置网络超时时间
//        conn.setConnectTimeout(5000);
//        //界面上所有的参数名加上他的值
//        conn.setUseCaches(false);
//        conn.setRequestProperty("Connection", "Keep-Alive");
//        conn.setRequestProperty("Charsert", "UTF-8");
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setRequestProperty("accept", "application/json");
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//        //设置允许对外输出数据
//
//
//        byte[] writebytes = s.getBytes();
//        // 设置文件长度
//        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
//        OutputStream out = conn.getOutputStream();
//        out.write(s.getBytes());
//        out.flush();
//        out.close();
//
//        int responseCode=conn.getResponseCode();
//        if (responseCode == 200) {
//            //用io流与web后台进行数据交互
//            InputStream is = conn.getInputStream();
//            //字节流转字符流
//            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
//            code = parse_login(str);
//            return code;
//        }
//        return code;
//    }

    //虚拟试衣url获取
    public int virtual_fit_function(String phoneNum) throws Exception {
        int code=-1;
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum",phoneNum);
        json.put("data",json_1);
        String S = json.toString();
        String s=URLEncoder.encode(S,"UTF-8");
        //获取网络上get方式提交的整个路径
        String path="http://47.101.147.32:8000/user/getmodel"+"?data="+s;
        URL url = new URL(path);
        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        conn.setRequestMethod("GET");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(6000);
        conn.connect();
        //获取结果码
        if(conn.getResponseCode()==200){
            //用io流与web后台进行数据交互
            InputStream is=conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
            code=parse_virtual_fit(str);
            return code;
        }
        return code;
    }

    //获取衣服
    public int get_cloth_function(String phoneNum,String classifyCode) throws Exception {
        int code=-1;
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum",phoneNum);
        json_1.put("ClassifyCode",classifyCode);
        json.put("data",json_1);
        String S = json.toString();
        String s = URLEncoder.encode(S, "utf-8");
        //获取网络上get方式提交的整个路径
        String path="http://47.101.147.32:8000/wardrobe/getcloth"+"?data="+s;
        URL url = new URL(path);
        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        conn.setRequestMethod("GET");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(6000);
        conn.connect();
        int responseCode=conn.getResponseCode();
        //获取结果码
        if(conn.getResponseCode()==200){
            //用io流与web后台进行数据交互
            InputStream is=conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
            code=parse_get_cloth(str, Integer.parseInt(classifyCode));

            return code;
        }
        return code;
    }

    //添加衣服
    public int add_cloth_function(String phoneNum,String classifyCode,File clothPic) throws Exception {
        String path="http://47.101.147.32:8000/wardrobe/newcloth";
        int code = -1;
        int TIME_OUT = 10000; //超时时间
        String CHARSET = "utf-8"; //编码格式
        String FAILURE = "FAILURE";
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("PhoneNum",phoneNum);
        json_1.put("ClassifyCode",classifyCode);
        json.put("data",json_1);
        String s = json.toString();

        //边界标识 随机生成，这个作为boundary的主体内容
        String BOUNDARY = UUID.randomUUID().toString();

        String PREFIX = "--";
        //回车换行，用于调整协议头的格式
        String LINE_END = "\r\n";
        //格式的内容信息
        String CONTENT_TYPE = "multipart/form-data";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时时间
        conn.setReadTimeout(TIME_OUT);
        conn.setConnectTimeout(TIME_OUT);
        //允许输入流
        conn.setDoInput(true);
        //允许输出流
        conn.setDoOutput(true);
        //不允许使用缓存
        conn.setUseCaches(false);
        //请求方式
        conn.setRequestMethod("POST");
        //设置编码 utf-8
        conn.setRequestProperty("Charset", CHARSET);
        //设置为长连接
        conn.setRequestProperty("connection", "keep-alive");

        //这里设置请求方式以及boundary的内容，即上面生成的随机字符串
        conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                + BOUNDARY);
        if (clothPic != null) {
            OutputStream outputSteam = conn.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outputSteam);

            //这里的StringBuffer 用来拼接我们的协议头
            StringBuffer sb = new StringBuffer();
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            sb.append("Content-Disposition: form-data; name=\"data\"" + LINE_END + LINE_END);
            sb.append(s + LINE_END);
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            /**
             * 这里重点注意：
             * name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
             * filename是文件的名字，包含后缀名的 比如:abc.png
             */
            sb.append("Content-Disposition: form-data; name=\"ClothPic\"; filename=\"" + clothPic.getName() + "\"" + LINE_END);

            //这里Content-Type 传给后台一个mime类型的编码字段，用于识别扩展名
            sb.append("Content-Type: image/jpeg; charset=" + CHARSET + LINE_END);
            sb.append(LINE_END);
            dos.write(sb.toString().getBytes());
            InputStream is = new FileInputStream(clothPic);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
            }
            is.close();
            dos.write(LINE_END.getBytes());
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
            dos.write(end_data);
            dos.flush();
        }
        int res = conn.getResponseCode();
        if (res == 200) {
            //用io流与web后台进行数据交互
            InputStream is_2=conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is_2);//写个工具类流转换成字符串
            code=parse_login(str);
            return code;
        }
        return code;
    }

    //删除衣服
    public int delete_cloth_function(String clothNum) throws Exception {
        String path="http://47.101.147.32:8000/wardrobe/delcloth";
        int code = -1;
        URL url = new URL(path);
        //打开网络连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置提交方式
        JSONObject json = new JSONObject();
        JSONObject json_1 = new JSONObject();
        json_1.put("ClothNum", clothNum);
        json.put("data", json_1);

        String s = json.toString();

        conn.setRequestMethod("POST");
        //设置网络超时时间
        conn.setConnectTimeout(5000);
        //界面上所有的参数名加上他的值
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("accept", "application/json");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //设置允许对外输出数据


        byte[] writebytes = s.getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
        OutputStream out = conn.getOutputStream();
        out.write(s.getBytes());
        out.flush();
        out.close();

        int responseCode=conn.getResponseCode();
        if (responseCode == 200) {
            //用io流与web后台进行数据交互
            InputStream is = conn.getInputStream();
            //字节流转字符流
            String str = StreamChangeStrUtils.toChange(is);//写个工具类流转换成字符串
            code = parse_login(str);
            return code;
        }
        return code;
    }

    //解析函数
    private int parse_login(String json) {
        int code=-1;
        try {
            if(json != null && json.startsWith("\ufeff"))
            {
                json =  json.substring(1);
            }
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);
            code= Integer.parseInt(jsonObject.getString("code"));

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return code;
    }
    private int parse_find_password(String json) {
        int code=-1;
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);
            code= Integer.parseInt(jsonObject.getString("code"));
            String stirng_data = jsonObject.getString("data");
            JSONObject jsonObject_data=new JSONObject(stirng_data);
            String requestion=jsonObject_data.getString("Requestion");
            setRequestion(requestion);
            } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return code;
    }
    private int parse_find_password_2(String json) {
        int code=-1;
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);
            code= Integer.parseInt(jsonObject.getString("code"));
            String stirng_data = jsonObject.getString("data");
            JSONObject jsonObject_data=new JSONObject(stirng_data);
            String password=jsonObject_data.getString("Password");
            setPassword(password);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return code;
    }
    private int parse_get_information(String json) {
        int code=-1;
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);

            code= Integer.parseInt(jsonObject.getString("code"));

            String stirng_data = jsonObject.getString("data");
            JSONObject jsonObject_data=new JSONObject(stirng_data);

            String userName=jsonObject_data.getString("UserName");
            setUserName(userName);

            String avater_string=jsonObject_data.getString("Avater");
            setAvater(SaveImage.saveImages(avater_string,Avater,"avater.jpg"));

            String password=jsonObject_data.getString("Password");
            setPassword(password);

            String sex=jsonObject_data.getString("Sex");
            setSex(sex);

            JSONArray jsonList_style=new JSONArray(jsonObject_data.getString("Style"));
            List<String> style = new ArrayList<String>();
            for (int i = 0; i < jsonList_style.length(); i++) {
                style.add(jsonList_style.getString(i));
            }
            setStyle(style);

            String requestion=jsonObject_data.getString("Requestion");
            setRequestion(requestion);

            String answer=jsonObject_data.getString("Answer");
            setRequestion(answer);

        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
    private int parse_virtual_fit(String json) throws JSONException, MalformedURLException {
        int code=-1;
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);
            code= Integer.parseInt(jsonObject.getString("code"));
            String stirng_data = jsonObject.getString("data");
            JSONObject jsonObject_data=new JSONObject(stirng_data);
            String modelURL=jsonObject_data.getString("Model");
            if(modelURL.startsWith("http"))
                setModelURL(modelURL);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return code;
    }
    private int parse_get_cloth(String json,int classifyCode) throws JSONException, IOException {
        int code = -1;
            JSONObject jsonObject;
            jsonObject = new JSONObject(json);

            code = Integer.parseInt(jsonObject.getString("code"));
            if(code==0) {
                String stirng_data = jsonObject.getString("data");
                JSONObject jsonObject_data = new JSONObject(stirng_data);

                JSONArray jsonList_cloth = new JSONArray(jsonObject_data.getString("ClothList"));
                ClothList_0.clear();
                ClothList_1.clear();
                ClothList_2.clear();
                ClothList_3.clear();
                ClothList_4.clear();
                ClothList_5.clear();
                List<Cloth> clothList = new ArrayList<Cloth>();
                String urlTem;
                switch (classifyCode) {
                    case 0:
                        for (int i = 0; i < jsonList_cloth.length(); i++) {
                            JSONObject object = (JSONObject) jsonList_cloth.get(i);
                            urlTem = object.getString("ClothUrl");
                            ClothList_0.add(new Cloth("",null));
                            ClothList_0.get(i).setClothNum(object.getString("ClothNum"));
                            ClothList_0.get(i).setClothFile(SaveImage.saveImages(urlTem, ClothList_0.get(i).getClothFile(), "clothList0_"+i+".jpg"));
                        }
                        break;
                    case 1:
                        for (int i = 0; i < jsonList_cloth.length(); i++) {
                            JSONObject object = (JSONObject) jsonList_cloth.get(i);
                            urlTem = object.getString("ClothUrl");
                            ClothList_1.add(new Cloth("",null));
                            ClothList_1.get(i).setClothNum(object.getString("ClothNum"));
                            ClothList_1.get(i).setClothFile(SaveImage.saveImages(urlTem, ClothList_1.get(i).getClothFile(), "clothList1_"+i+".jpg"));
                        }break;
                    case 2:
                        for (int i = 0; i < jsonList_cloth.length(); i++) {
                            JSONObject object = (JSONObject) jsonList_cloth.get(i);
                            urlTem = object.getString("ClothUrl");
                            ClothList_2.add(new Cloth("",null));
                            ClothList_2.get(i).setClothNum(object.getString("ClothNum"));
                            ClothList_2.get(i).setClothFile(SaveImage.saveImages(urlTem, ClothList_2.get(i).getClothFile(), "clothList2_"+i+".jpg"));
                        }
                        break;
                    case 3:
                        for (int i = 0; i < jsonList_cloth.length(); i++) {
                            JSONObject object = (JSONObject) jsonList_cloth.get(i);
                            urlTem = object.getString("ClothUrl");
                            ClothList_3.add(new Cloth("",null));
                            ClothList_3.get(i).setClothNum(object.getString("ClothNum"));
                            ClothList_3.get(i).setClothFile(SaveImage.saveImages(urlTem, ClothList_3.get(i).getClothFile(), "clothList3_"+i+".jpg"));
                        }
                        break;
                    case 4:
                        for (int i = 0; i < jsonList_cloth.length(); i++) {
                            JSONObject object = (JSONObject) jsonList_cloth.get(i);
                            urlTem = object.getString("ClothUrl");
                            ClothList_4.add(new Cloth("",null));
                            ClothList_4.get(i).setClothNum(object.getString("ClothNum"));
                            ClothList_4.get(i).setClothFile(SaveImage.saveImages(urlTem, ClothList_4.get(i).getClothFile(), "clothList4_"+i+".jpg"));
                        }
                        break;
                    case 5:
                        for (int i = 0; i < jsonList_cloth.length(); i++) {
                            JSONObject object = (JSONObject) jsonList_cloth.get(i);
                            urlTem = object.getString("ClothUrl");
                            ClothList_5.add(new Cloth("",null));
                            ClothList_5.get(i).setClothNum(object.getString("ClothNum"));
                            ClothList_5.get(i).setClothFile(SaveImage.saveImages(urlTem, ClothList_5.get(i).getClothFile(), "clothList5_"+i+".jpg"));
                        }
                        break;
                }
            }

            return code;
    }
}

