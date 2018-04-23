package com.example.voldarex.neuralmachinetranslation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.voldarex.neuralmachinetranslation.R;
import com.example.voldarex.neuralmachinetranslation.api.APIBahasaIndonesia;
import com.example.voldarex.neuralmachinetranslation.model.BahasaIndonesiaResponse;
import com.example.voldarex.neuralmachinetranslation.utils.Service;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cardViewHasilTerjemahan)
    CardView cardViewHasilTerjemahan;
    @BindView(R.id.editTextInputBahsa)
    EditText editTextInputBahasaTerjemahan;
    @BindView(R.id.buttonClear)
    ImageView buttonClear;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        callListBahasaIndonesia();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_exit) {
            this.finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);

    }

    @OnClick(R.id.buttonTranslate)
    public void translate() {
        cardViewHasilTerjemahan.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.buttonClear)
    public void clear() {
        editTextInputBahasaTerjemahan.setText(null);
        cardViewHasilTerjemahan.setVisibility(View.INVISIBLE);
        buttonClear.setVisibility(View.INVISIBLE);
    }

    @OnTextChanged(value = R.id.editTextInputBahsa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextInputChanged(Editable editable) {
        buttonClear.setVisibility(View.VISIBLE);
//        cardViewHasilTerjemahan.setVisibility(View.VISIBLE);
    }

    private void callListBahasaIndonesia() {
        APIBahasaIndonesia client = Service.createService(APIBahasaIndonesia.class);
        Call<BahasaIndonesiaResponse> call = client.getListBahasaIndonesia();
        call.enqueue(new Callback<BahasaIndonesiaResponse>() {
            @Override
            public void onResponse(Call<BahasaIndonesiaResponse> call, Response<BahasaIndonesiaResponse> response) {

            }

            @Override
            public void onFailure(Call<BahasaIndonesiaResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "AKSES KE SERVER GAGAL" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
