package edu.depaul.cdm.se452.group2.inventory;

import net.minidev.json.JSONObject;

public class SellerServiceResponse {
    public static String constructDefaultSuccess() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        return jsonObject.toJSONString();
    }

    public static String constructSuccessWithSeller(Seller seller) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        jsonObject.appendField("seller", seller);
        return jsonObject.toJSONString();
    }

    public static String constructErrorNoSellerExists(long id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "error");
        jsonObject.appendField("sellerId", id);
        jsonObject.appendField("message", "no seller with id found");
        return jsonObject.toJSONString();
    }
}
 