package software2project.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

public class SQLExecute {
    private final SessionInfo user = SessionInfo.getUser();
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();
    SQLStatementFactory sql = new SQLStatementFactory();
    
    public void getCustomerListAll() throws Exception{
        Statement statement = connectionManager.getConnection().createStatement();
        ResultSet results = statement.executeQuery(sql.getAllCustomersSQLString());

        while (results.next()){
            Customer temp = new Customer();
            temp.setCustomerId(results.getInt(1));
            temp.setCustomerName(results.getString(2));
            temp.setAddressId(results.getInt(3));
            temp.setAddress(results.getString(4));
            temp.setAddress2(results.getString(5));
            temp.setPhone(results.getString(6));
            temp.setPostalCode(results.getString(7));
            temp.setCityId(results.getInt(8));
            temp.setCity(results.getString(9));
            temp.setCountryId(results.getInt(10));
            temp.setCountry(results.getString(11));       
            user.getCustomerList().add(temp);
        }
        
    }
    
    public void getAppointmentListAll() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet allAppointmentResults = loadAllAppointments.executeQuery(sql.getAllAppointmentsSQLString());
        
        while (allAppointmentResults.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(allAppointmentResults.getInt(9));
            tempCustomer.setCustomerName(allAppointmentResults.getString(10));
            tempCustomer.setAddressId(allAppointmentResults.getInt(11));
            tempCustomer.setAddress(allAppointmentResults.getString(12));
            tempCustomer.setAddress2(allAppointmentResults.getString(13));
            tempCustomer.setPhone(allAppointmentResults.getString(14));
            tempCustomer.setPostalCode(allAppointmentResults.getString(15));
            tempCustomer.setCityId(allAppointmentResults.getInt(16));  
            tempCustomer.setCity(allAppointmentResults.getString(17));
            tempCustomer.setCountryId(allAppointmentResults.getInt(18));
            tempCustomer.setCountry(allAppointmentResults.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(allAppointmentResults.getInt(1));
            tempAppointment.setTitle(allAppointmentResults.getString(2));
            tempAppointment.setDescription(allAppointmentResults.getString(3));
            tempAppointment.setLocation(allAppointmentResults.getString(4));
            tempAppointment.setContact(allAppointmentResults.getString(5));
            tempAppointment.setURL(allAppointmentResults.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(allAppointmentResults.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(allAppointmentResults.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
    }
    
    public void getAllUpcomingAppointments() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet allAppointmentResults = loadAllAppointments.executeQuery(sql.getAllUpcomingAppointmentsSQLString());
        
        while (allAppointmentResults.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(allAppointmentResults.getInt(9));
            tempCustomer.setCustomerName(allAppointmentResults.getString(10));
            tempCustomer.setAddressId(allAppointmentResults.getInt(11));
            tempCustomer.setAddress(allAppointmentResults.getString(12));
            tempCustomer.setAddress2(allAppointmentResults.getString(13));
            tempCustomer.setPhone(allAppointmentResults.getString(14));
            tempCustomer.setPostalCode(allAppointmentResults.getString(15));
            tempCustomer.setCityId(allAppointmentResults.getInt(16));  
            tempCustomer.setCity(allAppointmentResults.getString(17));
            tempCustomer.setCountryId(allAppointmentResults.getInt(18));
            tempCustomer.setCountry(allAppointmentResults.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(allAppointmentResults.getInt(1));
            tempAppointment.setTitle(allAppointmentResults.getString(2));
            tempAppointment.setDescription(allAppointmentResults.getString(3));
            tempAppointment.setLocation(allAppointmentResults.getString(4));
            tempAppointment.setContact(allAppointmentResults.getString(5));
            tempAppointment.setURL(allAppointmentResults.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(allAppointmentResults.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(allAppointmentResults.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
    }
    
    public void getAllHistoricalAppointments() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet allAppointmentResults = loadAllAppointments.executeQuery(sql.getAllHistoricalAppointmentsSQLString());
        
        while (allAppointmentResults.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(allAppointmentResults.getInt(9));
            tempCustomer.setCustomerName(allAppointmentResults.getString(10));
            tempCustomer.setAddressId(allAppointmentResults.getInt(11));
            tempCustomer.setAddress(allAppointmentResults.getString(12));
            tempCustomer.setAddress2(allAppointmentResults.getString(13));
            tempCustomer.setPhone(allAppointmentResults.getString(14));
            tempCustomer.setPostalCode(allAppointmentResults.getString(15));
            tempCustomer.setCityId(allAppointmentResults.getInt(16));  
            tempCustomer.setCity(allAppointmentResults.getString(17));
            tempCustomer.setCountryId(allAppointmentResults.getInt(18));
            tempCustomer.setCountry(allAppointmentResults.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(allAppointmentResults.getInt(1));
            tempAppointment.setTitle(allAppointmentResults.getString(2));
            tempAppointment.setDescription(allAppointmentResults.getString(3));
            tempAppointment.setLocation(allAppointmentResults.getString(4));
            tempAppointment.setContact(allAppointmentResults.getString(5));
            tempAppointment.setURL(allAppointmentResults.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(allAppointmentResults.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(allAppointmentResults.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
    }
    
    public void getConsultantSchedules() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet allAppointmentResults = loadAllAppointments.executeQuery(sql.getAllConsultantSchedulesSQLStatement());
        
        while (allAppointmentResults.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(allAppointmentResults.getInt(9));
            tempCustomer.setCustomerName(allAppointmentResults.getString(10));
            tempCustomer.setAddressId(allAppointmentResults.getInt(11));
            tempCustomer.setAddress(allAppointmentResults.getString(12));
            tempCustomer.setAddress2(allAppointmentResults.getString(13));
            tempCustomer.setPhone(allAppointmentResults.getString(14));
            tempCustomer.setPostalCode(allAppointmentResults.getString(15));
            tempCustomer.setCityId(allAppointmentResults.getInt(16));  
            tempCustomer.setCity(allAppointmentResults.getString(17));
            tempCustomer.setCountryId(allAppointmentResults.getInt(18));
            tempCustomer.setCountry(allAppointmentResults.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(allAppointmentResults.getInt(1));
            tempAppointment.setTitle(allAppointmentResults.getString(2));
            tempAppointment.setDescription(allAppointmentResults.getString(3));
            tempAppointment.setLocation(allAppointmentResults.getString(4));
            tempAppointment.setContact(allAppointmentResults.getString(5));
            tempAppointment.setURL(allAppointmentResults.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(allAppointmentResults.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(allAppointmentResults.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
    }
    
    public void getUserTodaySchedule() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet results = loadAllAppointments.executeQuery(sql.getAllUserAppointmentsTodaySQLStatement());
        
        while (results.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(results.getInt(9));
            tempCustomer.setCustomerName(results.getString(10));
            tempCustomer.setAddressId(results.getInt(11));
            tempCustomer.setAddress(results.getString(12));
            tempCustomer.setAddress2(results.getString(13));
            tempCustomer.setPhone(results.getString(14));
            tempCustomer.setPostalCode(results.getString(15));
            tempCustomer.setCityId(results.getInt(16));  
            tempCustomer.setCity(results.getString(17));
            tempCustomer.setCountryId(results.getInt(18));
            tempCustomer.setCountry(results.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(results.getInt(1));
            tempAppointment.setTitle(results.getString(2));
            tempAppointment.setDescription(results.getString(3));
            tempAppointment.setLocation(results.getString(4));
            tempAppointment.setContact(results.getString(5));
            tempAppointment.setURL(results.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(results.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(results.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
        
    }
    
    public void getUserWeekSchedule() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet allAppointmentResults = loadAllAppointments.executeQuery(sql.getAllUserAppointmentsWeekSQLStatement());
        
        while (allAppointmentResults.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(allAppointmentResults.getInt(9));
            tempCustomer.setCustomerName(allAppointmentResults.getString(10));
            tempCustomer.setAddressId(allAppointmentResults.getInt(11));
            tempCustomer.setAddress(allAppointmentResults.getString(12));
            tempCustomer.setAddress2(allAppointmentResults.getString(13));
            tempCustomer.setPhone(allAppointmentResults.getString(14));
            tempCustomer.setPostalCode(allAppointmentResults.getString(15));
            tempCustomer.setCityId(allAppointmentResults.getInt(16));  
            tempCustomer.setCity(allAppointmentResults.getString(17));
            tempCustomer.setCountryId(allAppointmentResults.getInt(18));
            tempCustomer.setCountry(allAppointmentResults.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(allAppointmentResults.getInt(1));
            tempAppointment.setTitle(allAppointmentResults.getString(2));
            tempAppointment.setDescription(allAppointmentResults.getString(3));
            tempAppointment.setLocation(allAppointmentResults.getString(4));
            tempAppointment.setContact(allAppointmentResults.getString(5));
            tempAppointment.setURL(allAppointmentResults.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(allAppointmentResults.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(allAppointmentResults.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
        
    }
    
    public void getUserMonthSchedule() throws Exception{
        user.clearListData();
             
        Statement loadAllAppointments = connectionManager.getConnection().createStatement();
        ResultSet allAppointmentResults = loadAllAppointments.executeQuery(sql.getAllUserAppointmentsMonthSQLStatement());
        
        while (allAppointmentResults.next()){
            Customer tempCustomer = new Customer();
            
            tempCustomer.setCustomerId(allAppointmentResults.getInt(9));
            tempCustomer.setCustomerName(allAppointmentResults.getString(10));
            tempCustomer.setAddressId(allAppointmentResults.getInt(11));
            tempCustomer.setAddress(allAppointmentResults.getString(12));
            tempCustomer.setAddress2(allAppointmentResults.getString(13));
            tempCustomer.setPhone(allAppointmentResults.getString(14));
            tempCustomer.setPostalCode(allAppointmentResults.getString(15));
            tempCustomer.setCityId(allAppointmentResults.getInt(16));  
            tempCustomer.setCity(allAppointmentResults.getString(17));
            tempCustomer.setCountryId(allAppointmentResults.getInt(18));
            tempCustomer.setCountry(allAppointmentResults.getString(19));
            
            Appointment tempAppointment = new Appointment();
            
            tempAppointment.setCustomer(tempCustomer);
            tempAppointment.setAppointmentId(allAppointmentResults.getInt(1));
            tempAppointment.setTitle(allAppointmentResults.getString(2));
            tempAppointment.setDescription(allAppointmentResults.getString(3));
            tempAppointment.setLocation(allAppointmentResults.getString(4));
            tempAppointment.setContact(allAppointmentResults.getString(5));
            tempAppointment.setURL(allAppointmentResults.getString(6));
                      
            ZonedDateTime UTCTimeStart = LocalDateTime.parse(allAppointmentResults.getString(7), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime startTime = UTCTimeStart.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setStartTime(startTime);
            ZonedDateTime UTCTimeEnd = LocalDateTime.parse(allAppointmentResults.getString(8), 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")).atZone(ZoneId.of("UTC"));
            ZonedDateTime endTime = UTCTimeEnd.withZoneSameInstant(ZoneOffset.systemDefault());
            tempAppointment.setEndTime(endTime);
            
            user.getAppointmentList().add(tempAppointment);
        }
        
    }
    
    public void addCustomer(Customer c) throws Exception{
        Statement statement = connectionManager.getConnection().createStatement();
        user.setSelectedCustomer(c);
        String SQLAddCountry = sql.getAddCountrySQLString(user.getSelectedCustomer());
        if (SQLAddCountry != null){
            statement.execute(SQLAddCountry);
        }
        String SQLAddCity = sql.getAddCitySQLString(user.getSelectedCustomer());
        if (SQLAddCity != null){
            statement.execute(SQLAddCity);
        } 
        String SQLAddAddress = sql.getAddAddressSQLString(user.getSelectedCustomer());
        if (SQLAddAddress != null){
            statement.execute(SQLAddAddress);
        }
        String SQLAddCustomer = sql.getAddCustomerSQLString(user.getSelectedCustomer());
        if (SQLAddCustomer != null){
            statement.execute(SQLAddCustomer);
        }
        
    }
    
    public void deleteCustomer(Customer c) throws Exception{
        Runnable execute = () -> {
            try{
        Statement statement = connectionManager.getConnection().createStatement();
        user.setSelectedCustomer(c);
        String SQLDeleteCustomer = sql.getDeleteCustomerSQLString(c);
        statement.execute(SQLDeleteCustomer);
            } catch (SQLException se){
                Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setHeaderText("SQL Exception");
                alert.setContentText("SQL Exception");
                alert.show();
            };
            errorMessage.run();
            }
        };
         execute.run();     
    }
    
    public void modifyCustomer(Customer c) throws Exception{
        
        Statement statement = connectionManager.getConnection().createStatement();
        
        String SQLAddCountry = sql.getAddCountrySQLString(user.getSelectedCustomer());
        if (SQLAddCountry != null){
            statement.execute(SQLAddCountry);
        }
        String SQLAddCity = sql.getAddCitySQLString(user.getSelectedCustomer());
        if (SQLAddCity != null){
            statement.execute(SQLAddCity);
        } 
        String SQLAddAddress = sql.getModifyAddressSQLString();
        if (SQLAddAddress != null){
            statement.execute(SQLAddAddress);
        }
        String SQLAddCustomer = sql.getModifyCustomerSQLString();
        if (SQLAddCustomer != null){
            statement.execute(SQLAddCustomer);
        }
        
    }
    
    public void addAppointment(Appointment a) throws Exception{
        Runnable execute = () -> {
            try{
                Statement statement = connectionManager.getConnection().createStatement();
                user.setSelectedAppointment(a);

                String SQLAddAppointment = sql.getAddAppointmentSQLString(user.getSelectedAppointment());
                statement.execute(SQLAddAppointment);
            } catch (SQLException se) {
                 Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setHeaderText("SQL Exception");
                alert.setContentText("SQL Exception");
                alert.show();
            };
            errorMessage.run();
            }
        };
         execute.run();
            
    }
    
    public void modifyAppointment(Appointment a) throws Exception{
        Runnable execute = () -> {
            try{
                Statement updateAppointment = connectionManager.getConnection().createStatement();
            String appointmentUpdateSQL = ("UPDATE appointment SET title ='" + a.getTitle() 
                + "', description = '" + a.getDescription() + "', location = '"
                + a.getLocation() + "', url = '" + a.getURL()
                + "', start = '" + a.getStartTimeUTC() + "', end = '" 
                + a.getEndTimeUTC() + "', lastUpdate = CURRENT_TIMESTAMP, lastUpdateBy = '" 
                + user.getUsername() + "' WHERE appointmentId = '" + user.getSelectedAppointment().getAppointmentId()
                +"';");
            updateAppointment.execute(appointmentUpdateSQL);
            }  catch (SQLException se) {
                Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setHeaderText("SQL Exception");
                alert.setContentText("SQL Exception");
                alert.show();
            };
            errorMessage.run();
            }
        };
         execute.run();
    }
    
    public boolean checkAppointmentConflict(Appointment a) throws Exception{
        Statement checkConflictStatement = connectionManager.getConnection().createStatement();
        String checkConflictSQL = sql.checkAppointmentConflict(a);
        ResultSet conflicts = checkConflictStatement.executeQuery(checkConflictSQL);
        return !conflicts.next();
    }
    
    public boolean checkModifyAppointmentConflict(Appointment a) throws Exception{
        Statement checkConflictStatement = connectionManager.getConnection().createStatement();
        String checkConflictSQL = sql.checkAppointmentModifyConflict(a);
        ResultSet conflicts = checkConflictStatement.executeQuery(checkConflictSQL);
        return !conflicts.next();
    }
    
    public void deleteAppointment(Appointment a) throws Exception{
        
        Runnable execute = () -> {
            try {
            Statement statement = connectionManager.getConnection().createStatement();
            user.setSelectedAppointment(a);
            String SQLDeleteAppointment = sql.getDeleteAppointmentSQLString(a);
            statement.execute(SQLDeleteAppointment); 
            } catch (SQLException se) {
                Runnable errorMessage = () -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Exception");
                alert.setHeaderText("SQL Exception");
                alert.setContentText("SQL Exception");
                alert.show();
            };
            errorMessage.run();
            }
        };
        
        execute.run();
        
        
    }
}

