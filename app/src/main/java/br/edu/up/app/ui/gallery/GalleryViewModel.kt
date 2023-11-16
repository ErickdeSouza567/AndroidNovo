// GalleryViewModel.kt
package br.edu.up.app.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.BancoSQLite
import br.edu.up.app.data.Horario
import br.edu.up.app.data.HorarioRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HorarioRepositoryImpl
    val ultimaData: Flow<Horario?> // Alterado para Flow

    init {
        val produtoDao = BancoSQLite.get(application).produtoDao()
        repository = HorarioRepositoryImpl(produtoDao)
        ultimaData = repository.ultimaData
    }

    fun salvarData(data: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.inserirData(Horario(dataHora = data))
        }
    }
}
