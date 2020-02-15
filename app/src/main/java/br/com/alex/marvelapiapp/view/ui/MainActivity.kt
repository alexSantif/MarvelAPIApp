package br.com.alex.marvelapiapp.view.ui

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View.GONE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.marvelapiapp.R
import br.com.alex.marvelapiapp.utils.hideSoftKeyboard
import br.com.alex.marvelapiapp.utils.isNetworkAvailable
import br.com.alex.marvelapiapp.utils.showSnackBar
import br.com.alex.marvelapiapp.utils.showToast
import br.com.alex.marvelapiapp.view.ui.adapter.MainAdapter
import br.com.alex.marvelapiapp.viewmodel.MainViewModel
import br.com.alex.marvelapiapp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
                true
            } else false
        }
    }
}
