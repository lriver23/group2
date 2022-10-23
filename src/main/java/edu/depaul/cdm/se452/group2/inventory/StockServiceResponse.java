package edu.depaul.cdm.se452.group2.inventory;

import net.minidev.json.JSONObject;

public class StockServiceResponse {

    public static String constructDefaultSuccess() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        return jsonObject.toJSONString();
    }

    public static String constructSuccessWithStock(Stock stock) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        jsonObject.appendField("stock", stock);
        return jsonObject.toJSONString();
    }

    public static String constructErrorNoStockExists(long id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "error");
        jsonObject.appendField("stockId", id);
        jsonObject.appendField("message", "no stock with id found");
        return jsonObject.toJSONString();
    }
}
