package br.com.alex.marvelapiapp.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.marvelapiapp.R
import br.com.data.datasource.entity.Comic
import br.com.data.datasource.remote.response.characters.CharacterResult
import br.com.data.datasource.remote.response.comics.ComicResults
import br.com.sharedutils.hideSoftKeyboard
import br.com.sharedutils.isNetworkAvailable
import br.com.sharedutils.showToast
import br.com.alex.marvelapiapp.ui.adapter.CharacterComicsAdapter
import br.com.alex.marvelapiapp.ui.adapter.MainAdapter
import br.com.viewmodel.MainViewModel
import br.com.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        val viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this@MainActivity, viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.getComics()
        viewModel.comicsLiveData.observe(this, Observer {
            pb_loading_data.visibility = GONE

            if (it.isEmpty()) {
                showToast(this, "Nenhuma HQ encontrada")
            } else {
                buildComicsList(it)
            }
        })

        et_search_view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideSoftKeyboard(this@MainActivity)
                getCharacter()
                true
            } else false
        }
    }

    private fun buildComicsList(it: MutableList<Comic>) {
        rv_comics_list.adapter = MainAdapter(it, this)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_comics_list.layoutManager = layoutManager
    }

    private fun getCharacter() {
        if (isNetworkAvailable()) {
            pb_loading_data.visibility = VISIBLE
            rv_comics_list.visibility = GONE
            viewModel.getCharacterByName(et_search_view.text.toString())
            viewModel.characterLiveData.observe(this, Observer {
                if (it.isEmpty()) {
                    showToast(
                        this,
                        "Personagem não encontrado"
                    )
                } else {
                    changeViewsVisibility()
                    buildCharacterData(it)
                    viewModel.getCharacterComics(it[0].id)
                    viewModel.characterComicsLiveData.observe(this, Observer { characterComics ->
                        if (characterComics == null) {
                            showToast(
                                this,
                                "Erro ao buscar as HQs da personagem"
                            )
                        } else {
                            buildCharacterComicsList(characterComics)
                        }
                    })
                }
            })
        } else {
            showToast(this, "Sem conexão com a Internet")
        }
    }

    private fun buildCharacterComicsList(characterComics: MutableList<ComicResults>) {
        rv_hero_comics.adapter = CharacterComicsAdapter(characterComics, this)
        val layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_hero_comics.layoutManager = layoutManager
    }

    private fun buildCharacterData(it: MutableList<CharacterResult>) {
        tv_hero_title.text = it[0].name
        tv_hero_description.text = it[0].description
        tv_hero_comics.text = "Veja as HQs de ${et_search_view.text}"
        Glide.with(this)
            .load("${it[0].characterThumbnail?.path}/portrait_xlarge.${it[0].characterThumbnail?.extension}")
            .into(iv_hero_image)
    }

    private fun changeViewsVisibility() {
        iv_hero_image.visibility = VISIBLE
        pb_loading_data.visibility = GONE
        tv_hero_title.visibility = VISIBLE
        tv_hero_description.visibility = VISIBLE
        tv_hero_comics.visibility = VISIBLE
        rv_hero_comics.visibility = VISIBLE
    }
}
