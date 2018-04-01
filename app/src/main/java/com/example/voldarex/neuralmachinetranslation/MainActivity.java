package com.example.voldarex.neuralmachinetranslation;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


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
    }

    @OnTextChanged(value = R.id.editTextInputBahsa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextInputChanged(Editable editable) {
        buttonClear.setVisibility(View.VISIBLE);
    }
}
