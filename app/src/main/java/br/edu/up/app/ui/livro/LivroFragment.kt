package br.edu.up.app.ui.livro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.up.app.data.Livro
import br.edu.up.app.databinding.FragmentLivroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LivroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : LivroViewModel by activityViewModels()
        val binding = FragmentLivroBinding.inflate(layoutInflater)

        val produto = viewModel.livro
        binding.inputNome.setText(produto.nome)
        binding.inputDescricao.setText(produto.descricao)
        binding.inputPreco.setText(produto.preco.toString())
        binding.inputPeso.setText(produto.peso.toString())
        binding.inputFoto.setText(produto.foto)

        binding.btnSalvar.setOnClickListener {
            val livroSalvar = Livro(
                produto.id,
                produto.docId,
                binding.inputNome.text.toString(),
                binding.inputDescricao.text.toString(),
                binding.inputPreco.text.toString().toDouble(),
                binding.inputPeso.text.toString().toInt(),
                binding.inputFoto.text.toString(),
                0
            )
            viewModel.livro = livroSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }

        return binding.root
    }
}