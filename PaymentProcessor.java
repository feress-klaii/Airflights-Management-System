public class PaymentProcessor {
    
    public static boolean processCreditCardPayment(String cardNumber, String cardHolder, 
                                                  String expiryDate, String cvv, double amount) {
        System.out.println("💳 Processing credit card payment...");
        System.out.println("Card Holder: " + cardHolder);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        // Simulate payment processing
        try {
            Thread.sleep(1000); // Simulate processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("✓ Payment approved!");
        return true;
    }
    
    public static boolean processUPIPayment(String upiId, double amount) {
        System.out.println("📱 Processing UPI payment...");
        System.out.println("UPI ID: " + upiId);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        // Simulate payment processing
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("✓ Payment successful!");
        return true;
    }
    
    public static boolean processCashPayment(double amount) {
        System.out.println("💰 Processing cash payment...");
        System.out.println("Amount: $" + String.format("%.2f", amount));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("✓ Cash payment received!");
        return true;
    }
}