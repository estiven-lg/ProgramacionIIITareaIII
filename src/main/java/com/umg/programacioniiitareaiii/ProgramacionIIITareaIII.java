package com.umg.programacioniiitareaiii;

import java.util.Arrays;
import java.util.Scanner;

import classes.TreeB;

public class ProgramacionIIITareaIII {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("ingresa los grados del arbol");
        int grades = input.nextInt();

        TreeB tree = new TreeB(grades);

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("1. encontrar dato");
            System.out.println("2. ingresar dato");
            System.out.println("3. eliminar dato");
            System.out.println("4. mostrar arbol");
            System.out.println("0. salir");
            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el d7ato a buscar:");
                    int key = input.nextInt();
                    TreeB found = tree.find(key);
                    if (found == null) {
                        System.out.println("Dato no encontrado :c");
                    } else {
                        System.out.println("Dato encontrado en el nodo :");
                        System.out.println(Arrays.toString(found.values));
                    }

                    break;
                case 2:
                    System.out.print("Ingresa el nuevo dato :");
                    int newData = input.nextInt();
                    tree.insert(newData);
                    break;

                case 3:
                    System.out.println("Ya no dio tiempo, perdon :,c");
                case 4:
                    tree.display();
                    break;

                default:
                    break;
            }

        }

    }
}
