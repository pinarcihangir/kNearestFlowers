
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;



public class Main 
{
    public static void main(String[] args)
    {
        Main main = new Main();
        Scanner inputstream = null;
        try
        {
            File file = new File("C:\\Users\\osman\\Documents\\NetBeansProjects\\Cicek\\metin.txt");
            inputstream = new Scanner(file);
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Aradığınız dosya bulunamadı...");
            System.exit(0);
        }
        
        String delimiter = ",";
        ArrayList<CicekClass> cicekler = new ArrayList<CicekClass>();
        ArrayList<CicekClass> irisSetosa = new ArrayList<CicekClass>();
        ArrayList<CicekClass> irisVersicolor = new ArrayList<CicekClass>();
        ArrayList<CicekClass> irisVerginica = new ArrayList<CicekClass>();
        
        while(inputstream.hasNextLine())
        {
            StringTokenizer str = new StringTokenizer(inputstream.nextLine(),delimiter);
            
            CicekClass cicek = new CicekClass();
            
            
            
            int sayac = 0;
            while(str.hasMoreTokens())
            {
                String token =str.nextToken();
                switch (sayac) 
                {
                    case 0:
                        cicek.setCanakYaprakUzunlugu(Float.parseFloat(token));
                        cicek.getOzellikDegerleri().set(0, Float.parseFloat(token));
                        sayac++;
                        
                        break;
                    case 1:
                        cicek.setCanakYaprakGenisligi(Float.parseFloat(token));
                        cicek.getOzellikDegerleri().set(1, Float.parseFloat(token));
                        sayac++;
                        
                        break;
                    case 2:
                        cicek.setTacYaprakUzunlugu(Float.parseFloat(token));
                        cicek.getOzellikDegerleri().set(2, Float.parseFloat(token));
                        sayac++;
                        
                        break;
                    case 3:
                        cicek.setTacYaprakGenisligi(Float.parseFloat(token));
                        cicek.getOzellikDegerleri().set(3, Float.parseFloat(token));
                        sayac++;
                        
                        break;
                    default:
                        String tur = token;
                        cicek.setTür(tur);
                        
                }
                
                
            }
            cicekler.add(cicek);
        }
        Scanner scanner = new Scanner(System.in);        
        System.out.println("En yakın kaç tane çiçeği referans olarak almak istediğinizi belirlememiz için bu k değerini giriniz :");
        int k = scanner.nextInt();
        boolean state = true;
        while(state)
        {
            
            System.out.println("1 - Bitki sınıflanrıma kısmına girmek için bu şıkkı seçiniz.");
            System.out.println("2 - Ekleme ve Silme işlemleri için bu şıkkı seçiniz.");
            System.out.println("3 - Başarı ölçümünü görmek için bu şıkkı seçiniz");
            System.out.println("4 - VeriSetini listeleme için bu şıkkı seçiniz.");
            System.out.println("5 - Çıkmak için -1 giriniz.");
            int girdi= scanner.nextInt();
            
            if(girdi==1)
            {
                
                main.bitkiSiniflandirma(cicekler,k);
                
            }
            
            else if(girdi==2)
            {
                System.out.println("1 - Çiçek ekleme");
                System.out.println("2 - Çiçek silme");
                int deger = scanner.nextInt();
                if(deger==1)
                {
                    main.cicekEkle(cicekler);
                }
                
                if(deger==2)
                {
                    main.cicekSil(cicekler);
                }
            }
            else if(girdi==3)
            {
                main.basariOlcumu(cicekler,k);
            }
            
            else if(girdi==4)
            {
                main.listele(cicekler);
            }
            
            else if(girdi==-1)
            {
                state = false;
            }
            
            else
            {
                //
            }
            
        }
        
        
    }
    
    public void ozellikEkle(String ozellik, float deger,ArrayList<CicekClass> cicekler)
    {
        
        for(int i =0;i<cicekler.size();i++)
        {
            cicekler.get(i).setOzellikler(cicekler.get(i).getOzellikler()+","+ozellik);
            cicekler.get(i).getOzellikDegerleri().add(deger);
        }
        
        System.out.println("Özellikler Eklendi...");
    }
    
    
    public void ozellikSil(ArrayList<CicekClass> cicekler, String silinecekOzellik)
    {
        for(int i =0;i<cicekler.size();i++)
        {
            if(cicekler.get(i).getOzellikler().contains(silinecekOzellik)==false)
            {
                System.out.println("Silinmek istenen özellik bulunamadı...");
                break;
            }
            int ozellikSirasi = ozellikSirasiBul(silinecekOzellik,cicekler.get(i));
            
            if(ozellikSirasi!=-1)
            {
                cicekler.get(i).getOzellikDegerleri().remove(ozellikSirasi);
            }
            
            String delimiter= ",";
            StringTokenizer str = new StringTokenizer(cicekler.get(i).getOzellikler(),delimiter);
        
            while(str.hasMoreTokens())
            {
                String string  = str.nextToken();
            
                if(string.equalsIgnoreCase(silinecekOzellik))
                {
                    string = "";
                }
            }  
        }
        
        
        
        
    }
    
    
    
