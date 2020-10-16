package br.com.sharedutils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.security.MessageDigest

fun showToast(context: Context, text: String) {
    GlobalScope.launch(Dispatchers.Main) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}

fun showSnackBar(context: Context, view: View, text: String) {
    GlobalScope.launch(Dispatchers.Main) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setBackgroundTint(
            ContextCompat.getColor(context, android.R.color.holo_red_dark)
        ).show()
    }
}

fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager: InputMethodManager = activity
        .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus?.windowToken, 0
    )
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}