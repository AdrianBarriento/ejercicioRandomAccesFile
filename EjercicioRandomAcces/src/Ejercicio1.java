import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio1 {
    //pedir el nombre de un fihcero, comprobar si existe y si esta vacio, pedir una palabra,
    // buscarla y modificarla poniendola en mayuscula en todas us apariciones, al hacer camabios sobreescribimos toda la linea

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String rutaFichero = validarFichero();
        File fichero = new File(rutaFichero);
        StringBuffer buffer =null;

        ArrayList<Character> arrayLetras = new ArrayList<>();
        char letraAux;
        String palabraBuscada, palabra, frase;
        long posicion=0;
        String texto="";
        try {
            if((fichero.exists())&&(fichero.length()>0)){
                RandomAccessFile file = new RandomAccessFile(fichero, "rw");
                System.out.println("Que palabra desea buscar: ");
                palabraBuscada = teclado.nextLine();
                palabra = palabraBuscada.toUpperCase();

                file.seek(0);
                while(file.getFilePointer()!=file.length()){
                    letraAux = file.readChar();
                    arrayLetras.add(letraAux);
                }

                for (char letra:arrayLetras) {
                    texto+=letra;
                }
                if(texto.contains(palabraBuscada)){
                    texto=texto.replaceAll(palabraBuscada, palabra);
                }
                buffer = new StringBuffer(texto);
                file.seek(0);
                file.writeChars(buffer.toString());
                file.seek(0);
                while(file.getFilePointer()!=file.length()){
                    System.out.println(file.readLine());
                }
                file.close();

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String validarFichero(){
        Scanner teclado = new Scanner(System.in);
        String ficheroPedido;
        System.out.println("INTRODUZCA EL NOMBRE DEL FICHERO: ");
        ficheroPedido = teclado.nextLine();
        return  ficheroPedido;
    }

}
