package com.lgmember.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lgmember.bean.ActivityBean;
import com.lgmember.bean.CardListBean;
import com.lgmember.bean.HistoryScoresBean;
import com.lgmember.bean.MessageBean;
import com.lgmember.bean.ProjectMessageBean;
import com.lgmember.model.Card;
import com.lgmember.model.HistoryScores;
import com.lgmember.model.Message;
import com.lgmember.model.Project;
import com.lgmember.model.ProjectMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2017/3/9.
 */

public class JsonUtil {

    public static HistoryScoresBean parseHistoryScoresResult(JSONObject jsonObject) {

        List<HistoryScores> historyScoresList = new ArrayList<HistoryScores>();
        HistoryScoresBean resultBean = null;
        try {
            int code = jsonObject.getInt("code");
            int total = jsonObject.getInt("total");
            JSONArray jarr = jsonObject.getJSONArray("data");
            historyScoresList = parseJsonArrayWithGson(jarr.toString(),HistoryScores.class);
            resultBean = new HistoryScoresBean();
            resultBean.setCode(code);
            resultBean.setTotal(total);
            resultBean.setHistoryScoresList(historyScoresList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultBean;

    }

    public static CardListBean parseCardListResult(JSONObject jsonObject) {

        List<Card> cardList = new ArrayList<Card>();
        CardListBean resultBean = null;
        try {
            int code = jsonObject.getInt("code");
            int total = jsonObject.getInt("total");
            JSONArray jarr = jsonObject.getJSONArray("data");
            cardList = parseJsonArrayWithGson(jarr.toString(),Card.class);
            resultBean = new CardListBean();
            resultBean.setCode(code);
            resultBean.setTotal(total);
            resultBean.setCardList(cardList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultBean;

    }
    public static MessageBean parseMessageResult(JSONObject jsonObject) {

        List<Message> messageList = new ArrayList<Message>();
        MessageBean resultBean = null;
        try {
            int code = jsonObject.getInt("code");
            int total = jsonObject.getInt("total");
            JSONArray jarr = jsonObject.getJSONArray("data");
            for (int i = 0; i < jarr.length(); i++) {
                JSONObject jObject = jarr.getJSONObject(i);
                messageList.add(new Message(jObject.getInt("message_id"), jObject.getString("title")
                        , jObject.getString("content"), jObject.getString("hyperlink"),
                        jObject.getString("create_time"),jObject.getString("author"),jObject.getInt("state")));
            }
            resultBean = new MessageBean();
            resultBean.setCode(code);
            resultBean.setTotal(total);
            resultBean.setMessageList(messageList);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultBean;

    }




    //将Json数据解析成相应的映射对象

    public static <T> T parseJsonWithGson(String jsonData,Class<T> type){
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData,type);
        return result;

    }

    //将Json数据解析成相应的映射对象列表
    public static <T> List<T> parseJsonArrayWithGson(String jsonData,Class<T> type){

            List<T> list = new ArrayList<T>();
            try {
                Gson gson = new Gson();
                JsonArray arry = new JsonParser().parse(jsonData).getAsJsonArray();
                for (JsonElement jsonElement : arry) {
                    list.add(gson.fromJson(jsonElement, type));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
}
