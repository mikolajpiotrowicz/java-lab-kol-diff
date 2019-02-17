/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// JavaServerFaces/JSF Managed Bean

package warstwa_internetowa;

import java.util.Arrays;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import pomoc.JSFPomoc;
import pomoc.Uslugi;
import pomoc.Zmiana_danych;
import warstwa_biznesowa.Fasada_warstwy_biznesowej;

/**
 *
 * @author mpiotrowicz
 */
@Named(value = "managed_produkt")
@RequestScoped
public class Managed_produkt implements ActionListener {
   
    @EJB
    private Fasada_warstwy_biznesowej fasada;
    private String nazwa;
    private float cena;
    private float promocja;
    private String producent;
    private double liczba_przecenionych_produktow;
    private float cena_brutto;
    private DataModel items;
    private int stan = 1;
    private Date data_produkcji;
    private int min_liczba_przecenionych_prodoktow = 0;
    private int max_liczba_przecenionych_prodoktow = 10;
    private NumberConverter number_convert=new NumberConverter();
    private Zmiana_danych zmiana1 = new Zmiana_danych("nazwa");
    private Zmiana_danych zmiana2 = new Zmiana_danych("cena");
    private Zmiana_danych zmiana3 = new Zmiana_danych("producent");
    private Zmiana_danych zmiana4 = new Zmiana_danych("liczba");
    
    public Managed_produkt() {
    }

    public Zmiana_danych getZmiana1()            {    return zmiana1;    }
    public void setZmiana1(Zmiana_danych zmiana) {    this.zmiana1 = zmiana;    }
    public Zmiana_danych getZmiana2()            {    return zmiana2;    }
    public void setZmiana2(Zmiana_danych zmiana2){    this.zmiana2 = zmiana2;   }
    public Zmiana_danych getZmiana3()            {    return zmiana3;    }
    public void setZmiana3(Zmiana_danych zmiana3){    this.zmiana3 = zmiana3;   }
    public Zmiana_danych getZmiana4()            {    return zmiana4;    }
    public void setZmiana4(Zmiana_danych zmiana4){    this.zmiana4 = zmiana4;   }

    public Fasada_warstwy_biznesowej getFasada(){
        return fasada;
    }

    public void setFasada(Fasada_warstwy_biznesowej fasada){
        this.fasada = fasada;
    }
    
    public NumberConverter getNumber_convert(){
	this.number_convert.setPattern("######.##zł");
	return number_convert;
    }

    public SelectItem[] getItemsAvailableSelectOne() {
	return JSFPomoc.getSelectItems(fasada.findAll(), true);
    }

    public int getMinPromocja()
    {
	return 0;
    }

    public int getMaxPromocja()
    {
	return 100;
    }
    
    public int getMinCena()
    {
	return 1;
    }
    
    public int getMaxCena()
    {
	return 1000000;
    }
    
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }
    
    public float getPromocja() {
        return promocja;
    }

    public void setPromocja(float promocja) {
        this.promocja = promocja;
    }
    
    public float getCena_brutto() {
        return cena_brutto;
    }

    public float getCena_brutto_() {
        return Uslugi.cena_brutto((int)promocja, cena);
    }
    
    public void setCena_brutto(float cena_brutto) {
        this.cena_brutto = cena_brutto;
    }
    
    public Date getData_produkcji() {
 	return data_produkcji;
    }
    
    public void setLiczba_przecenionych_produktow(double liczba_przecenionych_produktow) {
        this.cena_brutto = cena_brutto;
    }
    
    public double getLiczba_przecenionych_produktow() {
 	return liczba_przecenionych_produktow;
    }
    
    public void setData_produkcji(Date data_produkcji) {
	this.data_produkcji = data_produkcji;
    }

    public DataModel utworz_DataModel(){
	return new ListDataModel(fasada.items());
    }
    
    public DataModel getItems(){
	if (items == null){
	    items = utworz_DataModel();
	}
	return items;
    }
    
    public void setItems(DataModel items){
	this.items = items;
    }
    
    public int getStan(){
	return stan;
    }
    
    public void setStan(int stan){
	this.stan = stan;
    }
    
    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public void dodaj_produkt(){
        String dane[] = {nazwa, ""+cena, ""+promocja, producent};
        fasada.utworz_produkt(dane, data_produkcji);
    }

    public void dane_produktu(){
	stan = 1;
        String[] dane = fasada.dane_produktu();
	if (dane == null){
	    stan = 0;
	} else {
            nazwa=dane[0];
            cena=Float.parseFloat(dane[1]);
            promocja=Float.parseFloat(dane[2]);
	    producent=dane[3];
            data_produkcji.setTime(Long.parseLong(dane[4]));
	    cena_brutto=Float.parseFloat(dane[5]);
            System.out.print(Arrays.toString(dane));
	}
    }
    
    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException{
	dodaj_produkt();
	dane_produktu();
    }
}
