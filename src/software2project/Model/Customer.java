package software2project.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    //Properties
    private SimpleIntegerProperty customerId;
    private SimpleStringProperty customerName;
    private SimpleIntegerProperty active;
    private SimpleIntegerProperty addressId;
    private SimpleStringProperty address;
    private SimpleStringProperty address2;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty phone;
    private SimpleIntegerProperty cityId;
    private SimpleStringProperty city;
    private SimpleIntegerProperty countryId;
    private SimpleStringProperty country;
    
    //Contstructor
    public Customer(int cId, String cname, int active, int addressId, String address, String address2, 
            String postal, String phone, int cityId, String city, int countryId, String country){
        this.customerId = new SimpleIntegerProperty(cId);
        this.customerName = new SimpleStringProperty(cname);
        this.active = new SimpleIntegerProperty(active);
        this.addressId = new SimpleIntegerProperty(addressId);
        this.address = new SimpleStringProperty(address);
        this.address2 = new SimpleStringProperty(address2);
        this.postalCode = new SimpleStringProperty(postal);
        this.phone = new SimpleStringProperty(phone);
        this.cityId = new SimpleIntegerProperty(cityId);
        this.city = new SimpleStringProperty(city);
        this.countryId = new SimpleIntegerProperty(countryId);
        this.country = new SimpleStringProperty(country);
    }
    
    public Customer(){
        this.customerId = new SimpleIntegerProperty();
        this.customerName = new SimpleStringProperty();
        this.active = new SimpleIntegerProperty();
        this.addressId = new SimpleIntegerProperty();
        this.address = new SimpleStringProperty();
        this.address2 = new SimpleStringProperty();
        this.postalCode = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.cityId = new SimpleIntegerProperty();
        this.city = new SimpleStringProperty();
        this.countryId = new SimpleIntegerProperty();
        this.country = new SimpleStringProperty();
    }
    
    //Set Methods
    public void setCustomerId(int id){
        this.customerId.set(id);
    }
    
    public void setCustomerName(String name){
        this.customerName.set(name);
    }
    
    public void setActive(int active){
        this.active.set(active);
    }
    
    public void setAddressId(int id){
        this.addressId.set(id);
    }
    
    public void setAddress(String a){
        this.address.set(a);
    }
    
    public void setAddress2(String a2){
        this.address2.set(a2);
    }
    
    public void setPostalCode(String pc){
        this.postalCode.set(pc);
    }
    
    public void setPhone(String p){
        this.phone.set(p);
    }
    
    public void setCityId(int id){
        this.cityId.set(id);
    }
    
    public void setCity(String c){
        this.city.set(c);
    }
    
    public void setCountryId(int id){
        this.countryId.set(id);
    }
    
    public void setCountry(String cntry){
        this.country.set(cntry);
    }
    
    //Get Methods
    public int getCustomerId(){
        return this.customerId.get();
    }
    
    public String getCustomerName(){
        return this.customerName.get();
    }
    
    public int getActive(){
        return this.active.get();
    }
    
    public int getAddressId(){
        return this.addressId.get();
    }
    
    public String getAddress(){
        return this.address.get();
    }
    
    public String getAddress2(){
        return this.address2.get();
    }
    
    public String getPostalCode(){
        return this.postalCode.get();
    }
    
    public String getPhone(){
        return this.phone.get();
    }
    
    public int getCityId(){
        return this.cityId.get();
    }
    
    public String getCity(){
        return this.city.get();
    }
    
    public int getCountryId(){
        return this.countryId.get();
    }
    
    public String getCountry(){
        return this.country.get();
    }
}
