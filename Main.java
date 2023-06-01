import AnalisadorLexico;
import AnalisadorSintatico;
import AnalisadorSemantico;
import Token;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String entrada = "x = 10 + 5;";
        AnalisadorLexico analisadorLexico = new AnalisadorLexico(entrada);
        List<Token> tokens = analisadorLexico.analisar();

        AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(tokens);
        analisadorSintatico.analisar();

        System.out.println("Tabela de tokens:");
        System.out.println("-----------------");
        System.out.println("Tipo\t\tValor");
        System.out.println("-----------------");
        for (Token token : tokens) {
            System.out.println(token.getTipo() + "\t" + token.getValor());
        }
        AnalisadorSemantico analisadorSemantico = new AnalisadorSemantico(tokens);
        analisadorSemantico.analisar();

    }
}
