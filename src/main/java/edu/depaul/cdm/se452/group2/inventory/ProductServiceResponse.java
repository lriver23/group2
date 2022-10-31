package edu.depaul.cdm.se452.group2.inventory;

import net.minidev.json.JSONObject;

public class ProductServiceResponse {
    
    public static String constructDefaultSuccess() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        return jsonObject.toJSONString();
    }

    public static String constructSuccessWithProduct(Product product) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        jsonObject.appendField("product", product);
        return jsonObject.toJSONString();
    }

    public static String constructErrorNoProductExists(long id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "error");
        jsonObject.appendField("productId", id);
        jsonObject.appendField("message", "no product with id found");
        return jsonObject.toJSONString();
    }

}
