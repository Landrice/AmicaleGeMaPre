/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gettersSetters;

/**
 *
 * @author Ralande
 */
public class Convertion {

    public Double sacENkilo(Double nombreactuel) {
        Double convertion = 50.00 * nombreactuel;
        return convertion;
    }

    public Double sacENgramme(Double nombreactuel) {
        Double convertion = 50000.00 * nombreactuel;
        return convertion;
    }

    public Double kiloENsac(Double nombreactuel) {
        Double convertion = nombreactuel / 50.00;
        return convertion;
    }

    public Double kiloENgramme(Double nombreactuel) {
        Double convertion = nombreactuel * 1000;
        return convertion;
    }

    public Double grammeENkilo(Double nombreactuel) {
        Double convertion = nombreactuel / 1000.00;
        return convertion;
    }

    public Double grammeENsac(Double nombreactuel) {
        Double convertion = nombreactuel / 50000.00;
        return convertion;
    }
}
