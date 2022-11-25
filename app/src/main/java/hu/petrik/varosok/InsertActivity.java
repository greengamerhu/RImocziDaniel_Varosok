package hu.petrik.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class InsertActivity extends AppCompatActivity {
    private Button buttonBackFromInstert, buttonInsert;
    private EditText editTextLakossag, editTextOrszag, editTextVaros;
    private String url = "https://retoolapi.dev/NjPci3/varosok";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        init();
        buttonBackFromInstert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lak =  editTextLakossag.getText().toString();
                String varos = editTextVaros.getText().toString();
                String orszag = editTextOrszag.getText().toString();
                if (varos.isEmpty()) {
                    Toast.makeText(InsertActivity.this,
                            "A Város mező nem lehet ures", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (orszag.isEmpty()) {
                    Toast.makeText(InsertActivity.this,
                            "Az Ország mező nem lehet ures", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lak.isEmpty()) {
                    Toast.makeText(InsertActivity.this,
                            "A lakosság mező nem lehet ures", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson conv = new Gson();
                Varos varoska = new Varos(0, varos, orszag, Integer.parseInt(lak));
                RequestTask task = new RequestTask(url, "POST", conv.toJson(varoska) );
                task.execute();

            }
        });

    }
    private void init() {
        buttonBackFromInstert = findViewById(R.id.buttonBackFromInstert);
        editTextLakossag = findViewById(R.id.editTextLakossag);
        buttonInsert = findViewById(R.id.buttonInsert);
        editTextOrszag = findViewById(R.id.editTextOrszag);
        editTextVaros = findViewById(R.id.editTextVaros);
        editTextLakossag.setText("0");
    }
    public void urlapAlaphelyzetbe() {
        editTextLakossag.setText("");
        editTextOrszag.setText("");
        editTextVaros.setText("");
    }
    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }

        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }

            } catch (IOException e) {
                Toast.makeText(InsertActivity.this,
                        e.toString(), Toast.LENGTH_SHORT).show();
            }
            return response;
        }



        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);
            Gson converter = new Gson();

            switch (requestType) {
                case "POST":
                    Toast.makeText(InsertActivity.this, "Sikeres felvétel", Toast.LENGTH_SHORT).show();
                    urlapAlaphelyzetbe();
                    break;

            }
        }
    }

}