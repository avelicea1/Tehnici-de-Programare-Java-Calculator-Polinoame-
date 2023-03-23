package DataModels;

import java.util.*;

public class Polinom {
    private TreeMap <Integer,Double> monoame = new TreeMap<>(Collections.<Integer>reverseOrder());

    public void addMonom(Monom monom){
        if(monoame.containsKey(monom.getGrad())==false) {
            monoame.put(monom.getGrad(), monom.getCoeficient());
        }else{
            monoame.put(monom.getGrad(), monom.getCoeficient()+monoame.get(monom.getGrad()));
        }
    }
    public String toString(){
        int k=1;
        String rezultat="";
        if(monoame.size()==1){
            if(monoame.firstEntry().getValue()==0){rezultat+="0";}
        }
        for (Map.Entry<Integer, Double> entry: monoame.entrySet()){
            if(k==1){
                    if(entry.getKey()==0){
                        if(entry.getValue()==0){
                            k++;
                        }else {
                            rezultat+=entry.getValue();
                        }
                    }else{
                        if(entry.getKey()==1){
                            if(entry.getValue()==0){
                                k++;
                            }else {
                                rezultat+=entry.getValue()+"*x";
                            }
                        }else {
                            if(entry.getValue()==0){
                                rezultat+="0";
                                k++;
                            }else {
                                if(entry.getValue()<0){
                                    if(entry.getKey()==0) {
                                        rezultat +=  entry.getValue();
                                    }else{
                                        if(entry.getKey()==1){
                                            rezultat += entry.getValue() + "*x" ;
                                        }else{
                                            rezultat +=entry.getValue() +"*x^"+entry.getKey();
                                        }
                                    }
                                }else {
                                    if(entry.getValue()==1){
                                        if(entry.getKey()==0) {
                                            rezultat +=  entry.getValue();
                                        }else{
                                            if(entry.getKey()==1){
                                                rezultat +="x" ;
                                            }else{
                                                rezultat +="x^"+entry.getKey();
                                            }
                                        }
                                    }else {
                                        rezultat += entry.getValue() + "*x^" + entry.getKey();
                                    }
                                }
                            }
                        }
                    }
                    k++;
            }else{
                if(entry.getValue()==0){
                    k++;
                }else {
                    if(entry.getValue()<0){
                        if(entry.getKey()==0) {
                            rezultat +=entry.getValue();
                        }else{
                            if(entry.getKey()==1){
                                rezultat +=entry.getValue() + "*x" ;
                            }else{
                                rezultat+=entry.getValue() +"*x^" + entry.getKey();
                            }
                        }

                    }
                    else {
                        if(entry.getValue()==1){
                            if(entry.getKey()==0) {
                                rezultat += "+"+ entry.getValue();
                            }else{
                                if(entry.getKey()==1){
                                    rezultat +="+x" ;
                                }else{
                                    rezultat +="+x^"+entry.getKey();
                                }
                            }
                        }else{
                            if(entry.getKey()==0){
                                if(entry.getValue()==0){
                                    k++;
                                }else {
                                    if (entry.getValue() < 0) {
                                        rezultat +=entry.getValue() ;
                                    }else{
                                        rezultat+="+"+entry.getValue();
                                    }
                                }
                            }else {
                                if(entry.getKey()==1){
                                    if(entry.getValue()==0){
                                        k++;
                                    }else {
                                        if (entry.getValue() < 0) {
                                            rezultat +=entry.getValue() + "*x";
                                        }else{
                                            rezultat+="+"+entry.getValue()+"*x";
                                        }
                                    }
                                }
                                else {
                                    rezultat+="+"+entry.getValue()+"*x^" + entry.getKey();
                                }
                            }
                        }
                    }
                }
                k++;
            }
        }
        return rezultat;
    }

    public TreeMap<Integer, Double> getMonoame() {
        return monoame;
    }


    public void setMonoame(TreeMap<Integer, Double> monoame) {
        this.monoame = monoame;
    }

    public void clear(){
        monoame.clear();
    }



}