    public int ozellikSayisi(CicekClass cicek)
    {
        int sayac = 0;
        for (int i = 0; i<cicek.getOzellikDegerleri().size();i++)
        {
            sayac++;
        }
        
        return sayac;
    }
    
    
    public ArrayList kNearest(ArrayList<CicekClass> list, CicekClass ornekCicek,int a)
    {
        ArrayList<CicekClass> listWithDistance = new ArrayList<CicekClass>(list);
        for(int i = 0; i<list.size();i++)
        {
            float toplam = 0;
            for(int k = 0; k<ozellikSayisi(list.get(i));k++)
            {
                toplam += 
                     ((float)(list.get(i).getOzellikDegerleri().get(k)) - (float)(ornekCicek.getOzellikDegerleri().get(k)))*
                     ((float)(list.get(i).getOzellikDegerleri().get(k)) - (float)(ornekCicek.getOzellikDegerleri().get(k)));
            }
            
            
            
            listWithDistance.get(i).setDistance((float)Math.sqrt(toplam));
            
            
            
            //Uzaklıkları (distance) CicekClass içinde tutma fikrin var. Üzerine biraz daha düşünmen gerekiyor. Hatta değişkenleri tanımladın bile.. :P 
            
        }
        
        boolean sorted = false;
        float temp;
        
        
        while(!sorted)
        {
            sorted =true;
            
            for(int i=0; i<listWithDistance.size()-1;i++)
            {
                if(listWithDistance.get(i).getDistance() > listWithDistance.get(i+1).getDistance())
                {
                    CicekClass tempCicek = new CicekClass();
                    try
                    {
                        tempCicek =(CicekClass) listWithDistance.get(i).clone();
                    }
                    catch(CloneNotSupportedException e)
                    {
                        //
                    }

                    listWithDistance.set(i, listWithDistance.get(i+1));
                    listWithDistance.set(i+1, tempCicek);
                    
                    sorted = false;
                    
                }
            }
        }
        
        
        //Uzaklıkları sıralanmış listeyi döndürüyor
        
        
        int sayacSetosa = 0;
        int sayacVersicolor = 0;
        int sayacVirginica = 0;
        
        for(int i =0;i<a;i++)
        {
            if(listWithDistance.get(i).getTür().equalsIgnoreCase("Iris-setosa"))
            {
                sayacSetosa+=1;
            }
            
            else if(listWithDistance.get(i).getTür().equalsIgnoreCase("Iris-versicolor"))
            {
                sayacVersicolor+=1;
            }
            
            else if(listWithDistance.get(i).getTür().equalsIgnoreCase("Iris-virginica"))
            {
                sayacVirginica+=1;
            }
            else
            {
                //
            }
        }
        
        if(sayacSetosa == sayacVersicolor || sayacVersicolor==sayacVirginica || sayacSetosa==sayacVirginica)
        {
            ArrayList<CicekClass> kNumberedList = new ArrayList<CicekClass>();
            kNumberedList.add(listWithDistance.get(0));
            return kNumberedList;
        }
        
        ArrayList<CicekClass> kListWithDistance = new ArrayList<CicekClass>();
        
        for(int i = 0; i<a;i++)
        {
            kListWithDistance.add(listWithDistance.get(i));
        }
        return kListWithDistance; 
    }
    
