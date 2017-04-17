package com.lgmember.util;

/**
 * Created by Yanan_Wu on 2017/1/5.
 */

public class Common {


    //基地址
    public static  String URL_BASE = "http://221.212.177.245/";
    //登录网址
    public static String URL_LOGIN = URL_BASE+"login";
    //注册网址
    public static String URL_REGISTER = URL_BASE+"mobile-reg";
    //请求手机验证码
    public static String URL_REQUEST_CODE = URL_BASE+"sms-capt";
    //修改密码
    public static String URL_MODIFY_PWD = URL_BASE+"pwd";
    //获取个人资料
    public static String URL_MEMBER_MESSAGE = URL_BASE+"profile";
    //更新个人资料
    public static String URL_EDIT_MEMBER_MESSAGE = URL_BASE+"profile-update";
    //积分规则
    public static String URL_SCORES_RULE = URL_BASE+"point/rule";
    //历史积分
    public static String URL_HISTORY_SCORES = URL_BASE+"point/history";
    //积分消息
    public static String URL_SCORES_INFORMATION = URL_BASE+"point/information";
    //返回会员姓名
    public static String URL_GET_MEMBER_NAME = URL_BASE+"get-member-name";
    //活动码签到
    public static String URL_ACTIVITY_CODE = URL_BASE+"project/sign-in";
    //会员活动报名
    public static String URL_ACTIVITY_JOIN = URL_BASE+"project/check-in";
    //会员活动列表
    public static String URL_PROJECT = URL_BASE+"project";
    //以往报名
    public static String URL_ALREAD_JOIN = URL_BASE+"project2/history";
    //即将参与
    public static String URL_SOON_JOIN = URL_BASE+"project2/future";
    //热门活动
    public static String URL_HOT = URL_BASE+"project2/popular";
    //新的活动列表
    public static String URL_PROJECT_MESSAGE_ALLLIST = URL_BASE+"project2/all";
    //会员招募信息列表
    public static String URL_PROJECT_MESSAGE = URL_BASE+"project-message";
    //会员招募信息详情
    public static String URL_PROJECT_MESSAGE_DETAIL = URL_BASE+"app-project-message";
    //全部礼物列表
    public static String URL_EXCHANGE_ALL_GIGT = URL_BASE+"point/giftlist";
    //筛选后的礼物列表
    public static String URL_EXCHANGE_SELECT_GIGT = URL_BASE+"point/giftexchangelist";
    //点击某行礼物显示该礼物的详细信息
    public static String URL_EXCHANGE_GIFT_INFO = URL_BASE+"gift/information";
    //积分兑换礼品
    public static String URL_EXCHANGE_GIFT = URL_BASE+"point/exchange";
    //消息列表
    public static String URL_MESSAGE_LIST = URL_BASE+"message/list";
    //删除消息
    public static String URL_DELETE_MESSAGE = URL_BASE+"message/delete";
    //消息详细信息
    public static String URL_MESSAGE_DETAIL = URL_BASE+"message/detail";
    //各个状态的卡券列表
    public static String URL_CARD_LIST = URL_BASE+"coupon";
    //领取卡券
    public static String URL_GET_CARD = URL_BASE+"coupon/get";
    //领取卡券兑换码
    public static String URL_CARD_CODE = URL_BASE+"coupon/code";
    //创建问题反馈
    public static String URL_CREATE_FEEDBACK = URL_BASE+"create-feedback";
    //问题反馈列表
    public static String URL_FEEDBACK_LIST = URL_BASE+"feedback";
    //删除问题反馈
    public static String URL_DELETE_FEEDBACK = URL_BASE+"delete-feedback";
    //申请实名认证
    public static String URL_CERTIFICATION = URL_BASE+"auth";
    //照片上传
    public static String URL_UPLOAD_IMG = URL_BASE+"upload";
    //收藏列表
    public static String URL_COLLECTION_LIST = URL_BASE+"favorite";
    //添加收藏
    public static String URL_ADD_COLLECTION = URL_BASE+"favorite/add";
    //删除收藏
    public static String URL_DELETE_COLLECTION = URL_BASE+"favorite/delete";
    //忘记密码
    public static String URL_FORGET_PASSWORD = URL_BASE+"forgotpwd";
    //更新头像
    public static String URL_UPDATE_PHOTO = URL_BASE+"avatar/update";
    //上传录音
    public static String URL_UPLOAD_RECORD = URL_BASE+"radio-record/upload";
    //录音节目识别结果查询
    public static String URL_RECORD_RESULT = URL_BASE+"radio-record/query";
    //录音识别列表
    public static String URL_RECORD_LIST = URL_BASE+"radio-record";

}
