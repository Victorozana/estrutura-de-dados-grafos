public class ArvoreAVL {
    private No raiz;

    private int altura(No no){
        if (no == null) return 0;
        return no.getAltura();
    }

    private int obterFatorBalanceamento(No no){
        if(no == null) return 0;
        return altura(no.getEsquerdo()) - altura(no.getDireito());
    }

    public void inserir(Produto produto){
        this.raiz = inserirRecursivo(this.raiz, produto);
    }

    private No inserirRecursivo(No noAtual, Produto produto){
        if (noAtual == null){
            return new No(produto);
        }

        if (produto.getCodigo() < noAtual.getProduto().getCodigo()){
            noAtual.setEsquerdo(inserirRecursivo(noAtual.getEsquerdo(), produto));
        } else if (produto.getCodigo() > noAtual.getProduto().getCodigo()) {
            noAtual.setDireito(inserirRecursivo(noAtual.getDireito(), produto));
        } else {
            return noAtual;
        }

        noAtual.setAltura(1 + Math.max(altura(noAtual.getEsquerdo()), altura(noAtual.getDireito())));

        int balanceamento = obterFatorBalanceamento(noAtual);

        // 1. Rotação Simples à Direita (LL)
        if (balanceamento > 1 && produto.getCodigo() < noAtual.getEsquerdo().getProduto().getCodigo()){
            return rotacaoDireita(noAtual);
        }

        // 2. Rotação Simples à Esquerda (RR)
        if (balanceamento < -1 && produto.getCodigo() > noAtual.getDireito().getProduto().getCodigo()){
            return rotacaoEsquerda(noAtual);
        }

        // 3. Rotação Dupla Esquerda-Direita (LR)
        if (balanceamento > 1 && produto.getCodigo() > noAtual.getEsquerdo().getProduto().getCodigo()){
            noAtual.setEsquerdo(rotacaoEsquerda(noAtual.getEsquerdo()));
            return rotacaoDireita(noAtual);
        }

        // 4. Rotação Dupla Direita-Esquerda (RL)
        if (balanceamento < -1 && produto.getCodigo() < noAtual.getDireito().getProduto().getCodigo()){
            noAtual.setDireito(rotacaoDireita(noAtual.getDireito()));
            return rotacaoEsquerda(noAtual);
        }

        return noAtual;
    }

    private No rotacaoDireita(No y){
        No x = y.getEsquerdo();
        No T2 = x.getDireito();

        x.setDireito(y);
        y.setEsquerdo(T2);

        y.setAltura(Math.max(altura(y.getEsquerdo()), altura(y.getDireito())) + 1);
        x.setAltura(Math.max(altura(x.getEsquerdo()), altura(x.getDireito())) + 1);

        return x;
    }

    private No rotacaoEsquerda(No x){
        No y = x.getDireito();
        No T2 = y.getEsquerdo();

        y.setEsquerdo(x);
        x.setDireito(T2);

        x.setAltura(Math.max(altura(x.getEsquerdo()), altura(x.getDireito())) + 1);
        y.setAltura(Math.max(altura(y.getEsquerdo()), altura(y.getDireito())) + 1);

        return y;
    }

    public void imprimirArvore() {
        System.out.println("\n--- Estrutura Visual da Árvore AVL ---");
        if (this.raiz == null) {
            System.out.println("A árvore está vazia.");
        } else {
            imprimirRecursivo(this.raiz, 0);
        }
        System.out.println("--------------------------------------\n");
    }

    private void imprimirRecursivo(No no, int nivel) {
        // Condição de parada da recursão
        if (no == null) {
            return;
        }

        // 1. Percorre toda a subárvore DIREITA primeiro (ficará no topo da impressão)
        imprimirRecursivo(no.getDireito(), nivel + 1);

        // 2. Imprime o nó atual com a indentação correta
        for (int i = 0; i < nivel; i++) {
            System.out.print("\t"); // Cria o recuo visual baseado na profundidade
        }

        // Captura o FB para você ver se a matemática está funcionando
        int fb = obterFatorBalanceamento(no);

        // Imprime o Código do Produto e o Fator de Balanceamento
        System.out.println(no.getProduto().getCodigo() + " (FB:" + fb + ")");

        // 3. Percorre toda a subárvore ESQUERDA por último (ficará na base da impressão)
        imprimirRecursivo(no.getEsquerdo(), nivel + 1);
    }

}
