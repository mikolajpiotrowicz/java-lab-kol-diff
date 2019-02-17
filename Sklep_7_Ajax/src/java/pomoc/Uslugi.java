/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomoc;

/**
 *
 * @author mpiotrowicz
 */
public class Uslugi {
	
    public static float cena_brutto(int promocja, float cena)
    {
	return cena * (1- (float)promocja / 100);
    }
}
