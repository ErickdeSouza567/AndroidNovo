package br.edu.up.app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import br.edu.up.app.data.Horario
import br.edu.up.app.databinding.FragmentGalleryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        galleryViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
                .get(GalleryViewModel::class.java)

        val textView: TextView = binding.textGallery
        val datePicker: DatePicker = binding.datePicker
        val saveButton: Button = binding.saveButton
        val listButton: Button = binding.listButton

        galleryViewModel.ultimaData // Não é necessário chamar .observe()

        saveButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
            val data = calendar.timeInMillis
            galleryViewModel.salvarData(data)
        }

        // Modificação: Removendo a observação inicial para a última data

        // ...

        listButton.setOnClickListener {
            // Use corrotinas para observar as atualizações no LiveData apenas quando o botão "Listar" for clicado
            lifecycleScope.launchWhenStarted {
                galleryViewModel.ultimaData.collect { horario: Horario? ->
                    // Atualize a UI quando houver uma mudança na data
                    textView.text = "Última Data Salva:\n${formatarDataHora(horario?.dataHora)}"
                }
            }
        }

        return root
    }

    private fun formatarDataHora(dataHora: Long?): String {
        // Verifica se a dataHora não é nula
        return dataHora?.let {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dataHora
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateFormat.format(calendar.time)
        } ?: "N/A"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
