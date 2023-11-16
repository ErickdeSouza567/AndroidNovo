package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class HorarioRepositoryImpl @Inject constructor(private val produtoDao: ProdutoDAO) : HorarioRepository {

    override val ultimaData: Flow<Horario?> = produtoDao.obterUltimaData()

    override suspend fun inserirData(horario: Horario) {
        produtoDao.inserirData(horario)
    }

    override suspend fun obterUltimaData(): Horario? {
        return produtoDao.obterUltimaData().firstOrNull()
    }
}