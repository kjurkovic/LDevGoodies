package co.infinum.ldevgoodies.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import co.infinum.ldevgoodies.activities.JobSchedulerActivity;
import co.infinum.ldevgoodies.interfaces.JobSchedulerCallback;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 11/02/15
 */
public class DemoJobService extends JobService {

    private JobSchedulerCallback mJobSchedulerCallback;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Messenger callback = intent.getParcelableExtra("messenger");
        Message m = Message.obtain();
        m.what = JobSchedulerActivity.SERVICE_OBJECT;
        m.obj = this;
        try {
            callback.send(m);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return START_NOT_STICKY;
    }

    /* these 2 are new ones :) */

    @Override
    public boolean onStartJob(JobParameters params) {
        // job started
        switch (params.getJobId()) {
            case JobSchedulerActivity.JOB_ID:
                if (mJobSchedulerCallback != null) {
                    mJobSchedulerCallback.onJobStarted(params.getJobId());
                }
                // do some cool stuff here preferably on another thread...
                // when finished:
                jobFinished(params, false);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // job stopped
        if (mJobSchedulerCallback != null) {
            mJobSchedulerCallback.onJobStopped(params.getJobId());
        }
        return true;
    }

    public void setUiCallback(JobSchedulerCallback uiCallback) {
        mJobSchedulerCallback = uiCallback;
    }
}
