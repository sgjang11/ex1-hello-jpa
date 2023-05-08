package helloJPA;

public class ValueMain {
    public static void main(String[] args) {

        Address address1 = new Address("city1", "street1", "zipcode1");
        Address address2 = new Address("city1", "street1", "zipcode1");

        System.out.println("address1 == address2 " + (address1.getStreet()==address2.getStreet()));
        System.out.println("address1 == address2 " + (address1==address2));
        System.out.println("address1 == address2 " + (address1.equals(address2)));


    }

}
