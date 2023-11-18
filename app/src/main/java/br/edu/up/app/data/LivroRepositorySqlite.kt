package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LivroRepositorySqlite
    @Inject constructor(val livroDAO: LivroDAO) : LivroRepository {

    override val produtos: Flow<List<Livro>> get() = livroDAO.listar()
    override suspend fun salvar(livro: Livro) {
        if (livro.id == 0){
            livroDAO.inserir(livro)
        } else {
            livroDAO.atualizar(livro)
        }
    }
    override suspend fun excluir(livro: Livro){
        livroDAO.excluir(livro)
    }

    override suspend fun excluirTodos(){
        livroDAO.excluirTodos()
    }

//    init {
//        CoroutineScope(Job()).launch {
//
//            livroDAO.excluirTodos()
//            delay(15000)
//            val produtos = produtos()
//            for(p in produtos){
//                p.id = 0
//                livroDAO.inserir(p)
//            }
//        }
//    }

//    companion object {
//        fun produtos(): MutableList<Livro> {
//            val lista = mutableListOf(
//                Livro(
//                    1,
//                    "Acompanhamentos",
//                    "Manteiga, nata, mel, requeijão salgado, queijo branco.",
//                    27.56,
//                    160,
//                    "cafe1.jpg",
//                    1
//                ),
//                Livro(
//                    2,
//                    "Bruschetta de salmão e abacate",
//                    "Pão preto, salmão defumado, queijo Philadelphia, abacate, tomate, ovos.",
//                    52.49,
//                    180,
//                    "cafe2.jpg",
//                    1
//                ),
//                Livro(
//                    3,
//                    "Croque Madame",
//                    "Pão torrado, salame de vitela, ovos, manteiga, queijo, rúcula, cenoura, pepino, rabanete.",
//                    35.1,
//                    125,
//                    "cafe3.jpg",
//                    1
//                ),
//                Livro(
//                    4,
//                    "Panquecas de queijo",
//                    "Folhada, recheio de queijo, rúcula, cenoura, pepino, rabanete.",
//                    32.34,
//                    110,
//                    "cafe4.jpg",
//                    1
//                ),
//                Livro(
//                    5,
//                    "Sanduíche de queijo",
//                    "Pão torrado, queijo cheddar, queijo gouda, manteiga, rúcula, cenoura, pepino, rabanete.",
//                    22.34,
//                    110,
//                    "cafe5.jpg",
//                    1
//                ),
//                Livro(
//                    6,
//                    "Sanduíche de torrada escura",
//                    "Pão escuro, presunto, ovos, queijo gouda, rúcula, cenoura, pepino, rabanete.",
//                    28.14,
//                    170,
//                    "cafe6.jpg",
//                    1
//                ),
//                Livro(
//                    7,
//                    "Omelete misto",
//                    "Salada de ovos, filé de frango, queijo gouda, rúcula e tomate.",
//                    25.1,
//                    170,
//                    "cafe7.jpg",
//                    1
//                ),
//                Livro(
//                    8,
//                    "Prato de queijo",
//                    "Queijo de leite de ovelha, queijo motal, requeijão, queijo branco.",
//                    20.3,
//                    170,
//                    "aperitivo1.jpg",
//                    2
//                ),
//                Livro(
//                    9,
//                    "Frios",
//                    "Linguicinha, rolinho de frango, basturma de vitela.",
//                    25.82,
//                    120,
//                    "aperitivo2.jpg",
//                    2
//                ),
//                Livro(
//                    10,
//                    "Berinjelas ao estilo oriental",
//                    "Berinjelas, nozes, curry, creme de leite, alho.",
//                    19.44,
//                    145,
//                    "aperitivo3.jpg",
//                    2
//                ),
//                Livro(
//                    11,
//                    "Lanche especial de berinjelas",
//                    "Berinjela marinada, molho especial, verduras.",
//                    21.9,
//                    120,
//                    "aperitivo4.jpg",
//                    2
//                ),
//                Livro(
//                    12,
//                    "Tomates marinados em vinagre",
//                    "Tomate, vinagre de uva, salsa, sementes de abóbora, azeite, alho.",
//                    12.62,
//                    120,
//                    "aperitivo5.jpg",
//                    2
//                ),
//                Livro(
//                    13,
//                    "Petisco de batata",
//                    "Batata, queijo branco, queijo motal, tomate seco, queijo gouda, azeite, alho.",
//                    17.84,
//                    120,
//                    "aperitivo6.jpg",
//                    2
//                ),
//                Livro(
//                    14,
//                    "Salada de berinjela",
//                    "Berinjela, tomate, pimentão, pimentão, óleo de milho, alho, verduras frescas.",
//                    23.5,
//                    120,
//                    "salada1.jpg",
//                    3
//                ),
//                Livro(
//                    15,
//                    "Salada Russa",
//                    "Carne, batata, cenoura, ovos, pepino fresco, pepino em conserva, maionese, ervilha.",
//                    25.1,
//                    120,
//                    "salada2.jpg",
//                    3
//                ),
//                Livro(
//                    16,
//                    "Salada crocante de berinjela",
//                    "Berinjelas, tomates, molho de ostra, sementes de gergelim branco.",
//                    27.56,
//                    130,
//                    "salada3.jpg",
//                    3
//                ),
//                Livro(
//                    17,
//                    "Sopa de frango com arroz",
//                    "Frango, arroz redondo, cebola, cenoura, ameixa cereja, cúrcuma.",
//                    27.56,
//                    130,
//                    "sopa1.jpg",
//                    4
//                ),
//                Livro(
//                    18,
//                    "Sopa de macarrão com almôndegas",
//                    "Macarrão caseiro, vitela, caldo de carne, cebola, cenoura, açafrão, feijão mungo.",
//                    26.69,
//                    180,
//                    "sopa2.jpg",
//                    4
//                ),
//                Livro(
//                    19,
//                    "Sopa de cebola",
//                    "Cebola, farinha de trigo, batata, ovos, coentro, manteiga.",
//                    23.5,
//                    150,
//                    "sopa3.jpg",
//                    4
//                ),
//                Livro(
//                    20,
//                    "Filé mignon com batata",
//                    "Filé mignon, manteiga, pimenta do reino, tomate, batata.",
//                    57.29,
//                    220,
//                    "carne1.jpg",
//                    5
//                ),
//                Livro(
//                    21,
//                    "Dolma do Azerbaijão",
//                    "Vitela, borrego, beringela, tomate, pimentão.",
//                    51.49,
//                    230,
//                    "carne2.jpg",
//                    5
//                ),
//                Livro(
//                    22,
//                    "Fritas com Carne",
//                    "Massa, vitela, rabo gordo, verduras frescas.",
//                    39.89,
//                    170,
//                    "carne3.jpg",
//                    5
//                ),
//                Livro(
//                    23,
//                    "Vitela com cogumelos em molho picante",
//                    "Vitela, cogumelos, manteiga, molho de pimenta doce, cebola, coentro.",
//                    35.69,
//                    180,
//                    "carne4.jpg",
//                    5
//                ),
//                Livro(
//                    24,
//                    "Tabaka de frango",
//                    "Frango para duas pessoas",
//                    28.29,
//                    150,
//                    "frango1.jpg",
//                    6
//                ),
//                Livro(
//                    25,
//                    "Filé de frango com batata",
//                    "Filé de frango, batata, tomate",
//                    22.49,
//                    140,
//                    "frango2.jpg",
//                    6
//                ),
//                Livro(
//                    26,
//                    "Frango árabe",
//                    "Frango, cebola, tomate, pimentão, berinjela, batata.",
//                    27.29,
//                    230,
//                    "frango3.jpg",
//                    6
//                ),
//                Livro(
//                    27,
//                    "Frutas da estação",
//                    "Maçã, pêssego, morango, melão e uvas.",
//                    15.1,
//                    250,
//                    "sobremesa1.jpg",
//                    7
//                ),
//                Livro(
//                    28,
//                    "Geléia",
//                    "Geléia de amoras, morango e pêssego.",
//                    21.33,
//                    150,
//                    "sobremesa2.jpg",
//                    7
//                ),
//                Livro(
//                    29,
//                    "Variedade de frutas secas e nozes",
//                    "Castanhas, damasco, uvas secas e nozes",
//                    19.3,
//                    150,
//                    "sobremesa3.jpg",
//                    7
//                ),
//                Livro(
//                    30,
//                    "Sorvete",
//                    "Sorvete de morango.",
//                    14.09,
//                    150,
//                    "sobremesa4.jpg",
//                    7
//                ),
//                Livro(
//                    31,
//                    "Vinho tinto",
//                    "Vinho tinto seco, francês.",
//                    47.89,
//                    250,
//                    "bebida1.jpg",
//                    8
//                ),
//                Livro(
//                    32,
//                    "Cerveja Pilsen",
//                    "Caneco de cerveja Pilsen de 500ml.",
//                    11.9,
//                    170,
//                    "bebida2.jpg",
//                    8
//                ),
//                Livro(
//                    33,
//                    "Xícara de chá",
//                    "Xícara de chá de ervas egípcias.",
//                    5.34,
//                    150,
//                    "bebida3.jpg",
//                    8
//                ),
//                Livro(
//                    34,
//                    "Capuccino",
//                    "Xícara pequena de café.",
//                    8.3,
//                    150,
//                    "bebida4.jpg",
//                    8
//                ),
//                Livro(
//                    35,
//                    "Coquetel de maracujá",
//                    "Coquetel de vodka com maracujá.",
//                    16.99,
//                    150,
//                    "bebida5.jpg",
//                    8
//                )
//            )
//            return lista
//        }
//    }
}