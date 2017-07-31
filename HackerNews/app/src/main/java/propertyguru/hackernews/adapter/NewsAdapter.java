package propertyguru.hackernews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import propertyguru.hackernews.R;
import propertyguru.hackernews.feature.comments.CommentsActivity;
import propertyguru.hackernews.feature.home.NewsFragment;
import propertyguru.hackernews.model.Story;

/**
 * Created by hitesh on 6/21/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Story>  listStories;
    private int          rowLayout;
    private NewsFragment context;

    public NewsAdapter(List<Story> listIds, int rowLayout, NewsFragment context) {
        this.listStories = listIds;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        holder.title.setText(listStories.get(position).getTitle());
        holder.score.setText(String.valueOf(listStories.get(position).getScore()));
        holder.author.setText("by- "+ listStories.get(position).getBy());
    }

    @Override
    public int getItemCount() {
        return listStories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView score;
        TextView author;
        TextView comments;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.heading);
            score = (TextView) itemView.findViewById(R.id.score);
            author = (TextView) itemView.findViewById(R.id.author);
            comments = (TextView) itemView.findViewById(R.id.text_comment);

            comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();

                    context.startActivity(CommentsActivity.getStartIntent(v.getContext(), listStories.get(itemPosition)));

                }
            });
        }

    }
}
