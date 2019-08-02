package dao;

import model.Person;

import java.util.Properties;

import java.sql.*;

import static java.lang.System.getProperty;

public class PersonDAOImpl implements PersonDAO throws SQLException{
        private String jdbcUrl;
        private String jdbcUsername;
        private String jdbcPassword;


        private final String ADD_PERSON="INSERT INTO person(personId,personName,personSurname) VALUES(?,?,?)";
        private final String UPDATE_PERSON="UPDATE person SET personName=?,personSurname=? WHERE personId=? ";
        private final String DELETE_USER="  DELETE FROM person WHERE personId=?";

        PersonDAOImpl(){
                setConnection();
        }
        private void setConnection(){
                public Properties prop=new Properties();
                public InputStream input=null;
                public Connection connection=null;
                public Statement statement=null;
                try{
                input=ClassLoader.getSystemClassLoader().getResourceAsStream("database.properties");
                prop.load(input);
                }catch(IOException io){
                        System.err.printl("input oluşturmada hata meydana geldi  HATA : "+io);
                }

        jdbcUrl = prop.getProperty("jdbc.url");
        jdbcUsername = prop.getProperty("jdbc.username");
        jdbcPassword = prop.getProperty("jdbc.password");
        try {
        connection = DriverManager.getConnection(jdbcUrl, properties);
        System.out.println("Veritababı bağlantısı kurulmuştur");
        } catch (Exception e) {
        System.err.println("Veritababı bağlantısı kurulamamıştır...  HATA : "+e);
        }
        }
        @Override
        public Person getPersonById(int personId){
                public ResultSet resultSet;
                while(resultSet.next()){
                int id=resultSet.getInt(personId);
                String isim=resultSet.getString("isim");
                String soyisim=resultSet.getString("soyisim");
                Person person=new Person(id,isim,soyisim);
                return person;
                }
        }
        @Override
        public void getPerson(Person person){
                try{
                System.out.println("Bağlantı kuruldu");
                while(resultSet.next()){
                System.out.println(resultSet.getString("id")+"    "+resultSet.getString("isim")+"   "+resultSet.getString("soyisim"));
                }
                }catch(SQLException exception){
                        System.err.println(exception);
                }finally{
                connection.close();
                }
        }
        @Override
        public static void updatePerson(Person person){
        try{
        execute(UPDATE_PERSON,person.getIsim(),person.getSoyisim(),person.getId());
        System.out.println("Kayit guncellendi.");

        }catch(SQLException exception){
        System.err.println(exception);
        }
        }
        @Override
        public void addPerson(int personId){
        try{
        execute(ADD_PERSON,person.getId(),person.getIsim(),person.getSoyisim());
        System.out.println("Kayit eklendi.");

        }catch(SQLException exception){
        System.err.println(exception);
        }
        }
        @Override
        public void deletePerson(int personId){
        try{
        execute(DELETE_USER,personId);
        System.out.println("Kayit silindi.");

        }catch(SQLException exception){
        System.err.println(exception);
        }
        }
        private ResultSet executeQuery(String sql,Object...queryParameters){
        try{
        PreparedStatement ps=connection.prepareStatement(sql);
        int index=1;
        if(queryParameters!=null){
        for(Object paramater:queryParameters){
        ps.setObject(index++,paramater);
        }
        }
        ResultSet resultSet=ps.executeQuery();
        return resultSet;
        }catch(Exception e){
        System.err.println("ResultSet functionunda hata meydana geldi  HATA :"+e);
        }
        return null;
        }
        }
        }
}