package LAB_1_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

     static HashMap<String, ArrayList<Integer>> year = new HashMap<>();



    public static void main(String[] args) {

        ArrayList<HashMap<String, ArrayList<Integer>>> monthlySales = new ArrayList<>();


        ArrayList<String> aylar = new ArrayList<String>();
        aylar.add("Datasets/01-January.csv");
        aylar.add("Datasets/02-February.csv");
        aylar.add("Datasets/03-March.csv");
        aylar.add("Datasets/04-April.csv");
        aylar.add("Datasets/05-May.csv");
        aylar.add("Datasets/06-June.csv");
        aylar.add("Datasets/07-July.csv");
        aylar.add("Datasets/08-August.csv");
        aylar.add("Datasets/09-September.csv");
        aylar.add("Datasets/10-October.csv");
        aylar.add("Datasets/11-November.csv");
        aylar.add("Datasets/12-December.csv");



        Thread t0 = new threadpasa(aylar.get(0), "th0", year);
        Thread t1 = new threadpasa(aylar.get(1), "th1", year);
        Thread t2 = new threadpasa(aylar.get(2), "th2", year);
        Thread t3 = new threadpasa(aylar.get(3), "th3", year);
        Thread t4 = new threadpasa(aylar.get(4), "th4", year);
        Thread t5 = new threadpasa(aylar.get(5), "th5", year);
        Thread t6 = new threadpasa(aylar.get(6), "th6", year);
        Thread t7 = new threadpasa(aylar.get(7), "th7", year);
        Thread t8 = new threadpasa(aylar.get(8), "th8", year);
        Thread t9 = new threadpasa(aylar.get(9), "th9", year);
        Thread t10 = new threadpasa(aylar.get(10) ,"th10", year);
        Thread t11 = new threadpasa(aylar.get(11), "th11", year);


        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();





        for( int x = 0; x<aylar.size(); x++){
            HashMap<String, ArrayList<Integer>> readdata =  readFiles(aylar.get(x));
            HashMap<String, ArrayList<Integer>> calculatedmonthlysales = calculateMonthlySales(readdata);
            calculateYearlySales(calculatedmonthlysales);
        }





        System.out.println("  ************YEAR MAP YAPISININ ?????? ************.");
        for (Map.Entry<String, ArrayList<Integer>> entry :  year.entrySet()) {//gene for each yap??s?? kuruyorum ald??m adamdan map yap??s??n??.
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
        System.out.println("************YEAR MAP YAPISININ ?????? ************");


        System.out.println("PART 2 BA??LIYOR ............ ");
        Scanner scan = new Scanner(System.in);
        System.out.println("Which product do you want search?");
        String userSelect = scan.nextLine();


        if(year.containsKey(userSelect)){
            System.out.println("For the product "+ userSelect);
            System.out.println("In-store sales: " + year.get(userSelect).get(0) + " ???");
            System.out.println("Online sales: " + year.get(userSelect).get(1) + " ???");
            System.out.println("Total sales: " + (year.get(userSelect).get(0) + year.get(userSelect).get(1)) + " ???");
        } else if(userSelect.equals("X") || userSelect.equals("x")){
            //TODO

            int sum = 0;

            for (Map.Entry<String, ArrayList<Integer>> entry :  year.entrySet()) {
                 sum += entry.getValue().get(0) +  entry.getValue().get(1);
            }


            System.out.println("The sum of in-store and online sales for all products: "+ sum );
        }else {
            System.out.println("Product " + userSelect + " not found!");
        }




    }







    static HashMap<String, ArrayList<Integer>> readFiles(String month) { // https://www.web-gelistirme-sc.com/tr/java/next-ve-nextline-yontemleri-arasindaki-scanner-sinifindaki-fark-nedir/1044541919/#:~:text=Bir%20Scanner%20%2C%20varsay??lan%20olarak%20bo??lukla,ilerletir%20ve%20atlanan%20giri??i%20d??nd??r??r.
        File file = new File(month); //file okumak i??in yazd??????m kod bildi??im gibi her zaman File file = new file("blabla.csv") ile ayn?? sadece parametreli hali.
        HashMap<String, ArrayList<Integer>> data = new HashMap<>(); // bir hashmap d??nd??rce??i i??in bu fonksyon bir map tan??mlad??m.
        try {
            Scanner scanner = new Scanner(file); // de??erleri okumak istiyorum. Scaner kulan??p i??ine file instancemi verirsem o dosyay?? okuyabilirim.
            scanner.nextLine(); // imleci bir sonraki sat??ra yerle??tirdim. ????nk?? split yapaca????m.

            while (scanner.hasNextLine()) { // ilk c??mleden sonra sat??r alt?? veri olup olmad?????? kontrol etmek i??in kulland??????m??z fonksiyon.
                String readLine = scanner.nextLine(); // T??m sat??r?? okumak i??in nextLine() i??levini kullanabilirsiniz.

                // D,35,10,33
                String[] splitLine = readLine.split(","); // okudu??u veriyi tek tek par??ala.String instance'??ndan split kulan??yorum. , g??rd??nm?? onu kes ve string arrayine onu kaydet dedim.

                String name = splitLine[0]; //okudu??un ilk k??s??m isim olsun ve onu name'in i??inde tutay??m.
                int price = Integer.parseInt(splitLine[1]); //okudu??un string valuesunu int bir de??ere de??i??tirmek i??in b??yle bir ??ey yap??yoruz.
                int storeP = Integer.parseInt(splitLine[2]); //okudu??un string valuesunu int bir de??ere de??i??tirmek i??in b??yle bir ??ey yap??yoruz.
                int onlineP = Integer.parseInt(splitLine[3]); //okudu??un string valuesunu int bir de??ere de??i??tirmek i??in b??yle bir ??ey yap??yoruz.

                ArrayList<Integer> values = new ArrayList<>(); // ??imdi ismi kolayca g??mebiliyorum de mi ? neden ????nk?? key zaten string demek ki value i??in benim o int de??erleri tutmam i??in ne yapamam gerek arraylist olu??turmam gerek ki mape ekliyim.
                values.add(price);
                values.add(storeP); //array listime okudu??um de??erleri tek tek ekledim. 0 da price var bla bla.
                values.add(onlineP);

                data.put(name, values); //d??nd??rce??imiz map yap??s??n??n i??ine string k??sm?? (name) ve arraylist (??ntleri) values ile at??yoruz.
            }

        } catch (FileNotFoundException e) {
            System.out.println("EXCEPTION!!!"); // bi terslik olursa d??nd??rs??n diye try catch att??k.
        }

        return data; // map yap??m??z?? geri d??nd??rd??m.
    }





    static void calculateYearlySales(HashMap<String, ArrayList<Integer>>  monthlySales) {


        for (Map.Entry<String, ArrayList<Integer>> entry :  monthlySales.entrySet()) {//gene for each yap??s?? kuruyorum ald??m adamdan map yap??s??n??.
            String product = entry.getKey();  // A de??erimiz geldi ilk ??al????t??g??nda. // bence buna gerek yok
            int store = 0;
            int online = 0;
            int mstore = 0, monline = 0; //Toplama yapca????m i??in 0 dan ba??latt??m

            if(year.containsKey(product)){
                           ArrayList<Integer>  data    =  year.get(product);
                           store = data.get(0);
                           online = data.get(1);
            }

                 mstore = entry.getValue().get(0);
                 monline = entry.getValue().get(1);



                 store = store + mstore;
                 online = online + monline;


            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(store);
            arrayList.add(online);

            year.put(product, arrayList);

        } // for each
    }



    static HashMap<String, ArrayList<Integer>> calculateMonthlySales(HashMap<String, ArrayList<Integer>> sales) {
        HashMap<String, ArrayList<Integer>> ans = new HashMap<>(); //d??nd??rce??im map yap??s??

        for (Map.Entry<String, ArrayList<Integer>> entry : sales.entrySet()) {
            String month = entry.getKey(); // A k?? ald??k key olarak.
            ArrayList<Integer> values = entry.getValue(); // A n??n arraylistinide elimde tutup i??lem yapmak i??in onuda ald??m ve kendi a??t??g??m arraylistin i??ine att??m.

            int storeSales = values.get(0) * values.get(1); //labda verdi??i gibi ??arpma i??lemleri yap??ld??
            int onlineSales = values.get(0) * values.get(2); //labda verdi??i gibi ??arpma i??lemleri yap??ld??

            ArrayList<Integer> monthlySales = new ArrayList<>();  // yeni bir yap?? kuraca????m??z i??in bu sefer arrlistimiz 0 ve 1 den olu??cak.
            monthlySales.add(storeSales); //eklemeleri yapt??m.
            monthlySales.add(onlineSales); //eklemeleri yapt??m.

            ans.put(month, monthlySales);  //tekrar ismini ve yan??nada yeni arraylistini koydum.
        }

        return ans; // ve art??k ??arp??lm???? de??erler ile yeni map yap??m??z?? d??nd??rm???? oldum.
    }


}


