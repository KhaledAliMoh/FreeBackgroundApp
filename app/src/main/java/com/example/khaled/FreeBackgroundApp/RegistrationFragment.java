package com.example.khaled.FreeBackgroundApp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Khaled on 11/22/2016.
 */
public class RegistrationFragment extends Fragment {
    String TAG = "Reg fragment";

    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextRePassword;
    Button buttonCreateAccount;

    TextView textViewUserNameError;
    TextView textViewEmailError;
    TextView textViewPasswordError;
    TextView textViewRePasswordError;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);

        editTextUserName = (EditText) rootView.findViewById(R.id.registration_user_name);
        editTextEmail = (EditText) rootView.findViewById(R.id.registration_email);
        editTextPassword = (EditText) rootView.findViewById(R.id.registration_password);
        editTextRePassword = (EditText) rootView.findViewById(R.id.registration_re_password);

        textViewUserNameError = (TextView) rootView.findViewById(R.id.message_error_user_name);
        textViewEmailError = (TextView) rootView.findViewById(R.id.message_error_email);
        textViewPasswordError = (TextView) rootView.findViewById(R.id.message_error_password);
        textViewRePasswordError = (TextView) rootView.findViewById(R.id.message_error_re_password);

        buttonCreateAccount = (Button) rootView.findViewById(R.id.registration_submit_button);
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistration();
            }
        });
        return rootView;
    }
    void startRegistration(){
        boolean ok = true;
        String stringUserName = editTextUserName.getText().toString();
        String stringEmail = editTextEmail.getText().toString();
        String stringPassword = editTextPassword.getText().toString();
        String stringRePassword = editTextRePassword.getText().toString();

        if(stringUserName == null){
            ok = false;
            textViewUserNameError.setVisibility(View.VISIBLE);
        }

        if(!isValidEmail(stringEmail) || stringEmail == null){
            ok = false;
            textViewEmailError.setVisibility(View.VISIBLE);
        }

        if(stringPassword.length() < 6 || stringPassword == null){
            ok = false;
            textViewPasswordError.setVisibility(View.VISIBLE);
        }

        if(!stringPassword.equals(stringRePassword) || stringRePassword == null){
            ok = false;
            textViewRePasswordError.setVisibility(View.VISIBLE);
        }

        if(ok) {
            //clearData();
            makeRequest();
        }
    }

    void makeRequest(){
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Registration...", "Please wait...", false, false);

        String stringUserName = editTextUserName.getText().toString();
        String stringEmail = editTextEmail.getText().toString();
        String stringPassword = editTextPassword.getText().toString();
        final HashMap<String, String> hashMapUser = new HashMap<String, String >();
        hashMapUser.put("username", stringUserName);
        hashMapUser.put("email", stringEmail);
        hashMapUser.put("password", stringPassword);

        RequestQueue queue = MySingletonForRequestQueue.getInstance(getActivity()).getRequestQueue();

        String url ="http://backgroundfree.pe.hu/api/storeuser";
        //Toast.makeText(getActivity(), new JSONObject(hashMapUser).toString(), Toast.LENGTH_LONG).show();
        //Log.d(TAG, new JSONObject(hashMapUser).toString());

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(hashMapUser), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loading.dismiss();
                Log.d(TAG, "On Res" + response.toString());
                Toast.makeText(getActivity(), "On Res" + response.toString(), Toast.LENGTH_LONG).show();
                try {
                    if(response.getString("result").equals("Success")) {
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            /*@Override // if you use String Request you must override getParams to send data
            protected Map<String, String> getParams() throws AuthFailureError {
                String stringUserName = editTextUserName.getText().toString();
                String stringEmail = editTextEmail.getText().toString();
                String stringPassword = editTextPassword.getText().toString();
                final HashMap<String, String> hashMapUser = new HashMap<String, String >();
                hashMapUser.put("username", stringUserName);
                hashMapUser.put("email", stringEmail);
                hashMapUser.put("password", stringPassword);
                Log.d(TAG, "In get params" + new JSONObject(hashMapUser).toString());
                return hashMapUser;
            }*/
            /*@Override
            public byte[] getBody() {
                return super.getBody();
            }*/
            /*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }*/
        };

        queue.add(request);
    }

    void clearData(){
                // clear all data and make messages error invisible
        editTextUserName.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        editTextRePassword.setText("");

        textViewUserNameError.setVisibility(View.INVISIBLE);
        textViewEmailError.setVisibility(View.INVISIBLE);
        textViewPasswordError.setVisibility(View.INVISIBLE);
        textViewRePasswordError.setVisibility(View.INVISIBLE);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
