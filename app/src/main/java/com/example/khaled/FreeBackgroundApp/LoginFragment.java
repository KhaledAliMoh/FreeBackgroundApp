package com.example.khaled.FreeBackgroundApp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Khaled on 11/22/2016.
 */
public class LoginFragment extends Fragment {

    final String Tag = "Login Fragment ";
    EditText editTextEmail;
    EditText editTextPassword;
    Button loginButton;
    TextView createTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = (EditText) rootView.findViewById(R.id.editText_email);
        editTextPassword = (EditText) rootView.findViewById(R.id.editText_password);

        loginButton = (Button) rootView.findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Login", Toast.LENGTH_LONG).show();
                startLogin();
            }
        });

        createTextView = (TextView) rootView.findViewById(R.id.textView_create);
        createTextView.setOnClickListener(new View.OnClickListener() {
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

        String stringEmail = editTextEmail.getText().toString();
        String stringPassword = editTextPassword.getText().toString();
        HashMap<String, String> hashMapUser = new HashMap<String, String >();
        hashMapUser.put("e_mail", stringEmail);
        hashMapUser.put("password", stringPassword);

        RequestQueue queue = MySingletonForRequestQueue.getInstance(getActivity()).getRequestQueue();

        String url ="http://api.themoviedb.org/3/movie/popular?api_key=9ae53f3101bef7b8b4015690ea82b38c";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(hashMapUser), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loading.dismiss();
                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();

                startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        queue.add(jsonObjectRequest);


    }

}
