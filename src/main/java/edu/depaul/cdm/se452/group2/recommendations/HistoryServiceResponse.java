package edu.depaul.cdm.se452.group2.recommendations;

import net.minidev.json.JSONObject;

public class HistoryServiceResponse {
    
    public static String constructDefaultSuccess() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        return jsonObject.toJSONString();
    }

    public static String constructSuccessWithHistory(History history) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        jsonObject.appendField("history", history);
        return jsonObject.toJSONString();
    }

    public static String constructErrorNoHistoryExists(long id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "error");
        jsonObject.appendField("historyId", id);
        jsonObject.appendField("message", "no history with id found");
        return jsonObject.toJSONString();
    }

}
