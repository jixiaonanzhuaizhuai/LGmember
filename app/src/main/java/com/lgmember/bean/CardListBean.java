package com.lgmember.bean;

import com.lgmember.model.Card;

import java.util.List;

/**
 * Created by Yanan_Wu on 2017/3/9.
 */

public class CardListBean extends HttpResultBean {
    private int total;
    private List<Card> cardList;

    public CardListBean(){
        super();
    }

    public CardListBean(int total, List<Card> cardList) {
        this.total = total;
        this.cardList = cardList;
    }

    public CardListBean(int code, int total, List<Card> cardList) {
        super(code);
        this.total = total;
        this.cardList = cardList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
