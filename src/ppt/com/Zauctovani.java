package ppt.com;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Zauctovani {
    private String zauctoval;
    private LocalDate datumZauctovani;
    private Doklad dokladZauct;
    private boolean zauct;

    public Zauctovani(String zauctoval, LocalDate date, Doklad d){
        this.zauctoval = zauctoval;
        this.datumZauctovani = date;
        this.dokladZauct = d;
        zauct = false;
    }

    @Override
    public String toString() {
        return  "zauctoval='" + zauctoval + '\'' +
                ", " + dokladZauct;
    }

    public BigDecimal zauctuj(){
        if(dokladZauct.getDatumSplatnosti().isBefore(dokladZauct.getDatumVystaveni())){
            throw new DateTimeException("doklad datum");
        }
        if(!dokladZauct.getCalc()){
            throw new IllegalStateException("doklad calc");
        }
        if(dokladZauct.getSazbaDPH() > 0){
            if(dokladZauct.getDatumVystaveni().getYear() <= 2010){
                BigDecimal tmp = dokladZauct.getCenaSDPH();
                dokladZauct.setCenaSDPH(dokladZauct.getCenaSDPH().setScale(0, BigDecimal.ROUND_HALF_UP));
                double rozdil = tmp.doubleValue() - dokladZauct.getCenaSDPH().doubleValue();
                dokladZauct.setZaokr(new BigDecimal(rozdil));
            }else{
                if(dokladZauct.getDatumVystaveni().getYear() <= 2014){
                    BigDecimal tmp = dokladZauct.getCenaSDPH();
                    dokladZauct.setCenaSDPH(dokladZauct.getCenaSDPH().setScale(0, BigDecimal.ROUND_UP));
                    double rozdil = tmp.doubleValue() - dokladZauct.getCenaSDPH().doubleValue();
                    dokladZauct.setZaokr(new BigDecimal(rozdil));
                }else{
                    BigDecimal tmp = dokladZauct.getCenaSDPH();
                    dokladZauct.setCenaSDPH(dokladZauct.getCenaSDPH().setScale(0, BigDecimal.ROUND_HALF_UP));
                    double rozdil = tmp.doubleValue() - dokladZauct.getCenaSDPH().doubleValue();
                    dokladZauct.setZaokr(new BigDecimal(rozdil));
                }
            }
        }else{
            dokladZauct.setZaokr(new BigDecimal(0));
        }
        return dokladZauct.getCenaSDPH();
    }

    public String getZauctoval() {
        return zauctoval;
    }


    public void setZauctoval(String zauctoval) {
        zauctoval = zauctoval;
    }

    public LocalDate getDatumZauctovani() {
        return datumZauctovani;
    }

    public void setDatumZauctovani(LocalDate datumZauctovani) {
        this.datumZauctovani = datumZauctovani;
    }

    public Doklad getDokladZauct() {
        return dokladZauct;
    }

    public void setDokladZauct(Doklad dokladZauct) {
        this.dokladZauct = dokladZauct;
    }
}
