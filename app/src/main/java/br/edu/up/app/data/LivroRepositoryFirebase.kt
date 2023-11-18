package br.edu.up.app.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LivroRepositoryFirebase
    @Inject constructor(val produtosRef : CollectionReference): LivroRepository {

    private var _produtos = MutableStateFlow(listOf<Livro>())
    override val produtos: Flow<List<Livro>> get() = _produtos.asStateFlow()

    init {
        produtosRef.addSnapshotListener { snapshot, _ ->
            if (snapshot == null){
                _produtos = MutableStateFlow(listOf())
            } else {
                var livros = mutableListOf<Livro>()
                snapshot.documents.forEach { doc ->
                    val livro = doc.toObject<Livro>()
                    if (livro != null){
                        livro.docId = doc.id
                        livros.add(livro)
                    }
                }
                _produtos.value = livros
            }

        }
    }

    override suspend fun salvar(livro: Livro) {
        if(livro.docId.isNullOrEmpty()){
            var doc = produtosRef.document()
            livro.docId = doc.id
            doc.set(livro)
        } else {
            produtosRef.document(livro.docId).set(livro)
        }
    }

    override suspend fun excluir(livro: Livro) {
        produtosRef.document(livro.docId).delete()
    }

    override suspend fun excluirTodos() {
        _produtos.collect{ produtos ->
            produtos.forEach{ produto ->
                produtosRef.document(produto.docId).delete()
            }
        }
    }
}