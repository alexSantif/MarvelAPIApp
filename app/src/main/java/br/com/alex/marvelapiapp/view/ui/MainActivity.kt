package br.com.alex.marvelapiapp.view.ui

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
import br.com.alex.marvelapiapp.utils.hideSoftKeyboard
import br.com.alex.marvelapiapp.utils.isNetworkAvailable
import br.com.alex.marvelapiapp.utils.showSnackBar
import br.com.alex.marvelapiapp.utils.showToast
import br.com.alex.marvelapiapp.view.ui.adapter.CharacterComicsAdapter
import br.com.alex.marvelapiapp.view.ui.adapter.MainAdapter
import br.com.alex.marvelapiapp.viewmodel.MainViewModel
import br.com.alex.marvelapiapp.viewmodel.ViewModelFactory
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
        if (isNetworkAvailable(this)) {
            viewModel.getComics()
            viewModel.comicsLiveData.observe(this, Observer {
                pb_loading_data.visibility = GONE

                if (it == null) {
                    showToast(this, "Erro ao baixar a lista de HQs")
                } else {
                    rv_comics_list.adapter = MainAdapter(it, this)
                    val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    rv_comics_list.layoutManager = layoutManager
                }
            })
        } else {
            showSnackBar(this, cl_main_activity, "Sem conexão com a Internet")
        }

        et_search_view.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideSoftKeyboard(this@MainActivity)
                getCharacter()
                true
            } else false
        }
    }

    private fun getCharacter() {
        if (isNetworkAvailable(this)) {
            pb_loading_data.visibility = VISIBLE
            rv_comics_list.visibility = GONE
            viewModel.getCharacterByName(et_search_view.text.toString())
            viewModel.characterLiveData.observe(this, Observer {
                iv_hero_image.visibility = VISIBLE
                pb_loading_data.visibility = GONE
                tv_hero_title.visibility = VISIBLE
                tv_hero_description.visibility = VISIBLE
                tv_hero_comics.visibility = VISIBLE
                rv_hero_comics.visibility = VISIBLE

                if (it == null) {
                    showToast(this, "Erro ao buscar a personagem")
                } else {
                    tv_hero_title.text = it[0].name
                    tv_hero_description.text = it[0].description
                    tv_hero_comics.text = "Veja as HQs de ${et_search_view.text}"
                    Glide.with(this)
                        .load("${it[0].characterThumbnail?.path}/portrait_xlarge.${it[0].characterThumbnail?.extension}")
                        .into(iv_hero_image)

                    viewModel.getCharacterComics(it[0].id)
                    viewModel.characterComicsLiveData.observe(this, Observer { characterComics ->
                        if (characterComics == null) {
                            showToast(this, "Erro ao buscar as HQs da personagem")
                        } else {
                            rv_hero_comics.adapter = CharacterComicsAdapter(characterComics, this)
                            val layoutManager =
                                LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                            rv_hero_comics.layoutManager = layoutManager
                        }
                    })
                }
            })
        } else {
            showToast(this, "Sem conexão com a Internet")
        }
    }
}
