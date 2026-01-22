package org.example;

import org.json.*;
import org.bson.*;
import org.springframework.data.mongodb.util.BsonUtils;

public class Converter {
    public static JSONObject convertXmlToJson(String xml){
        JSONObject jsonObj = XML.toJSONObject(xml);
        if (jsonObj == null) throw new NullPointerException();
        return jsonObj;
    }
    public static XML convertJsonToXml(JSONObject json){
        return JSONObject.fromJson(json.toString(), XML.class);
    }
    public static JSONObject convertBsonToJson(Document bson){
        String jsonString = BsonUtils.toJson(bson);
        if (jsonString == null) throw new NullPointerException();
        return new JSONObject(jsonString);
    }
    public static Document convertJsonToBson(JSONObject json){
        return Document.parse(json.toString());
    }
}
