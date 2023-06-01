import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {
    private String entrada;
    private List<Token> tokens;
    private int posicaoAtual;

    public AnalisadorLexico(String entrada) {
        this.entrada = entrada;
        this.tokens = new ArrayList<>();
        this.posicaoAtual = 0;
    }

    public List<Token> analisar() {
        while (posicaoAtual < entrada.length()) {
            char caractereAtual = entrada.charAt(posicaoAtual);

            if (isLetra(caractereAtual)) {
                processarIdentificador();
            } else if (isDigito(caractereAtual)) {
                processarNumero();
            } else if (isOperador(caractereAtual)) {
                processarOperador();
            } else if (isEspacoEmBranco(caractereAtual)) {
                posicaoAtual++;
            } else {
                // Erro: caractere inválido
                posicaoAtual++;
            }
        }

        return tokens;
    }

    private void processarIdentificador() {
        StringBuilder identificador = new StringBuilder();
        identificador.append(entrada.charAt(posicaoAtual));

        posicaoAtual++;
        while (posicaoAtual < entrada.length()) {
            char caractereAtual = entrada.charAt(posicaoAtual);
            if (isLetraOuDigito(caractereAtual)) {
                identificador.append(caractereAtual);
                posicaoAtual++;
            } else {
                break;
            }
        }

        tokens.add(new Token(TipoToken.IDENTIFICADOR, identificador.toString()));
    }

    private void processarNumero() {
        StringBuilder numero = new StringBuilder();
        numero.append(entrada.charAt(posicaoAtual));

        posicaoAtual++;
        while (posicaoAtual < entrada.length()) {
            char caractereAtual = entrada.charAt(posicaoAtual);
            if (isDigito(caractereAtual)) {
                numero.append(caractereAtual);
                posicaoAtual++;
            } else {
                break;
            }
        }

        tokens.add(new Token(TipoToken.NUMERO, numero.toString()));
    }

    private void processarOperador() {
        char caractereAtual = entrada.charAt(posicaoAtual);
        tokens.add(new Token(TipoToken.OPERADOR, String.valueOf(caractereAtual)));
        posicaoAtual++;
    }

    private boolean isLetra(char c) {
        return Character.isLetter(c);
    }

    private boolean isDigito(char c) {
        return Character.isDigit(c);
    }

    private boolean isLetraOuDigito(char c) {
        return Character.isLetterOrDigit(c);
    }

    private boolean isOperador(char c) {
        return "+-*/".contains(String.valueOf(c));
    }

    private boolean isEspacoEmBranco(char c) {
        return Character.isWhitespace(c);
    }

    public static void main(String[] args) {
        String entrada = "int x = 10 + 5;";

        AnalisadorLexico analisadorLexico = new AnalisadorLexico(entrada);
        List<Token> tokens = analisadorLexico.analisar();

        System.out.println("Tabela de tokens:");
        System.out.println("-----------------");
        System.out.println("Tipo\t\tValor");
        System.out.println("-----------------");
        for (Token token : tokens) {
            System.out.println(token.getTipo() + "\t" + token.getValor());
        }
    }
}
