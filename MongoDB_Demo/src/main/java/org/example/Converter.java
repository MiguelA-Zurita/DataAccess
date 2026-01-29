package org.example;

import org.json.*;
import org.bson.*;
import java.io.*;

public class Converter { //Converter Class for utilities
    public static JSONObject convertXmlToJson(String xml) throws JSONException{ //Convert XML string into a JSON
        return XML.toJSONObject(xml);
    }

    public static String convertJsonToXml(JSONObject json) { //Convert JSON string into XML
        return XML.toString(json);
    }

    public static JSONObject convertBsonToJson(Document bson) { //Convert BSON document into JSON
        return new JSONObject(bson.toJson());
    }

    public static Document convertJsonToBson(JSONObject json) { //Convert JSON document into BSON
        return Document.parse(json.toString());
    }
}
