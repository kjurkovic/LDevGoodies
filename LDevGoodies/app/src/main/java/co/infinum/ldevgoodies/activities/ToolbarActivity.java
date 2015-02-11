package co.infinum.ldevgoodies.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.infinum.ldevgoodies.R;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 11/02/15
 */
public class ToolbarActivity extends Activity implements Toolbar.OnMenuItemClickListener {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        ButterKnife.inject(this);
        setToolbar();
    }

    private void setToolbar() {
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setBackgroundColor(getResources().getColor(R.color.test_app_primary));
        mToolbar.setOnMenuItemClickListener(this);
        mToolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Once in awhile someone amazing comes along. So here I am...", Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }
}
