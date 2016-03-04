package eric_hth.my360android;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by eric_360 on 04/03/16.
 */
public class M3UserRecyclerView extends RecyclerView {
    // MARK: private var
    private SHomeAdapter adapter;
    private List<M3UserActivity.User> objects;
    // MARK: SHomeRecycler()
    public M3UserRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SHomeAdapter();
        setAdapter(adapter);
    }
    // MARK: public
    public void loadData(List<M3UserActivity.User> objects){
        this.objects = objects;
        adapter.loadData(objects);
    }
    // MARK: private classes
    private class SHomeViewHolder extends RecyclerView.ViewHolder{
        // MARK: SHomeViewHolder()
        public SHomeViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
        // MARK: public var
        public TextView textView;
        public ImageView imageView;
    }
    private class SHomeAdapter extends RecyclerView.Adapter{
        // MARK: private var
        private List<M3UserActivity.User> data;
        // MARK: public
        public void loadData(List<M3UserActivity.User> objects){
            this.data = objects;
            this.notifyDataSetChanged();
        }
        // MARK: Adapter @Override
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.m3user_cell, null);
            return new SHomeViewHolder(view);
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            SHomeViewHolder viewHolder = (SHomeViewHolder) holder;
            viewHolder.textView.setText(data.get(position).getName());
            Picasso.with(viewHolder.imageView.getContext()).load("http://www.google.fr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").into(viewHolder.imageView);
        }
        @Override
        public int getItemCount() {
            if (data != null){
                return data.size();
            }
            return 0;
        }
    }
}
