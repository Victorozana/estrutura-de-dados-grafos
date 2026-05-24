//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArvoreAVL avl = new ArvoreAVL();

        avl.inserir(new Produto(1L, "Sabonete", "Higiene", 5, 21.9));
        avl.inserir(new Produto(2L, "Xampô", "Higiene", 5, 21.9));
        avl.inserir(new Produto(3L, "Condicionador", "Higiene", 5, 21.9));
        avl.inserir(new Produto(6L, "Condicionador", "Higiene", 5, 21.9));

        avl.imprimirArvore();
    }
}