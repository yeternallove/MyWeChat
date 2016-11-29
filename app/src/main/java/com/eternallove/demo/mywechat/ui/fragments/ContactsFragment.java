package com.eternallove.demo.mywechat.ui.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.db.MyWeChatDB;
import com.eternallove.demo.mywechat.modle.ContactsBean;
import com.eternallove.demo.mywechat.util.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/9
 */
public class ContactsFragment extends Fragment {
    private String TAG = ContactsFragment.class.getSimpleName();

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String url = "http://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> contactList;
    private MyWeChatDB myWeChatDB;
    @BindView(R.id.list_view)
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_contacts,container,false);
        ButterKnife.bind(this,view);
        contactList = new ArrayList<>();
        new GetContacts().execute();
        return view;
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            myWeChatDB = MyWeChatDB.getInstance(getActivity());
            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url);

//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj.getJSONArray("contacts");
//
//                    // looping through All Contacts
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//
//                        String id = c.getString("id");
//                        String name = c.getString("name");
//                        String email = c.getString("email");
//                        String address = c.getString("address");
//                        String gender = c.getString("gender");
//
//                        // Phone node is JSON Object
//                        JSONObject phone = c.getJSONObject("phone");
//                        String mobile = phone.getString("mobile");
//                        String home = phone.getString("home");
//                        String office = phone.getString("office");
//                        //adding each child node to SQLite
//                        myWeChatDB.saveContent(id,0,name,email,address,gender,mobile,home,office);
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                }
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//            }
//            // tmp hash map for single contact
//            HashMap<String, String> contact = new HashMap<>();
//            ArrayList<ContactsBean> contactlist;
//            contactlist = myWeChatDB.selectContacts();
//            // adding each child node to HashMap key => value
//            for( int i = 0; i < contactlist.size() ; i++ ) {
//                contact.put("id", contactlist.get(i).getId());
//                contact.put("name", contactlist.get(i).getName());
//                contact.put("email", contactlist.get(i).getEmail());
//                contact.put("mobile", contactlist.get(i).getMobile());
//                // adding contact to contact list
//                contactList.add(contact);
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), contactList,
                    R.layout.item_contact, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);
        }

    }
}
/**
 * example json
 {
 "contacts": [
 {
 "id": "c200",
 "name": "Ravi Tamada",
 "email": "ravi@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c201",
 "name": "Johnny Depp",
 "email": "johnny_depp@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c202",
 "name": "Leonardo Dicaprio",
 "email": "leonardo_dicaprio@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c203",
 "name": "John Wayne",
 "email": "john_wayne@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c204",
 "name": "Angelina Jolie",
 "email": "angelina_jolie@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "female",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c205",
 "name": "Dido",
 "email": "dido@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "female",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c206",
 "name": "Adele",
 "email": "adele@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "female",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c207",
 "name": "Hugh Jackman",
 "email": "hugh_jackman@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c208",
 "name": "Will Smith",
 "email": "will_smith@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c209",
 "name": "Clint Eastwood",
 "email": "clint_eastwood@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c2010",
 "name": "Barack Obama",
 "email": "barack_obama@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c2011",
 "name": "Kate Winslet",
 "email": "kate_winslet@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "female",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 },
 {
 "id": "c2012",
 "name": "Eminem",
 "email": "eminem@gmail.com",
 "address": "xx-xx-xxxx,x - street, x - country",
 "gender" : "male",
 "phone": {
 "mobile": "+91 0000000000",
 "home": "00 000000",
 "office": "00 000000"
 }
 }
 ]
 }

 */
