// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome: String, var cursando: String = "nao matriculado") {
    var conteudoAtual: List<ConteudoEducacional> = listOf()

    fun marcarConteudoConcluido(formacao: Formacao, conteudo: ConteudoEducacional) {
        if (formacao.conteudos.contains(conteudo)) {
            conteudoAtual += conteudo
            println("Conteúdo '${conteudo.nome}' marcado como concluído para o usuário $nome.")
        } else {
            println("O conteúdo '${conteudo.nome}' não faz parte da formação '${formacao.nome}'.")
        }
    }

    fun getProgresso(formacao: Formacao): Int {
        val conteudosConcluidos = conteudoAtual.count { it in formacao.conteudos }
        val totalConteudos = formacao.conteudos.size
        val progresso = (conteudosConcluidos.toDouble() / totalConteudos.toDouble()) * 100
        return progresso.toInt()
    }
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }
}

fun main() {
    val conteudo1 = ConteudoEducacional("Primeiros passos Docker", 120)
    val conteudo2 = ConteudoEducacional("Docker Compose", 170)
    val conteudo3 = ConteudoEducacional("Container Aplicacao Web", 150)

    val formacao = Formacao("Formação Docker", listOf(conteudo1, conteudo2, conteudo3))

    val usuario1 = Usuario("Camila")
    val usuario2 = Usuario("Maria")
    val usuario3 = Usuario("Leandro")

    formacao.matricular(usuario1)
    formacao.matricular(usuario2)
    formacao.matricular(usuario3)

    usuario1.marcarConteudoConcluido(formacao, conteudo1)
    usuario1.marcarConteudoConcluido(formacao, conteudo2)
    

    println("Camila: ${usuario1.getProgresso(formacao)}% concluído")
    println("Maria: ${usuario2.getProgresso(formacao)}% concluído")
    println("Leandro: ${usuario3.getProgresso(formacao)}% concluído")
}
