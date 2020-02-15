package br.com.alex.marvelapiapp.view.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.marvelapiapp.R
import br.com.alex.marvelapiapp.data.datasource.remote.response.comics.ComicResults
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.comics_list_row.view.*

class MainAdapter (
    private val comics: List<ComicResults>,
    private val activity: Activity
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.comics_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = comics[position]
        holder.tvComicTitle.text = comic.title
        Glide.with(activity).load("${comic.thumbnail.path}/portrait_xlarge.${comic.thumbnail.extension}").into(holder.ivComicImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvComicTitle: TextView = itemView.tv_comic_title
        val ivComicImage: ImageView = itemView.iv_comic_image
    }
}