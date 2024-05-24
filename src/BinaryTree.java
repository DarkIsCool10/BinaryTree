import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        boolean resultado = false;
        Nodo<T> aux = raiz;

        while (aux != null) {
            // Si es la raiz
            if (aux.getDato().compareTo(value) == 0) {
                Nodo<T> nodoAEliminar = aux;
                //Valida si tiene hijos a la derecha
                if (aux.getDerecha() != null) {
                    raiz = aux.getDerecha(); //Si tiene hijo lo vuelve raiz
                    if (nodoAEliminar.getIzquierda() != null) { // Verifica si el nodo a eliminar tiene hijo a la izquierda
                        insertarNodo((Nodo<T>) nodoAEliminar.getIzquierda()); //Si tiene lo inserta a el arbol
                        //Se eliminan las referencias a los hijos del nodo a eliminar
                        nodoAEliminar.setIzquierda(null);
                        nodoAEliminar.setDerecha(null);
                    }
                    //Valida si tiene hijos a la izquierda
                } else if (aux.getIzquierda() != null) {
                    raiz = aux.getIzquierda();//Si tiene hijo lo vuelve raiz
                    if (nodoAEliminar.getDerecha() != null) {//Verifica si el nodo a eliminar tiene hijo a la derecha
                        insertarNodo((Nodo<T>) nodoAEliminar.getDerecha());//Si lo tiene lo inserta a el arbol
                        //Se eliminan las referencias a los hijos del nodo a eliminar
                        nodoAEliminar.setIzquierda(null);
                        nodoAEliminar.setDerecha(null);
                    }
                } else {
                    //Si no tiene hijos actualiza la raiz a null
                    raiz = null;
                }
                //Confirma que la eliminacion se realizo
                resultado = true;
                //Y reinicia la variable auxiliar
                aux = null;
            } else if (aux.getIzquierda() != null && aux.getIzquierda().getDato().compareTo(value) == 0) {
                Nodo<T> nodoAEliminar = aux.getIzquierda();
                // Si el valor está a la izquierda del nodo que estamos recorriendo
                // Miramos si tenemos izquierda en el nodo a eliminar

                if (aux.getIzquierda().getIzquierda() != null) {
                    // Tenemos Nodo a la izquierda
                    // Apuntamos el nodo que estamos recorriendo al siguiente del nodo a eliminar
                    aux.setIzquierda(aux.getIzquierda().getIzquierda());
                    // Reposicionamos sus hijos
                    if (nodoAEliminar.getDerecha() != null) {
                        agregarDato((T) nodoAEliminar.getDerecha());
                    }
                    nodoAEliminar.setDerecha(null);
                    nodoAEliminar.setIzquierda(null);
                    resultado = true;
                    aux = null;
                } else {
                    // No tenemos nodo a la izquierda del elemento a eliminar
                    // Miramos si es nodo hoja

                    if (aux.getIzquierda() == null && aux.getDerecha() == null) {
                        aux.setIzquierda(null);
                    } else {
                        aux.setIzquierda(null);
                        if (nodoAEliminar.getDerecha() != null) {
                            insertarNodo((Nodo<T>) nodoAEliminar.getDerecha());
                        }
                    }

                    resultado = true;
                    aux = null;
                }

            } else if (aux.getDerecha() != null && aux.getDerecha().getDato().compareTo(value) == 0) {
                Nodo<T> nodoAEliminar = aux.getDerecha();
                // Si el valor está a la derecha del nodo que estamos recorriendo
                // Miramos si tenemos derecha en el nodo a eliminar

                if (aux.getDerecha().getDerecha() != null) {
                    // Tenemos Nodo a la izquierda
                    // Apuntamos el nodo que estamos recorriendo al siguiente del nodo a eliminar
                    aux.setDerecha(aux.getDerecha().getDerecha());
                    // Reposicionamos sus hijos
                    if (nodoAEliminar.getIzquierda() != null) {
                        agregarDato((T) nodoAEliminar.getIzquierda());
                    }
                    nodoAEliminar.setDerecha(null);
                    nodoAEliminar.setIzquierda(null);
                    resultado = true;
                    aux = null;
                } else {
                    // No tenemos nodo a la izquierda del elemento a eliminar
                    // Miramos si es nodo hoja

                    if (aux.getIzquierda().getIzquierda() == null && aux.getDerecha().getDerecha() == null) {
                        aux.setDerecha(null);
                    } else {
                        aux.setDerecha(null);
                        if (nodoAEliminar.getIzquierda() != null) {
                            insertarNodo((Nodo<T>) nodoAEliminar.getIzquierda());
                        }

                    }

                    resultado = true;
                    aux = null;
                }
            } else {
                if (value.compareTo(aux.getDato()) == 1) {
                    aux = aux.getDerecha();
                } else {
                    aux = aux.getIzquierda();
                }
            }
        }
        // En caso de borrar el nodo disminuimos la cantidad de nodos en 1
        if (resultado) {
            numeroElementos--;
        }

        return resultado;
    }

    private void insertarNodo(Nodo<T> nodo) {
        if (raiz == null) {
            raiz = nodo;
            System.out.println("Inserto la raiz");
        } else {
            // Necesitamos encontrar en que posición debemos insertar el nodo
            Nodo<T> aux = raiz;

            while (aux != null) {
                // Comprobamos si tenemos que insertarlo ya
                // Comprobamos si nodo hoja
                if (aux.getDerecha() == null && aux.getIzquierda() == null) {
                    if (nodo.getDato().compareTo(aux.getDato()) == 1) {
                        // Derecha
                        System.out.println(nodo.getDato() + " Lo insertamos a la derecha de: " + aux.getDato());
                        aux.setDerecha(nodo);
                        aux = null;
                    } else {
                        // Izquierda
                        System.out.println(nodo.getDato() + " Lo insertamos a la izquierda de: " + aux.getDato());
                        aux.setIzquierda(nodo);
                        aux = null;
                    }
                } else if (nodo.getDato().compareTo(aux.getDato()) == 1 && aux.getDerecha() == null) {
                    // Lo insertamos a la derecha
                    System.out.println(nodo.getDato() + " Lo insertamos a la derecha de: " + aux.getDato());
                    aux.setDerecha(nodo);
                    aux = null;
                } else if (nodo.getDato().compareTo(aux.getDato()) == -1 && aux.getIzquierda() == null) {
                    // Lo insertamos a la izquierda
                    System.out.println(nodo.getDato() + " Lo insertamos a la izquierda de: " + aux.getDato());
                    aux.setIzquierda(nodo);
                    aux = null;
                } else {
                    // Pasamos de nodo
                    if (nodo.getDato().compareTo(aux.getDato()) == 1) {
                        aux = aux.getDerecha();
                    } else {
                        aux = aux.getIzquierda();
                    }
                }
            }
        }
    }

    //============================== METODOS PREPARCIAL ===================================//

    public float sumaNivelesParesRecursivo(Nodo<T> nodo, int nivel) {
        if (nodo == null) {
            return 0;
        }

        float suma = 0;
        if (nivel % 2 == 0) {
            suma += ((Number) nodo.getDato()).floatValue();
        }
        return suma + sumaNivelesParesRecursivo(nodo.getIzquierda(), nivel + 1) +
                sumaNivelesParesRecursivo(nodo.getDerecha(), nivel + 1);
    }

    public float sumaNivelesPares() {
        if (estaVacio()) {
            return 0;
        }

        return sumaNivelesParesRecursivo(raiz, 0);
    }

    public int alturaRecursiva(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaIzquierda = alturaRecursiva(nodo.getIzquierda());
        int alturaDerecha = alturaRecursiva(nodo.getDerecha());

        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }

    public int altura() {
        if (estaVacio()) {
            return 0;
        }

        return alturaRecursiva(raiz);
    }

    public LinkedList<Persona> personasConNombreVocal() {
        LinkedList<Persona> personasVocales = new LinkedList<>();
        personasConNombreVocal(raiz, personasVocales);
        return personasVocales;
    }

    private void personasConNombreVocal(Nodo<T> nodo, LinkedList<Persona> lista) {
        if (nodo == null) {
            return;
        }

        Persona persona = (Persona) nodo.getDato();
        if (persona != null && nombreComienzaConVocal(persona.getNombre())) {
            lista.add(persona);
        }

        personasConNombreVocal(nodo.getIzquierda(), lista);
        personasConNombreVocal(nodo.getDerecha(), lista);
    }

    private boolean nombreComienzaConVocal(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }

        char primeraLetra = Character.toLowerCase(nombre.charAt(0));
        return primeraLetra == 'a' || primeraLetra == 'e' || primeraLetra == 'i' ||
                primeraLetra == 'o' || primeraLetra == 'u';
    }

    public int alturaSinRecursividad() {
        if (raiz == null) {
            return 0; // Árbol vacío
        }

        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.offer(raiz);
        int altura = 0;

        while (!cola.isEmpty()) {
            int nivelSize = cola.size();
            altura++;

            // Agregar todos los nodos del nivel actual a la cola y seguir recorriendo
            for (int i = 0; i < nivelSize; i++) {
                Nodo<T> nodo = cola.poll();
                if (nodo.getIzquierda() != null) {
                    cola.offer(nodo.getIzquierda());
                }
                if (nodo.getDerecha() != null) {
                    cola.offer(nodo.getDerecha());
                }
            }
        }

        return altura;
    }

    public List<List<T>> obtenerNiveles() {
        List<List<T>> niveles = new ArrayList<>();
        if (raiz == null) {
            return niveles; // Árbol vacío, retornar lista vacía
        }

        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.offer(raiz);

        while (!cola.isEmpty()) {
            int nivelSize = cola.size();
            List<T> nivelActual = new ArrayList<>();

            // Agregar todos los nodos del nivel actual a la lista
            for (int i = 0; i < nivelSize; i++) {
                Nodo<T> nodo = cola.poll();
                nivelActual.add(nodo.getDato());

                // Agregar los hijos del nodo a la cola para procesarlos en el siguiente nivel
                if (nodo.getIzquierda() != null) {
                    cola.offer(nodo.getIzquierda());
                }
                if (nodo.getDerecha() != null) {
                    cola.offer(nodo.getDerecha());
                }
            }

            // Agregar el nivel actual a la lista de niveles
            niveles.add(nivelActual);
        }

        return niveles;
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }

        // Si el nodo no tiene hijos, es una hoja
        if (nodo.getIzquierda() == null && nodo.getDerecha() == null) {
            return 1;
        }

        // Llamadas recursivas para contar las hojas en los subárboles izquierdo y derecho
        int hojasIzquierda = contarHojas(nodo.getIzquierda());
        int hojasDerecha = contarHojas(nodo.getDerecha());

        // Sumar las hojas de ambos subárboles
        return hojasIzquierda + hojasDerecha;
    }

    public T encontrarNumeroMenor() {
        if (raiz == null) {
            return null; // Árbol vacío
        }

        Nodo<T> nodoActual = raiz;
        while (nodoActual.getIzquierda() != null) {
            nodoActual = nodoActual.getIzquierda();
        }

        return nodoActual.getDato();
    }

    public void imprimirAmplitud() {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.offer(raiz);

        while (!cola.isEmpty()) {
            Nodo<T> nodo = cola.poll();
            System.out.print(nodo.getDato() + " ");

            if (nodo.getIzquierda() != null) {
                cola.offer(nodo.getIzquierda());
            }
            if (nodo.getDerecha() != null) {
                cola.offer(nodo.getDerecha());
            }
        }
    }

    public Nodo<T> obtenerNodoMayor() {
        if (raiz == null) {
            return null; // Árbol vacío
        }

        Nodo<T> nodoActual = raiz;
        while (nodoActual.getDerecha() != null) {
            nodoActual = nodoActual.getDerecha();
        }

        return nodoActual;
    }

    public Nodo<T> obtenerNodoMenor() {
        if (raiz == null) {
            return null; // Árbol vacío
        }

        Nodo<T> nodoActual = raiz;
        while (nodoActual.getIzquierda() != null) {
            nodoActual = nodoActual.getIzquierda();
        }

        return nodoActual;
    }

    public void borrarArbol() {
        borrarArbol(raiz);
        raiz = null; // Establecer la raíz como nula una vez que todos los nodos han sido eliminados
    }

    private void borrarArbol(Nodo<T> nodo) {
        if (nodo == null) {
            return;
        }

        // Eliminar los nodos de los subárboles izquierdo y derecho recursivamente
        borrarArbol(nodo.getIzquierda());
        borrarArbol(nodo.getDerecha());

        // Eliminar el nodo actual
        nodo.setIzquierda(null);
        nodo.setDerecha(null);
    }

}
