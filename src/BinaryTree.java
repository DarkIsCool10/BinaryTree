public class BinaryTree<T extends Comparable<T>> {
    private Nodo<T> raiz;
    private int numeroElementos;

    public BinaryTree() {
        this.raiz = null;
        this.numeroElementos = 0;
    }

    // Método estaVacio
    public boolean estaVacio() {
        return raiz == null;
    }

    public void agregarDato(T elemento) {
        Nodo<T> nuevo = new Nodo(elemento);
        if (this.raiz == null) {
            this.raiz = nuevo;
        } else {
            Nodo<T> aux = this.raiz;
            Nodo<T> ant = null;
            while (aux != null) {
                ant = aux;
                if (elemento.compareTo(aux.getDato()) > 0) {
                    aux = aux.getDerecha();
                } else {
                    aux = aux.getIzquierda();
                }
            }
            if (elemento.compareTo(ant.getDato()) > 0) {
                ant.setDerecha(nuevo);
            } else {
                ant.setIzquierda(nuevo);
            }
        }
    }

    public void recorrerInOrden() {
        recorrerInOrden(raiz);
    }

    private void recorrerInOrden(Nodo<T> raiz) {
        if (raiz != null) {
            recorrerInOrden(raiz.getIzquierda());
            System.out.println(raiz.getDato());
            recorrerInOrden(raiz.getDerecha());
        }
    }

    public void recorrerPostOrden() {
        recorrerPostOrden(raiz);
    }

    private void recorrerPostOrden(Nodo<T> raiz) {
        if (raiz != null) {
            recorrerPostOrden(raiz.getIzquierda());
            recorrerPostOrden(raiz.getDerecha());
            System.out.print(raiz.getDato());
        }
    }

    public void recorrerPreOrden() {
        recorrerPreOrden(raiz);
    }

    private void recorrerPreOrden(Nodo<T> raiz) {
        if (raiz != null) {
            System.out.println(raiz.getDato());
            recorrerPreOrden(raiz.getIzquierda());
            recorrerPreOrden(raiz.getDerecha());
        }
    }

    public void imprimirArbol() {
        imprimirArbolRec(raiz, 0);
    }

    private void imprimirArbolRec(Nodo<T> nodo, int nivel) {
        if (nodo == null)
            return;

        imprimirArbolRec(nodo.getDerecha(), nivel + 1);
        for (int i = 0; i < nivel; i++)
            System.out.print("   ");
        System.out.println(nodo.getDato());
        imprimirArbolRec(nodo.getIzquierda(), nivel + 1);
    }

    public boolean eliminarElemento(T value) {
        boolean resultado = false; // Variable para almacenar el resultado de la eliminación
        Nodo<T> aux = raiz; // Nodo auxiliar para recorrer el árbol

        while (aux != null) { // Bucle para recorrer el árbol
            // Si el nodo actual contiene el valor a eliminar
            if (aux.getDato().compareTo(value) == 0) {
                Nodo<T> nodoAEliminar = aux; // Nodo a eliminar

                // Si el nodo a eliminar es la raíz
                if (aux.getDerecha() != null) { // Si tiene hijo derecho
                    raiz = aux.getDerecha(); // La raíz se mueve al hijo derecho
                    // Insertar el subárbol izquierdo del nodo a eliminar en el nuevo árbol
                    if (nodoAEliminar.getIzquierda() != null) {
                        insertarNodo((Nodo<T>) nodoAEliminar.getIzquierda());
                        nodoAEliminar.setIzquierda(null);
                        nodoAEliminar.setDerecha(null);
                    }
                } else if (aux.getIzquierda() != null) { // Si tiene hijo izquierdo
                    raiz = aux.getIzquierda(); // La raíz se mueve al hijo izquierdo
                    // Insertar el subárbol derecho del nodo a eliminar en el nuevo árbol
                    if (nodoAEliminar.getDerecha() != null) {
                        insertarNodo((Nodo<T>) nodoAEliminar.getDerecha());
                        nodoAEliminar.setIzquierda(null);
                        nodoAEliminar.setDerecha(null);
                    }
                } else { // Si el nodo a eliminar es una hoja
                    raiz = null; // El árbol queda vacío
                }

                resultado = true; // La eliminación fue exitosa
                aux = null; // Salir del bucle
            } else if (aux.getIzquierda() != null && aux.getIzquierda().getDato().compareTo(value) == 0) {
                // Si el nodo a eliminar está a la izquierda del nodo actual
                // Código similar al caso anterior para la eliminación
            } else if (aux.getDerecha() != null && aux.getDerecha().getDato().compareTo(value) == 0) {
                // Si el nodo a eliminar está a la derecha del nodo actual
                // Código similar al caso anterior para la eliminación
            } else {
                // Si el valor a eliminar no se encuentra en el nodo actual,
                // avanzar hacia el siguiente nodo según el valor comparado
                if (value.compareTo(aux.getDato()) == 1) {
                    aux = aux.getDerecha(); // Avanzar a la derecha
                } else {
                    aux = aux.getIzquierda(); // Avanzar a la izquierda
                }
            }
        }

        if (resultado) {
            numeroElementos--; // Disminuir la cantidad de nodos si se eliminó uno
        }

        return resultado; // Devolver el resultado de la eliminación
    }

    private void insertarNodo(Nodo<T> nodo) {
        if (raiz == null) { // Si el árbol está vacío
            raiz = nodo; // El nuevo nodo se convierte en la raíz
        } else { // Si el árbol no está vacío
            Nodo<T> aux = raiz; // Nodo auxiliar para recorrer el árbol

            while (aux != null) { // Bucle para encontrar la posición adecuada para el nuevo nodo
                if (aux.getDerecha() == null && aux.getIzquierda() == null) { // Si el nodo actual es una hoja
                    // Insertar el nuevo nodo como hijo izquierdo o derecho según el valor comparado
                } else if (nodo.getDato().compareTo(aux.getDato()) == 1 && aux.getDerecha() == null) {
                    // Insertar el nuevo nodo a la derecha del nodo actual
                } else if (nodo.getDato().compareTo(aux.getDato()) == -1 && aux.getIzquierda() == null) {
                    // Insertar el nuevo nodo a la izquierda del nodo actual
                } else {
                    // Avanzar hacia el siguiente nodo según el valor comparado
                }
            }
        }
    }


}