    public String tahminEt(ArrayList<CicekClass> list)
    {
        int sayacSetosa = 0;
        int sayacVersicolor = 0;
        int sayacVirginica = 0;
        
        for(int i = 0; i<list.size();i++)
        {
            if(list.get(i).getTür().equalsIgnoreCase("Iris-setosa"))
            {
                sayacSetosa+=1;
            }
            
            else if(list.get(i).getTür().equalsIgnoreCase("Iris-versicolor"))
            {
                sayacVersicolor+=1;
            }
            
            else
            {
                sayacVirginica+=1;
            }
            
        }
        if(sayacSetosa>sayacVersicolor && sayacSetosa > sayacVirginica)
        {
            return "Iris-Setosa";
        }
        else if(sayacVersicolor > sayacSetosa && sayacVersicolor > sayacVirginica)
        {
            return "Irıs-versicolor";
        }
        else
        {
            return "Iris-Virginica";
        }
    }
    
    
    public void bitkiSiniflandirma(ArrayList<CicekClass> cicekler,int k)             
    {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Seçtiğiniz şıkka göre sizden girdiler alınacak ve girdiğiniz verilere göre "+k+" tane en yakın çiçeğin "
                 + "verilerini ve de girdileri girilen çiçeğin cinsini tahmin edip ekrana yazdıracağız  ");
        
        System.out.println("---------------------------------------------------------------------------------------------------");
        
        System.out.println("1 - Yeni çiçeğin 4 adet özelliğini giriniz");
        System.out.println("2 - Yeni çiçeğin sadece 2 adet çanak yaprak özelliğini (uzunluk ve genişlik) giriniz ");
        System.out.println("3 - Yeni çiçeğin sadece 2 adet taç yaprak özelliğini (uzunluk ve genişlik) giriniz ");
        
        System.out.println("---------------------------------------------------------------------------------------------------");
        
        int girdi;
        while(true)
        {
            try
            {
                girdi = scanner.nextInt();
                break;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Yanlış girdi. Lütfen tekrar deneyiniz.");
            }
        }
        
        if(girdi==1)
        {
            
            System.out.print("Çiçeğin 4 adet özeliğini sırasıyla float cinsinden giriniz \n "
                    + "(Çanak Yaprak Uzunluğu, Çanak Yaprak Genişliği, Taç Yaprak Uzunluğu, Taç Yaprak Genişliği)  ");
            float deger1 = scanner.nextFloat();
            float deger2 = scanner.nextFloat();
            float deger3 = scanner.nextFloat();
            float deger4 = scanner.nextFloat();
            CicekClass cicek = new CicekClass();
                
            cicek.getOzellikDegerleri().set((ozellikSirasiBul("canakYaprakUzunlugu",cicek)), deger1);
            cicek.getOzellikDegerleri().set((ozellikSirasiBul("canakYaprakGenisligi",cicek)), deger2);
            cicek.getOzellikDegerleri().set((ozellikSirasiBul("tacYaprakUzunlugu",cicek)), deger3);
            cicek.getOzellikDegerleri().set((ozellikSirasiBul("tacYaprakGenisligi",cicek)), deger4);

            String tahmin =tahminEt(kNearest(cicekler,cicek,k));
            System.out.println("Çiçeğinizin türü tahmini olarak :"+tahmin);
            System.out.println("---------------");
            yazdir(kNearest(cicekler,cicek,k));
        }    
        else if(girdi==2)
        {
             System.out.print("Çiçeğin sadece 2 adet çanak yaprak özelliğini (Uzunluk,Genişlik) giriniz :");
                float canakuzunluk = scanner.nextFloat();
                float canakgenislik = scanner.nextFloat();
                CicekClass cicek2 = new CicekClass();
                
                //Burası gereksiz sooonradan silinebilir
                
                int ozellikSirasi = (ozellikSirasiBul("canakYaprakUzunlugu",cicek2));
                
                if(ozellikSirasi!=-1)
                {
                    cicek2.getOzellikDegerleri().set((ozellikSirasiBul("canakYaprakUzunlugu",cicek2)), canakuzunluk);
                    cicek2.getOzellikDegerleri().set((ozellikSirasiBul("canakYaprakGenisligi",cicek2)), canakgenislik);

                    String tahmin2 = tahminEt(kNearest(cicekler,cicek2,k));
                    System.out.println("Çiçeğinizin türü tahmini olarak :"+tahmin2);
                    System.out.println("---------------");
                    
                    yazdir(kNearest(cicekler,cicek2,k));
            
                }
                else
                {
                    System.out.println("Aradığınız özellik bulunamadı...");
                    
                }
            
        }
               
                
        else if(girdi==3)
        {
            System.out.print("Çiçeğin sadece 2 adet taç yaprak özelliğini (Uzunluk,Genişlik) giriniz :");
            float tacUzunluk = scanner.nextFloat();
            float tacGenislik = scanner.nextFloat();
            CicekClass cicek3 = new CicekClass();
            cicek3.getOzellikDegerleri().set(ozellikSirasiBul("tacYaprakUzunlugu",cicek3), tacUzunluk);
            cicek3.getOzellikDegerleri().set(ozellikSirasiBul("tacYaprakGenisligi",cicek3), tacGenislik);
            String tahmin3 = tahminEt(kNearest(cicekler,cicek3,k));
            System.out.println("Çiçeğinizin türü tahmini olarak :" +tahmin3);
            yazdir(kNearest(cicekler,cicek3,k));
            
        }
        
