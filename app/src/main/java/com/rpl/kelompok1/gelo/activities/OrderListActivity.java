package com.rpl.kelompok1.gelo.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rpl.kelompok1.gelo.R;
import com.rpl.kelompok1.gelo.adapters.OrderAdapter;
import com.rpl.kelompok1.gelo.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends AppCompatActivity {
    private ListView listViewOrder;
    private List<Order> listOrder;
    private OrderAdapter mOrderAdapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    Query query, query2, query3, query4, query5, query6;
    FirebaseUser user;
    String status;
    NotificationCompat.Builder builder;

    @Override
    protected void onStart() {
        super.onStart();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listOrder.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Order order = postSnapshot.getValue(Order.class);
                    listOrder.add(order);
                    status = order.getStatus();

                    Intent intent = new Intent(OrderListActivity.this, OrderListActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(OrderListActivity.this, 0, intent, 0);

                    if(status.equalsIgnoreCase("dijemput")){
                        builder.setContentTitle("Laundry Dijemput.")
                                .setContentText("Laundry anda sedang dijemput.")
                                .setSubText("Tap to view the order.")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent);
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = builder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        notificationManager.notify(1, notification);
                    }else if(status.equalsIgnoreCase("diproses")){
                        builder.setContentTitle("Laundry Diproses.")
                                .setContentText("Laundry anda sedang diproses.")
                                .setSubText("Tap to view the order.")
                                .setContentIntent(pendingIntent);
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = builder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        notificationManager.notify(1, notification);
                    }else if(status.equalsIgnoreCase("sudah jadi")){
                        builder.setContentTitle("Laundry Sudah Jadi.")
                                .setContentText("Laundry anda siap diantar.")
                                .setSubText("Tap to view the order.")
                                .setContentIntent(pendingIntent);
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = builder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        notificationManager.notify(1, notification);
                    }else if(status.equalsIgnoreCase("diantar")){
                        builder.setContentTitle("Laundry Sedang Diantar.")
                                .setContentText("Laundry anda sedang diantar, mohon menyiapkan uang.")
                                .setSubText("Tap to view the order.")
                                .setContentIntent(pendingIntent);
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = builder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        notificationManager.notify(1, notification);
                    }else if(status.equalsIgnoreCase("selesai")){
                        builder.setContentTitle("Laundry Selesai!.")
                                .setContentText("Terima kasih telah melakukan pesanan.")
                                .setSubText("Tap to view the order.")
                                .setContentIntent(pendingIntent);
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification = builder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        notificationManager.notify(1, notification);
                        listViewOrder.setClickable(false);
                    }
                }

                mOrderAdapter = new OrderAdapter(OrderListActivity.this, listOrder);
                listViewOrder.setAdapter(mOrderAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        /*query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {




                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference("order");
        query =  mDatabase.orderByChild("idUser").equalTo(user.getUid());

        builder = new NotificationCompat.Builder(OrderListActivity.this);
        builder.setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        listViewOrder = (ListView) findViewById(R.id.listViewOrder);
        listOrder = new ArrayList<>();
    }
}
