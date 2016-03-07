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
    private List<M3Server.M3UserWidgets> objects;
    // MARK: SHomeRecycler()
    public M3UserRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SHomeAdapter();
        setAdapter(adapter);
    }
    // MARK: public
    public void loadData(List<M3Server.M3UserWidgets> objects){
        this.objects = objects;
        adapter.loadData(objects);
    }
    // MARK: private classes
    private class SHomeViewHolder extends RecyclerView.ViewHolder{
        // MARK: public var
        public TextView textView;
        public ImageView imageView;
        // MARK: SHomeViewHolder()
        public SHomeViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
    private class SHomeAdapter extends RecyclerView.Adapter{
        // MARK: private var
        private List<M3Server.M3UserWidgets> data;
        // MARK: public
        public void loadData(List<M3Server.M3UserWidgets> objects){
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
            String urlBis = "http://app.360mooc.com/api/medias/user/" + data.get(position).getId() + "?thumbnail=true&company=" + M3LoginActivity.company;
            Picasso.with(viewHolder.imageView.getContext()).load(urlBis).into(viewHolder.imageView);
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
