package PackageTests;
//import org.junit.jupiter.api.Test;

import BusinessLogic.Operatii;
import DataModels.Monom;
import DataModels.Polinom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperatiiTest {


    public void initializeazaPolinoame(Polinom polinom1, Polinom polinom2){
        Monom monom1 = new Monom(2,2);
        Monom monom2 = new Monom(3,4);
        polinom1.addMonom(monom1);
        polinom1.addMonom(monom2);
        Monom monom4 = new Monom(3,2);
        Monom monom5 = new Monom(5,3);
        polinom2.addMonom(monom4);
        polinom2.addMonom(monom5);
    }
    @Test
    public void addTest(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.adunaPolinoame(polinom1,polinom2);
        assertEquals("3.0*x^4+5.0*x^3+5.0*x^2",rezultat.toString(),"Adunarea e corecta!");
    }
    @Test
    public void substractTest(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.scadePolinoame(polinom1,polinom2);
        assertEquals("3.0*x^4-5.0*x^3-1.0*x^2",rezultat.toString(),"Scaderea e corecta!");
    }
    @Test
    public void multiplyTest(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.inmultestePolinoame(polinom1,polinom2);
        assertEquals("15.0*x^7+9.0*x^6+10.0*x^5+6.0*x^4",rezultat.toString(),"Inmultirea e corecta!");
    }
    @Test
    public void divideTest(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        ArrayList<Polinom> listPolinoms= new ArrayList<Polinom>();
        try {
            listPolinoms = operatie.impartePolinoame(polinom1, polinom2);
        }catch(Exception exp){
            System.out.println("nu e bine introdus!");
        }
        int i=0;
        for(Polinom polinomIterator: listPolinoms){
            if(i==0){
                assertEquals("0.6*x-0.36",polinomIterator.toString(),"Catul e corect!");
                i++;
            }
            else{
                if(i==1){
                    i++;
                    assertEquals("0+3.08*x^2",polinomIterator.toString(),"Restul e corect!");
                }
            }

        }
    }
    @Test
    public void derivate1Test(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.derivarePolinom(polinom1);
        assertEquals("12.0*x^3+4.0*x",rezultat.toString(),"Derivarea1  e corecta!");
    }
    @Test
    public void derivate2Test(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.derivarePolinom(polinom2);
        assertEquals("15.0*x^2+6.0*x",rezultat.toString(),"Derivarea2  e corecta!");
    }
    @Test
    public void integrare1Test(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.integrarePolinom(polinom1);
        assertEquals("0.6*x^5+0.6666666666666666*x^3",rezultat.toString(),"Integrarea1  e corecta!");
    }
    @Test
    public void integrare2Test(){
        Polinom polinom1 = new Polinom();
        Polinom polinom2 = new Polinom();
        initializeazaPolinoame(polinom1,polinom2);
        Operatii operatie = new Operatii();
        Polinom rezultat = operatie.integrarePolinom(polinom2);
        assertEquals("1.25*x^4+x^3",rezultat.toString(),"Integrarea2  e corecta!");
    }



}
