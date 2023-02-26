package ir.brandimo.training.shop.error;

public class EntityExist extends RuntimeException{
    public EntityExist(String message) {
        super(message);
    }
}
