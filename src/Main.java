public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> arbol = new BinaryTree<>();

        arbol.agregarDato(41);
        arbol.agregarDato(47);
        arbol.agregarDato(3);
        arbol.agregarDato(7);
        arbol.agregarDato(6);
        arbol.agregarDato(8);

        arbol.imprimirArbol();
        arbol.eliminarElemento(41);
        arbol.imprimirArbol();


    }
}