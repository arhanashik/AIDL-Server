package com.w3engineers.core.aidlserver;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.w3engineers.core.IAdd;
import com.w3engineers.core.Person;
import com.w3engineers.core.R;

import java.util.ArrayList;
import java.util.List;

/*
 *  ****************************************************************************
 *  * Created by : Md. Hasnain on 5/4/2018 at 10:24 AM.
 *  * Email : ashik.pstu.cse@gmail.com
 *  *
 *  * Last edited by : Md. Hasnain on 5/4/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

public class AdditionService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IAdd.Stub mBinder = new IAdd.Stub() {
        @Override
        public int addNumbers(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }

        public List<String> getStringList() throws RemoteException {
            return MainActivity.getList();
        }

        @Override
        public Person getPerson() throws RemoteException {
            return new Person(1, "Mr. Perfect", 24);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return MainActivity.getPersons();
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void placeCall(final String number) throws RemoteException {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_FROM_BACKGROUND);
            if (ActivityCompat
                    .checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                // use System.currentTimeMillis() to have a unique ID for the pending intent
                PendingIntent pIntent = PendingIntent
                        .getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent1, 0);

                // build notification
                // the addAction re-use the same intent to keep the example short
                Notification n = new Notification.Builder(getApplicationContext())
                        .setContentTitle("AIDL Server App")
                        .setContentText("Please grant call permission from settings")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(0, n);
                return;
            }
            startActivity(intent);
        }
    };
}
