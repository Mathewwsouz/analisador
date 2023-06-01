import java.util.List;

public class AnalisadorSintatico {
    private List<Token> tokens;
    private int posiçãoAtual;

    public AnalisadorSintatico(List<Token> tokens) {
        this.tokens = tokens;
        this.posiçãoAtual = 0;
    }

    public void analisar() {
        if (programa()) {
            System.out.println("Análise sintática concluída com sucesso!");
        } else {
            System.out.println("Erro de sintaxe.");
        }
    }

    private boolean programa() {
        return instrucao();
    }

    private boolean instrucao() {
        if (posiçãoAtual < tokens.size()) {
            Token token = tokens.get(posiçãoAtual);
            if (token.getTipo() == TipoToken.IDENTIFICADOR) {
                posiçãoAtual++;
                if (posiçãoAtual < tokens.size()) {
                    token = tokens.get(posiçãoAtual);
                    if (token.getTipo() == TipoToken.OPERADOR && token.getValor().equals("=")) {
                        posiçãoAtual++;
                        return expressao() && instrucao();
                    }
                }
            }
        }
        return false;
    }

    private boolean expressao() {
        if (posiçãoAtual < tokens.size()) {
            Token token = tokens.get(posiçãoAtual);
            if (token.getTipo() == TipoToken.NUMERO) {
                posiçãoAtual++;
                return true;
            }
        }
        return false;
    }
}
