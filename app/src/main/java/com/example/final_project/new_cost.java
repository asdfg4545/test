package com.example.final_project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class new_cost extends AppCompatActivity {
    private DBHelper helper;
    private EditText et_cost_title;
    private EditText et_cost_money;
    private DatePicker dp_cost_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cost);
        initView();
    }

    private void initView() {
        helper = new DBHelper(new_cost.this);
        et_cost_title = findViewById(R.id.et_cost_title);
        et_cost_money = findViewById(R.id.et_cost_money);
        dp_cost_date = findViewById(R.id.dp_cost_date);
    }

    public void okButton(View view) {
        String titleStr = et_cost_title.getText().toString().trim();
        String moneyStr = et_cost_money.getText().toString().trim();
        String dateStr = dp_cost_date.getYear() +  "-" + (dp_cost_date.getMonth() + 1) + "-" + dp_cost_date.getDayOfMonth();
        if (moneyStr.isEmpty()) {
            Toast toast = Toast.makeText(this, "請填寫金額", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Title", titleStr);
            values.put("Money", moneyStr);
            values.put("Date", dateStr);
            long account = db.insert("account", null, values);
            if (account > 0) {
                Toast toast = Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                setResult(1);
                finish();
            } else {
                Toast toast = Toast.makeText(this, "請重試", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                db.close();
            }
            setResult(1);
            finish();
        }
    }

    public void cancelButton(View view) {
        setResult(1);
        finish();
    }
}