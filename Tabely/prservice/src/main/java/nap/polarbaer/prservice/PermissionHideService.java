package nap.polarbaer.prservice;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by jhans on 15.11.2016.
 * Edited by SnakesOnWheels on 21.11.2017.
 */

public class PermissionHideService extends Service {

    private static final String LOG_TAG = "PermissionHideService";
    private static final String START = "nap.polarbaer.prservice.PermissionHideService.START";
    private static final String STOP = "nap.polarbaer.prservice.PermissionHideService.STOP";
    private Toast toast;

    private View mOverlay;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "startCommand");
        String message = intent.getAction();
        if (START.equals(message))
            showPermissionTapjack();
        else
            hidePermissionTapjack();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        hidePermissionTapjack();
        super.onDestroy();
    }

    // Pure CopyPasta
    private void showPermissionTapjack() {
        Log.d(LOG_TAG, "showPermissionTapjack");
        if (mOverlay == null) {
            WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams
                    .TYPE_SYSTEM_OVERLAY, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams
                    .FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager
                    .LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

            layoutParams.format = PixelFormat.TRANSLUCENT;
            layoutParams.setTitle("Whack-A-Droid update");
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.x = 0;
            layoutParams.y = dipToPx(-40);
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.windowAnimations = 0;

            mOverlay = View.inflate(getApplicationContext(), R.layout.overlay, null);

            windowManager.addView(mOverlay, layoutParams);

//            // Using a fullscreen Toast
//            // Set duration
//            int toastDurationInMilliSeconds = 10000;
//            toast = Toast.makeText(getApplicationContext(), "Fullscreen toast, mate!!", Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.FILL_VERTICAL, 0, 0);
//            // Set the countdown to display the toast
//            CountDownTimer toastCountDown;
//            toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
//                public void onTick(long millisUntilFinished) {
//                    toast.show();
//                }
//                public void onFinish() {
//                    toast.cancel();
//                }
//            };
//            // Show the toast and starts the countdown
//            toast.show();
//            toastCountDown.start();

        }
    }

    private void hidePermissionTapjack() {
        Log.d(LOG_TAG, "hidingPermissionTapjack");
        if (mOverlay != null) {
            WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
            windowManager.removeViewImmediate(mOverlay);
            mOverlay = null;
        }
    }

    private int dipToPx(int dip) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources()
                .getDisplayMetrics()));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
