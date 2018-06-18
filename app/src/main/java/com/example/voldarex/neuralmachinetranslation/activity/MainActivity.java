package com.example.voldarex.neuralmachinetranslation.activity;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.voldarex.neuralmachinetranslation.R;
import com.example.voldarex.neuralmachinetranslation.api.APITranslation;
import com.example.voldarex.neuralmachinetranslation.model.Sentence;
import com.example.voldarex.neuralmachinetranslation.utils.CustomAboutDialogClass;
import com.example.voldarex.neuralmachinetranslation.utils.CustomExitDialogClass;
import com.example.voldarex.neuralmachinetranslation.utils.CustomFontTextView;
import com.example.voldarex.neuralmachinetranslation.utils.Service;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    int id = 3;

    @BindView(R.id.cardViewHasilTerjemahan)
    LinearLayout cardViewHasilTerjemahan;

    @BindView(R.id.editTextInputBahsa)
    EditText editTextInputBahasaTerjemahan;

    @BindView(R.id.textViewHasilTerjemahan)
    CustomFontTextView textViewHasilTerjemahan;

    @BindView(R.id.buttonClear)
    CustomFontTextView buttonClear;

    private Toolbar mToolbar;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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

            CustomExitDialogClass exitDialog = new CustomExitDialogClass(this);
            exitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            exitDialog.show();
        } else if (id == R.id.action_about) {
            CustomAboutDialogClass aboutDialog = new CustomAboutDialogClass(this);
            aboutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            aboutDialog.show();

        }

        return super.onOptionsItemSelected(item);

    }

    @OnClick(R.id.buttonTranslate)
    public void translate() {
        progress = ProgressDialog.show(MainActivity.this, "Toba Translate", "Menerjemahkan", true);

        callApiTranslate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getApiTranslate();
                cardViewHasilTerjemahan.setVisibility(View.VISIBLE);
                progress.dismiss();
            }
        }, 2000);


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
    }

    private void callApiTranslate() {
        Sentence sentence = new Sentence();
        String input_bahasa = editTextInputBahasaTerjemahan.getText().toString();
        sentence.setText(input_bahasa);
        APITranslation client = Service.createService(APITranslation.class);
        Call<Sentence> call = client.putSentence(sentence.getText());
        call.enqueue(new Callback<Sentence>() {
            @Override
            public void onResponse(Call<Sentence> call, Response<Sentence> response) {

            }

            @Override
            public void onFailure(Call<Sentence> call, Throwable t) {

            }
        });
    }

    private void getApiTranslate() {
        APITranslation client = Service.createService(APITranslation.class);
        Call<Sentence> call = client.getSentence();
        call.enqueue(new Callback<Sentence>() {
            @Override
            public void onResponse(Call<Sentence> call, Response<Sentence> response) {
                Sentence sentence = response.body();
                String output_sentence = sentence.getText().toString();
                System.out.println(output_sentence);
                textViewHasilTerjemahan.setText(output_sentence);
            }

            @Override
            public void onFailure(Call<Sentence> call, Throwable t) {

            }
        });
    }
}
