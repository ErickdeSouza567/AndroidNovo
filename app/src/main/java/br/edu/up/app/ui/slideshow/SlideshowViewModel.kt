package br.edu.up.app.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Filme mais locado da semana:"
    }
    val text: LiveData<String> = _text
}