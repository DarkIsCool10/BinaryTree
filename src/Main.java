import javax.sound.midi.Soundbank;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> arbol = new BinaryTree<>();

        arbol.agregarDato(5);
        arbol.agregarDato(3);
        arbol.agregarDato(2);
        arbol.agregarDato(4);
        arbol.agregarDato(7);
        arbol.agregarDato(8);

        /*
        arbol.recorrerInOrden();
        arbol.eliminarElemento(5);
        arbol.recorrerInOrden();
        */

        BinaryTree<Float> arbol1 = new BinaryTree<>();
        arbol1.agregarDato(5.5f);
        arbol1.agregarDato(3.3f);
        arbol1.agregarDato(8.8f);
        arbol1.agregarDato(2.2f);
        arbol1.agregarDato(4.4f);
        arbol1.agregarDato(7.7f);
        arbol1.agregarDato(9.9f);
        arbol1.agregarDato(10.9f);

        arbol1.imprimirAmplitud();
        System.out.println("\n");
        List<List<Float>> niveles = arbol1.obtenerNiveles();
        System.out.println(niveles);
        int hojas = arbol1.contarHojas();
        System.out.println(hojas);
        int altura = arbol1.alturaSinRecursividad();
        System.out.println(altura);
        //float suma = arbol.sumaNivelesPares();
        //System.out.println("Suma de los nodos en niveles pares: " + suma);

        BinaryTree<Persona> personas = new BinaryTree<>();
        personas.agregarDato(new Persona("Camilo"));
        personas.agregarDato(new Persona("Ines"));
        personas.agregarDato(new Persona("Alberto"));
        personas.agregarDato(new Persona("Andres"));
        personas.agregarDato(new Persona("Sara"));

        // Obtener la lista de personas cuyos nombres comienzan con una vocal
        LinkedList<Persona> personasConNombreVocal = personas.personasConNombreVocal();

        // Imprimir la lista de personas con nombre que comienza en vocal
        System.out.println("Personas cuyo nombre comienza con vocal:");
        for (Persona persona : personasConNombreVocal) {
            System.out.println(persona.getNombre());
        }




    }
}