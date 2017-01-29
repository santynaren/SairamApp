package com.nagajothy.smazee.sairamapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AbsentActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> studentList;
    String url = "http://www.ownapedia.com/absentinsert.php" ;
    private String TAG = StudentListActivity.class.getSimpleName();
    TextView name , id ,date;
    Toolbar toolbar;
    EditText reason;
    Button report;

    String absentname , absentid , absentdate ,absentreason , mentorid , class_id , status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent);
        name = (TextView)findViewById(R.id.text_name);
        id = (TextView)findViewById(R.id.text_id);
        date = (TextView)findViewById(R.id.text_date);
        report =(Button)findViewById(R.id.report_button);
        reason = (EditText)findViewById(R.id.text_reason);
        toolbar = (Toolbar)findViewById(R.id.toolbarabsent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Report");
        Intent absent = getIntent();
        absentname = absent.getStringExtra("name");
        absentid = absent.getStringExtra("id");
        mentorid = absent.getStringExtra("mentor_id");
        class_id = absent.getStringExtra("class_id");
        name.setText(absentname);
        id.setText(absentid);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        absentdate = dateFormat.format(calendar.getTime());
        date.setText(absentdate);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absentreason = reason.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject name = jsonObject.getJSONObject("Name");

                               // JSONObject listname = name.getJSONObject("status");
                            status = name.getString("status");
                             //   studentname = listname.getString("stud_name");
                             //   student_id = listname.getString("stud_clg_id");
                                // studentname = listname.getString("stud_name");
                             //   HashMap<String, String> vendors = new HashMap<>();

                                // adding each child node to HashMap key => value
                          //      vendors.put("name", studentname);
                            //    vendors.put("id", student_id);
                                //adding contact to contact list
                              //  studentList.add(vendors);


                          Toast.makeText(AbsentActivity.this,status,Toast.LENGTH_LONG).show();

                                  //  Intent indi = new Intent(AbsentActivity.this,AbsentActivity.class);
                                   // indi.putExtra("name",indi_name);
                                   // indi.putExtra("id",student_id);
                                  //  startActivity(indi);






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
                        params.put("student_id", absentid);
                        params.put("student_name", absentname);
                        params.put("absent_date", absentdate);
                        params.put("absent_reason", absentreason);
                        params.put("mentor_id", mentorid);
                        params.put("class_id", class_id);
                        // params.put("ambition_value",ambition);

                        return params;
                    }
                };
                MySingleton.getInstance(AbsentActivity.this).addToRequestque(stringRequest);
            }
        });
    }
}
