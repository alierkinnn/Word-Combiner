package com.backend.backend.service;

import com.backend.backend.model.Text;
import com.backend.backend.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TextServiceImpl implements TextService{

    public static boolean icindeVarmi(ArrayList<String> list) {
        for(int i=0;i<list.size();i++) {
            for(int j=i+1;j<list.size();j++) {
                if(list.get(i).length()>list.get(j).length()) {
                    if(list.get(i).substring(0,list.get(j).length()).equals(list.get(j))) {
                        list.remove(j);
                        return true;
                    }
                }
                else if(list.get(i).length()<list.get(j).length()) {
                    if(list.get(j).substring(0,list.get(i).length()).equals(list.get(i))) {
                        list.remove(i);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public static int control(String x,String y,int kontrol) {
        String input = x + " " +y ;
        String[] inputArr = input.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        Set<String> mySet = new LinkedHashSet<String>();
        for(String s : inputArr) {
            list.add(s);
            mySet.add(s);
        }
        if(mySet.size() != list.size()) {
            //System.out.println("deneme");
            kontrol = kontrol + 1;
            return kontrol;
        }
        else {
            if(icindeVarmi(list)) {
                //System.out.println("deneme");
                kontrol = kontrol + 1;
                return kontrol;
            }
        }
        return kontrol;
    }


    @Autowired
    private TextRepository repository;

    @Override
    public Text saveText(Text text) {
        findOutput(text);
        return repository.save(text);
    }

    public void findOutput(Text text){

        Long startTime = System.nanoTime();

        String output1 = "";
        String time = "";

        ArrayList<String> inputList = text.getInput(); //tüm inputları inputList adlı değişkene attım
        ArrayList<String> inputList2 = new ArrayList<String >();
        //tüm inputlarımı gezerek hepsini küçük harfe çevirdim
        for(int i=0;i<inputList.size();i++){
            inputList2.add(inputList.get(i));
            inputList2.set(i, inputList2.get(i).toLowerCase());
        }
        //inputların cümle mi kelime mi oldugunu anlama
        int wordOrSentence = 0;
        for(int i=0;i<inputList2.size();i++) {
            for(int j=0;j<inputList2.get(i).length();j++) {
                if(inputList2.get(i).charAt(j) == ' ') {
                    wordOrSentence = 1;
                }
            }
        }

        //output bulma işlemi yapılıp yapılmayacağına karar verme
        int sonKontrol = 0;
        for(int i=0;i<inputList2.size();i++) {
            for(int j=i+1;j<inputList2.size();j++) {
                int kontrol = 0;
                //System.out.println(kontrol);
                if(control(inputList2.get(i),inputList2.get(j),kontrol) == 1) {
                    sonKontrol++;
                    break;
                }
            }
        }


        //cümleyse ve output bulunacaksa bu koşula gir
        if(wordOrSentence == 1 && sonKontrol+1 == inputList2.size()){
            String inputs = "";
            for(int i=0;i<inputList2.size();i++) {
                inputs = inputs+inputList2.get(i)+" ";
            }
            //System.out.println(inputs);

            String[] inputArr = inputs.split(" ");
            Set<String> mySet = new LinkedHashSet<String>();
            //mySet.add("");
            for(String s : inputArr) {
                mySet.add(s);
            }

            String setToArray[] = new String[mySet.size()];
            String inputArr2[] = mySet.toArray(setToArray);
            ArrayList<String> inputArrList = new ArrayList<String>();
            for(int i=0;i<inputArr2.length;i++) {
                inputArrList.add(inputArr2[i]);
            }

            for(int i=0;i<inputArrList.size();i++) {
                for(int j=i+1;j<inputArrList.size();j++) {
                    if(inputArrList.get(i).length()>inputArrList.get(j).length()) {
                        if(inputArrList.get(i).substring(0,inputArrList.get(j).length()).equals(inputArrList.get(j))) {
                            inputArrList.remove(j);
                        }
                    }
                    else if(inputArr2[i].length()<inputArr2[j].length()) {
                        if(inputArrList.get(j).substring(0,inputArrList.get(i).length()).equals(inputArrList.get(i))) {
                            inputArrList.remove(i);
                        }
                    }
                }
                //System.out.println(inputArrList.get(i));
            }

            for(String s:inputArrList){
                output1 += s + " ";
            }
            text.setOutput(output1);

            long endTime = System.nanoTime();
            double dtime = (double)(endTime-startTime)/1000000;
            time = Double.toString(dtime);
            text.setTime(time);
        }

        //kelimeyse ve output bulunacaksa bu koşula gir
        else if(wordOrSentence == 0 && sonKontrol+1 == inputList2.size()){
            text.setOutput("kelime");
            text.setTime("0");
        }


        else{
            text.setOutput("birleştirme işlemi yapılamaz");
            text.setTime("0");
        }


    }

    @Override
    public List<Text> getOutput() {
        return repository.findAll();
    }
}
