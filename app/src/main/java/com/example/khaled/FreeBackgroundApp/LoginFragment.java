package com.example.khaled.FreeBackgroundApp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Khaled on 11/22/2016.
 */
public class LoginFragment extends Fragment {

    final String Tag = "Login Fragment ";
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    TextView textViewCreateAccount;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = (EditText) rootView.findViewById(R.id.editText_email);
        editTextPassword = (EditText) rootView.findViewById(R.id.editText_password);

        buttonLogin = (Button) rootView.findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Login", Toast.LENGTH_LONG).show();
                //startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
                startLogin();
            }
        });

        textViewCreateAccount = (TextView) rootView.findViewById(R.id.textView_create);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Creation", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity().getApplicationContext(), RegistrationActivity.class));
            }
        });

        return rootView;
    }

    void startLogin(){

        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Login...","Please wait...",false,false);

        /*String stringEmail = editTextEmail.getText().toString();
        String stringPassword = editTextPassword.getText().toString();
        if(!isValidEmail(stringEmail)){
            loading.dismiss();
            Toast.makeText(getActivity(),"Invalid Email ", Toast.LENGTH_LONG).show();
            return;
        }
        HashMap<String, String> hashMapUser = new HashMap<String, String >();
        hashMapUser.put("e_mail", stringEmail);
        hashMapUser.put("password", stringPassword);
        */

        final HashMap<String, String> input = new HashMap<String, String>();
        input.put("input", "3");
        RequestQueue queue = MySingletonForRequestQueue.getInstance(getActivity()).getRequestQueue();

        String url ="http://backgroundfree.pe.hu/api/test";
        //Toast.makeText(getActivity(), new JSONObject(input).toString(), Toast.LENGTH_LONG).show();

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(getActivity(), "On Res " + response.toString(), Toast.LENGTH_LONG).show();
                Log.d(Tag, response.toString());
                //startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Log.d(Tag, error.toString());
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Toast.makeText(getActivity(), "in getParams", Toast.LENGTH_LONG).show();
                return input;
            }
        };

        queue.add(jsonObjectRequest);


    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
