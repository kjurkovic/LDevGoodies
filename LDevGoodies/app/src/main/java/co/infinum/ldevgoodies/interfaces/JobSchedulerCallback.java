package co.infinum.ldevgoodies.interfaces;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 11/02/15
 */
public interface JobSchedulerCallback {

    void onJobStarted(int jobId);

    void onJobStopped(int jobId);
}
