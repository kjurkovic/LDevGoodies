package co.infinum.ldevgoodies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.infinum.ldevgoodies.R;

/**
 * @author Kristijan Jurkovic
 *         kristijan.jurkovic@infinum.hr
 * @since 11/02/15
 */
public class ExampleListAdapter extends RecyclerView.Adapter<ExampleListAdapter.ViewHolder> {

    public interface OnItemClickListener {
        public void onItemClick(int position, View view);
    }

    private String[] items;

    private OnItemClickListener onItemClickListener;

    public ExampleListAdapter(String[] items, OnItemClickListener listener) {
        this.items = items;
        this.onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemName.setText(items[i]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnItemClickListener onItemClickListener;

        @InjectView(R.id.item_name)
        TextView itemName;

        ViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            onItemClickListener = listener;
            ButterKnife.inject(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getPosition(), v);
            }
        }
    }
}
