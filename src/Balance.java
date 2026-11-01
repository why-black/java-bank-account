import java.util.Objects;

public final class Balance {

    private final long kopecks;
    private final Currency currency;

    public Balance(
        long kopecks,
        Currency currency
    ){
        if (kopecks < 0) throw new IllegalArgumentException("Balance cant be less than zero");
        if (currency == null) throw new IllegalArgumentException("Currency cant be null");
        this.kopecks = kopecks;
        this.currency = currency;
    }
    public long getKopecks(){
        return kopecks;
    }
    public Currency getCurrency(){
        return currency;
    }
    public Balance add(Balance other){
        Objects.requireNonNull(other, "Another balance cant be null");

        if (!other.getCurrency().getCode().equals(this.getCurrency().getCode())) {
            throw new IllegalArgumentException("Curency codes arent equal");
        }
        long newKopecks = other.kopecks + this.kopecks;
        return new Balance(newKopecks, this.currency);
    }
    public Balance subtract(Balance other){
        Objects.requireNonNull(other, "Yo man, you wanna get it for free?");
        if (!other.getCurrency().getCode().equals(this.getCurrency().getCode())) {
            throw new IllegalArgumentException("Curency codes arent equal");
        }

        if (other.kopecks > this.kopecks) {
            throw new IllegalArgumentException("You need more money, sir");
        }
        long newKopecks = this.kopecks - other.kopecks;
        return new Balance(newKopecks, this.currency);
    }
    public Balance convertTo(Currency targetCurrency){
        Objects.requireNonNull(targetCurrency, "Currency cant be null");
        if (targetCurrency.equals(this.getCurrency())){
            return this;
        }
        long newKopecks = this.kopecks * this.currency.getPriceInRublesKopecks() 
            / targetCurrency.getPriceInRublesKopecks();
        return new Balance(newKopecks, targetCurrency);
    }
    @Override
    public String toString(){
        return String.format("%d.%02d %s", this.kopecks/100, this.kopecks % 100, this.currency.getSymbol());
    }
}
