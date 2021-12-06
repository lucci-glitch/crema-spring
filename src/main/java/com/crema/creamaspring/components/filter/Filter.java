package com.crema.creamaspring.components.filter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Filter {
    private final String JSON_TAGGER_URL = "https://json-tagger.com/tag";

    public String filterSentence(String sentence) throws JSONException {
        //TODO: dela upp
        try {
            URL url = new URL(JSON_TAGGER_URL);
            List<String> nouns = new ArrayList<>();
            String output;


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(sentence.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));


            while ((output = reader.readLine()) != null) {
                JSONObject obj = new JSONObject(output);
                JSONArray outerArray = obj.getJSONArray("sentences");

                for (int i = 0; i < outerArray.length(); ++i) {
                    JSONArray innerArray = (JSONArray) outerArray.get(i);

                    for (int j = 0; j < innerArray.length(); ++j) {
                        JSONObject jsonobject = innerArray.getJSONObject(j);
                        JSONObject udTags = (JSONObject) jsonobject.get("ud_tags");

                        if (udTags.get("pos_tag").toString().equals("NOUN")) {
                            nouns.add(jsonobject.getString("word_form"));
                        }
                    }
                }
            }
            if (nouns.size() > 0) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, nouns.size());
                return nouns.get(randomNum);
            } else {
                return "";
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
