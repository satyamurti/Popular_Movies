package com.mrspd.moviesappstage1.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrspd.moviesappstage1.R;
import com.mrspd.moviesappstage1.model.Trailer;

import java.util.List;

///////////////////////////////////////////////////////////////////////////
// Created by Satyamurti Doddini with ❤  only for udacity
///////////////////////////////////////////////////////////////////////////
public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.ViewHolder> {

    List<Trailer> trailers;
    public static Context context;

    public MovieTrailerAdapter(List<Trailer> trailers, Context context) {
        this.trailers = trailers;
        this.context = context;
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_view_trailers, parent, false);

        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(vip(trailers.get(position).getKey()))
                .into(holder.thumbnail);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return trailers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.iv_movieTrailer_thumbnail);
            thumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Trailer trailer = trailers.get(getAdapterPosition());
            Intent playVideo = new Intent(Intent.ACTION_VIEW);
            playVideo.setData(Uri.parse(vp(trailer.getKey())));
            playVideo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (playVideo.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(playVideo);
            } else {
                Toast.makeText(context, "Error playing the video", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public static String vp(String path) {
        return "https://youtube.com/watch?v="
                + path;
    }

    public static String vip(String path) {
        Log.d("MoviePath", "https://img.youtube.com/vi/"+ path
                + "/mqdefault.jpg") ;
        return "https://img.youtube.com/vi/" + path
                + "/mqdefault.jpg";
    }

}
