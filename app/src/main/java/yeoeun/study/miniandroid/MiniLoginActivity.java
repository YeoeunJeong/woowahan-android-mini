package yeoeun.study.miniandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

public class MiniLoginActivity extends AppCompatActivity {
    @Bind(R.id.login_id_edittext)
    EditText editId;

    @Bind(R.id.login_pw_edittext)
    EditText editPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_signin_button)
    void onSiginClick() {
        String adminId = editId.getText().toString();
        String adminPw = editPw.getText().toString();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // editor.putString("login_check", "true");
        editor.putString("admin_id", adminId);
        editor.putString("admin_pw", adminPw);
        editor.commit();

        Intent intent = new Intent(MiniLoginActivity.this, MiniMainActivity.class);
        startActivity(intent);
        finish();
    }
}
