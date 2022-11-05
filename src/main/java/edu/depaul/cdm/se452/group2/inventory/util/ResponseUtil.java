package edu.depaul.cdm.se452.group2.inventory.util;

import net.minidev.json.JSONObject;

public class ResponseUtil {
    public static String constructDefaultSuccess() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        return jsonObject.toJSONString();
    }

    public static String constructSuccessWithObject(Object objectToBePrinted, String objectName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "success");
        jsonObject.appendField(objectName, objectToBePrinted);
        return jsonObject.toJSONString();
    }

    public static String constructErrorNoIdExists(String id, String objectName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("status", "error");
        jsonObject.appendField(String.format("%sId", objectName), id);
        jsonObject.appendField("message", String.format("no %s with id found", objectName));
        return jsonObject.toJSONString();
    }
}
