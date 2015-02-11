package co.infinum.ldevgoodies.activities;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import co.infinum.ldevgoodies.interfaces.JobSchedulerCallback;
import co.infinum.ldevgoodies.services.DemoJobService;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 11/02/15
 */
public class JobSchedulerActivity extends Activity implements JobSchedulerCallback {

    private static final String TAG = "DemoScheduler";
    public static final int JOB_ID = 0;
    public static final int SERVICE_OBJECT = 0;
    private ComponentName mComponentName;
    private DemoJobService mDemoJobService;

    /* main looper - handler should be static and instantiated as Weak reference */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SERVICE_OBJECT:
                    mDemoJobService = (DemoJobService) msg.obj;
                    mDemoJobService.setUiCallback(JobSchedulerActivity.this);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initService();
        createJob();
    }

    private void initService() {
        mComponentName = new ComponentName(this, DemoJobService.class);
        Intent service = new Intent(this, DemoJobService.class);
        service.putExtra("messenger", new Messenger(mHandler));
        startService(service);
    }

    private void createJob() {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder jobBuilder = new JobInfo.Builder(JOB_ID, mComponentName);
        jobBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        jobBuilder.setRequiresCharging(true);
        //jobBuilder.setRequiresDeviceIdle(true);
        // not for periodic jobs:
        // with initial delay:
        //jobBuilder.setMinimumLatency(60 * 1000);
        //jobBuilder.setOverrideDeadline(5 * 60 * 1000);
        scheduler.schedule(jobBuilder.build());
    }

    @Override
    public void onJobStarted(int jobId) {
        Log.d(TAG, "job started with id: " + jobId);
    }

    @Override
    public void onJobStopped(int jobId) {
        Log.d(TAG, "job stopped with id: " + jobId);
    }

}
