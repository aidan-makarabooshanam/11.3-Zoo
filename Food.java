
// TODO: make Food abstract
// TODO: extend Entity
public abstract class Food extends Entity{
    protected boolean isAnimalProduct;
    protected int nutritionValue;
    protected boolean isVegetableProduct;
    // TODO: add instance variables
    public Food(String name, int x, int y, boolean isAnimalProduct, boolean isVegetableProduct, int nutritionValue) {
        super(name, x, y);
        this.isAnimalProduct=isAnimalProduct;
        this.isVegetableProduct=isVegetableProduct;
        this.nutritionValue=nutritionValue;
    }
    // TODO: add constructor
    public abstract void beEaten(Animal eater);
    // TODO: add abstract method beEaten(Animal eater)

    // TODO: add non-abstract methods as needed

}
