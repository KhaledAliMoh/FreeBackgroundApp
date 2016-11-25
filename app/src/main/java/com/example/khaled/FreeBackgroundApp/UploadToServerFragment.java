package com.example.khaled.FreeBackgroundApp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;


/**
 * Created by Khaled on 11/25/2016.
 */
public class UploadToServerFragment extends Fragment {
    String TAG = "Upload To Server Fragment";
    int PICK_IMAGE = 1;
    ImageView imageViewImageToUpload;
    Button buttonChooseImage;
    Button buttonUploadImage;
    Bitmap bitmap;
    Uri imageFileUri;
    String imageFileName;
    int serverResponseCode = 0;
    private ProgressDialog dialog = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_upload_to_server, container, false);
        imageViewImageToUpload = (ImageView) rootView.findViewById(R.id.image_view_upload_image);
        buttonChooseImage = (Button) rootView.findViewById(R.id.browse_images_button);
        buttonUploadImage = (Button) rootView.findViewById(R.id.upload_image_button);

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        return rootView;
    }

    void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "khaled " + HelperClass.getPath(getActivity(), data.getData()), Toast.LENGTH_LONG).show();
            imageFileUri = data.getData();
            Log.d(TAG, "khaled " + HelperClass.getPath(getActivity(), data.getData()));
            imageFileName = HelperClass.getPath(getActivity(), data.getData()).toString();
            File file = new File(imageFileName);
            if (file.isFile()) {
                Log.d(TAG, "khaled1111 " + data.getData());
                Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                //Drawable d = new BitmapDrawable(getResources(), myBitmap);
                imageViewImageToUpload.setImageBitmap(myBitmap);
            }

            /*try {
                if (bitmap != null) {
                    bitmap.recycle();
                }

                imageFileUri = data.getData();
                InputStream stream = getActivity().getContentResolver().openInputStream(imageFileUri);
                imageFileName = getFileName(imageFileUri);
                bitmap = BitmapFactory.decodeStream(stream);

                stream.close();
                //Log.d(TAG, imageFilePath.toString());
                //Log.d(TAG, imageFileName.toString());
                //Toast.makeText(getActivity(), data.getData().getPath().toString(), Toast.LENGTH_LONG).show();
                imageViewImageToUpload.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            super.onActivityResult(requestCode, resultCode, data);
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...

        }
    }


    void uploadImage() {
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Upload Image", "Please wait...", false, false);
        String imageAsString = getStringImage(bitmap);
        HashMap<String, String> output = new HashMap<String, String>();
        output.put("image", imageAsString);
        RequestQueue queue = MySingletonForRequestQueue.getInstance(getActivity()).getRequestQueue();

        String url = "http://backgroundfree.pe.hu/api/storewallpaper";
        //Toast.makeText(getActivity(), new JSONObject(input).toString(), Toast.LENGTH_LONG).show();

        Toast.makeText(getActivity(), "KH " + new JSONObject(output).toString(), Toast.LENGTH_LONG).show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(output), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loading.dismiss();
                Toast.makeText(getActivity(), "On Res " + response.toString(), Toast.LENGTH_LONG).show();
                Log.d(TAG, response.toString());
                //startActivity(new Intent(getActivity().getApplicationContext(), HomeActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Log.d(TAG, error.toString());
                Toast.makeText(getActivity(), "Err " + error.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

        queue.add(jsonObjectRequest);


    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
