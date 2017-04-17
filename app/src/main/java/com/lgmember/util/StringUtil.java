package com.lgmember.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.lgmember.activity.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yanan_Wu on 2017/1/6.
 */

public class StringUtil {

    public static String[] NATIONS = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族",
            "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族", "哈尼族",
            "哈萨克族", "傣族", "黎族", "傈傈族", "佤族", "畲族", "高山族", "拉祜族", "水族",
            "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达翰尔族", "仫佬族", "羌族",
            "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族",
            "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族",
            "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族", "外籍人士"};
    public static String[] EDUCATIONS = {"初中","高中","专科","本科","硕士","博士"};
    public static String[] GENDER = {"男","女"};

    //身份证号验证
    public static boolean userCardCheck(String userCard) {
        if (userCard.matches("^(\\d{14}|\\d{17})(\\d|[xX])$")) {
            return true;
        }
        return false;
    }

    //手机号验证
    public static boolean isPhone(String phone) {
        if (phone.matches("^1[34578]+\\d{9}$")) {
            return true;
        }
        return false;
    }

    //判断两次密码是否一致
    public static boolean isNewPwdEquallyConfirmPwd(String newPwd,String confirmPwd) {
        if (newPwd.equals(confirmPwd)) {
            return true;
        }
        return false;
    }

    //密码验证,为8-20位
    public static boolean isPassword(String password) {
        if (password.matches("^[0-9A-Za-z]{8,20}$")) {
            return true;
        }
        return false;
    }

    //判断参数是否全是数字
    public static boolean isAllNumber(String s){
        for(int i = s.length();--i >= 0;){
            if (!Character.isDigit(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
    //bitmap 转 base64
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        final String result = base64Data.substring(base64Data.lastIndexOf(","),base64Data.length());
        byte[] bytes = Base64.decode(result, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
    public static int codeTomsg(int code){
        int codeArray[] = {R.string.code_1,R.string.code_2,R.string.code_3,R.string.code_4,R.string.code_5,R.string.code_6,
                R.string.code_7,R.string.code_8,R.string.code_9,R.string.code_10,R.string.code_11,};
        for (int i=1;i<=codeArray.length;i++){
            if (i == code){
                return codeArray[i-1];
            }
        }
        return 0;
    }

    //根据身份证号，获取年龄

    public static int IDcard2Age(String idno){
        int leh = idno.length();
        String dates = "";
        if (leh == 18){
            dates = idno.substring(6,10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year = df.format(new Date());
            int u = Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else {
            dates = idno.substring(6,8);
            return Integer.parseInt(dates);
        }
    }

    //boolean 转换为是与否
    public static String boolean2String(Boolean b){

        if (b == true){
            return "是";
        }else {
            return "否";
        }
    }

    //报名状态
    public static String numToJoinState(int num){
        if (num == -1){
            return "报名参与";
        }else if (num == 0){
            return "报名未签到";
        }else if(num == 1){
            return "报名且签到";
        }else if(num == 2){
            return "未报名但签到";
        }else if (num == 3){
            return "后补报名";
        }
        return null;
    }

    //会员状态

    public static String numToState(int num){

        String stateArray[] = {"正常","锁定","删除"};
        for (int i =1; i<=stateArray.length;i++){
            if (i == num){
                return stateArray[i-1];
            }
        }
        return null;
    }

    public static String numToNation(int num){
        String nationArray[] = {"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族"
                ,"满族","侗族","瑶族","白族","土家族","哈尼族","哈萨克族","傣族","黎族","傈傈族"
                ,"佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族","土族"
                ,"达翰尔族","仫佬族","羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族"
                ,"塔吉克族","怒族","乌孜别克族","俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族"
                ,"独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"};
        for (int i = 1;i<=nationArray.length;i++){
            if (i == num){
                return nationArray[i-1];
            }else if (num == 90){
                return "外籍人士";
            }
        }
        return null;
    }

    public static String numToEducation(int num){

        String eduArray[] = {"初中","高中","专科","本科","硕士","博士"};
        for (int i =1; i<=eduArray.length;i++){
            if (i == num){
                return eduArray[i-1];
            }
        }
        return null;
    }
    public static String numToSoure(int num){

        String sourceArray[] = {"APP会员中心","微信会员中心","网页会员中心","线下会员中心","其它"};
        for (int i =1; i<=sourceArray.length;i++){
            if (i == num){
                return sourceArray[i-1];
            }
        }
        return null;
    }
    public static String numToLevels(int num){

        String levelsArray[] = {"红卡","银卡","金卡","钻石卡"};
        for (int i =1; i<=levelsArray.length;i++){
            if (i == num){
                return levelsArray[i-1];
            }
        }
        return null;
    }
    public static String numToGender(boolean b){

        if (b){
            return "男";
        }else {
            return "女";
        }
    }



}

