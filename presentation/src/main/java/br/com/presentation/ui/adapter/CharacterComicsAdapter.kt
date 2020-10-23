package br.com.presentation.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.data.datasource.remote.response.comics.ComicResults
import br.com.presentation.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_comics_list_row.view.*

class CharacterComicsAdapter(
    private val characterComics: List<ComicResults>,
    private val activity: Activity
) :
    RecyclerView.Adapter<CharacterComicsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.character_comics_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characterComics.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterComic = characterComics[position]
        Glide.with(activity)
            .load("${characterComic.thumbnail.path}/portrait_xlarge.${characterComic.thumbnail.extension}")
            .placeholder(ContextCompat.getDrawable(activity, R.drawable.ic_launcher_background))
            .into(holder.ivComicImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivComicImage: ImageView = itemView.iv_comic_image
    }
}