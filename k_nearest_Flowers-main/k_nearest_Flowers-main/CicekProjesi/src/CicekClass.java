
import java.util.ArrayList;
import java.util.Arrays;


public class CicekClass implements Cloneable
{
    private float canakYaprakUzunlugu;
    //private String ScanakYaprakUzunlugu;
    
    private float canakYaprakGenisligi;
    
    private float tacYaprakUzunlugu;
    private float tacYaprakGenisligi;
    private String Tür;
    
    private String ozellikler;
    
    private float distance;

    
    private ArrayList<Float> ozellikDegerleri = new ArrayList<Float>();
    private ArrayList<String> ozelliklerListesi = new ArrayList<String>();

    public CicekClass()
    {
        canakYaprakUzunlugu =(float) 0.0;
        canakYaprakGenisligi =(float)0.0;
        tacYaprakUzunlugu =(float) 0.0;
        tacYaprakGenisligi =(float) 0.0;
       
        ozellikler = "canakYaprakUzunlugu,canakYaprakGenisligi,tacYaprakUzunlugu,tacYaprakGenisligi";
        ozellikDegerleri.add(canakYaprakUzunlugu);
        ozellikDegerleri.add(canakYaprakGenisligi);
        ozellikDegerleri.add(tacYaprakUzunlugu);
        ozellikDegerleri.add(tacYaprakGenisligi);
        
        
        ozelliklerListesi.add("canakYaprakUzunlugu");
        ozelliklerListesi.add("canakYaprakGenisligi");
        ozelliklerListesi.add("tacYaprakUzunlugu");
        ozelliklerListesi.add("tacYaprakGenisligi");
        Tür="";
        distance =0;
        
    }
    
    public Object clone()throws CloneNotSupportedException
    {  
        return super.clone();  
    }  
    
    public CicekClass(float canakYaprakUzunlugu,float canakYaprakGenisligi,float tacYaprakUzunlugu,float tacYaprakGenisligi)
    {
        this.canakYaprakUzunlugu = canakYaprakUzunlugu;
        this.canakYaprakGenisligi = canakYaprakGenisligi;
        this.tacYaprakUzunlugu = tacYaprakUzunlugu;
        this.tacYaprakGenisligi = tacYaprakGenisligi;
    }
    
    public void CanakOzellikleri(float canakYaprakUzunlugu,float canakYaprakGenisligi)
    {
        this.setCanakYaprakUzunlugu(canakYaprakUzunlugu);
        this.setCanakYaprakGenisligi(canakYaprakGenisligi);
    }
    
    public void tacOzellikleri(float tacYaprakUzunlugu, float tacYaprakGenisligi)
    {
        this.setTacYaprakUzunlugu(tacYaprakUzunlugu);
        this.setTacYaprakGenisligi(tacYaprakGenisligi);
    }

    /**
     * @return the canakYaprakUzunlugu
     */
    public float getCanakYaprakUzunlugu() {
        return canakYaprakUzunlugu;
    }

    /**
     * @param canakYaprakUzunlugu the canakYaprakUzunlugu to set
     */
    public void setCanakYaprakUzunlugu(float canakYaprakUzunlugu) {
        this.canakYaprakUzunlugu = canakYaprakUzunlugu;
    }

    /**
     * @return the canakYaprakGenisligi
     */
    public float getCanakYaprakGenisligi() {
        return canakYaprakGenisligi;
    }

    /**
     * @param canakYaprakGenisligi the canakYaprakGenisligi to set
     */
    public void setCanakYaprakGenisligi(float canakYaprakGenisligi) {
        this.canakYaprakGenisligi = canakYaprakGenisligi;
    }

    /**
     * @return the tacYaprakUzunlugu
     */
    public float getTacYaprakUzunlugu() {
        return tacYaprakUzunlugu;
    }

    /**
     * @param tacYaprakUzunlugu the tacYaprakUzunlugu to set
     */
    public void setTacYaprakUzunlugu(float tacYaprakUzunlugu) {
        this.tacYaprakUzunlugu = tacYaprakUzunlugu;
    }

    /**
     * @return the tacYaprakGenisligi
     */
    public float getTacYaprakGenisligi() {
        return tacYaprakGenisligi;
    }

    /**
     * @param tacYaprakGenisligi the tacYaprakGenisligi to set
     */
    public void setTacYaprakGenisligi(float tacYaprakGenisligi) {
        this.tacYaprakGenisligi = tacYaprakGenisligi;
    }

    /**
     * @return the Tür
     */
    public String getTür() {
        return Tür;
    }

    /**
     * @param Tür the Tür to set
     */
    public void setTür(String Tür) {
        this.Tür = Tür;
    }

    /**
     * @return the ozellikler
     */
    public String getOzellikler() {
        return ozellikler;
    }

    /**
     * @param ozellikler the ozellikler to set
     */
    public void setOzellikler(String ozellikler) {
        this.ozellikler = ozellikler;
    }

    /**
     * @return the ozellikDegerleri
     */
    public ArrayList getOzellikDegerleri() {
        return ozellikDegerleri;
    }

    /**
     * @param ozellikDegerleri the ozellikDegerleri to set
     */
    public void setOzellikDegerleri(ArrayList ozellikDegerleri) {
        this.ozellikDegerleri = ozellikDegerleri;
    }

    /**
     * @return the distance
     */
    public float getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**
     * @return the ozelliklerListesi
     */
    public ArrayList getOzelliklerListesi() {
        return ozelliklerListesi;
    }

    /**
     * @param ozelliklerListesi the ozelliklerListesi to set
     */
    public void setOzelliklerListesi(ArrayList ozelliklerListesi) {
        this.ozelliklerListesi = ozelliklerListesi;
    }
    
}
