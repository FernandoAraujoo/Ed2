public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVLTree() {
        this.root = null;
    }
    public void inserir(T value) {
        root = inserirRec(root, value);
    }

    private AVLNode<T> inserirRec(AVLNode<T> node, T value) {
        if (node == null) {
            return new AVLNode<>(value);
        }
        if (value.compareTo(node.getInfo()) < 0) {
            node.setLeft(inserirRec(node.getLeft(), value));
        } else if (value.compareTo(node.getInfo()) > 0) {
            node.setRight(inserirRec(node.getRight(), value));
        } else {
            return node;
        }
        node.setFatBal(calcularBalanceamento(node));
        return balancear(node);
    }
    public void remover(T value) {
        root = removerRec(root, value);
    }

    private AVLNode<T> removerRec(AVLNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.getInfo()) < 0) {
            node.setLeft(removerRec(node.getLeft(), value));
        } else if (value.compareTo(node.getInfo()) > 0) {
            node.setRight(removerRec(node.getRight(), value));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                AVLNode<T> child = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if (child == null) {
                    node = null;
                } else {
                    node = child;
                }
            } else {
                AVLNode<T> minValueNode = encontrarMenorValor(node.getRight());
                node.setInfo(minValueNode.getInfo());
                node.setRight(removerRec(node.getRight(), minValueNode.getInfo()));
            }
        }
        if (node == null) {
            return null;
        }

        node.setFatBal(calcularBalanceamento(node));

        return balancear(node);
    }

    private AVLNode<T> encontrarMenorValor(AVLNode<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void passeio() {
        passeioRec(root);
    }

    private void passeioRec(AVLNode<T> node) {
        if (node != null) {
            passeioRec(node.getLeft());
            System.out.print(node.getInfo() + " ");
            passeioRec(node.getRight());
        }
    }
    private int calcularBalanceamento(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return altura(node.getLeft()) - altura(node.getRight());
    }
    private int altura(AVLNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(altura(node.getLeft()), altura(node.getRight()));
    }
    private AVLNode<T> balancear(AVLNode<T> node) {
        int balanceamento = calcularBalanceamento(node);

        if (balanceamento > 1) { 
            if (calcularBalanceamento(node.getLeft()) >= 0) { 
                return rotacaoDireita(node); 
            } else { 
                node.setLeft(rotacaoEsquerda(node.getLeft()));
                return rotacaoDireita(node);
            }
        }

        if (balanceamento < -1) { 
            if (calcularBalanceamento(node.getRight()) <= 0) { 
                return rotacaoEsquerda(node); 
            } else { 
                node.setRight(rotacaoDireita(node.getRight()));
                return rotacaoEsquerda(node);
            }
        }

        return node;
    }
    private AVLNode<T> rotacaoDireita(AVLNode<T> y) {
        AVLNode<T> x = y.getLeft();
        AVLNode<T> temp = x.getRight();

        x.setRight(y);
        y.setLeft(temp);

        y.setFatBal(calcularBalanceamento(y));
        x.setFatBal(calcularBalanceamento(x));

        return x;
    }
    private AVLNode<T> rotacaoEsquerda(AVLNode<T> x) {
        AVLNode<T> y = x.getRight();
        AVLNode<T> temp = y.getLeft();

        y.setLeft(x);
        x.setRight(temp);

        x.setFatBal(calcularBalanceamento(x));
        y.setFatBal(calcularBalanceamento(y));

        return y;
    }
}
