package br.edu.up.app

import android.app.Application
import android.content.Context
import br.edu.up.app.data.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppCardapio : Application() {

    @Provides
    fun provideProdutosRef() : CollectionReference {
        return Firebase.firestore.collection("produtos")
    }

    @Provides
    fun provideProdutoRepositoryFirebase(produtosRef: CollectionReference)
                                                          : LivroRepository{
        return LivroRepositoryFirebase(produtosRef)
    }

    @Provides
    fun provideProdutoRepositorySqlite(livroDAO: LivroDAO) : LivroRepositorySqlite {
        return LivroRepositorySqlite(livroDAO)
    }

    @Provides
    fun provideProdutoDAO(bancoSQLite: BancoSQLite): LivroDAO {
        return bancoSQLite.produtoDao()
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context): BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}