💡 O que é o Design Pattern Strategy?

O padrão Strategy permite definir uma família de algoritmos, encapsulá-los em classes separadas e torná-los intercambiáveis em tempo de execução. Ele é usado quando você tem várias formas de fazer algo, e quer deixar a decisão desacoplada da lógica principal.
---------------------------------------------------------------------------------------------
📦 Estrutura geral do projeto:

src/
 ├── app/Main.java                ← Ponto de entrada
 |
 └── strategy/
      ├── MediaStrategy.java      ← Interface da estratégia
      ├── Disciplina.java         ← Modelo que usa a estratégia
      ├── Aritmetica.java         ← Implementação 1
      └── Geometrica.java         ← Implementação 2
---------------------------------------------------------------------------------------------
🔍 Análise linha por linha:

🧠 MediaStrategy.java – Interface da Estratégia

package strategy;

public interface MediaStrategy {
    double calcularMedia(double p1, double p2);
    String verificarSituacao(double media);
}

-Declara os métodos que todas as estratégias devem implementar.
-calcularMedia: calcula a média.
-verificarSituacao: retorna "Aprovado" ou "Reprovado".
---------------------------------------------------------------------------------------------
➕ Aritmetica.java – Estratégia Aritmética

package strategy;

public class Aritmetica implements MediaStrategy {
    @Override
    public double calcularMedia(double p1, double p2) {
        return (p1 + p2) / 2.0;
    }
    @Override
    public String verificarSituacao(double media) {
        return media >= 5.0 ? "Aprovado" : "Reprovado";
    }
}

-Implementa a média simples: (P1 + P2) / 2.
-Aprovado se média ≥ 5.0.
---------------------------------------------------------------------------------------------
✖️ Geometrica.java – Estratégia Geométrica

package strategy;

public class Geometrica implements MediaStrategy {
    @Override
    public double calcularMedia(double p1, double p2) {
        return Math.sqrt(p1 * p2);
    }
    @Override
    public String verificarSituacao(double media) {
        return media >= 7.0 ? "Aprovado" : "Reprovado";
    }
}
Usa a fórmula da média geométrica: √(P1 * P2).

-Aprovado se média ≥ 7.0.
---------------------------------------------------------------------------------------------
🎓 Disciplina.java – Classe que usa a estratégia

package strategy;
import strategy.MediaStrategy;

public class Disciplina {
    private String nome;
    private double p1;
    private double p2;
    private double media;
    private String situacao;
    private final MediaStrategy estrategia;
    public Disciplina(MediaStrategy estrategia) {
        this.estrategia = estrategia;
    }
    public void calcularMedia() {
        this.media = estrategia.calcularMedia(p1, p2);
        this.situacao = estrategia.verificarSituacao(media);
    }
    // getters e setters
}

-A classe Disciplina não sabe qual algoritmo está usando — apenas usa a interface.
-estrategia é passada no construtor.
-calcularMedia() chama os métodos da estratégia atual.
---------------------------------------------------------------------------------------------
🧪 Main.java – Teste com entrada do usuário

Scanner scanner = new Scanner(System.in);
-Prepara para ler os dados digitados.

System.out.println("1 - Média Aritmética");
System.out.println("2 - Média Geométrica");
-Mostra as opções.

int escolha = scanner.nextInt();
-Lê a escolha da estratégia.

MediaStrategy estrategia;
if (escolha == 1) {
    estrategia = new Aritmetica();
} else if (escolha == 2) {
    estrategia = new Geometrica();
} else {
    System.out.println("Opção inválida...");
    estrategia = new Aritmetica();
}
- Aqui se escolhe dinamicamente a estratégia a ser usada.

Isso substitui o uso de if dentro de Disciplina, mantendo baixo acoplamento.

Disciplina d = new Disciplina(estrategia);
-Passa a estratégia como parâmetro.

System.out.print("Nota P1: ");
d.setP1(scanner.nextDouble());
-Define as notas P1 e P2.

d.calcularMedia();
-Chama a estratégia para fazer os cálculos.

System.out.printf(...);
-Exibe tudo formatado.
---------------------------------------------------------------------------------------------
📌 Como o Strategy foi aplicado:

-Elemento:	Papel no Strategy
-MediaStrategy: Interface da estratégia
-Aritmetica / Geometrica: Implementações concretas
-Disciplina:	Contexto que usa a estratégia
-Main: Escolhe e aplica a estratégia dinamicamente
