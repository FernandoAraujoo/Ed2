public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.inserir(10);
        tree.inserir(20);
        tree.inserir(30);
        tree.inserir(40);
        tree.inserir(50);

        System.out.println("Árvore AVL após inserção:");
        tree.passeio();

        tree.remover(20);
        System.out.println("\nÁrvore AVL após remoção do 20:");
        tree.passeio(); 

       
        tree.remover(60);
        System.out.println("\nÁrvore AVL após tentativa de remoção do 60:");
        tree.passeio(); 
    }
}