        else
        {
            System.out.println("Yanlış girdi...");
        }
          
                
                
                
          
              
    }
    
    
    public void basariOlcumu(ArrayList<CicekClass> cicekler,int k)
    {
        ArrayList<CicekClass> irisSetosa = new ArrayList<CicekClass>();
        ArrayList<CicekClass> irisVersicolor = new ArrayList<CicekClass>();
        ArrayList<CicekClass> irisVirginica = new ArrayList<CicekClass>();
        
        
        ArrayList<CicekClass> setosaTest = new ArrayList<CicekClass>();
        ArrayList<CicekClass> versicolorTest = new ArrayList<CicekClass>();
        ArrayList<CicekClass> virginicaTest = new ArrayList<CicekClass>();
        
        for(int i= 0; i<= 149;i++)
        {
            if(i>=0 && i<39)
            {
                irisSetosa.add(cicekler.get(i));
            }
            
            else if(i>=39 && i<=49)
            {
                setosaTest.add(cicekler.get(i));
            }
            
            else if(i>=50 && i<89)
            {
                irisVersicolor.add(cicekler.get(i));
            }
            
            else if(i>=89 && i<=99)
            {
                versicolorTest.add(cicekler.get(i));
            }
            
            else if(i>=100 && i<139)
            {
                irisVirginica.add(cicekler.get(i));
            }
            
            else if(i>=139 && i<=149)
            {
                virginicaTest.add(cicekler.get(i));
            }
            else
            {
                //
            }
        }
        int setosaDogruTahminSayisi = 0;
        for(int i =0; i<setosaTest.size();i++)
        {
            System.out.print((i+1)+". Çiçeğin tahmini türü : \t");
            System.out.print(tahminEt(kNearest(irisSetosa,setosaTest.get(i),k)));
            System.out.println("");
            System.out.println("**********************************************************************************");
            
            System.out.print((i+1)+". Çiçeğin gerçek türü : \t");
            System.out.print(setosaTest.get(i).getTür());
            System.out.println("");
            
            System.out.println("----------------------------------------------------------------------------------");
            
            if(tahminEt(kNearest(irisSetosa,setosaTest.get(i),k)).equalsIgnoreCase("Iris-setosa"))
            {
                setosaDogruTahminSayisi+=1;
            }
        }
        
        System.out.println("Başarılı tahmin oranı :" +(setosaDogruTahminSayisi / setosaTest.size()));
        
        
        int versicolorDogruTahminSayisi = 0;
        for(int i =0; i<versicolorTest.size();i++)
        {
            System.out.print((i+1)+". Çiçeğin tahmini türü : \t");
            System.out.print(tahminEt(kNearest(irisVersicolor,versicolorTest.get(i),k)));
            System.out.println("");
            System.out.println("**********************************************************************************");
            
            System.out.print((i+1)+". Çiçeğin gerçek türü : \t");
            System.out.print(versicolorTest.get(i).getTür());
            System.out.println("");
            
            System.out.println("----------------------------------------------------------------------------------");
            
            if(tahminEt(kNearest(irisVersicolor,versicolorTest.get(i),k)).equalsIgnoreCase("Iris-versicolor"))
            {
                versicolorDogruTahminSayisi+=1;
            }
        }
        
        System.out.println("Başarılı tahmin oranı :" +(versicolorDogruTahminSayisi / versicolorTest.size()));
        
        
        int virginicaDogruTahminSayisi = 0;
        for(int i =0; i<virginicaTest.size();i++)
        {
            System.out.print((i+1)+". Çiçeğin tahmini türü : \t");
            System.out.print(tahminEt(kNearest(irisVirginica,virginicaTest.get(i),k)));
            System.out.println("");
            System.out.println("**********************************************************************************");
            
            System.out.print((i+1)+". Çiçeğin gerçek türü : \t");
            System.out.print(virginicaTest.get(i).getTür());
            System.out.println("");
            
            System.out.println("----------------------------------------------------------------------------------");
            
            if(tahminEt(kNearest(irisVirginica,virginicaTest.get(i),k)).equalsIgnoreCase("Iris-virginica"))
            {
                virginicaDogruTahminSayisi+=1;
            }
        }
        
        System.out.println("Başarılı tahmin oranı :" +(virginicaDogruTahminSayisi / virginicaTest.size()));
 
    }
    
    
    
    public int ozellikSirasiBul(String arananOzellik,CicekClass cicek)
    {
        if(cicek.getOzellikler().contains(arananOzellik)==false)
        {
            return -1;
        }
        
        String delimiter = ",";
        String line = cicek.getOzellikler();
        StringTokenizer str = new StringTokenizer(line,delimiter);
        int sayac = 0;
        while(str.hasMoreTokens())
        {
            String token = str.nextToken();
            if(token.equalsIgnoreCase(arananOzellik)==true)
            {
                break;
            }
            else
            {
                sayac+=1;
            }
        }
        return sayac;
    }
    
    
    
    
    public void yazdir (ArrayList<CicekClass> list)
    {
            
        for(int i = 0; i<list.size();i++)
        {
            String delimiter = ","; 
            StringTokenizer str = new StringTokenizer(list.get(i).getOzellikler(),delimiter);
            System.out.print("Çiçek Özellikleri = ");
            int sayac = 0;
            while(str.hasMoreTokens())
            {
                String token = str.nextToken();
                Float ozellikDegeri = (float)list.get(i).getOzellikDegerleri().get(sayac);
                System.out.print(token +" " );
                System.out.print(ozellikDegeri +"\t");
                sayac+=1;
            }
            System.out.print("Distance :"+list.get(i).getDistance()+"\t" + "Tür :"+list.get(i).getTür());
            System.out.println("");
            System.out.println("----------------------------------------------------------------------");
        }
    }
    
    public void yazdir2(CicekClass cicek)
    {
        String delimiter = ","; 
        StringTokenizer str = new StringTokenizer(cicek.getOzellikler(),delimiter);
        System.out.print("Çiçek Özellikleri = ");
        int sayac =0;
        while(str.hasMoreTokens())
        {
            String token =str.nextToken();
            Float ozellikDegeri = (float)cicek.getOzellikDegerleri().get(sayac);
            System.out.print(token +" " );
            System.out.print(ozellikDegeri +"\t");
            sayac+=1;
            
        }
        
        System.out.print(cicek.getDistance()+"\t" + "Tür :"+cicek.getTür());
        System.out.println("");
        System.out.println("----------------------------------------------------------------------");
        
    }
    
    public void cicekEkle(ArrayList<CicekClass> cicekler)
    {
        Scanner scanner = new Scanner(System.in);
        CicekClass cicek = new CicekClass();
        
        String delimiter = ",";
        StringTokenizer str = new StringTokenizer(cicekler.get(0).getOzellikler(),delimiter);
        
        int sayac =0;
        while(str.hasMoreTokens())
        {
            String token = str.nextToken();
            
            System.out.println(token+" özelliğinin değerini giriniz : ");
            float deger = scanner.nextFloat();
            cicek.getOzellikDegerleri().set(sayac, deger);
            sayac+=1;
        }
        String dummy = scanner.nextLine();
        System.out.println("Eklemek istediğiniz çiçeğin adını giriniz : ");
        String tur = scanner.nextLine();
        cicek.setTür(tur);
        
        cicekler.add(cicek);
        
        System.out.println("Çiçek eklendi...");
        
    }
    
    public void cicekSil(ArrayList<CicekClass> cicekler)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" 1 - Silinmesini istediğiniz değerin indeksini gireceksiniz ve o indeksteki çiçeği silmek"
                 + "istiyorsanız bu şıkkı seçiniz. ");
        System.out.println(" 2 - Verisetideki tüm çiçekleri silmek için bu şıkkı seçiniz.");
        
        int girdi =scanner.nextInt();
        
        if(girdi==1)
        {
            System.out.print("Silmek istediğiniz çiçeğin indisini giriniz");
            int indeks = scanner.nextInt();
            
            cicekler.remove(indeks);
            
        }
        
        else if(girdi==2)
        {
            System.out.println("Verisetindeki tüm çiçekler silindi...");
            cicekler.clear();
        }
        
        else
        {
            System.out.println("Yanlış işlem. Tekrar deneyiniz.");
        }
    }
    
    public void listele(ArrayList<CicekClass> cicekler)
    {
        if(cicekler.size()==0)
        {
            System.out.println("Veriseti şuanda boş...");
            
        }
        for(int i = 0; i<cicekler.size();i++)
        {
            for(int k =0;k<cicekler.get(i).getOzellikDegerleri().size();k++)
            {
                System.out.print(cicekler.get(i).getOzellikDegerleri().get(k) +"\t");
            }
            System.out.print(cicekler.get(i).getTür());
            System.out.println("");
        }
        
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------");
    }
   
    
    
    
    
    /*
    
    cicek2.getOzellikDegerleri().set((ozellikSirasiBul("tacYaprakUzunlugu",cicek2)),(float) 0);
    cicek2.getOzellikDegerleri().set((ozellikSirasiBul("tacYaprakGenisligi",cicek2)), (float) 0);
    
    temp = listWithDistance.get(i).getDistance();

                    listWithDistance.get(i).setDistance(listWithDistance.get(i+1).getDistance());  
                    listWithDistance.get(i+1).setDistance(temp);
    
     public void yazdir (ArrayList<CicekClass> list)
    {
            
        for(int i = 0; i<list.size();i++)
        {
            String delimiter = ","; 
            StringTokenizer str = new StringTokenizer(list.get(i).getOzellikler());
            System.out.print("Çiçek Özellikleri = ");
            int sayac = 0;
            while(str.hasMoreTokens())
            {
                String token = str.nextToken();
                Float ozellikDegeri = (float)list.get(i).getOzellikDegerleri().get(sayac);
                System.out.print(token +" " );
                System.out.print(ozellikDegeri +"\t");
                sayac+=1;
            }
            System.out.print(list.get(i).getDistance()+"\t" + "Tür :"+list.get(i).getTür());
            System.out.println("");
            System.out.println("----------------------------------------------------------------------");
        }
   
        
    }
    
    
    for(int i =0;i<cicekler.get(0).getOzellikDegerleri().size();i++)
        {
            
        }
    
    public ArrayList kNearest(ArrayList<CicekClass> list, CicekClass ornekCicek)
    {
        ArrayList<Float> distanceList = new ArrayList<Float>();
        for(int i = 0; i<list.size();i++)
        {
            float toplam = 0;
            for(int k = 0; k<ozellikSayisi(list.get(i));k++)
            {
                toplam += 
                     ((float)(list.get(i).getOzellikDegerleri().get(k)) - (float)(ornekCicek.getOzellikDegerleri().get(k)))*
                     ((float)(list.get(i).getOzellikDegerleri().get(k)) - (float)(ornekCicek.getOzellikDegerleri().get(k)));
            }
            
            distanceList.add((float)Math.sqrt(toplam));
            
        }
        
        
     
        return distanceList; 
    }
    
    
    
    
    
    
    public ArrayList kNearest(ArrayList<CicekClass> list, CicekClass ornekCicek,int kDegeri)
    {
        ArrayList<Float> distanceList = new ArrayList<Float>();
        ArrayList<Float> kDistanceList = new ArrayList<Float>();
        for(int i = 0; i<list.size();i++)
        {
            float toplam = 0;
            for(int k = 0; k<ozellikSayisi(list.get(i));k++)
            {
                toplam += 
                     ((float)(list.get(i).getOzellikDegerleri().get(k)) - (float)(ornekCicek.getOzellikDegerleri().get(k)))*
                     ((float)(list.get(i).getOzellikDegerleri().get(k)) - (float)(ornekCicek.getOzellikDegerleri().get(k)));
            }
            
            distanceList.add((float)Math.sqrt(toplam));
            
        }
        
        int enyakinSetosaSayisi  =0;
        int enyakinVersicolorSayisi = 0;
        int enyakinVirginicaSayisi = 0;
        
        for(int i = 0; i<kDegeri;i++)
        {
            kDistanceList.add(distanceList.get(i));
            
        }
        
        
        
        
     
        return distanceList; 
    
    
    
    
            for(int i =0;i < 4;i++)
                {
                    if(cicek.getOzellikDegerleri().get(k)!=null)
                    {
                        
                    }
                }
    
    
            
    
            for(int i = 0;i<cicekler.size();i++)
                {
                    for(int l = 0;l<cicekler.get(i).getOzellikDegerleri().size();l++)
                    {
                        while(l==0 && l==1)
                        {
                            cicekler.get(l).getOzellikDegerleri().set(l,uzunluk);
                        }
                            
                    }
                }
    
    
    
    }
    
    
    
    */
    
  
    
    
    

    
    
    
}

