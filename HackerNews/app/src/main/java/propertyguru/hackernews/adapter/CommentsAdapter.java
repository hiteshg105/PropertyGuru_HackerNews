package propertyguru.hackernews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import propertyguru.hackernews.R;
import propertyguru.hackernews.model.Comment;
import propertyguru.hackernews.utils.Utils;


/**
 * Created by hitesh on 6/23/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {
    List<Comment> comments;

    public CommentsAdapter(ArrayList<Comment> comments) {
        this.comments=comments;
    }


    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_row, parent, false);
        return new CommentsViewHolder(parent.getContext(), v);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        Comment comment = comments.get(position);
        ( holder).setComment(comment, position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder {

        Context mContext;
        TextView mCommentText;
        TextView mCommentAuthor;
        TextView mCommentDate;
        RelativeLayout mContainer;

        public CommentsViewHolder(Context context, View view) {
            super(view);
            mContext = context;
            mCommentText = (TextView)view.findViewById(R.id.text_comment);
            mCommentAuthor = (TextView)view.findViewById(R.id.text_post_author);
            mCommentDate = (TextView)view.findViewById(R.id.text_post_date);
            mContainer = (RelativeLayout)view.findViewById(R.id.container_item);
        }

        public void setComment(Comment comment, int position) {
            if (comment.getText() != null) mCommentText.setText(Html.fromHtml(comment.getText().trim()));
            if (comment.getBy() != null) mCommentAuthor.setText(comment.getBy());
            long millisecond = comment.getTime() * 1000;
            mCommentDate.setText(new PrettyTime().format(new Date(millisecond)));
            setCommentIndent(comment.getDepth());

        }

        private void setCommentIndent(int depth) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                    mContainer.getLayoutParams();
            float margin = Utils.convertPixelsToDp(depth * 40, mContext);
            layoutParams.setMargins((int) margin, 0, 0, 0);
            mContainer.setLayoutParams(layoutParams);
        }
    }
}
