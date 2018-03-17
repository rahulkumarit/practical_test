package com.rxandroid.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rxandroid.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Devrepublic-14 on 3/16/2018.
 */
public class AndroidXMLParsingActivity extends BaseActivity {

    static final String URL = "https://api.androidhive.info/pizza/?format=xml";
    //https://api.androidhive.info/volley/person_object.json
    static final String NEWURL = "https://api.androidhive.info/volley/person_object.json";

    // XML node keys
    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_COST = "cost";
    static final String KEY_DESC = "description";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_xml);
        try {
            initial();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initial() {
        Button btnParase = findViewById(R.id.btnParase);
        btnParase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paraseXmlFromUrl();
            }
        });
    }

    //PRASING XML FROM URL:
    private void paraseXmlFromUrl() {
        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
        new UrlToXmlClass().execute(URL);
    }

    public class UrlToXmlClass extends AsyncTask<String, String, String> {

        public String xmlvalue = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String xml = null;
            try {
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(strings[0]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                xml = EntityUtils.toString(httpEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return XML
            return xml;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
            Log.e("Response of url:", "" + s);
            Document doc = getDomElement(s); // getting DOM element
            NodeList nl = doc.getElementsByTagName(KEY_ITEM);
            for (int i = 0; i < nl.getLength(); i++) {
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                Element e = (Element) nl.item(i);
                // adding each child node to HashMap key => value
                map.put(KEY_ID, getValue(e, KEY_ID));
                map.put(KEY_NAME, getValue(e, KEY_NAME));
                map.put(KEY_COST, "Rs." + getValue(e, KEY_COST));
                map.put(KEY_DESC, getValue(e, KEY_DESC));
                // adding HashList to ArrayList
                menuItems.add(map);
            }
            Log.e("menuItems size:", "menuItems" + menuItems.size());
        }


        public Document getDomElement(String xml) {
            Document doc = null;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is);

            } catch (ParserConfigurationException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage());
                return null;
            }
            // return DOM
            return doc;
        }


        public String getValue(Element item, String str) {
            NodeList n = item.getElementsByTagName(str);
            return this.getElementValue(n.item(0));
        }

        public final String getElementValue(Node elem) {
            Node child;
            if (elem != null) {
                if (elem.hasChildNodes()) {
                    for (child = elem.getFirstChild(); child != null; child = child.getNextSibling()) {
                        if (child.getNodeType() == Node.TEXT_NODE) {
                            return child.getNodeValue();
                        }
                    }
                }
            }
            return "";
        }
    }


}
