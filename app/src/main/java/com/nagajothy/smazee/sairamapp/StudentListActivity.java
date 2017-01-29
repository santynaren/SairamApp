package com.nagajothy.smazee.sairamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentListActivity extends AppCompatActivity {
    ListView listview;
    Toolbar toolbar;
    ArrayList<HashMap<String, String>> studentList;
    String teacher_id , indi_name;
    String studentname, student_id , class_id;
    String url = "http://www.ownapedia.com/mentor.php" ;
    private String TAG = Homepage.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        listview = (ListView) findViewById(R.id.list_view);
        studentList = new ArrayList<>();
        Intent list = getIntent();
        teacher_id = list.getStringExtra("teacher");
        toolbar = (Toolbar)findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List of your Students");
        Toast.makeText(StudentListActivity.this, teacher_id, Toast.LENGTH_LONG);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray name = jsonObject.getJSONArray("Name");
                    for (int i = 0; i < name.length(); i++) {
                        JSONObject listname = name.getJSONObject(i);
                        studentname = listname.getString("stud_name");
                        student_id = listname.getString("stud_clg_id");
                        class_id = listname.getString("stud_class_id");
                       // studentname = listname.getString("stud_name");
                        HashMap<String, String> vendors = new HashMap<>();

                        // adding each child node to HashMap key => value
                        vendors.put("name", studentname);
                        vendors.put("id", student_id);

                        //adding contact to contact list
                        studentList.add(vendors);


                    }
                    ListAdapter adapter = new SimpleAdapter(
                            StudentListActivity.this, studentList,
                            R.layout.list_item, new String[]{"name"}, new int[]{R.id.name_list});

                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                             indi_name = ((TextView) view.findViewById(R.id.name_list)).getText().toString();
                              //  Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(position)+indi_name,Toast.LENGTH_LONG).show();

                            Intent indi = new Intent(StudentListActivity.this,AbsentActivity.class);
                            indi.putExtra("name",indi_name);
                            indi.putExtra("id",student_id);
                            indi.putExtra("mentor_id",teacher_id);
                            indi.putExtra("class_id",class_id);
                            startActivity(indi);

                        }


                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "" + error.getMessage() + "," + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("teacher_id", teacher_id);
                // params.put("ambition_value",ambition);

                return params;
            }
        };
        MySingleton.getInstance(StudentListActivity.this).addToRequestque(stringRequest);



    }

}
