package ir.madreseplus.ui.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class RvAdapter<T, E extends RvViewHolder> extends RecyclerView.Adapter<E> {
    private OnRvItemClickListener<T> onRvItemClickListener;

    protected List<T> items = new ArrayList<>();


    public RvAdapter() {

    }

    public RvAdapter(List<T> items) {
        this.items = items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void clearAllItems(){
        this.items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public void onBindViewHolder(@NonNull E holder, int position) {
        holder.bind(items.get(position));

        if (onRvItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onRvItemClickListener.onItemClick(items.get(position), position));
        }
    }

    public void setOnRvItemClickListener(OnRvItemClickListener<T> onRvItemClickListener) {
        this.onRvItemClickListener = onRvItemClickListener;
    }

    public void removeItem(T item) {
        int index = items.indexOf(item);
        if (index > RecyclerView.NO_POSITION) {
            items.remove(index);
            notifyItemRemoved(index);
        }
    }
}
