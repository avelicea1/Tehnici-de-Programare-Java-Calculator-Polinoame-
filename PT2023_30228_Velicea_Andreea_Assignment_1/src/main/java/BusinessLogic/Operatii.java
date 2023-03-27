package BusinessLogic;

import DataModels.Monom;
import DataModels.Polinom;

import java.util.ArrayList;
import java.util.Map;

public class Operatii {

    //am implementat operatie de adunare parcurgand primul polinom si verificand daca se regasesc monoame de acelasi grad si in
    //cel de-al doilea polinom si am adunat coeficientii corespunzatori lor, iar apoi am parcurs fiecare polinom
    //si am adaugat la rezultat monoamele care nu au gradul comun.
    public Polinom adunaPolinoame(Polinom polinom1, Polinom polinom2){
        Polinom rezultat = new Polinom();
        if(polinom2.getMonoame().size()==0 && polinom1.getMonoame().size()==0)rezultat.addMonom(new Monom(0,0));
        for(Map.Entry<Integer,Double> entry: polinom1.getMonoame().entrySet()){
            if(polinom2.getMonoame().containsKey(entry.getKey())==true){
                Monom monom = new Monom (entry.getValue()+polinom2.getMonoame().get(entry.getKey()),entry.getKey());
                rezultat.addMonom(monom);
            }
        }
        for(Map.Entry<Integer,Double> entry: polinom1.getMonoame().entrySet()){
            if(rezultat.getMonoame().containsKey(entry.getKey())==false){
                rezultat.addMonom(new Monom(entry.getValue(), entry.getKey()));
            }
        }
        for(Map.Entry<Integer,Double> entry: polinom2.getMonoame().entrySet()){
            if(rezultat.getMonoame().containsKey(entry.getKey())==false){
                    rezultat.addMonom(new Monom(entry.getValue(), entry.getKey()));
            }
        }
        return rezultat;
    }
    //am implementat operatie de scadere parcurgand primul polinom si verificand daca se regasesc monoame de acelasi grad si in
    //cel de-al doilea polinom si am scazut coeficientii corespunzatori lor, iar apoi am parcurs fiecare polinom
    //si am adaugat la rezultat monoamele care nu au gradul comun.
    public Polinom scadePolinoame(Polinom polinom1,Polinom polinom2){
        Polinom rezultat = new Polinom();
        if(polinom2.getMonoame().size()==0 && polinom1.getMonoame().size()==0)rezultat.addMonom(new Monom(0,0));
        for(Map.Entry<Integer,Double> entry: polinom1.getMonoame().entrySet()){
            if(polinom2.getMonoame().containsKey(entry.getKey())==true){
                Monom monom = new Monom (entry.getValue()-polinom2.getMonoame().get(entry.getKey()),entry.getKey());
                rezultat.addMonom(monom);
            }
        }
        for(Map.Entry<Integer,Double> entry: polinom1.getMonoame().entrySet()){
            if(rezultat.getMonoame().containsKey(entry.getKey())==false){
                rezultat.addMonom(new Monom(entry.getValue(),entry.getKey()));
            }
        }
        for(Map.Entry<Integer,Double> entry: polinom2.getMonoame().entrySet()){
            if(rezultat.getMonoame().containsKey(entry.getKey())==false){
                rezultat.addMonom(new Monom(-entry.getValue(),entry.getKey()));
            }
        }
        return rezultat;
    }
    //am parcurg ambele polinoame si am inmultit fiecare monom din polinomul 1 cu fiecare monom din polinomul 2
    public Polinom inmultestePolinoame(Polinom polinom1,Polinom polinom2){
        Polinom rezultat = new Polinom();
        if(polinom2.getMonoame().size()==0 && polinom1.getMonoame().size()==0)rezultat.addMonom(new Monom(0,0));
        if(polinom1.getMonoame().size()==0 && polinom2.getMonoame().size()!=0)rezultat.setMonoame(polinom2.getMonoame());
        if(polinom1.getMonoame().size()!=0 && polinom2.getMonoame().size()==0)rezultat.setMonoame(polinom1.getMonoame());
        for(Map.Entry<Integer,Double> entry1: polinom1.getMonoame().entrySet()){
            for(Map.Entry<Integer,Double> entry2: polinom2.getMonoame().entrySet()){
                Monom monom = new Monom(entry1.getValue()*entry2.getValue(),entry1.getKey()+entry2.getKey());
                if(monom.getCoeficient()!=0) {
                    if(rezultat.getMonoame().containsKey(monom.getGrad())==true){
                        monom.setCoeficient(rezultat.getMonoame().get(monom.getGrad())+monom.getCoeficient());
                    }
                    rezultat.addMonom(monom);
                }
            }
        }
        return rezultat;
    }
    // functia este folosita la calcularea impartirii si calculeaza gradul cel mai mare din polinom
    public int grad(Polinom polinom){
        int grad = -1;
        for(Map.Entry<Integer,Double> entry: polinom.getMonoame().entrySet()){
            if(entry.getValue()!=0){
                if( entry.getKey()>grad){
                    grad = entry.getKey();
                }
            }
        }
        return grad;
    }
    //functia este folosita la impartire si extrage monomul cu cel mai mare grad, adica primul
    public Monom cautaMaxim(Polinom polinom){
        int i=0;
        Monom monom = new Monom(0,0);
        for(Map.Entry<Integer,Double >entry : polinom.getMonoame().entrySet()){
            if(entry.getValue()!=0){
            if(i==0){
                monom.setCoeficient(entry.getValue());
                monom.setGrad(entry.getKey());
                i++;
            }
            }
        }
        return monom;
    }
    //am realizat impartirea efectuand operatii de scadere si inmultire. Am impartit primul monom din primul polinom
    //la primul monom din al doilea polinom. Apoi am inmultit rezultatul impartirii cu polinomul 2 si l am scazut din
    //polinomul 1. Am efectuat aceasta operatie pana cand gradul deimpartitului este mai mic decat gradul impartitorului
    //actualizand mereu catul cu suma rezultatelor impartirii polinomului 1 la polinomul 2 si actualizand restul
    //cu rezultatul in urma scaderii din polinomul 1 a impartirii efectuate intre cat si polinom 2
    public ArrayList<Polinom> impartePolinoame(Polinom polinom1, Polinom polinom2)throws Exception{
        ArrayList<Polinom>rezultat = new ArrayList<Polinom>();
        if(polinom2.getMonoame().size()==0 || polinom1.getMonoame().size()==0)throw new Exception("nu se pot imparti doua polinoame nule!");
        if(polinom2.getMonoame().size()==1&&polinom2.getMonoame().firstEntry().getValue()==0)throw new Exception();
        Polinom cat = new Polinom();
        Polinom rest = polinom1;

        if(grad(rest)< grad(polinom2)){
            throw new Exception("gradul deimpartitului trebuie sa fie mai mare decat gradul impartitorului!");
        }else{
            while( grad(rest) >= grad(polinom2)){
                Monom monom1 = cautaMaxim(rest);
                Monom monom2 = cautaMaxim(polinom2);
                Monom monomRezultat = new Monom((monom1.getCoeficient())/(monom2.getCoeficient()), (monom1.getGrad()-monom2.getGrad()) );
                cat.addMonom(monomRezultat);
                Polinom polinomRezultat = new Polinom();
                polinomRezultat.addMonom(monomRezultat);
                Polinom rezultatInmultire = inmultestePolinoame(polinomRezultat,polinom2);
                rest = scadePolinoame(rest, rezultatInmultire);
            }
        }
        rezultat.add(cat);
        rezultat.add(rest);
        return rezultat;
    }
    //am parcurs fiecare monom si am efectuat derivarea
    public Polinom derivarePolinom(Polinom polinom){

        Polinom rezultat = new Polinom();
        if(polinom.getMonoame().size()==0 )rezultat.addMonom(new Monom(0,0));
        for(Map.Entry<Integer, Double> entry: polinom.getMonoame().entrySet()){
            if(entry.getKey()>0){
                Monom monom = new Monom(entry.getValue()*entry.getKey(), entry.getKey()-1);
                rezultat.addMonom(monom);
            }else{
                rezultat.addMonom(new Monom(0,0));
            }
        }
        return rezultat;
    }
    //am parcurs fiecare monom si am efectuat integrarea
    public Polinom integrarePolinom(Polinom polinom){
        Polinom rezultat = new Polinom();
        if(polinom.getMonoame().size()==0 )rezultat.addMonom(new Monom(0,0));
        for(Map.Entry<Integer, Double> entry: polinom.getMonoame().entrySet()){
            Monom monom = new Monom(entry.getValue()/ (entry.getKey()+1), entry.getKey()+1);
            rezultat.addMonom(monom);
        }
        return rezultat;
    }



}
