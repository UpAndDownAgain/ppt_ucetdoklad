package ppt.com;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Doklad {
    private String cisloDokladu;
    private LocalDate datumVystaveni;
    private LocalDate datumSplatnosti;
    private BigDecimal cenaBezDPH;
    private int sazbaDPH;
    private BigDecimal dph;
    private BigDecimal cenaSDPH;
    private boolean calc;
    private boolean zauct;
    private BigDecimal zaokr;


    public void setZaokr(BigDecimal zaokr) {
        this.zaokr = zaokr;
    }

    public Doklad(String cs, LocalDate vystaveni, BigDecimal cena){
        this.cisloDokladu = cs;
        this.datumVystaveni = vystaveni;
        this.cenaBezDPH = new BigDecimal(cena.toString());

        this.sazbaDPH = -1;
        this.datumSplatnosti = vystaveni;
        this.dph = new BigDecimal(0);
        this.cenaSDPH =  new BigDecimal(0);
        this.calc = false;

    }
    public Doklad(String cs, LocalDate vystaveni, BigDecimal cena, int dph){
        this(cs, vystaveni, cena);
        this.sazbaDPH = dph;
        this.datumSplatnosti = this.datumSplatnosti.plusDays(14);
        this.calculatePrice();
    }

    public Doklad(Doklad d){
        this.cisloDokladu = new String(d.cisloDokladu);
        this.datumVystaveni = d.datumVystaveni;
        this.datumSplatnosti = d.datumSplatnosti;
        this.cenaBezDPH = d.cenaBezDPH;
        this.cenaSDPH = d.cenaSDPH;
        this.sazbaDPH = d.sazbaDPH;
        this.dph = d.dph;
        this.calc = d.calc;
    }

    @Override
    public String toString() {
        return cisloDokladu;
    }

    public boolean equals(Doklad d){
        return this.cisloDokladu.equals(d.cisloDokladu);
    }

    public void calculatePrice(){
        double tmp = this.cenaBezDPH.doubleValue() + (this.cenaBezDPH.doubleValue() * (sazbaDPH / 100.0));
        this.cenaSDPH = new BigDecimal(tmp);
        this.calc = true;
    }

    public String getCisloDokladu() {
        return cisloDokladu;
    }

    public void setCisloDokladu(String cisloDokladu) {
        this.cisloDokladu = cisloDokladu;
    }

    public LocalDate getDatumVystaveni() {
        return datumVystaveni;
    }

    public void setDatumVystaveni(LocalDate datumVystaveni) {
        this.datumVystaveni = datumVystaveni;
    }

    public LocalDate getDatumSplatnosti() {
        return datumSplatnosti;
    }

    public void setDatumSplatnosti(LocalDate datumSplatnosti) {
        this.datumSplatnosti = datumSplatnosti;
    }

    public BigDecimal getCenaBezDPH() {
        return cenaBezDPH;
    }

    public void setCenaBezDPH(BigDecimal cenaBezDPH) {
        this.cenaBezDPH = cenaBezDPH;
    }

    public int getSazbaDPH() {
        return sazbaDPH;
    }

    public void setSazbaDPH(int sazbaDPH) {
        this.sazbaDPH = sazbaDPH;
    }

    public BigDecimal getDph() {
        return dph;
    }

    public void setDph(BigDecimal dph) {
        this.dph = dph;
    }

    public BigDecimal getCenaSDPH() {
        return cenaSDPH;
    }

    public void setCenaSDPH(BigDecimal cenaSDPH) {
        this.cenaSDPH = cenaSDPH;
    }
    public boolean getCalc(){
        return this.calc;
    }

    public boolean isZauct() {
        return zauct;
    }

    public void setZauct(boolean zauct) {
        this.zauct = zauct;
    }

    public BigDecimal getZaokr() {
        return zaokr;
    }

}
