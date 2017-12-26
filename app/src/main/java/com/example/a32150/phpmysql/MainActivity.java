package com.example.a32150.phpmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import okhttp3.*;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client;
    EditText account, password;
    String str_account, str_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.userid);
        password = findViewById(R.id.temperature);

    }

    public void onClickAdd(View v) {//add
       /// Log.d("Test", "GET HTTP");
        str_account=account.getText().toString();
        str_password=password.getText().toString();
        if(!str_account.equals("") && !str_password.equals("")) {
            client = new OkHttpClient();
            String pararm = "account=" + str_account + "&password=" + str_password;
            final Request request = new Request.Builder().url("http://192.168.58.3:8080/code/finalexam/add.php?" + pararm).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //Log.d("Result", "onFailure");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //Log.d("Result", "onResponse");
                    String json = response.body().string();
                    Log.d("Test", json);//抓取伺服器端網頁回應字串
                    if (json.equals("新增成功")) {//比對伺服器端網頁回應字串
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "新增成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "新增失敗,資料重複", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }   else    {
            Toast.makeText(MainActivity.this, "Account和Password不得為空", Toast.LENGTH_SHORT).show();
        }

    }
    public void onClickUpdate(View v) {//update
        /// Log.d("Test", "GET HTTP");
        str_account=account.getText().toString();
        str_password=password.getText().toString();
        if(!str_account.equals("") && !str_password.equals("")) {
            client = new OkHttpClient();
            String pararm = "account=" + str_account + "&password=" + str_password;
            final Request request = new Request.Builder().url("http://192.168.58.3:8080/code/finalexam/update.php?" + pararm).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //Log.d("Result", "onFailure");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //Log.d("Result", "onResponse");
                    String json = response.body().string();
                    Log.d("Test", json);//抓取伺服器端網頁回應字串
                    if (json.equals("修改成功")) {//比對伺服器端網頁回應字串
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "修改失敗,資料不存在", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            });
        }  else {
            Toast.makeText(MainActivity.this, "Account和Password不得為空", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickDelete(View v) {//delete
        /// Log.d("Test", "GET HTTP");
        str_account = account.getText().toString();
        if (!str_account.equals("")) {
            client = new OkHttpClient();
            String pararm = "account=" + str_account;
            final Request request = new Request.Builder().url("http://192.168.58.3:8080/code/finalexam/delete.php?" + pararm).build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //Log.d("Result", "onFailure");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //Log.d("Result", "onResponse");
                    String json = response.body().string();
                    Log.d("Test", json);//抓取伺服器端網頁回應字串
                    if (json.equals("刪除成功")) {//比對伺服器端網頁回應字串
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "刪除成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "刪除失敗,資料不存在", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Account不得為空", Toast.LENGTH_SHORT).show();
            Log.d("Test", "" + str_account.length());
        }
    }
}