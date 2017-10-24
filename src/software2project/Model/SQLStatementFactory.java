package software2project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class SQLStatementFactory {
    private SessionInfo userInfo = SessionInfo.getUser();
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();
    //Login SQL Statements
    public  String getLoginSQLString(String u, String p){
        String SQLLogin = ("Select userName, password FROM user WHERE userName='" 
            + u + "' && password='" + p + "'");
        return SQLLogin;
    }
    
    //Add SQL Statements
    public String getAddCountrySQLString(Customer c) throws SQLException{
        
        try {
            
            Statement countryLookup = connectionManager.getConnection().createStatement();
            String CountrySQL = ("Select countryId FROM country WHERE (country='" 
                    + c.getCountry() + "');");
            ResultSet results = countryLookup.executeQuery(CountrySQL);
            
            if (results.next()){
                    userInfo.getSelectedCustomer().setCountryId(results.getInt(1));
                    return null;
            } else {
                    Statement highestCountry = connectionManager.getConnection().createStatement();
                    String SQLFindHighestCountryID = "SELECT MAX(countryId) FROM country";
                    ResultSet highestCountryID = highestCountry.executeQuery(SQLFindHighestCountryID);
                    highestCountryID.next();
                    c.setCountryId(highestCountryID.getInt(1) + 1);
                    String SQLAddCountry = ("INSERT INTO country (countryId, country, "
                            + "createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"
                            + c.getCountryId() + "', '" + c.getCountry()
                            + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername() + "', CURRENT_TIMESTAMP, '"
                            + userInfo.getUsername() + "');");  
                    return SQLAddCountry;
            }
                 
        } catch (SQLException e) {
            return null;
        }
    }
    
    public String getAddCitySQLString(Customer c) throws SQLException{
        try {         
            Statement cityLookup = connectionManager.getConnection().createStatement();
            String citySQL = ("Select cityId FROM city WHERE (city='" 
                    + c.getCity() + "');");
            ResultSet results = cityLookup.executeQuery(citySQL);
            
            if (results.next()){
                    userInfo.getSelectedCustomer().setCityId(results.getInt(1));
                    return null;
            } else {
                    Statement highestCity = connectionManager.getConnection().createStatement();
                    String SQLFindHighestCityId = "SELECT MAX(cityId) FROM city";
                    ResultSet highestCityId = highestCity.executeQuery(SQLFindHighestCityId);
                    highestCityId.next();
                    c.setCityId(highestCityId.getInt(1) + 1);
                    String SQLAddCity = ("INSERT INTO city (cityId, city, countryId, "
                            + "createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('"
                            + c.getCityId() + "', '" + c.getCity() +
                            "', '" + c.getCountryId() + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername() 
                            + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername() + "');"); 
                    return SQLAddCity;
                }        
        } catch (SQLException e) {
            return null;
        } 
    }
    
    public String getAddAddressSQLString(Customer c) throws SQLException{
        try {  
            
            Statement addressLookup = connectionManager.getConnection().createStatement();
            String AddressSQL = ("Select addressID FROM address WHERE (address='" 
                    + c.getAddress() + "' && address2='" + 
                    c.getAddress2() + "' && postalCode='"
                    + c.getPostalCode() + "' && phone='"
                    + c.getPhone() + "');");
            ResultSet addressResults = addressLookup.executeQuery(AddressSQL);
            
            
            if (addressResults.next()){
                    userInfo.getSelectedCustomer().setAddressId(addressResults.getInt(1));
                    return null;
            } else {
                    Statement highestAddress = connectionManager.getConnection().createStatement();
                    String SQLFindHighestAddressId = "SELECT MAX(addressId) FROM address";
                    ResultSet highestAddressId = highestAddress.executeQuery(SQLFindHighestAddressId);
                    highestAddressId.next();
                    c.setAddressId(highestAddressId.getInt(1) + 1);
                    String SQLAddAddress = ("INSERT INTO address (addressId, address,"
                            + " address2, phone, postalCode, cityId, createDate,"
                            + " createdBy, lastUpdate, lastUpdateBy) VALUES ('"
                            + c.getAddressId() + "', '" + c.getAddress()
                            + "', '" + c.getAddress2()
                            + "', '" + c.getPhone()
                            + "', '" + c.getPostalCode()
                            + "', '" + c.getCityId()
                            + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername()
                            + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername() + "');"); 
                    return SQLAddAddress;
                }        
        } catch (SQLException e) {
            return null;
        } 
    }

    public String getAddCustomerSQLString (Customer c) throws SQLException{
        try {  
            Statement highestCustomerID = connectionManager.getConnection().createStatement();
                    String SQLFindHighestCustomer = "SELECT MAX(customerId) FROM customer";
                    ResultSet highestCustomerResultSet = highestCustomerID.executeQuery(SQLFindHighestCustomer);
                    highestCustomerResultSet.next();
                    c.setCustomerId(highestCustomerResultSet.getInt(1) + 1);

                    
                    String SQLAddCustomer = ("INSERT INTO customer (customerId, "
                            + "customerName, addressId, active, createDate, createdBy, "
                            + "lastUpdate, lastUpdateBy) VALUES ('"
                            + c.getCustomerId() + "', '" + c.getCustomerName()
                            + "', '" + c.getAddressId()
                            + "', '" + Integer.toString(c.getActive())
                            + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername()
                            + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername() + "');");  
          
                    return SQLAddCustomer;        
        } catch (SQLException e) {
            return null;
        }
    }
    
    public String getAddAppointmentSQLString (Appointment a) throws SQLException{
        try {  
            Statement newAppointmentID = connectionManager.getConnection().createStatement();
            String SQLFindHighestAppointmentID = "SELECT MAX(appointmentId) FROM appointment";
            ResultSet highestAppointment = newAppointmentID.executeQuery(SQLFindHighestAppointmentID);
            

            if (highestAppointment.next()){
                a.setAppointmentId(highestAppointment.getInt(1) + 1);
            } else{
                a.setAppointmentId(1);
            }        
            String SQLAddAppointments = ("INSERT INTO appointment (appointmentId, customerId, "
                + "title, description, location, contact, url, start, end, createDate, createdBy, "
                + "lastUpdate, lastUpdateBy) VALUES ('"
                + a.getAppointmentId() + "', '" + a.getCustomerId()
                + "', '" + a.getTitle() + "', '" + a.getDescription()
                + "', '" + a.getLocation() + "', '" + a.getContact()
                + "', '" + a.getURL() + "', '" + a.getStartTimeUTC()
                + "', '" + a.getEndTimeUTC()
                + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername()
                + "', CURRENT_TIMESTAMP, '" + userInfo.getUsername() + "');");
            
            return SQLAddAppointments;  
        
        } catch (SQLException e) {
            return null;
        }
    }
    
    //Modify SQL Statements
    public  String getModifyAddressSQLString() throws SQLException{
        String AddressUpdateSQL = ("UPDATE address SET address='" 
                    + userInfo.getSelectedCustomer().getAddress() + "', address2='" + 
                    userInfo.getSelectedCustomer().getAddress2() + "', postalCode='"
                    + userInfo.getSelectedCustomer().getPostalCode() + "', phone='"
                    + userInfo.getSelectedCustomer().getPhone() + "', cityId='"
                    + userInfo.getSelectedCustomer().getCityId() +"', lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = '" + userInfo.getUsername() + "' WHERE "
                    + "addressId = '" + userInfo.getSelectedCustomer().getAddressId()
                    +"';");
        return AddressUpdateSQL;
    }
    
    public String getModifyCustomerSQLString() throws SQLException{
        String customerUpdateSQL = ("UPDATE customer SET customerName = '"
                + userInfo.getSelectedCustomer().getCustomerName() +"', lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = '" + userInfo.getUsername() 
                + "' WHERE customerId = '" + userInfo.getSelectedCustomer().getCustomerId()
                +"';");
        return customerUpdateSQL;
    }
    
    public String getModifyAppointmentSQLString(Appointment a) throws SQLException{
        String appointmentUpdateSQL = ("UPDATE appointment SET title ='" + a.getTitle() 
            + "', description = '" + a.getDescription() + "', location = '"
            + a.getLocation() + "', url = '" + a.getURL()
            + "', start = '" + a.getStartTimeUTC() + "', end = '" 
            + a.getEndTimeUTC() + "', lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = '" 
            + userInfo.getUsername() + "' WHERE appointmentId = '" + a.getAppointmentId()
            +"';");
    return appointmentUpdateSQL;
    }
    
    //Delete SQL Statements
    public String getDeleteAppointmentSQLString(Appointment a) throws SQLException{
        String deleteAppointmentSQL = ("DELETE FROM appointment WHERE appointmentId = " 
                + a.getAppointmentId() + ";"); 
        return deleteAppointmentSQL;
    }
    
    public  String getDeleteCustomerSQLString(Customer c) throws SQLException{
        String deleteSQL = ("DELETE FROM customer WHERE customerId = " 
                + c.getCustomerId());
        return deleteSQL;
    }
    
    public  String getDeleteAddressSQLString(Customer c) throws SQLException{
        String deleteAddressSQL = ("DELETE FROM address WHERE addressId = " 
                + c.getAddressId());
        return deleteAddressSQL;
    }
    
    //Report SQL Statements
    public  String getAllCustomersSQLString(){
        return ("SELECT customer.customerId, "
                + "customer.customerName, address.addressId, address.address, "
                + "address.address2, address.phone, address.postalCode, "
                + "city.cityId, city.city, country.countryId, country.country "
                + "FROM customer LEFT JOIN address ON customer.addressId = "
                + "address.addressID LEFT JOIN city ON address.cityId = "
                + "city.cityId LEFT JOIN country ON city.countryId = "
                + "country.countryId;");
    }
    
    public  String getAllAppointmentsSQLString(){
        return ("SELECT appointment.appointmentId, appointment.title, "
               + "appointment.description, appointment.location, appointment.contact, "
               + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
               + "customer.customerName, address.addressId, address.address, " 
               + "address.address2, address.phone, address.postalCode, " 
               + "city.cityId, city.city, country.countryId, country.country " 
               + "FROM appointment LEFT JOIN customer on appointment.customerId = "
               + "customer.customerId LEFT JOIN address ON customer.addressId = " 
               + "address.addressID LEFT JOIN city ON address.cityId = " 
               + "city.cityId LEFT JOIN country ON city.countryId = " 
               + "country.countryId;");
    }
    
    public  String getAllHistoricalAppointmentsSQLString() {
        return ("SELECT appointment.appointmentId, appointment.title, "
               + "appointment.description, appointment.location, appointment.contact, "
               + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
               + "customer.customerName, address.addressId, address.address, " 
               + "address.address2, address.phone, address.postalCode, " 
               + "city.cityId, city.city, country.countryId, country.country " 
               + "FROM appointment LEFT JOIN customer on appointment.customerId = "
               + "customer.customerId LEFT JOIN address ON customer.addressId = " 
               + "address.addressID LEFT JOIN city ON address.cityId = " 
               + "city.cityId LEFT JOIN country ON city.countryId = " 
               + "country.countryId WHERE start < CURRENT_TIMESTAMP;");
}
    
    public  String getAllUpcomingAppointmentsSQLString() {
        return ("SELECT appointment.appointmentId, appointment.title, "
              + "appointment.description, appointment.location, appointment.contact, "
              + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
              + "customer.customerName, address.addressId, address.address, " 
              + "address.address2, address.phone, address.postalCode, " 
              + "city.cityId, city.city, country.countryId, country.country " 
              + "FROM appointment LEFT JOIN customer on appointment.customerId = "
              + "customer.customerId LEFT JOIN address ON customer.addressId = " 
              + "address.addressID LEFT JOIN city ON address.cityId = " 
              + "city.cityId LEFT JOIN country ON city.countryId = " 
              + "country.countryId WHERE start > CURRENT_TIMESTAMP;");
    }
    
    public  String getAllConsultantSchedulesSQLStatement(){
        return ("SELECT appointment.appointmentId, appointment.title, "
               + "appointment.description, appointment.location, appointment.contact, "
               + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
               + "customer.customerName, address.addressId, address.address, " 
               + "address.address2, address.phone, address.postalCode, " 
               + "city.cityId, city.city, country.countryId, country.country " 
               + "FROM appointment LEFT JOIN customer on appointment.customerId = "
               + "customer.customerId LEFT JOIN address ON customer.addressId = " 
               + "address.addressID LEFT JOIN city ON address.cityId = " 
               + "city.cityId LEFT JOIN country ON city.countryId = " 
               + "country.countryId WHERE start > CURRENT_TIMESTAMP ORDER BY appointment.contact DESC, appointment.start ASC;");
    }
    
    public  String getAllUserAppointmentsWeekSQLStatement(){
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfDay = currentTime.withHour(0).withMinute(0);
        ZonedDateTime startOfDayUCT = ZonedDateTime.of(startOfDay, ZoneId.of("UCT"));
        String today = startOfDayUCT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ZonedDateTime nextWeekTimeUCT = startOfDayUCT.plusDays(7);
        String nextWeek = nextWeekTimeUCT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return ("SELECT appointment.appointmentId, appointment.title, "
               + "appointment.description, appointment.location, appointment.contact, "
               + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
               + "customer.customerName, address.addressId, address.address, " 
               + "address.address2, address.phone, address.postalCode, " 
               + "city.cityId, city.city, country.countryId, country.country " 
               + "FROM appointment LEFT JOIN customer on appointment.customerId = "
               + "customer.customerId LEFT JOIN address ON customer.addressId = " 
               + "address.addressID LEFT JOIN city ON address.cityId = " 
               + "city.cityId LEFT JOIN country ON city.countryId = " 
               + "country.countryId WHERE appointment.contact = '" + userInfo.getUsername()
               + "' AND appointment.start >= CURRENT_TIMESTAMP AND appointment.start <= '" 
               + nextWeek +"';");
    }
    
    public  String getAllUserAppointmentsMonthSQLStatement(){
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfDay = currentTime.withHour(0).withMinute(0);
        ZonedDateTime startOfDayUCT = ZonedDateTime.of(startOfDay, ZoneId.of("UCT"));
        String today = startOfDayUCT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ZonedDateTime nextWeekMonthUCT = startOfDayUCT.plusMonths(1);
        String nextMonth = nextWeekMonthUCT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return ("SELECT appointment.appointmentId, appointment.title, "
               + "appointment.description, appointment.location, appointment.contact, "
               + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
               + "customer.customerName, address.addressId, address.address, " 
               + "address.address2, address.phone, address.postalCode, " 
               + "city.cityId, city.city, country.countryId, country.country " 
               + "FROM appointment LEFT JOIN customer on appointment.customerId = "
               + "customer.customerId LEFT JOIN address ON customer.addressId = " 
               + "address.addressID LEFT JOIN city ON address.cityId = " 
               + "city.cityId LEFT JOIN country ON city.countryId = " 
               + "country.countryId WHERE appointment.contact = '" + userInfo.getUsername()
               + "' AND appointment.start >= CURRENT_TIMESTAMP AND appointment.start <= '" 
               + nextMonth +"';");
    }
    
    public  String getAllUserAppointmentsTodaySQLStatement(){
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime startOfDay = currentTime.withHour(0).withMinute(0);
        ZonedDateTime startOfDayUCT = ZonedDateTime.of(startOfDay, ZoneId.of("UCT"));
        String today = startOfDayUCT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ZonedDateTime endOfDayUCT = startOfDayUCT.plusHours(24);
        String endOfDay = endOfDayUCT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return ("SELECT appointment.appointmentId, appointment.title, "
               + "appointment.description, appointment.location, appointment.contact, "
               + "appointment.url, appointment.start, appointment.end, customer.customerId, " 
               + "customer.customerName, address.addressId, address.address, " 
               + "address.address2, address.phone, address.postalCode, " 
               + "city.cityId, city.city, country.countryId, country.country " 
               + "FROM appointment LEFT JOIN customer on appointment.customerId = "
               + "customer.customerId LEFT JOIN address ON customer.addressId = " 
               + "address.addressID LEFT JOIN city ON address.cityId = " 
               + "city.cityId LEFT JOIN country ON city.countryId = " 
               + "country.countryId WHERE appointment.contact = '" + userInfo.getUsername()
               + "' AND appointment.start >= CURRENT_TIMESTAMP AND appointment.start <= '" 
               + endOfDay +"';");
    }
    
    public  String getAppointmentTypeSQLStatement() {
        return ("SELECT  title,  COUNT(title) FROM appointment WHERE start > CURRENT_TIMESTAMP GROUP BY title;");
    } 
    
    //Error Checking SQL
    public  String checkAppointmentConflict(Appointment a){
        String checkConflictSQL = ("SELECT * FROM appointment WHERE ((start >= '" +
            a.getStartTimeUTC() + "' AND end <= '" + a.getEndTimeUTC() 
            + "') OR (start < '" + a.getEndTimeUTC() + "' AND end >= '" 
            + a.getEndTimeUTC() + "') " + "OR (start <= '" + a.getStartTimeUTC() 
            + "' AND end > '" + a.getStartTimeUTC() + "')) AND contact = '" 
            + a.getContact() + "';");
        return checkConflictSQL;
    }
    
    public  String checkAppointmentModifyConflict(Appointment a){
        String checkConflictSQL = ("SELECT * FROM appointment WHERE ((start >= '" +
            a.getStartTimeUTC() + "' AND end <= '" + a.getEndTimeUTC() +
            "') OR (start < '" + a.getEndTimeUTC() + "' AND end >= '" + a.getEndTimeUTC() +
            "') OR (start <= '" + a.getStartTimeUTC() + "' AND end > '" + a.getStartTimeUTC() +
            "')) AND (contact = '" + a.getContact() + "') AND (appointmentId <> '" 
            + a.getAppointmentId() + "');");
        return checkConflictSQL;
    }
}
