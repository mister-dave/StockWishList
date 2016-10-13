package layout;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.david.stockwishlist.R;


public class FragmentNotification extends BaseFragment {
    private EditText ed2, ed3;
    private Button b1;
    private int mNotificationId = 000;

    public static BaseFragment newInstance() {
        Bundle bundle = new Bundle();
        BaseFragment fragment = new FragmentNotification();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setHasOptionsMenu(true);


    }

    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_notification;
    }

    @Override
    void findViews() {
        ed2 = (EditText) mRootView.findViewById(R.id.editText2);
        ed3 = (EditText) mRootView.findViewById(R.id.editText3);
        b1 = (Button) mRootView.findViewById(R.id.buttonTextEmail);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "button click");
                delayedExecut();

            }
        });


    }

    public void delayedExecut() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                new AlertDialog.Builder(getContext())
                        .setTitle("Warning!")
                        .setMessage("You have lost connectivity to device. Do want to reconnect?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue
                                executeNotification();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        }, 5000);
    }

    public void executeNotification() {
        String title = ed2.getText().toString().trim();
        String body = ed3.getText().toString().trim();

        mNotificationId++;

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(body);
//
//        // Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, title);
        sendIntent.putExtra(Intent.EXTRA_TITLE,"extratitle");

        sendIntent.setType("text/plain");

//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//        sendIntent.setType("text/html");
//        sendIntent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
//        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Re: " + title);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "body");


        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(sendIntent);
//            startActivity(Intent.createChooser(sendIntent, "Send Email"));
        }

        // Intent resultIntent = new Intent(getContext(), FragmentFetchDatabase.class);
        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        getContext(),
                        0,
                        sendIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);


        //shows the notification in header
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
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

    public void notification2() {

    }
}
