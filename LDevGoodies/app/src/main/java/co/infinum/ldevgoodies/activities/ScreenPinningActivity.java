package co.infinum.ldevgoodies.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.infinum.ldevgoodies.R;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 11/02/15
 */
public class ScreenPinningActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.pin_action)
    Button mPinAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenpinning);
        ButterKnife.inject(this);
        mPinAction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isPinningIsOn()) {
            stopLockTask();
        } else {
            startLockTask();
        }
    }

    public boolean isPinningIsOn() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.isInLockTaskMode();
    }
}
