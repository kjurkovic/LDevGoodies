package co.infinum.ldevgoodies.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.infinum.ldevgoodies.R;
import co.infinum.ldevgoodies.adapters.ExampleListAdapter;
import co.infinum.ldevgoodies.custom.decorators.VerticalDividerDecorator;


public class MainActivity extends Activity implements ExampleListAdapter.OnItemClickListener{

    public static final String CARDVIEW = "cardview";

    public static final String TOOLBAR = "toolbar";

    public static final String JOBSCHEDULER = "jobscheduler";

    public static final String SCREENPINNING = "screenpinning";

    private static final String[] items = new String[] { CARDVIEW, TOOLBAR, JOBSCHEDULER, SCREENPINNING };

    @InjectView(R.id.example_list)
    RecyclerView exampleList;

    private ExampleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setupRecycler();
    }

    private void setupRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ExampleListAdapter(items, this);
        exampleList.setHasFixedSize(true);
        exampleList.setLayoutManager(linearLayoutManager);
        exampleList.addItemDecoration(new VerticalDividerDecorator(this));
        exampleList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, View view) {
        Intent intent = null;
        switch (items[position]) {
            case CARDVIEW:
                intent = new Intent(this, CardViewActivity.class);
                break;
            case TOOLBAR:
                intent = new Intent(this, ToolbarActivity.class);
                break;
            case JOBSCHEDULER:
                intent = new Intent(this, JobSchedulerActivity.class);
                break;
            case SCREENPINNING:
                intent = new Intent(this, ScreenPinningActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
