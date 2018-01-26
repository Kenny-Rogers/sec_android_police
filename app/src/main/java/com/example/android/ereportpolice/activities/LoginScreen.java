package com.example.android.ereportpolice.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.android.ereportpolice.R;
import com.example.android.ereportpolice.utils.Constants;
import com.example.android.ereportpolice.utils.NetworkUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    private Button btn_sign_in;
    private EditText et_team_name, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init(); //initializing the variables
    }

    private void init() {
        btn_sign_in = findViewById(R.id.btn_sign_in);
        et_team_name = findViewById(R.id.et_team_name);
        et_password = findViewById(R.id.et_password);
    }

    public void sign_in(View view) {
        String sign_in_url = Constants.SERVER_URL + "final_proj_api/public/log_user_in.php";
        final String team_name = et_team_name.getText().toString();
        final String password = et_password.getText().toString();
        final String user_type = "patrol_team";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, sign_in_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_type", user_type);
                params.put("team_name", team_name);
                params.put("password", password);
                return params;
            }
        };
        NetworkUtil.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
