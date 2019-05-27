/**
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
package main;

import contract.ControllerOrder;
import controller.Controller;
import model.Model;
import view.View;

import java.io.File;

/**
 * The Class Main.
 *
 * @author Jean-Aymeric Diet
 */
public abstract class Main {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(view, model);
        view.setController(controller);

        int temp = 1000;

        for (int t = 60; t > 0; t--) {
            try {
                Thread.sleep(temp);
            } catch (InterruptedException e) {
                System.out.print("Erreur");
            }
            System.out.println(t);

        }
    }
}
