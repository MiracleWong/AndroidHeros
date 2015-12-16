package com.miraclewong.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
    // 显示通知的形式
    private int NOTIFICATION_ID_BASIC = 1;
    private int NOTIFICATION_ID_COLLAPSE = 2;
    private int NOTIFICATION_ID_HEADSUP = 3;
    private int NOTIFICATION_ID_VISIBILITY = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * 构建基本式的Notification
     * @param view
     */
    public void basicNotify(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.baidu.com"));
        // 构造PendingIntent，需要在普通的Intent上面包装一层
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        // 创建Notification对象
        Notification.Builder builder = new Notification.Builder(this);
        // 设置Notification的各种属性
        builder.setSmallIcon(R.mipmap.ic_launcher);         // PS：没有设置smallIcon的时候，不会产生Notification，但是不设置LargeIcon是可以产生Notification的
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("Basic Notifications");
        builder.setContentText("I am a basic notification");
        builder.setSubText("it is really basic");
        builder.setTicker("dsfadsaf");
        // 通过NotificationManager来发出Notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_BASIC,
                builder.build());
    }

    public void collapsedNotify(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.sina.com"));
        // 构造PendingIntent，需要在普通的Intent上面包装一层
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, 0);
        // 创建Notification对象
        Notification.Builder builder = new Notification.Builder(this);
        // 设置Notification的各种属性
        builder.setSmallIcon(R.mipmap.ic_launcher);         // PS：没有设置smallIcon的时候，不会产生Notification，但是不设置LargeIcon是可以产生Notification的
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        // 通过RemoteViews建立自定义的Notification视图
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification);
        contentView.setTextViewText(R.id.textView, "Show me when collapsed");
        Notification notification = builder.build();
        // 指定视图
        notification.contentView = contentView;
        // 通过RemoteViews建立自定义的Notification视图——下拉时候的大视图
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification_expanded);
        // 指定视图
        notification.bigContentView = expandedView;

        // 通过NotificationManager来发出Notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_COLLAPSE,
                notification);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
