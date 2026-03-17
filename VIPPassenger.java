public class VIPPassenger extends Passenger {
    private String vipLevel;
    private double bonusDiscount;
    
    public VIPPassenger(String passengerId, String name, String email, String phone, String vipLevel) {
        super(passengerId, name, email, phone);
        this.vipLevel = vipLevel;
        this.bonusDiscount = calculateBonusDiscount();
    }
    
    @Override
    public double calculateDiscount() {
        double baseDiscount = 0.15; // 15% base discount for VIP
        return baseDiscount + bonusDiscount;
    }
    
    private double calculateBonusDiscount() {
        switch(vipLevel.toUpperCase()) {
            case "GOLD": return 0.05;
            case "PLATINUM": return 0.10;
            case "DIAMOND": return 0.15;
            default: return 0.03; // Default for other VIP levels
        }
    }
    
    @Override
    public String getPassengerType() {
        return "VIP Passenger (" + vipLevel + ")";
    }
    
    @Override
    public void sendNotification(String message) {
        System.out.println("⭐ VIP NOTIFICATION for " + name + " (" + vipLevel + " VIP): " + message);
    }
    
    public String getVipLevel() {
        return vipLevel;
    }
}