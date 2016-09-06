package com.nacker.xutils3;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.io.File;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    private String url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "http://www.baidu.com";
    }

    @Event(R.id.get)
    private void get (View v) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍后");

        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("username" , "nacker");
        params.addQueryStringParameter("pwd" , "123456");

        x.http().get(params, new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Log.i("nacker","onSuccess---" + result);

                progressDialog.cancel();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                progressDialog.cancel();
            }
        });
    }

    @Event(R.id.post)
    private void post (View v) {

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userName","nacker");
//        上下两句相等
        params.addParameter("userName","nacker");


        params.addHeader("token","nacker");

        x.http().post(params, new Callback.CommonCallback<Object>() {

            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Event(R.id.other)
    private void other (View v) {

        RequestParams params = new RequestParams(url);
        params.addParameter("userName","nacker");

        x.http().request(HttpMethod.PUT,params, new Callback.CommonCallback<String>(){


            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Event(R.id.upload)
    private void upload (View v) {

        String path = "/mnt/sdcard/Download/1.jpg";

        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file", new File(path));


        x.http().post(params, new Callback.CommonCallback<Object>() {

            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Event(R.id.download)
        private void download (View v) {
            RequestParams params = new RequestParams(url);
            params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/app");
            params.setAutoRename(true);
            x.http().post(params, new Callback.ProgressCallback<File>() {


                @Override
                public void onSuccess(File result) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                    getActivity().startActivity(intent);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public void onWaiting() {

                }

                @Override
                public void onStarted() {

                }

                @Override
                public void onLoading(long total, long current, boolean isDownloading) {
                    Log.i("nacker","current<<"+current + "total<<"+total);
                }
            });
         }

    @Event(R.id.cache)
    private void cache (View v) {

        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(1000 * 60);
        x.http().get(params, new Callback.CacheCallback<Object>(){


            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(Object result) {
                // false 不相信本地缓存
                // true 相信本地缓存
                return false;
            }
        });

    }
}
