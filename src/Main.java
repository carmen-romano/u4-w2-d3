//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        //Creazione prodotti
        // BOOKS
        Product product1 = new Product( "Il signore degli anelli", "Books", 100.99);
        Product product2 = new Product("The Witcher", "Books", 199.99);
        Product product3 = new Product( "You Like It Darker", "Books", 99.99);
        Product product4 = new Product("Harry Potter", "Books", 399.99);
        Product product5 = new Product("Hunger Games", "Books", 49.99);
        // BABY
        Product product6 = new Product( "Tutina", "Baby", 100.99);
        Product product7 = new Product("Coperta", "Baby", 199.99);
        Product product8 = new Product( "Scarpe", "Baby", 99.99);
        Product product9 = new Product("Pagliacetto", "Baby", 399.99);
        // BOYS
        Product product10 = new Product( "Cappottino", "Boy", 140.99);
        Product product11 = new Product("Felpa", "Boy", 99.99);
        Product product12 = new Product( "Cardigan", "Boy", 49.99);
        Product product13 = new Product("Jeans", "Boy", 144.99);

        List<Product> allProduct = Arrays.asList(product1, product2, product3, product4, product5,product6,product7,product8,product9,product10,product11,product12,product13);


        //---------------ESERCIZIO 1---------------------------------//
        Predicate<Product> isPriceMoreThanFifty = product -> product.getPrice() > 100;
        Predicate<Product> isBooks = product -> product.getCategory().equals("Books");

        List<Product> isBookMoreThanFifty= allProduct.stream().filter(isBooks.and(isPriceMoreThanFifty)).toList();
        System.out.println("Prodotti di categoria books con un prezzo superiore a 100: " + isBookMoreThanFifty);
        //---------------ESERCIZIO 2---------------------------------//

        // Creazione clienti
        Customer customer1 = new Customer("Marco",1);
        Customer customer2 = new Customer("Paolo",2);
        Customer customer3 = new Customer("Mario",2);

        // Creazione ordini
        Order order1 = new Order(OrderStatus.PROCESSING, LocalDate.now(), LocalDate.now().plusDays(3), Arrays.asList(product2, product3,product6),customer1);
        Order order2 = new Order(OrderStatus.SHIPPED, LocalDate.now().minusDays(1), LocalDate.now().plusDays(2), Arrays.asList(product5, product10), customer2);
        Order order3 = new Order(OrderStatus.SHIPPED, LocalDate.parse("2021-03-01"), LocalDate.now().plusDays(2), Arrays.asList(product5, product10), customer2);


        List<Order> AllOrders = Arrays.asList(order1, order2,order3);
        List<Order> ordiniPerCategoriaBaby = AllOrders.stream().filter(ordine -> ordine.getProducts().stream().anyMatch(prodotto -> prodotto.getCategory().equals("Baby"))).toList();

        System.out.println("Ordini categoria baby: "+ ordiniPerCategoriaBaby);
        //---------------ESERCIZIO 3---------------------------------//
        System.out.println("prezzo prima dello sconto: "+ product10.getPrice());
        allProduct.stream().filter(product -> product.getCategory().equals("Boy")).forEach(product -> {
                    double sconto = (product.getPrice() * 10) / 100;
                    double nuovoPrezzo = product.getPrice() - sconto;
                    product.setPrice(nuovoPrezzo);
                });
        System.out.println("prezzo dopo lo sconto: "+product10.getPrice());

        //---------------ESERCIZIO 4---------------------------------//
        List<Order> ordiniCustomerTier2 = AllOrders.stream().filter(ordine -> ordine.getCustomer().getTier() == 2).toList();
        System.out.println("ordini customer tier 2"+ordiniCustomerTier2);
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        List<Order> ordiniDate = AllOrders.stream().filter(order -> order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate)).toList();
        System.out.println("ordini compresi fra 02/2021 e 04/2024 customer Tier2"+ordiniDate);
    }

}
