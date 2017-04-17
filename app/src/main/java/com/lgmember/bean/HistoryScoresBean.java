package com.lgmember.bean;

import com.lgmember.api.HttpHandler;
import com.lgmember.model.HistoryScores;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanan_Wu on 2017/3/9.
 */

public class HistoryScoresBean extends HttpResultBean {
    private int total;
    private List<HistoryScores> historyScoresList;

    public HistoryScoresBean(){
        super();
    }

    public HistoryScoresBean(int total, List<HistoryScores> historyScoresList) {
        this.total = total;
        this.historyScoresList = historyScoresList;
    }

    public HistoryScoresBean(int code, int total, List<HistoryScores> historyScoresList) {
        super(code);
        this.total = total;
        this.historyScoresList = historyScoresList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HistoryScores> getHistoryScoresList() {
        return historyScoresList;
    }

    public void setHistoryScoresList(List<HistoryScores> historyScoresList) {
        this.historyScoresList = historyScoresList;
    }
}
