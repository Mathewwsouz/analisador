import java.util.List;

public class AnalisadorSemantico {
    private List<Token> tokens;
    private int posiçãoAtual;

    public AnalisadorSemantico(List<Token> tokens) {
        this.tokens = tokens;
        this.posiçãoAtual = 0;
    }

    public void analisar() {
        TabelaSemantica tabela = new TabelaSemantica();

        if (verificarSemantica(tabela)) {
            System.out.println("Análise semântica concluída com sucesso!");
        } else {
            System.out.println("Erro semântico.");
        }

        tabela.exibirTabela();
    }

    private boolean verificarSemantica(TabelaSemantica tabela) {
        if (posiçãoAtual < tokens.size()) {
            Token token = tokens.get(posiçãoAtual);
            if (token.getTipo() == TipoToken.IDENTIFICADOR) {
                // Verificar se o identificador foi declarado previamente
                if (!verificarDeclaracao(token, tabela)) {
                    return false;
                }
                posiçãoAtual++;
                if (posiçãoAtual < tokens.size()) {
                    token = tokens.get(posiçãoAtual);
                    if (token.getTipo() == TipoToken.OPERADOR && token.getValor().equals("=")) {
                        posiçãoAtual++;
                        if (posiçãoAtual < tokens.size()) {
                            token = tokens.get(posiçãoAtual);
                            if (token.getTipo() == TipoToken.NUMERO) {
                                // Fazer as verificações semânticas necessárias
                                tabela.adicionarEntrada(token.getValor(), "INTEGER");
                                posiçãoAtual++;
                                return true;
                            } else {
                                // Erro: atribuição inválida
                                return false;
                            }
                        } else {
                            // Erro: atribuição inválida
                            return false;
                        }
                    } else {
                        // Erro: operador de atribuição inválido
                        return false;
                    }
                } else {
                    // Erro: atribuição inválida
                    return false;
                }
            } else {
                // Erro: identificador inválido
                return false;
            }
        }
        return false;
    }

    private boolean verificarDeclaracao(Token token, TabelaSemantica tabela) {
        // Verificar se o identificador foi declarado previamente
        String identificador = token.getValor();
        // Lógica para verificar se o identificador foi declarado
        // ...
        tabela.adicionarEntrada(identificador, ""); // Adicionar entrada na tabela (tipo vazio por enquanto)
        return true; // Supondo que o identificador foi declarado corretamente
    }
}
